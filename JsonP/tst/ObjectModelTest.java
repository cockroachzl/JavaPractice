import static org.junit.Assert.*;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.junit.Test;


public class ObjectModelTest {

	@Test
	public void testReader() {
		JsonReader reader = Json.createReader(new StringReader("{}"));
		JsonObject json = reader.readObject();
		assertEquals(0,	json.size());
	}

}
