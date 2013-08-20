import java.util.ListResourceBundle;


public class GlobalRes extends ListResourceBundle {
	public static final String HELLO = "hello";
	public static final String GOODBYE = "goodbye";
	
	private static final Object[][] contents = {
		{ GlobalRes.HELLO,   "Ciao" },
		{ GlobalRes.GOODBYE, "Ciao" },
	};
	
	public Object[][] getContents(){
		return contents;
	}
}