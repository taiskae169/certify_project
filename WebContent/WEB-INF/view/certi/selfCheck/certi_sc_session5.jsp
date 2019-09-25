<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:if test="${!empty sessionID}">
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>자격루:응시자격 자가진단</title>
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

	<jsp:include page="/WEB-INF/view/Bar/NavBar.jsp"/>

  <!-- Image Showcases -->
  <section class="showcase" style="width:1450px;;margin:auto;">

  </section >
  
  <!-- 본문  -->
	<div class="container"><!-- 좌우측의 공간 확보 -->
               
        <!-- 본문 들어가는 부분 -->
        <hr />         
	 	<div>
	 		<h1> 응시자격 자가진단</h1>
	 		<b><i> ${user_name} 회원님의 
				<span style="font-size: 20px;">${specCerti.name}</span>
	 		  자격증 응시 자격을 확인합니다.</i></b>
	 		  <button class="btn" onclick="location.href='/certify/main.certi'"> 메인으로 </button>
	 		  <button class="btn" onclick="#"> 관심자격증 등록 </button>
	 		  <button class="btn" onclick="location.href='/certify/user/mp/myPage.certi'"> 마이페이지 </button>
	 		<br><hr/>
	 	</div>
	 	<div class="row">
	 	<h4> 자격증명 : <span>${specCerti.name}</span></h4>
	 	<h4> 진단 결과 : <span id="pass" >${pass}</span></h4>
	 		<table id="user_certiInfo" style="border-style: solid; width:100%;">
 				<c:forEach var="check" items="${checkList}">
 					<tr style="border-style: solid;">
 						<td >
 							<c:if test="${check.possible == true}" >
 								<b style="color:blue; font-size: 15px;">가능</b>
 							</c:if>
 							<c:if test="${check.possible == false}" >
 								<i>불가능</i>
 							</c:if>
 						</td>
 						<td>
 							${check.mess}
 						</td>
 					</tr>
 				</c:forEach>
 			</table>		
	 		<div>
			<button class="btn" onclick="javascript:window.location='/certify/main.certi'">메인으로</button>
	 		</div>	 		
		</div> 
	</div> <!-- container end -->
    
    
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
		alert("로그인이 필요한 서비스입니다.");
		window.location="/certify/main.certi";
	</script>
</c:if>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function goSession5(id){
		var cerNum=id;
		var URL = "/certify/certi/certi_sc_session5.certi?cerNum=";
		window.location=URL+cerNum;
	}
	$(document).ready(function(){
		if( $('#pass').text() == '불가능' ){
			$('#pass').css('color', 'red');
		}else{
			$('#pass').css('color', 'blue');
		}
	});
	
</script>

