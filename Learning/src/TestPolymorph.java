
class Base {
	public void foo() { System.out.println("Base::foo()"); }
}
class Derived extends Base {
	public void foo() { System.out.println("Derived::foo()"); }
}
public class TestPolymorph {
	public static void main(String[] args){
		Base b0 = new Base();
		Base b1 = new Derived();
		b0.foo();
		b1.foo();
	}
}
