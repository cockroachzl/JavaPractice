

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Currency;
import java.util.Set;

public class TestCurrency {

	public static void main(String[] args) {
		Set<Currency> currs = Currency.getAvailableCurrencies();
		try (PrintWriter fout = new PrintWriter(new File("currency.lst")); PrintWriter fout2 = new PrintWriter(new File("currency_3frac.lst"))) {
			for ( Currency curr : currs) {
				fout.println(curr.getDisplayName() + " : " + curr.getSymbol() + " : " + curr.getCurrencyCode() + " : " + curr.getNumericCode()
						+ " : " + curr.getDefaultFractionDigits());
				if (curr.getDefaultFractionDigits() == 3)
					fout2.println(curr.getDisplayName() + " : " + curr.getSymbol() + " : " + curr.getCurrencyCode() + " : " + curr.getNumericCode()
						+ " : " + curr.getDefaultFractionDigits());
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
