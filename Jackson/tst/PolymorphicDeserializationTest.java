import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import creature.Animal;
import creature.Dog;
import creature.PregnantDog;


public class PolymorphicDeserializationTest {

	@Test
	public void testSerialization() throws JsonGenerationException, JsonMappingException, IOException {
		Animal dog = new Dog("doggy", 10.0);
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
	
	@Test
	public void testSerialization2() throws JsonGenerationException, JsonMappingException, IOException {
		Animal dog = new Dog("doggy", 10.0);
		Animal dogmom = new PregnantDog("mommy", 20.0, dog);
//		System.out.println(dogmom.getClass().getSimpleName());
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("dogmom.json"), dogmom);
		
		String str = mapper.writeValueAsString(dogmom);
		System.out.println(str);
	}
	
	@Test
	public void testDeserialization2() throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		Animal dogmom = mapper.readValue(new File("dogmom.json"), Animal.class );
		
		String str = mapper.writeValueAsString(dogmom);
		System.out.println(str);
	}
}
