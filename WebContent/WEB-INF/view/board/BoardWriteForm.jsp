<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<script type="text/javascript" src="<%=application.getContextPath()%>/ckeditor/ckeditor.js" charset="UTF-8">
	CKEDITOR.replace('editor1');
</script>
</head>

<form action="BoardWritePro.certi" method="post" enctype="multipart/form-data">
	<table cellSpacing=1 cellPadding=1 width="80%" border=1 align="center">
	<tbody>
		<tr>
			<td width="10%">작성자</td><td><p>${sessionScope.memId}<p></td>
		</tr>
		<tr>
			<td>제목</td> <td><input type="text" name="title" id="title"></td>
		</tr>
		<tr>
			<td>카테고리</td><td>
			<select  name="cate" style="width:200px;height:25px;" id="cate">
			<option value="" selected>게시판을 선택해주세요.</option>
			<option value="">---------------------------</option>
			<c:forEach begin="0" end="${catelist.size()-1}" step="1" var="i">
			<c:set var="cate" value="${catelist[i]}"/>
			<option value="${cate.num }" id="${cate.num }">${cate.value}</option>	
			</c:forEach>
		
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea id="editor1" class="ckeditor" name="content" rows="20" cols="50"></textarea>
			</td>
		</tr>
		<tr>
			<td>첨부파일</td><td><input multiple="multiple" type="file" name="save" /></td>
		</tr>
		<tr>
			<td align="center">
			<button onclick="window.location='BoardList.certi'">목록보기</button>
			</td>
		</tr>
		
	</tbody></table>
	<input type="submit" value="전송">
</form>
