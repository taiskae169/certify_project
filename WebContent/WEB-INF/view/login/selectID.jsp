<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	<script src="//code.jquery.com/jquery.js"></script>
	<meta charset="UTF-8">
	<title>아이디 선택</title>
	
	<script type="text/javascript">
		
	</script>
</head>
<body>
	<div class="container">
		<h4>아이디 선택</h4>
		<hr />
		<form action="/certify/user/selectIDPro.certi" class="form-horizontal" role="form" method="POST">
			<div class="form-group">
				<label for="name" class="col-lg-2 control-label">아이디</label>
				<div class="col-md-4">
					
					<select class="form-control" id="id" name="id">
							<c:forEach var="id" items="${IDList}" varStatus="i">
								<script type="text/javascript">
									var mail = "${id}";
									var tmp = mail.split('@');
									$('#id${i.count}').text("tmp");
								</script>

								<option value="${id}" id="id${i.count}">${id}</option>
							</c:forEach>
					</select>
					
				
				</div>
			</div>
			<button type="submit" class="btn btn-default" style="float:right;">찾기</button>
		</form>
		
	</div>



</body>
</html>