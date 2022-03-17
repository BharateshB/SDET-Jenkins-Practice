package com.crm.PRACTICE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
//import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

public class SampleJDBCExecuteUpdate {

@Test
public void sampleJDBCQueryUpdate() throws Throwable
{
	//register the database
	Driver driverref = new Driver();
	DriverManager.registerDriver(driverref);
	
	//get connection to database--provide database name
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
	
	//issue create statement
	Statement state = con.createStatement();
	
	//execute query--provide table name
	int res = state.executeUpdate("insert into student() values(5,'charan','male')");
	if(res==1) {
		System.out.println("database is updated");
	}
	else {
		System.out.println("database is not updated");
	}
	
	//close the connection
	con.close();
}
}
