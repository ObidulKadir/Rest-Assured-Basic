package Review;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.json.JSONArray;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class jsonParsing {
	
	@Test
	void testJsonResponseOne() {
	given().contentType("application/json")
	.when().get("http://localhost:3000/students")
	.then().statusCode(200).header("content-type", containsString("application/json"))
	.log().all();
	
	}
	
	@Test (priority = 2)
	public void jsonParsingTwo() {
		
		Response res = given().contentType(ContentType.JSON)
				.when().get("http://localhost:3000/students");
		
		Assert.assertEquals(res.statusCode(), 200);
		String name = res.jsonPath().getString("[0].name");
		System.out.println(name);
		
		Assert.assertEquals("John Doe", name);
		
				
	}
	@Test (priority = 3)
	void testJsonArrayRB() {
	    Response res = given()
	            .contentType(ContentType.JSON)
	    .when()
	            .get("http://localhost:3000/students");
	    
	    JSONArray ja = new JSONArray(res.getBody().asString());
	    System.out.println(ja);
	    
	    for (int i = 0; i < ja.length(); i++) {
	        String studentName = ja.getJSONObject(i).get("name").toString();
	        System.out.println(studentName);
	    }
	}
	
	@Test (priority = 4)
	void testJsonArrayListResponseBody() {

	    Response res = given()
	            .contentType(ContentType.JSON)
	    .when()
	            .get("http://localhost:3000/students");

	  List<String > names = res.jsonPath().getList("name");
	  boolean status = false;
	  for(String n : names) {
		  System.out.println("JsonArrayListResponseBody : "+n);
		   if(n.equals("Michael Johnson")) {
			   status= true;
			   break;
		   }
	  }}
}
