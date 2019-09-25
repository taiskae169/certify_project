<%@ page language="java"  contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="//code.jquery.com/jquery.js"></script>	

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">	
  <meta name="description" content="">
  <meta name="author" content="">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

  <title>자격루</title>

  <!-- Bootstrap core CSS -->
  

  <!-- Custom fonts for this template -->
 
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
   <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
  <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
<c:if test="${count!=0 }">
 <c:forEach begin="0" end="${Clist.size()-1}" step="1" var="i">
	<style>
	
      div.b${i} {
      	display: none;
      }
      
      #re${i}{
      	float:right;
        align:right;
      }
    </style>


  
  
<script>
$(document).ready( function() { <%-- 페이지 실행후 바로 실행  --%>
	var i = ${i}
	
	  $("div.b"+i).css("display",'none');
  $( '#re'+i ).click( function() {
    $( 'div.b'+i ).toggle( 'slow' );
  });
});
</script>

</c:forEach>
</c:if>
  <!-- Custom styles for this template -->
  <link href="/certify/resource/gen/css/landing-page.min.css" rel="stylesheet">
</head>
<c:if test="${count==0 }"> <%-- 코멘트 개수 가져오기  --%>
 <div position = "static" style=" float:center; width:1000;">
<div position = "static" style=" float:center; width:1000;" >
  	<div style=" float:left; width:100%; height:30; align:right">
		<span style="float:right; align:right">코멘트개수( ${count } )</span>
  	</div>	
 </div>
 
 <div position = "static" style=" float:center; width:1000;">
  <div style=" float:left; width:100%; height:100">
 	<h3>해당하는 댓글이 없습니다</h3>
 </div>
 </div>
 </div>
 
  	<div position = "static" style=" float:center; background-color:#0FF;  width:1000;">
 <form action="BoardCommentWrite.certi?num=${board.num }" method="post">
  <div style="border-top:1px dashed black; background-color:#efefef; float:left; width:150; height:130">
 	  <span style="line-height:100px; font-size:14pt">${sessionScope.sessionID}</span>
 </div>
  	<div style="border-top:1px dashed black; background-color:#efefef; float:left; width:700; height:130; padding-top:20px;">
 			<textarea style="height:80" row="50" cols="100" name="content"></textarea>
 		</div>
 	<div style="border-top:1px dashed black; background-color:#efefef; float:left; width:150; height:130; padding-top:20px">
  	  <br>
  	  <input class="btn btn-lg btn-success" style="float:center; align:center" type="submit" value="전송">  	  
  	</div>
 	</form>
 </div>
</c:if>
<c:if test="${count!=0 }">

 <div position = "static" style=" float:center; width:1000;">
  	<div style=" float:left; width:100%; height:30; align:right">
		<span style="float:right; align:right">코멘트개수( ${count } )</span>
  	</div>	
 </div>
 
 <c:forEach begin="0" end="${Clist.size()-1}" step="1" var="i">
 <c:set var="comment" value="${Clist[i]}"/>
 
 <div position = "static" style=" float:center; width:1000;">
 	<div style="border-top:1px solid black; float:left; width:150; height:130">
 	<span style="line-height:100px; font-size:14pt">${comment.id}</span>
 </div>
  		<div style="overflow: auto; border-top:1px solid black; float:left; width:700; height:130; padding-top:15px">
 			<span style="align:center; text-align:center;">${comment.content}</span>
 		</div>
 		<div style="border-top:1px solid black; float:left; width:150; height:130; padding-top:20px">
  	  <span style="font-size:11pt"><fmt:formatDate value="${comment.reg_date}" pattern="yyyy-MM-dd"/></span>
  	  <br>
  	  <br>
  	  <button style="margin-right:30px" class="btn btn-sm btn-primary" id="re${i }">답글</button> <%-- 답글 버튼을 누르면 hover 기능을 통해 답글 창 생성  --%>
  	  <c:if test="${comment.id == sessionScope.sessionID}">
  	  <button style="float:left; margin-left:30px" class="btn btn-sm btn-danger" onclick="window.location='BoardCommentDelete.certi?b_num=${board.num }&c_num=${comment.num}'">삭제</button>
  	  </c:if>
  	</div>
 	</div>
 	
 	<div class="b${i }" position = "static" style=" float:center; width:1000;">
 <form action="BoardReCommentWrite.certi?b_num=${board.num }&c_num=${comment.num}" method="post">
  <div style="border-top:1px dashed black; background-color:#efefef; float:left; width:150; height:130">
 	  <span style="line-height:100px; font-size:14pt">${sessionScope.sessionID}</span>
 </div>
  	<div style="border-top:1px dashed black; background-color:#efefef; float:left; width:750; height:130; padding-top:20px;">
 			<textarea style="height:80" row="50" cols="100" name="content"></textarea>
 		</div>
 	<div style="border-top:1px dashed black; background-color:#efefef; float:left; width:250; height:130; padding-top:20px">
  	  <br>
  	  <input class="btn btn-lg btn-success" style="float:center; align:center" type="submit" value="전송">  	  
  	</div>
 	</form>
 </div>
 	</c:forEach>
 	
 	
 	<div position = "static" style=" float:center; background-color:#0FF;  width:1000;">
 <form action="BoardCommentWrite.certi?num=${board.num }" method="post">
  <div style="border-top:1px dashed black; background-color:#efefef; float:left; width:150; height:130">
 	  <span style="line-height:100px; font-size:14pt">${sessionScope.sessionID}</span>
 </div>
  	<div style="border-top:1px dashed black; background-color:#efefef; float:left; width:700; height:130; padding-top:20px;">
 			<textarea style="height:80" row="50" cols="100" name="content"></textarea>
 		</div>
 	<div style="border-top:1px dashed black; background-color:#efefef; float:left; width:150; height:130; padding-top:20px">
  	  <br>
  	  <input class="btn btn-lg btn-success" style="float:center; align:center" type="submit" value="전송">  	  
  	</div>
 	</form>
 </div>
        	</c:if>				
</html>