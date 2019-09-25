<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//code.jquery.com/jquery.js"></script>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>자격루</title>

  <!-- Bootstrap core CSS -->
  
  

  <!-- Custom fonts for this template -->

  <!-- Custom styles for this template -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
  
  

</head>

<body>

  <!-- Navigation -->


  <!-- Masthead -->
	<jsp:include page="/WEB-INF/view/Bar/Search.jsp"/>
  
  
  	<jsp:include page="/WEB-INF/view/Bar/NavBar.jsp"/>
      
      <jsp:include page="/WEB-INF/view/Bar/SideBar.jsp"/>
        
<c:if test="${count==0 }"> <%-- 글 존재여부 확인  --%>
<table class="table table-bordered" id="dataTable" width="70%" cellspacing="0">
									<tr>
										<td align="center">
											<h3>해당하는 게시글이 없습니다.. :(</h3>
										</td>
									</tr>	
							</table>
</c:if>
<c:if test="${count!=0 }">
<div style="width:70%; float:left">
<table class="table table-bordered table-striped" id="dataTable" width="70%" cellspacing="0" border="1px">
			<thead>
				<tr height="40"  style="font-size:20px">
					<td align = "center" width="75">번호</td>
					<td align = "center" width="100">작성자</td>
					<td align = "center" width="300">제목</td>
					
				</tr>
			</thead>
        	<tbody>
        		<c:forEach begin="0" end="${boardlist.size()-1}" step="1" var="i">
        		<c:set var="board" value="${boardlist[i]}"/>
        		<tr height="30">
        		<td align="center"  width="75" > ${board.num}</td>
        		<td align="center"  width="100" > ${board.id}</td>
        		<td align="center"  width="300" ><a href="BoardContent.certi?num=${board.num }"> ${board.title}</a></td>
        		</tr>
        		</c:forEach>
        	</tbody>
        	</table>
        	</c:if>
        	
        	
        	<div position = "static" align="center">
        	<c:if test="${count>0 }">
        	
      <c:if test="${startPage>10}">
         <div align="center"><a href="BoardList.certi?pageNum=${startPage - 10}">[이전]</a></div>
      </c:if>
      <c:forEach begin="${startPage}" end="${endPage }" step="1" var="i">
         <div align="center"><a href="BoardList.certi?pageNum=${i}">[${i}]</a></div>
       </c:forEach>
      <c:if test="${endPage < pageCount}">
         <div align="center"><a href="BoardList.certi?pageNum=${startPage + 10}">[다음]</a></div>
      </c:if>
   </c:if>
   </div>
   <div position = "static" align ="center">
   <button class="btn btn-sm btn-primary" onclick="window.location='BoardWriteForm.certi'">글쓰기</button>
   </div>
   </div>	
   </body>				
</html>