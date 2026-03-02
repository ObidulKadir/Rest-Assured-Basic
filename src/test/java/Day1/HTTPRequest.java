package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import static io.restassured.matcher.RestAssuredMatchers.*;

public class HTTPRequest {
	
	int id ;

	@Test (priority = 1)
	void getStudentDetails() {
		given()

				.when().get("https://thetestingworldapi.com/api/studentsDetails")

				.then().statusCode(200).log().all();

	}

	@Test (priority = 2)
	void createStudentDetails() {

		HashMap data = new HashMap();
		data.put("first_name", "Kadir");
		data.put("middle_name", "AB");
		data.put("last_name", "sample string 4");
		data.put("date_of_birth", "sample string 5");
		
		

		id = given()
			.contentType("application/json")
			.body(data)

				.when()
					.post("https://thetestingworldapi.com/api/studentsDetails")
					.jsonPath().getInt("id");

				/*
				 * { "first_name": "sample string 2", "middle_name": "sample string 3",
				 * "last_name": "sample string 4", "date_of_birth": "sample string 5" }
				 */
//				.then()
//					.statusCode(201)
//					.log().all();

	}
	
	@Test(priority = 3, dependsOnMethods = {"createStudentDetails"})
	void updateStudentDetails() {
		
		HashMap data = new HashMap();
		data.put("id", id);
		data.put("first_name", "test1");
		data.put("middle_name", "tst2");
		data.put("last_name", "sample string 10");
		data.put("date_of_birth", "sample string 11");
		
				given()
					.contentType("application/json")
					.body(data)

				.when()
					.put("https://thetestingworldapi.com/api/studentsDetails/"+id)
				
				.then()		
					.statusCode(200)
					.body("status",equalTo("true"))
					.body("msg", equalTo("update  data success"))
					.log().all();
	}
}
