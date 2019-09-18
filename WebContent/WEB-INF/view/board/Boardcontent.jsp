<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div position = "static">
			<table cellSpacing=1 cellPadding=1 width="80%" border=1 align="center">
				<tr align="center" height="40">
					<td align="center"><b>글번호</b></td>
						<td align="center"  >${board.num }</td>
					    <td align="center" style="color:; font-size:17px;"><b>작성자</b></td>
					    <td align="center" > ${board.id }</td>
					    <td align="center" style="color:; font-size:17px;"><b>작성일</b></td>
					    <td align="center" > ${board.reg_date }</td>
				 	 </tr>	
					<tr height="40">
					   	<td align="center" style="color:; font-size:17px;"><b>제목</b></td>
					    <td align="left"  colspan="7" ><b>${board.title}</b></td>
				 	 </tr>
				 	 <tr style="height:auto;">
				 	 		<td align="center" style="color:; font-size:17px;"><b>내용</b></td>
					    <td  align="left" valign="top" colspan="8" id="contentarea"><span> ${board.content }</span> <br/>
					    <c:if test="${board.newname != null}">
					    <img src="/Bong/imgs/${board.newname}"/>
					    </c:if>
					    </td>
				 	 </tr >	 	 
				 	 <tr style="height:auto;">
				 	 	<td>첨부파일</td>
				 	 	<c:if test="${board.newname == null}">
					    <td  align="left" valign="top" colspan="7" >
					    		<span style="color:gray;">첨부파일 없음</span>
					    		</c:if>
					    <c:if test="${board.newname != null}">
					  		 	 	
								<td align="left"><button onclick="window.location='Download.certi?newname=${board.newname}'">다운로드</button></td>
						</c:if>
				 	 </tr>
				</table>
				</div>
				<div position = "static" align="center">
				<button class="btn btn-sm btn-primary" onclick="window.location='BoardUpdateForm.certi?num=${board.num}'">수정하기</button>
				<button class="btn btn-sm btn-primary" onclick="window.location='BoardList.certi'">목록보기</button>
				<button class="btn btn-sm btn-danger" onclick="window.location='BoardDelete.certi?num=${board.num}'">삭제하기</button>
				</div>
				<div position = "static" align="center">
				<jsp:include page="CommentList.jsp"/>
				</div>
				
				
				
</body>