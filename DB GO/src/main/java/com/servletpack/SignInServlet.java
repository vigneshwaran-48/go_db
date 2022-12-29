package com.servletpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dboperations.FindCookiePosition;
import com.otheroperations.SaveSignInDetails;
import com.otheroperations.UserNameAvailable;

/**
 * Servlet implementation class SignInServlet
 */
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FindCookiePosition fcp = new FindCookiePosition();
		Cookie[] cooks = request.getCookies();
		int position = fcp.getCookiePosition("userName", cooks);
		String userName = request.getParameter("userName");
		
		if(position != 20) {
			RequestDispatcher rd = request.getRequestDispatcher("review");
			rd.forward(request, response);
		}
		else if(userName != null) {
			
			UserNameAvailable una = new UserNameAvailable();
			RequestDispatcher rd = request.getRequestDispatcher("JSP files/signupstatus.jsp");
			
			if(una.isUserNameAvailable(userName)) {
				request.setAttribute("isUserNameAvailable", "success");
				
				String userPass = request.getParameter("userPassword");
				
				Cookie cook = new Cookie("userName", userName);
				cook.setMaxAge(86400);
				response.addCookie(cook);
				
				Cookie passCook = new Cookie("userPassword", userPass);
				passCook.setMaxAge(86400);
				response.addCookie(passCook);
				
				SaveSignInDetails ssd = new SaveSignInDetails();
				ssd.saveSignInDetails(userName, userPass);
			}
			else {
				request.setAttribute("isUserNameAvailable", "failure");
			}
			rd.forward(request, response);
			
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("signinpage.html");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
