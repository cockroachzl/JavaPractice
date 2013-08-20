import java.util.*;
public class TestTimeZone {
	public static void main(String[] args){
		String[] ids = TimeZone.getAvailableIDs();
		for (int i = 0; i < ids.length; ++i)
			System.out.println(ids[i]);
		TimeZone cur = TimeZone.getDefault();
		System.out.println(cur.getDSTSavings());
		System.out.println(cur.useDaylightTime());
		System.out.println(cur.getOffset(Calendar.getInstance().getTimeInMillis()));
	}
}
