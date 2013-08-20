import java.io.*;
public class TestFilterStream {
	static String str = "liang zhang";
	public static void main(String[] args) throws IOException {
		StringReader src = new StringReader(str);
		FilterReader f = new UppercaseConverter(src);
		int c;
		while ((c = f.read()) != -1)
			System.out.print((char)c);
		System.out.println();
		f.close();
	}
}

class UppercaseConverter extends FilterReader {
	UppercaseConverter(Reader in) {
		super(in);
	}
	
	public int read() throws IOException {
		int c = super.read();
		return (c == -1 ? c : Character.toUpperCase((char)c));
	}
	
	public int read(char[] buf, int offset, int count) throws IOException {
		int nread = super.read(buf, offset, count);
		int last = offset + nread;
		for (int i = offset; i < last; ++i)	{
			buf[i] = Character.toUpperCase(buf[i]);
		}
		return nread;
	}
}

//class ByteTranslator extends FilterReader {
//	ByteTranslator(Reader in)
//}