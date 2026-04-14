package Day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationDeserialization {

//	json object -----Serialize-------> JSON ----Deserialize---->json object

// Serialize - Pojo to Serialize.

	@Test
	void convertPojo2json() throws JsonProcessingException {

		// create pojo class object
		students studentPojo = new students();
		studentPojo.setName("Jason Mamua");
		studentPojo.setAge("21");
		studentPojo.setGrade("F");

		String courseArry[] = { "Python", ".NET" };
		studentPojo.setCourses(courseArry);

		// convert java object to json
		ObjectMapper objMapper = new ObjectMapper();

		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(studentPojo);
		// JsonObject converts it into string that represents a json data.
		System.out.println(jsonData);
	}

	@Test(priority = 2)
	void convertJson2Pojo() throws JsonMappingException, JsonProcessingException {

		String jsonData = "{\r\n" + "  \"name\" : \"Jason Mamua\",\r\n" + "  \"grade\" : \"F\",\r\n"
				+ "  \"age\" : \"21\",\r\n" + "  \"courses\" : [ \"Python\", \".NET\" ]\r\n" + "}";

		// convert json data to ---> Pojo Object
		ObjectMapper objMapper = new ObjectMapper();

		students JsonToPojo = objMapper.readValue(jsonData, students.class); // json data converts to pojo

		/*
		 * you cannot simply use a variable where you have defined a structure; you must
		 * provide a Class type (such as students.class) to act as a blueprint for the
		 * conversion . This is required because the ObjectMapper needs to know the
		 * specific target format to correctly map the incoming JSON fields to Java
		 * variables . The .class parameter tells the method "what type" of object to
		 * create so it can return a usable Java object with the correct data types
		 */
		System.out.println("Name : " + JsonToPojo.name);
		System.out.println("Name : " + JsonToPojo.grade);
	}

}
