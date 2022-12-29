package com.otheroperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SaveSignInDetails {

	public void saveSignInDetails(String userName, String userPassword) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "vicky", "vi99g@NESH");
			Statement st = c.createStatement();
			st.execute("use GO_DB");
			
			st.executeUpdate("INSERT INTO user_details (user_name, user_password) values ('" + userName + "', '" + userPassword + "')");
			System.out.println("Inserted succefully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
