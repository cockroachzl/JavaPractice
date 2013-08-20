import java.rmi.*;
public interface MyRemoteInterface extends Remote{
	public String sayHello(int id) throws RemoteException;
}
