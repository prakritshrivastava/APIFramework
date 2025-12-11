package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidatorUtil {
	
	//Encapsulate the response.
	private static String getJsonResponseAsString(Response response) {
		return response.getBody().asString();
	}

	//Below method capable of returning any wrapper data type, Remember that <T> is the type Parameter and T is the actual type.
	public static <T>T read(Response response,String jsonPath) {
		ReadContext ctx=JsonPath.parse(getJsonResponseAsString(response));
		return ctx.read(jsonPath);
	}
	
	//below is overloaded method for returning list
	public static <T> List<T> readList(Response response,String jsonPath) {
		ReadContext ctx=JsonPath.parse(getJsonResponseAsString(response));
		return ctx.read(jsonPath);
	}
	
	//below is overloaded method for returning maps
	public static <T> List<Map<String,T>> readListOfMap(Response response,String jsonPath) {
		ReadContext ctx=JsonPath.parse(getJsonResponseAsString(response));
		return ctx.read(jsonPath);
	}
}
