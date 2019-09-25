<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Theme Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="../../dist/css/bootstrap-theme.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="theme.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  <style>
    .dropbtn {
  background-color: #D9BE82;
  color: white;
  padding: 16px;
  font-size: 14px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 140px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {background-color: #8C7238;}
</style>
  </head>
<body bgcolor="#829FD9"> <!-- hover라 그런지 전체 컬러를 꾸며야 한다 --> 
<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
    <script src="js/bootstrap.min.js"></script>
    
    <div style="position:static; width:100%; height:100%;">
	<div style="position:static; width:100%; height:20%; display:flex; align-items:center; justify-content:center; border-bottom:5px dashed; border-color:white;">
	<p style="font-size:25pt; color:white;">자격증을 선택해주세요</p>	
	</div>
 
  <div class="dropdown">  <!-- 기능사 목록 dropdown으로 표현하기 --> 
  <button class="btn btn-lg btn-default dropbtn">기능사</button>
  <div class="dropdown-content">
      <c:forEach begin="0" end="${certilist0.size()-1 }" step="1" var="i">
    <div style="width:500px; height:40">
   <c:set var="certi" value="${certilist0[i]}"/>
   <a href="InterestTypePro.certi?cre_name=${certi.num }">${ certi.name}</a>
	</div>
	</c:forEach>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">산업기사</button>
  <div class="dropdown-content">
      <c:forEach begin="0" end="${certilist1.size()-1 }" step="1" var="i">
    <div style="width:1000px; height:40">
   <c:set var="certi" value="${certilist1[i]}"/>
   <a href="InterestTypePro.certi?cre_name=${certi.num }">${ certi.name}</a>
	</div>
	</c:forEach>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">기사</button>
  <div class="dropdown-content">
      <c:forEach begin="0" end="${certilist2.size()-1 }" step="1" var="i">
    <div style="width:500px; height:40">
   <c:set var="certi" value="${certilist2[i]}"/>
   <a href="InterestTypePro.certi?cre_name=${certi.num }">${ certi.name}</a>
	</div>
	</c:forEach>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">기술사</button>
  <div class="dropdown-content">
      <c:forEach begin="0" end="${certilist3.size()-1 }" step="1" var="i">
    <div style="width:500px; height:40">
   <c:set var="certi" value="${certilist3[i]}"/>
   <a href="InterestTypePro.certi?cre_name=${certi.num }">${ certi.name}</a>
	</div>
	</c:forEach>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">기능장</button>
  <div class="dropdown-content">
      <c:forEach begin="0" end="${certilist4.size()-1 }" step="1" var="i">
    <div style="width:500px; height:40">
   <c:set var="certi" value="${certilist4[i]}"/>
   <a href="InterestTypePro.certi?cre_name=${certi.num }">${ certi.name}</a>
	</div>
	</c:forEach>
  </div>
</div>


<div class="dropdown">
  <button class="dropbtn">국가전문</button>
  <div class="dropdown-content">
      <c:forEach begin="0" end="${certilist5.size()-1 }" step="1" var="i">
    <div style="width:500px; height:40">
   <c:set var="certi" value="${certilist5[i]}"/>
   <a href="InterestTypePro.certi?cre_name=${certi.num }">${ certi.name}</a>
	</div>
	</c:forEach>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">민간자격</button>
  <div class="dropdown-content">
      <c:forEach begin="0" end="${certilist6.size()-1 }" step="1" var="i">
    <div style="width:500px; height:40">
   <c:set var="certi" value="${certilist6[i]}"/>
   <a href="InterestTypePro.certi?cre_name=${certi.num }">${ certi.name}</a>
	</div>
	</c:forEach>
  </div>
</div>

<div class="dropdown">
  <button class="dropbtn">어학자격</button>
  <div class="dropdown-content">
      <c:forEach begin="0" end="${certilist7.size()-1 }" step="1" var="i">
    <div style="width:500px; height:40">
   <c:set var="certi" value="${certilist7[i]}"/>
   <a href="InterestTypePro.certi?cre_name=${certi.num }">${ certi.name}</a>
	</div>
	</c:forEach>
  </div>
</div>

</body>
</html>

