import java.util.*;

public class TestResourceBundle {
	public static void main(String[] args){
		ResourceBundle res = ResourceBundle.getBundle("GlobalRes_en_US");
		String msg0 = res.getString(GlobalRes.HELLO);
		String msg1 = res.getString(GlobalRes.GOODBYE);
		System.out.println(msg0);
		System.out.println(msg1);
	}
}





