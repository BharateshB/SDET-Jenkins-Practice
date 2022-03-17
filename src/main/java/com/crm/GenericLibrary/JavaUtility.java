package com.crm.GenericLibrary;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Date;
import java.util.Random;

import org.apache.http.auth.NTCredentials;

/**
 * This class consists of generic methods wrt Java
 * @author Admin
 *
 */
public class JavaUtility {

	/**
	 * This methods will generate a random number and return it to the user
	 * @return
	 */
	public int getRandomNumber() {
		Random rand = new Random();
		int random = rand.nextInt(500);
		return random;
	}
	public String getSystemDate() {
		Date d=new Date();
		String date = d.toString();
		return date;
	}
	
	
	/**
	 * this method will return the date in the format day-mon-year
	 * @return
	 */
	public String getSystemDateInFormat() {
		Date d= new Date();

		String d1 = d.toString();
		 String[] date = d1.split(" ");
		 String mon = date[1];
		 String day = date[2];
		 String time = date[3].replace(":", "-");
		 String year = date[5];
		 String dateformat = day+"-"+mon+"-"+year+"-"+time;
		 return dateformat;
		
	}
	
	public String getLocalDateInFormat() {
		LocalDateTime lDateTime= LocalDateTime.now();
		String smonth=lDateTime.getMonth().name();
		int idate=lDateTime.getDayOfMonth();
		int iyear=lDateTime.getYear();
		Integer Iyear= iyear;
		Integer Idate=idate;
		String year=Iyear.toString();
		String date= Idate.toString();
		String month= smonth.substring(0, 1).toUpperCase()+smonth.substring(1).toLowerCase();
		String dateformat=date+":"+month+":"+year;	;
		return dateformat;	
	}
	
	
}
