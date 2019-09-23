<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <script src="//code.jquery.com/jquery.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<button id="loadListd">불러오기</button>
	
	<pre id="content"></pre>
	
	
	<form action="/certify/user/mp/caldelete.certi" method="POST" id="calbox">
		<table class="table table-bordered">
			<thead>
					<th><input type="checkbox" onclick="checkAllDel(this.checked)" /></th>
					<th>이벤트 이름</th>
					<th>날짜</th>
					<th>알람 방식</th>
					<th>알람 시간</th>
			</thead>
			<tbody id="cal_body">
			
			</tbody>

		</table>
	</form>
	<button class='btn btn-sm btn-warning' id="loadList">일정불러오기</button>
	<button class='btn btn-sm btn-warning' onclick='calendarModifyForm()'>일정 수정</button>
	<button class='btn btn-sm btn-warning' onclick='calendarRemoveCheck()'>삭제</button>
	
	
	
<script type="text/javascript">
	var CLIENT_ID = '74093674387-j64supuapk07470j07hso4fc9fuufb1g.apps.googleusercontent.com';
	var API_KEY = 'AIzaSyDoo2SNaod2Uze___-WlVqrV9x3MKReLCs';

	var DISCOVERY_DOCS = ["https://www.googleapis.com/discovery/v1/apis/calendar/v3/rest"];

	var SCOPES = "https://www.googleapis.com/auth/calendar";
	var loadButton = document.getElementById('loadList');
	
	//범위
	
	function handleClientLoad(){
		console.log("load시작");
		
		gapi.load('client:auth2', initClient);
	}

	function initClient(){
		console.log("init 시작");
		gapi.client.init({
			apiKey:API_KEY,
			clientId:CLIENT_ID,
			discoveryDocs:DISCOVERY_DOCS,
			scope:SCOPES
		}).then(function(){
			gapi.auth2.getAuthInstance().isSignedIn.listen(updateSigninStatus);
			//isSignedIn.listen()란 로그인 상태를 받을 함수를 넣으면 된다.
			//isSignedIn.listen(updateSigninStatus);일시 updateSigninStatus 함수 안에 로그인 상태가 들어가게 된다
			//로그인 상태일 시 true, 아닐 시 false
		
			updateSigninStatus(gapi.auth2.getAuthInstance().isSignedIn.get());
			//authorizeButton.onclick = handleAuthClick;
			//signoutButton.onclick = handleSignoutClick;
			//버튼을 클릭했을 때 작동되도록 설정되어있음
			loadButton.onclick=handleAuthClick;
			},function(error){
				setList(JSON.stringify(error,null,2));
		});
	}//initClient 함수 종료

	function handleAuthClick(event){
		gapi.auth2.getAuthInstance().signIn();
	}

	function updateSigninStatus(isSignedIn){
		if(isSignedIn){
			listUpcomingEvents();
		}else{
			setList('Need Load Calendar List');
		}
	}//updateSigninStatus 함수 종료
	
	function setList(message){
		var pre = document.getElementById('content');
		var textContent = document.createTextNode(message + "\n");
		pre.appendChild(textContent);
		
	}//setList 함수 종료

	function listUpcomingEvents(){
		console.log('listupcoming 시작');
		gapi.client.calendar.events.list({
			'calendarId' : 'primary',
			'timeMin' : (new Date()).toISOString(),
			'showDeleted' : false,
			'singleEvents': true,
			'maxResults' : 20,
			'orderBy' : 'startTime'
		}).then(function(response){
			console.log('불러오기 시작');
			var events = response.result.items;

			if(events.length>0){
				for(i=0; i<events.length;i++){
					var event = events[i];
					var when = event.start.date;
					
					var che = /^시험일정/;
					var alarm = event.reminders.overrides;
					if(che.test(event.summary)){
						console.log('이름 테스트 성공');
						setList(event.summary+'('+when +')' + event.id +'\n');
						for(a=0; a<alarm.length;a++){
							var al = alarm[a];
							var time = al.minutes;
							var day =0;
							setList('알람목록 : ' + al.method + '/'+al.minutes );

							//테이블 정보 추가하는 로직
							var cal_body = document.getElementById('cal_body');
							var row = cal_body.insertRow(cal_body.rows.length);
							var cell0=row.insertCell(0);
							var cell1=row.insertCell(1);
							var cell2=row.insertCell(2);
							var cell3=row.insertCell(3);
							var cell4=row.insertCell(4);
							cell0.innerHTML= '<input type="checkbox" name="chkVal" value="'+ event.id+'" />';
							cell1.innerHTML= event.summary +'\n'+ '<input type="hidden" name="summarys" value=" ' + event.summary+'" /> ';
							cell2.innerHTML= when +'\n'+ '<input type="hidden" name="whens" value=" ' + when +'" /> ';
							if(al.method=='popup'){
								var alr = '알림';
							}else{
								var alr = '메일';
							}
							cell3.innerHTML= alr  +'\n'+ '<input type="hidden" name="alrs" value=" ' + al.method+'" /> ';
							
							
							minute = time/60;
							while(true){
								if(minute>0){
									minute= minute-24;
									day= day+1;
								}else{
									minute= minute*-1;
									break;
								}
							}
							cell4.innerHTML= day + '일 전 ' + minute +'시' +'\n'+ '<input type="hidden" name="days" value="' + day+'" /> '+'\n'+ '<input type="hidden" name="minutes" value=" ' + minute+'" /> ';
							
						}
						//setList(event.summary+'('+when +')' + '\n');
					}else{
						console.log('이름 테스트 실패');
					}
					
					
				}
			}else{
				setList('No upcoming events found');

			}
			var chkVal = document.getElementsByName('chkVal');
			if(chkVal.length==0){
				console.log('테스트 성공');
				var cal_body = document.getElementById('cal_body');
				var row = cal_body.insertRow(cal_body.rows.length);
				var cell0=row.insertCell(0);
				var cell1=row.insertCell(1);
				var cell2=row.insertCell(2);
				var cell3=row.insertCell(3);
				var cell4=row.insertCell(4);

				cell1.innerHTML = '일정이 없습니다.';
			}
		});
	}

	function calendarRemoveCheck(){
		var chkVal = document.getElementsByName('chkVal');
		var n =0;
		for(var i=0; i<chkVal.length; i++){
			if(chkVal[i].checked ==true){
				console.log(chkVal[i].value);
				calendarRemove(chkVal[i].value);
				
			}
		}
		alert('삭제되었습니다.');
		location.reload();
	}

	function calendarRemove(id){
		gapi.client.calendar.events.delete({
			'calendarId' : 'primary',
			'eventId' : id
		}).then(function(){

		});
	}

	function checkAllDel(bool){
		var chkVal = document.getElementsByName('chkVal');
		for(var i =0; i<chkVal.length;i++){
			chkVal[i].checked = bool;
		}
	}

	function calendarModifyForm(){
		var chkVal = document.getElementsByName('chkVal');
		var summarys = document.getElementsByName('summarys');
		var alrs = document.getElementsByName('alrs');
		var days = document.getElementsByName('days');
		var minutes = document.getElementsByName('minutes');
		var n=0;
		var summary='';
		var alr='';
		var day='';
		var minute='';
		console.log('chkval length : ' + chkVal.length);
		for(var i=0; i<chkVal.length;i++){
			console.log('check0' + chkVal[0].checked);
			console.log('check1' + chkVal[1].checked);
			
			if(chkVal[i].checked==true){
				console.log('i :'+ i);
				n++;
				summary= summarys[i].value;
				alr=alrs[i].value;
				day=days[i].value;
				minute=minutes[i].value;
				console.log('n : ' + n);
			}
		}
		if(n==1){
			$('#modiSummary').val(summary);
			if(alr=='popup'){
				$('#modiMethod').val('popup').prop('selected',true);
			}else{
				$('#modiMethod').val('mail').prop('selected',true);
			}
			if(day==1){
				$('#modiDay').val('1').prop('selected',true);
			}else{
				$('#modiDay').val('2').prop('selected',true);
			}
			for(var a=0;a<23;a++){
				if(a==minute){
					$('#modiminute').val(a).prop('selected',true);
				}
			}
		}else if(n>1){
			alert('1개만 선택해 주세요');
			return false;
		}else{
			alert('선택한 항목이 없습니다.');
			return false;
		}
		$('#calendarModifyForm').modal('show');
	}

	function modifyEvent(eventId){
		gapi.client.calendar.events.update({
			'calendarId' : 'primary',
			'eventId' : eventId 
		}).then(function(){})
	}
</script>

    <script async defer src="https://apis.google.com/js/api.js"
      onload="this.onload=function(){};handleClientLoad()"
      onreadystatechange="if (this.readyState === 'complete') this.onload()">
    </script>
    
    <div class="modal fade in" id="calendarModifyForm" role="dialog">
    	<div class='modal-dialog'>
    		<div class='modal-content'>
    			<div class='modal-header'>
    				<button type='button' class='close' data-dismiss='modal'>x</button>
    				<h4 class='modal-title'>이벤트 수정</h4>
    			</div><!-- header 종료 -->
    			<div class='modal-body'>
    				<form>
    					<label for='modiSummary'>이벤트 이름</label>
    					<div class='form-group'>
    						<input class='form-control' type='text' id='modiSummary' />
    						<!-- 이벤트 이름 입력 창 -->
    					</div><!-- group 종료 -->
    					<div class='form-group'>
    						<label for='modiMethod'>알람</label>
    						<select class='form-control' id='modiMethod'>
    							<option value='popup'>알림</option>
    							<option value='mail'>메일</option>
    						</select>
    						<label for='modiDay'>시간</label>
    						<select id='modiDay' class='form-control'>
    							<option value='1'>1일전</option>
    							<option value='2'>2일전</option>
    						</select>
    						<select id='modiminute' class='form-control'>
    							<c:forEach var='i' begin='0' end='23' step='1'>
    								<option value=${23-i } >${23-i }시</option>
    							</c:forEach>
    						</select>

    					</div><!-- group 종료 -->
    					
    				</form>
    				<button onclick="modifyEvent();" class='form-control'>수정</button>
    			</div><!-- body 종료 -->
    		</div><!-- content 종료 -->
    	</div><!-- dialog 종료 -->
    </div><!-- 모달 종료 -->


</body>
</html>