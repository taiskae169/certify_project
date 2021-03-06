<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>자격루</title>

  <!-- Bootstrap core CSS -->
  <link href="/certify/resource/gen/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/certify/resource/gen/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="/certify/resource/gen/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

  <!-- Custom styles for this template -->
  <link href="/certify/resource/gen/css/landing-page.min.css" rel="stylesheet">

</head>

<body>

  <!-- Navigation -->


  <!-- Masthead -->
  <header>
    <div class="overlay" style="height:100px" style="width:80%; background:white;margin:auto;">
    	<img src="/certify/resource/image/main/test.jpg" style="width:50px;height:50px;top:30px;left:130px;position:relative; float:left"/>
    	<div class="col-md-10 col-lg-8 col-xl-7 mx-auto" style="top:30px; left:130px">
          <form>
            <div class="form-row">
              <div class="col-12 col-md-9 mb-2 mb-md-0">
                <input type="text" class="form-control form-control-lg">
              </div>
              <div class="col-12 col-md-3">
                <button type="submit" class="btn btn-block btn-lg btn-primary">검색</button>
              </div>
            </div>
          </form>
        </div>
        
    </div>
    

  </header>

  <!-- Icons Grid -->
  <hr style="width:1450px;"/>

  <nav class="navbar navbar-light bg-light static-top" style="width:1450px;;margin:auto">
    <div class="container">
      <a class="navbar-brand" href="#">자격루</a>
      <a class="btn btn-primary" href="#">Login</a>
    </div>
  </nav>

  <!-- Image Showcases -->
  <section class="showcase" style="width:1450px;;margin:auto;">
    
  </section >
  <div style="width:1450px;height:606px;margin:auto;display:block;">
	<div style="width:59%;height:600px;border-style:solid;float:left;">
    	<div style="height:380px;border-style:solid;margin:3px;">
    		신청가능 시험 목록 출력창
    	</div>
    	<div style="height:200px;border-style:solid;margin:3px;">
    		공지사항 <br />
    		
    	</div>
    </div>
    <div style="width:580px;height:600px;border-style:solid;float:right;">
    	<div style="height:172.25px;margin:3px;">
    		<jsp:include page="/WEB-INF/view/main/loginbox.jsp">
    			<jsp:param value="${naverURI}" name="naverURI"/>
    		</jsp:include>
    	</div>
    	<div style="height:119px;margin:3px;border-style:solid;">
    		응시자격링크
    	</div>
    	<div style="height:291px;margin:3px;border-style:solid;">
    		게시판
    	</div>
    </div>
   </div>
  <!-- Testimonials -->
  <div style="width:1450px;;height:100px;margin:auto;border-style:solid;display:block;">
 		<ul style="list-style:none;margin:0;padding:0;">
 			<li style="margin:0;padding:0;border=0;float:left;"><img src="/certify/resource/image/main/qnet.jpg" style="height:70px;weghit:300px;"></li>
 			<li style="margin:0;padding:0;border=0;float:left;"><img src="/certify/resource/image/main/qnet.jpg" style="height:70px;weghit:300px;"></li>
 			<li style="margin:0;padding:0;border=0;float:left;"><img src="/certify/resource/image/main/qnet.jpg" style="height:70px;weghit:300px;"></li>
 			<li style="margin:0;padding:0;border=0;float:left;"><img src="/certify/resource/image/main/qnet.jpg" style="height:70px;weghit:300px;"></li>
 		</ul>
  </div>
	<!-- 자격증 사이트 목록 -->

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