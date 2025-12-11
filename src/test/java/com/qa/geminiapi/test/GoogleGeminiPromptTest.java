package com.qa.geminiapi.test;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoogleGeminiPromptTest extends BaseTest{

	
	@Test
	public void geminiPromptTest() {
		
		String requestBody="{\r\n"
				+ "    \"contents\": [\r\n"
				+ "      {\r\n"
				+ "        \"parts\": [\r\n"
				+ "          {\r\n"
				+ "            \"text\": \"Emulate the output of the code int a = 10; int b = 20; int c = a + b; System.out.println(c);\"\r\n"
				+ "          }\r\n"
				+ "        ]\r\n"
				+ "      }\r\n"
				+ "    ]\r\n"
				+ "}";
		
		ConfigManager.set("apikey", "");
		Response response=restClient.post(BASE_URL_GEMINI, GEMINI_ENDPOINT, requestBody, null, null, AuthType.API_KEY, ContentType.JSON);
		response.prettyPrint();
	}
	
	
}
