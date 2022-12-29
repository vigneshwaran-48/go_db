package com.servletpack;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dboperations.ConnectToDB;
import com.dboperations.FindCookiePosition;
import com.dboperations.RetrieveData;
import com.otheroperations.AddUserReview;

/**
 * Servlet implementation class ReviewServlet
 */
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RetrieveData rtd = new RetrieveData();
		ConnectToDB ctb = new ConnectToDB();
		Connection c = null;
		Cookie cooks[] = request.getCookies();
		FindCookiePosition fcp = new FindCookiePosition();
		int position = fcp.getCookiePosition("userName", cooks);
		
		String userReview = request.getParameter("userReview");
		
		if(userReview != null) {
			int userStars = Integer.parseInt(request.getParameter("stars"));
			AddUserReview auv = new AddUserReview();
			auv.addReview(cooks[position].getValue(), userReview, userStars);
		}
		try {
			c = ctb.getConnectionObject("localhost", "mysql", "vicky", "vi99g@NESH");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		String[][] reviews = rtd.getTableData(c, "GO_DB", "user_reviews");
		request.setAttribute("reviewsData", reviews);
		
		RequestDispatcher rd = request.getRequestDispatcher("review.jsp");
		rd.forward(request, response);
	}

}
