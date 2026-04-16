package ApiChaining;

import static io.restassured.RestAssured.*;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class GetEmploye {
	
@Test
void testGetEmaploy(ITestContext context) {

		int id = (int) context.getSuite().getAttribute("id");
		
		
	given()
				.contentType("application/json")
				.pathParam("id", id)
//				.body(data.toString())
		
		.when()
			.get("https://dummy.restapiexample.com/api/v1/employee/{id}")
		.then()
			.statusCode(200)
			.log().all();
	
			
	System.out.println("Generated id from Create user : "+id);
		
	}
	

}
