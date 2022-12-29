package com.otheroperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VerifyUserClass {

	public boolean isLegalUser(String userName, String userPassword) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "vicky", "vi99g@NESH");
			Statement st = c.createStatement();
			st.execute("use GO_DB");
			
			ResultSet rs = st.executeQuery("select * from user_details");
			while(rs.next()) {
				if(rs.getString(2).equals(userName) && rs.getString(3).equals(userPassword)) {
					return true;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
