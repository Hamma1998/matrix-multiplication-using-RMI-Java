
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;
public class ProductClient {
	
	public ProductClient (String [] args) {


	    try{

			int[][] mat1;
			int[][] mat2;
			int lig=0,col=0,lig2=0,col2=0;
			Scanner sc=new Scanner(System.in);
			while (lig ==0 || col==0 || lig2==0 || col2==0 || col != lig2) {


				System.out.println("taille ligne");
				lig=sc.nextInt();
				System.out.println("taille colonne");
				col=sc.nextInt();
				System.out.println("taille ligne 2");
				lig2=sc.nextInt();
				System.out.println("taille colonne 2 ");
				col2=sc.nextInt();

			}
			System.out.println("entrer les valeurs de la premiere matrice");
			mat1 = new int[lig][col];
			mat1 = lire_matrice(lig,col);

			System.out.println("entrer les valeurs de la deuxieme matrice");
			mat2 = new int[lig2][col2];
			mat2 = lire_matrice(lig2,col2);
	    	
		  if(System.getSecurityManager() == null) 
			System.setSecurityManager(new RMISecurityManager());
	          Registry reg = LocateRegistry.getRegistry("localhost",1099); 
		  FabProductInterface fabrique = (FabProductInterface) reg.lookup("Fabrique");
		  
	          ProductInterface ProductObj1;
	          ProductObj1= (ProductInterface)fabrique.newProduct();
	          ProductInterface ProductObj2; 
	          ProductObj2= (ProductInterface)fabrique.newProduct();
	          ProductInterface ProductObj3; 
	          ProductObj3= (ProductInterface)fabrique.newProduct();
	          ProductInterface ProductObj4; 
	          ProductObj4= (ProductInterface)fabrique.newProduct();
	          
	          int[][] result1 = ProductObj1.ProductMatrix(mat1,mat2,0,lig/2,  0 , col2/2,col);
	          int[][] result2 = ProductObj2.ProductMatrix(mat1,mat2,0,lig/2,col2/2,col2,col);
	          int[][] result3 = ProductObj3.ProductMatrix(mat1,mat2,lig/2,lig,0,col2/2,col);
	          int[][] result4 = ProductObj4.ProductMatrix(mat1,mat2,lig/2,lig,col2/2,col2,col);

			System.out.println("Le produit des deux matrices est :\n");
			afficherMatrice(assembler(result1,result2,result3,result4,lig,col2),lig,col2);
	          
	     }    
	     catch (Exception e) {
		  System.out.println ("Erreur d'acces a l'objet distant.");
		  System.out.println (e.toString());
	          }
	}
	
	public int[][] lire_matrice(int lig,int col) {

		Scanner sc=new Scanner(System.in);
		int[][] t;
		int i, j ;

		t = new int[lig][col] ;

		for (i = 0 ; i < t.length ; i = i + 1 )
		{   for (j = 0 ; j < t[0].length ; j = j + 1 ) 
		    {
		        System.out.print(" [" + (i+1) + "," + (j+1) + "]:") ;
		        t[i][j] = sc.nextInt();
		        }
		          
		}

		return t;

		}
	public int[][] assembler( int[][] a ,int[][] b,int[][] c, int[][] d,int lig,int col){		
		int[][] result = new int[lig][col];
		
		for(int i=0; i<lig;i++) {
			for(int j=0; j<col;j++) {
				if (i<lig/2) {
					if(j<col/2) {
						result[i][j] = a[i][j];
					} else {
						result[i][j] = b[i][j];
					}
				}else {
					if(j<col/2) {
						result[i][j] = c[i][j];
					} else {
						result[i][j] = d[i][j];
					}
				}
			}
		}
		return result;
	}
	
	public void afficherMatrice(int[][] m, int lig,int col2) {
			for (int i=0;i<lig;i++) {
				for (int j=0;j<col2;j++) {
					System.out.print(m[i][j] + "  ");
				}
				System.out.print("\n");
			}
	}
}
