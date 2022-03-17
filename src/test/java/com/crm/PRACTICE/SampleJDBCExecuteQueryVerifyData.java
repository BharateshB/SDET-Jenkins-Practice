package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteQueryVerifyData {
	@Test
	public void sampleJDBCExecuteQueryVerifyData() throws Throwable {
	String expdata = "bharatesh";
	//register the database
Driver driverRef=new Driver();
DriverManager.registerDriver(driverRef);

//get connection to database--provide database name
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");

//issue create statement
Statement state = con.createStatement();

//execute query--provide table name
ResultSet res = state.executeQuery("select * from student");

while(res.next()) {
	
	String actdata = res.getString(2);
	if(expdata.equalsIgnoreCase(actdata)) {
		System.out.println(actdata+" "+"data is verified");
		break;
	}
}
//close the connection
con.close();

}
}
