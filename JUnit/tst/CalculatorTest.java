import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calc = new Calculator();
		double result = calc.add(10, 50);
		assertEquals(60, result, 1E-9);
	}

}
