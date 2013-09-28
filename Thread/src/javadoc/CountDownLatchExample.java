package javadoc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {
	public static void main(String[] args) {
		try {
			new Driver2(5).main();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Driver { 
	private final int N;
	Driver(int nWorkers) {
		N = nWorkers;
	}
	void main() throws InterruptedException {
		CountDownLatch startSignal = new CountDownLatch(1);
		CountDownLatch doneSignal = new CountDownLatch(N);

		for (int i = 0; i < N; ++i)
			// create and start threads
			new Thread(new Worker(startSignal, doneSignal)).start();

		doSomethingElse(); // don't let run yet
		startSignal.countDown(); // let all threads proceed
		doSomethingElse();
		doneSignal.await(); // wait for all to finish
	}
	void doSomethingElse() throws InterruptedException{
		System.out.println("Driver is sleeping ... ");
		TimeUnit.SECONDS.sleep(3);
		System.out.println("Driver is waking up ... ");
	}
}

class Worker implements Runnable {
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;

	Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
	}

	public void run() {
		try {
			startSignal.await();
			doWork();
			doneSignal.countDown();
		} catch (InterruptedException ex) {
		} // return;
	}

	void doWork() { System.out.println(Thread.currentThread().getName() + " is working."); }
}

class Driver2 { 
	private final int N;
	Driver2(int nWorkers) { N = nWorkers; }
	void main() throws InterruptedException {
	     CountDownLatch doneSignal = new CountDownLatch(N);
	     Executor e = Executors.newFixedThreadPool(N);

	     for (int i = 0; i < N; ++i) // create and start threads
	       e.execute(new WorkerRunnable(doneSignal, i));

	     doneSignal.await();           // wait for all to finish
	   }
}

class WorkerRunnable implements Runnable {
	private final CountDownLatch doneSignal;
	private final int i;

	WorkerRunnable(CountDownLatch doneSignal, int i) {
		this.doneSignal = doneSignal;
		this.i = i;
	}

	public void run() {
		doWork(i);
		doneSignal.countDown();
	}

	void doWork(int i) { System.out.println(Thread.currentThread().getName() + " is working on task " + i + "."); }
}