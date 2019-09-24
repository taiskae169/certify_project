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
	<div style="position:static; width:1000px; background-color:red; height:100%; display:flex; align-items:center; justify-content:center">
	<div>
	<form action="InterestSearchPro.certi" method="post">
	<div><input type="text" name="search" id="search"></div>
	<div><input type="submit" value="전송"></div> 
</form>
	</div>
	<div>
	<a class="btn btn-default" href="/certify/InterestTypeForm.certi" role="button" style="display:block;float:right;">목록으로 검색하기</a>
		</div>
	</div>

</body>
</html>