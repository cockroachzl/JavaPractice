package polymorphic;

public class Line {
	public void draw() {
		System.out.println("Drawing a " + this.getClass().getSimpleName());
	}
}
