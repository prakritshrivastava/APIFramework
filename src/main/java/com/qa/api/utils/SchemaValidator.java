package com.qa.api.utils;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class SchemaValidator {
	
	
	public static boolean validateSchema(Response response,String schemaFileName) {
		
		try {
			response
				.then()
					.assertThat()
					.body(matchesJsonSchemaInClasspath(schemaFileName));
		
			System.out.println("Schema Validation is passed for : "+schemaFileName);
			return true;
		}
		catch(Exception exp) {
			System.out.println("Schema validation is failed: "+exp.getMessage());
			return false;
		}
		
	}
	

}
