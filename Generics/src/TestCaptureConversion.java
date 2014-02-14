import java.util.Arrays;
import java.util.List;


public class TestCaptureConversion {

	public static void main(String[] args) {
		CaptureConversion cc = new CaptureConversion();
		List<?> nums = Arrays.asList(1, 2, 3);
//		cc.fromWildCard(nums);
//		cc.fromGenericType(nums);
		List<Integer> l = (List<Integer>) cc.passThroughGenericType(nums);
		System.out.println(l);
	}

}

class CaptureConversion {
	void fromWildCard(List<?> t) {
		fromGenericType(t);
	}
	<T> void fromGenericType(List<T> t) {
		System.out.println(t);
	}
	
	<T> List<T> passThroughGenericType(List<T> t) {
		System.out.println(t);
		return t;
	}
}
