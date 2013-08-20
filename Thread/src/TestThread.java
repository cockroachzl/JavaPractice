
public class TestThread {
	public static void main(String[] args){
		System.out.println(Thread.currentThread().getName());
		new PingPong("Ping", 33, null	).start();
		new PingPong("Pong", 100, null).start();
	}
}

class PingPong extends Thread {
	private String word;
	private int delay;
	
	public PingPong(String whatToSay, int delayTime, Runnable target){
		super(target);
		word = whatToSay;
		delay = delayTime;
	}
	
	
	
	public void run() {
		try {
			for(int i = 0; i < 30; ++i) {
				System.out.print(word + " ");
				Thread.sleep(delay); 
			} 
		} catch (InterruptedException ex) {
			return;
		}
	}
}
