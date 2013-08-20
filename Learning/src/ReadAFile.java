import java.io.*;

public class ReadAFile {
	public static void main(String[] args){
		String filename = "quizcard.txt";
		if (args.length > 1)
			filename = args[1];
		try {
			File myFile = new File(filename);
			FileReader fileReader = new FileReader(myFile);
			BufferedReader reader = new BufferedReader(fileReader);
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
