import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigDecimalTest {
	public static void main(String[] args){
		BigDecimal d0 = new BigDecimal(4.01);
		print(d0);
		BigDecimal d1 = d0.setScale(2, RoundingMode.DOWN);
		print(d1);
		BigDecimal d2 = d0.add(d1);
		print(d2);
	}
	
	static void print(BigDecimal d){
		System.out.println(d.toPlainString() + " scale:" + d.scale());
	}
}
