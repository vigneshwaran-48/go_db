<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>GO DB</title>
  <link href="CSS files/codearea.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>

<body>
  <header class="top-header">
    <div class="top-heading x-axis-flex">
      <div class="top-heading-icon"></div>
      <h1 class="top-heading-text">GO DB</h1>
    </div>
  </header>

  <section class="create-table-form-section x-axis-flex">
    <div class="main-section-inner-div code-templates y-axis-flex">
      <div class="sub-template-div">
        <h1>DDL</h1>
        <button type="button" class="template-button">CREATE</button>
        <button type="button" class="template-button">ALTER</button>
        <button type="button" class="template-button">DROP</button>
        <button type="button" class="template-button">TRUNCATE</button>
        <button type="button" class="template-button">RENAME</button>
      </div>

      <div class="sub-template-div">
        <h1>DML</h1>
        <button type="button" class="template-button">SELECT</button>
        <button type="button" class="template-button">INSERT</button>
        <button type="button" class="template-button">UPDATE</button>
        <button type="button" class="template-button">DELETE</button>
        <button type="button" class="template-button">MERGE</button>
      </div>

      <div class="sub-template-div">
        <h1>DCL</h1>
        <button type="button" class="template-button">GRANT</button>
        <button type="button" class="template-button">REVOKE</button>
      </div>

      <div class="sub-template-div">
        <h1>TCL</h1>
        <button type="button" class="template-button">COMMIT</button>
        <button type="button" class="template-button">ROLLBACK</button>
        <button type="button" class="template-button">SAVEPOINT</button>
      </div>
    </div>
    <form action="codearea" method="get" class="main-section-inner-div code-area">
      <textarea name="userCode"></textarea>
    </form>
    <div class="main-section-inner-div output-area">
    	<%
    		ArrayList<ArrayList<String>> array = (ArrayList<ArrayList<String>>) request.getAttribute("codeResultArray");
    		if(array != null){
    			out.println("<table style='border:1px solid white;padding:5px;font-size:20px;'>");
    			boolean flag = false;
    			for(ArrayList<String> a : array){
    				out.println("<tr style='border:1px solid white;padding:5px;font-size:20px;'>");
    				
					for(String s : a){
						if(!flag){
	    					out.println("<th style='border:1px solid white;padding:5px;font-size:20px;'>" + s + "</th>");
	    				}
						else{
							out.println("<td style='border:1px solid white;padding:5px;font-size:20px;'>" + s + "</td>");
						}
        			}
					flag = true;
        			out.println("</tr>");
        		}
    			out.println("</table>");
    			Integer rowsInSet = (Integer) request.getAttribute("rowsInSet");
    			if(rowsInSet != null){
    				out.println("<p class='command-prompt-style'>" + rowsInSet + " rows in set </p>");
    			}
    		}
    		else{
    			Integer rows = (Integer) request.getAttribute("rowsAffected");
    			if(rows != null){
    				out.println("<p class='command-prompt-style'>Query OK, "+ rows + " rows affected</p>");
    			}
    			else{
    				String e = (String) request.getAttribute("error");
    				if(e != null){
    					out.println("<p class='command-prompt-style'>" + e + "</p>");
    				}	
    			}
    		}
    	%>
    </div>
  </section>
  
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
<script src="JS files/codearea.js"></script>
</html>
