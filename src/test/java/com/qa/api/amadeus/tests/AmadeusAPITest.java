package com.qa.api.amadeus.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest{
	
	
	private String access_token;
	
	@BeforeTest
	public void getAccessToken() {
		
		Response response=restClient.post(BASE_URL_AMADEUS_TOKEN, AMADEUS_ENDPOINT, ConfigManager.get("cliendid_amadeus"), ConfigManager.get("cliendsecret_amadeus"), ConfigManager.get("granttype_amadeus"),ContentType.URLENC );
		access_token=response.jsonPath().get("access_token");
		System.out.println("Access token for AMADEUS: "+access_token);
		
	}
	
	@Test
	public void runTest() {
		System.out.println("Test is run");
	}
	
	

}
