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
 				<form id="user_Info" name="user_Info" method="post">
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
 							패스워드 : <input type="text" name="pw" id="pw" value="${uvo.pw}"/>
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
 							// 변경 : <select name="wana">
 							<c:forEach var="cate" items="${certi_cate}">
 									<option value="${cate.certi_num}">${cate.name}</option>
 									<c:if test="${uvo.wana==cate.certi_num}">
 										<option value="${cate.certi_num}" selected>${cate.name}</option>
 									</c:if>
 								</c:forEach>
 							</select>
 							
 						</td>
 					</tr>
 					<tr>
 						<td colspan="2">응시자격 : 
 							<c:forEach var="quals" items="${quals}">
 								<c:if test="${uvo.qual==quals.num}">${quals.value}</c:if>
 							</c:forEach>
 							   // 변경 : <select name="qual">
	 							<c:forEach var="quals" items="${quals}">
 									<option value="${quals.num}">${quals.value}</option>
 									<c:if test="${uvo.qual==quals.num}">
 										<option value="${quals.num}" selected>${quals.value}</option>
 									</c:if>
 								</c:forEach>
 							</select>
 						</td>
 					</tr>
 					<tr>
 						<td> 외부사이트 계정 연결 </td>
 						<td> <span><a>네이버</a></span> <span><a>카카오</a></span> <span><a>구글</a></span> </td>
 					</tr>
 				</table>
 				</form>
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
 						<td>변경</td>
 					</thead>
 					<c:if test="${empty eduList}">
 						<tr align="center"><td colspan="8">입력된 학력사항이 없습니다.</td></tr>
 					</c:if>
 					<c:if test="${eduList!=null}">
 					<c:set var="i" value="0"/>
 						<c:forEach var="edu" items="${eduList}">
 							<form name="form_edu_${i}" id="form_edu_${i}">
 							<tr align="center">
		 						<td>${edu.edu_name}</td> <input type="hidden" name="edu_name" value="${edu.edu_name}"/>
		 						<td>${edu.major_name}</td> <input type="hidden" name="major_name" value="${edu.major_name}"/>
		 						<td>
									<c:forEach var="ev" items="${edu_value}"> 
				 						<c:if test="${ev.num == edu.edu}">${ev.value}</c:if>
				 					</c:forEach>	 							
				 					<input type="hidden" name="edu" value="${edu.edu}"/>
		 						</td>
		 						<td>
		 							<c:forEach var="cate" items="${certi_cate}">
				 						<c:if test="${cate.certi_num == edu.major}">${cate.name}</c:if>
				 					</c:forEach>
				 					<input type="hidden" name="major" value="${edu.major}"/>
		 						</td>
		 						<td>
									<c:choose>
					 					<c:when test="${edu.state == 0}">졸업</c:when>
					 					<c:when test="${edu.state == 1}">재학</c:when>
					 					<c:when test="${edu.state == 2}">졸업예정</c:when>
					 					<c:when test="${edu.state == 3}">중퇴</c:when>
					 				</c:choose>
					 				<input type="hidden" name="state" value="${edu.state}"/>
		 						</td>
		 						<td><fmt:formatDate value="${edu.ent_date}" type="both" pattern="yyyy년 MM월 dd일" /></td>
		 							<input type="hidden" name="ent_date" value="${edu.ent_date}"/>
		 						<td><fmt:formatDate value="${edu.gra_date}" type="both" pattern="yyyy년 MM월 dd일"/></td>
		 							<input type="hidden" name="gra_date" value="${edu.gra_date}"/>
		 						<td>
		 							<a id="delete" onclick="delete_eduForm(form_edu_${i});">
		 								<img src="/certify/resource/image/icon_deletebox.png" width="30"/>
		 							</a>
		 						</td>
	 						</tr>
	 						</form>
	 						<c:set var="i" value="${i+1}"/>
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
 						<td>변경</td>
 					</thead>
 					<c:if test="${empty careerList}">
 						<tr align="center"><td colspan="5">입력된 경력사항이 없습니다.</td></tr>
 					</c:if>
 					<c:if test="${careerList!=null}">
 					<c:set var="j" value="0"/>
 						<c:forEach var="career" items="${careerList}">
 							<form name="form_career_${j}" id="form_career_${j}">
 							<tr align="center">
		 						<td>${career.company}</td> <input type="hidden" name="company" value="${career.company}"/>
		 						<td>
		 							<c:forEach var="cate" items="${certi_cate}">
				 						<c:if test="${cate.certi_num == career.comp_cate}">${cate.name}</c:if>
				 					</c:forEach>
				 					<input type="hidden" name="comp_cate" value="${career.comp_cate}"/>
		 						</td>
		 						<td><fmt:formatDate value="${career.com_ent_date}" type="both" pattern="yyyy년 MM월 dd일"/></td>
		 						<td><fmt:formatDate value="${career.com_gra_date}" type="both" pattern="yyyy년 MM월 dd일"/></td>
		 							<input type="hidden" name="com_ent_date" value="${career.com_ent_date}"/>
		 							<input type="hidden" name="com_gra_date" value="${career.com_gra_date}"/>
		 						<td>
		 							<a id="delete" onclick="delete_careerForm(form_career_${j})">
		 								<img src="/certify/resource/image/icon_deletebox.png" width="30"/>
		 							</a>
		 						</td>
	 						</tr>
	 						</form>
	 						<c:set var="j" value="${j+1}"/>
	 					</c:forEach>
 					</c:if>
 				</table>
 			</div>
 			
 			<div id="certi_info">
 				<h3>회원 자격정보</h3>
 				<table id="user_certiInfo" style="border-style: solid; width:100%;">
 					<thead style="border-style: solid;" align="center">
 						<td>자격증명</td>
 						<td>자격증 분류</td>
 						<td>자격 종목</td>
 						<td>취득일자</td>
 						<td>변경</td>
 					</thead>
 					<c:if test="${empty certiList}">
 						<tr align="center"><td colspan="5">입력된 자격사항이 없습니다.</td></tr>
 					</c:if>
 					<c:if test="${certiList!=null}">
 					<c:set var="x" value="0"/>
 						<c:forEach var="certi" items="${certiList}">
 							<form name="form_certi_${x}" id="form_certi_${x}" >
 							<tr align="center">
 								<c:forEach var="ac" items="${allCerti}">
			 						<c:if test="${ac.num == certi.cer_name}">
			 							<td>${ac.name}</td> <input type="hidden" name="cer_name" value="${certi.cer_name}"/>
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
					 						<input type="hidden" name="type" value="${certi.type}"/>
				 						</td>
			 							<td>
			 								<c:forEach var="cate" items="${certi_cate}">
				 								<c:if test="${cate.certi_num == ac.cate}">${cate.name}</c:if>
				 							</c:forEach>
				 							<input type="hidden" name="cate" value="${certi.cate}"/>
			 							</td>
			 						</c:if>
		 						</c:forEach>
		 						<td>
		 							<fmt:formatDate value="${certi.cer_date}" type="both" pattern="yyyy년 MM월 dd일"/>
		 							<input type="hidden" name="cer_date" value="${certi.cer_date}"/>
		 						</td>
		 						<td>
		 							<a id="delete" onclick="delete_certiForm(form_certi_${x})">
		 								<img src="/certify/resource/image/icon_deletebox.png" width="30"/>
		 							</a>
		 						</td>
	 						</tr>
	 						</form>
	 						<c:set var="x" value="${x+1}"/>
	 					</c:forEach>
 					</c:if>
 				</table>
 			</div>
 			<br></hr><br>
 			<!-- 하단버튼 -->
 			<div align="center">
 				<button id="modifyFinish">수정 완료</button>
 			</div>
 			
 		</div>
 		
		
		<!--// 본문 들어가는 부분 -->
        </div> <!-- end of container -->
    </body>
</html>

<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	function delete_eduForm(form){
		console.log(form.id);
		var formid = form.id;
		var c = confirm("정말 삭제하시겠습니까?");
		if(c==true){
			$.ajax({
				url : "myPage_delete.certi",
				type: "GET",
				data : {"userEdu" : $('#'+formid).serialize()},
				success:function(data){
					alert("삭제되었습니다!");
					window.location.reload();
				},
				error:function(jqXHR, textStatus, errorThrown){
					alert("에러가 발생하였습니다!"+textStatus+" : "+errorThrown);
					return false;
				}
			});
		}
	}

	function delete_careerForm(form){
		console.log(form);
		var formid = form.id;
		var c = confirm("정말 삭제하시겠습니까?");
		if(c==true){
			$.ajax({
				url : "myPage_delete.certi",
				type: "GET",
				data : {"userCareer" : $('#'+formid).serialize()},
				success:function(data){
					alert("삭제되었습니다!");
					window.location.reload();
				},
				error:function(jqXHR, textStatus, errorThrown){
					alert("에러가 발생하였습니다!"+textStatus+" : "+errorThrown);
					return false;
				}
			});
		}
	}

	function delete_certiForm(form){
		console.log(form.id);
		var formid = form.id;
		var c = confirm("정말 삭제하시겠습니까?");
		if(c==true){
			$.ajax({
				url : "myPage_delete.certi",
				type: "GET",
				data : {"userCerti" : $('#'+formid).serialize()},
				success:function(data){
					alert("삭제되었습니다!");
					window.location.reload();
				},
				error:function(jqXHR, textStatus, errorThrown){
					alert("에러가 발생하였습니다!"+textStatus+" : "+errorThrown);
					return false;
				}
			});
		}
	}


	$(document).ready(function(){
		$('#modifyFinish').click(function(){
			var user_info = $('#user_Info').serialize();
			alert(user_info);

			$.ajax({
				url : "myPage_modifyPro.certi",
				type: "GET",
				data : {"user_info" : user_info},
				success:function(data){
					alert("수정되었습니다!");
					window.location="/certify/user/mp/myPage.certi";
				},
				error:function(jqXHR, textStatus, errorThrown){
					alert("에러가 발생하였습니다!"+textStatus+" : "+errorThrown);
					return false;
				}
			});
		});
	});
</script>
