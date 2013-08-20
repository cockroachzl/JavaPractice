import java.io.*;
public class TestPipedStream {
	public static void main(String[] args) throws IOException {
		PipedWriter out = new PipedWriter();
		PipedReader in = new PipedReader();
		out.connect(in);
		
		TextGenerator data = new TextGenerator(out);
		data.start();
		int ch;
		while ((ch = in.read()) != -1)
			System.out.println((char)ch);
		System.out.println();
	}
}

class TextGenerator extends Thread {
	private Writer out;
	public TextGenerator(Writer out) { 
		this.out = out;
	}
	public void run() {
		try {
			try {
				for (char c = 'a'; c <= 'z'; c++)
					out.write(c);
			} finally {
				out.close();
			}
		} catch (IOException ex) {
			getUncaughtExceptionHandler().uncaughtException(this, ex);
		}
	}
}