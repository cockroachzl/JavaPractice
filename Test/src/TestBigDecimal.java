import java.math.BigDecimal;
import java.math.RoundingMode;

public class TestBigDecimal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal d0 = new BigDecimal(46.21);
		print(d0);
		
		BigDecimal d0s = new BigDecimal("46.00");
		print(d0s);
		
		BigDecimal d0ss = d0s.stripTrailingZeros();
		print(d0ss);
		
		BigDecimal d1 = d0.setScale(2, RoundingMode.DOWN);
		print(d1);
		
		int i = 2;
		BigDecimal d2i = new BigDecimal(i);
		print(d2i);
		
		BigDecimal q = d0s.divide(d2i,2,RoundingMode.DOWN);
		print(q);
		
		BigDecimal r = d0s.subtract(q.multiply(d2i));
		print(r);
	}
	
	static void print(BigDecimal d0){
		System.out.println(d0.toPlainString() + " scale: " + d0.scale() + " unscaledValue: " + d0.unscaledValue() 
				+ " presicion: " + d0.precision() + " ulp: " + d0.ulp() );
	}

}
