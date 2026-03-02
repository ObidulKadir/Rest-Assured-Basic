package Day2;

import java.util.HashMap;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;

public class DiffWaysToCreateReqBody_Hashmap {
	
	String id;
	
	/*
	 * 1. post request body with hashmap
	 * 2. Post request body with org.json
	 * 3. Post request body creation using POJO class
	 * 4. Post request using external json file data.
	 */
	
	@Test (priority = 1)
	void testPostUsingHashmap() {
		
		HashMap data = new HashMap<>();
		
		data.put("name", "Jason Mamua");
		data.put("age", 21);
		data.put("grade", "F");
		
		String courseArry[] = {
				"Python",".NET"
		};
		data.put("courses", courseArry);
		
		id =
			given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Jason Mamua"))
			.body("age", equalTo(21))
			.body("courses[0]", equalTo("Python"))
			.body("courses[1]", equalTo(".NET"))
			.log().all()
			.extract().jsonPath().getString("id");
		
	}
	
@Test(priority = 2)
void testDelete() {
		
		
		given()

		.when()
			.delete("http://localhost:3000/students/"+id)
		
		.then()
			.statusCode(anyOf(is(200), is (204)))
			.log().all();
		
	}


}
