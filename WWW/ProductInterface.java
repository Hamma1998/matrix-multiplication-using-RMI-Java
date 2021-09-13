
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface ProductInterface extends Remote 
{
	int[][] ProductMatrix( int[][] m1, int[][] m2, int debI, int finI, int debJ, int finJ, int comm) throws RemoteException;
}

