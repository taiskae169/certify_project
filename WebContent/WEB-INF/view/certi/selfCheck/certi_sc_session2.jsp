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

  <nav class="navbar navbar-light bg-light static-top" style="width:1450px;;margin:auto">
    <div class="container">
      <a class="navbar-brand" href="#">자격루</a>
      <a class="btn btn-primary" href="#">Login</a>
    </div>
  </nav>

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
	 		
	 		<button id="${cerNum}" onclick="goSession3(this.id)"> 다음 단계로 </button>
	 		
	 		<c:if test="${empty eduList}"> <!-- 작업 끝나고 empty로 바꾸기!! -->
	 		
	 			<p> 입력된 학력정보가 없습니다. </p>
	 			<p> 학력정보를 입력해주세요. </p>	
	 			<br> 			
	 			<div class="input_informations">
			 		<h2>학력사항</h2>
			 		<b>학력사항을 입력합니다. (필수항목)</b>
			 		<div id="eduInfo">
						<div id="edu_forms">
							<c:set var="i" value="0" /> <!-- default value for form's id & name -->
			        		<form class="form-horizontal"  name="user_edu_${i}" id="user_edu_${i}" role="form" method="post" >
			            		<div class="form-group" id="divEdu_name">
			                		<label for="edu_name" class="col-lg-2 control-label" >학교명</label>
			                		<div class="col-lg-10">
						                <input type="text" id="edu_name" class="form-control" name="edu_name" data-rule-required="true" placeholder="학교명(클릭하여 입력하세요.)" maxlength="40" readOnly onclick="openSub(this.form)">
			               			</div>
			            		</div>
					            <div class="form-group" id="divMajor_name">
					                <label for="major_name" class="col-lg-2 control-label">학과명</label>
					                <div class="col-lg-10">
					                    <input type="text" id="major_name" class="form-control" name="major_name" data-rule-required="true" placeholder="학과명(클릭하여 입력하세요.)" readOnly onclick="openSub(this.form)">
					                </div>
					            </div>
					            <div class="form-group" id="divEduType">
					                <label for="inputEduType" class="col-lg-2 control-label">학제</label>
					                <div class="col-lg-10">
					                    <input type="text" id="eduType" class="form-control" name="eduType" data-rule-required="true" placeholder="학제" maxlength="30" readOnly onclick="openSub(this.form)">
					                </div>
					            </div>
					            <div class="form-group" id="divState">
					                <label for="inputState" class="col-lg-2 control-label">상태</label>
					                <div class="col-lg-10">
					                    <select class="form-control" id="state" name="state">
					                        <option value="0">졸업</option>
											<option value="1" selected>재학중</option>
											<option value="2">졸업예정</option>
											<option value="3">중퇴</option>
					                    </select>
			                		</div>
					            </div>
					            <div class="form-group" id="divMajor">
							    	<label for="inputMajor" class="col-lg-2 control-label">분류</label>
							        <div class="col-lg-10">
							        	<select class="form-control" name="major" id="major">
							            	<c:forEach var="cate" items="${certi_cate}">
												<option value="${cate.certi_num}">${cate.name}</option>
											</c:forEach>
							            </select>
					                </div>
							    </div>
							    <div class="form-group" id="divEnt_Date">
					                <label for="inputEnt_date" class="col-lg-2 control-label">입학일자</label>
					                <div class="col-lg-10">
					                    <input type="date" id="ent_date" class="form-control" name="ent_date" data-rule-required="true" required max="9999-12-31"  placeholder="YYYY-MM-DD">
					                </div>
					            </div>
					            <div class="form-group" id="divGra_Date">
					                <label for="inputGra_date" class="col-lg-2 control-label">졸업일자</label>
					                <div class="col-lg-10">
					                    <input type="date" id="gra_date" class="form-control" name="gra_date" data-rule-required="true" max="9999-12-31"  placeholder="YYYY-MM-DD" >
					                    <span> ※ 현재 재학 시 미기재 또는 졸업일자 기재필수 (졸업예정자는 예정 졸업일자를 작성해주세요.) </span>
					                </div>
					            </div>
								<input type="hidden" name="edu">
								<input type="button" value="입력" id="submitForm"/>
				           	</form>
				       	</div>
				       </div>

			       </div>
	 			<!-- 입력창 open으로 띄워 입력받은 후 sql.insert -> ajax로 페이지 리로드 -->
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

