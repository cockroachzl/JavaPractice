import java.nio.charset.Charset;


public class CharacterSet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for ( String name : Charset.availableCharsets().keySet())
			System.out.println(name);
		Charset defaultCharset = Charset.defaultCharset();
		System.out.println("default: " + defaultCharset.name());
	}

}
