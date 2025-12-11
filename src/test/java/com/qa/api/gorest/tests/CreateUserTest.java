package com.qa.api.gorest.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import com.qa.api.pojo.User;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {


	@Test(priority=2)
	public void createUserWithPOJOTest() {
		
		//User user = new User(10,"tom","tomapi@nal.com","male","active");
		User user = User.builder().name("tomm").email("tomtest123@hotmail.com").status("active").gender("male").build();
		Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		response.prettyPrint();
		ChainTestListener.log(response.getBody().asString());
	}
	
	//Set the token for GoRest
	@BeforeClass
	public void goRestTokenSetup() {
		ConfigManager.set("bearertoken", "bf6be8aecd5c51e77358afe8090209744f44d4bfa03c8338d5a091410feeb045");
	}
	
	//Data provider always returns data in form of 2D Array
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"Karim","active","male"},
			{"Jyoti","active","female"},
			{"Sandesh","inactive","male"},
		};
	}
	
	@DataProvider
	public Object[][] getUserSheetData() {
		return ExcelUtil.readDatafromExcel("UserData");
	}

	//Order of method parameters should be same as the columns inside the excel
	@Test(priority=1,dataProvider="getUserSheetData")
	public void createUserWithPOJOTestWithRamdomREmail(String name,String gender,String status) {
		
		//User user = new User(10,"tom","tomapi@nal.com","male","active");
		User user = User.builder().name(name).email(StringUtils.getRandomEmailID()).status(status).gender(gender).build();
		Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		System.out.println("The Response is as below");
		response.prettyPrint();
		ChainTestListener.log(response.getBody().asString());
	}
	
	
}