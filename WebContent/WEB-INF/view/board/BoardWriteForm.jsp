<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery.js"></script>
<!DOCTYPE html>
<html>

<head>

<script type="text/javascript" src="<%=application.getContextPath()%>/ckeditor/ckeditor.js" charset="UTF-8">
	CKEDITOR.replace('editor1');
</script>

  <title>자격루</title>

  <!-- Bootstrap core CSS -->
  
  
 
  <!-- Custom fonts for this template -->

  <!-- Custom styles for this template -->
   
  
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
 
  

</head>
<body>
<jsp:include page="/WEB-INF/view/Bar/Search.jsp"/>

<jsp:include page="/WEB-INF/view/Bar/NavBar.jsp"/>
      
  <jsp:include page="/WEB-INF/view/Bar/SideBar.jsp"/>
  
<form action="BoardWritePro.certi" method="post" enctype="multipart/form-data">
	<div style="position:static; width:1060; height:100%; margin-top:50; margin-left:300; border:1px solid; padding:10; border-radius:10px">
			<div style="width:1060; height:50;">
				<div style="float:left; width:160; height:50; border-bottom:1.5px dashed; display: flex; align-items:flex-end; justify-content:left; padding-left:15">
					<b style="font-size:14pt">게시판</b>
				</div>
				
				<div position="static" style="float:left; width:800; height:50; border-bottom:1.5px dashed; display:flex; align-items:flex-end;">
				<c:forEach begin="0" end="${catelist.size()-1}" step="1" var="i"> <%-- 카테고리 이름과 넘버 가져오기  --%>
				<c:set var="cate" value="${catelist[i]}"/>
				<input type="radio" name="cate" value="${cate.num }">${cate.value}
				</c:forEach>
			</div>
			
			
			<div position = "static" style="width:1060; height:50;">
			
				<div position="static" style="float:left; width:210; height:50; display:flex; align-items:center; justify-content:left; padding-left:30">
					<b style="font-size:14pt">제목</b>
				</div>
				<div class="dropdown" position="realative" style="float:left; width:750; height:50; display:flex; align-items:center; justify-content:left">
					
  					<input type="text" name="title" id="title">
  					 
					</div>				
			</div>
			
			<div position = "static" style="width:1060; height:50;">
			
				<div position="static" style="float:left; width:210; height:50; display:flex; align-items:center; justify-content:left; padding-left:30">
					<b style="font-size:14pt">파일첨부</b>
				</div>
				<div class="dropdown" position="static" style="float:left; width:750; height:50; display:flex; align-items:center; justify-content:left">
					
  					<input multiple="multiple" type="file" name="save" />
  					 
					</div>				
			</div>
				
				<div position = "static" style="width:1000; height:100%;">
				<div position="static" style="float:left; width:100%; align:center; overflow:scroll; padding-left:30">
					<textarea id="editor1" class="ckeditor" name="content" rows="20" cols="50" width="960"></textarea> <%-- checkeditor api 사용하기 --%>
				</div>
				</div>
				
				
			
	
			</div>
			
		

				
				<div position="static" style="width:1000; height:70; margin-left:330; display:flex; align-items:center; justify-content:center" >
				<div position="absolute" style="width:1000; height:70; display:flex; align-items:center; justify-content:center" >
				<input class="btn btn-sm btn-primary" type="submit" value="전송"> <button class="btn btn-sm btn-primary" style="margin-left:30px" onclick="window.location='BoardList.certi?catenum=10'">목록보기</button>
				</div>
					</form>
				
				</div>
				
				
	
	</body>
	</html>
	
	<script src="/certify/resource/gen/vendor/jquery/jquery.min.js"></script>
  <script src="/certify/resource/gen/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>