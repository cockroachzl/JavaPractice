import java.io.*;

public class TestPushbackInputStream {
	public static void main(String[] args) throws IOException {
		PushbackInputStream in = new PushbackInputStream(System.in);
		int max = 0;
		int maxByte = -1;
		int b;
		
		do {
			int count;
			int b1 = in.read();
			for (count = 1; (b = in.read()) == b1; ++count) 
				continue;
			if (count > max) {
				max = count;
				maxByte = b1;
			}
			in.unread(b);
		} while (b != -1);
		System.out.println(max + " bytes of " + (char)maxByte);
	}
}
