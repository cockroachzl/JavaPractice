package polymorphic;

public class Painter {
	public static <GeoObj> void myDraw(GeoObj obj) {
//		obj.draw(); // Error: The way of C++ template does not work in Java Generics
	}
}
