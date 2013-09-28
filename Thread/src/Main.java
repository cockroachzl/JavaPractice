import javadoc.BeeperControl;


public class Main {

	public static void main(String[] args) {
		BeeperControl bc = new BeeperControl();
		bc.beep(); //non-blocking
		System.out.println("exit");
	}

}
