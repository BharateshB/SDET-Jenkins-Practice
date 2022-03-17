package com.crm.PRACTICE;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PracticeTestNG {

	@Test(invocationCount = 2)
	public void createOrg() {
		Assert.fail();
		System.out.println("Org is created");
		
	}
	
	@Test(priority = 1)
	public void modifyOrg() {
		System.out.println("Org is modified");
	}
	
	@Test(priority = 2)
	public void deleteOrg() {
		System.out.println("org is deleted");
	}
	
}
