<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8">
        <!-- meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/ -->
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>��Ÿ�</title>
        <!-- Bootstrap -->
        
        <link href="/resources/image/icon/HalfLife.ico" rel="shortcuticon">
        <!-- jQuery (��Ʈ��Ʈ���� �ڹٽ�ũ��Ʈ �÷������� ���� �ʿ���) -->	
        <script src="//code.jquery.com/jquery.js"></script>
        <!-- ��� ������ �÷������� �����ϰų� (�Ʒ�) �ʿ��� ������ ���ϵ��� �����ϼ��� -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
        <!-- Respond.js ���� IE8 ���� ������ ����� Ȱ��ȭ�ϼ��� (https://github.com/scottjehl/Respond) -->
        <script src="/resource/bootstrap/js/respond.js"></script>
        
        <!-- ��� CSS �߰� -->
       
        

    </head>
    <body>
        <div class="container"><!-- �¿����� ���� Ȯ�� -->
            <!-- ���â -->
            <div class="modal fade in" id="defaultModal">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">��</button>
                            <h4 class="modal-title">�˸�</h4>
                        </div>
                        <div class="modal-body">
                            <p class="modal-contents"></p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">�ݱ�</button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
            </div><!-- /.modal -->
            <!--// ���â -->
                <!-- ���� ���� �κ� -->
                 
 		<hr />
 
        <form class="form-horizontal" role="form" method="get" action="/certify/user/signup.certi" >
            <div class="form-group">
                <label for="provision" class="col-lg-2 control-label">ȸ�����Ծ��</label>
                <div class="col-lg-10" id="provision">
                    <textarea class="form-control" rows="8" style="resize:none" readonly="readonly">
�������
                    </textarea>
                    <div class="radio">
                        <label>
                            <input type="radio" id="provisionYn" name="provisionYn" value="Y" autofocus="autofocus" checked>
                            �����մϴ�.
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" id="provisionYn" name="provisionYn" value="N" checked>
                            �������� �ʽ��ϴ�.
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="memberInfo" class="col-lg-2 control-label">����������޹�ħ</label>
                <div class="col-lg-10" id="memberInfo">
                    <textarea class="form-control" rows="8" style="resize:none" readonly="readonly">
���������� �׸� �� �������
                    </textarea>
                    <div class="radio">
                        <label>
                            <input type="radio" id="memberInfoYn" name="memberInfoYn" value="Y" >
                            �����մϴ�.
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" id="memberInfoYn" name="memberInfoYn" value="N" checked>
                            �������� �ʽ��ϴ�.
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group" id="divId">
                <label for="inputId" class="col-lg-2 control-label">���̵�</label>
                <div class="col-lg-10">
                    <input type="email" class="form-control" id="email" name="id" data-rule-required="true" placeholder="�̸���" maxlength="40" value="${userinfo.id}">
                	<button type="button" class="btn" onclick="openIdChk();">�ߺ�Ȯ��</button>
                	<input type="hidden" id="kakaoId" name="kakaoId" value="${userinfo.kakaoId}">
                	<input type="hidden" id="naverId" name="naverId" value="${userinfo.naverId}">
                	<input type="hidden" id="googleId" name="googleId" value="${userinfo.googleId}">
                	<input type="hidden" id="idchecked" value="0">
                </div>
            </div>
            <div class="form-group" id="divPassword">
                <label for="inputPassword" class="col-lg-2 control-label">�н�����</label>
                <div class="col-lg-10">
                    <input type="password" class="form-control" id="password" name="pw" data-rule-required="true" placeholder="�н�����" maxlength="30">
                </div>
            </div>
            <div class="form-group" id="divPasswordCheck">
                <label for="inputPasswordCheck" class="col-lg-2 control-label">�н����� Ȯ��</label>
                <div class="col-lg-10">
                    <input type="password" class="form-control" id="passwordCheck" data-rule-required="true" placeholder="�н����� Ȯ��" maxlength="30">
                </div>
            </div>
            <div class="form-group" id="divName">
                <label for="inputName" class="col-lg-2 control-label">�̸�</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control onlyHangul" id="name" name="name" data-rule-required="true" placeholder="�ѱ۸� �Է� �����մϴ�." maxlength="15" value="${userinfo.name}">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPhoneNumber" class="col-lg-2 control-label">�������</label>
                <div class="col-lg-10">
                    <input type="date" class="form-control" id="dirth" name="birth" data-rule-required="true">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPhoneNumber" class="col-lg-2 control-label">����ī�װ�</label>
                <div class="col-lg-10">
                    <select class="form-control" id="wana" name="wana">
                    	<c:forEach var="cate" items="${category}">
                    		<option value="${cate.certi_num}">${cate.name}</option>
                    	</c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPhoneNumber" class="col-lg-2 control-label">�����ڰ�</label>
                <div class="col-lg-10">
                    <select class="form-control" id="qual" name="qual">
                        <option value="0">��ɻ�</option>
                        <option value="1">������</option>
                        <option value="2">���</option>
                        <option value="3">�����</option>
                        <option value="4">�����</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button type="submit" class="btn btn-default">Sign in</button>
                </div>
            </div>
        </form>
         

         
         
         
        <script>
        	function openIdChk(){
        		var modalContents = $(".modal-contents");
                var modal = $("#defaultModal");
                var divId = $('#divId');
            	$.ajax({
                	type:'POST',
                    url:'/certify/user/idcheck.certi',
                    data:{
                        "email":$('#email').val()
                    },
                    success:function(data){
						if($.trim(data)=="YES"){
							if($('email').val()!=''){
								console.log('idüũ');
								modalContents.text("��밡���� ���̵��Դϴ�.");
								modal.modal('show');
								$("#idchecked").val("1");
								
							}
						}//if��
						else if($.trim(data)=="BLOCK"){
							modalContents.text("���̵� �Է����ּ���");
							modal.modal('show');
							divId.removeClass("has-success");
	                        divId.addClass("has-error");
	                        $("#idchecked").val("0");
						}
						else{
							if($('#email').val()!=''){
								modalContents.text("�ߺ��� ���̵��Դϴ�.");
								modal.modal('show');
								divId.removeClass("has-success");
		                        divId.addClass("has-error");
		                        $("#idchecked").val("0");
							}
						}//else�� ��
                    }//success:function ��    
                })//ajax ��
            }//openIdChk ��
         
            $(function(){
                //����� ���������� ����
                var modalContents = $(".modal-contents");
                var modal = $("#defaultModal");
                 
                $('.onlyAlphabetAndNumber').keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val($(this).val().replace(/[^_a-z0-9]/gi,'')); //_(underscore), ����, ���ڸ� ����
                    }
                });
                 
                $(".onlyHangul").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[a-z0-9]/gi,''));
                    }
                });
             
                $(".onlyNumber").keyup(function(event){
                    if (!(event.keyCode >=37 && event.keyCode<=40)) {
                        var inputVal = $(this).val();
                        $(this).val(inputVal.replace(/[^0-9]/gi,''));
                    }
                });
                 
                //------- �˻��Ͽ� ���¸� class�� ����
                $('#id').keyup(function(event){
                     
                    var divId = $('#divId');
                     
                    if($('#id').val()==""){
                        divId.removeClass("has-success");
                        divId.addClass("has-error");
                    }else{
                        divId.removeClass("has-error");
                        divId.addClass("has-success");
                    }
                });
                 
                $('#password').keyup(function(event){
                     
                    var divPassword = $('#divPassword');
                     
                    if($('#password').val()==""){
                        divPassword.removeClass("has-success");
                        divPassword.addClass("has-error");
                    }else{
                        divPassword.removeClass("has-error");
                        divPassword.addClass("has-success");
                    }
                });
                 
                $('#passwordCheck').keyup(function(event){
                     
                    var passwordCheck = $('#passwordCheck').val();
                    var password = $('#password').val();
                    var divPasswordCheck = $('#divPasswordCheck');
                     
                    if((passwordCheck=="") || (password!=passwordCheck)){
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                });
                 
                $('#name').keyup(function(event){
                     
                    var divName = $('#divName');
                     
                    if($.trim($('#name').val())==""){
                        divName.removeClass("has-success");
                        divName.addClass("has-error");
                    }else{
                        divName.removeClass("has-error");
                        divName.addClass("has-success");
                    }
                });
                 
                $('#nickname').keyup(function(event){
                     
                    var divNickname = $('#divNickname');
                     
                    if($.trim($('#nickname').val())==""){
                        divNickname.removeClass("has-success");
                        divNickname.addClass("has-error");
                    }else{
                        divNickname.removeClass("has-error");
                        divNickname.addClass("has-success");
                    }
                });
                 
                $('#email').keyup(function(event){
                     
                    var divEmail = $('#divEmail');
                     
                    if($.trim($('#email').val())==""){
                        divEmail.removeClass("has-success");
                        divEmail.addClass("has-error");
                    }else{
                        divEmail.removeClass("has-error");
                        divEmail.addClass("has-success");
                    }
                });
                 
                $('#phoneNumber').keyup(function(event){
                     
                    var divPhoneNumber = $('#divPhoneNumber');
                     
                    if($.trim($('#phoneNumber').val())==""){
                        divPhoneNumber.removeClass("has-success");
                        divPhoneNumber.addClass("has-error");
                    }else{
                        divPhoneNumber.removeClass("has-error");
                        divPhoneNumber.addClass("has-success");
                    }
                });
                 
                 
                //------- validation �˻�
                $( "form" ).submit(function( event ) {
                     
                    var provision = $('#provision');
                    var memberInfo = $('#memberInfo');
                    var divId = $('#divId');
                    var divPassword = $('#divPassword');
                    var divPasswordCheck = $('#divPasswordCheck');
                    var divName = $('#divName');
                    var divNickname = $('#divNickname');
                    var divEmail = $('#divEmail');
                    var divPhoneNumber = $('#divPhoneNumber');
                     
                    //ȸ�����Ծ��
                    
                    if($('#provisionYn:checked').val()=="N"){
                        modalContents.text("ȸ�����Ծ���� �����Ͽ� �ֽñ� �ٶ��ϴ�."); //��� �޽��� �Է�
                        modal.modal('show'); //��� ����

						console.log('test');
                         
                        provision.removeClass("has-success");
                        provision.addClass("has-error");
                        $('#provisionYn').focus();
                        return false;
                    }else{
                        provision.removeClass("has-error");
                        provision.addClass("has-success");
                    }
                     
                    //����������޹�ħ
                    if($('#memberInfoYn:checked').val()=="N"){
                        modalContents.text("����������޹�ħ�� �����Ͽ� �ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        memberInfo.removeClass("has-success");
                        memberInfo.addClass("has-error");
                        $('#memberInfoYn').focus();
                        return false;
                    }else{
                        memberInfo.removeClass("has-error");
                        memberInfo.addClass("has-success");
                    }
                     
                    //���̵� �˻�
                    if($('#id').val()==""){
                        modalContents.text("���̵� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        divId.removeClass("has-success");
                        divId.addClass("has-error");
                        $('#id').focus();
                        return false;
                    }else{
                        divId.removeClass("has-error");
                        divId.addClass("has-success");
                    }

                    if($('#idchecked').val()=="0"){
                        modalContents.text("���̵� �ߺ�Ȯ���� ���ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        divId.removeClass("has-success");
                        divId.addClass("has-error");
                        $('#id').focus();
                        return false;
                    }else{
                        divId.removeClass("has-error");
                        divId.addClass("has-success");
                    }
                     
                    //�н����� �˻�
                    if($('#password').val()==""){
                        modalContents.text("�н����带 �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        divPassword.removeClass("has-success");
                        divPassword.addClass("has-error");
                        $('#password').focus();
                        return false;
                    }else{
                        divPassword.removeClass("has-error");
                        divPassword.addClass("has-success");
                    }
                     
                    //�н����� Ȯ��
                    if($('#passwordCheck').val()==""){
                        modalContents.text("�н����� Ȯ���� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                        $('#passwordCheck').focus();
                        return false;
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                     
                    //�н����� ��
                    if($('#password').val()!=$('#passwordCheck').val() || $('#passwordCheck').val()==""){
                        modalContents.text("�н����尡 ��ġ���� �ʽ��ϴ�.");
                        modal.modal('show');
                         
                        divPasswordCheck.removeClass("has-success");
                        divPasswordCheck.addClass("has-error");
                        $('#passwordCheck').focus();
                        return false;
                    }else{
                        divPasswordCheck.removeClass("has-error");
                        divPasswordCheck.addClass("has-success");
                    }
                     
                    //�̸�
                    if($('#name').val()==""){
                        modalContents.text("�̸��� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        divName.removeClass("has-success");
                        divName.addClass("has-error");
                        $('#name').focus();
                        return false;
                    }else{
                        divName.removeClass("has-error");
                        divName.addClass("has-success");
                    }
                     
                    //����
                    if($('#nickname').val()==""){
                        modalContents.text("������ �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        divNickname.removeClass("has-success");
                        divNickname.addClass("has-error");
                        $('#nickname').focus();
                        return false;
                    }else{
                        divNickname.removeClass("has-error");
                        divNickname.addClass("has-success");
                    }
                     
                    //�̸���
                    if($('#email').val()==""){
                        modalContents.text("�̸����� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        divEmail.removeClass("has-success");
                        divEmail.addClass("has-error");
                        $('#email').focus();
                        return false;
                    }else{
                        divEmail.removeClass("has-error");
                        divEmail.addClass("has-success");
                    }
                     
                    //�޴��� ��ȣ
                    if($('#phoneNumber').val()==""){
                        modalContents.text("�޴��� ��ȣ�� �Է��Ͽ� �ֽñ� �ٶ��ϴ�.");
                        modal.modal('show');
                         
                        divPhoneNumber.removeClass("has-success");
                        divPhoneNumber.addClass("has-error");
                        $('#phoneNumber').focus();
                        return false;
                    }else{
                        divPhoneNumber.removeClass("has-error");
                        divPhoneNumber.addClass("has-success");
                    }
                     
                 
                });
                 
            });
             
        </script>
                <!--// ���� ���� �κ� -->

        </div>
        
    </body>
</html>