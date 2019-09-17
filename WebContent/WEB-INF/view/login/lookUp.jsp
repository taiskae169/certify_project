<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
</head>
<body>
	<div class="container">
		<h4>아이디 찾기</h4>
		<hr />
		<form action="#" class="form-horizontal" role="form" method="POST">
			<div class="form-group">
				<label for="name" class="col-lg-2 control-label">이름</label>
				<div class="col-md-4">
				<input type="text" class="form-control onlyHangul" id="name" data-rule-required="true" placeholder="한글만 입력 가능합니다.">
				</div>
			</div>
			<div class="form-group">
				<label for="birth" class="col-lg-2 control-label">생년월일</label>
				<div class="col-md-4">
				<input type="date" class="form-control" id="birth" data-rule-required="true" >
				</div>
			</div>
			<button type="submit" class="btn btn-default" style="float:right;">찾기</button>
		</form>
	</div>
</body>
</html>