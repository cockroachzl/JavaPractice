package javadoc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionExample {

	static long[] array = new long[15];
	public static void main(String[] args) {
		final ForkJoinPool mainPool = new ForkJoinPool();
		mainPool.invoke(new IncrementTask(array, 0, array.length));
		
		for (long i : array)
			System.out.println(i);
	}

}

class IncrementTask extends RecursiveAction {
	final long[] array;
	final int lo;
	final int hi;
	static final int THRESHOLD = 5;

	IncrementTask(long[] array, int lo, int hi) {
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}

	protected void compute() {
		if (hi - lo < THRESHOLD) {
			for (int i = lo; i < hi; ++i)
				array[i]++;
		} else {
			int mid = (lo + hi) >>> 1;
			invokeAll(new IncrementTask(array, lo, mid), new IncrementTask(
					array, mid, hi));
		}
	}
}