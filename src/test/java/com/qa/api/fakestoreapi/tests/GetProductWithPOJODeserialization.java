package com.qa.api.fakestoreapi.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.ObjectMapperUtil;

import io.restassured.response.*;

import io.restassured.http.ContentType;

public class GetProductWithPOJODeserialization extends BaseTest {
	
	
	@Test
	public void getAllProductsTest() {
		Response response=restClient.get(BASE_URL_FAKE_PRODUCT, FAKE_PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		
		//Use array.class as the Product array is an array.
		Product[] AllProducts=ObjectMapperUtil.deserialize(response, Product[].class);
		
		for(Product product:AllProducts) {
			System.out.println("ID: "+product.getId());
			System.out.println("Title: "+product.getPrice());
			System.out.println("Price: "+product.getDescription());
			//Problem with above approach is that the respnse is very complex and hence a lot of assertions would be required here.
			//Hence for complex JSON the Jayway JSON needs to be used.
		}
		
		
		
	}
	

}
