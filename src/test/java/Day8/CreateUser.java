package Day8;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {

	@Test
	void testCreateUser(ITestContext context) {
		
		String BearerToken = "goRestToken";
		
		JSONObject data = new JSONObject();
		Faker faker = new Faker();
		
		data.put("name",faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().safeEmailAddress());
		data.put("status", faker.options().option("ACTIVE", "INACTIVE"));
		
	int id =	given()
				.headers("Authorization", "Bearer "+BearerToken)
				.contentType("application/json")
				.body(data.toString())
				
		
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
	
//	context.setAttribute("id", id);
	context.getSuite().setAttribute("id", id);
	context.getSuite().setAttribute("token", BearerToken);
			
	System.out.println("Generated id from Create user : "+id);
	
		
		
	}
}
