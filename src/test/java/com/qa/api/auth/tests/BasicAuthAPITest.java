package com.qa.api.auth.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthAPITest extends BaseTest {
	
	
	@Test
	public void basicAuthTest() {
		Response response=restClient.get(BASE_URL_HEROKU_APP, HEROKU_ENDPOINT, null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//As response is in HTML and not JSON , get the response as string and do not use jsonPath()
		Assert.assertTrue(response.getBody().asString().contains("Congratulations"));
	}
	
	

}
