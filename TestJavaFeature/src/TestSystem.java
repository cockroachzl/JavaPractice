import java.io.IOException;
import java.util.*;
import java.io.*;


public class TestSystem {
	public static void main(String[] args){
		long start = System.nanoTime();
		Properties p = System.getProperties();
		p.list(System.out);
		long elapsed = System.nanoTime()-start;
		System.out.println(elapsed + " nanoseconds elapsed.");
		
		Map<String, String> envs = System.getenv();
		for (Map.Entry<String,String> entry : envs.entrySet()){
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		
		String[] output = ls("."," ");
		System.out.println(output);
	}
	
	static String[] ls(String dir, String opts){
		try {
			Process child = new ProcessBuilder("java").redirectErrorStream(true).start();
			InputStream lsOut = child.getInputStream();
			InputStreamReader reader = new InputStreamReader(lsOut);
			BufferedReader in = new BufferedReader(reader);
			
			List<String> lines = new ArrayList<String>();
			String line;
			while ((line = in.readLine()) != null)
				lines.add(line);
			if (child.waitFor() != 0)
				System.out.println("Failed to excute, exit code:" + child.exitValue());
			return lines.toArray(new String[lines.size()]);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
