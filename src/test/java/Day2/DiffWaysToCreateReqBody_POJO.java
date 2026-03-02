package Day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;

import org.testng.annotations.Test;

import net.minidev.json.JSONObject;

public class DiffWaysToCreateReqBody_POJO {
	
String id;
	
	/*
	 * 1. post request body with hashmap
	 * 2. Post request body with org.json
	 * 3. Post request body creation using POJO class
	 * 4. Post request using external json file data.
	 */
	
	@Test (priority = 1)
	void testPostUsingPojoClass() {
		
		Post_POJOClass data = new Post_POJOClass();
		
		data.setName("Jason Mamua");
		data.setAge("21");
		data.setGrade("F");
		String courseArry[] = {
				"Python",".NET"
		};
		data.setCourses(courseArry);
		
		id =
			given()
			.contentType("application/json")
			.body(data)
			//.body(data.toString())
		
		.when()
			.post("http://localhost:3000/students")
			
		.then()
			.statusCode(201)
			.body("name", equalTo("Jason Mamua"))
			.body("age", equalTo("21"))
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
