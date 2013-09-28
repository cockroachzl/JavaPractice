package effective.java.concurrent;

import java.util.concurrent.TimeUnit;

public class StopThread {
	private static boolean stopRequested;
	private static synchronized void requestStop() {
		stopRequested = true;
	}
	private static synchronized boolean isStopRequested() {
		return stopRequested;
	}
	public static void main(String[] args) throws InterruptedException {
		Thread backgroundThread = new Thread(new Runnable() {
			public void run() {
				int i = 0;
				while (!isStopRequested())
					i++;
				System.out.println(Thread.currentThread().getName() + " stopped.");
			}
		});
		
		backgroundThread.start();
		TimeUnit.SECONDS.sleep(3);
		requestStop();
		System.out.println(Thread.currentThread().getName() + " stopped.");
	}
}
