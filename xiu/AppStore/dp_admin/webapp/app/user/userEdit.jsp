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
var validator;
$(document).ready(function(){
	//$("#uspServGroup.uspGroupName").focus();
	//$("#re").click(function() {
	//     $("label").remove();

validator = $("#usrApiForm").validate({

			rules: {
				"user.userName":{
						required: true,
						minlength:4,
						maxlength:64,
						userName:true

					},
				"user.password":{
					required: true,
					minlength:4,
					maxlength:64
					},
					"passwordConfirm":{
						required: true,
						checkPassword:true
					},
					"user.realName":{
						minlength:4,
						maxlength:64,
						realName:true
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
				"user.userName":{
					required: '<font color="red">用户名不能为空!</font>',
					minlength: '<font color="red">用户名长度不能小于4个字符!</font>',
					maxlength: '<font color="red">用户名长度不能超过64个字符!</font>'
					},
				"user.password":{
					required:'<font color="red">密码不能为空!</font>',
					minlength: '<font color="red"> 密码长度不能小于4个字符!</font>',
					maxlength: '<font color="red">密码长度不能超过64个字符!</font>'

					},
					"passwordConfirm":{
						required:'<font color="red">确认密码不能为空!</font>'
						},

					"user.realName":{
						minlength: '<font color="red">真实姓名长度不能小于4个字符!</font>',
						maxlength: '<font color="red">真实姓名长度不能超过64个字符!</font>'
					},
					"user.email":{
						maxlength: '<font color="red">Email长度不能超过64个字符!</font>'
					},
					"user.telephone":{
						maxlength: '<font color="red">电话号码长度不能超过20个字符!</font>'
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
},'<font color="red">电话号码正确格式，比如0755-26990000；手机号码格式13、14、15、18开头，比如13726990000!</font>');

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
},'<font color="red">邮件格式不正确，比如admin@coship.com!</font>');

$.validator.addMethod("checkPassword",function(){
	var password = document.getElementById("password").value;
	var passwordConfirm = document.getElementById("passwordConfirm").value;
       if(password!=passwordConfirm){
        	return false;
       }else{
        	return true;
       }
},'<font color="red">两次输入密码不一致!</font>');

$.validator.addMethod("userName",function(){
	var userName = document.getElementById("userName").value;
		if (RuleLib.Name.test(userName.trim()))
		{
			return true;
		}
		return false;

},'<font color="red">用户名只能输入字母、数字、下划线、中文、空格!</font>');

$.validator.addMethod("realName",function(){
	var realName = document.getElementById("realName").value;
		if (RuleLib.Name.test(realName.trim()))
		{
			return true;
		}
		return false;

},'<font color="red">真实姓名只能输入字母、数字、下划线、中文、空格!</font>');

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
	function doSave() {
		var usrApiForm = $("#usrApiForm");

		if(validator.form()){
		    doSelect();
		    usrApiForm.submit();
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
	    	dialogList('提示信息',300,150,"该选项的值已经存在!",0,0,1,this);
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
	        	add("select2","select1");
	        }
	    }
	}


	function add(source,desc)
	{
		// begin add
		var $options = $("#" +source+" option:selected");// 获得选项

		var obj = document.getElementById(desc);


		for (var j=0;j<$options.length;j++)
		{
			// 不在复选框2中就将一中的添加进去
			if(!jsSelectIsExitItem(obj,$options[j].value))
			{
				jsAddItemToSelect(obj,$options[j].text,$options[j].value);
				
			}
		}
        
		$options.remove();

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
		add('select1','select2');
	}


	function removeSelect()
	{
		var obj = document.getElementById("select2"); // 获得选项
		// 删除列表中选中的选项
		jsRemoveSelectedItemFromSelect(obj);

	}

	function remove_all()
	{
		doSelect();
		// 获得全部选项
		removeSelect();
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




	/**
	 * 功能 ：模拟IE下点击select的option单击事件
	 * @param {Object} form 表单
	 */
	function simOptionClick4IE(form){
	    var evt = window.event;
	   // alert(evt);
	    var selectObj = evt ? evt.srcElement : null;
	    // IE Only
	    if (evt && selectObj && evt.offsetY && evt.button != 2 &&
	    (evt.offsetY > selectObj.offsetHeight || evt.offsetY < 0)) {

	        // 记录原先的选中项
	        //var oldIdx = selectObj.selectedIndex;

	        setTimeout(function(){
	            var option = selectObj.options[selectObj.selectedIndex];
	            if (option.value == '') {
	                return false;
	            }
	           // alert(option.value);
	            // 此时可以通过判断 oldIdx 是否等于 selectObj.selectedIndex来判断用户是不是点击了同一个选项,进而做不同的处理
	            if (option.value == "N")
		   		{
		    		$("#statusTips").empty().append("用户将不能登录系统!");
		    	}else{
		    		$("#statusTips").empty();
		    	}
	                    }, 60);
	    }
	}



</script>
</head>
<body id="cnt_body">
<div id="position">
  <p>当前位置：权限管理 &gt; 用户管理
  <div id="pright"></div>
</div>
<s:form id="usrApiForm" name="usrApiForm" action="user!updateUser.action" method="post" cssStyle="margin:0" theme="simple">
<s:hidden name="user.password" value="%{user.password}" />
<s:hidden name="user.id" value="%{user.id}" />
<s:hidden name="user.createdDate" value="%{user.createdDate}" />
<s:hidden name="user.createdUser" value="%{user.createdUser}" />
<s:hidden name="user.userName" value="%{user.userName}" />

<div class="view_nav"  id="nav01" ><span class="float_l">用户编辑</span><span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span></div>
<div id="box1">
<div class="data_view">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th scope="row">用户名：</th>
      <td width="" colspan="3">
  		 <s:textfield  id="userName" name=""  size="64" maxlength="64" value="%{user.userName}" disabled="true"/>
  		 <span class="style-red">*</span>

      </td>
    </tr>
    <%--
    <tr>
    	<th scope="row">密码：</th>
      <td id="upLoadTd">
      		<input id="password" size="25" name="user.password" type="password" value="<s:property value="%{user.password}" />"/><span class="style-red">*</span>
      </td>
    </tr>

    <tr>
    	<th scope="row">确认密码：</th>
      <td id="upLoadTd">
      		<input id="passwordConfirm" size="25"  type="password" value="<s:property value="%{user.password}" />"/><span class="style-red">*</span>
      </td>
    </tr>
     --%>
    <tr>
      <th scope="row">真实姓名：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="realName" name="user.realName"  size="64" value="%{user.realName}" /><font color='gray'>&nbsp;由字母、数字、下划线、中文、空格组成</font>
      </td>
    </tr>
    <tr>
    	<th scope="row">电子邮箱：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="email" name="user.email"  size="64" value="%{user.email}" />
      </td>
    </tr>
    <tr>
      <th scope="row">联系电话：</th>
      <td  id="upLoadTd" colspan="3">
			<s:textfield  id="telephone" name="user.telephone"  size="20" value="%{user.telephone}" />
      </td>
    </tr>
    <tr>
      <th scope="row">是否有效：</th>
      <td id="upLoadTd" colspan="3">
			<select name="user.status" id="status" onclick="simOptionClick4IE(this)">
			<s:if test='user.status == "Y"'>
				<option value="Y" onclick="simOptionClick4IE(this)">是</option>
				<option value="N" onclick="simOptionClick4IE(this)">否</option>
			</s:if>
			<s:elseif test='user.status == "N"'>
				<option value="N" onclick="simOptionClick4IE(this)">否</option>
				<option value="Y" onclick="simOptionClick4IE(this)">是</option>
			</s:elseif>
			<s:else>
				<option value="Y" onclick="simOptionClick4IE(this)">是</option>
				<option value="N" onclick="simOptionClick4IE(this)">否</option>
			</s:else>

			</select><span class="style-red">*</span><span id="statusTips" style="color:red;"></span>
      </td>
    </tr>

   <%-- <tr>
      <th scope="row">分配角色：</th>
      <td>
    <div class="centent">
	<select multiple id="select1" style="width:100px;height:160px;">
		<s:iterator value="allRoleList" status="st"  >
			<option value="<s:property value='id' />"><s:property value="name" /></option>
		</s:iterator>
	</select>
	<div>
		<span id="add">
			选中添加到右边&gt;&gt;
		</span>
		<span id="add_all">
			全部添加到右边&gt;&gt;
		</span>
	</div>
    </div>

<div class="centent">
	<select multiple id ="select2" style="width:100px;height:160px;" name="selectRole">
		<s:iterator value="userRoleList" status="st"  >
			<option value="<s:property value='id' />"><s:property value="name" /></option>
		</s:iterator>
	</select>

	<div>
		<span id="remove">
			&lt;&lt;选中删除到左边
		</span>
		<span id="remove_all">
			&lt;&lt;全部删除到左边
		</span>
	</div>
</div>
</td>
     </tr>--%>

     <tr>
      <th scope="row">分配角色：</th>
      <td width="10%" align="left">
       <table width="50%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="center" style="font-weight: bold;border-style: none;" >系统拥有的角色</td>
  </tr>
  <tr>
    <td style="border-style: none;"><select multiple id="select1" style="width:200px;height:160px;">
	<s:iterator value="allRoleList" status="st"  >
		<option value="<s:property value='id' />"><s:property value="name" /></option>
	</s:iterator>
	</select>	</td>
  </tr>
</table>

</td>
	<td style="width:50px;text-align:center;">
	<div>
      <ul>
        <li><input name="" type="button" value="&gt;&gt;" onClick="add_all()" style="width: 30px"/></li>
        <li><input name="" type="button" value="&gt;" onClick="add('select1','select2')" style="width: 30px"/></li>
        <li><input name="" type="button" value="&lt;" onClick="removeSelect()" style="width: 30px"/></li>
        <li><input name="" type="button" value="&lt;&lt;" onClick="remove_all()" style="width: 30px"/></li>

      </ul>
    </div>
    </td>
<td width="431">

<table width="50%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="center" style="font-weight: bold;border-style: none;" >
	用户所属角色</td>
  </tr>
  <tr>
    <td style="border-style: none;">
	<select multiple id ="select2" style="width:200px;height:160px;" name="selectRole">
		<s:iterator value="userRoleList" status="st"  >
			<option value="<s:property value='id' />"><s:property value="name" /></option>
		</s:iterator>
	</select>
		</td>
  </tr>


  </table>
   </table>
</div>
</div>

<div class="btnlistbar" align="left">
  <input class="inputstyle" type="button" value="保存" onclick="doSave()"/>
  <input class="inputstyle" type="button" value="取消" onclick="doBack()"/>
</div>

</s:form>




</body>
</html>
