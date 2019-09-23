<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    

<div class="col-sm-3 col-lg-2 sidebar" style="float:left; background-color:#1B578C; margin-right:20px; height:700px; border-bottom-left-radius:5px; border-bottom-right-radius:5px;">
          <ul class="nav nav-sidebar">
           <li><a href="BoardList.certi?catenum=10" style="color:white; font-size:13pt">전체게시판</a></li>
          <c:forEach begin="0" end="${catelist.size()-1}" step="1" var="i">
          <c:set var="cateboard" value="${catelist[i] }" />
          	 <li><a href="BoardList.certi?catenum=${cateboard.num }" style="color:white; font-size:13pt">${cateboard.value }게시판</a></li>
          </c:forEach>
          </ul>
        </div>