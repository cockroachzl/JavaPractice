
public class TestAbstract {
	public static void main(String[] args) {
		int count = Integer.parseInt(args[0]);
		long time = new MethodBenchmark().repeat(count);
		System.out.println(count + "methods in " + time + " nanoseconds");
		time = new LoopBenchmark().repeat(1);
		System.out.println("1000000 loops in " + time + " nanoseconds");
	}
}

abstract class Benchmark {
	abstract void benchmark();
	public final long repeat(int count) {
		long start  = System.nanoTime();
		for (int i = 0; i < count; ++i) 
			benchmark();
		return System.nanoTime() - start;	
	}
}

class MethodBenchmark extends Benchmark {
	void benchmark() {};
	
}

class LoopBenchmark extends Benchmark {
	int count = 1000000;
	void benchmark() {
		for(int i = 0; i < count; ++i);
	}
}