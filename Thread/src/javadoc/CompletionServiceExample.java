package javadoc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;

public class CompletionServiceExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	<Result> void solve(Executor e, Collection<Callable<Result>> solvers)
			throws InterruptedException, ExecutionException {
		CompletionService<Result> ecs = new ExecutorCompletionService<Result>(e);
		for (Callable<Result> s : solvers)
			ecs.submit(s);
		int n = solvers.size();
		for (int i = 0; i < n; ++i) {
			Result r = ecs.take().get();
			if (r != null)
				use(r);
		}
	}

	<Result> void solve2(Executor e, Collection<Callable<Result>> solvers)
			throws InterruptedException {
		CompletionService<Result> ecs = new ExecutorCompletionService<Result>(e);
		int n = solvers.size();
		List<Future<Result>> futures = new ArrayList<Future<Result>>(n);
		Result result = null;
		try {
			for (Callable<Result> s : solvers)
				futures.add(ecs.submit(s));
			for (int i = 0; i < n; ++i) {
				try {
					Result r = ecs.take().get();
					if (r != null) {
						result = r;
						break;
					}
				} catch (ExecutionException ignore) {
				}
			}
		} finally {
			for (Future<Result> f : futures)
				f.cancel(true);
		}

		if (result != null)
			use(result);
	}
	
	<Result> void use(Result r){
		System.out.println(r);
	}
}
