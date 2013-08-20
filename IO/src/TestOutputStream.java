import java.io.*;
public class TestOutputStream {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		translate((byte) args[0].charAt(0),(byte) args[1].charAt(0), System.in, System.out);
	}
	
	static void translate(byte from, byte to) throws IOException{
		int b;
		while ((b = System.in.read()) != -1)
			System.out.write(b == from ? to : b);
	}
	
	static void translate(byte from, byte to, InputStream in , OutputStream out) throws IOException {
		int b;
		while ((b = in.read()) != -1)
			out.write(b == from ? to : b);
	}
}
