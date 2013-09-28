package instrument;
import java.lang.instrument.*;
import java.math.BigDecimal;


public class MyAgent {
  public static void premain(String args, Instrumentation inst) {
    BigDecimal num = new BigDecimal("123.456");
    long size = inst.getObjectSize(num);
    System.out.println("Bytes used by object: " + size);
  }
}