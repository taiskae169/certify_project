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
	var school_name_val = $('input#school_name').val();
	var major_name_val = $('input#major_name').val();

	function press(f){ 
		if(f.keyCode == 13){ //javascript에서는 13이 enter키를 의미함 
			search_form.submit(); 
		} 
	}

	function setUni_name(selectId){		
		opener.document.user_edu_${num}.edu_name.value = selectId;
		window.location="eduChooseProcess.certi?num="+${num}+"&school_nameFix="+selectId;
	}
	function setMajor_name(selectId){		
		opener.document.user_edu_${num}.major_name.value = selectId;
		window.location=window.location.href+"&major_nameFix="+selectId;
	}
</script>
<!-- 대학교 검색 -->
<c:if test="${major_List==null}" >
<div id="uni_check">
	<form name="search_form" action="eduChooseProcess.certi">
		<b>학교명을 입력하세요.</b><br>
		<span> 고등학교의 경우 직접 입력해주세요. ("고등학교"를 정확히 입력해주세요!) </span>
		<input type="text" name="school_name" id="school_name" onkeypress="JavaScript:press(this.form)" />
		<input type="hidden" name="num" value="${num}" >
		<input type="submit" value="검색" id="search" />
	</form>
	<br>
	<div class="scrollBoard">
		<c:if test="${uni_List!=null}" >
			<c:forEach begin="0" end="${uni_List_length}" step="1" var="i">
				<span><a onclick="setUni_name(this.id);" id="${uni_List[i]}"> ${uni_List[i]} </a></span><br>
			</c:forEach>
		</c:if>
		<c:if test="${uni_List==null}" >
			<span> 해당하는 학교가 없습니다. </span>
		</c:if>
	</div>
</div>
</c:if>

<!-- 학과 검색 -->
<c:if test="${school_nameFix!=null}">
<div id="major_check" >
	<form name="search_form" action="eduChooseProcess.certi">
		<b>학과명을 입력하세요.</b><br>
		<input type="text" name="major_name" id="major_name" onkeypress="JavaScript:press(this.form)"  />
		<input type="hidden" name="num" value="${num}" >
		<input type="hidden" name="school_nameFix" value="${school_nameFix}" >
		<input type="submit" value="검색" id="search" />
	</form>
	<br>
	<div class="scrollBoard">
		<c:if test="${major_List!=null}" >
			<c:forEach begin="0" end="${major_List_length}" step="1" var="i">
				<span><a onclick="setMajor_name(this.id);" id="${major_List[i]}">${major_List[i]}</a></span><br>
			</c:forEach>
		</c:if>
		<c:if test="${major_List==null}" >
			<span> 해당하는 학과/전공이(가) 없습니다. </span>
		</c:if>
	</div>
</div>
</c:if>


<c:if test="${eduType!=null}">
	<c:if test="${major==999 }">
		<input type="hidden" id="major" value="${major}">
		<script>
			var major_val = document.getElementById('major').value;
			function setMajor(major){
				opener.document.user_edu_${num}.major.value = major_val;
				opener.document.user_edu_${num}.major.text = "기타";
				opener.document.user_edu_${num}.major.setAttribute('disabled','true');
			}
			setMajor(major_val);
		</script>
	</c:if>
	<c:if test="${edu!=null}">
		<input type="hidden" id="edu" value="${edu}">
		<input type="hidden" id="eduType" value="${eduType}">		
		<input type="hidden" id="major" value="${eduType}">
			<script>
				var edu_val = document.getElementById('edu').value;
				var eduType_val = document.getElementById('eduType').value;
				function setEdu(edu, eduType){
					opener.document.user_edu_${num}.edu.value = edu_val;
					opener.document.user_edu_${num}.eduType.value = eduType_val;
					self.close();
				}
				setEdu(edu,eduType);
			</script>
	</c:if>
</c:if>

