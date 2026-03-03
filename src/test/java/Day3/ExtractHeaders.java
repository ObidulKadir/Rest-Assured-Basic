package Day3;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class ExtractHeaders {
	
	@Test
	void extractResponseHeaders() {

		Response res = 
				given()
				.baseUri("https://jsonplaceholder.typicode.com")
				.pathParam("pathP", "posts")
				.queryParam("userId", 1)
				
				.when()
					.get("/{pathP}");
//				.then()
//					. statusCode(200)
//					.log().headers();
				
			System.out.println("Status code: "+res.statusCode());
			System.out.println("Content-type: "+res.getContentType());
			System.out.println("Session id: "+res.getSessionId());
		
	}

}
