import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;

import org.junit.Test;


public class SerializationTest {
	@Test
	public void testWriteReplaceAndReadResolve() throws IOException, ClassNotFoundException {
		Name myname = new Name("Liang Zhang");
		String filename = "name.ser";
		FileOutputStream fileOut = new FileOutputStream(filename);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(myname);
		out.close();
		
		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		myname = (Name)in.readObject();		
		in.close();
		
		assertEquals("Liang Zhang", myname.getName());

	}

}
