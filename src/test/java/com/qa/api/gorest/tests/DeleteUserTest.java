package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest extends BaseTest{
	
	
	
	@Test
	public void deleteUser() {
		
		//Create a user
				User user = User.builder()
								.name("Peter")
								.email(StringUtils.getRandomEmailID())
								.status("active")
								.gender("male")
								.build();
				Response postResponse=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
				
				int userID = postResponse.jsonPath().getInt("id");
				System.out.println("User ID Extracted: "+userID);
				
				//get same user
				Response getResponse=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
				Assert.assertEquals(getResponse.jsonPath().getInt("id"), userID);
				
				//delete same user
				Response responseDelete=restClient.delete(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userID, AuthType.BEARER_TOKEN, ContentType.JSON);
				Assert.assertEquals(responseDelete.getStatusCode(), 204);
				System.out.println("Response Message after delete: "+responseDelete.getStatusLine());
				
				//confirm if user is deleted
				Response getResponseConfirmation=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
				Assert.assertEquals(getResponseConfirmation.getStatusCode(), 404);
				Assert.assertEquals(getResponseConfirmation.jsonPath().getString("message"), "Resource not found");
				
	}
	

}
