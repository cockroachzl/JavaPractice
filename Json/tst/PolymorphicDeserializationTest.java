import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;


public class PolymorphicDeserializationTest {

	@Test
	public void testSerialization() throws JsonGenerationException, JsonMappingException, IOException {
		Animal dog = new Dog(10.0);
		dog.setName("a bog's name");
		Zoo zoo = new Zoo(dog);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("zoo.json"), zoo);
		
		String str = mapper.writeValueAsString(zoo);
		System.out.println(str);
	}
	
	@Test
	public void testDeserialization() throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		Zoo zoo = mapper.readValue(new File("zoo.json"), Zoo.class );
		
		String str = mapper.writeValueAsString(zoo);
		System.out.println(str);
	}
}
