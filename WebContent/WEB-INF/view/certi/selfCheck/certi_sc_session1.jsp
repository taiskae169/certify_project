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
	 			<b><i>자가진단을 원하시는 자격증을 선택해주세요.</i></b>
	 			<br><hr/> 
	 		</div>
	 		<div>
	 			<a><span id="gukki"
	 				style="border-style: solid; padding: 5px; background-color: navy; color: white;">
	 				국가기술자격</span></a> \
	 			<a><span id="gukjun"
	 				 style="border-style: solid; padding: 5px;">
	 				 국가전문자격</span></a>
	 			<br><br>
	 			<form name="searchCerti" action="certi_sc_session1.certi" >
	 				<input type="hidden" name="type" value="${type}" />
	 				<input type="text" name="certi_name" placeholder="검색할 자격증을 입력해주세요."/>
	 			</form>
	 		</div>
	 		<br>
	 		</hr>
	 		<div>
	 			<ul>
	 				<!--  자격증 리스트 출력  -->
	 				<c:if test="${count>0}">
		 				<c:forEach begin="0" end="${certiList.size()-1}" step="1" var="i">
	        				<c:set var="certi" value="${certiList[i]}"/>
		 					<li><a id="${certi.num}" onclick="goSession2(this.id)">${certi.name}</a></li>
		 				</c:forEach>
	 				</c:if>
	 				<c:if test="${count<=0}">
	 					<p>해당하는 자격증이 없습니다 :( </p>
	 				</c:if>
	 			</ul>
	 		</div>
	 		<div position = "static" align="center">
		 		<c:if test="${count>0 }">
			      <c:if test="${startPage>10}">
			      	<c:if test="${certi_name!=null}">
			      		<a href="certi_sc_session1.certi?type=${type}&pageNum=${startPage - 10}&certi_name=${certi_name}">[이전]</a>
			      	</c:if>
			      	<c:if test="${certi_name==null}">
			         	<a href="certi_sc_session1.certi?type=${type}&pageNum=${startPage - 10}">[이전]</a>
			        </c:if>
			      </c:if>
			      <c:forEach begin="${startPage}" end="${endPage }" step="1" var="i">
			      	<c:if test="${certi_name!=null}">
			      		<a href="certi_sc_session1.certi?type=${type}&pageNum=${i}&certi_name=${certi_name}">[${i}]</a>
			      	</c:if>
			      	<c:if test="${certi_name==null}">
			         <a href="certi_sc_session1.certi?type=${type}&pageNum=${i}">[${i}]</a>
			        </c:if>
			      </c:forEach>
			      <c:if test="${endPage < pageCount}">
			      	<c:if test="${certi_name!=null}">
			         <a href="certi_sc_session1.certi?type=${type}&pageNum=${startPage + 10}&certi_name=${certi_name}">[다음]</a>
			        </c:if>
			        <c:if test="${certi_name==null}">
			         <a href="certi_sc_session1.certi?type=${type}&pageNum=${startPage + 10}">[다음]</a>
			        </c:if>
			      </c:if>
			   </c:if>
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
	$(document).ready(function() {
		var getUrlParameter = function getUrlParameter(sParam) {
			var sPageURL = decodeURIComponent(window.location.search.substring(1)),
			sURLVariables = sPageURL.split('&'),
			sParameterName,
			i;
			for (i = 0; i < sURLVariables.length; i++) {
				sParameterName = sURLVariables[i].split('=');
				if (sParameterName[0] === sParam) {
					return sParameterName[1] === undefined ? true : sParameterName[1];
				}
			}
		};
		var type = getUrlParameter('type');

		if(type=='1'){
			$("#gukki").css('background-color' , 'navy');
		    $("#gukki").css('color' , 'white');
		    $("#gukjun").css('background-color' , 'white');
		    $("#gukjun").css('color' , 'black');
		}else{
			$("#gukjun").css('background-color' , 'navy');
		    $("#gukjun").css('color' , 'white');
		    $("#gukki").css('background-color' , 'white');
		    $("#gukki").css('color' , 'black');
		}

		 $("#gukki").click(function() {
				window.location="certi_sc_session1.certi?type=1";
		    });
		
	    $("#gukjun").click(function() {
			window.location="certi_sc_session1.certi?type=2";
	    });
	});

	function goSession2(id){
		var cerNum=id;
		var URL = "/certify/certi/certi_sc_session2.certi?cerNum=";
		window.location=URL+cerNum;
	}
	
</script>

