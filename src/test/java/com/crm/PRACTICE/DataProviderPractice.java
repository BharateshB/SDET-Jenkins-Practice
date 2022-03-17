package com.crm.PRACTICE;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {

	
	@Test(dataProvider = "getData")
	public void sampleDataProvider(String Name, String Model, int Qty  ) {
		System.out.println(Name+"-----"+Model+"---------"+Qty);
	}
	
	@DataProvider
	public Object[][] getData() {
		
		Object[][] obj = new Object[4][3];
		
		obj[0][0]="Lava";
		obj[0][1]="A7";
		obj[0][2]= 15000;
		
		obj[1][0]="infinix";
		obj[1][1]="hot 10";
		obj[1][2]= 11000;
		
		obj[2][0]="Samsung";
		obj[2][1]="2018";
		obj[2][2]= 18000;
		
		obj[3][0]="Nokia";
		obj[3][1]="Lumia";
		obj[3][2]= 15000;
		
		

		
		return obj;
	}
}
