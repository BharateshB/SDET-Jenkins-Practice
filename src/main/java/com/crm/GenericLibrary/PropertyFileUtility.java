package com.crm.GenericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * his class will read data from property file and return value to the user
 * @author Admin
 *
 */
public class PropertyFileUtility {

	public String readDataFromPropertyFile(String key) throws Throwable {
		FileInputStream fis= new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties pLib= new Properties();
		pLib.load(fis);
	   String value = pLib.getProperty(key);
		return value;
		
	}
}
