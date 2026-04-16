package StudentAPI;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;

import com.github.javafaker.Faker;

public class GetStudent {
	
	@Test
	void testGetStudent(ITestContext context) {
		int id = (int) context.getSuite().getAttribute("id");
		
		given()
					.contentType("application/json")
					.pathParam("id", id)
					
					
					.when()
						.get("https://thetestingworldapi.com/api/studentsDetails/{id}")
						
					.then()							
					.statusCode(200)
//					.body("status", equalTo(true))
					.log().all();
	
	}

}
