package StudentAPI;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateStudent {
	
	@Test
	void testCreateStudent(ITestContext context) {
		
		HashMap data = new HashMap<>();
		Faker faker = new Faker();
		
		Date pastDate = faker.date().past(10, TimeUnit.DAYS);
		
		data.put("first_name", faker.name().firstName());
		data.put("middle_name", faker.name().nameWithMiddle());
		data.put("last_name", faker.name().lastName());
		data.put("date_of_birth", "05-10-1994");
		
		int id = given()
					.contentType("application/json")
					.body(data)
					
					.when()
						.post("https://thetestingworldapi.com/api/studentsDetails")
						
					.then()							
					.statusCode(201)
					.log().all()
					.extract().jsonPath().getInt("id");
		
		System.out.println("Generated id "+id);
		context.getSuite().setAttribute("id", id);
	}

}
