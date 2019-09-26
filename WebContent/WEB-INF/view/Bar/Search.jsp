 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
  <header>
    <div class="overlay" style="height:100px; width:1000; background:white;margin:auto;">
    	<a href="/certify/main.certi"><img src="/certify/resource/image/logo_certiTimer.png" style="width:200px; top:10px;left:130px;position:relative; float:left"/></a>
    	<div class="col-md-10 col-lg-8 col-xl-7 mx-auto" style="top:30px; left:130px">
          <form action="/certify/cerinfo/search.certi">
            <div class="form-row">
              <div class="col-12 col-md-9 mb-2 mb-md-0">
                <input type="text" class="form-control form-control-lg" >
              </div>
              <div class="col-12 col-md-3">
                <button type="submit" class="btn btn-block btn-lg btn-primary">검색</button>
              </div>
            </div>
          </form>
        </div>
        
    </div>
  </header>