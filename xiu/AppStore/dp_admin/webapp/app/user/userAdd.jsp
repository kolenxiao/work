<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增用户</title>
<style type="text/css">

#cnt_body table tr td div span {
	text-align: center;
	font-weight: bold;
}
</style>
<script type="text/javascript">
var validator;
var err_message = "";
var mycars=new Array(1);
mycars[0]="Saab";


$(document).ready(function(){
	//$("#uspServGroup.uspGroupName").focus();
	//$("#re").click(function() {
	//     $("label").remove();

validator = $("#usrApiForm").validate({

			rules: {
				"user.userName":{
						checkNameIsNull: true,
						minlength:4,
						maxlength:64,
						userName:true,
						checkUserName:true

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
},'<font color="red">电话号码格式0755-26990000；手机号码格式13、14、15、18开头，比如13726990000!</font>');

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
},'<font color="red">邮件格式不正确,比如admin@coship.com!</font>');

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

$.validator.addMethod("checkNameIsNull",function(){
	var userName = document.getElementById("userName").value;
	$("#findUserNameError").empty();
		if (userName.trim() != "")
		{
			return true;
		}

		return false;

},'<font color="red">用户名不能为空!</font>');


$.validator.addMethod("checkUserName",function(){

	var result = true;
	var userName = document.getElementById("userName").value;
	var parm ={'userName':userName};


	$.ajax({
	     cache:false,
         async:false,
         type:"POST",
	   	 	url: "user!isUserNameSame.action",
	   	 	data: parm,
	   	 dataType: 'json',

		    success: function(response){
		    	var dataObj = eval(response);
		    	$("#findUserNameError").empty();
		    	//alert(dataObj.msg);
				if (dataObj && dataObj.msg) {
		    		result = false;
		    		//message="用户名已存在，请重新输入!";
		    		$("#findUserNameError").append(dataObj.msg);
				}
		    }
	   	});

		return result;

},' ');

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

	$(document).ready(function(){
		$("#status").change(function(){

		    if ($("#status").val().trim() == "N")
		    {
		    	//dialogList('提示信息',300,150,'是否设置该用户为无效用户?',0,0,2,
				//		this);
		    	//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行提交表单操作
				//$("#dialog_btn_conform").click(function()
				//		  {
							//执行
				//			  doSelect();
				//			  usrApiForm.submit();
			    //		  $("#dialog").remove();
				//			  $("#dialog_bg").remove();
				//		  });
		    	$("#statusTips").empty().append("用户将不能登录系统!");
		    }else{
		    	$("#statusTips").empty();
		    }
		});
	});

</script>
</head>
<body id="cnt_body">
<div id="position">
  <p>当前位置：权限管理 &gt; 用户管理
  <div id="pright"></div>
</div>
<s:form id="usrApiForm" name="usrApiForm" action="user!addUser.action" method="post" cssStyle="margin:0" theme="simple">
<s:token/>
<s:hidden name="usrService.usrServType" value="2" />
<div class="view_nav"  id="nav01" ><span class="float_l">新增用户</span><span class="icon_down" onClick="ShowHideBox(this,'box1','nav01')" ></span></div>
<div id="box1">
<div class="data_view">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th scope="row">用户名：</th>
      <td colspan="3">
  		 <s:textfield  id="userName" name="user.userName"  size="25" />
  		 <span class="style-red">*</span><font color='gray'>&nbsp;由字母、数字、下划线、中文、空格组成</font>
  		 <span id="findUserNameError" style="color:red;"></span>
      </td>

    </tr>
     <tr>
      <th scope="row">真实姓名：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="realName" name="user.realName"  size="25"  /><font color='gray'>&nbsp;由字母、数字、下划线、中文、空格组成</font>
      </td>
    </tr>
    <tr>
     <th scope="row">密码：</th>
      <td id="upLoadTd" colspan="3">
      		<s:password  id="password" name="user.password"  size="25"  /><span class="style-red">*</span>
      </td>
    </tr>
    <tr>
     <th scope="row">确认密码：</th>
      <td id="upLoadTd" colspan="3">

      		<s:password  id="passwordConfirm" name="passwordConfirm"  size="25"  /><span class="style-red">*</span>
      </td>
    </tr>

    <tr>
    <th scope="row">电子邮箱：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="email" name="user.email"  size="25"  />
      </td>
    </tr>

    <tr>
      <th scope="row">联系电话：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="telephone" name="user.telephone"  size="25"  />
      </td>
    </tr>
    <%-- <tr>
      <th scope="row">是否有效：</th>

      <td id="upLoadTd" colspan="3">
			<select name="user.status" id="status">
				<option value="Y">是</option>
				<option value="N">否</option>
			</select><span class="style-red">*</span><span id="statusTips" style="color:red;"></span>
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

    <td style="border-style: none;">


    <select multiple id="select1" style="width:200px;height:160px;">
	<s:iterator value="userRoleList" status="st"  >
		<option value="<s:property value='id' />"><s:property value="name" /></option>
	</s:iterator>
	</select>
	</td>

  </tr>
</table>

</td>
	<td width="77" style="width:30px;text-align:center;">
	<div >
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

	</select>
		</td>
  </tr>
</table>

</td>

     </tr>

  </table>
</div>
</div>

<div class="btnlistbar" align="left">
  <input class="inputstyle" type="button" value="保存" onClick="doSave()"/>
  <input class="inputstyle" type="button" value="取消" onClick="doBack()"/>
</div>

</s:form>




</body>
</html>
