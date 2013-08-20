import java.io.*;
public class TestInputStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InputStream in;
		if (args.length == 0)
			in = System.in;
		else
			in = new FileInputStream(args[0]);
		int total = 0;
		int c;
		while ((c=in.read()) != -1) {
			System.out.println(c);
			++total;
		}
		System.out.println(total + " bytes");
	}

}
