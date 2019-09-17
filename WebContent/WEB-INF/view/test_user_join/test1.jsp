<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 기본값 0 -->
<c:set var="i" value="0" />
<div id="input_informations">
	<!-- 학력사항 기재코드 -->
	<h2> 학력사항 </h2>
	<b>학력사항을 입력합니다. </b><input type="button" id="hideShow_edu" value="넘어가기" /><br>
	<div id="eduInfo">
		<div id="edu_forms">
		<form class="forms_edu" name="user_edu_${i}" id="user_edu_${i}" method="post" action="testEC_Pro.certi">
			학교명 : <input type="text" name="edu_name" readOnly onclick="openSub(this.form)" />
			학과명 : <input type="text" name="major_name" readOnly onclick="openSub(this.form)"/>
			학제 : <input type="text" name="eduType" readOnly onclick="openSub(this.form)"/>
			상태 : <select id="status" name="state">
					<option value="0">졸업</option>
					<option value="1">재학중</option>
					<option value="2">졸업예정</option>
					<option value="3">중퇴</option>
				</select>
			분류 : <select id="major" name="major" required>
					<c:forEach var="cate" items="${category}">
							<option value="${cate.certi_num}">${cate.name}</option>
					</c:forEach>
				</select>
			입학일자 : <input type="date" name="ent_date" />	
			졸업일자 : <input type="date" name="gra_date" />
			<input type="hidden" name="edu">
		</form>
		</div>
		<input id="plus" type="button" name="session_plus" value="추가" onclick="addForm_edu()">
	</div>
	
	<!-- 경력사항 기재코드 -->
	<h2> 경력사항 </h2>
	<b>경력사항을 입력합니다. </b><input type="button" id="hideShow_career" value="넘어가기" /><br>
	<div id="careerInfo">
		<div id="career_forms">
			<form class="forms_career" name="user_career_${i}" id="user_career_${i}" method="post" action="testEC_Pro.certi">
				사업체명 : <input type="text" name="company" />
				사업체 업종/직무 : <select name="comp_cate">
						<c:forEach var="cate" items="${category}">
							<option value="${cate.certi_num}">${cate.name}</option>
						</c:forEach>
					</select>
				입사일자 : <input type="date" name="comp_ent_date" />	
				퇴사일자 : <input type="date" name="comp_gra_date" />
					<span>(미퇴사시 미기재)</span>
			</form>
		</div>
		<input id="plus" type="button" name="session_plus" value="추가" onclick="addForm_career()">
	</div>
	
	<button id="allSubmit"> 입력! </button>
</div>

<!-- 학력/경력사항 입력창 숨기기/보이기 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	    $("#eduInfo").show();
	    $("#careerInfo").show();
	    $("#hideShow_edu").click(function() {
	    	if($(this).val()=='작성하기'){
	        	$("#eduInfo").show('2500');
	        	$(this).val('넘어가기');
			}else{
		        $("#eduInfo").hide('2500');
		        $(this).val('작성하기');
			}
	    });
	    $("#hideShow_career").click(function() {
	    	if($(this).val()=='작성하기'){
	        	$("#careerInfo").show('2500');
	        	$(this).val('넘어가기');
			}else{
		        $("#careerInfo").hide('2500');
		        $(this).val('작성하기');
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
		url="test_uni.certi?num="+num;
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
	    form.className = "forms_edu"; 
	    form.name = 'user_edu_'+num;
	    form.id = "user_edu_"+num;
	    form.method = "post"; 
	    form.action = "testEC_Pro.certi";
	    form.innerHTML = "\
	    	학교명 : <input type='text' name='edu_name' readOnly onclick='javascript:openSub(this.form);' />\
		   	학과명 : <input type='text' name='major_name' readOnly onclick='javascript:openSub(this.form);'/>\
		    학제 : <input type='text' name='eduType' readOnly onclick='javascript:openSub(this.form);'/>\
			상태 : <select id='status' name='state'><option value='0'>졸업</option>\
					<option value='1'>재학중</option><option value='2'>졸업예정</option>\
					<option value='3'>중퇴</option>	</select>\
			분류 : <select id='major' name='major' required>\
					<c:forEach var='cate' items='${category}'>\
							<option value='${cate.certi_num}'>${cate.name}</option>\
					</c:forEach>\
				</select>\
			입학일자 : <input type='date' name='ent_date' />\
			졸업일자 : <input type='date' name='gra_date' />\
			<input type='hidden' name='edu'>\
			<input type='button' name='session_minus' value='삭제' onclick='removeForm(this.form);'>";
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
	    form.className = "forms_career"; 
	    form.name = 'user_career_'+num;
	    form.id = "user_career_"+num;
	    form.method = "post"; 
	    form.action = "testEC_Pro.certi";
	    form.innerHTML = "\
	    	사업체명 : <input type='text' name='company' />\
			사업체 업종 : <select name='comp_cate'>\
					<c:forEach var='cate' items='${category}'>\
						<option value='${cate.certi_num}'>${cate.name}</option>\
					</c:forEach>\
				</select>\
			입사일자 : <input type='date' name='comp_ent_date' />\
			퇴사일자 : <input type='date' name='comp_gra_date' />\
				<span>(미퇴사시 미기재)</span>\
			<input type='button' name='session_minus' value='삭제' onclick='removeForm(this.form);'>";
	     document.getElementById('career_forms').appendChild(form);
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
				var eduArr = new Array();
				var careerArr = new Array();
				var edu_formsize = $('#edu_forms').find('.forms_edu');
				var career_formsize = $('#career_forms').find('.forms_career');
				
				for(var x=0; x<edu_formsize.length; x++){
					eduArr[x] = edu_formsize[x].id;
				}
				for(var x=0; x<career_formsize.length; x++){
					careerArr[x] = career_formsize[x].id;
				}
				for(var x=0; x < eduArr.length; x++){
					edu_data = edu_data + $('#'+eduArr[x]).serialize()+'@@';
				}
				for(var x=0; x < careerArr.length; x++){
					career_data = career_data + $('#'+careerArr[x]).serialize()+'@@';
				}

				$.ajax({
					url : "testEC_Pro.certi",
					type: "POST",
					data : { "eduList" : edu_data , "careerList" : career_data },
					success:function(data){
						alert("저장되었습니다!");
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



