package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice2 {

	@Test(dataProvider = "getData")
	public void dataprovider(String name, int rollNo ) {
		System.out.println(name+"-------"+ rollNo);
	}
	
	@DataProvider
	public Object[][] getData(){
		
		Object[][] obj = new Object[6][2];
		
		obj[0][0]="Bharatesh";
		obj[0][1]=1;
		
		obj[1][0]="Bharath";
		obj[1][1]=2;
		
		obj[2][0]="Bharthu";
		obj[2][1]=3;
		
		obj[3][0]="Bhanvi";
		obj[3][1]=4;
		
		obj[4][0]="Bh";
		obj[4][1]=5;
		
		obj[5][0]="Bh2";
		obj[5][1]=6;
	
		return obj;
	}
}
