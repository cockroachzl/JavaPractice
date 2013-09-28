import java.util.ArrayList;
import java.util.List;


public class Features {
	public static void main(String[] args) {
		List<String> strings = new ArrayList<String>();
		unsafeAdd(strings, new String("abc"));
		String s = strings.get(0);
	}
	
	private static void unsafeAdd(List<String> list, String str) {
		list.add(str);
	}

}
