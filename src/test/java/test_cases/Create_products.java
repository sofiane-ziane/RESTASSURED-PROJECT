//hard assertion: 
package test_cases;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
//import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;
//import static io.restassured.RestAssured.then
public class Create_products {
	
	SoftAssert softAssert;
	String access_token = null;
	
	public Create_products() {
		softAssert = new SoftAssert();
	}

	
	@Test
	public void Create_a_Products() {
	
		HashMap<String,String> payload = new HashMap<String,String>();
//		payload.put("name", "value")
		payload.put("name", "My super macine 023");
		payload.put("price", "50");
		payload.put("description", "The best choice for amazing customer");
		payload.put("category_id", "2");

		Response response = 
		given()
			    .baseUri("https://techfios.com/api-prod/api/product")
		        .header("Content-Type","application/json; charset=UTF-8")
//		        .body(payload). // String body 1st wway to post data
//		        .header("Authorization","Bearer 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") 
		        .header("Authorization","Bearer " +access_token) 
		        .body(new File("src/main/java/data/createPayLoad.json")). //file body 2nd way to post data
	    when()
	    	   .post("/create.php").
		then() //validation
			   .extract().response();
		int ActualStatusCode = response.getStatusCode();	
		Assert.assertEquals(ActualStatusCode, 201);
		
		String ActualHeader = response.getHeader("Content-Type");
		Assert.assertEquals(ActualHeader, "application/json; charset=UTF-8");
		System.out.println(ActualHeader);
		
		String responseBody = response.getBody().asString();
		System.out.println("response body:" + responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		String responseMessage = jp.getString("message");
		Assert.assertEquals(responseMessage, "Product was created.");
		System.out.println(responseMessage);
		
		long responsTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("time is:" + responsTime);
		
		if(responsTime<=2000) {
			System.out.println("response time within range");
		}else{
			System.out.println("response time is out of range");
		}
	}
}
