package serialization;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

class ExternalizableName implements Externalizable{
	private String name;
	private long id;
	private transient int hash;
	private static long nextID = 0;
	
	//much have a public default constructor
	public ExternalizableName() {}
	public ExternalizableName(String name) {
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