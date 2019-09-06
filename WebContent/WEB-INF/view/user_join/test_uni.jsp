<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.scrollBoard{
        overflow-x: auto;
        overflow-y: auto;
        width: 500px;
        height: 500px;
    }
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	function search(){
		location.href=
	}
</script>

<form>
	<b>학교명을 입력하세요.</b><br>
	<input type="text" name="school_name" id="school_name"/>
	<input type="button" name="검색" id="search" onclick="search();"/>

</form>
<br><br><br>
<div class="scrollBoard" border="1">
	
</div>

