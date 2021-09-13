

import java.rmi.*;
import java.rmi.server.*;
public class Product extends UnicastRemoteObject implements ProductInterface 
{
       public Product() throws RemoteException
       {
	    super();
       }
	public  int[][] ProductMatrix( int[][] m1, int[][] m2, int debI, int finI, int debJ, int finJ, int comm)throws RemoteException {
		int C[][] = new int[finI][finJ];
		for(int i=debI; i<finI; i++){
			for(int j=debJ; j<finJ; j++) {
				C[i][j] = 0;
				for (int k=0; k<comm; k++) {
					C[i][j] += m1[i][k] * m2[k][j];
				}
			}

		}
		return C;
	}

}
