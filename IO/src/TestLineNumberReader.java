import java.io.*;
public class TestLineNumberReader {
	public static void main(String[] args) throws IOException {
		findChar(args);
		String[] args1 = {"void", "src/TestLineNumberReader.java"};
		findWord(args1);
	}
	public static void findChar(String[] args) throws IOException{
		if (args.length != 2)
			throw new IllegalArgumentException("need char and file");
		int match = args[0].charAt(0);
		FileReader fileIn = new FileReader(args[1]);
		LineNumberReader in = new LineNumberReader(fileIn);
		int ch;
		while ((ch = in.read())!= -1 ){
			if (ch == match){
				System.out.println("'" + (char)ch + "' at line " + in.getLineNumber());
				in.close();
				return;
			}
		}
		System.out.println((char)match + " not found");
		in.close();
	}
	
	static void findWord(String[] args) throws IOException {
		if (args.length != 2)
			throw new IllegalArgumentException("need word and file");
		String word = args[0];
		FileReader fileIn = new FileReader(args[1]);
		LineNumberReader in = new LineNumberReader(fileIn);
		String line;
		while ((line = in.readLine()) != null) {
			if(line.indexOf(word) != -1){
				System.out.println(in.getLineNumber() + " : " + line);
			}
		}
	}
}

