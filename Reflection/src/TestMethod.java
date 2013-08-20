import java.lang.reflect.*;


public class TestMethod {
	static String str = "ThisisLiang";
	public static void main(String[] args) {
		System.out.println(str.indexOf("is", 0));
		
		try {
			Method indexM = String.class.getMethod("indexOf", String.class, int.class);
			System.out.println( (Integer)indexM.invoke(str, "is", 0) );
		} catch (NoSuchMethodException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}
	}
}


