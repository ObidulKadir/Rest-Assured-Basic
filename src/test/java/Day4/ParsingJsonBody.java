package Day4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonBody {

	@Test
	void testJsonResponse() {
		/*
		 * //approach 1 given() .contentType("ContentType.JSON")
		 * 
		 * .when()
		 * 
		 * .get("http://localhost:3000/students")
		 * 
		 * .then() .statusCode(200) // .header("content-type",
		 * "application/json; charset =utf-8") .header("Content-Type",
		 * containsString("application/json")) .log().all() .body("students.size()",
		 * equalTo(5)) .body("[4].id", equalTo("e38d")) .body("[4].name",
		 * equalTo("Jason Mamua")) .body("[4].courses[0]", equalTo("Python"));
		 * 
		 */
		// Approach 2

		Response res = given().contentType(ContentType.JSON)

				.when().get("http://localhost:3000/students");

		Assert.assertEquals(res.getStatusCode(), 200);

		String course = res.jsonPath().getString("[4].courses[0]");
		Assert.assertEquals("Python", course);
		Assert.assertEquals(res.header("Content-Type"), "application/json");

		String name = res.jsonPath().getString("[1].name");

		Assert.assertEquals(name, "Jane Smith");

	}

//	@Test(priority =  2)
//	void testJsonObjResponseBody() {
//		Response res = given().contentType("ContentType.JSON")
//
//				.when().get("http://localhost:3000/students");
//
//		JSONObject jo = new JSONObject(res.toString()); // converting response to json object type
//
//		for (int i = 0; i < jo.getJSONArray("students").length(); i++) {
//			String studentName = jo.getJSONArray("students").getJSONObject(i).get("name").toString();
//			System.out.println(studentName);
//			
//		}


	@Test(priority = 2)
	void testJsonArrayResponseBody() {

	    Response res = given()
	            .contentType(ContentType.JSON)
	    .when()
	            .get("http://localhost:3000/students");

	   JSONArray  ja = new JSONArray(res.getBody().asString());// converting json body to json array

	    for (int i = 0; i < ja.length(); i++) {

	        String studentName = ja.getJSONObject(i).get("name").toString();

	        System.out.println(studentName);
	        
	    }
	}
	
	@Test(priority = 3)
	void testJsonArrayListResponseBody() {

	    Response res = given()
	            .contentType(ContentType.JSON)
	    .when()
	            .get("http://localhost:3000/students");

	  List<String > names = res.jsonPath().getList("name");
	  boolean status = false;
	  for(String n : names) {
		  System.out.println(n);
		   if(n.equals("Michael Johnson")) {
			   status= true;
			   break;
		   }
	}
	  Assert.assertEquals(status, true);

	}

	}
