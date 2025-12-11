package com.qa.api.spotify.tests;

import org.testng.annotations.BeforeTest;

import com.qa.api.base.BaseTest;


public class SpotifyAPITest extends BaseTest{

	
	@BeforeTest
	public void getAccessToken() {
		restClient.post(BASE_URL_SPOTIFY_TOKEN, SPOTIFY_ENDPOINT, BASE_URL_HEROKU_APP, BASE_URL_GOREST, BASE_URL_FAKE_PRODUCT, null);
	}
	
	//Key: AU4Pj6EC7iiYfAkFy4w8u3tf7ALvxii8
	//Secret: oBfPFQH1abOvby7q
	
}
