<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function openSub(){
		url="test_test.certi?type=sch_name";
		open(url, "confirm","toolbar=no, location=no, status= no, menubar=no, scrollbars=no, resizable=no, width=600, height=600");
	}
</script>
<form name="" action="">
	구분 : <select>
		<option>고등학교</option>
		<option>대학교</option>
		<option>대학원</option>
	</select>
	학교명 : <a id="school_name_click" onclick="openSub();"><input type="text" name="school_name" readOnly /></a>
	
	학과명 : <a id="school_name_click" onclick="openSub();"><input type="text" name="school_name" readOnly /></a>
</form>


