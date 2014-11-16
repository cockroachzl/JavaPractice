package jackson2;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersonJsonTest {

	@Test
	public void testPersonJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
		
		Person liang = new Person("Liang Zhang", 30);
		
		mapper.writeValue(new File("person.json"), liang);
		
		// or:
//		byte[] jsonBytes = mapper.writeValueAsBytes(myResultObject);
		// or:
//		String jsonString = mapper.writeValueAsString(myResultObject);
	}
}
