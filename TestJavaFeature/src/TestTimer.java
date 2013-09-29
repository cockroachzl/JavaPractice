import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

class MemoryWatchTask extends TimerTask {
	public void run() {
		System.out.println(new Date() + ": ");
		Runtime rt = Runtime.getRuntime();
		rt.traceMethodCalls(true);
		rt.traceInstructions(true);
		System.out.println(rt.freeMemory()+ " free, ");
		System.out.println(rt.totalMemory() + " total");
		System.out.println();
	}
}

public class TestTimer {
	public static void main(String[] args){
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(new MemoryWatchTask(), 0, 1000);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
