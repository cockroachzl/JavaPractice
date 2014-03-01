import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
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
	
	@Test
	public void testStreamingWrite() throws IOException {
		JsonFactory f = new JsonFactory();
		JsonGenerator g = f.createJsonGenerator(new File("user.json"), JsonEncoding.UTF16_LE);

		g.writeStartObject();
		g.writeObjectFieldStart("name");
		g.writeStringField("first", "Joe");
		g.writeStringField("last", "Sixpack");
		g.writeEndObject(); // for field 'name'
		g.writeStringField("gender", User.Gender.MALE.toString());
		g.writeBooleanField("verified", false);
		g.writeFieldName("userImage"); // no 'writeBinaryField' (yet?)
		byte[] binaryData = { 0, 1, 2, 3 };
		g.writeBinary(binaryData);
		g.writeEndObject();
		g.close(); // important: will force flushing of output, close underlying output
	}
	
	@Test
	public void testStreamingRead() throws JsonParseException, IOException {
		JsonFactory f = new JsonFactory();
		JsonParser jp = f.createJsonParser(new File("user.json"));
		User user = new User();
		JsonToken token = jp.nextToken(); // will return JsonToken.START_OBJECT (verify?)
		assertEquals(JsonToken.START_OBJECT, token);
		while (jp.nextToken() != JsonToken.END_OBJECT) {
		  String fieldname = jp.getCurrentName();
		  jp.nextToken(); // move to value, or START_OBJECT/START_ARRAY
		  if ("name".equals(fieldname)) { // contains an object
		    User.Name name = new User.Name();
		    while (jp.nextToken() != JsonToken.END_OBJECT) {
		      String namefield = jp.getCurrentName();
		      jp.nextToken(); // move to value
		      if ("first".equals(namefield)) {
		        name.setFirst(jp.getText());
		      } else if ("last".equals(namefield)) {
		        name.setLast(jp.getText());
		      } else {
		        throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
		      }
		    }
		    user.setName(name);
		  } else if ("gender".equals(fieldname)) {
		    user.setGender(User.Gender.valueOf(jp.getText()));
		  } else if ("verified".equals(fieldname)) {
		    user.setVerified(jp.getCurrentToken() == JsonToken.VALUE_TRUE);
		  } else if ("userImage".equals(fieldname)) {
		    user.setUserImage(jp.getBinaryValue());
		  } else {
		    throw new IllegalStateException("Unrecognized field '"+fieldname+"'!");
		  }
		}
		jp.close(); // ensure resources get cleaned up timely and properly
	}
}
