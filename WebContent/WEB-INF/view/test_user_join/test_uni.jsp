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
	var school_name_val = $('input#school_name').val();
	function search(){
		if(school_name_val!=null){
			window.location="test_uni.certi?school_name="+school_name_val;
		}else{
			alert("학교 명을 입력해주세요.");
		}
	}
	function setUni_name(){		
	    opener.document.user_edu_info.school_name.value=${uni_name[i]};
	    opener.document.user_edu_info.school_name.value = document.getElementById("${i}").value
		self.close();
	}
</script>

<form>
	<b>학교명을 입력하세요.</b><br>
	<input type="text" name="school_name" id="school_name"/>
	<input type="button" value="검색" name="search" id="search" onclick="search();"/>

</form>
<br><br><br>
<div class="scrollBoard" border="1">
	<c:if test="${uni_name!=null}" >
		<c:forEach begin="0" end="${uni_name_length}" step="1" var="i">
			<span><a onclick="setUni_name();" id="${i}">${uni_name[i]}</a></span><br>
		</c:forEach>
	</c:if>
	<c:if test="${uni_name==null}" >
		<span> 해당하는 학교가 없습니다. </span>
	</c:if>
</div>

