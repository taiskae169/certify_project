<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.scrollBoard{
        overflow-x: auto;
        overflow-y: auto;
        width: 500px;
        height: 500px;
        border-style: solid;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	var certi_name_val = $('input#certi_name').val();

	function press(f){ 
		if(f.keyCode == 13){ //javascript에서는 13이 enter키를 의미함 
			search_form.submit(); 
		} 
	}

	function setCerti_name(selectId){		
		opener.document.user_certi_${num}.certi_name.value = selectId;
		self.close();
	}
	
</script>
<!-- 자격증 검색 -->
<div id="certi_check">
	<form name="search_form" action="Pro_careerInput.certi">
		<b>자격증을 입력하세요.</b><br>
		세부검색 : <select name="searchHelp">
			<option value="all" selected>전체</option>
			<option value="ss1">국가기술자격</option>
			<option value="ss2">국가전문자격</option>
			<option value="ss3">민간자격</option>
			<option value="ss4">어학자격</option>
		</select>
		<input type="text" name="certi_name" id="certi_name" onkeypress="JavaScript:press(this.form)" />
		<input type="hidden" name="num" value="${num}" >
		<input type="submit" value="검색" id="search" />
	</form>
	<br>
	<div class="scrollBoard">
		<c:if test="${certi_list != null}" >
			<c:forEach begin="0" end="${certi_list.size()}" step="1" var="i">
				<span><a onclick="setCerti_name(this.id);" id="${certi_list[i]}"> ${certi_list[i]} </a></span><br>
			</c:forEach>
		</c:if>
		<c:if test="${certi_list == null}" >
			<span> 해당하는 자격증이 없습니다. </span>
		</c:if>
	</div>
</div>


