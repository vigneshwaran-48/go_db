<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>GO DB</title>
  <link href="CSS files/searchTableStyle.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>

<body>
  <header class="top-header">
    <div class="top-heading x-axis-flex">
      <div class="top-heading-icon"></div>
      <h1 class="top-heading-text">GO DB</h1>
    </div>
  </header>

  <form action="displaycontent" method="get" class="main-content x-axis-flex">
    <div class="databases-and-tables-list x-axis-flex">
      <div class="databases y-axis-flex">
        <div class="heading">
          <h1>Databases</h1>
        </div>
        <%
        	ArrayList<String> dataBasesList = (ArrayList<String>) request.getAttribute("userDBList");
        	if(dataBasesList != null){
        		for(String s : dataBasesList){
        			out.println("<button name='UserClickedDB' value='" + s + "'  class='content-buttons'>" + s + "</button>");
        		}
        	}
        	else{
        		out.println("<h2>No Databases available</h2>");
        	}
        %>
        </div>
      
      <div class="tables y-axis-flex">
        <div class="heading">
          <h1>Tables</h1>
        </div>
        <%
        	ArrayList<String> tablesList = (ArrayList<String>) request.getAttribute("userTableList");
        	if(tablesList != null){
        		for(String s : tablesList){
        			out.println("<button name='UserClickedTable' value='" + s + "'  class='content-buttons'>" + s + "</button>");
        		}
        	}
        	else{
        		out.println("<h2>No Tables available</h2>");
        	}
        %>
      </div>
    </div>
    <div class="table-content">
    	<%
    		String[][] tableData = (String[][]) request.getAttribute("userTableData");
    		if(tableData != null){
    			out.println("<table style='border:1px solid black;padding:5px;font-size:20px;'>");
        		for(int i = 0;i < tableData.length;i++){
        			out.println("<tr style='border:1px solid black;padding:5px;font-size:20px;'>");
        			for(int j = 0;j < tableData[i].length;j++){
        				if(i == 0){
        					out.println("<th style='border:1px solid black;padding:5px;font-size:20px;background-color:#282A3A;color:white;'>" + tableData[i][j] + "</th>");
        				}
        				else{
        					out.println("<td style='border:1px solid black;padding:5px;font-size:20px;background-color:#282A3A;color:white;'>" + tableData[i][j] + "</td>");
        				}
        			}
        			out.println("</tr>");
        		}
        		out.println("</table>");
    		}
    	%>
    </div>
  </form>
  <a href="mainapplication.jsp"><button class="back-button">GO Back</button></a>
  <footer class="bottom-end-section">
    <h1>Contact Me</h1>
    <ul class="social-media x-axis-flex">
      <li><a href="https://www.instagram.com/" target="_blank"><i class="bi bi-instagram"></i></a></li>
      <li><a href="https://www.facebook.com/" target="_blank"><i class="bi bi-facebook"></i></a></li>
      <li><a href="https://twitter.com/" target="_blank"><i class="bi bi-twitter"></i></a></li>
    </ul>
  </footer>
</body>
<script src="script.js"></script>
</html>
