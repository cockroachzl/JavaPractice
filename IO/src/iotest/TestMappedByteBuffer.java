package iotest;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.io.*;
public class TestMappedByteBuffer {
	
	public static void main(String[] args) throws IOException {
		File file = new File("src/iotest/TestMappedByteBuffer.java");
		String charSet = Charset.defaultCharset().name();
		char ch = 'i';
		System.out.println(count(file, charSet, ch));
	}

	public static int count(File file, String charSet, char ch) throws IOException {
		Charset charset = Charset.forName(charSet);
		CharsetDecoder decoder = charset.newDecoder();
		FileInputStream fis = new FileInputStream(file);
		FileChannel fc = fis.getChannel();
		
		long size = fc.size();
		MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, size);
		CharBuffer cb = decoder.decode(bb);
		int count = 0;
		for (int i = 0; i < size && i < Integer.MAX_VALUE; i++)
			if (cb.charAt(i) == ch)
				++count;
		fc.close();
		fis.close();
		return count;
	}
}
