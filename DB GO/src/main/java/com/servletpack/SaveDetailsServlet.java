package com.servletpack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dboperations.ValidateDBDetails;

/**
 * Servlet implementation class SaveDetailsServlet
 */
public class SaveDetailsServlet extends HttpServlet {
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
		
		ValidateDBDetails vdb = new ValidateDBDetails();
		RequestDispatcher rd = request.getRequestDispatcher("JSP files/loginstatus.jsp");
		HttpSession session = request.getSession();
		
		if(vdb.isValideDBDetails(request.getParameter("ip"), Integer.parseInt(request.getParameter("port")), request.getParameter("Uname"), request.getParameter("Upass"), request.getParameter("dbtype"))) {
			request.setAttribute("loginStatus", "success");
			String temp = request.getParameter("ip") + ", " + request.getParameter("port") + ", " +  request.getParameter("Uname") + ", " + request.getParameter("Upass")+ ", " +request.getParameter("dbtype");
			
			session.setAttribute("userDbDetails", temp);
		
			rd.forward(request, response);
			
		}
		else {
			request.setAttribute("loginStatus", "fail");
			rd.include(request, response);
		}
	}
}
