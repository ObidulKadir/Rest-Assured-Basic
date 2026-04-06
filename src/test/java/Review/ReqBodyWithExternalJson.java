package Review;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ReqBodyWithExternalJson {
	
	/* 4 ways the request body can be create
	 * 1. Post Request Body with Hashmap
	 * 2. Post Request Body with org.json
	 * 3. Post Request body creationg using POJO class
	 * 4. Post Request body using external Json File
	 */
	
	String id;
	
	@Test
	void testPostReqExternalJson() throws FileNotFoundException {
		
		File f = new File (".\\body.json"); //locate the file
		
		FileReader fr = new FileReader(f); // read the content what inside of the file.
		JSONTokener jt = new JSONTokener(fr); // Prepare that text for json parsing.
		
		JSONObject data  = new JSONObject(jt); // converts it to the json object.
		
		id = 
		given()
			.contentType("application/json") // Request content is JSON. "i am sending JSON Data"
			.body(data.toString()) // jsonObject data changes to JsonText.
			
		.when() // This marks the point where the request about to sent.
			.post("http://localhost:3000/students") // Actual data from the body.json, will posted in this URL.
		
		.then() // where the validation is defined. 
			.statusCode(201) // verifies the server returned HTTP status code 201. and this is the expected code if the status code return 501 then this test will fail.
			.body ("name", equalTo("Obidul Kadir")) // check the name . equalTo() -> matcher from hamcrest library. Mainly compares expected vs actual.
			.body("courses[0]", equalTo("Java")) // check the firstElement of the courses.
			.log().all() // prints all response in console.
			.contentType("application/json") // Way 1: to validate the header.
			.header("Content-Type", containsString("application/json")) // way 2: to validate the header.
			.extract().jsonPath().getString("id"); // extract the id from the json response. and Save the id into id variable.
			
				/*
				 *   Extract :: get the data from the response.
				 *   JsonPath() :: navigate JSON Response
				 *   getString() :: read the value from the JSON Response.
				 */
		
		System.out.println("Generated id : "+id);
	}
	
	@Test (dependsOnMethods = "testPostReqExternalJson")
	void deleteFromExternalJson() {
		
		System.out.println("Generated id from test 1 : "+id);
		
		given()
		
		.when()
			.delete("http://localhost:3000/students/" + id)
			
		.then()
			.statusCode(anyOf(is(200), is(204)))
			.log().all();
	}
	
	
	/*
	 *  then() -- Validate the response such as headers, cookies, time, content,logging, status code.
	 *   then().body() - Use it to validate the content  such as name, id, etc.
	 *   
	 *   Headers --- are metadata about the response. They describe how to handle the response.
	 *   Cookies-- Small data stored by browser/server. and used for login session, tracking , authentication.
	 */
	

}
