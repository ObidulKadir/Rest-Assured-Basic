package Day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	void testUpdateUser(ITestContext context) {

		int id = (int) context.getSuite().getAttribute("id");

		String BearerToken = (String) context.getSuite().getAttribute("token");
		System.out.println("bearer token "+BearerToken);

		JSONObject data = new JSONObject();
		Faker faker = new Faker();

		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().safeEmailAddress());
		data.put("status", faker.options().option("ACTIVE", "INACTIVE"));

		given().headers("Authorization", "Bearer " + BearerToken).contentType("application/json").body(data.toString()).pathParam("id", id)

				.when().patch("https://gorest.co.in/public/v2/users/{id}")
				.then().statusCode(200).log().all();
		

		System.out.println("Generated id from Create user : " + id);

	}

}
