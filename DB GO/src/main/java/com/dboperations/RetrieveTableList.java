package com.dboperations;

import java.sql.*;
import java.util.ArrayList;

public class RetrieveTableList {

	public ArrayList<String> getTablesList(Connection c, String dbName){
		ArrayList<String> tablesList = new ArrayList<String>();
		
		try {
			Statement st = c.createStatement();
			st.executeUpdate("use " + dbName);
			ResultSet rs = st.executeQuery("show tables");
			
			while(rs.next()) {
				tablesList.add(rs.getString(1));
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return tablesList;
	}
}
