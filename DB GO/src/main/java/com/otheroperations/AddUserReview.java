package com.otheroperations;

import java.sql.*;

public class AddUserReview {

	public void addReview(String userName,String userReview, int userStarCount) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "vicky", "vi99g@NESH");
			Statement st = c.createStatement();
			
			st.execute("use GO_DB");
			st.executeUpdate("INSERT INTO user_reviews (user_name, user_stars, user_review) values ('"
					+ userName + "', " + userStarCount + ", '" + userReview + "')");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
