import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;


public class TestJacksonBasic {
	@Test
	public void testFull() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		User user = mapper.readValue(new File("user.json"), User.class);
		System.out.println(user);
		String str = mapper.writeValueAsString(user);
		System.out.println(str);
	}
	
	@Test
	public void testRaw() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		@SuppressWarnings("unchecked")
		Map<String,Object> userData = mapper.readValue(new File("user.json"), Map.class);
		System.out.println(userData);
		String str = mapper.writeValueAsString(userData);
		System.out.println(str);
	}
	
	@Test
	public void testGeneric() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		Map<String,User> userData = mapper.readValue(new File("users.json"), new TypeReference<Map<String,User>>() { });
		System.out.println(userData);
		String str = mapper.writeValueAsString(userData);
		System.out.println(str);
	}
	
	@Test
	public void testTreeModeReadAndModify() throws JsonProcessingException, IOException {
		ObjectMapper m = new ObjectMapper();
		// can either use mapper.readTree(source), or mapper.readValue(source,
		// JsonNode.class);
		JsonNode rootNode = m.readTree(new File("user.json"));
		System.out.println(rootNode);
		// ensure that "last name" isn't "Xmler"; if is, change to "Jsoner"
		JsonNode nameNode = rootNode.path("name");
		System.out.println(nameNode);
		String lastName = nameNode.path("last").getTextValue();
		System.out.println(lastName);
		
		if ("Sixpack".equalsIgnoreCase(lastName)) {
			((ObjectNode) nameNode).put("last", "Jsoner");
			// and write it out:
			m.writeValue(new File("user-modified.json"), rootNode);
		}
	}
	
	@Test
	public void testTreeModeCreate() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper m = new ObjectMapper();
		ObjectNode userOb = m.createObjectNode();
		ObjectNode nameOb = userOb.putObject("name");
		nameOb.put("first", "Joe");
		nameOb.put("last", "Sixpack");
		userOb.put("gender", User.Gender.MALE.toString());
		userOb.put("verified", false);
		byte[] imageData = {'A', 'B', 'C'}; // or wherever it comes from
		userOb.put("userImage", imageData);
		String str = m.writeValueAsString(userOb);
		System.out.println(str);
	}
}
