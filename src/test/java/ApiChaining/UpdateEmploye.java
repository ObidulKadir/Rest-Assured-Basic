package ApiChaining;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateEmploye {

	@Test
	void testUpdateEmploye(ITestContext context) {

		int id = (int) context.getSuite().getAttribute("id");
		JSONObject data = new JSONObject(); // create jsonobject to store data.
		Faker faker = new Faker(); // faker to generate dynamic data.

		data.put("name", faker.name().fullName());
		data.put("salary", faker.options().option("50000", "5645464"));
		data.put("age", 30);

		given().contentType("application/json").body(data.toString()).pathParam("id", id)

				.when().put("https://dummy.restapiexample.com/api/v1/update/{id}")

				.then().log().all();

	}

}
