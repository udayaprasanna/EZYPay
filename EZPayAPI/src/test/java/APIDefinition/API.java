package APIDefinition;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * API is the base class for all API definition classes which contains all the common functions. 
 */
public class API {
	
	public API(String RequestPath)
	{
		
		try {
			
			  byte[] encoded = Files.readAllBytes(Paths.get(RequestPath));
			  requestString = new String(encoded, Charset.defaultCharset());
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RestAssured.baseURI = "https://r8alxaspi1.execute-api.ap-southeast-2.amazonaws.com/api/ezypay";
		request = RestAssured.given();
		
	}
	
	
	String requestString;
	
	public RequestSpecification request;
	
	public Response response;
	
	
	/**	 
	 * To build the API request from request file after replacing the place holders in the request with testdata
	 * @param testData should have the following format 'placeholder1:value1,placeholder2:value2'
	 */

	public void updateRequestwithTestData(String testData){
		
		//Code to replace test data in the request
		String [] fieldAndReplaceValue = testData.split(",");
		
		for(int index = 0; index < fieldAndReplaceValue.length; index++){
			
			String [] fieldAndValue = fieldAndReplaceValue[index].split(":");
			requestString = requestString.replace(fieldAndValue[0],fieldAndValue[1]);
			
		}
		
		//add json body to request
		request.header("Content-Type", "application/json");
		request.body(requestString);
		
		System.out.println(requestString);
		
	}

}
