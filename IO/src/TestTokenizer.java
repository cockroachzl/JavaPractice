import java.io.*;
import java.util.StringTokenizer;


public class TestTokenizer {
	public static void main(String[] args) throws IOException {
//		Reader in = new InputStreamReader(System.in);
//		System.out.println(sumStream(in));
		System.out.println(sumString("12 34 56"));
	}
	static double sumStream(Reader source) throws IOException {
		StreamTokenizer in = new StreamTokenizer(source);
		double result = 0.0;
		while (in.nextToken() != StreamTokenizer.TT_EOF){
			if (in.ttype == StreamTokenizer.TT_NUMBER)
				result += in.nval;
		}
		return result;
	}
	static double sumString(String str){
		StringTokenizer tokens = new StringTokenizer(str);
		double sum = 0;
		while (tokens.hasMoreTokens()) {
			sum += Double.valueOf(tokens.nextToken());
		}
		return sum;
	}
}
