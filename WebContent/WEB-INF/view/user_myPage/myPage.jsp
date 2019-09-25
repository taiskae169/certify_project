<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${!empty sessionID}">
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>자격루:마이페이지</title>
  <!-- Bootstrap core CSS -->
  <!-- Custom fonts for this template -->
  <link href="/certify/resource/gen/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="/certify/resource/gen/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
  
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <!-- Custom styles for this template -->
  <link href="/certify/resource/gen/css/landing-page.min.css" rel="stylesheet">
  <script src="//code.jquery.com/jquery.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>

<body>
  <!-- Navigation -->
  <!-- Masthead -->
  <jsp:include page="/WEB-INF/view/Bar/Search.jsp"/>

<jsp:include page="/WEB-INF/view/Bar/NavBar.jsp"/>

  <!-- Icons Grid -->

  <!-- Image Showcases -->

  
  <!-- 본문  -->
  
  	<c:if test="${empty type}">
	    <jsp:include page="/WEB-INF/view/user_myPage/myPage_data.jsp">
			<jsp:param value="${uvo}" name="uvo"/>
			<jsp:param value="${edu_value}" name="edu_value"/>
	    	<jsp:param value="${certi_cate}" name="certi_cate"/>
	    	<jsp:param value="${allCerti}" name="allCerti"/>
	    	<jsp:param value="${eduList}" name="eduList"/>
	    	<jsp:param value="${careerList}" name="careerList"/>
	    	<jsp:param value="${certiList}" name="certiList"/>
	    	<jsp:param value="${quals}" name="quals"/>
	    </jsp:include>
    </c:if>
    <c:if test="${!empty type}">
    	<jsp:include page="/WEB-INF/view/user_myPage/myPage_modify.jsp">
    		<jsp:param value="${uvo}" name="uvo"/>
			<jsp:param value="${edu_value}" name="edu_value"/>
	    	<jsp:param value="${certi_cate}" name="certi_cate"/>
	    	<jsp:param value="${allCerti}" name="allCerti"/>
	    	<jsp:param value="${eduList}" name="eduList"/>
	    	<jsp:param value="${careerList}" name="careerList"/>
	    	<jsp:param value="${certiList}" name="certiList"/>
	    	<jsp:param value="${quals}" name="quals"/>
	    </jsp:include>
  	</c:if>
    
    
  <!-- 본문 끝 -->
  <!-- Testimonials -->

  <hr />
  <!-- Footer -->
  <footer class="footer bg-light">
    <div class="container">
      <div class="row">
        <div class="col-lg-6 h-100 text-center text-lg-left my-auto">
          <ul class="list-inline mb-2">
            <li class="list-inline-item">
              <a href="#">About</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="#">Contact</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="#">Terms of Use</a>
            </li>
            <li class="list-inline-item">&sdot;</li>
            <li class="list-inline-item">
              <a href="#">Privacy Policy</a>
            </li>
          </ul>
          <p class="text-muted small mb-4 mb-lg-0">&copy; Your Website 2019. All Rights Reserved.</p>
        </div>
        <div class="col-lg-6 h-100 text-center text-lg-right my-auto">
          <ul class="list-inline mb-0">
            <li class="list-inline-item mr-3">
              <a href="#">
                <i class="fab fa-facebook fa-2x fa-fw"></i>
              </a>
            </li>
            <li class="list-inline-item mr-3">
              <a href="#">
                <i class="fab fa-twitter-square fa-2x fa-fw"></i>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <i class="fab fa-instagram fa-2x fa-fw"></i>
              </a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="/certify/resource/gen/vendor/jquery/jquery.min.js"></script>
  <script src="/certify/resource/gen/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>
</c:if>
<c:if test="${empty sessionID}">
	<script>
		alert("로그인이 필요한 기능입니다.");
		window.location="/certify/main.certi";
	</script>
</c:if>
