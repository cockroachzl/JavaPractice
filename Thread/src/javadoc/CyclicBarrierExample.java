package javadoc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {

}

class Solver {
	final int N;
	final float[][] data;
	final CyclicBarrier barrier;

	class Worker implements Runnable {
		int myRow;
		boolean isDone = false;

		Worker(int row) {
			myRow = row;
		}

		public void run() {
			while (!done()) {
				processRow(myRow);

				try {
					barrier.await();
				} catch (InterruptedException ex) {
					return;
				} catch (BrokenBarrierException ex) {
					return;
				}
			}
		}

		private boolean done() {
			return isDone;
		}

		private void processRow(int myRow2) {
			
			
		}
	}

	public Solver(float[][] matrix) {
	     data = matrix;
	     N = matrix.length;
	     barrier = new CyclicBarrier(N,
	                                 new Runnable() {
	                                   public void run() {
	                                     mergeRows(...);
	                                   }
	                                 });
	     for (int i = 0; i < N; ++i)
	       new Thread(new Worker(i)).start();

	     waitUntilDone();
	   }
}