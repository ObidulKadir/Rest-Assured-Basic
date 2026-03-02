package Day3;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class Path_QueryParameters {
	
		//https://jsonplaceholder.typicode.com/posts?userId=1
	
	@Test
	void testQueryAndPathParameters() {
		
		given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.pathParam("pathP", "posts")
			.queryParam("userId", 1)
			
		.when()
			.get("/{pathP}")
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 1)
	void queryParamUsingMap() {
		Map <String, Object> params = new HashMap<>();
		params.put("userId", 1);
		params.put("_limit", 5);
		
		given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.pathParam("myPath", "posts")
			.queryParams(params)
			
		.when()
			.get("/{myPath}")
		
		.then()
			.statusCode(200);
		
	}
	
	@Test (priority = 2)
	public void listQueryParam() {

	    given()
	        .baseUri("https://dummyjson.com")
	        .queryParam("id", 1, 2, 3)
	    .when()
	        .get("/products")
	    .then()
	        .log().all();
	}

}
