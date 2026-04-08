package Day3;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;

public class CookiesAndHeaders {
	
	
	
	//Practical: Headers send + Validate
	@Test
	void sendAndValidateHeaders() {
		
		given()
			.baseUri("https://httpbin.org")
			.header("Accept", "application/json")
			.header("X-Client-Version", "1.0.0")  //custom headers
		
		.when()
		        .get("/headers")
		
		.then()
			.statusCode(200)
			.body("headers.Accept", equalTo("application/json"))
			.body("headers.X-Client-Version", equalTo("1.0.0"))
			.log().all();
	}
	
	// extract headers from response + validate
	
	@Test(priority = 1)
	void extractResponseHeaders() {
		
		Response res = given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.pathParam("pathP", "posts")
			.queryParam("userId", 1)
			
	.when()
		.get("/{pathP}");
		
		System.out.println("Status: " + res.getStatusCode());
	    System.out.println("Content-Type: " + res.getHeader("Content-Type"));
		
	}
	
	//Practical: Cookie Set + Read
	@Test(priority = 2)
	void setAndVerifyCookies() {
		
		given()
				.baseUri("https://httpbin.org")
			
	.when()
		.get("/cookies/set?session_id=abc123&user=aryan")
    .then()
        .statusCode(anyOf(is(200), is(302)))
        .log().all();
		
        		
	}
	
	// Practical: Session maintain (Cookies auto carry forward)
	
	@Test(priority = 3)
	 void cookieSessionFlow() {
		
		CookieFilter cookieFilter = new CookieFilter();
		System.out.println(cookieFilter);

	    // Set cookie
	    given()
	        .baseUri("https://httpbin.org")
	        .filter(cookieFilter)
	        .redirects().follow(false)   
	    .when()
	        .get("/cookies/set?session_id=abc123")
	    .then()
	        .statusCode(anyOf(is(200), is(302)));

	    // Verify cookie carried
	    given()
	        .baseUri("https://httpbin.org")
	        .filter(cookieFilter)
	    .when()
	        .get("/cookies")
	    .then()
	        .statusCode(200)
	        .body("cookies.session_id", equalTo("abc123"))
	        .log().all();
	}
	
	//Practical: Login → Token extract → Next call _> send a token in a header 
	
	@Test(priority = 4)
	void tokenExtractAndSendNextCall() {
	    //1. login and extract token
		
		Response loginRs = given()
				.baseUri("https://reqres.in")
				.header("content-type", "application/json")
				.body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
				
				.when()
					.post("/api/login")
				.then()
					.statusCode(200)
					.body("token", notNullValue())
			        .extract().response();

		String token = loginRs.jsonPath().getString("token");
		System.out.println("token + " +token);
		
		
		//2. Use token as Authorization header
		
		given()
			.baseUri("https://reqres.in")
			.header("Authorization", "Bearer " + token)
			.header("Accept", "application/json")
			
		.when()
			.get("/api/users/2")
			
		.then()
			.statusCode(200)
			.log().all();
	}
	

}
