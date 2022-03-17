package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;

public class SampleJDBCExecuteTryCatchFinally {
@Test
public void sampleJDBCExecuteTryCatchFinally() throws Throwable {
	
	Connection con=null;
	try {
		//register the database
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		
		//get connection to database--provide database name
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
		System.out.println("connection established");
		
		//issue create statement
		Statement state = con.createStatement();
		
		//execute query--provide table name
		int res = state.executeUpdate("insert into stu values(5,'charan','male')");
		if(res==1) {
			System.out.println("database is updated");
		}
		else {
			System.out.println("database is not updated");
		}
	}
		catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			con.close();
			System.out.println("connection closed");
		}
	}
	
}

