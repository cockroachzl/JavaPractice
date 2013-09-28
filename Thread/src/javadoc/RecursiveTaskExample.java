package javadoc;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskExample {

	public static void main(String[] args) {
		final ForkJoinPool mainPool = new ForkJoinPool();
		int num = mainPool.invoke(new Fibonacci(3));
		System.out.println(num);
	}

}

class Fibonacci extends RecursiveTask<Integer> {
	   final int n;
	   Fibonacci(int n) { this.n = n; }
	   public Integer compute() {
	     if (n <= 1)
	        return n;
	     Fibonacci f1 = new Fibonacci(n - 1);
	     f1.fork();
	     Fibonacci f2 = new Fibonacci(n - 2);
	     return f2.compute() + f1.join();
	   }
	 }