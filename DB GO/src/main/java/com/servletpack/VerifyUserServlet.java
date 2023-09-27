package com.servletpack;

import java.io.IOException; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.otheroperations.CookieOperations;
import com.otheroperations.FindCookiePosition;
import com.otheroperations.VerifyUserClass;

/**
 * Servlet implementation class VerifyUserServlet
 */
public class VerifyUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		CookieOperations co = new CookieOperations();
		
		VerifyUserClass vuc = new VerifyUserClass();
		RequestDispatcher rd = request.getRequestDispatcher("JSP files/signinstatus.jsp");
		
		if(vuc.isLegalUser(userName, userPassword)) {
			
			request.setAttribute("signinStatus", "success");
			
			Cookie c1 = new Cookie("userName", co.encodeCookie(userName));
			c1.setMaxAge(86400);
			response.addCookie(c1);
			
			Cookie c2 = new Cookie("userPassword", co.encodeCookie(userPassword));
			c2.setMaxAge(86400);
			response.addCookie(c2);
		}
		else {
			request.setAttribute("signinStatus", "failure");
		}
		rd.forward(request, response);
	}

}
