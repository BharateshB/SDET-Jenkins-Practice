package com.crm.PRACTICE;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

public class PropertyFilePractice {
	@Test
	public void propertyFilePractice() throws Throwable {
		//Step 1: read the file
				FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				
				//Step 2: Create Obj of Properties
				Properties pObj = new Properties();
				pObj.load(fis);
				
				//Step 3:read the data
				String USERNAME = pObj.getProperty("username");
				
				//Verification
				System.out.println(USERNAME);

	}

}
