import java.util.*;
import java.io.*;
public class TestCurrency {
	public static void main(String[] args){
		System.out.println("The current currency is " + Currency.getInstance(Locale.getDefault()));
		System.out.println(printCurrency(Currency.getInstance("JPY")));
		
		PrintWriter fout = null;
		try {
			fout = new PrintWriter(new File("Currency.lst"));
			Set<Currency> currencies = Currency.getAvailableCurrencies();
			for (Currency curr : currencies){
				fout.println(printCurrency(curr) );
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fout != null)
				fout.close();
		}
		
	}
	private static String printCurrency(Currency curr){
		return curr.getDisplayName() + " : " + curr.getSymbol() + " : " + curr.getCurrencyCode()
				+ " : " + curr.getNumericCode() + " : " + curr.getDefaultFractionDigits() ;
	}
	
}
