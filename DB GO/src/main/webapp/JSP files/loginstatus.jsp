<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>GO DB</title>
  <link href="CSS files/loginstatus.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>

<body>
  <header class="top-header">
    <div class="top-heading x-axis-flex">
      <div class="top-heading-icon"></div>
      <h1 class="top-heading-text">GO DB</h1>
    </div>
  </header>

  <section class="full-login-wrapper y-axis-flex">
    <h1>Login Status</h1>
    <div class="login-status-box">
    <%
    	String status = (String) request.getAttribute("loginStatus");
    	if(status.equals("success")){
    		out.println("<h1 class='success-login'>Login Successful</h1>");
    	}
    	else{
    		out.println("<h1 class='failure-login'>Login Unsuccessful</h1>");
    		out.println("<h2 style='text-align:center;'>Check whether the given details are correct</h2>");
    	}
    %>  
    </div>
    <%
    	if(status.equals("success")){
    		out.println("<a href='mainapplication.jsp'><button class='ok-button'>Ok</button></a>");
    	}
    	else{
    		out.println("<a href='userDB.html'><button class='ok-button'>Ok</button></a>");
    	}
    %>
    
  </section>
  <footer class="bottom-end-section">
    <h1>Contact Me</h1>
    <ul class="social-media x-axis-flex">
      <li><a href="https://www.instagram.com/" target="_blank"><i class="bi bi-instagram"></i></a></li>
      <li><a href="https://www.facebook.com/" target="_blank"><i class="bi bi-facebook"></i></a></li>
      <li><a href="https://twitter.com/" target="_blank"><i class="bi bi-twitter"></i></a></li>
    </ul>
  </footer>
</body>
</html>
