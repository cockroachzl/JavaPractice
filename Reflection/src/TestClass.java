
public class TestClass {
	public static void main(String[] args){
		
		try {
			Class<?> type  = Class.forName("java.util.Map$Entry");
			System.out.println(type.getCanonicalName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
