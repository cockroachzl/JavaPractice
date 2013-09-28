import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;


public class StackTest {
	@Test
	public void testPush(){
		Stack<Number> numStack = new Stack<Number>();
		numStack.push(new Integer(123));
	}
	
	@Test
	public void testPushAll(){
		Stack<Number> numStack = new Stack<Number>();
		Iterable<Integer> integers = Arrays.asList(1, 2, 3);
		numStack.pushAll(integers);
	}
	
	@Test
	public void testPop(){
		Stack<Number> numStack = new Stack<Number>();
		numStack.push(new Integer(1));
		Object obj = numStack.pop();
	}
	
	@Test
	public void testPopAll(){
		Stack<Number> numStack = new Stack<Number>();
		Iterable<Integer> integers = Arrays.asList(1, 2, 3);
		numStack.pushAll(integers);
		Collection<Object> objects = new ArrayList();
		numStack.popAll(objects);
	}
	
}
