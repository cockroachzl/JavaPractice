import java.util.Currency;
import java.util.Locale;
import java.util.*;
public class TestCurrency {
	public static void main(String[] args){
		System.out.println("The current currency is " + Currency.getInstance(Locale.getDefault()));
		Set<Currency> currencies = Currency.getAvailableCurrencies();
		for (Currency curr : currencies){
			System.out.println(curr.getDisplayName() + " : " + curr.getSymbol() + " : " + curr.getCurrencyCode());
		}
	}
}
