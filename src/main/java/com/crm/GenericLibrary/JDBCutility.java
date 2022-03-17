package com.crm.GenericLibrary;

import static org.testng.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.FormatFlagsConversionMismatchException;

import org.testng.annotations.IFactoryAnnotation;

import com.mysql.cj.jdbc.Driver;

 
/**
 * This class is used to read the data from database
 * @author Admin
 *
 */
public class JDBCutility {
	Boolean flag= false;
	Connection con= null;
	
	/**
	 * this method is used to register and get connected with database
	 * @throws Throwable
	 */
	public void databaseConnection() throws Throwable {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);	
		con = DriverManager.getConnection(IpathConstants.Databasepath,IpathConstants.userName,IpathConstants.password);
	}
	
	/**
	 * this method is used to close the connection
	 * @throws Throwable
	 */
	public void closeConnection() throws Throwable {
		con.close();
	}
	
	/**
	 * this method is used to read and verify the data 
	 * @param query
	 * @param columnIndex
	 * @param expdata
	 * @return
	 * @throws Throwable
	 */
	public String executequery(String query,int columnIndex,String expdata) throws Throwable {
		ResultSet result = con.createStatement().executeQuery(query);
		while(result.next()) {
			String data = result.getString(columnIndex);
			
			if (data.equalsIgnoreCase(expdata)) {
				flag=true;//rising flag
				break;
			}
		}
		if (flag) {
			System.out.println("data is verified");
			return expdata;
		}
		else {
			System.out.println("data is not verfied");
			return " ";
		}
	}
}