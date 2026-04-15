package Day8;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class GetUser {
	@Test
	void testgetUser(ITestContext context) {
		int id = (int) context.getSuite().getAttribute("id"); // get the id from the create user class.
		/*
		 * getAttribute method returns an Object type, it cannot be saved directly into an integer variable
. You must add casting (e.g., (Integer)) to convert it into the correct format for your code to work
		 */

		String BearerToken = (String) context.getSuite().getAttribute("token");
		System.out.println("bearer token "+BearerToken);
		

		given()
			.headers("Authorization", "Bearer " + BearerToken)
			.contentType("application/json")
			.pathParam("id", id)

		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		
		.then()
			.statusCode(200)
			.log().all();	
		
		context.setAttribute("id", id);

		System.out.println("Generated id from Create user : " + id);

	}

}
