import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class MockTest {
	@Mock
	List<Object> mockedList;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	//verify
	@Test
	public void test1() {
		//using mock object
		 mockedList.add("one");
		 mockedList.clear();

		 //verification
		 verify(mockedList, times(1)).add("one");
		 verify(mockedList, times(1)).clear();
	}
	
	//stub
	@Test
	public void test2() {
//		//You can mock concrete classes, not only interfaces
//		 LinkedList<String> mockedList = mock(LinkedList.class);
		 
		 //stubbing
		 when(mockedList.get(0)).thenReturn("first");
		 when(mockedList.get(1)).thenThrow(new RuntimeException());
		 
		 //following prints "first"
		 assertEquals("first", mockedList.get(0));
		 
		 //following prints "null" because get(999) was not stubbed
		 assertNull(mockedList.get(999));
		 
		 verify(mockedList).get(0);
		 
		 //following throws runtime exception
		 thrown.expect(RuntimeException.class);
		 mockedList.get(1);
	}
	
	//Argument Matchers
	@Test
	public void test3() {
		 //stubbing using built-in anyInt() argument matcher
		 when(mockedList.get(anyInt())).thenReturn("element");
		 
		 //stubbing using hamcrest (let's say isValid() returns your own hamcrest matcher):
		 when(mockedList.contains(isA(String.class))).thenReturn(true);
		 
		 //following prints "element"
		 assertEquals("element", mockedList.get(999));
		 
		 //you can also verify using an argument matcher
		 verify(mockedList).get(anyInt());
	}
	
	@Test
	public void test4() {
		 //using mock 
		 mockedList.add("once");
		 
		 mockedList.add("twice");
		 mockedList.add("twice");
		 
		 mockedList.add("three times");
		 mockedList.add("three times");
		 mockedList.add("three times");
		 
		 //following two verifications work exactly the same - times(1) is used by default
		 verify(mockedList).add("once");
		 verify(mockedList, times(1)).add("once");
		 
		 //exact number of invocations verification
		 verify(mockedList, times(2)).add("twice");
		 verify(mockedList, times(3)).add("three times");
		 
		 //verification using never(). never() is an alias to times(0)
		 verify(mockedList, never()).add("never happened");
		 
		 //verification using atLeast()/atMost()
		 verify(mockedList, atLeastOnce()).add("three times");
		 verify(mockedList, never()).add("five times");
		 verify(mockedList, atMost(5)).add("three times");
	}
	
}
