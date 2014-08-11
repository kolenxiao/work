<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<%-- <%@ taglib prefix="s" uri="/struts-tags"%> --%>
  <% String randval = (String)session.getAttribute("session_picCode"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用商店管理后台</title>
<script type='text/javascript'>
if (self != top) {
	top.location.href = self.location.href;
}
</script>
<script src="<%=ctxPath%>/js/validateForm.js" type="text/javascript"></script>
<script type="text/javascript">
	var validator;
	/**
	 * 登录 提交
	 */
	 $(document).ready(function(){
		 $("#userName_id").focus();

		 var options={
		        	type         : 'POST',
		        	dataType     : 'json',
		            success      : function(responseText){
		                //异步请求处理成功
				     	var dataObj = eval(responseText);
						if (dataObj && dataObj.success) {
					    	location.href = "<%=ctxPath%>/main.jsp";
						} else {
							if (dataObj && dataObj.msg) {
								if (dataObj.msg == "验证码不正确")
								{
									$("#vcode_pic_error1").empty().append(dataObj.msg+"!");

								}else
								{
									$("#user_login_error").empty().append(dataObj.msg+"!");
								}

								//dialogList('提示信息',300,150,dataObj.msg,0,0,1,this);
							} else {
								$("#user_login_error").empty().append("登录失败!");
							}
							RefreshImage();
						}

		            }
		        };

	        $('#btn_login').click(function(){
	        	var formName = document.getElementById("loginForm_id");
	        	if (validator(formName))
	        	{
	        		$("#loginForm_id").ajaxSubmit(options);
	        	}
	            return false;
	        });

	    });

	 function RefreshImage(){
		  var el =document.getElementById("rand_num_id");
	      el.src=el.src+'?';//这个特别重要

	 }

	//自定义输出错误信息
	 FormValid.showError = function(errMsg,errName,formName) {
	 if (formName=='loginForm') {
	 	for (key in FormValid.allName) {
	 		document.getElementById('errMsg_'+FormValid.allName[key]).innerHTML = '';
	 	}
	 	for (key in errMsg) {
	 		document.getElementById('errMsg_'+errName[key]).innerHTML = errMsg[key];
	 	}
	 }
	 };
	 //校验用户名
	 function checkName(){
		 // 清空错误信息
		 $("#user_login_error").empty();
			var userName = document.getElementById("userName_id").value;
			if(userName.trim() ==""){
		       return false;
			}
			return true;
	 }
	 //校验验证码
	 function checkVcode(){
		 //清空错误信息
		 $("#vcode_pic_error1").empty();
			var validateCode = document.getElementById("j_validateCode").value;
			if(validateCode.trim() ==""){
		       return false;
			}
			return true;
	 }
 </script>
</head>
<body id="login_body" oncontextmenu="return false">
<form id="loginForm_id" action="user!login.action" method="post" name="loginForm">
  <div id="login_main">
    <div id="login_logo">
<%--     <img src="<%=ctxPath%>/images/sysetem_name.jpg" width="314" height="19" alt="logo name" /> --%>
    </div>
    <div id="login">

      <p id="login_signal"><img src="<%=ctxPath%>/images/login_tip.jpg" width="108" height="29" alt="login" /></p>
      <p id="txt_username">
        <label for="user_name">用户名：</label>
      </p>
      <p id="txt_userpwd">
        <label for="user_pwd">密 &nbsp;&nbsp;码：</label>
      </p>
      <!-- 
      <p id="txt_vcode">
        <label for="user_vcode">验证码：</label>
      </p>
       -->
      <div id="user_name">
      	<input type="text" id="userName_id" name="user.userName" valid="custom|limit" custom="checkName" min="0" max="64" maxlength="64" errmsg="login_username_notEmpty|login_username_len64"/> <span id="user_login_error" style="color:red;"></span>
      	<span id="errMsg_user.userName" class="error_class" style="color:#FF0000"></span>
      </div>
      <div id="user_pwd">
      	<input type="password" id="password_id" name="user.password" onpaste="return false"  valid="required|limit" min="0" max="64" errmsg="login_password_notEmpty|login_password_len64" maxlength="64"/>
      	<span id="errMsg_user.password" class="error_class"style="color:#FF0000"></span>
      </div>
      <!-- 
      <div id="vcode"><input type="text" id="j_validateCode" name="validateCode" autocomplete="off" valid="custom" custom="checkVcode" errmsg="login_vcode_notEmpty" /></div>
      <p id="vcode_pic"><img id="rand_num_id" src="rand.action" onmouseup="RefreshImage()" alt="点击重刷新"/><span id="vcode_pic_error1" style="color:red;"></span>
      <span id="errMsg_validateCode" style="color:#FF0000"></span>
      </p>
      <p id="tip_vcode"></p>
       -->
      <p id="tip_username_id"><span class="error_input"></span></p>
      <input type="submit" value="" id="btn_login"/>
    </div>
    <p id="copyright"><!-- <s:property value="%{getText('sdp.sce.dp.admin.global.copyright')}"/> -->© 深圳市同洲电子股份有限公司版权所有,2009-2013,版本号V100R003B009</p>
  </div>
</form>
<script type="text/javascript">
	initValid(document.loginForm);
	insertLanguageJS();
</script>
</body>
</html>

