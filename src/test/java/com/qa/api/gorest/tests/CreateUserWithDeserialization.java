package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.api.base.BaseTest;
import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.utils.StringUtils;
import com.qa.api.utils.ObjectMapperUtil;
import io.restassured.response.*;

import io.restassured.http.ContentType;

import com.qa.api.pojo.User;

public class CreateUserWithDeserialization extends BaseTest {

	
	@BeforeClass
	public void goRestTokenSetup() {
		ConfigManager.set("bearertoken", "bf6be8aecd5c51e77358afe8090209744f44d4bfa03c8338d5a091410feeb045");
	}
	
	@Test
	public void createUserTest() {
		
		User user = new User(null,"tom",StringUtils.getRandomEmailID(),"male","active");
		
		Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "tom");
		
		int userID=response.jsonPath().getInt("id");
		
		//GET Call
		Response getResponse=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userID, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		//Below will do deserialization and return object of the class
		User userResponse=ObjectMapperUtil.deserialize(getResponse, User.class);
		
		//Since now we have deserialized the response the assertions can be done using getters and setters
		Assert.assertEquals(userResponse.getName(),user.getName());
		Assert.assertEquals(userResponse.getEmail(),user.getEmail());
		Assert.assertEquals(userResponse.getGender(),user.getGender());
		Assert.assertEquals(userResponse.getStatus(),user.getStatus());
		
	}
	
	
}
