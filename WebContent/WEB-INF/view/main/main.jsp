<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
  <script src="//code.jquery.com/jquery.js"></script>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>자격루</title>

  <!-- Bootstrap core CSS -->
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- Custom fonts for this template -->

  <!-- Custom styles for this template -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  

  
  

</head>

<body bgcolor="#F9F3ED">

  <!-- Navigation -->


  <!-- Masthead -->
  <jsp:include page="/WEB-INF/view/Bar/Search.jsp"/>
  <jsp:include page="/WEB-INF/view/Bar/NavBar.jsp"/>

  <!-- Icons Grid -->
  <hr style="width:1450px;"/>
  
  <!--  nav bar -->
      
  <!-- Image Showcases -->
  <section class="showcase" style="width:1450px;;margin:auto;">
    
  </section >
  <div position = "static" style="width:1450px;height:606px;margin:auto;display:block;">
	<div style="width:59%;height:600px;float:left;">
    	<div style="height:310px;margin:3px; text-align:center;">
    		<h4 align="left">신청가능한 시험 목록</h4>
    		<table class="table table-striped" >
    			<thead>
    				<tr>
    					<td>시험이름</td>
    					<td>신청기간</td>
    					<td>신청 사이트</td>
    				</tr>
    			</thead>
    			<tbody id="certiTable1">
    				<tr>
    					<td>제26회 정수시설운영관리사 1,2차 원서접수</td>
    					<td>9월 30일 (월), 오전 9시 – 10월 9일 (수), 오후 6시</td>
    					<td><a href="http://www.q-net.or.kr/man001.do?gSite=Q">이동하기</a></td>
    				</tr>
    				<tr>
    					<td>정기 기능사 4회 필기시험</td>
    					<td>9월 28일 (토), 오전 9시 – 10월 6일 (일), 오후 6시</td>
    					<td><a href="http://www.q-net.or.kr/man001.do?gSite=Q">이동하기</a></td>
    				</tr>
    				<tr>
    					<td>박물관및미술관준학예사 원서접수</td>
    					<td>10월 7일 (월), 오전 9시 – 10월 16일 (수), 오후 6시 </td>
    					<td><a href="http://www.q-net.or.kr/man001.do?gSite=Q">이동하기</a></td>
    				</tr>
    				<tr>
    					<td>정기 기사 3회 실기시험 </td>
    					<td>10월 12일 (토), 오전 9시 – 10월 25일 (금), 오후 6시 </td>
    					<td><a href="http://www.q-net.or.kr/man001.do?gSite=Q">이동하기</a></td>
    				</tr>
    				<tr>
    					<td>정기 기사 4회 실기 원서접수</td>
    					<td>10월 14일 (월), 오전 9시 – 10월 17일 (목), 오후 6시</td>
    					<td><a href="http://www.q-net.or.kr/man001.do?gSite=Q">이동하기</a></td>
    				</tr>
    				<tr>
    					<td>제27회 청소년지도사 1차합격자발표</td>
    					<td>10월 25일 (금), 오전 9시 – 오후 6시</td>
    					<td><a href="http://www.q-net.or.kr/man001.do?gSite=Q">이동하기</a></td>
    				</tr>
    			</tbody>
			</table>

    	</div>
    	<div style="height:200px;margin:3px;">
    		<c:if test="${boardcount==0 }">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<tr>
										<td align="center">
											<h3>해당하는 게시글이 없습니다.. :(</h3>
										</td>
									</tr>	
							</table>
</c:if>
<c:if test="${nocount!=0 }">
<div position = "static">
<h3 align="left">공지사항</h3>
<table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0" border="1px">
			<thead>
				<tr height="30"  style="font-size:20px">
					<td align = "center" width="75">번호</td>
					<td align = "center" width="50">작성자</td>
					<td align = "center" width="300">제목</td>
					
				</tr>
			</thead>
        	<tbody>
        		<c:forEach begin="0" end="${noboardlist.size()-1}" step="1" var="i">
        		<c:set var="noboard" value="${noboardlist[i]}"/>
        		<tr height="30">
        		<td align="center"  width="50" > ${noboard.num}</td>
        		<td align="center"  width="50" > ${noboard.id}</td>
        		<td align="center"  width="300" ><a href="BoardContent.certi?num=${noboard.num }"> ${noboard.title}</a></td>
        		</tr>
        		</c:forEach>
        	</tbody>
        	</table>
        	</div>
        	</c:if>
    		
    	</div>
    </div>
    
    <div style="width:580px;height:600px;float:right;">
    	<div style="height:132.25px;margin:3px;">
    		<jsp:include page="/WEB-INF/view/main/loginbox.jsp">
    			<jsp:param value="${naverURI}" name="naverURI"/>
    			<jsp:param value="${sessionID}" name="sessionID"/>
    		</jsp:include>
    	</div>
    	<div style="height:119px;margin:3px;">
    		<a href="javascript:openlist();"><img src="/certify/resource/image/main/준비중.jpg" style="height:113px;width:561px;"></a>
    		<script type="text/javascript">
    		console.log('tst' + '${sessionID}');
    		console.log('${sessionID}');
    			function openlist(){
        			console.log('${sessionID}');
					var sessionID = '${sessionID}';
        			if(sessionID){
        				window.open('http://localhost:8080/certify/user/mp/callist.certi', '리스트',"toolbar=no, menubar=no, scrollbars=no, width=700, height=500");
            			}else{
                			alert('로그인이 필요합니다.');
                		}
    				
        			}
				
			</script>
    	</div>
    	<div style="height:311px;margin:3px;">
    		<c:if test="${boardcount==0 }">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<tr>
										<td align="center">
											<h3>해당하는 게시글이 없습니다.. :(</h3>
										</td>
									</tr>	
							</table>
</c:if>
<c:if test="${boardcount!=0 }">
<div position = "static" style="hight:315px;">
<table class="table table-bordered table-striped" id="dataTable" style="width:"750"; cellspacing:"0"; border:"1px";">
			<thead>
				<tr height="40"  style="font-size:18px">
					<td align = "center" width="75">번호</td>
					<td align = "center" width="100">작성자</td>
					<td align = "center" width="300">제목</td>
					
				</tr>
			</thead>
        	<tbody>
        		<c:forEach begin="0" end="${boardlist.size()-1}" step="1" var="i">
        		<c:set var="board" value="${boardlist[i]}"/>
        		<tr height="30">
        		<td align="center"  width="75" > ${board.num}</td>
        		<td align="center"  width="100" > ${board.id}</td>
        		<td align="center"  width="300" ><a href="BoardContent.certi?num=${board.num }"> ${board.title}</a></td>
        		</tr>
        		</c:forEach>
        	</tbody>
        	</table>
        	</c:if>
    	</div>
    </div>
   </div>
  <!-- Testimonials -->
  <div style="width:1450px;;height:100px;margin:auto;display:inline-block;">
 		<ul style="list-style:none;margin:0;padding:0;text-align:center;">
 			<li style="margin:6px;padding:0;border=0;float:left;">
 				<a href="http://www.q-net.or.kr"><img src="/certify/resource/image/main/qnet.jpg" style="height:70px;weghit:300px;" class="img-thumbnail"></a>
 			</li>
 			<li style="margin:6px;padding:0;border=0;float:left;">
 				<img src="/certify/resource/image/main/kor_logo.jpg" style="height:70px;weghit:300px;" class="img-thumbnail">
 			</li>
 			<li style="margin:6px;padding:0;border=0;float:left;">
 				<img src="/certify/resource/image/main/toeic.jpg" style="height:70px;weghit:300px;" class="img-thumbnail">
 			</li>
 			<li style="margin:6px;padding:0;border=0;float:left;">
 				<img src="/certify/resource/image/main/tofle.jpg" style="height:70px;weghit:300px;" class="img-thumbnail">
 			</li>
 			<li style="margin:6px;padding:0;border=0;float:left;">
 				<img src="/certify/resource/image/main/HSK.jpg" style="height:70px;weghit:300px;" class="img-thumbnail">
 			</li>
 			<li style="margin:6px;padding:0;border=0;float:left;">
 				<img src="/certify/resource/image/main/JLPT.jpg" style="height:70px;weghit:300px;" class="img-thumbnail">
 			</li>
 			<li style="margin:6px;padding:0;border=0;float:left;">
 				<img src="/certify/resource/image/main/delf-page.png" style="height:70px;weghit:300px;" class="img-thumbnail">
 			</li>
  			<li style="margin:6px;padding:0;border=0;float:left;">
 				<img src="/certify/resource/image/main/jungbo.jpg" style="height:70px;weghit:300px;" class="img-thumbnail">
 			</li>
 			<li style="margin:6px;padding:0;border=0;float:left;">
 				<img src="/certify/resource/image/main/Goethe.png" style="height:70px;weghit:300px;" class="img-thumbnail">
 			</li>
 		</ul>
  </div>
	<!-- 자격증 사이트 목록 -->

  <!-- Footer -->
  <footer>
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


