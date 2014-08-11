<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务注册</title>

<script type="text/javascript">
//added of 2011.08.15 以Ajax提交form表单
$(document).ready(function(){ 
	validator = $("#usrApiForm").validate({

		rules: {			
				"user.realName":{						
					maxlength:64
				},
				"user.email":{						
					checkEmail:true,						
					maxlength:64
				},
				"user.telephone":{						
					checkPhone:true,
					maxlength:20
				}
		},
		messages: {			
			"user.realName":{
				maxlength: '<font color="red">真实姓名长度不能超过64个字符！</font>'	
			},
			"user.email":{				
				
				maxlength: '<font color="red">Email长度不能超过64个字符！</font>'		
			},
			"user.telephone":{
				maxlength: '<font color="red">电话号码长度不能超过20个字符！</font>'	
			}	
				
				
		},
		errorPlacement: function(error, element) {   
		      error.appendTo( element.parent() );   
		    },  
		    onkeyup: false
	});
});

$.validator.addMethod("checkPhone",function(){
	var telephone = document.getElementById("telephone").value;	
	//电话
	var tel = /^(([0][1-9][0-9]{2}|[0][1-9][0-9])-){1}([0-9]{7}|[0-9]{8}){1}(-([0-9]{3,4})){0,1}$/;
	//手机
	var mobile = /^(13|14|15|18)+[0-9]{9}$/;
	if(telephone.trim() !=""){
	       if(tel.test(telephone.trim())||mobile.test(telephone.trim())){
	        	return true;
	       }else{
	        	return false;
	       }   
		}else{
			return true;
		}    
},'<font color="red">电话号码格式不对!</font>');		

$.validator.addMethod("checkEmail",function(){
	var email = document.getElementById("email").value;		
	if(email.trim() !=""){
	       if(RuleLib.email.test(email.trim())){
	        	return true;
	       }else{
	        	return false;
	       }   
		}else{
			return true;
		}    
},'<font color="red">邮件格式不对!</font>');	
//ended of 2011.08.15
function ShowHideBox(me,boxid,navid){
	var el = document.getElementById(boxid);
	if(el.style.display == 'none'){
		el.style.display = 'block';
		me.style.background = "url(<%=ctxPath%>/images/icon_up.gif) no-repeat";
		document.getElementById(navid).setAttribute("calss","view_nav");
	} else{
		el.style.display = 'none';
		me.style.background = "url(<%=ctxPath%>/images/icon_down.gif ) no-repeat";
		document.getElementById(navid).setAttribute("calss","view_nav");
	}
}
	


function ShowHideBox3(me){
	var el = document.getElementById('box3');
	if(el.style.display == 'none'){
		el.style.display = 'block';
		me.style.background = "url(../../images/icon_up.gif) no-repeat";
		document.getElementById("nav03").setAttribute("calss","view_nav");
	} else{
		el.style.display = 'none';
		me.style.background = "url(../../images/icon_down.gif ) no-repeat";
		document.getElementById("nav03").setAttribute("calss","view_nav");
	}
}  


	function IsTelephone(obj)// 正则判断
	{ 
		var pattern=/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
		if(pattern.test(obj)) 
		{ 
			return true; 
		} 
		else 
		{ 
			return false; 
		} 
	} 


	
	function doBack() {
		window.location.href = "user!userDetail.action";
	}
	
function doSave()
{	
	var usrApiForm = $("#usrApiForm");	
		
		usrApiForm.submit();
}
//重置信息
function resetForm()
{	
	document.getElementById("realName").value="";	
	document.getElementById("email").value="";
	document.getElementById("telephone").value="";
}
	
	
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p>当前位置：修改个人信息
  <div id="pright"></div>
</div>
<s:form id="usrApiForm" name="usrApiForm" action="user!updateUserselfInformation.action" method="post" cssStyle="margin:0" theme="simple">
<s:hidden name="user.id" value="%{user.id}" />
<s:hidden name="user.createdDate" value="%{user.createdDate}" />
<s:hidden name="user.createdUser" value="%{user.createdUser}" />
<s:hidden name="user.password" value="%{user.password}" />
<s:hidden name="user.userName" value="%{user.userName}" />
<s:hidden name="user.status" value="%{user.status}" />


<div class="view_nav"  id="nav01" ><span class="float_l">用户编辑</span><span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span></div>
<div id="box1">
<div class="data_view">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  
    <tr>
      <th scope="row">用户名：</th>
      <td>  		 
  		 <s:property value="%{user.userName}"/>
  		     
      </td>      
    </tr>   
   
   <tr>
      <th scope="row">用户角色：</th>
      <td>
      <s:iterator value="userRoleList" status="st"  >
      	<s:if test="#st.index+1 == userRoleList.size()">
      		<s:property value="name"/>
      	</s:if>
      	<s:else>
      		<s:property value="name"/>,
      	</s:else>
      
      </s:iterator>  </td>    
    </tr>   
    <tr>
      <th scope="row">是否有效：</th>
      <td>  
  		 <s:if test='user.status == "Y"'>
				是				
		 </s:if>
		 <s:elseif test='editUser.status == "N"'>
				否				
		 </s:elseif>  		    
      </td>           
    </tr>   
    
    <tr>
      <th scope="row">真实姓名：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="realName" name="user.realName"  value ="%{user.realName}" size="64"  />
      </td>      
    </tr>
    <tr>
    <th scope="row">电子邮箱：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="email" name="user.email"  value ="%{user.email}" size="64"  />
      </td>
    </tr>
    
    <tr>
      <th scope="row">联系电话：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="telephone" name="user.telephone" value="%{user.telephone}" size="20"  />
      </td>
    </tr>
     
  </table>
  
</div>
</div>

<div class="btnlistbar" align="left">
  <input class="inputstyle" type="button" value="确定" id="btn_save" onclick="doSave()"/>
  <input class="inputstyle" type="button" value="重置" onclick="resetForm()"/>
  <input class="inputstyle" type="button" value="取消" onclick="doBack()"/>
</div>

</s:form>




</body>
</html>
