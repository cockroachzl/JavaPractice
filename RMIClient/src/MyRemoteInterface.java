import java.rmi.Remote;
import java.rmi.RemoteException;
public interface MyRemoteInterface extends Remote{
	public String sayHello(int id) throws RemoteException;
}
