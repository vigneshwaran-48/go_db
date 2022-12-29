package com.otheroperations;

import java.sql.*;

public class UserNameAvailable {

	public boolean isUserNameAvailable(String userName) {
	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "vicky", "vi99g@NESH");
			Statement st = c.createStatement();
			st.execute("use GO_DB");
			
			ResultSet rs = st.executeQuery("select * from user_details");
			
			while(rs.next()) {
				if(rs.getString(2).equals(userName)) {
					return false;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
