package com.crm.PRACTICE;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;

import org.apache.poi.ss.formula.functions.Now;
import org.testng.annotations.Test;

import com.mysql.cj.result.LocalDateTimeValueFactory;

public class DateForCalendarTest {

	@Test
	
	public void dateCalendar() {
		/*
		Date d = new Date();
		System.out.println(d);
		String d1 = d.toString();
		String[] date = d1.split(" ");
		String month= date[1];
		String day = date[2];
		String year = date[5];
		System.out.println(day+"-"+month+"-"+year);
		*/
		
		LocalDateTime lDate=  LocalDateTime.now();
		String calMonth = lDate.getMonth().name();
		int idate = lDate.getDayOfMonth();
		System.out.println(calMonth);
		int iYear= lDate.getYear();
		Integer IDate=idate;
		String date=IDate.toString();
		Integer IYear=iYear;
		String year= IYear.toString();
		String month=calMonth.substring(0, 1).toUpperCase()+calMonth.substring(1).toLowerCase();
		System.out.println(date+"-"+month+"-"+year);
		
	}
}
