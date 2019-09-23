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
<script language="javascript" type="text/javascript">  

function InterestSearch(){  
    window.open("/certify/InterestSearch.certi", "검색새창", "width=800, height=700, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
}  
</script>

</head>
<body>
	<jsp:include page="/WEB-INF/view/Bar/Search.jsp"/>

	<jsp:include page="/WEB-INF/view/Bar/NavBar.jsp"/>
	
	<div style="position:static; width:1000px; background-color:red; height:100%;">
		<div style="position:static; width:1000px; background-color:red; height:100%; display:flex; align-items:center; justify-content:center">
		<button class="btn btn-lg btn-primary" onclick="InterestSearch()">검색</button>
		</div>
		
		
	</div>
	


</body>
</html>