import java.lang.reflect.*;

public class TypeDesc {
	
	public static void main(String[] args){
		TypeDesc desc = new TypeDesc();
		for (String name : args){
			try {
				Class<?> startClass = Class.forName(name);
				desc.printType(startClass, 0, basic);
			} catch (ClassNotFoundException ex) {
				System.err.println(ex);
			}
		}
	}
	
	private java.io.PrintStream out = System.out;
	private static String[]
			basic = { "class", "interface", "enum", "annotation" },
			supercl = { "extends", "implements"},
			iFace = {null, "extends"};
	
	private void printType( Type type, int depth, String[] labels ) {
		if (type == null)
			return;
		Class<?> cls = null;
		if (type instanceof Class<?>)
			cls = (Class<?>) type;
		else if (type instanceof ParameterizedType)
			cls = (Class<?>) ((ParameterizedType)type).getRawType();
		else
			throw new Error("Unexpected non-class type");
		//print this type
		for (int i = 0; i < depth; ++i)
			out.print("  ");
		int kind = cls.isAnnotation() ? 3 : cls.isEnum() ? 2 : cls.isInterface() ? 1 : 0;
		out.print(labels[kind] + " ");
		out.print(cls.getCanonicalName());
		
		//print generic type parameters if present
		TypeVariable<?>[] params = cls.getTypeParameters();
		if (params.length > 0) {
			out.print('<');
			for (TypeVariable<?> param : params) {
				out.print(param.getName());
				out.print(", ");
			}
			out.println("\b\b>");
		}
		else
			out.println();
		
		// print out all interfaces this class implements
		Type[] interfaces = cls.getGenericInterfaces();
		for (Type iface : interfaces){
			printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
		}
		//recurse on the superclass
		printType(cls.getGenericSuperclass(), depth + 1, supercl);
	}
}
