import java.util.ListResourceBundle;


public class GlobalRes_en extends ListResourceBundle {
	private static final Object[][] contents = {
		{ GlobalRes.HELLO,   "Hello" },
		{ GlobalRes.GOODBYE, "Goodbye"},
	};
	
	public Object[][] getContents(){
		return contents;
	}
}
