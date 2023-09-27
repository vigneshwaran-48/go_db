package com.dboperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Scanner;

public class ConnectToDB {

	public Connection getConnectionObject(String ip, int port, String dbName, String userName, String password) throws Exception{

		if(dbName.equals("mysql")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/", userName, password);
			return c;
		}
		else {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mariadb://" + ip + ":" + port + "/", userName, password);
			return c;
		}
	}
	
	public Connection getInstantConnection(String dbDetails) {
		
		Connection c = null;
		try {
			
			String[] userDetails = dbDetails.split(", ");
			
			ConnectToDB cdb = new ConnectToDB();
			c = cdb.getConnectionObject(userDetails[0], Integer.parseInt(userDetails[1]), userDetails[4], userDetails[2], userDetails[3]);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
}
