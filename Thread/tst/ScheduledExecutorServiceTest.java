import javadoc.BeeperControl;

import org.junit.Test;


public class ScheduledExecutorServiceTest {

	@Test
	public void testBeeperControl() {
		BeeperControl bc = new BeeperControl();
		bc.beep();
	}
}
