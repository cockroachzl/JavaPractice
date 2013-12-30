import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;


public class ClassNameTest {

	@Test
	public void testClassName() {
		System.out.println(String.class.getName());
		System.out.println(String.class.getCanonicalName());
		System.out.println(String.class.getSimpleName());
		
		System.out.println(TimeoutException.class.getName());
		System.out.println(TimeoutException.class.getCanonicalName());
		System.out.println(TimeoutException.class.getSimpleName());
		
		System.out.println(ExecutionException.class.getName());
		System.out.println(ExecutionException.class.getCanonicalName());
		System.out.println(ExecutionException.class.getSimpleName());
	}
	
	@Test
	public void testClassNameInConstructor() {
		B b = new B();
	}
}


class A {
	A() { 
		System.out.println("A()::" + getClass().getSimpleName());
	}
}

class B extends A {
	B() { 
		System.out.println("B()::" + getClass().getSimpleName());
	}
}