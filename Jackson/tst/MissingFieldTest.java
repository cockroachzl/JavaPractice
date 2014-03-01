import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import creature.Cat;
import creature.SimpleCat;


public class MissingFieldTest {
	@Test
	public void serialize() throws JsonGenerationException, JsonMappingException, IOException {
		Cat cat = new Cat();
		cat.setName("cat");
		cat.setLikesCream(true);
		cat.setLives(5);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("cat.json"), cat);
		
		String str = mapper.writeValueAsString(cat);
		System.out.println(str);
	}
	
	@Test
	public void deserialize() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
		SimpleCat cat = mapper.readValue(new File("cat.json"), SimpleCat.class);
		
		String str = mapper.writeValueAsString(cat);
		System.out.println(str);
	}
}
