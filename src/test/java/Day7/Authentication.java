package Day7;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.testng.annotations.Test;

public class Authentication {

	@Test(priority = 1)
	void testBasicAuthentication() {

		given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}
	/*
	 * Basic Authentication: Most straightforward method where the user name and
	 * password are provided to achieve a sucessfull authentication. When this
	 * method used, the credentials typically fo directly to the server to be
	 * validated.
	 */

	@Test(priority = 2)
	void testDigestAuthentication() {

		given().auth().digest("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}
//	
	/*
	 * Digest: more secure than basic auth. It does not hit the server directly with
	 * credentials instead it performs an internal process and may check multiple
	 * times before finalizing the connection to the server. . This complexity makes
	 * it harder for unauthorized parties to break the authentication
	 */

	@Test(priority = 3)
	void testPreemtiveAuthentication() {

		given().auth().preemptive().basic("postman", "password").when().get("https://postman-echo.com/basic-auth")
				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();
	}

	/*
	 * Preemptive: it is a combination of basic and digest auth. It is used basic
	 * auth but the adds an additional layer of a preemptive alogorthim on top of
	 * it.
	 */

//	@Test(priority = 4)
	void testBearerTokenAuthentication() {

		String bearer_token = "GITHUB_TOKEN";

		given().headers("Authorization", "Bearer " + bearer_token).when().get("https://api.github.com/user/repos")
				.then().statusCode(200).log().all();

	}

	/*
	 * Bearer token authentication is a widely used security method where a user
	 * must generate a specific token to access an API. Syntax: The header key is
	 * typically Authorization, and the value must include the word Bearer followed
	 * by a space and the actual token string (e.g., Bearer your_token_here
	 */
	@Test(priority = 4)
	void testAuth2Authentication() {

		given().auth().oauth2("GITHUB_TOKEN").when()
				.get("https://api.github.com/user/repos").then().statusCode(200).log().all();
	}

	/*
	 * OAuth 2.0 is an advanced and modern authentication and authorization standard
	 * used to secure APIs Oath1.0 has four parameters but auth2.0 has only
	 * parameters but both machanisim are similar in terms of security and
	 * alogortihm
	 */

	/*   === bearer token vs oauth 2.0
	 * The main difference is that OAuth 2.0 is a complete framework that defines
	 * how to get access to data, while a Bearer Token is a specific type of
	 * credential used within that framework to actually prove you have access
	 */

	@Test(priority = 5)
	void testAPIKeyAuthentication() {
		given().pathParam("myPath", "geo/1.0/direct").queryParam("q", "London").queryParam("limit", 5)
				.queryParam("appid", "e4b4e44f4a6311f21ab70176be529c0f").when()
				.get("http://api.openweathermap.org/{myPath}").then().statusCode(200).log().all();
	}
	/*
	 * API KEY: API Key authentication is a method used to authenticate requests by
	 * providing a specific key to the server. by using the App key, the request
	 * sends to the server.
	 */
}
