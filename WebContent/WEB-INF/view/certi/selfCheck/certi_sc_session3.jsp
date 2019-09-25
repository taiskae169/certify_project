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
    	<!-- 모달창 -->
        <div class="modal fade in" id="defaultModal">
        	<div class="modal-dialog">
            	<div class="modal-content">
                	<div class="modal-header">
                    	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">알림</h4>
                    </div>
					<div class="modal-body">
						<p class="modal-contents"></p>
					</div>
					<div class="modal-footer">
	                	<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
	               	</div>
                </div><!-- /.modal-content -->
			</div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
        <!--// 모달창 -->
               
        <!-- 본문 들어가는 부분 -->
        <hr />         
	 	<div>
	 		<h1> 응시자격 자가진단</h1>
	 		<b><i> ${user_name} 회원님의 경력정보를 확인합니다.</i></b>
	 		<br><hr/>
	 	</div>
	 	<div class="row">
	 		<c:if test="${!empty careerList}">
	 			<table id="user_careerInfo" style="border-style: solid; width:100%;">
 					<thead style="border-style: solid;" align="center">
 						<td>사업체/기관명</td>
 						<td>사업체/기관 업종</td>
 						<td>입사일자</td>
 						<td>퇴사일자</td>
 					</thead>
 					<c:if test="${careerList==null}">
 						<tr align="center"><td colspan="4">입력된 경력사항이 없습니다.</td></tr>
 					</c:if>
 					<c:if test="${careerList!=null}">
 						<c:forEach var="career" items="${careerList}">
 							<tr align="center">
		 						<td>${career.company}</td>
		 						<td>
		 							<c:forEach var="cate" items="${certi_cate}">
				 						<c:if test="${cate.certi_num == career.comp_cate}">${cate.name}</c:if>
				 					</c:forEach>
		 						</td>
		 						<td><fmt:formatDate value="${career.com_ent_date}" type="both" pattern="yyyy년 MM월 dd일"/></td>
		 						<td><fmt:formatDate value="${career.com_gra_date}" type="both" pattern="yyyy년 MM월 dd일"/></td>
	 						</tr>
	 					</c:forEach>
 					</c:if>
 				</table>
	 		</c:if>
	 		<button class="btn" id="insertData" onclick="JavaScript:window.location='/certify/user/mp/input_eduCareer.certi'"> 추가 작성 </button>
	 		<button id="${cerNum}" onclick="goSession4(this.id)"> 다음 단계로 </button>
	 		
	 		<c:if test="${empty eduList}"> <!-- 작업 끝나고 empty로 바꾸기!! -->
	 		
	 			
	 		</c:if>

	 		<div>

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
	function goSession4(id){
		var cerNum=id;
		var URL = "/certify/certi/certi_sc_session4.certi?cerNum=";
		window.location=URL+cerNum;
	}
	
	
</script>

