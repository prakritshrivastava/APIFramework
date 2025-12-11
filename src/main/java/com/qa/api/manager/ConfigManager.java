package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	//read properties from config.properties
	private static Properties prop=new Properties();
	
	//Static block gets initialzed as soon as configuration class is initialized, hence initialization will happen immediately
		
	static {
		
		//mvn clean install -Denv=qa/dev/prod (-D is used for passing command line arguments)
		//if no argument passed, then TC will execute on default env: qa env
		String envName=System.getProperty("env","qa");
		System.out.println("Running TC on env: "+envName);
		
		//Dynamically create filename
		String fileName = "config_"+envName+".properties";
		
		
		//The below uses reflection to getClass Loader and then reteurns the resource specified as a stream. 
		InputStream stream=ConfigManager.class.getClassLoader().getResourceAsStream(fileName);
		if(stream != null) {
			
			try {
				prop.load(stream);
				System.out.println("Config properties: "+prop);
			}
			catch(IOException ioExp) {
				System.out.println();
			}
			
		}
	}
	
	//To get any value
	public static String get(String key) {
		return prop.getProperty(key).trim();
	}
	
	//To set any value
	public static void set(String key,String value) {
		prop.setProperty(key, value);
	}
}
