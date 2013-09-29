

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

import lombok.Getter;

public class Name implements Serializable {
	@Getter
    private String name;
    private long id;
    private transient int hash;
    private static long nextID = 0;

    public Name(String name) {
        this.name = name;
        synchronized (Name.class) {
            id = nextID++;
        }
        hash = name.hashCode();
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.writeUTF(name);
        out.writeLong(id);
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
    	in.registerValidation(new ObjectInputValidation() {
			@Override
			public void validateObject() throws InvalidObjectException {
				System.out.println("validating");
			}
		}, 0);
        name = in.readUTF();
        id = in.readLong();
        hash = name.hashCode();
    }
    
    Object writeReplace() throws ObjectStreamException {
    	return this;
    }

    Object readResolve() throws ObjectStreamException {
    	return this;
    }
    
    public int hashCode() {
        return hash;
    }

    // ... override equals, provide other useful methods
}