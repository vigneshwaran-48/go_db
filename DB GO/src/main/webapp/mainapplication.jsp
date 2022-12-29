<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>GO DB</title>
  <link href="CSS files/mainappcss.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>

<body>
  <header class="top-header">
    <div class="top-heading x-axis-flex">
      <div class="top-heading-icon"></div>
      <h1 class="top-heading-text">GO DB</h1>
    </div>
  </header>
	<%
		Cookie[] cooks = request.getCookies();
		Cookie temp = null;
		
		if(cooks != null){
			for(int i = 0;i < cooks.length;i++) {
				if(cooks[i].getName().equals("useCommand") || cooks[i].getName().equals("dbName")) {
					temp = cooks[i];
					temp.setMaxAge(0);
					response.addCookie(temp);
				}
		 	}
		}
	
	%>
  <section class="buttons-wrapper x-axis-flex">
    <a href="codearea"><button class="button-tag">Create something</button></a>
    <a href="displaycontent"><button class="button-tag">See something</button></a>
    <a href="signin"><button class="button-tag">Reviews</button></a>
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
<script src="script.js"></script>
</html>