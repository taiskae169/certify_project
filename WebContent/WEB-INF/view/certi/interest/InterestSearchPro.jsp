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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="position:static; width:100%; height:100%; background-color:#829FD9">
	<div style="position:static; width:100%; height:20%; display:flex; align-items:center; justify-content:center; border-bottom:5px dashed; border-color:white;">
	<p style="font-size:25pt; color:white;">자격증을 선택해주세요</p>	
	</div>
	<div style="position:static; width:100%; height:80%; display:flex; align-items:flex-start; border-color:white;">
	<form action="InterestSearchEnd.certi" method="post">
	<c:forEach begin="0" end="${certilist.size()-1 }" step="1" var="i">
	<div style="padding-left:50px; width:950px; height:40">
	<c:set var="certi" value="${certilist[i]}"/>
		<input type="radio" name="certi" value="${certi.num }">${certi.name}
		</div>
	</c:forEach>
	<input class="btn btn-lg btn-success" style="margin-left:50px" type="submit" value="선택완료">
	</form>
	</div>
	
	
	</div>
</body>
</html>