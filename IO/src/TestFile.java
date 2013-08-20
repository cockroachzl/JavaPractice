import java.io.*;
public class TestFile {
	public static void main(String[] args) throws IOException{
		filterDir(".");
		filterSuffix("src",".java");
	}
	public static void fileStatus() {
		File file = new File("./src/TestFile.java");
		System.out.println("exist : " + file.exists());
		System.out.println("read  : " + file.canRead());
		System.out.println("write : " + file.canWrite());
		System.out.println("isFile: " + file.isFile());
		System.out.println("isDir : " + file.isDirectory());
		System.out.println("isAbsolute : " + file.isAbsolute());
		System.out.println("isHidden : " + file.isHidden());
		
		System.out.println("LastModified : " + file.lastModified());
	}
	public static void filterDir(String str){
		File dir = new File(str);
		String[] files = dir.list(new DirFilter());
		System.out.println(files.length + " dir(s): ");
		for (String file : files)
			System.out.println("\t" + file);
	}
	
	public static void filterSuffix(String dir, String suffix) throws IOException{
		File dirFile = new File(dir);
		if(!dirFile.isDirectory()) {
			System.out.println(dirFile.getCanonicalPath() + " is not a directory!");
			return;
		}
		File[] files = dirFile.listFiles(new SuffixFilter(suffix));
		for (File file : files){
			System.out.println(file.getCanonicalPath());
		}
	}
}

class DirFilter implements FilenameFilter {
	public boolean accept(File dir, String name){
		return new File(dir,name).isDirectory();
	}
}

class SuffixFilter implements FileFilter {
	private String suffix;
	
	SuffixFilter(String suffix) { this.suffix = suffix; }
	
	public boolean accept(File pathname) {
		String filename = pathname.getName();
		int start = filename.lastIndexOf(suffix);
		if(start >= 0 && start + suffix.length() == filename.length())
			return true;
		else
			return false;
		
	}
}