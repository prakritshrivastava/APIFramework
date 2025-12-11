package com.qa.api.exceptions;

public class FrameworkExceptions extends RuntimeException{

	//Constructor to accept a message which would be displayed when Runtime Exception is thrown (Custom Exception) 
	public FrameworkExceptions(String message) {
		super(message);
	}
	
}
