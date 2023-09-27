package com.dboperations;

import java.sql.Connection;

public class ValidateDBDetails {

	public boolean isValideDBDetails(String ip, int port, String userName, String userPassword, String dbName) {
		
		boolean dbStatus = false;
		
		ConnectToDB cdb = new ConnectToDB();
		try {
			Connection c = cdb.getConnectionObject(ip, port, dbName, userName, userPassword);
			if(c != null) {
				dbStatus = true;
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dbStatus;
	}
}
