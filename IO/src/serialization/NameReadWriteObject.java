package serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class NameReadWriteObject implements Serializable{
	private static final long serialVersionUID = -7280809160250762923L;
	private String name;
	private long id;
	private transient int hash;
	private static long nextID = 0;
	
	public NameReadWriteObject(String name) {
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
	
	private Object writeReplace() throws IOException{
		System.out.println(this.getClass().getSimpleName() + "writeReplace is called.");
		return this;
	}
	
	private Object readResolve() throws IOException, ClassNotFoundException {
		System.out.println(this.getClass().getSimpleName() + "readResolve is called.");
		return this;
	}
}