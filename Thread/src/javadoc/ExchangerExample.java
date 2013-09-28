package javadoc;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

}

class FillAndEmpty {
	   Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
	   DataBuffer initialEmptyBuffer = ... a made-up type
	   DataBuffer initialFullBuffer = ...

	   class FillingLoop implements Runnable {
	     public void run() {
	       DataBuffer currentBuffer = initialEmptyBuffer;
	       try {
	         while (currentBuffer != null) {
	           addToBuffer(currentBuffer);
	           if (currentBuffer.isFull())
	             currentBuffer = exchanger.exchange(currentBuffer);
	         }
	       } catch (InterruptedException ex) {}
	     }
	   }

	   class EmptyingLoop implements Runnable {
	     public void run() {
	       DataBuffer currentBuffer = initialFullBuffer;
	       try {
	         while (currentBuffer != null) {
	           takeFromBuffer(currentBuffer);
	           if (currentBuffer.isEmpty())
	             currentBuffer = exchanger.exchange(currentBuffer);
	         }
	       } catch (InterruptedException ex) {}
	     }
	   }

	   void start() {
	     new Thread(new FillingLoop()).start();
	     new Thread(new EmptyingLoop()).start();
	   }
	 }