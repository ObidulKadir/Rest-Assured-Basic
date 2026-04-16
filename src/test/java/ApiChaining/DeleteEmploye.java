package ApiChaining;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class DeleteEmploye {

	@Test
	void testDeleteEmploye(ITestContext context) {
		int id = (int) context.getSuite().getAttribute("id");

		given().contentType("application/json").pathParam("id", id)
//				.body(data.toString())

				.when().delete("https://dummy.restapiexample.com/api/v1/delete/{id}").then().statusCode(204).log()
				.all();

		System.out.println("Generated id from Create user : " + id);

	}
}
