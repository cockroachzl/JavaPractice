package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestNameSerialization {

	@Test
	public void testWriteReplaceReadResolve() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("WriteReplaceReadResolve.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		Map<String,NameReadWriteObject> hashmap = new HashMap<String, NameReadWriteObject>();
		hashmap.put("lz", new NameReadWriteObject("Liang Zhang"));
		hashmap.put("hc", new NameReadWriteObject("Huajuan Chen"));
		hashmap.put("ll", new NameReadWriteObject("Liu Liu"));
		out.writeObject(hashmap);
		
		FileInputStream fileIn = new FileInputStream("WriteReplaceReadResolve.txt");
		ObjectInputStream in  = new ObjectInputStream(fileIn);
		try {
			Map<String,NameReadWriteObject> mapIn = (HashMap<String, NameReadWriteObject>) in.readObject();
			for (Map.Entry<String, NameReadWriteObject> entry : mapIn.entrySet()) {
				System.out.println(entry.getKey() + " + " + entry.getValue());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
