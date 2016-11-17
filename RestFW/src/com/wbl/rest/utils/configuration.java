package com.wbl.rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class configuration {
	
	public static String URI;
	
	
	public  static void loadproperties(){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getProperty("user.dir")+"//resources//configuration.properties"));
			URI = prop.getProperty("uri");
			
			
		} 
		 catch (IOException e) {
		
			e.printStackTrace();
		}
	
	}

}
