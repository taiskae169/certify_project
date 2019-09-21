<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery.js"></script>
<!DOCTYPE html>
<html>

<head>

  <title>자격루</title>

  <!-- Bootstrap core CSS -->
  
  
 
  <!-- Custom fonts for this template -->

  <!-- Custom styles for this template -->
   
  
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
 
  

</head>
<jsp:include page="/WEB-INF/view/Bar/Search.jsp"/>

<jsp:include page="/WEB-INF/view/Bar/NavBar.jsp"/>
      
  <jsp:include page="/WEB-INF/view/Bar/SideBar.jsp"/>
  

	<div position="absolute" style="width:1000; height:100%; margin-top:50; margin-left:300; border:1px solid; padding:10; border-radius:10px">
			<div position = "static" style="width:1000; height:50;">
				<div position="static" style="float:left; width:800; height:50; border-bottom:1.5px dashed; display: flex; align-items:flex-end; justify-content:left; padding-left:15">
					<b style="font-size:14pt">${board.title}</b>
				</div>
				
				<div position="static" style="float:left; width:160; height:50; border-bottom:1.5px dashed; display:flex; align-items:flex-end; justify-content:center;">
				<span style="font-size:13pt"><fmt:formatDate value="${board.reg_date }" pattern="yyyy-MM-dd"/></span>
				</div>
			</div>
			
			<div position = "static" style="width:1000; height:50;">
			
				<div position="static" style="float:left; width:750; height:50; display:flex; align-items:center; justify-content:left; padding-left:30">
					<span>${board.id }</span>
				</div>
				<c:if test="${board.newname != null}"></c:if>
				<div class="dropdown" position="static" style="float:left; width:210; height:50; display:flex; align-items:center; justify-content:left">
					
  					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">첨부파일 <span class="caret"></span></a>
  					<ul class="dropdown-menu" role="menu">
                  <li><a href="Download.certi?newname=${board.newname}">${board.newname }</a></li>
                  </ul>
  					 
					</div>				
			</div>
				
				<div position = "static" style="width:1000; height:100%;">
				<div position="static" style="float:left; width:100%; align:center; overflow:scroll; padding-left:30">
					 <span>${board.content }</span>
					 <c:if test="${board.newname != null}">
					 
					    <img src="/certify/imgs/${board.newname}" style="width:800px; height:450px"/>
					    </c:if>
				</div>
				</div>
				
	
			</div>

				
				<div position="static" style="width:1000; height:70; margin-left:330; display:flex; align-items:center; justify-content:center" >
				<c:if test="${board.id == sessionScope.memId }">
				<button class="btn btn-sm btn-primary" onclick="window.location='BoardUpdateForm.certi?num=${board.num}'">수정하기</button>
				<button class="btn btn-sm btn-danger" onclick="window.location='BoardDelete.certi?num=${board.num}'">삭제하기</button>
				</c:if>
				<button class="btn btn-sm btn-primary" onclick="window.location='BoardList.certi?catenum=10'">목록보기</button>
				</div>
				
	

				
				<div position = "static" align="center">
				<jsp:include page="CommentList.jsp"/>
				</div>	
				
				
				
</body>