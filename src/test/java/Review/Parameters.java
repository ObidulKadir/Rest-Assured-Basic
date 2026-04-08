package Review;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class Parameters {
	
 /*
  * What exactly do the path param: 
  * 1. fetch the specific resource
  * 2. update
  * 3. delete 
  * 
  * Similar, what exactly do the query param?
  * 1. filtering, sorting, pagination.
  * 2. Limiting fields.
  */
	
	@Test
	void queryandPathParameters() {
		
		given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.pathParam("pathP","posts")
			.queryParam("userId", 1)
			
			/*
			 * Usually, inside of given(), the requestSpecification object create and then
			 * store : Base URL, Path Param, Query Params.
			 */
		.when()
			.get("/{pathP}")
			
			/*
			 *  1. catch all data from given () and replace with {pathP} replace 
			 *  2. Add query parram
			 *  3. then build full URL
			 *  4. then, call a api.
			 */
			
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test ( dependsOnMethods = {"queryandPathParameters"})
	void queryParamUsingMap() {
		Map<String, Object> params = new HashMap<>(); // store key-value pairs to map
		
		//multiple Query params
		params.put("userId", 1);
		params.put("_limit", 5);
		
		given()
		.baseUri("https://jsonplaceholder.typicode.com")
		.pathParam("myPath", "posts")
		.queryParams(params)
		
		/*
		 * Multiple query params
		 * key = query param name
		 * value = query param value.
		 * 
		 * then convert this :  ?userId=1&_limit=5
		 */
		
	.when()
		.get("/{myPath}")
	
	.then()
		.statusCode(200);
	
	}
	@Test (priority = 3)
	public void listQueryParam() {

	    given()
	        .baseUri("https://dummyjson.com")
	        .queryParam("id", 1, 2, 3)
	    .when()
	        .get("/products")
	        /*
	         *  https://dummyjson.com/products?id=1&id=2&id=3
	         */
	    .then()
	        .log().all();
	}

}
