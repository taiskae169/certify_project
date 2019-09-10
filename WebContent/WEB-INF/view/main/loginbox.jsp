<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="//developers.kakao.com/sdk/js/kakao.min.js" ></script>
<script src="https://apis.google.com/js/api:client.js"></script>
<meta name="google-signin-client_id" content="74093674387-j64supuapk07470j07hso4fc9fuufb1g.apps.googleusercontent.com">


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
		<div style=" position:relative; left:320px;bottom:105px; width:40px;">
			<a href="${naverURI}"><img src="/certify/resource/image/login/naver_icon_long.PNG" style="width:200px;height:30px; margin:2px;"/> </a> <br />
			<a href="javascript:loginWithKakao()" id="custom-login-btn"><img src="/certify/resource/image/login/kakao_icon_middle.png" style="width:200px;height:30px; margin:2px;"/></a> <br />
			<script type="text/javascript">
				//<![CDATA[
					//앱의 자바키를 설정
					Kakao.init('2f8c03ebb18198694ddbf5441cfee132');
					function loginWithKakao(){
						Kakao.Auth.login({
							scope: "account_email",
			                success: function(authObj) {
			               	Kakao.API.request({
			    				url:'/v2/user/me',
								success:function(res){
									console.log('JSON.stringify(res) ='+JSON.stringify(res));
									var userID = res.id;
									var email = res.kakao_account.email;
									var name = res.properties.nickname;
				
									console.log('ID : ' + userID);
									console.log('email :' + email);

									
				
									location="/certify/user/kakaoLogin.certi?kakaoId="+userID+"&id="+email+"&name="+name;
									Kakao.Auth.logout();
									console.log('kakao.logout');
								},
								fail:function(error){
									alert(JSON.stringify(error));
								}
							});
						},
							fail:function(err){
								alert(JSON.stringify(err));
							}
						});
					};
				//]]>
			</script>
			
			

			<a href="javascript:loginWithGoogle();" ><img src="/certify/resource/image/login/google_icon_middle.png" style="width:200px;height:30px; margin:2px;"/> </a>
			<script type="text/javascript">
			//<![CDATA[
				function loginWithGoogle(){
					console.log('init');
					gapi.load('auth2',function(){
						console.log('auth2');
						gauth = gapi.auth2.init({
							client_id:'74093674387-j64supuapk07470j07hso4fc9fuufb1g.apps.googleusercontent.com'
						})
						gauth.then(function(){
							console.log('googleAuth success');
							console.log('googlelogin start');

							gauth.signIn({prompt:'select_account'}).then(function(){
								var gUser = gauth.currentUser.get();
								profile = gUser.getBasicProfile();
								firstName = profile.getGivenName();
								lastName = profile.getFamilyName();
								name = firstName+lastName;
								email = profile.getEmail();
								id = profile.getId();
								gauth.signOut().then(function(){
									console.log('로그아웃');
									gauth.disconnect();
								});
								location = "/certify/user/googleLogin.certi?id="+email+"&name="+name+"&googleId=" +id;							
							});

						},
						function(){
							console.log('googleAuth fail');
						});

					});
				}
					
					
			//]]>
			</script>
			

		
		</div>
	</div>
		
</div>