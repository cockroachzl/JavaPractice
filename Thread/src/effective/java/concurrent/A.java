package effective.java.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class A {
	public void a(){
	Map m = Collections.synchronizedMap(new HashMap());
	}
}
