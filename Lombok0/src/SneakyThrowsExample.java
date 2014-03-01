import lombok.SneakyThrows;
import lombok.val;
import java.io.*;
 
public class SneakyThrowsExample implements Runnable {

	@SneakyThrows(UnsupportedEncodingException.class)
	public String utf8ToString(byte[] bytes) {
		return new String(bytes, "UTF-8");
	}

	@SneakyThrows
	public void run() {
		throw new Throwable();
	}
	
	public String toString(){
		val name = this.getClass().getCanonicalName();
		String ret = name;
		return ret;
	}
}