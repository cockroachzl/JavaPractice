package knowledge;

import java.io.File;
import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

public class TestInheritance {
	
	@Test
	public void testBook() throws JsonGenerationException, JsonMappingException, IOException {
		Book book = new PaperBook("Liang", 10);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("book.json"), book);

		String str = mapper.writeValueAsString(book);
		System.out.println(str);
	}
}
