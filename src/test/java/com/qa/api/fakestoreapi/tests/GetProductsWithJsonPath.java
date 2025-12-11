package com.qa.api.fakestoreapi.tests;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.JsonPathValidatorUtil;
import com.qa.api.utils.ObjectMapperUtil;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetProductsWithJsonPath extends BaseTest{

	
	@Test
	public void getAllProductsTest() {
		Response response=restClient.get(BASE_URL_FAKE_PRODUCT, FAKE_PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.ANY);
		
		List<Number> pricesList= JsonPathValidatorUtil.readList(response, "$[?(@.price > 50)].price");
		System.out.println("Prices List: "+pricesList);
		

		List<Number> idsList= JsonPathValidatorUtil.readList(response, "$[?(@.price > 50)].id");
		System.out.println("ID List: "+idsList);
		
		List<Number> rateList= JsonPathValidatorUtil.readList(response, "$[?(@.price > 50)].rating.rate");
		System.out.println("Rates: "+rateList);
		
		List<Number> countList= JsonPathValidatorUtil.readList(response, "$..count");
		System.out.println("Count: "+countList);
		
		//Json Path for going to each element and obtaining its id and title: $[*].['id','title']
		List<Map<String,Object>> idTitleList = JsonPathValidatorUtil.readListOfMap(response, "$[*].['id','title']");
		System.out.println("ID Title List: "+idTitleList);
		
		List<Map<String,Object>> idTitleCategoryList = JsonPathValidatorUtil.readListOfMap(response, "$[*].['id','title','category']");
		System.out.println("ID Title Category List: "+idTitleCategoryList);
		
		List<Map<String,Object>> jeweleryIdTitle = JsonPathValidatorUtil.readList(response, "$[?(@.category == \"women's clothing\")].['id','title']");
		System.out.println("Jewelery ID title:"+jeweleryIdTitle);
		
		//go to each element using start and get the price which is minimum $[*].price
		Double minPrice=JsonPathValidatorUtil.read(response, "min($[*].price)");
		System.out.println("Min Price: "+minPrice);
		
		
		//go to each element using start and get the price which is maximum $[*].price
		Double maxPrice=JsonPathValidatorUtil.read(response, "max($[*].price)");
		System.out.println("Max Price: "+maxPrice);
		
	}
	

}
