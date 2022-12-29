package com.servletpack;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dboperations.ConnectToDB;
import com.dboperations.FindCookiePosition;
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
		Connection c = new ConnectToDB().getInstantConnection();
		Cookie[] tempCookie = request.getCookies();
		
		if(code != null) {
			if(code.startsWith("use")) {
				String tempCode = code.replace(" ", "34");
				tempCode = tempCode.replace(";", "7");
				Cookie cook = new Cookie("useCommand", tempCode);
				response.addCookie(cook);
			}
			RunCode rc = new RunCode();
			ArrayList tempArray = null;
			FindCookiePosition fcp = new FindCookiePosition();
			int resultStatus = fcp.getCookiePosition("useCommand", tempCookie);
			
			if(resultStatus != 20) {
				String tempCode = tempCookie[resultStatus].getValue().replace("34", " ");
				tempCode = tempCode.replace("7", ";");
				tempArray = rc.runCode(code, c, tempCode);
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
				}
				else if (temp.length == 1){
					request.setAttribute("rowsAffected", (int) temp[0]);
				}
				else {
					System.out.println("No way!...");
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("JSP files/codearea.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
