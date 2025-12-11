package com.qa.api.utils;

import org.checkerframework.checker.units.qual.t;

import com.fasterxml.jackson.databind.*;
import com.qa.api.exceptions.*;
import io.restassured.response.Response;

public class ObjectMapperUtil {

	
	private static ObjectMapper objmapper = new ObjectMapper();
	
	//User Mapper needs a .class file, t make it generic we ask user to pass it as a method parameter
	public static <T>T deserialize(Response response,Class <T> targetClass) {
		try {
			return objmapper.readValue(response.getBody().asString(),targetClass);
		}
		catch(Exception exp) {
			throw new RuntimeException("deserilaization is failed for class: "+targetClass.getName());
		}
	}
	
	
}
