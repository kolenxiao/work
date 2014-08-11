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
       var options={ 
       	type         : 'POST',
       	dataType     : 'json',
       	/*  resetForm    : true,  */
           beforeSubmit : showRequest, 
           success      : function(responseText){
               //异步请求处理成功 
		     	var dataObj = eval(responseText);
				if (dataObj && dataObj.success) {
			    	location.href = "user!searchUser.action";
				} else {
					if (dataObj && dataObj.msg) {
						dialogList('提示信息',300,150,dataObj.msg,0,0,1,this);
					} else {
						dialogList('提示信息',300,150,"修改失败!",0,0,1,this);
					}
				}           	
           	
           }
       }; 
       
       $('#btn_save').click(function(){
           $("#usrApiForm").ajaxSubmit(options); 
           return false;     	
       });
       //提交表单前校验
       function showRequest()  {
    	   var userName = $("#userName");
   		var password = $("#password");
   		var passwordConfirm = $("#passwordConfirm");	
   		var realName = $("#realName");
   		var email = $("#email");
   		var telephone = $("#telephone");	
   		var oldPassword = $("#oldPassword");
   		
   		if (userName.val().trim() == "") {			
   			dialogList('提示信息',300,150,'用户名称不能为或空格!',0,0,1,this);
   			userName.focus();
   			return false;
   		}
   		else if (userName.val().trim()!="" && userName.val().length>64)
   		{	
   			dialogList('提示信息',300,150,'用户名长度不能超过64位!',0,0,1,this);
   			userName.focus();
   			return false;
   		}
   		else if (oldPassword.val() == "")
   		{
   			dialogList('提示信息',300,150,'原始密码不能为空!',0,0,1,this);
   			oldPassword.focus();
   			return false;
   		}
   		else if (oldPassword.val() != "" && oldPassword.val().length >64)
   		{			
   			dialogList('提示信息',300,150,'原始密码长度不能超过64位!',0,0,1,this);
   			oldPassword.focus();
   			return false;
   		}
   		
   		else if (password.val() == "" ) {
   			dialogList('提示信息',300,150,'新密码不能为空!',0,0,1,this);
   			password.focus();
   			return false;
   		} 
   		else if (password.val() != "" && password.val().length > 64)
   		{
   			dialogList('提示信息',300,150,'新密码长度不能超过64位!',0,0,1,this);
   			password.focus();
   			return false;
   		}
   		else if (password.val() != passwordConfirm.val() )
   		{
   			dialogList('提示信息',300,150,'新密码和确认密码不一致!',0,0,1,this);	
   			password.focus();
   			return false;
   		}
   		
   		else if (realName.val() != "" && realName.val().length > 64)
   		{
   			dialogList('提示信息',300,150,'真实姓名长度不能超过64位!',0,0,1,this);	
   			realName.focus();
   			return false;
   		}
   		
   		else if (email.val() != "" && email.val().length>64)
   		{
   			dialogList('提示信息',300,150,'邮件长度不能超过64位!',0,0,1,this);	
   			email.focus();
   			return false;
   		}
   		
   		else if (email.val()!=""&&!email.val().match( /^.+@.+$/ ))
   		{
   			
   			dialogList('提示信息',300,150,'邮件格式不对!',0,0,1,this);
   			email.focus();
   			return false;
   		}
   		else if(telephone.val()!=""&&telephone.val().length>20)
   		{
   			dialogList('提示信息',300,150,'电话号码长度不能超过20个字符!',0,0,1,this);
   			telephone.focus();
   			return false;
   		}
   		
   		else if (telephone.val()!=""&&!IsTelephone(telephone.val()))
   		{
   			dialogList('提示信息',300,150,'电话格式不对!',0,0,1,this);
   			telephone.focus();
   			return false;
   		}
       } 
   });
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
		window.location.href = "user!searchUser.action";
	}
	
	// 判断select中是否存在该选项
	function jsSelectIsExitItem(objSelect, objItemValue) {        
	    var isExit = false;        
	    
	    for (var i = 0; i < objSelect.options.length; i++) {        
	        if (objSelect.options[i].value == objItemValue) {        
	            isExit = true;        
	            break;        
	        }        
	    }        
	    return isExit;        
	}         

	// 向select选项中 加入一个Item        
	function jsAddItemToSelect(objSelect, objItemText, objItemValue) {        
	    //判断是否存在        
	    if (jsSelectIsExitItem(objSelect, objItemValue)) {        
	        alert("该Item的Value值已经存在");        
	    } else {        
	        var varItem = new Option(objItemText, objItemValue);      
	        objSelect.options.add(varItem);     
	        //alert("成功加入");     
	    }        
	}  
	
	// 删除select中选中的项    
	function jsRemoveSelectedItemFromSelect(objSelect) {        
	    var length = objSelect.options.length - 1;    
	    for(var i = length; i >= 0; i--){    
	        if(objSelect[i].selected == true){    
	            objSelect.options[i] = null;    
	        }    
	    }    
	}      
	
	function add()
	{
		// begin add
		var $options = $('#select1 option:selected');// 获得选项
		
		var obj = document.getElementById("select2"); 
		
		
		for (var j=0;j<$options.length;j++)
		{
			// 不在复选框2中就将一中的添加进去
			if(!jsSelectIsExitItem(obj,$options[j].value))
			{
				jsAddItemToSelect(obj,$options[j].text,$options[j].value);
			} 
		}
		
		
		// 删除列表中选中的选项,追加给对方
		//$options.appendTo('#select2');
		
	}
	
	function add_all()
	{		
		var obj = document.getElementById("select1"); 
		
		var optionlength = obj.options.length;
		
		var i;
		
		for (i=0;i<optionlength;i++)
		{
			obj.options[i].selected = true; //保持选中状态 		
		}
		add();
	}
	
	
	function remove()
	{		
		var obj = document.getElementById("select2"); // 获得选项
		// 删除列表中选中的选项
		jsRemoveSelectedItemFromSelect(obj);
		
	}
	
	function remove_all()
	{		
		doSelect();
		// 获得全部选项
		remove();
		//doSelect();
		//remove();
	}
	
	
	function doSelect()
	{
		var obj = document.getElementById("select2"); 
		
		var optionlength = obj.options.length;
		
		var i;
		
		for (i=0;i<optionlength;i++)
		{
			obj.options[i].selected = true; //保持选中状态 		
		}
		
	}
	
	
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p>当前位置：权限管理 &gt; 用户管理
  <div id="pright"></div>
</div>
<s:form id="usrApiForm" name="usrApiForm" action="user!updateUserInformation.action" method="post" cssStyle="margin:0" theme="simple">
<s:hidden name="user.id" value="%{user.id}" />
<s:hidden name="user.createdDate" value="%{user.createdDate}" />
<s:hidden name="user.createdUser" value="%{user.createdUser}" />

<div class="view_nav"  id="nav01" ><span class="float_l">用户编辑</span><span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span></div>
<div id="box1">
<div class="data_view">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <s:hidden name="user.status" value="%{user.status}" />
    <tr>
      <th scope="row">用户名：</th>
      <td>
  		 <s:textfield  id="userName" name="user.userName"  size="64" value="%{user.userName}" />  
  		 <span class="style-red">*</span>    
      </td>      
    </tr>
   
   <tr>
    	<th scope="row">原始密码：</th>
      <td id="upLoadTd">
  		 <s:password  id="oldPassword" name="oldPassword"  size="64"  /><span class="style-red">*</span>
      </td>
    </tr>
   
    <tr>
    	<th scope="row">新密码：</th>
      <td id="upLoadTd">
      		<s:password id="password" size="64" name="user.password" type="password" /><span class="style-red">*</span>
      </td>
    </tr>
    
    <tr>
    	<th scope="row">确认密码：</th>
      <td id="upLoadTd">
      		<s:password id="passwordConfirm" size="64"  type="password"/><span class="style-red">*</span>
      </td>
    </tr>
    
    <tr>
      <th scope="row">真实姓名：</th>
      <td id="upLoadTd">
			<s:textfield  id="realName" name="user.realName"  size="64" value="%{user.realName}" />
      </td>
    </tr>
    <tr>
    	<th scope="row">电子邮箱：</th>
      <td id="upLoadTd">
			<s:textfield  id="email" name="user.email"  size="64" value="%{user.email}" />
      </td>
    </tr>
    <tr>
      <th scope="row">联系电话：</th>
      <td  id="upLoadTd">
			<s:textfield  id="telephone" name="user.telephone"  size="64" value="%{user.telephone}" />
      </td>
    </tr>
    <%-- 
    <tr>
      <th scope="row">是否有效：</th>     
      <td id="upLoadTd" >
			<select name="user.status">
			<s:if test='user.status == "Y"'>
				<option value="Y">是</option>
				<option value="N">否</option>
			</s:if>
			<s:elseif test='editUser.status == "N"'>
				<option value="N">否</option>
				<option value="Y">是</option>
			</s:elseif>
			<s:else>
				<option value="Y">是</option>
				<option value="N">否</option>
			</s:else>
				
			</select><span class="style-red">*</span>
      </td>
    </tr>
    --%>
   
    
     
  </table>
  
</div>
</div>

<div class="btnlistbar" align="left">
  <input class="inputstyle" type="button" value="保存" id="btn_save"/>
  <input class="inputstyle" type="button" value="取消" onclick="doBack()"/>
</div>

</s:form>




</body>
</html>
