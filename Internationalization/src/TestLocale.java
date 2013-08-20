import java.util.Locale;



public class TestLocale {
	public static void main(String[] args){
		System.out.println(Locale.getDefault().getDisplayLanguage());
		System.out.println(Locale.getDefault().getDisplayCountry());
		System.out.println(Locale.getDefault().getDisplayVariant());
		System.out.println(Locale.getDefault().getDisplayScript());
		System.out.println(Locale.getDefault().getDisplayName());
		System.out.println(Locale.FRANCE.getDisplayCountry(Locale.CHINA));
		

	}
}
