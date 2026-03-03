package Day3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

import groovy.util.logging.Log;
import io.restassured.filter.cookie.CookieFilter;

public class cookies {

	@Test (priority = 1)
	void sessionFlow() {
		given().baseUri("https://httpbin.org")

				.when().get("/cookies/set?session_id=abc123&user=aryan")
				
				.then()
				.statusCode(anyOf(is(200), is(302)))
				.log().headers();
//				.log().cookies();
	}
	
	@Test(priority = 2)
	void cookieSessionFlow() {
		CookieFilter cookieFilter = new CookieFilter();
		System.out.println(cookieFilter);
		
		// set cookie
		
		given()
			.baseUri("https://httpbin.org")
			.filter(cookieFilter)
			.redirects().follow(false)
			
		.when()
		.get("/cookies/set?session_id=abc123")
		
		.then()
			.statusCode(anyOf(is(200), is(302)));
//			.log().cookies();
		
		//verify cookie carried
		
		given()
		.baseUri("https://httpbin.org")
		.filter(cookieFilter)
		
		.when()
		 .get("/cookies")
		 .then()
		 	.statusCode(200)
		 	.body("cookies.session_id", equalTo("abc123"))
			.log().cookies();
	}
	

}
