package Day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class DeleteUser {

	@Test
	void testdeleteUser(ITestContext context) {
		
		int id = (int) context.getSuite().getAttribute("id");
			
		String BearerToken = (String) context.getSuite().getAttribute("token");
		System.out.println("bearer token "+BearerToken);
			
			given()
					.headers("Authorization", "Bearer "+BearerToken)
					.contentType("application/json")
					.pathParam("id", id)
//					.body(data.toString())
			
			.when()
				.delete("https://gorest.co.in/public/v2/users/{id}")
			.then()
				.statusCode(204)
				.log().all();
				
		System.out.println("Generated id from Create user : "+id);
		
			
			
		}


}
