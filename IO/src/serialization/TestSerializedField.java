package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;

/**
 * Test harness for <code>Rectangle</code>
 * @version 1.0
 * @author Liang Zhang
 * @since 1.0
 */
public class TestSerializedField {
	public static void main(String[] args) throws IOException{
		serialize();
		deserialize();
	}
	
	static void serialize() throws IOException{
		FileOutputStream fileOut = new FileOutputStream("TestSerializedFields.txt");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		Rectangle rec = new Rectangle(1.0, 1.0, 3.0, 4.0);
		out.writeObject(rec);
		out.close();
	}
	
	static void deserialize() throws IOException {
		FileInputStream fileIn = new FileInputStream("TestSerializedFields.txt");
		ObjectInputStream in = new ObjectInputStream(fileIn);
		try {
			Rectangle rec = (Rectangle)in.readObject();
			System.out.println(rec);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		in.close();
	}
}

/**
 * The <code>Rectangle</code> class supports serialization with persistent fields
 * @author zhangliang
 * @version 1.0
 * @since 1.0
 */
class Rectangle implements Serializable {
	/** the auto generated serial version UID */
	private static final long serialVersionUID = -5230386363173699739L;
	/** @serial */
	private static final ObjectStreamField[] serialPersistentFields = {
		new ObjectStreamField("x1", Double.TYPE),
		new ObjectStreamField("y1", Double.TYPE),
		new ObjectStreamField("x2", Double.TYPE),
		new ObjectStreamField("y2", Double.TYPE),
	};
	
	/** @serialField x1 double X-coordinate of upper left corner. */
	/** @serialField y1 double Y-coordinate of upper left corner. */
	/** @serialField x2 double X-coordinate of bottom right corner. */
	/** @serialField y2 double Y-coordinate of bottom right corner. */
	private transient double x, y, width, height;
	
	/**
	 * Create a new rectangle
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	Rectangle(double x, double y, double width, double height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * To convert the rectangle into String
	 * @see Rectangle#Rectangle(double, double, double, double)
	 */
	public String toString(){
		return "leftupper(" + x + "," + y + ")" + " width: " + width + " height: " + height;
	}
	
	/**
	 * user-defined deserialization
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream.GetField fields = in.readFields();
		x = fields.get("x1", 0.0);
		y = fields.get("y1", 0.0);
		double x2 = fields.get("x2", 0.0);
		double y2 = fields.get("y2", 0.0);
		width = x2 - x;
		height = y2 - y;
	}
	
	/**
	 * user-defined serialization
	 * @param out
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		ObjectOutputStream.PutField fields = out.putFields();
		fields.put("x1", x);
		fields.put("y1", y);
		fields.put("x2", x + width);
		fields.put("y2", y + height);
		out.writeFields();
	}
}