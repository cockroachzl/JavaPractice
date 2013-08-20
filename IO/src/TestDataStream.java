import java.io.*;
public class TestDataStream {
	public static void main(String[] args) throws IOException{
		double[] data = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6};
		String filename = "c.txt";
		writeData(data, filename);
		double[] data2 = readData(filename);
		for (Double d : data2)
			System.out.println(d);
		
	}
	
	public static void writeData(double[] data, String filename) throws IOException{
		OutputStream fout = new FileOutputStream(filename);
		DataOutputStream out = new DataOutputStream(fout);
		out.writeInt(data.length);
		for (double d : data)
			out.writeDouble(d);
		out.close();
	}
	
	public static double[] readData(String filename) throws IOException {
		InputStream fin = new FileInputStream(filename);
		DataInputStream in = new DataInputStream(fin);
		int length = in.readInt();
		double[] data = new double[length];
		for (int i=0; i < length; ++i)
			data[i] = in.readDouble();
		in.close();
		return data;
	}
}
