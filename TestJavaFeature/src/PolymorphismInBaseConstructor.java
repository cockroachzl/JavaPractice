
public class PolymorphismInBaseConstructor {
	public static void main(String[] args){
		Base b = new Derived();
	}
}

class Base {
	Base() {
		System.out.println("Begin to construct Base");
		name();
		System.out.println("End to construct Base");
	}
	void name() {
		System.out.println("My name is Base");
	}
}

class Derived extends Base {
	Derived() {
		System.out.println("Begin to construct Derived");
		name();
		System.out.println("End to construct Derived");
	}
	void name() {
		System.out.println("My name is Derived");
	}
}