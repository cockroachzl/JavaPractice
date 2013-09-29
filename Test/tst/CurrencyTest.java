import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Assume;

import org.junit.rules.TestRule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyTest {
	
	private static int testCount = 0;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Rule
	public TestName testName = new TestName();
	
	@Rule
	public TestRule globalTimeout = new Timeout(1000);
	
	@BeforeClass
	public static void setup(){
		System.out.println("BeforeClass");
	}
	@Before
	public void before() {
		++testCount;
	}
	@Test (timeout = 100)
	public void test1() {
		BigDecimal totalAmount = new BigDecimal("123");
		BigDecimal divisor = new BigDecimal("2");
		assertEquals(new BigDecimal("62"), totalAmount.divide(divisor,0,RoundingMode.HALF_UP));
		assertEquals(new BigDecimal("61"), totalAmount.divide(divisor,0,RoundingMode.DOWN ));
		thrown.expect(ArithmeticException.class);
		thrown.expectMessage(startsWith("Rounding"));
		assertEquals(new BigDecimal("61"), totalAmount.divide(divisor,0,RoundingMode.UNNECESSARY));
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void test2() {
		new ArrayList<Object>().get(1);
	}
	
	@Test
	public void test3() {
		thrown.expect(IndexOutOfBoundsException.class);
		new ArrayList<Object>().get(1);
	}
	
	@Test
	public void test4() {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Assume.assumeTrue("length of array", arr.size() > 1);
		assertEquals("get(1)", 100, arr.get(1).intValue());
	}
	
	@Test
	public void test5() {
		assertEquals("check test method name", "test5", testName.getMethodName());
	}
	
	@After
	public void after() {
		System.out.println("after");
	}
	
	@AfterClass
	public static void shutdown() {
		System.out.println(testCount + " finished." );
	}
}
