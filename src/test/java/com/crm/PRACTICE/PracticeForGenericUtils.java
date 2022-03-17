package com.crm.PRACTICE;

import org.testng.annotations.Test;

import com.crm.GenericLibrary.JavaUtility;
import com.crm.GenericLibrary.PropertyFileUtility;

public class PracticeForGenericUtils {
@Test
public void practiceForGenericUtils() throws Throwable {
	JavaUtility jLib = new JavaUtility();
	int ran = jLib.getRandomNumber();
	String dat = jLib.getSystemDateInFormat();
	String date = jLib.getSystemDate();
	System.out.println(ran+date);
	System.out.println(dat);
	
	PropertyFileUtility pLib= new PropertyFileUtility();
	String BROWS = pLib.readDataFromPropertyFile("browser");
	System.out.println(BROWS);

	
}
}

