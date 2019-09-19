<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <!-- meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/ -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>사거리</title>
        <!-- Bootstrap -->
        
        <link href="/resources/image/icon/HalfLife.ico" rel="shortcuticon">
        <!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요한) -->	
        <script src="//code.jquery.com/jquery.js"></script>
        <!-- 모든 합쳐진 플러그인을 포함하거나 (아래) 필요한 각각의 파일들을 포함하세요 -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <!-- Respond.js 으로 IE8 에서 반응형 기능을 활성화하세요 (https://github.com/scottjehl/Respond) -->
        <script src="/resource/bootstrap/js/respond.js"></script>
        
        <!-- 모달 CSS 추가 -->
    </head>
    <body>
        <div class="container"><!-- 좌우측의 공간 확보 -->
            <!-- 모달창 -->
            <div class="modal fade in" id="defaultModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                            <h4 class="modal-title">알림</h4>
                        </div>
                        <div class="modal-body">
                            <p class="modal-contents"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <!--// 모달창 -->
        <!-- 본문 들어가는 부분 -->
        <hr />         
 		
 		<div>
 			<h1> ${sessionID}님의 회원정보입니다.</h1>
 			<b><i> <!-- 문구 --> </i></b>
 			<br><hr/> 
 			<div id="user_info">
 				<table>
 					<tr><td rowspan=""><!-- 프로필 --></td><td>아이디 : ${uvo.id}</td></tr>
 					<tr><td></td></tr>
 					
 				
 				</table>
 			</div>
 			
 			
 		</div>
 		
		
		<!--// 본문 들어가는 부분 -->
        </div> <!-- end of container -->
    </body>
</html>
