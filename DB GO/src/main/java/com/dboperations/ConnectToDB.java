package com.dboperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class ConnectToDB {

	public Connection getConnectionObject(String ip, String dbName, String userName, String password) throws Exception{

		if(dbName.equals("mysql")) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/", userName, password);
			return c;
		}
		else {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mariadb://" + ip + ":3306/", userName, password);
			return c;
		}
		
	}
	
	public Connection getInstantConnection() {
		File f = new File("/home/vigneshwaran/AdvanceJava/DB GO/src/main/webapp/userdb.txt");
		Scanner sc;
		Connection c = null;
		try {
			sc = new Scanner(f);
			String[] userDetails = sc.nextLine().split(", ");
			
			ConnectToDB cdb = new ConnectToDB();
		
			c = cdb.getConnectionObject(userDetails[0], userDetails[3], userDetails[1], userDetails[2]);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
}
