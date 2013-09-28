package effective.java.concurrent;

import java.util.concurrent.TimeUnit;

public class VolatileStopThread {
	private static volatile boolean stopRequested;
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!stopRequested)
					i++;
				System.out.println(Thread.currentThread().getName() + " stopped");
			}
		});
		
		backgroundThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		stopRequested = true;
		System.out.println(Thread.currentThread().getName() + " stopped");
	}

}