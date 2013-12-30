import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


public class StringTest {

	@Test
	public void testString0() {
		String firstName = "Liang";
		String lastName = "Zhang";
		String fullname = String.format("%2s %1s", firstName, lastName);
		System.out.println(fullname);
	}
	
	@Test
	public void testToString() {
		List<String> strings = new ArrayList<>();
		System.out.println(strings.toString());
	}
	
	@Test
	public void testToString2() {
		List<String> lst = Arrays.asList("aaa", "bbb", "ccc");
		System.out.println(lst.toString());
		String[] arr = {"aaa", "bbb", "ccc"};
		System.out.println(arr.toString());
	}
	
	
}
