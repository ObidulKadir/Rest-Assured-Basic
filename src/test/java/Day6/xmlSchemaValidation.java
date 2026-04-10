package Day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

public class xmlSchemaValidation {

	@Test
	public void xmlSchemaValidator() {
		given()

				.when().get("https://b34a79f7-433b-4556-81c9-97a10a235fda.mock.pstmn.io/books") // create mock server
																								// that will return the
																								// response only in xml
																								// format.
				.then().statusCode(200).log().all().assertThat()
				.body(RestAssuredMatchers.matchesXsdInClasspath("students.xsd")); //
		/*
		 * 1. AssertThat() - It is validation method 
		 * 2. Body - It will read the body content.
		 *  3. RestAssuredMatchers - Hamcrest method that will validate the xml
		 * schema. 4. MatchXsd - Validate the file in xsd format 5. inclasspath - called
		 * "src/test/resources" folder,
		 * 
		 * Summary: **This line validates that the API's XML response strictly follows
		 * the structure, tags, and data types defined in a specific `.xsd` schema
		 * file.**
		 ** 
		 * It automatically retrieves the schema from the project's `src/test/resources`
		 * folder to ensure full data integrity with a single assertion.**
		 */
	}
}
