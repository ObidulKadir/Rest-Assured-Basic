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

public class UpdateStudent {
	
	@Test
	void testUpdateStudent(ITestContext context) {
		
		int id = (int) context.getSuite().getAttribute("id");
		HashMap data = new HashMap<>();
		Faker faker = new Faker();
		
		data.put("id", id);
		data.put("first_name", faker.name().firstName());
		data.put("middle_name", faker.name().nameWithMiddle());
		data.put("last_name", faker.name().lastName());
		data.put("date_of_birth", "05-10-1994");
		
		given()
					.contentType("application/json")
					.body(data)
					.pathParam("id", id)
					
					.when()
						.put("https://thetestingworldapi.com/api/studentsDetails/{id}")
						
					.then()							
					.statusCode(200)
					.log().all();
				
	}

}
