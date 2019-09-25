<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

  <nav class="navbar navbar-default" style="width:100%;">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/certify/main.certi">자격루</a>
          </div>
          <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">

              <li class="active"><a href="/certify/main.certi">Home</a></li> <!-- 홈페이지로 가기 -->
              <li><a href="/certify/BoardList.certi?catenum=10">게시판</a></li> <!-- 게시판 페이지로 가기 -->
              <li><a href="javascript:InterestMain();">관심자격증</a></li> <!-- 관심자격증창 띄우기 -->
              <li><a href="javascript:openlist();"">시험일정</a></li> <!-- 시험일정 창띄우기 -->
              <li><a href="/certify/certi/certi_sc_session1.certi?type=1">응시자격 자가진단</a></li>
           		<!-- 응시자격 페이지로 가기 -->

              <li class="active"><a href="main.certi">Home</a></li>
              <li><a href="/certify/BoardList.certi?catenum=10">게시판</a></li>
              <li><a href="/certify/certi/certi_sc_session1.certi?type=1">응시자격검사</a></li>

              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Action</a></li>
                  <li><a href="#">Another action</a></li>
                  <li><a href="#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="dropdown-header">Nav header</li>
                  <li><a href="#">Separated link</a></li>
                  <li><a href="#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </nav>
      <script type="text/javascript">
    		console.log('tst' + '${sessionID}');
    		console.log('${sessionID}');
    			function openlist(){
        			console.log('${sessionID}');
					var sessionID = '${sessionID}';
        			if(sessionID){
        				window.open('http://localhost:8080/certify/user/mp/callist.certi', '리스트',"toolbar=no, menubar=no, scrollbars=no");
            			}else{
                			alert('로그인이 필요합니다.');
                		}
    				
        			}
    			function InterestMain(){  
    			    window.open("/certify/InterestMain.certi", "검색새창", "width=600, height=700, toolbar=no, menubar=no, scrollbars=no" );  
    			} 
				
			</script>