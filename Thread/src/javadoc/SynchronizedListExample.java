package javadoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class SynchronizedListExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List list = Collections.synchronizedList(new ArrayList());

	  synchronized (list) {
	      Iterator i = list.iterator(); // Must be in synchronized block
	      while (i.hasNext())
	          foo(i.next());
	  }
	}

	private static void foo(Object next) {
		// TODO Auto-generated method stub
		
	}

}
