<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<form action="BoardWritePro.certi" method="post" enctype="multipart/form-data">
	<table cellSpacing=1 cellPadding=1 width="80%" border=1 align="center">
	<tbody>
		<tr>
			<td>작성자</td> <td><p>${sessionScope.memId}<p></td>
		</tr>
		<tr>
			<td>제목</td> <td><input type="text" name="subject"></td>
		</tr>
		<tr>
			<td>내용</td> <td width="300"><textarea row="50" cols="100" name="content"></textarea></td>
		</tr>
		<tr>
			<td>첨부파일<input multiple="multiple" type="file" name="save" /></td>
		</tr>
		<tr>
			<td align="center"><input type="submit" value="전송"></td><td align="center"><button onclick="window.location='Boardlist.kb'">목록보기</button></td>
		</tr>
		
	</tbody></table>
	
</form>
