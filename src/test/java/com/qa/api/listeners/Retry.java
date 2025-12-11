package com.qa.api.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	
	private int count=0;
	private static int maxTry=3;
	
	@Override
	public boolean retry(ITestResult itestresult) {
		if(!itestresult.isSuccess()) {
			if(count < maxTry) {
				count++;
				itestresult.setStatus(ITestResult.FAILURE);
				return true; // Test testNG to rerun the test
			}
			else {
				//mark as failed if maxTry is reached
				itestresult.setStatus(ITestResult.FAILURE);
			}
		}
		else{
			itestresult.setStatus(ITestResult.SUCCESS); //If test passed 
		}
		return false;
	}
	

}
