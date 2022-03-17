package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

//import org.testng.annotations.Test;

public class SampleJDBCExecuteQuery {
	@Test
public void sampleJDBCExecuteQuery() throws Throwable
{
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
		System.out.println(res.getString(1)+" "+res.getString(2)+" "+res.getString(3));
		
		}
	//close the connection
	con.close();	
}
}
