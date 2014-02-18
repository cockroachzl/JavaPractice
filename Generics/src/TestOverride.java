
public class TestOverride {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class Base {
	void m(Number s) { }

	<T extends Number> void f(T t) { }


}

class Derived extends Base {
//	@Override
//	<T extends Number> void m(T t) { } //a generic method cannot override nongeneric method
	
	@Override
	void f(Number s) { }
}