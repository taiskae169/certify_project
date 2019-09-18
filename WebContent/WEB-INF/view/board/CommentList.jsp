<%@ page language="java"  contentType="text/html; charset=euc-kr"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>자격루</title>

  <!-- Bootstrap core CSS -->
  <link href="/certify/resource/gen/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/certify/resource/gen/vendor/fontawesome-free/css/all.min.css" rel="stylesheet">
  <link href="/certify/resource/gen/vendor/simple-line-icons/css/simple-line-icons.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
 
 
 <c:forEach begin="0" end="${Clist.size()-1}" step="1" var="i">
	<style>
	
      p.a${i} {
        cursor: pointer;
        font-weight: bold;
      }
      div.b${i} {
      	display: none;
      }
    </style>

  <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
  <script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
  
  
<script>
$(document).ready( function() {
	var i = ${i}
	
	  $("div.b"+i).css("display",'none');
  $( 'p.a'+i ).click( function() {
    $( 'div.b'+i ).toggle( 'slow' );
  });
});
</script>

</c:forEach>
  <!-- Custom styles for this template -->
  <link href="/certify/resource/gen/css/landing-page.min.css" rel="stylesheet">
</head>
<c:if test="${count==0 }">
<div position = "static" style=" float:center; background-color:#0FF;  width:1200;">
  <div style=" float:left; background-color:#0FC; width:20%; height:20">
 	작성자
 </div>
  <div float="left" style="border:1px">
  	<div style=" float:left; background-color:red; width:80%; height:20">
  	 hello
  	</div>
 	</div>
 </div>
 
 <div position = "static" style=" float:center; background-color:#0FF;  width:1200;">
  <div style=" float:left; background-color:#0FC; width:20%; height:100">
 	<h3>해당하는 댓글이 없습니다</h3>
 </div>
 </div>
 
 <div position = "static" style=" float:center; background-color:#0FF;  width:1200;">
 <form action="BoardCommentWrite.certi?num=${board.num }" method="post">
  <div style=" float:left; background-color:#0FC; width:20%; height:100">
 	 <p>${sessionScope.memId}<p>
 </div>
  <div float="left" style="border:1px">
  	<div style=" float:left; background-color:red; width:80%; height:20">
  	<input type="submit" value="전송">
  	</div>
  	<div style="float:left; background-color:#0FF; width:80%; height:80">
 			<textarea row="50" cols="100" name="content"></textarea>
 		</div>
 	</div>
 	</form>
 </div>
 
 
</c:if>
<c:if test="${count!=0 }">

 <div position = "static" style=" float:center; background-color:#0FF;  width:1200;">
  <div style=" float:left; background-color:#0FC; width:20%; height:20">
 	작성자
 </div>
  <div float="left" style="border:1px">
  	<div style=" float:left; background-color:red; width:80%; height:20">
  	 hello
  	</div>
 	</div>
 </div>
 
 <c:forEach begin="0" end="${Clist.size()-1}" step="1" var="i">
 <c:set var="comment" value="${Clist[i]}"/>
 <div position = "static" style=" float:center; background-color:#0FF;  width:1200;">
 	
 	<div style=" float:left; background-color:#0FC; width:20%; height:100">
 	<span>${comment.id}</span>
 </div>
 <div float="left" style="border:1px">
  	<div style=" float:left; background-color:red; width:80%; height:20; align:right">
  	  <p class="a${i }" style="align:right">답글</p>
  	</div>
  		<div style="float:left; background-color:#0FF; width:80%; height:80">
 			<span>${comment.content}
 				<p class="b${i }">Lorem ipsum dolor.</p>
 			</span>
 		</div>
 	</div>
 	</div>
 	<div class="b${i }" position = "static" style=" float:center; background-color:#0FF;  width:1200;">
 <form action="BoardCommentWrite.certi?num=${board.num }" method="post">
  <div style=" float:left; background-color:#0FC; width:20%; height:100">
 	 <p>${sessionScope.memId}<p>
 </div>
  <div float="left" style="border:1px">
  	<div style=" float:left; background-color:red; width:80%; height:20; align:right">
  	<input style="float:right; align:right" type="submit" value="전송">
  	</div>
  	<div style="float:left; background-color:#0FF; width:80%; height:80">
 			<textarea style="height:80" row="80" cols="120" name="content"></textarea>
 		</div>
 	</div>
 	</form>
 </div>
 	</c:forEach>
 	<div position = "static" style=" float:center; background-color:#0FF;  width:1200;">
 <form action="BoardCommentWrite.certi?num=${board.num }" method="post">
  <div style=" float:left; background-color:#0FC; width:20%; height:100">
 	 <p>${sessionScope.memId}<p>
 </div>
  <div float="left" style="border:1px">
  	<div style=" float:left; background-color:red; width:80%; height:20; align:right">
  	<input style="float:right; align:right" type="submit" value="전송">
  	</div>
  	<div style="float:left; background-color:#0FF; width:80%; height:80">
 			<textarea style="height:80" row="80" cols="120" name="content"></textarea>
 		</div>
 	</div>
 	</form>
 </div>
        	</c:if>				
</html>