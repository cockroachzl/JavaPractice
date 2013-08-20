import java.util.regex.*;
public class RegexReplace {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		replace0();
		System.out.println(replace1("Liang", "Zhang", "This is Liang Zhang"));
	}
	
	static void replace0(){
		Pattern pat = Pattern.compile("sun", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pat.matcher("The God of Sun");
		String result = matcher.replaceAll("moon");
		System.out.println(result);
	}
	
	static String replace1(String w1, String w2, String input){
		Pattern pat = Pattern.compile("\\b(" + w1 + ")(\\W+)(" + w2 + ")\\b");
		Matcher matcher = pat.matcher(input);
		String result = matcher.replaceAll("$3$2$1");
		return result;
	}

}
