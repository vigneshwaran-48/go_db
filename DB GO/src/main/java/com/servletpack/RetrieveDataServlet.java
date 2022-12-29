package com.servletpack;

import java.io.*;
import java.io.File;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dboperations.ConnectToDB;
import com.dboperations.FindCookiePosition;
import com.dboperations.RetrieveDBList;
import com.dboperations.RetrieveData;
import com.dboperations.RetrieveTableList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Servlet implementation class RetrieveDataServlet
 */
public class RetrieveDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = new ConnectToDB().getInstantConnection();
		RetrieveDBList rdb = new RetrieveDBList();
		ArrayList<String> dbList = rdb.getDBList(c);
		
		String clickedDBName = request.getParameter("UserClickedDB");
		String clickedTableName = request.getParameter("UserClickedTable");
		
		if(clickedTableName != null) {
			
			RetrieveData rtd = new RetrieveData();
			String temp[][] = null;
			
			Cookie[] tempCookie = request.getCookies();
			FindCookiePosition fcp = new FindCookiePosition();
			int resultStatus = fcp.getCookiePosition("dbName", tempCookie);
			
			if(resultStatus != 20) {
				temp = rtd.getTableData(c, tempCookie[resultStatus].getValue(), clickedTableName);
				clickedDBName = tempCookie[resultStatus].getValue();
			}
			
			request.setAttribute("userTableData", temp);
			
		}
		
		if(clickedDBName != null) {
			RetrieveTableList rtl = new RetrieveTableList();
			ArrayList<String> tablesList = rtl.getTablesList(c, clickedDBName);
			request.setAttribute("userTableList", tablesList);
			Cookie cook = new Cookie("dbName", clickedDBName);
			response.addCookie(cook);
		}
		
		
		request.setAttribute("userDBList", dbList);
		RequestDispatcher rd = request.getRequestDispatcher("JSP files/searchTable.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
