//interview question
//hard assert: if the check doesn't pass it will not execute the rest of the code 
//soft assert: if the check doesn't pass it will execute this error and  still  run the rest of the code
// SoftAssert softAssert = new SoftAssert();           softassert object
// softAssert.assertEquals(actual, expected, message); softassert initialize 
package test_cases;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import java.util.HashMap;

public class Reading_products {
	
	SoftAssert softAssert;
	
	public Reading_products() {
		softAssert = new SoftAssert();
	}
	
	
	@Test
	public void readProducts() {
	
		Response response = 	
		given()
			    .baseUri("https://techfios.com/api-prod/api/product")
		        .header("Content-Type","application/json")
//		        .auth().preemptive().basic("", ""). //basic auth
		        .header("Authorization","Bearer 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") //bearer token
		        .queryParam("id", "602")
		        .queryParam("name", "Student Practicing  1.0").
		when()
	    	   .get("/read_one.php").
		then()
			   .extract().response();
		int ActualStatusCode = response.getStatusCode();	
//		Assert.assertEquals(ActualStatusCode, 200);
		softAssert.assertEquals(ActualStatusCode, 200, " code not match!");
		
		String ActualHeader = response.getHeader("Content-Type");
//		Assert.assertEquals(ActualHeader, "application/json");  hard assert
		softAssert.assertEquals(ActualHeader, "application/json", "header not match!");
		System.out.println(ActualHeader);
		
		String responseBody = response.getBody().asString();
		System.out.println("response body:" + responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		String responseID = jp.getString("id");
//		Assert.assertEquals(responseID,"602"); hard assert
		softAssert.assertEquals(responseID,"602","jp not match!");
		
		String responseNAME = jp.getString("name");
//		Assert.assertEquals(responseNAME,"Student Practicing  1.0"); hard assert
		softAssert.assertEquals(responseNAME,"Student Practicing  3.0", "name not match!");
		
		String responseDESCRIPTION = jp.getString("description");
//		Assert.assertEquals(responseDESCRIPTION,"The best pillow for Intervieweer."); hard assert
		softAssert.assertEquals(responseDESCRIPTION,"The best pillow for Interviewer.", "description not match!");
		
		String responsePRICE = jp.getString("price");
//		Assert.assertEquals(responsePRICE,"1000"); hard assert
		softAssert.assertEquals(responsePRICE,"2000", "price not match!");
		
		String responseCATEGORY_ID = jp.getString("category_id");
//		Assert.assertEquals(responseCATEGORY_ID,"2");
		softAssert.assertEquals(responseCATEGORY_ID,"2", "cat id not match!");
		
		String responseCATEGORY_NAME = jp.getString("category_name");
//		Assert.assertEquals(responseCATEGORY_NAME,"Electronics"); hard assert
		softAssert.assertEquals(responseCATEGORY_NAME,"Electronics", "cat name not match!");
		softAssert.assertAll(); //check each statement
	}

}
