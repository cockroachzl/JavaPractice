
public class TestWaitNotify {
	public static void main(String[] args) {
		MsgPrinter msgPrinter = new MsgPrinter();
		MsgPrinterScheduler timer = new MsgPrinterScheduler(msgPrinter, 0);
		MsgPrinterScheduler msg7 = new MsgPrinterScheduler(msgPrinter, 7);
		MsgPrinterScheduler msg15 = new MsgPrinterScheduler(msgPrinter, 15);
		new Thread(timer).start();
		new Thread(msg7).start();
		new Thread(msg15).start();
	}
}
class MsgPrinterScheduler implements Runnable {
	private MsgPrinter msgPrinter;
	private int sec;
	MsgPrinterScheduler(MsgPrinter msgPrinter, int sec){
		this.msgPrinter = msgPrinter;
		this.sec = sec;
	}
	public void run(){
		try {
			if (sec == 0)
				msgPrinter.timer();
			else
				msgPrinter.printMessage(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class MsgPrinter{
	private int sec = 0;
	private int max = 30;
	
	public void timer() throws InterruptedException {
		for (int i = 0; i < max; ++i) {
			increase();
			Thread.sleep(1000);
		}
	}
	private synchronized void increase() throws InterruptedException {

			++sec;
			System.out.println(sec + " seconds");
			notifyAll();
	}
	
	public void printMessage(int interval) throws InterruptedException {
		while (sec < max) {
			_printMessage(interval);
			Thread.sleep(1000);
		}
			
	}
	private synchronized void _printMessage(int interval) throws InterruptedException{
			while(sec % interval !=0)
				wait();
			System.out.println("Thread " + Thread.currentThread().getId() + " : " + interval + " seconds elapsed.");
	}

	
	
}