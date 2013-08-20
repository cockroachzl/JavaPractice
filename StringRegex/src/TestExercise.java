
public class TestExercise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(separateNumber("1", ",", 3));
		System.out.println(separateNumber("123", ",", 3));
		System.out.println(separateNumber("12345", ",", 3));
	}
	//13.5 & 13.6
	static String separateNumber(String str, String separator, int stride){
		StringBuilder strbuf = new StringBuilder();
		int start = 0;
		int end = str.length() % stride;
		int length = str.length() - end;
		if (start != end) {
			strbuf.append(str, start, end);
		}
		start = end;
		end += stride;
		while (length != 0) {
			if (length != str.length())
				strbuf.append(',');
			strbuf.append(str, start, end);
			start = end;
			end += stride;
			length -= stride;
		}
		return strbuf.toString();
	}
}

//13.5 & 13.6
