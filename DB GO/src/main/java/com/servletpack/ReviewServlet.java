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
import javax.servlet.http.HttpSession;

import com.dboperations.ConnectToDB;
import com.dboperations.RetrieveData;
import com.otheroperations.AddUserReview;
import com.otheroperations.CookieOperations;
import com.otheroperations.FindCookiePosition;

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
		Cookie[] cooks = request.getCookies();
		CookieOperations co = new CookieOperations();
		FindCookiePosition fcp = new FindCookiePosition();
		
		int position = fcp.getCookiePosition("userName", cooks);
		Connection c = null;
		
		String userReview = request.getParameter("userReview");
		
		if(position != 20) {
			if(userReview != null) {
				int userStars = Integer.parseInt(request.getParameter("stars"));
				AddUserReview auv = new AddUserReview();
				auv.addReview(co.decodeCookie(cooks[position].getValue()), userReview, userStars);
			}
			
			try {
				c = ctb.getConnectionObject("localhost", 3306, "mysql", "vicky", "vi99g@NESH");
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			String[][] reviews = rtd.getTableData(c, "GO_DB", "user_reviews");
			request.setAttribute("reviewsData", reviews);
			
			RequestDispatcher rd = request.getRequestDispatcher("review.jsp");
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("JSP files/cookiestatus.jsp");
			request.setAttribute("goTo", "signinpage.html");
			rd.forward(request, response);
		}
		
	}

}
