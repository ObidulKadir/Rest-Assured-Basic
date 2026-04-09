package Day6;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;


public class JsonSchemaValidation {
	
	@Test
	void jsonSchema() {
		
		given()
		
		.when()
			.get("http://localhost:3000/books")
		.then()
			.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemaValidation.json"));
		
		/*
		 * This is a predefined class in RestAssured specifically designed for schema validation
. It provides methods such as matchesJsonSchemaInClasspath, which allows the automation script to check the response body against a schema file located in the project's resources (typically the src/test/resources folder)
. This class is part of the io.rest-assured.module.jsv package
		 */
	}

}
