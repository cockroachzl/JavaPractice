import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;


public class ExecutorFrameworkTest {

	@Test
	public void testExecutorService() throws InterruptedException, ExecutionException{
		int nTasks = 3;
		ExecutorService es = Executors.newFixedThreadPool(10);
		List<Future<String> > results = new ArrayList<>();
		for (int i = 0; i < nTasks; ++i) {
			final int sleeptime = 5 - i;
			Future<String> result = es.submit(new Callable<String>() {
				public String call() throws InterruptedException {
					TimeUnit.SECONDS.sleep(sleeptime);
					return Thread.currentThread().getName();
				}
			});
			results.add(result);
		}
		List<Boolean> tasksDone = Arrays.asList(false, false, false);
		int nTasksdone = 0;
		while(nTasksdone != nTasks)
		for (int i = 0; i < nTasks; ++i) {
			if (!tasksDone.get(i) && results.get(i).isDone() ) {
				++nTasksdone;
				tasksDone.set(i, true);
				System.out.println(results.get(i).get());
			}
		}
	}
	
	@Test
	public void testExecutorService2() {
		
	}
}
