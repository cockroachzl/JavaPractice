
public class Fibonacci {
	public static int compute(int n) {
		if (n == 0 || n == 1)
			return n;
		int n0 = 0;
		int n1 = 1;
		int n2 = n0 + n1;
		int i = 2;
		while ( i++ <= n) {
			n2 = n0 + n1;
			n0 = n1;
			n1 = n2;
		}
		return n2;
	}
	
	long getNthElement(int i) {
		return 0;
	}
}