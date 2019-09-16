<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
	function openSub(name){
		var formname = name;
		console.log(formname);
		var splitArr = name.split('_');
		var num = splitArr[3];
		url="test_uni.certi?num="+num;
		window.open(url, "confirm","toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizable=no, width=600, height=600");
	}

</script>
<c:set var="i" value="0" />
<div id="forms">
	<form name="user_edu_info_${i}" class="infoForm" action="" method="post">
		구분 : 
		<select id="choose_grade">
			<option id="ss1">고등학교</option>
			<option id="ss2">대학교/원</option>
		</select>
		학교명 : <input type="text" name="school_name" readOnly onclick="javascript:openSub(this.form);" />
		학과명 : <input type="text" name="major_name" readOnly onclick="javascript:openSub(this.form);"/>
		학제 : <input type="text" name="eduType" readOnly onclick="javascript:openSub(this.form);"/>
		상태 : <select id="status" name="status">
				<option value="0">졸업</option>
				<option value="1">재학중</option>
				<option value="2">졸업예정</option>
				<option value="3">중퇴</option>
			</select>
		입학일자 : <input type="date" name="ent_date" />	
		졸업일자 : <input type="date" name="gra_date" />
		<input type="hidden" name="edu">
	</form>
</div>
<input id="plus" type="button" name="session_plus" value="추가" onclick="addForm()">
<input type="button" name="session_minus" value="삭제" onclick="removeForm()">

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	var num = ${i}
	function addForm() {
	    var form = document.createElement('form');
	    num+=1;
	    form.name = 'user_edu_info_'+num;
	    form.className = 'infoForm';
	    form.innerHTML = "\
		    구분 : <select id='choose_grade'>\
		    	<option id='ss1'>고등학교</option>\
		    	<option id='ss2'>대학교/원</option></select>\
			학교명 : <a id='school_name' onclick='openSub(${i});'><input type='text' name='school_name' readOnly /></a>\
			학과명 : <a id='major_name' onclick='openSub(${i});'><input type='text' name='major_name' readOnly /></a>\
			학제 : <a id='eduType' onclick='openSub(${i});'><input type='text' name='eduType' readOnly /></a>\
			상태 : <select id='status' name='status'><option value='0'>졸업</option>\
					<option value='1'>재학중</option><option value='2'>졸업예정</option>\
					<option value='3'>중퇴</option>	</select>\
			입학일자 : <input type='date' name='ent_date' />\
			졸업일자 : <input type='date' name='gra_date' />\
			<input type='hidden' name='edu'>";
	     document.getElementById('forms').appendChild(form);
	}
	function removeForm() {
		$("#user_edu_info_"+num).remove();
	}
</script>




