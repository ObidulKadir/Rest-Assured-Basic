package Review;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.json.JSONObject;
import org.testng.annotations.Test;


public class Obj_JSON {
	String id;
	
	@Test
	void postUsingORGJson() {
		JSONObject data = new JSONObject();
		data.put("name", "Jason halum");
		data.put("age", 21);
		data.put("grade", "F");
		
		String courseArry[] = {
				"Python",".NET"
		};
		data.put("courses", courseArry);
		
		id = 
				given()
					.contentType("application/json")
					.body(data.toString())
					
				.when()
					.post("http://localhost:3000/students")
					
				.then()
					.statusCode(201)
					.body("name", equalTo("Jason halum"))
					.body("age", equalTo(21))
					.body("courses[0]", equalTo("Python"))
					.body("courses[1]", equalTo(".NET"))
					.log().all()
					.extract().jsonPath().getString("id");
		
		System.out.println("Generated id : "+id);
		
		//use a hashmap when you are writing internal java logic and need a fast way to look up data
		// use a jsonObject when you are preparing data to be sent to a web client, a mobile app, or a database that stores json.
		
		
					
	}
	
	
	
}
