import java.util.List;


public class GenericsArraysTest {
	Integer[] ints = new Integer[10];
	List<?>[] lists = new List<?>[10]; //compile ok
	//compile error, cannot create a generic array
//	List<? extends Object>[] objectlists = new List<? extends Object>[10]; 
}
