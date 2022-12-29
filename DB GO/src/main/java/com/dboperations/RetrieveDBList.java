package com.dboperations;

import java.sql.*;
import java.util.ArrayList;

public class RetrieveDBList {

	public ArrayList<String> getDBList(Connection c) {
		
		ArrayList<String> dbList = new ArrayList<String>();
		
		try {
			Statement st = c.createStatement();
			
			ResultSet rs = st.executeQuery("show databases;");
		
			while(rs.next()) {
				dbList.add(rs.getString(1));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
				
		return dbList;
	}
}
