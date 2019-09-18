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


  <!-- Custom styles for this template -->
  <link href="/certify/resource/gen/css/landing-page.min.css" rel="stylesheet">
</head>
<c:if test="${count==0 }">
<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
									<tr>
										<td align="center">
											<h3>해당하는 게시글이 없습니다.. :(</h3>
										</td>
									</tr>	
							</table>
</c:if>
<c:if test="${count!=0 }">
<div position = "static">
<table class="table table-bordered table-striped" id="dataTable" width="750" cellspacing="0" border="1px">
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
        		<c:if test="${board.newname != null}">
        		<td align="center"  width="300" ><a href="BoardContent.certi?num=${board.num }"> ${board.title} [이미지 있어유]</a></td>
        		</c:if>
        		<c:if test="${board.newname == null}">
        		<td align="center"  width="300" ><a href="BoardContent.certi?num=${board.num }"> ${board.title} [이미지 없어유]</a></td>
        		</c:if>
        		</tr>
        		</c:forEach>
        	</tbody>
        	</table>
        	</c:if>
        	</div>
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
</html>