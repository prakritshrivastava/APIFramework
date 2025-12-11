package com.qa.api.schema.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.*;

public class GoRestAPISchemaTest extends BaseTest {
	
	
	
	@BeforeClass
	public void goRestTokenSetup() {
		ConfigManager.set("bearertoken", "bf6be8aecd5c51e77358afe8090209744f44d4bfa03c8338d5a091410feeb045");	
	}
	
	
	@Test
	public void getUsersAPISchemaTest() {
		Response response=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN, ContentType.ANY);
		Assert.assertTrue(SchemaValidator.validateSchema(response,"getJsonSchema.json"));
		
	}
	
	@Test
	public void createUserAPISchemaTest() {
		
		User user=User.builder()
					.name("Peter")
					.email(StringUtils.getRandomEmailID())
					.gender("male")
					.status("active")
					.build();
		
		Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		Assert.assertTrue(SchemaValidator.validateSchema(response,"postJSONSchema.json"));
		
	}
	

}
