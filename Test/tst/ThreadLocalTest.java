import org.junit.Test;


public class ThreadLocalTest {

	@Test
	public void testThreadId() throws InterruptedException {
		System.out.println(Thread.currentThread().getName() + " : "+ ThreadId.get());
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " : " + ThreadId.get());
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " : " + ThreadId.get());
			}
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}
}
