package com.dboperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.Scanner;

public class ConnectToDB {
	
	private final static String PORT_DIRECTORIES = "/tmp/somespace";
	private final static int DEFAUL_PORT = 3306;
	
	// For normal GO_DB port action need to make this as false.
	private final static boolean IS_DIRECTORY_PORT_ENABLED = true;

	public Connection getConnectionObject(String ip, int port, String dbName, String userName, String password) throws Exception{
		
		if(IS_DIRECTORY_PORT_ENABLED) {
			port = getPort();
		}
		if(port == -1) {
			port = DEFAUL_PORT;
		}

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
						
			c = getConnectionObject(userDetails[0], Integer.parseInt(userDetails[1]), userDetails[4], userDetails[2], userDetails[3]);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return c;
	}
	
	private int getPort() {
		if(PORT_DIRECTORIES != null && !PORT_DIRECTORIES.isEmpty()) {
			File mainDir = new File(PORT_DIRECTORIES);
			if(!mainDir.isDirectory()) {
				return DEFAUL_PORT;
			}
			File[] files = new File(PORT_DIRECTORIES).listFiles();
			File lastModifiedDirectory = files[0];
			
			for(File file : files) {
				if(file.isDirectory() && lastModifiedDirectory.lastModified() < file.lastModified()) {
					lastModifiedDirectory = file;
				}
			}
			return Integer.parseInt(lastModifiedDirectory.getName());
		}
		return DEFAUL_PORT;
	}
}
