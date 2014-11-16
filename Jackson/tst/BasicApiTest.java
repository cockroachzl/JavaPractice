import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.junit.Test;


public class BasicApiTest {

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
