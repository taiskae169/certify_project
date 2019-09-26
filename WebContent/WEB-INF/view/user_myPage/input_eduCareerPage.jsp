<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
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
 		<div>
 			<h1> 회원 학력 및 경력정보 입력 </h1>
 			<b><i>주의! 작성을 원치 않는 항목은 반드시 "넘어가기"버튼, 또는"삭제"버튼을 활용해주세요!</i></b>
 			<br><hr/> 
 		</div>
 		<c:set var="i" value="0" /> <!-- default value for form's id & name -->
 		<div class="input_informations">
	 		<h2>학력사항</h2>
	 		<b>학력사항을 입력합니다. </b><input class="btn" type="button" id="hideShow_edu" value="넘어가기" /><br><br>
	 		<p id="warning_edu" style="color: red;">넘어가기 버튼을 누르시면 항목의 내용이 전송되지 않습니다. (기존 작성내용 포함)</p>
	 		<div id="eduInfo">
				<div id="edu_forms">
	        		<form class="form-horizontal"  name="user_edu_${i}" id="user_edu_${i}" role="form" method="post" action="inputEduCareer_Pro.certi" >
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
					            	<c:forEach var="cate" items="${category}">
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
	            	</form>
	        	</div>
	        	<input class="btn" id="plus" type="button" name="session_plus" value="추가입력" onclick="addForm_edu()">
	        </div>
        </div> <!-- input_informations -->
		<hr />
        <div class="input_informations">
        	<h2> 경력사항 </h2>
			<b>경력사항을 입력합니다. </b><input class="btn" type="button" id="hideShow_career" value="넘어가기" /><br><br>
			<p id="warning_career" style="color: red;">넘어가기 버튼을 누르시면 항목의 내용이 전송되지 않습니다. (기존 작성내용 포함)</p>
       		<div id="careerInfo">
				<div id="career_forms">
       				<form class="form-horizontal"  name="user_career_${i}" id="user_career_${i}" role="form" method="post" action="inputEduCareer_Pro.certi" >
       					<div class="form-group" id="divCompany">
	                		<label for="company" class="col-lg-2 control-label">사업체명</label>
	                		<div class="col-lg-10">
				                <input type="text" id="company" class="form-control" name="company" data-rule-required="true" placeholder="사업체명" maxlength="40">
	               			</div>
	            		</div>
	            		<div class="form-group" id="divComp_cate">
			                <label for="inputComp_cate" class="col-lg-2 control-label">사업체 업종/직무</label>
			                <div class="col-lg-10">
			                    <select id="comp_cate" class="form-control" id="comp_cate" name="comp_cate">
			                       	<c:forEach var="cate" items="${category}">
										<option value="${cate.certi_num}">${cate.name}</option>
									</c:forEach>
			                    </select>
	                		</div>
			            </div>
			           	<div class="form-group" id="divComp_Ent_Date">
			                <label for="inputComp_Ent_date" class="col-lg-2 control-label">입사일자</label>
			                <div class="col-lg-10">
			                    <input type="date" id="comp_ent_date" class="form-control" name="comp_ent_date" data-rule-required="true" max="9999-12-31"  placeholder="YYYY-MM-DD">
			                </div>
			            </div>
			            <div class="form-group" id="divComp_Gra_Date">
			                <label for="inputComp_Gra_date" class="col-lg-2 control-label">퇴사일자</label>
			                <div class="col-lg-10">
			                    <input type="date" id="comp_gra_date" class="form-control" name="comp_gra_date" data-rule-required="true" max="9999-12-31" placeholder="YYYY-MM-DD">
			                	<span> ※ 현재 재직중인 직장 제외, 현재 날짜를 꼭 입력해주세요. </span>
			                </div>
			            </div> 
       				</form>
       			</div>
       			<input class="btn" id="plus" type="button" name="session_plus" value="추가입력" onclick="addForm_career()">
       		</div>
       		<hr />
       		<div class="input_informations">
        	<h2> 취득자격사항 </h2>
			<b>회원님이 보유한 자격사항을 입력합니다. </b><input class="btn" type="button" id="hideShow_certi" value="넘어가기" /><br><br>
			<p id="warning_certify" style="color: red;">넘어가기 버튼을 누르시면 항목의 내용이 전송되지 않습니다. (기존 작성내용 포함)</p>
       		<div id="certifyInfo">
				<div id="certify_forms">
       				<form class="form-horizontal"  name="user_certi_${i}" id="user_certi_${i}" role="form" method="post" action="inputEduCareer_Pro.certi" >
       					<div class="form-group" id="divCerti">
	                		<label for="company" class="col-lg-2 control-label">자격증명</label>
	                		<div class="col-lg-10">
				                <input type="text" id="certi_name" class="form-control" name="certi_name" data-rule-required="true" placeholder="자격증명 (클릭하여 입력하세요.)" maxlength="40" readOnly onclick="openCerti(this.form)">
	               			</div>
	            		</div>
			           	<div class="form-group" id="divCerti_Ent_Date">
			                <label for="inputCer_Ent_date" class="col-lg-2 control-label">취득일자</label>
			                <div class="col-lg-10">
			                    <input type="date" id="cer_date" class="form-control" name="cer_date" data-rule-required="true" max="9999-12-31"  placeholder="YYYY-MM-DD">
			                </div>
			            </div>
       				</form>
       			</div>
       			<input class="btn" id="plus" type="button" name="session_plus" value="추가입력" onclick="addForm_certi()">
       		</div>
        </div> <!-- input_informations -->
        </div> 
		
		
		<!--// 본문 들어가는 부분 -->
        </div> <!-- end of container -->
        <br>
        <div style="position:relative; right:50px;">
			<div class="col-lg-offset-2 col-lg-10">
				<button class="btn btn-default" id="allSubmit">입력</button>
				<button class="btn btn-default" id="back" style="background-color: rgb(219,68,85)" onclick="javascript:history.back()">돌아가기</button>
			</div>
		</div>
    </body>
</html>


<!-- 학력/경력사항 입력창 숨기기/보이기 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    $("#eduInfo").show();
	    $("#careerInfo").show();
	    $("#certifyInfo").show();
	    $("#warning_edu").hide();
	    $("#warning_career").hide();
	    $("#warning_certify").hide();
	    $("#hideShow_edu").click(function() {
	    	if($(this).val()=='작성하기'){
	        	$("#eduInfo").show('2500');
	        	$(this).val('넘어가기');
			}else{
		        $("#eduInfo").hide('2500');
		        $(this).val('작성하기');
		        $("#warning_edu").show('2500');
			}
	    });
	    $("#hideShow_career").click(function() {
	    	if($(this).val()=='작성하기'){
	        	$("#careerInfo").show('2500');
	        	$(this).val('넘어가기');
			}else{
		        $("#careerInfo").hide('2500');
		        $(this).val('작성하기');
		        $("#warning_career").show('2500');
			}
	    });
	    $("#hideShow_certi").click(function() {
	    	if($(this).val()=='작성하기'){
	        	$("#certifyInfo").show('2500');
	        	$(this).val('넘어가기');
			}else{
		        $("#certifyInfo").hide('2500');
		        $(this).val('작성하기');
		        $("#warning_certify").show('2500');
			}
	    });
	});
</script>

<!-- 학력사항 기재 간 추가 팝업이 뜨는 스크립트 -->
<script type="text/javascript">
	function openSub(form){
		var formname = form.name;
		var splitArr = formname.split('_');
		var num = splitArr[2];
		url="eduChooseProcess.certi?num="+num;
		window.open(url, "confirm","toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizable=no, width=600, height=600");
	}

	function openCerti(form){
		var formname = form.name;
		var splitArr = formname.split('_');
		var num = splitArr[2];
		url="Pro_careerInput.certi?num="+num;
		window.open(url, "confirm","toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizable=no, width=600, height=600");
	}
</script>

<!-- 학력사항 추가/삭제 스크립트 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var num = ${i}
	function addForm_edu() {
	    var form = document.createElement('form');
	    num+=1;
	    form.className = "form-horizontal"; 
	    form.name = 'user_edu_'+num;
	    form.id = "user_edu_"+num;
	    form.role = "form";
	    form.method = "post"; 
	    form.action = "inputEduCareer_Pro.certi";
	    var tags = "<hr /><div class='form-group' id='divEdu_name'><label for='edu_name' class='col-lg-2 control-label'>학교명</label><div class='col-lg-10'><input type='text' id='edu_name' class='form-control' name='edu_name' data-rule-required='true' placeholder='학교명' maxlength='40' readOnly onclick='openSub(this.form)'></div></div>";
	    	tags += "<div class='form-group' id='divMajor_name'><label for='major_name' class='col-lg-2 control-label'>학과명</label><div class='col-lg-10'><input type='text' id='major_name' class='form-control' name='major_name' data-rule-required='true' placeholder='학과명' readOnly onclick='openSub(this.form)'></div></div>";
			tags += "<div class='form-group' id='divEduType'><label for='inputEduType' class='col-lg-2 control-label'>학제</label><div class='col-lg-10'><input type='text' id='eduType' class='form-control' name='eduType' data-rule-required='true' placeholder='학제' maxlength='30' readOnly onclick='openSub(this.form)'></div></div>";
			tags += "<div class='form-group' id='divState'><label for='inputState' class='col-lg-2 control-label'>상태</label><div class='col-lg-10'><select class='form-control' id='state' name='state'><option value='0'>졸업</option><option value='1'>재학중</option><option value='2'>졸업예정</option><option value='3'>중퇴</option></select></div></div>";
			tags += "<div class='form-group' id='divMajor'><label for='inputMajor' class='col-lg-2 control-label'>분류</label><div class='col-lg-10'><select class='form-control' id='major' name='major'><c:forEach var='cate' items='${category}'><option value='${cate.certi_num}'>${cate.name}</option></c:forEach></select></div></div>";
			tags += "<div class='form-group' id='divEnt_Date'><label for='inputEnt_date' class='col-lg-2 control-label'>입학일자</label><div class='col-lg-10'><input type='date' id='ent_date' class='form-control' name='ent_date' data-rule-required='true' required max='9999-12-31'  placeholder='YYYY-MM-DD'></div></div>";
			tags += "<div class='form-group' id='divGra_Date'><label for='inputGra_date' class='col-lg-2 control-label'>졸업일자</label><div class='col-lg-10'><input type='date' id='gra_date' class='form-control' name='gra_date' data-rule-required='true' max='9999-12-31'  placeholder='YYYY-MM-DD'><span> 현재 재학 시 미기재 또는 졸업일자 기재요망 (졸업예정자는 졸업일자를 작성해주세요.) </span></div></div>";
			tags += "<input type='hidden' name='edu'><input class='btn' type='button' name='session_minus' value='삭제' onclick='removeForm(this.form);'><br><br> ";
		form.innerHTML = tags;
	    document.getElementById('edu_forms').appendChild(form);
	}
	function removeForm(form_Name) {
		var formid = form_Name.id;
		$('#'+formid).detach();
	}
</script>

<!-- 경력사항 추가/삭제 스크립트 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var num = ${i}
	function addForm_career() {
	    var form = document.createElement('form');
	    num+=1;
	    form.className = "form-horizontal"; 
	    form.name = 'user_career_'+num;
	    form.id = "user_career_"+num;
	    form.role = "form";
	    form.method = "post"; 
	    form.action = "inputEduCareer_Pro.certi";
	    var tags = "<hr /><div class='form-group' id='divCompany'><label for='company' class='col-lg-2 control-label'>사업체명</label><div class='col-lg-10'><input type='text' id='company' class='form-control' name='company' data-rule-required='true' placeholder='사업체명' maxlength='40'></div></div>";
	    	tags += "<div class='form-group' id='divComp_cate'><label for='inputComp_cate' class='col-lg-2 control-label'>사업체 업종/직무</label><div class='col-lg-10'><select id='comp_cate' class='form-control' id='comp_cate' name='comp_cate'><c:forEach var='cate' items='${category}'><option value='${cate.certi_num}'>${cate.name}</option></c:forEach></select></div></div>";
	    	tags += "<div class='form-group' id='divComp_Ent_Date'><label for='inputComp_Ent_date' class='col-lg-2 control-label'>입사일자</label><div class='col-lg-10'><input type='date' id='comp_ent_date' lass='form-control' name='comp_ent_date' data-rule-required='true' required max='9999-12-31'  placeholder='YYYY-MM-DD'></div></div>";
	    	tags += "<div class='form-group' id='divComp_Gra_Date'><label for='inputComp_Gra_date' class='col-lg-2 control-label'>퇴사일자</label><div class='col-lg-10'><input type='date' id='comp_gra_date' class='form-control' name='comp_gra_date' data-rule-required='true' max='9999-12-31' placeholder='YYYY-MM-DD'><span> 현재 재직 시 미기재 </span></div></div>";
	    	tags += "<input class='btn' type='button' name='session_minus' value='삭제' onclick='removeForm(this.form);'><br><br> ";
		form.innerHTML = tags;
	    document.getElementById('career_forms').appendChild(form);
	}
	function removeForm(form_Name) {
		var formid = form_Name.id;
		$('#'+formid).detach();
	}
</script>

<!-- 자격사항 추가/삭제 스크립트 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	var num = ${i}
	function addForm_certi() {
	    var form = document.createElement('form');
	    num+=1;
	    form.className = "form-horizontal"; 
	    form.name = 'user_certi_'+num;
	    form.id = "user_certi_"+num;
	    form.role = "form";
	    form.method = "post"; 
	    form.action = "inputEduCareer_Pro.certi";
	    var tags = "<hr /><div class='form-group' id='divCerti'><label for='company' class='col-lg-2 control-label'>자격증명</label>";
	    	tags += "<div class='col-lg-10'><input type='text' id='certi_name' class='form-control' name='certi_name' data-rule-required='true' placeholder='자격증명 (클릭하여 입력하세요.)' maxlength='40' readOnly onclick='openCerti(this.form)'></div></div>";
	    	tags += "<div class='form-group' id='divCerti_Ent_Date'><label for='inputCer_Ent_date' class='col-lg-2 control-label'>취득일자</label><div class='col-lg-10'><input type='date' id='cer_date' class='form-control' name='cer_date' data-rule-required='true' max='9999-12-31'  placeholder='YYYY-MM-DD'></div></div>";
	    	tags += "<input class='btn' type='button' name='session_minus' value='삭제' onclick='removeForm(this.form);'><br><br> ";
		form.innerHTML = tags;
	    document.getElementById('certify_forms').appendChild(form);
	}
	function removeForm(form_Name) {
		var formid = form_Name.id;
		$('#'+formid).detach();
	}
</script>

<!-- 전체 폼들을 전송하는 쿼리 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
	$(function(){
		$('#allSubmit').click(function(){
			var p = confirm("정말 작성하시겠습니까?");
			if(p==true){
				var edu_data = '';
				var career_data = '';
				var certi_data = '';
				var eduArr = new Array();
				var careerArr = new Array();
				var certiArr = new Array();
				var edu_forms = $('#edu_forms').find('.form-horizontal');
				var career_forms = $('#career_forms').find('.form-horizontal');
				var certi_forms = $('#certify_forms').find('.form-horizontal');

				// 학력사항전송
				if($('#hideShow_edu').val()=='작성하기'){
					var c = confirm("학력사항이 작성되지 않았습니다. 계속하시겠습니까?");
					if(c==true){
						edu_data="notUse";
					}else{
						return false;
					}
				}else{
					for(var x=0; x<edu_forms.length; x++){
						if( $('#'+edu_forms[x].id+'#edu_name').val()=="" || $('#'+edu_forms[x].id+'#major_name').val()=="" ){
							alert("학교 및 학과는 필수기재사항입니다!")
							$('#'+edu_forms[x].id+'#edu_name').focus();
							return false;
						}else{
							eduArr[x] = edu_forms[x].id;
						}
					}
					for(var x=0; x < eduArr.length; x++){
						edu_data += $('#'+eduArr[x]).serialize()+'@@';
					}
				}

				// 경력사항전송
				if($('#hideShow_career').val()=='작성하기'){
					var c = confirm("경력사항이 작성되지 않았습니다. 계속하시겠습니까?");
					if(c==true){
						career_data="notUse";
					}else{
						return false;
					}
				}else{
					// 유효성검사 완성 X
					for(var x=0; x<career_forms.length; x++){
						if( $('#'+career_forms[x].id+'#company').val()=="" ){
							alert("사업체명은 필수기재사항입니다!")
							$('#'+career_forms[x].id+'#company').focus();
							return false;
						}else{
							careerArr[x] = career_forms[x].id;
						}
					}
					for(var x=0; x < careerArr.length; x++){
						career_data += $('#'+careerArr[x]).serialize()+'@@';
					}
				}

				// 자격사항전송
				if($('#hideShow_certi').val()=='작성하기'){
					var c = confirm("경력사항이 작성되지 않았습니다. 계속하시겠습니까?");
					if(c==true){
						certi_data="notUse";
					}else{
						return false;
					}
				}else{
					// 유효성검사 완성 X
					for(var x=0; x<certi_forms.length; x++){
						if( $('#'+certi_forms[x].id+'#cer_name').val()=="" ){
							alert("자격증명은 필수기재사항입니다!")
							$('#'+certi_forms[x].id+'#cer_name').focus();
							return false;
						}else{
							certiArr[x] = certi_forms[x].id;
						}
					}
					for(var x=0; x < certiArr.length; x++){
						certi_data += $('#'+certiArr[x]).serialize()+'@@';
					}
				}
				
				
				$.ajax({
					url : "inputUserData_Pro.certi",
					type: "POST",
					data : { "eduList" : edu_data , "careerList" : career_data , "certiList" : certi_data },
					success:function(data){
						alert("저장되었습니다!");
						window.location="/certify/user/mp/myPage.certi";
					},
					error:function(jqXHR, textStatus, errorThrown){
						alert("에러가 발생하였습니다!"+textStatus+" : "+errorThrown);
						return false;
					}
				});
			}else{
				return false;
			}
		})
	})

</script>
