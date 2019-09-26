<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <script src="https://apis.google.com/js/api.js"></script>
  

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <!-- Custom fonts for this template -->

  <!-- Custom styles for this template -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  


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
  <div style="width:1450px;height:606px;margin:auto;display:block;">
	<div style="width:59%;height:600px;border-style:solid;float:left;">
    	<div style="height:80px;margin:3px;">
    		<h1>정보처리기사</h1>
    		<hr />
    	</div>
    	
    	<div style="height:150px;margin:3px;">
    		컴퓨터를 효과적으로 활용하기 위해서 하드웨어뿐만 아니라 정교한 소프트웨어가 필요 하다. 이에 따라 우수한 프로그램을 개발하여 업무의 효율성을 높이고, 궁극적으로 국 가발전에 이바지하기 위해서 컴퓨터에 관한 전문적인 지식과 기술을 갖춘 사람을 양성 할 목적으로 제정됨. 
    		<br />
    		<br />
    		실시기관 홈페이지 :	<a href="http://www.q-net.or.kr">http://www.q-net.or.kr</a>
    		
    	</div>
    	<div style="height:300px;border-style:solid;margin:3px;">
    		<table class='table table-bordered'>
    			<thead>
    				<th>구분</th>
    				<th>필기접수</th>
    				<th>필시시험일</th>
    				<th>필기합격 발표</th>
    				<th>실기 접수</th>
    				<th>실기 시험일</th>
    				<th>합격발표일자</th>
    			</thead>
    			<tbody>
    				<tr>
    					<th>2019년 정기 1회</th>
    					<th>2019.01.25~<br />2019.01.31</th>
    					<th>2019.03.03</th>
    					<th>2019.03.14</th>
    					<th>2019.03.15~<br/>2019.03.21</th>
    					<th>2019.04.13~<br/>2019.04.27</th>
    					<th>2019.05.22</th>
    				</tr>
    				<tr>
    					<th>2019년 정기 2회</th>
    					<th>2019.03.29~<br />2019.04.04</th>
    					<th>2019.04.27</th>
    					<th>2019.05.17</th>
    					<th>2019.05.20~<br/>2019.05.23</th>
    					<th>2019.06.29~<br/>2019.07.12</th>
    					<th>2019.08.16</th>
    				</tr>
    				<tr>
    					<th>2019년 정기 3회</th>
    					<th>2019.07.05~<br />2019.07.11</th>
    					<th>2019.08.04</th>
    					<th>2019.08.30</th>
    					<th>2019.09.02~<br/>2019.09.05</th>
    					<th>2019.10.12~<br/>2019.10.27</th>
    					<th><a href="javascript:handleClientLoad();"  id='sign3'>2019.11.22</a></th>
    				</tr>
    				
    			</tbody>
    			
    		</table>
    	</div>
    </div>
    <div style="width:580px;height:600px;border-style:solid;float:right;">
    	<div style="height:152.25px;margin:3px;">
    		<jsp:include page="/WEB-INF/view/main/loginbox.jsp">
    			<jsp:param value="${naverURI}" name="naverURI"/>
    			<jsp:param value="${sessionID}" name="sessionID"/>
    		</jsp:include>
    	</div>
    	<div style="height:430px;margin:3px;text-align:center;">
    		<div style="height:300px;margin:3px;inline-block;">
    		<h4>자격증 분류</h4>
    		<hr />
    		<c:forEach var="info" items="${list}" varStatus="status" >
    			
    			<a class="btn btn-default" href="javascript:handleClientLoad();">${info.name}</a>

    		</c:forEach>

    	</div>
    </div>
   </div>
   <script type="text/javascript">

	var CLIENT_ID = '74093674387-j64supuapk07470j07hso4fc9fuufb1g.apps.googleusercontent.com';
	var API_KEY = 'AIzaSyDoo2SNaod2Uze___-WlVqrV9x3MKReLCs';

	var DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];

	var SCOPES = "https://www.googleapis.com/auth/calendar";
	var loadButton = document.getElementById('sign3');


	
	function handleClientLoad(){
		console.log("load시작");
		
		gapi.load('client:auth2', initClient);
	}

	function initClient(){
		console.log("init 시작");
		gapi.client.init({
			apiKey:API_KEY,
			clientId:CLIENT_ID,
			discoveryDocs:DISCOVERY_DOCS,
			scope:SCOPES
		}).then(function(){
			gapi.auth2.getAuthInstance().isSignedIn.listen(updateSigninStatus);
			//isSignedIn.listen()란 로그인 상태를 받을 함수를 넣으면 된다.
			//isSignedIn.listen(updateSigninStatus);일시 updateSigninStatus 함수 안에 로그인 상태가 들어가게 된다
			//로그인 상태일 시 true, 아닐 시 false
			handleAuthClick();
			updateSigninStatus(gapi.auth2.getAuthInstance().isSignedIn.get());
			//authorizeButton.onclick = handleAuthClick;
			//signoutButton.onclick = handleSignoutClick;
			//버튼을 클릭했을 때 작동되도록 설정되어있음
			//loadButton.onclick=handleAuthClick;
			},function(error){
				setList(JSON.stringify(error,null,2));
		});
	}//initClient 함수 종료

	
	function handleAuthClick(event){
		console.log('handleAuthClick');
		gapi.auth2.getAuthInstance().signIn();
		
	}

	function updateSigninStatus(isSignedIn){
		if(isSignedIn){
			//로그인되었을 때 작동할 함수 작성
			console.log("로그인   성공");
			uploadsign();
			
		}else{
			console.log("로그인    실패");
			
		}
	}//updateSigninStatus 함수 종료

	var event=	{
			"end": {
		          "date": "2019-11-22"
		        },
		        "start": {
		          "date": "2019-11-22"
		        },
		        "summary": "시험일정 정보처리기사 ",
		        "reminders": {
		          "overrides": [
		            {
		              "method": "popup",
		              "minutes": 900
		            }
		          ],
		          "useDefault": false
		        }
				}
	function uploadsign(){
		gapi.client.calendar.events.insert({
			"calendarId" : 'primary',
			"resource": event
		}).then(function(){
			window.reload;
			});
	}
   </script>


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