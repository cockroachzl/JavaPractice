package b;
public class MethodNameHiding {
	public static void main(String[] args){
		Derived d = new Derived();
		d.test();
	}
}

class Base {
	void foo() { System.out.println("Base.foo()"); }
	void foo(int i) { System.out.println("Base.foo(" + i + ')' ); }
}

class Derived extends Base {
	void foo(float f) { System.out.println("Derived.foo(" + f + ')'); }
	void test() {
		foo();
	}
}