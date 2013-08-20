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
		HashMap<String,BetterName> hashmap = new HashMap<String, BetterName>();
		hashmap.put("lz", new BetterName("Liang Zhang"));
		hashmap.put("hc", new BetterName("Huajuan Chen"));
		hashmap.put("ll", new BetterName("Liu Liu"));
		out.writeObject(hashmap);
	}
	
	static void deserializeBetterName() throws IOException {
		FileInputStream fileIn = new FileInputStream("betternamelist.txt");
		ObjectInputStream in  = new ObjectInputStream(fileIn);
		try {
			HashMap<String,BetterName> hashmap = (HashMap<String, BetterName>) in.readObject();
			for (Map.Entry<String, BetterName> entry : hashmap.entrySet()) {
				System.out.println(entry.getKey() + " + " + entry.getValue());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	static void serializeMuchBetterName() throws IOException {
		FileOutputStream fileOut = new FileOutputStream("muchbetternamelist.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		HashMap<String, MuchBetterName> hashmap = new HashMap<String, MuchBetterName>();
		hashmap.put("lz", new MuchBetterName("Liang Zhang"));
		hashmap.put("hc", new MuchBetterName("Huajuan Chen"));
		hashmap.put("ll", new MuchBetterName("Liu Liu"));
		out.writeObject(hashmap);
	}
	
	static void deserializeMuchBetterName() throws IOException {
		FileInputStream fileIn = new FileInputStream("muchbetternamelist.txt");
		ObjectInputStream in  = new ObjectInputStream(fileIn);
		try {
			HashMap<String,MuchBetterName> hashmap = (HashMap<String, MuchBetterName>) in.readObject();
			for (Map.Entry<String, MuchBetterName> entry : hashmap.entrySet()) {
				System.out.println(entry.getKey() + " + " + entry.getValue());
			}
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}

class Name implements Serializable{
	private String name;
	private long id;
	private transient boolean hashSet = false;
	private transient int hash;
	private static long nextID = 0;
	
	public Name(String name) {
		this.name = name;
		synchronized (Name.class) {
			id = nextID++;
		}
	}
	
	public int hashCode() {
		if (!hashSet) {
			hash = name.hashCode();
			hashSet = true;
		}
		return hash;
	}
	
	public String toString() {
		return name + " : " + id;
	}
}

class BetterName implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5410105609034679240L;
	private String name;
	private long id;
	private transient int hash;
	private static long nextID = 0;
	
	public BetterName(String name) {
		this.name = name;
		synchronized (Name.class) {
			id = nextID++;
		}
		hash = name.hashCode();
	}
	
	public int hashCode() {
		return hash;
	}
	
	public String toString() {
		return name + " : " + id;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException{
//		out.writeUTF(name);
//		out.writeLong(id);
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//		name = in.readUTF();
//		id = in.readLong();
		in.defaultReadObject();
		hash = name.hashCode();
	}
}

class MuchBetterName implements Externalizable{
	private String name;
	private long id;
	private transient int hash;
	private static long nextID = 0;
	
	//much have a public default constructor
	public MuchBetterName() {}
	public MuchBetterName(String name) {
		this.name = name;
		synchronized (Name.class) {
			id = nextID++;
		}
		hash = name.hashCode();
	}
	
	public int hashCode() {
		return hash;
	}
	
	public String toString() {
		return name + " : " + id;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException{
//		out.writeUTF(name);
//		out.writeLong(id);
		out.defaultWriteObject();
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
//		name = in.readUTF();
//		id = in.readLong();
		in.defaultReadObject();
		hash = name.hashCode();
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(name);
		out.writeLong(id);
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException {
		name = in.readUTF();
		id = in.readLong();
		hash = name.hashCode();
	}
}
