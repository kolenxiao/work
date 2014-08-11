<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<script type="text/javascript">
function validateForm() {

	//原始密码值
	//var oldPassword = document.getElementById("oldPassword");
	//原始确认密码
	var oldPasswordConf = document.getElementById("oldPasswordConf");
	//var oldPassword=oldPasswordConf.value;

	var oldPassword = encodeURIComponent(encodeURIComponent(oldPasswordConf.value)).replace(/%20/g, "+");

	var password = document.getElementById("password_id");
	var comfirmPassword = document.getElementById("comfirmPassword_id");
	if(isNull(oldPasswordConf.value)){
		$("#dialog").remove();
		 dialogList('提示信息',300,150,'原密码不能为空!',0,0,1,this);
		 oldPasswordConf.focus();
	     return false;
	}

	var message;
	$.ajax({
	    cache:false,
	    async:false,
	    type:"POST",
	    url: "user!isOldPasswordRight.action?oldPassword="+oldPassword,
	    dataType: 'json',
        success: function(response){
	        var dataObj = eval(response);
            if (dataObj && dataObj.msg)
			{
            	message=dataObj.msg;
			}
	   }
	 });
	if(message!=null){
		$("#dialog").remove();
		dialogList('提示信息',300,150,message,0,0,1,this);
		return false;
	}
	/* if(oldPasswordConf.value != oldPassword.value){
		 dialogList('提示信息',300,150,'原密码不正确!',0,0,1,this);
		 oldPasswordConf.focus();
	     return false;
	} */
	if(isNull(password.value)){
		$("#dialog").remove();
        dialogList('提示信息',300,150,'新密码不能为空!',0,0,1,this);
        password.focus();
        return false;
    }

	if(len(password.value)>32){
		$("#dialog").remove();
        dialogList('提示信息',300,150,'密码不能长度超过30!',0,0,1,this);
        password.focus();
        return false;
    }
    if(isNull(comfirmPassword.value)){
    	$("#dialog").remove();
    	 dialogList('提示信息',300,150,'确认新密码不能为空!',0,0,1,this);
    	 comfirmPassword.focus();
         return false;
    }
    if(password.value!=comfirmPassword.value){
    	$("#dialog").remove();
    	 dialogList('提示信息',300,150,'确认新密码与新密码不一致!',0,0,1,this);
    	 comfirmPassword.focus();
         return false;
    }

	return true;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>密码修改</title>
</head>
<body id="cnt_body">
<div id="position">
  <p>当前位置：系统管理 &gt; 密码修改</p>
  <div id="pright"></div>
</div>
<s:form action="user!editPassword" method="post" onsubmit="return validateForm();" cssStyle="margin:0" theme="simple">
<div class="view_nav"  id="nav01" ><span class="float_l">密码修改</span></div>
<div class="data_view">
<s:if test="message!=null">
<p id="tip_username_id"><span class="error_input"><s:property value="message" /></span></p>
</s:if>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
   <tr>
      <th scope="row">原密码：</th>

      <td colspan="3">
     	 <%-- <s:hidden id="oldPassword" value="%{user.password}"/> --%>
  		 <s:password  id="oldPasswordConf" name="oldPasswordConf"  size="25" maxlength="25" />
  		 <span class="style-red">*</span>
      </td>
    </tr>
    <tr>
      <th scope="row">新密码：</th>
      <td colspan="3">
  		 <s:password  id="password_id" name="user.password"  size="25" maxlength="25" />
  		 <span class="style-red">*</span>
      </td>
    </tr>
    <tr>
      <th scope="row">确认新密码：</th>
      <td colspan="3">
  		 <s:password  id="comfirmPassword_id" size="25" maxlength="25" />
  		 <span class="style-red">*</span>
      </td>
    </tr>

  </table>
</div>


<div class="btnlistbar" align="left">
  <input class="inputstyle" type="submit" value="提交" />
  <input class="inputstyle" type="reset" value="重置" />
  <input class="inputstyle" type="button" value="取消" onclick="history.back()"/>
</div>

</s:form>

</body>
</html>



