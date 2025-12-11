package com.qa.api.client;

import java.io.File;
import java.util.Base64;
import java.util.Map;

import com.qa.api.constants.AuthType;
import com.qa.api.exceptions.FrameworkExceptions;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;


public class RestClient {
	
	
	//define response specs
	private ResponseSpecification responseSpec200=RestAssured.expect().statusCode(200);
	private ResponseSpecification responseSpec204=RestAssured.expect().statusCode(204);
	private ResponseSpecification responseSpec200or201=RestAssured.expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification response200or404 = RestAssured.expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	
	//Encapsulated Setup method
	private RequestSpecification setupRequest(String baseURL,AuthType authType,ContentType contentType) {
		
		//the .get/.post is not defined hereb only prerequisites are filled.
		RequestSpecification request=RestAssured.given().log().all()
												.baseUri(baseURL)
												.contentType(contentType)
												.accept(contentType);
		
		switch(authType) {
			case BEARER_TOKEN -> {
				request.header("Authorization","Bearer "+ConfigManager.get("bearertoken"));
				break;
			}
			case BASIC_AUTH -> {
				request.header("Authorization","Basic "+generateBasicAuthToken());
				break;
			}
			case API_KEY -> {
				request.header("X-goog-api-key",ConfigManager.get("apikey"));
				break;
			}
			case NO_AUTH -> {
				System.out.println("No Authentication");
				break;
			}
			default -> {
				System.out.println("This Auth type is not supported, pass one of BEARER_TOKEN/BASIC_AUTH/API_KEY/NO_AUTH");
				throw new FrameworkExceptions("Invalid Auth type");
			}
		}
		
		return request;
		
	}
	
	
	private String generateBasicAuthToken() {
		
		//First create string with user:password
		String credentials=ConfigManager.get("basicauthusername").trim()+":"+ConfigManager.get("basicauthpassword").trim();
		
		//Use existing Base64 java class to create the encoded version
		return Base64.getEncoder().encodeToString(credentials.getBytes());
		
	}
	
	
	private void applyParams(RequestSpecification request, Map<String,String> queryParams,Map<String,String> pathParams) {
		
		if(queryParams!=null) {
			request.queryParams(queryParams);
		}
		if(pathParams!=null) {
			request.pathParams(pathParams);
		}
		
	}
	
	
	public Response get(String baseURL,String endPoint,Map<String,String> queryParams,Map<String,String> pathParams,AuthType authType,ContentType contentType) {
		
		RequestSpecification request=setupRequest(baseURL,authType,contentType);
		applyParams(request,queryParams,pathParams);
		
		//Only get ResponseSpecification, but do not write assertions, As this needs to be done in method being called
		Response response=request.get(endPoint).then().spec(response200or404).extract().response();
		response.prettyPrint();
		return response;
		
	}
	
	//The below method returns T object which can accept any kind of content JSON/XML , but does not accept File type object.
	public <T>Response post(String baseURL,String endPoint,T body,Map<String,String> queryParams,Map<String,String> pathParams,AuthType authType,ContentType contentType) {
		
		RequestSpecification request=setupRequest(baseURL,authType,contentType);
		applyParams(request,queryParams,pathParams);
		
		//below URL encoding is made as false this is donw to ask rest assured not to encode the url (to allow special characters to be passed as is.)
		Response response=request.urlEncodingEnabled(false).body(body).post(endPoint).then().spec(responseSpec200or201).extract().response();
		return response;
		
	}
	
	//As above method accepts all kind of content but does not accept File type object, creating seperate object with File type and thus overload the above method.
	public Response post(String baseURL,String endPoint,File file,Map<String,String> queryParams,Map<String,String> pathParams,AuthType authType,ContentType contentType) {
		
		RequestSpecification request=setupRequest(baseURL,authType,contentType);
		applyParams(request,queryParams,pathParams);
		
		Response response=request.body(file).post(endPoint).then().spec(responseSpec200or201).extract().response();
		return response;
		
	}
	
	
	//Below overloaded method is for OAuth2.0, to get token for auth2.0 using client id and client secret
	public Response post(String baseURL,String endPoint,String clientID,String clientSecret,String grantType,ContentType contentType) {
		
		Response response=RestAssured.given()
								.contentType(contentType)
								.formParam("grant_type",grantType)
								.formParam("client_id",clientID)
								.formParam("client_secret",clientSecret)
					          .when()
					          	.post(baseURL+endPoint);
		
		response.prettyPrint();
		return response;
		
	}
	
	public <T>Response put(String baseURL,String endPoint,T body,Map<String,String> queryParams,Map<String,String> pathParams,AuthType authType,ContentType contentType) {
		
		RequestSpecification request = setupRequest(baseURL,authType,contentType);
		applyParams(request,queryParams,pathParams);
		
		Response response=request.body(body).put(endPoint).then().spec(responseSpec200or201).extract().response();
		return response;
		
	}
	
	public <T>Response patch(String baseURL,String endPoint,T body,Map<String,String> queryParams,Map<String,String> pathParams,AuthType authType,ContentType contentType) {
		
		RequestSpecification request = setupRequest(baseURL,authType,contentType);
		applyParams(request,queryParams,pathParams);
		
		Response response=request.body(body).patch(endPoint).then().spec(responseSpec200or201).extract().response();
		return response;
		
	}
	
	
	public Response delete(String baseURL,String endPoint,AuthType authType,ContentType contentType) {
		
		RequestSpecification request = setupRequest(baseURL,authType,contentType);
		
		Response response=request.delete(endPoint).then().spec(responseSpec204).extract().response();
		return response;
		
	}
	
	
	
}
