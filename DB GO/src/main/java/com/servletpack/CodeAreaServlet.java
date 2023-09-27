package com.servletpack;

import java.io.IOException; 
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dboperations.ConnectToDB;
import com.dboperations.RunCode;

/**
 * Servlet implementation class CodeAreaServlet
 */
public class CodeAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("userCode");
		HttpSession session = request.getSession();

		if(session.getAttribute("userDbDetails") != null) {
			
			boolean isValidStatement = true;
			Connection c = new ConnectToDB().getInstantConnection((String) session.getAttribute("userDbDetails"));
			
			if(code != null) {	
				
				RunCode rc = new RunCode();
				ArrayList tempArray = null;
				
				if(session.getAttribute("useCommand") != null) {
					tempArray = rc.runCode(code, c, (String) session.getAttribute("useCommand"));
				}
				else {
					tempArray = rc.runCode(code, c, "");
				}
				Object[] temp = tempArray.toArray();
				try {
					if(temp.length == 2) {
						
						ArrayList array = rc.convertResultSet((ResultSet) tempArray.get(0));
						ArrayList<ArrayList<String>> resultSetData = (ArrayList<ArrayList<String>>) array.get(0);
						
						request.setAttribute("codeResultArray", array.get(0));
						request.setAttribute("rowsInSet", (int) array.get(1));
						
					}
					else if(temp.length == 3) {
						request.setAttribute("error", (String) temp[0]);
						isValidStatement = false;
					}
					else if (temp.length == 1){
						request.setAttribute("rowsAffected", (int) temp[0]);
						
						if(code.toLowerCase().startsWith("use")) {
							session.setAttribute("useCommand", code);

						}
					}
					else {
						System.out.println("No way!...");
					}
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				if(isValidStatement) {
					//This code is for storing the use command in cookie only if its a correct statement
					if(code.toLowerCase().startsWith("use")) {
						session.setAttribute("useCommand", code);
					}
				}
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("JSP files/codearea.jsp");
			rd.forward(request, response);
		}
		else {
			request.setAttribute("goTo", "userDB.html");
			RequestDispatcher rd = request.getRequestDispatcher("JSP files/cookiestatus.jsp");
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
