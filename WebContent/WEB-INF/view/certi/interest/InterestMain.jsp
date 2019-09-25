<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>InterestMain</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">


</head>
<body>
	<div style="position:static; width:100%; height:100%; background-color:#829FD9">
		<div style="position:static; width:100%; height:20%; display:flex; align-items:center; justify-content:center;">
			<div style=" width:100%; height:100%; word-break:break-all;word-wrap:break-word;">
			<p style="padding-left:230px; font-size:35pt; color:white;">관심자격증</p>
			
			<p style="padding-left:100px; font-size:25pt; color:white;">이름 또는 목록으로 검색해주세요</p>
			</div>
		</div>
	<div style="position:static; width:100%; height:40%; display:flex; align-items:center; justify-content:center; border-top:5px dashed; border-color:white;">
	<form action="InterestSearchPro.certi" method="post">
	<div style="position:static; width:100%; height:50%; display:flex; align-items:center; justify-content:center">
	<input type="text" style="border-radius: 1; font-size:14pt;" name="search" id="search" > <%-- 검색어 입력창 --%> 
	</div>
	<div style="position:static; width:100%; height:50%; display:flex; align-items:center; justify-content:center">
	<input class="btn btn-lg btn-success" type="submit" value="이름으로 검색" placeholder="검색어를 입력해주세요"></div> 
</form>
</div>
	
	
	<div style="position:static; width:100%; height:40%; display:flex; align-items:center; justify-content:center; border-top:5px dashed; border-color:white;">
	<a class="btn btn-lg btn-success" href="/certify/InterestTypeForm.certi" role="button" style="display:block;float:right;">목록으로 검색하기</a>
		</div>

</div>
</body>
</html>