import java.io.*;
import java.util.*;

public class TestCalendar {
	public static void main(String[] args){
		Date now = new Date();
		System.out.println(now);
		System.out.println(System.currentTimeMillis());
		System.out.println(System.nanoTime());
		
		Calendar cal = new GregorianCalendar();
		System.out.println(cal.getTime());
		System.out.println(cal.getTimeInMillis());
		System.out.println("year: " + cal.get(Calendar.YEAR));
		System.out.println("month: " + cal.get(Calendar.MONTH));
		System.out.println("day: " + cal.get(Calendar.DAY_OF_MONTH));
		System.out.println("week of the day: " + cal.get(Calendar.DAY_OF_WEEK));
		System.out.println(cal.isLenient());
		
//		oneWeek(System.out, Calendar.getInstance());
		
		
	}
	
	public static void oneWeek(PrintStream out, Calendar cal) {
		Calendar cur = (Calendar)cal.clone();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		do {
			out.println(cur.getTime());
			cur.add(Calendar.DAY_OF_WEEK, 1);
		} while (cur.get(Calendar.DAY_OF_WEEK) != dayOfWeek);
	}
}
