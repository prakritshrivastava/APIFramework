package com.qa.api.gorest.tests;

import org.testng.Assert;
import java.util.*;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;

import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest extends BaseTest{

	
	@Test(priority=-1)
	public void getAllUsers() {
		Response response=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
		
	}
	
	@Test(priority=-2)
	public void getUserWithParams() {
		
		Map<String,String> queryMap = new HashMap<String,String>();
		queryMap.put("status", "active");
		
		Response response=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, queryMap, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(response.statusLine().contains("OK"));
		
	}

	
	
	
	
}
