package serialization;
import java.io.*;
import java.util.*;

public class TestSerialization {
	public static void main(String[] args) throws IOException {
		serializeName();
		deserializeName();
		serializeBetterName();
		deserializeBetterName();
		serializeMuchBetterName();
		deserializeMuchBetterName();
	}
	
	static void serializeName() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("namelist.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		HashMap<String,Name> hashmap = new HashMap<String, Name>();
		hashmap.put("lz", new Name("Liang Zhang"));
		hashmap.put("hc", new Name("Huajuan Chen"));
		hashmap.put("ll", new Name("Liu Liu"));
		out.writeObject(hashmap);
	}
	
	static void deserializeName() throws IOException {
		FileInputStream fileIn = new FileInputStream("namelist.txt");
		ObjectInputStream in  = new ObjectInputStream(fileIn);
		try {
			HashMap<String,Name> hashmap = (HashMap<String, Name>) in.readObject();
			for (Map.Entry<String, Name> entry : hashmap.entrySet()) {
				System.out.println(entry.getKey() + " + " + entry.getValue());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	static void serializeBetterName() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("betternamelist.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		HashMap<String,NameReadWriteObject> hashmap = new HashMap<String, NameReadWriteObject>();
		hashmap.put("lz", new NameReadWriteObject("Liang Zhang"));
		hashmap.put("hc", new NameReadWriteObject("Huajuan Chen"));
		hashmap.put("ll", new NameReadWriteObject("Liu Liu"));
		out.writeObject(hashmap);
	}
	
	static void deserializeBetterName() throws IOException {
		FileInputStream fileIn = new FileInputStream("betternamelist.txt");
		ObjectInputStream in  = new ObjectInputStream(fileIn);
		try {
			HashMap<String,NameReadWriteObject> hashmap = (HashMap<String, NameReadWriteObject>) in.readObject();
			for (Map.Entry<String, NameReadWriteObject> entry : hashmap.entrySet()) {
				System.out.println(entry.getKey() + " + " + entry.getValue());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	static void serializeMuchBetterName() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("muchbetternamelist.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		HashMap<String, ExternalizableName> hashmap = new HashMap<String, ExternalizableName>();
		hashmap.put("lz", new ExternalizableName("Liang Zhang"));
		hashmap.put("hc", new ExternalizableName("Huajuan Chen"));
		hashmap.put("ll", new ExternalizableName("Liu Liu"));
		out.writeObject(hashmap);
	}
	
	static void deserializeMuchBetterName() throws IOException {
		FileInputStream fileIn = new FileInputStream("muchbetternamelist.txt");
		ObjectInputStream in  = new ObjectInputStream(fileIn);
		try {
			HashMap<String,ExternalizableName> hashmap = (HashMap<String, ExternalizableName>) in.readObject();
			for (Map.Entry<String, ExternalizableName> entry : hashmap.entrySet()) {
				System.out.println(entry.getKey() + " + " + entry.getValue());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
