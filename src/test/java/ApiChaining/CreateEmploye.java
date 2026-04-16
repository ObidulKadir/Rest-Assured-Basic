package ApiChaining;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateEmploye {
	
	@Test
	void createEmploye(ITestContext context) {

		
		JSONObject data = new JSONObject(); // create jsonobject to store data.
		Faker faker = new Faker(); // faker to generate dynamic data.
		
		data.put("name",faker.name().fullName());
		data.put("salary", faker.options().option("50000","5645464"));
		data.put("age", 25);
		
	int id =	given()
				.contentType("application/json")
				.body(data.toString())
		
		.when()
			.post("https://dummy.restapiexample.com/api/v1/create")
			.jsonPath().getInt("data.id");
	
//	context.setAttribute("id", id);
	context.getSuite().setAttribute("id", id);
			
	System.out.println("Generated id from Create user : "+id);
		
	}

}
