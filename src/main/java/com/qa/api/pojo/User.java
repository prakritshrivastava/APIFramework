package com.qa.api.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


	@Data   //Equivalent to getters and setters
	@AllArgsConstructor //Equivalent to constructor with all arguments
	@NoArgsConstructor //Equivalent to constructor with no arguments
	@Builder //Builder will take care of building the project
	@JsonInclude(Include.NON_NULL) //to allow fields like id which cannot be filled in request but will appear in response
	public class User {
		
		private Number id;
		private String name;
		private String email;
		private String gender;
		private String status;
		
	}
	

