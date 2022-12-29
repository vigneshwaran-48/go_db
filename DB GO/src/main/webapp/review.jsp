<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width">
  <title>GO DB</title>
  <link href="CSS files/reviewstyle.css" rel="stylesheet" type="text/css" />
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css">
</head>

<body>
  <header class="top-header">
    <div class="top-heading x-axis-flex">
      <div class="top-heading-icon"></div>
      <h1 class="top-heading-text">GO DB</h1>
    </div>
  </header>

  <section class="middle-body x-axis-flex">
    <section class="common-main-section user-review-writing-section y-axis-flex">
      <h1 class="review-heading">Write a review</h1>
      <form action="review" method="post" class="review-body y-axis-flex">
        <div class="stars-div x-axis-flex">
          <h2>Stars</h2><h2>&#128519; &#128519;</h2>
          <select name="stars" class="star-option-tag">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>
        </div>
        <textarea name="userReview" class="textarea-tag"></textarea>
        <button type="button" class="style-button">Post</button>
      </form>
    </section>
    
    <section class="common-main-section full-review-section y-axis-flex">
      <h1 class="review-heading">Reviews</h1>
      <ul class="review-page">
      
      <%
      		String[][] userReviews = (String[][]) request.getAttribute("reviewsData");
      		
      		if(userReviews != null){
      			for(int i = userReviews.length-1;i > 0;i--){
      				out.println("<li class='user-review-box y-axis-flex'>"
      			          			+"<div class='user-reveiew-head-part x-axis-flex'>"
      	            					+"<div class='user-profile-image-name x-axis-flex'>"
      	             						+" <i class='bi bi-person-circle'></i>"
      	           							+" <p class='user-name'>" + userReviews[i][1] + "</p>"
      	         						+"</div>");
      				out.println("<div class='review-stars'>");
      				//Printing stars rating starts here
      				for(int j = 1;j <= 5;j++){
      					if(j <= Integer.parseInt(userReviews[i][2])){
      						out.println("<i class='bi bi-star-fill' style='color: yellow;'></i>");
      					}
      					else{
      						out.println("<i class='bi bi-star-fill'></i>");
      					}
      				}
      				//Printing start rating ends here 
      				out.println("</div>"
					      			+"</div>"
					      			+"<div class='user-review-wrapper'>"
					      				+"<p class='user-review-para'>" + userReviews[i][3] + "</p>"
					      			+"</div>"
					      		+"</li>");
      			}
      		}
      
      %>
       
      </ul>
    </section>
  </section>
  <a href="mainapplication.jsp"><button class="style-button go-button">GO back</button></a>
  <footer class="bottom-end-section">
    <h1>Contact Me</h1>
    <ul class="social-media x-axis-flex">
      <li><a href="https://www.instagram.com/" target="_blank"><i class="bi bi-instagram"></i></a></li>
      <li><a href="https://www.facebook.com/" target="_blank"><i class="bi bi-facebook"></i></a></li>
      <li><a href="https://twitter.com/" target="_blank"><i class="bi bi-twitter"></i></a></li>
    </ul>
  </footer>
</body>
<script src="JS files/reviewscript.js"></script>
</html>
