import java.util.*;
public class TestProperties {
	public static void main(String[] args){
		Properties props = System.getProperties();
		for (Map.Entry<Object,Object> prop : props.entrySet()){
			System.out.println(prop.getKey() + " = " + prop.getValue());
		}
	}
}
