import java.lang.reflect.*;
import static java.lang.System.out;
import static java.lang.System.err;

public class SimpleClassDesc {
	public static void main(String[] args) {
		Class type = null;
		try {
			type = Class.forName(args[0]);
		} catch (ClassNotFoundException ex) {
			err.println(ex);
			return ;
		}
		out.print("class " + type.getSimpleName());
		Class superclass = type.getSuperclass();
		if (superclass != null)
			out.println(" extends " + superclass.getCanonicalName());
		else
			out.println();
		Method[] methods = type.getDeclaredMethods();
		for (Method m : methods)
			if (Modifier.isPublic(m.getModifiers()))
				out.println("  " + m);
	}
}
