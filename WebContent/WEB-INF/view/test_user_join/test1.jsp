<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script language="javascript">
	function openSub(){
		url="test_uni.certi";
		window.open(url, "confirm","toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizable=no, width=600, height=600");
	}
</script>
<form name="user_edu_info" action="">
	구분 : 
	<select>
		<option>고등학교</option>
		<option>대학교</option>
		<option>대학원</option>
	</select>
	학교명 : <a id="school_name" onclick="openSub();"><input type="text" name="school_name" readOnly /></a>
	학과명 : <a id="major_name" onclick="openSub();"><input type="text" name="major_name" readOnly /></a>
	학제 : <a id="major_years" onclick="openSub();"><input type="text" name="edu" readOnly /></a>
	상태 : <select id="status" name="status">
			<option value="0">졸업</option>
			<option value="1">재학중</option>
			<option value="2">졸업예정</option>
			<option value="3">중퇴</option>
		</select>
	입학일자 : <input type="date" name="ent_date" />	
	졸업일자 : <input type="date" name="gra_date" />
	<input type=hidden" name="major">
</form>


