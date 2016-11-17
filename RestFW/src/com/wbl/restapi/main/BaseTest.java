package com.wbl.restapi.main;

import org.testng.annotations.BeforeSuite;

import com.wbl.rest.utils.configuration;

public class BaseTest {
	@BeforeSuite
	public void beforeSuite(){
		configuration.loadproperties();
	}

}
