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
	<div style="width:500px; position:static">
	<form action="InterestSearchEnd.certi" method="post">
	<c:forEach begin="0" end="${certilist.size()-1 }" step="1" var="i">
	<div style="width:500px; height:40">
	<c:set var="certi" value="${certilist[i]}"/>
		<input type="radio" name="certi" value="${certi.num }">${certi.name}
		</div>
	</c:forEach>
	<input type="submit" value="선택완료">
	</form>
	</div>
	
</body>
</html>