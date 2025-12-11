package com.qa.api.base;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;
import com.aventstack.chaintest.plugins.*;

@Listeners(ChainTestListener.class)
public class BaseTest {

	/**
	 * Maintain all base URLS below
	 */
	//protected final static String BASE_URL_GOREST="https://gorest.co.in";
	protected static String BASE_URL_GOREST;
	protected final static String BASE_URL_FAKE_PRODUCT="https://fakestoreapi.com";
	protected final static String BASE_URL_HEROKU_APP="https://the-internet.herokuapp.com";
	protected final static String BASE_URL_SPOTIFY_TOKEN="https://accounts.spotify.com";
	protected final static String BASE_URL_AMADEUS_TOKEN="https://test.api.amadeus.com"; 
	protected final static String BASE_URL_GEMINI="https://generativelanguage.googleapis.com";
	
	/**
	 * Maintain API Endpoints
	 */
	protected final static String GOREST_USERS_ENDPOINT="/public/v2/users";
	protected final static String FAKE_PRODUCTS_ENDPOINT="/products";
	protected final static String HEROKU_ENDPOINT="/basic_auth";
	protected final static String SPOTIFY_ENDPOINT="/api/token";
	protected final static String AMADEUS_ENDPOINT="/v1/security/oauth2/token";
	protected final static String GEMINI_ENDPOINT="/v1beta/models/gemini-2.0-flash:generateContent";
	
	
	//Instantiate the RestClient
	protected RestClient restClient;
	
	//Create below method so that any test class in tests (which extends the BaseTest) can create an object of the restClient and thus access the RestClient methods
	//That is why it has been given with @BeforeTest annotation
	@BeforeTest
	public void initSetup() {
		restClient = new RestClient();
	}
	
	@BeforeSuite
	public void setupSuite() {
		BASE_URL_GOREST=ConfigManager.get("baseurl.gorest");
	}
	
	
	//Before Test
	
	//SAfter Test
	
	
}
