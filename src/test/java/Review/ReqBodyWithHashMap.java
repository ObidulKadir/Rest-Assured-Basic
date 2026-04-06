package Review;

import java.util.HashMap;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.matcher.RestAssuredMatchers.*;


public class ReqBodyWithHashMap {
	String id;
	
	@Test
	void testPostHashMap() {
		
		HashMap data = new HashMap<>();
		data.put("name", "Rj");
		data.put("Age", 21);
		data.put("grade", "F");
		
		String courseArray[] = {
				"Python", ".Net"
		};
		
		data.put("Courses", courseArray);
		
		System.out.println("Created HashMap data : "+data);
		
		id = 
				given()
				.contentType("application/json")
				.body(data)
				
				.when()
					.post("http://localhost:3000/students")
					
				.then()
					.statusCode(201)
					.body("name", equalTo("Rj"))
					.log().all()
					.extract().jsonPath().getString("id");
		
		System.out.println("Generated id : "+id);
							
	}
	
	@Test(dependsOnMethods = {"testPostHashMap"})
	void deletePostHashMap() {
		
		given()
		
		.when()
		 .delete("http://localhost:3000/students/"+id)
		 
		 .then()
		 	.statusCode(anyOf(is(200), is(204)))
		 	.log().all();
			
	}

}
