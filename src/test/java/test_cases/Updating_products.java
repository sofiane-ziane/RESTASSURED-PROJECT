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
public class Updating_products {
	
	SoftAssert softAssert;
	String access_token = null;
	
	public Updating_products() {
		softAssert = new SoftAssert();
	}

	
	@Test
	public void Update_a_Products() {
	


		Response response = 
		given()
			    .baseUri("https://techfios.com/api-prod/api/product")
		        .header("Content-Type","application/json")
//		        .body(payload). // String body 1st wway to post data
//		        .header("Authorization","Bearer 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") 
		        .header("Authorization","Bearer " +access_token) 
		        .body(new File("src/main/java/data/UpdatePayLoad2.json")). //file body 2nd way to post data
	    when()
	    	   .post("/update.php").
		then() //validation
			   .extract().response();
		int ActualStatusCode = response.getStatusCode();	
//		Assert.assertEquals(ActualStatusCode, 200);
		softAssert.assertEquals(ActualStatusCode, 200);
		
		String ActualHeader = response.getHeader("Content-Type");
//		Assert.assertEquals(ActualHeader, "application/json");
		softAssert.assertEquals(ActualHeader, "application/json; charset=UTF-8");
		System.out.println(ActualHeader);
		
		String responseBody = response.getBody().asString();
		System.out.println("response body:" + responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		String responseMessage = jp.getString("message");
//		Assert.assertEquals(responseMessage, "Product was updated.");
		softAssert.assertEquals(responseMessage, "Product was updated.");
		System.out.println(responseMessage);
		
		long responsTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("time is:" + responsTime);
		
		if(responsTime<=2000) {
			System.out.println("response time within range");
		}else{
			System.out.println("response time is out of range");
		}
		softAssert.assertAll();
	}
}
