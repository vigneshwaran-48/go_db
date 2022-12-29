package com.servletpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		File f = new File("/home/vigneshwaran/AdvanceJava/DB GO/src/main/webapp/userdb.txt");
		PrintWriter pw = null;
		ValidateDBDetails vdb = new ValidateDBDetails();
		RequestDispatcher rd = request.getRequestDispatcher("JSP files/loginstatus.jsp");
		
		if(vdb.isValideDBDetails(request.getParameter("ip"), request.getParameter("Uname"), request.getParameter("Upass"), request.getParameter("dbtype"))) {
			request.setAttribute("loginStatus", "success");
			try {
				pw = new PrintWriter(f);
				pw.println(request.getParameter("ip") + ", " + request.getParameter("Uname") + ", " + request.getParameter("Upass") + ", " + request.getParameter("dbtype"));
				pw.flush();
				rd.forward(request, response);
			} 
			catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				if(pw != null) {
					pw.close();
				}
			}
		}
		else {
			request.setAttribute("loginStatus", "fail");
		}
	}
}
