package com.qa.api.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer{
	
	public void transform(ITestAnnotation annotations,Class testClass,Constructor testConstructor,Method testMethod) {
		annotations.setRetryAnalyzer(Retry.class);	
	}

}
