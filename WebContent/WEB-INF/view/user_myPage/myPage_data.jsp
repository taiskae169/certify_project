<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <!-- meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/ -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>사거리</title>
        <!-- Bootstrap -->
        
        <link href="/resources/image/icon/HalfLife.ico" rel="shortcuticon">
        <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->	
        <script src="//code.jquery.com/jquery.js"></script>
        <!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <!-- Respond.js 으로 IE8 에서 반응형 기능을 활성화하세요 (https://github.com/scottjehl/Respond) -->
        <script src="/resource/bootstrap/js/respond.js"></script>
        
        <!-- 모달 CSS 추가 -->
    </head>
    <body>
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
 			<h1> ${uvo.name}님의 회원정보입니다.</h1>
 			<b><i> <!-- 문구 --> </i></b>
 			<br><hr/> 
 			<div id="user_info">
 				<h3>회원 개인정보</h3>
 				<table id="user_Info" style="border-style: solid; width:100%;" align="center">
 					<tr>
 						<td rowspan="3" align="center">
 							<c:if test="${empty uvo.profile}"><img src="/certify/resource/image/icon_userDefault.jpg"/></c:if>
 							<c:if test="${!empty uvo.profile}"><img src="/certify/resource/image/${uvo.profile}"/></c:if>
 						</td>
 						<td>아이디 : ${uvo.id}</td>
 					</tr>
 					<tr>
 						<td>
 							패스워드 : <span id="hide">****</span><span id="real">${uvo.pw}</span>
 							<button class="btn" id="show">보기</button>
 						</td>
 					</tr>
 					<tr>
 						<td>생년월일 : ${uvo.birth}</td>
 					</tr>
 					<tr>
 						<td colspan="2">관심자격분야 :
 							<c:forEach var="cate" items="${certi_cate}">
 								<c:if test="${cate.certi_num == uvo.wana}">${cate.name}</c:if>
 							</c:forEach>
 						</td>
 					</tr>
 					<tr>
 						<td colspan="2">응시자격 : 
 							<c:forEach var="quals" items="${quals}">
 								<c:if test="${uvo.qual == quals.num}">${quals.value}</c:if>
 							</c:forEach>
 						</td>
 					</tr>
 					<tr>
 						<td> 외부사이트 계정 연결 </td>
 						<td> <span><a>네이버</a></span> <span><a>카카오</a></span> <span><a>구글</a></span> </td>
 					</tr>
 				</table>
 			</div>
 			
 			<div id="edu_info">
 				<h3>회원 학력정보</h3>
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
 					<c:if test="${eduList==null}">
 						<tr align="center"><td colspan="7">입력된 학력사항이 없습니다.</td></tr>
 					</c:if>
 					<c:if test="${eduList!=null}">
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
 					</c:if>
 				</table>
 			</div>
 			
 			<div id="career_info">
 				<h3>회원 경력정보</h3>
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
 			</div>
 			
 			<div id="career_info">
 				<h3>회원 자격정보</h3>
 				<table id="user_certiInfo" style="border-style: solid; width:100%;">
 					<thead style="border-style: solid;" align="center">
 						<td>자격증명</td>
 						<td>자격증 분류</td>
 						<td>자격 종목</td>
 						<td>취득일자</td>
 					</thead>
 					<c:if test="${certiList==null}">
 						<tr align="center"><td colspan="4">입력된 자격사항이 없습니다.</td></tr>
 					</c:if>
 					<c:if test="${certiList!=null}">
 						<c:forEach var="certi" items="${certiList}">
 							<tr align="center">
 								<c:forEach var="ac" items="${allCerti}">
			 						<c:if test="${ac.num == certi.cer_name}">
			 							<td>${ac.name}</td>
			 							<td>
				 							<c:choose>
					 								<c:when test="${ac.type == 0}">기능사</c:when>
					 								<c:when test="${ac.type == 1}">산업기사</c:when>
					 								<c:when test="${ac.type == 2}">기사</c:when>
					 								<c:when test="${ac.type == 3}">기술사</c:when>
					 								<c:when test="${ac.type == 4}">기능장</c:when>
					 								<c:when test="${ac.type == 5}">국가전문자격</c:when>
					 								<c:when test="${ac.type == 6}">민간자격</c:when>
					 								<c:when test="${ac.type == 7}">어학자격</c:when>
					 						</c:choose>
				 						</td>
			 							<td>
			 								<c:forEach var="cate" items="${certi_cate}">
				 								<c:if test="${cate.certi_num == ac.cate}">${cate.name}</c:if>
				 							</c:forEach>
			 							</td>
			 						</c:if>
		 						</c:forEach>
		 						<td><fmt:formatDate value="${certi.cer_date}" type="both" pattern="yyyy년 MM월 dd일"/></td>
	 						</tr>
	 					</c:forEach>
 					</c:if>
 				</table>
 			</div>
 			<br></hr><br>
 			<!-- 하단버튼 -->
 			<div align="center">
 				<button id="goSelfCheck">응시자격 자가진단</button>
 				<button id="goFixData" onclick="window.location='/certify/user/mp/myPage.certi?type=mod'">정보 수정</button>
 				<button id="goFixData">회원 탈퇴</button>
 			</div>
 			
 		</div>
 		
		
		<!--// 본문 들어가는 부분 -->
        </div> <!-- end of container -->
    </body>
</html>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(document).ready(function() {
	    $("#hide").show();
	    $("#real").hide();
	    $("#show").click(function() {
	    	if($(this).text()=='보기'){
	        	$("#real").show();
	        	$("#hide").hide();
	        	$(this).text('숨기기');
			}else{
		        $("#real").hide();
		        $("#hide").show();
		        $(this).text('보기');
			}
	    });
	});
	
	$(document).ready(function(){
		$('#goSelfCheck').click(function(){
			window.location="/certify/certi/certi_sc_session1.certi?type=1";
		})
	});
</script>
