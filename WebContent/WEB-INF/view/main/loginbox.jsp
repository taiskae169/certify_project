<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<div class="container" style="background-color:#EAEAEA;width:568px;height:172.25px;margin:auto;">
	<form action="/certify/user/loginPro.certi" id="loginForm" style="display:inline-block; margin:10px;" method="POST">
		<label for="id-box" style="display:inline-block; width:60px;">EMAIL </label>
		<input type="text" name="id" /> <br />
		<label for="pw-box" style="display:inline-block; width:60px;">PW </label>
		<input type="password" name="pw" />
	</form>
	<button type="button" class="btn btn-info" onclick="document.getElementById('loginForm').submit();" style="position:relative; height:55px; bottom:15px;">로그인</button> <br />
	<div style="margin:10px;">
		<a href="#">회원가입</a> /
		<a href="#">ID/PW찾기</a>
		<div style=" position:relative; left:320px;bottom:105px;">
			<a href="${naverURI}"><img src="/certify/resource/image/login/naver_icon_long.PNG" style="width:200px;height:30px; margin:2px;"/> </a> <br />
			<a href="#"><img src="/certify/resource/image/login/kakao_icon_middle.png" style="width:200px;height:30px; margin:2px;"/></a> <br />
			<a href="#"><img src="/certify/resource/image/login/google_icon_middle.png" style="width:200px;height:30px; margin:2px;"/> </a>
		</div>
	</div>
		
</div>