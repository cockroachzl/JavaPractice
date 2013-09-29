

public class TestDouble {
	public static void main(String[] args){
		double d0 = 1.0e-20;
		System.out.println(d0);
		double d1 = 1;
		double d2 = d1 + d0;
		System.out.println(d1);
		System.out.println(d2);
		System.out.println(Math.log10(d0));
		System.out.println(Math.log10(d1));
		System.out.println(Math.log10(d2));
	}
}
