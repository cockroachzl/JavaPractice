package serialization;

import java.io.Serializable;

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