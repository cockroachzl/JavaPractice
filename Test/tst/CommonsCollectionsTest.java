import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.junit.Test;

public class CommonsCollectionsTest {

	@Test
	public void testListUtils() {
		List<Integer> list0 = new ArrayList<>(Arrays.asList(1,2,3));
		List<Integer> list1 = new ArrayList<>(Arrays.asList(3,4,5));
		List<Integer> list2 = ListUtils.intersection(list0, list1);
		assertEquals(1, list2.size());
	}
}
