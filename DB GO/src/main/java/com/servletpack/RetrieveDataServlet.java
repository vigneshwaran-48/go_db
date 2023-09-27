package com.servletpack;

import java.io.*; 
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dboperations.ConnectToDB;
import com.dboperations.RetrieveDBList;
import com.dboperations.RetrieveData;
import com.dboperations.RetrieveTableList;

import java.util.ArrayList;

/**
 * Servlet implementation class RetrieveDataServlet
 */
public class RetrieveDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
	
		if(session.getAttribute("userDbDetails") != null) {
			
			Connection c = new ConnectToDB().getInstantConnection((String) session.getAttribute("userDbDetails"));
			RetrieveDBList rdb = new RetrieveDBList();
			ArrayList<String> dbList = rdb.getDBList(c);
			
			String clickedDBName = request.getParameter("UserClickedDB");
			String clickedTableName = request.getParameter("UserClickedTable");
			
			if(clickedTableName != null) {
				
				RetrieveData rtd = new RetrieveData();
				String temp[][] = null;
				
				if(session.getAttribute("dbName") != null) {
					
					temp = rtd.getTableData(c, (String) session.getAttribute("dbName"), clickedTableName);
					clickedDBName = (String) session.getAttribute("dbName");
				}

				request.setAttribute("userTableData", temp);
				
			}
			
			if(clickedDBName != null) {
				RetrieveTableList rtl = new RetrieveTableList();
				ArrayList<String> tablesList = rtl.getTablesList(c, clickedDBName);
				request.setAttribute("userTableList", tablesList);
				session.setAttribute("dbName", clickedDBName);

			}
			
			
			request.setAttribute("userDBList", dbList);
			RequestDispatcher rd = request.getRequestDispatcher("JSP files/searchTable.jsp");
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
