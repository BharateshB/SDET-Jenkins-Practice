package com.crm.PRACTICE;

import java.net.URL;

import org.testng.annotations.Test;

public class ReadDataFromCmdLine {
@Test
public void readDataFromCmdLine() {
	String Browser = System.getProperty("browser");
	String URL = System.getProperty("url");
	String USERNAME = System.getProperty("username");
	String PASSWORD = System.getProperty("password");
	
	System.out.println("browser name is ------>" + Browser);
	System.out.println("url is -------->"+URL);
	System.out.println("username is --------->"+USERNAME);
	System.out.println("password is ----------->"+PASSWORD);
	
}
}
