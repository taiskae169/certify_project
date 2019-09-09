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
#uni_check{
	dispaly: block;
}
#major_check{
	dispaly: none;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	var staus = false;
	var school_name_val = $('input#school_name').val();
	var major_name_val = $('input#major_name').val();

	function search_uni(){
		window.location="test_uni.certi?school_name="+school_name_val;
	}
	function search_major(){
		window.location="test_uni.certi?major_name="+major_name_val;
	}

	function setUni_name(selectId){		
		opener.document.user_edu_info.school_name.value = selectId;
		window.location="test_uni.certi?school_nameFix="+selectId;
		$('#uni_check').hide();
		$('#major_check').show();
	}
	function setMajor_name(selectId){		
		opener.document.user_edu_info.major_name.value = selectId;
		window.location=window.location.href+"?major_nameFix="+selectId;
		self.close();
	}
</script>
<div id="uni_check">
	<form>
		<b>학교명을 입력하세요.</b><br>
		<input type="text" name="school_name" id="school_name" onKeypress="javascript:if(event.keyCode==13) {search_uni();}" />
		<input type="button" value="검색" name="search" id="search" onclick="search_uni();"/>
	</form>
	<br><br><br>
	<div class="scrollBoard" border-line="solid">
		<c:if test="${uni_name!=null}" >
			<c:forEach begin="0" end="${uni_name_length}" step="1" var="i">
				<span><a onclick="setUni_name(this.id);" id="${uni_name[i]}">${uni_name[i]}</a></span><br>
			</c:forEach>
		</c:if>
		<c:if test="${uni_name==null}" >
			<span> 해당하는 학교가 없습니다. </span>
		</c:if>
	</div>
</div>
<!-- 학교 및 학과 div 구분 -->
<div id="major_check" >
	<form>
		<b>학과명을 입력하세요.</b><br>
		<input type="text" name="major_name" id="major_name" onKeypress="javascript:if(event.keyCode==13) {search_major();}" />
		<input type="button" value="검색" name="search" id="search" onclick="search_major();"/>
	</form>
	<br><br><br>
	<div class="scrollBoard" border-line="solid">
		<c:if test="${major_name!=null}" >
			<c:forEach begin="0" end="${major_name_length}" step="1" var="i">
				<span><a onclick="setMajor_name(this.id);" id="${major_name[i]}">${major_name[i]}</a></span><br>
			</c:forEach>
		</c:if>
		<c:if test="${major_name==null}" >
			<span> 해당하는 학과/전공이(가) 없습니다. </span>
		</c:if>
	</div>
</div>

