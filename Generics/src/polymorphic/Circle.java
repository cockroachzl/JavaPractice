package polymorphic;

public class Circle {
	public void draw() {
		System.out.println("Drawing a " + this.getClass().getSimpleName());
	}
}
