import java.rmi.*;
import java.rmi.server.*;

public class MyRemoteImpl extends UnicastRemoteObject implements MyRemoteInterface {
	public String sayHello(int id) {
		return "Server say, Hello client " + id;
	}
	
	public MyRemoteImpl() throws RemoteException {}
	
	public static void main(String[] args){
		try {
			MyRemoteInterface service = new MyRemoteImpl();
			Naming.rebind("Remote_Hello", service);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
