package tests.API;

import APIDefinition.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;



//@SuppressWarnings("deprecation")
public class StepDef{

	API api;
	
	@Given("^I have a Login API POST request (.*?) with (.*?) username and (.*?) password$")
	public void i_have_a_Login_API_POST_request_with_username_and_password(String filename,String username, String password) throws Throwable {
		
		api = new API("src/test/resouces/RequestResponse/" + filename + ".json");
		
		api.updateRequestwithTestData("<username>:" + username + ",<password>:" + password);
		
	}
	
	@When("^I send a POST request to Login API$")
	public void i_send_a_POST_request_to_Login_API() throws Throwable {
	    
		api.response = api.request.post("/login");
	    
	}

	/**	 
	 * To verify the response with the response validation
	 * @param responseValidation should have the following format 'StatusCode,jsonpath1:expected value1,jsonpath2:expected value2'
	 */
	@SuppressWarnings("deprecation")
	@Then("^I should get the resposne with (.*?)$")
	public void i_should_get_the_resposne_with(String responseValidation) throws Throwable {
		
		String [] responseValidationarray = responseValidation.split(",");
		
		System.out.println(api.response.asString());
		
		Assert.assertEquals(responseValidationarray[0], String.valueOf(api.response.getStatusCode()));
		
		for(int index = 1; index < responseValidationarray.length; index++){
			
			String [] expResponseAndJSONPath = responseValidationarray[index].split(":");
			String expResponse = expResponseAndJSONPath[1];
			if(expResponseAndJSONPath.length > 1){
			
				for(int internalINdex = 2; internalINdex < expResponseAndJSONPath.length; internalINdex++){
					expResponse = expResponse + ":" + expResponseAndJSONPath[internalINdex];
					
				}
			}
			
			Assert.assertEquals(expResponse, api.response.jsonPath().get(expResponseAndJSONPath[0]));
			
		}

	}	
	
}


