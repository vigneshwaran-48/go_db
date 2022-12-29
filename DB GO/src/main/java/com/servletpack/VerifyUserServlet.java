package com.servletpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		VerifyUserClass vuc = new VerifyUserClass();
		RequestDispatcher rd = request.getRequestDispatcher("JSP files/signinstatus.jsp");
		
		if(vuc.isLegalUser(userName, userPassword)) {
			request.setAttribute("signinStatus", "success");
			Cookie cook = new Cookie("userName", userName);
			cook.setMaxAge(86400);
			response.addCookie(cook);
			Cookie passCook = new Cookie("userPassword", userPassword);
			passCook.setMaxAge(86400);
			response.addCookie(passCook);
		}
		else {
			request.setAttribute("signinStatus", "failure");
		}
		rd.forward(request, response);
	}

}
