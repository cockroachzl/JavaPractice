import java.lang.reflect.*;
public class TestProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Foo foo = new Foo();
		IFoo fooProxy = (IFoo) DebugProxy.getProxy(foo);
		System.out.println(fooProxy.add(1,2) );
		System.out.println(fooProxy.substract(1, 2) );
	}

}

class DebugProxy implements InvocationHandler {
	Object obj;
	private DebugProxy(Object obj) {this.obj = obj;}
	
	public static synchronized Object getProxy(Object obj){
		Class<?> objClass = obj.getClass();
		return Proxy.newProxyInstance(objClass.getClassLoader(), objClass.getInterfaces(), new DebugProxy(obj));
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		System.out.println("invoke " + method.getName());
		
		return method.invoke(obj, args);
	}
}
interface IFoo {
	int add(int i, int j);
	int substract(int i, int j);
}
class Foo implements IFoo {
	public int add(int i, int j) {return i+j; }
	public int substract(int i, int j) {return i-j; }
}