
import java.rmi.*;
import java.rmi.server.*;
public class FabProductImpl extends UnicastRemoteObject implements FabProductInterface{
   public FabProductImpl()throws RemoteException {}; 
   public ProductInterface newProduct() throws RemoteException {       
   return new Product();}
}

