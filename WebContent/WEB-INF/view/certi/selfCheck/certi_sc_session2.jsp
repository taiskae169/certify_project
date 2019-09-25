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
  <jsp:include page="/WEB-INF/view/Bar/Search.jsp"/>

<jsp:include page="/WEB-INF/view/Bar/NavBar.jsp"/>

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
	 		<!-- 유저 학력정보확인 -->
	 		<h1> 응시자격 자가진단</h1>
	 		<b><i> ${user_name} 회원님의 학력정보를 확인합니다.</i></b>
	 		<br><hr/>
	 	</div>
	 	<div class="row">
	 		<c:if test="${!empty eduList}">
	 			<table id="user_eduInfo" style="border-style: solid; width:100%;">
	 				<thead style="border-style: solid;" align="center">
	 					<td>학교명</td>
	 					<td>학과(전공)명</td>
	 					<td>학제</td>
	 					<td>전공분야</td>
	 					<td>상태</td>
	 					<td>입학일자</td>
	 					<td>졸업일자</td>
	 				</thead>
	 				<c:forEach var="edu" items="${eduList}">
	 					<tr align="center">
		 					<td>${edu.edu_name}</td>
		 					<td>${edu.major_name}</td>
		 					<td>
								<c:forEach var="ev" items="${edu_value}">
			 						<c:if test="${ev.num == edu.edu}">${ev.value}</c:if>
			 					</c:forEach>	 							
		 					</td>
		 					<td>
		 						<c:forEach var="cate" items="${certi_cate}">
				 					<c:if test="${cate.certi_num == edu.major}">${cate.name}</c:if>
				 				</c:forEach>
		 					</td>
		 					<td>
								<c:choose>
										<c:when test="${edu.state == 0}">졸업</c:when>
										<c:when test="${edu.state == 1}">재학</c:when>
										<c:when test="${edu.state == 2}">졸업예정</c:when>
										<c:when test="${edu.state == 3}">중퇴</c:when>
									</c:choose>
		 					</td>
		 					<td><fmt:formatDate value="${edu.ent_date}" type="both" pattern="yyyy년 MM월 dd일"/></td>
		 					<td><fmt:formatDate value="${edu.gra_date}" type="both" pattern="yyyy년 MM월 dd일"/></td>
							</tr>
						</c:forEach>
	 			</table>
	 		</c:if>
	 		
	 		<button class="btn" id="insertData" onclick="JavaScript:window.location='/certify/user/mp/input_eduCareer.certi'"> 추가 작성 </button>
	 		<button class="btn" id="${cerNum}" onclick="goSession3(this.id)"> 다음 단계로 </button>
			 		
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
	function goSession3(id){
		var cerNum=id;
		var URL = "/certify/certi/certi_sc_session3.certi?cerNum=";
		window.location=URL+cerNum;
	}

	function openSub(form){
		var formname = form.name;
		var splitArr = formname.split('_');
		var num = splitArr[2];
		url="/certify/user/mp/eduChooseProcess.certi?num="+num;
		window.open(url, "confirm","toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizable=no, width=600, height=600");
	}

	
	
</script>

