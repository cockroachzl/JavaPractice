
public class TestAttr {

}

class Attr {
	private final String name;
	private Object value = null;
	
	Attr(String name) {
		this.name = name;
	}
	Attr(String name, Object value){
		this(name);
		this.value = value;
	}
	String getName() { return name; }
	Object getValue() { return value; }
	Object setValue(Object newValue) {
		Object oldValue = value;
		value = newValue;
		return oldValue;
	}
	public String toString() {
		return name + ":" + value.toString();
	}
}
