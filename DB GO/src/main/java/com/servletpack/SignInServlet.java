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
		
		CookieOperations co = new CookieOperations();
		String userName = request.getParameter("userName");
		Cookie[] cooks = request.getCookies();
		FindCookiePosition fcp = new FindCookiePosition();
		int position = fcp.getCookiePosition("userName", cooks);
		
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
				
				Cookie c = new Cookie("userName", co.encodeCookie(userName));
				c.setMaxAge(86400);
				response.addCookie(c);
				
				Cookie c1 = new Cookie("userPassword", co.encodeCookie(userPass));
				c1.setMaxAge(86400);
				response.addCookie(c1);
				
				SaveSignInDetails ssd = new SaveSignInDetails();
				ssd.saveSignInDetails(userName, userPass);
			}
			else {
				request.setAttribute("isUserNameAvailable", "failure");
			}
			rd.forward(request, response);
			
		}
		else {
			System.out.println("Going to signin page");
			RequestDispatcher rd = request.getRequestDispatcher("signinpage.html");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Came into doPost method");
		doGet(request, response);
		
	}

}
