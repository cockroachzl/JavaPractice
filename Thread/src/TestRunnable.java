
public class TestRunnable {
	public static void main(String[] args) {
		Runnable ping = new RunPingPong("PingPing", 33);
		Runnable pong = new RunPingPong("PongPong", 44);
		new PingPong("Ping", 33, ping).start();
		new PingPong("Pong", 44, pong).start();
	}
}

class RunPingPong implements Runnable {
	private String word;
	private int delay;
	
	RunPingPong(String whatToSay, int delayTime) {
		word = whatToSay;
		delay = delayTime;
	}
	
	public void run() {
		try {
			for (int i = 0; i < 30; ++i) {
				System.out.print(word + " ");
				Thread.sleep(delay);
			}
		} catch (InterruptedException ex) {
			return;
		}
	}
}