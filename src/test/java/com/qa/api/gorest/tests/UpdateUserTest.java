package com.qa.api.gorest.tests;

import static org.testng.Assert.ARRAY_MISMATCH_TEMPLATE;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.*;


public class UpdateUserTest extends BaseTest {

	
	@Test
	public void updateUserTest() {
		
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
		
		
		//Update the user using setters
		user.setStatus("inactive");
		user.setName("Peter API");
		
		Response responsePut=restClient.put(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userID, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(responsePut.jsonPath().get("name"), user.getName());
		Assert.assertEquals(responsePut.jsonPath().get("status"), user.getStatus());
		
	}
	
	
}
