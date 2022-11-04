//hard assertion: 
package test_cases;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
//import static io.restassured.RestAssured.given;
//import static io.restassured.RestAssured.when;
//import static io.restassured.RestAssured.then
public class Reading_all_products {
	
	@Test
	public void readAllProducts() {
//		Given: all input details(baseUri,Headers,Payload/body,Query Parameters);
//		When: submit api request -->http metohd (Endpoint/Resource);
//		Then: validate response (status code,Headers,responseTime, Payload/body)
//		base URL/baseUri https://techfios.com/api-prod/api/product
//			Endpoint/Resource /read.php
//			Headers: content-type: application/json
//			Query Parameters: no
//			Http method: GET
//			autorization: no
//			Payload/body: no
//			status code: 200 ok

		Response response = 
	
		given()
			    .baseUri("https://techfios.com/api-prod/api/product")
		        .header("Content-Type","application/json; charset=UTF-8")
//		        .auth().preemptive().basic("", ""). //basic auth
		        .header("Authorization","Bearer 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"). //bearer token
	    when()
	    	   .get("/read.php").
		then()
			   .extract().response();
		int ActualStatusCode = response.getStatusCode();	
		Assert.assertEquals(ActualStatusCode, 200);
		String ActualHeader = response.getHeader("Content-Type");
		Assert.assertEquals(ActualHeader, "application/json; charset=UTF-8");
		System.out.println(ActualHeader);
		
	}

}
