<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.resources.add"/></title>

<script type="text/javascript">

var validator;
$(document).ready(function(){

validator = $("#uspResForm").validate({

			rules: {
				"resource.name":{
						checkNameIsNull: true,
						maxlength:200,
						resName:true
					},
				"resource.enName":{
					required: true,
					checkEnName:true,
					checkEnNameUnique:true,
					maxlength:50
					},
				"resource.url":{
					maxlength:200
				},
				"resource.orderField":{
					required: true,
					checkOrderField:true
				},
				"resource.description":{
					maxlength:200
				}
			},
			messages: {
				"resource.name":{
					maxlength: '<font color="red"><s:text name="sdp.sce.dp.admin.resources.resName.leng.lessthan200"/></font>'
					},
				"resource.enName":{
					required:'<font color="red"><s:text name="sdp.sce.dp.admin.resources.resIdentifier.notEmpty"/></font>',
					maxlength: '<font color="red"><s:text name="sdp.sce.dp.admin.resources.resIdentifier.leng.lessthan50"/></font>'
					},
					"resource.url":{
						maxlength: '<font color="red"><s:text name="sdp.sce.dp.admin.resources.resURL.leng.lessthan200"/></font>'
					},
					"resource.orderField":{
						required: '<font color="red"><s:text name="sdp.sce.dp.admin.resources.resOrderr.notEmpty"/></font>'

					},
					"resource.description":{
						maxlength: '<font color="red"><s:text name="sdp.sce.dp.admin.resources.resdescription.leng.lessthan200"/></font>'
					}
			},
			errorPlacement: function(error, element) {
			      error.appendTo( element.parent() );
			    },
			    onkeyup: false
});
});
$.validator.addMethod("checkOrderField",function(){
    var orderField = document.getElementById("orderField").value;
    if(!checkRate(orderField.trim())){
     	return false;
    }else{
     	return true;
    }
},'<font color="red"><s:text name="sdp.sce.dp.admin.resources.resOrder.mustbe.ingete"/></font>');



$.validator.addMethod("checkEnNameUnique",function(){
 	   $("#checkedResEnName").empty();

 	   var result = true;
    	   var resEnName = document.getElementById("resEnName").value;
    	   var parm ={'resEnName':resEnName};

    	   $.ajax({
    	        cache:false,
                async:false,
                type:"POST",
    	   	 	url: "resource!isResourceNameSame.action",
    	   	 	data: parm,
    	   	    dataType: 'json',

    		    success: function(response){
    		    	var dataObj = eval(response);
    				if (dataObj && dataObj.msg) {
    		    		result = false;
    		    		$("#checkedResEnName").append(dataObj.msg);
    				}
    		    }
    	   	});

    		return result;
},' ');

$.validator.addMethod("checkEnName",function(){
       var resEnName = document.getElementById("resEnName").value;
       var reg = /^[a-zA-Z_]{1}[a-zA-Z0-9_]*$/;
       if(!reg.test(resEnName)){
        	return false;
       }else{
    	   return true;
       }
},'<font color="red"><s:text name="sdp.sce.dp.admin.resources.residentity.check"/></font>');

$.validator.addMethod("resName",function(){
	var resName = document.getElementById("resName").value;
		if (RuleLib.Name.test(resName.trim()))
		{
			return true;
		}
		return false;

},'<font color="red"><s:text name="sdp.sce.dp.admin.resources.residentity.check"/></font>');

$.validator.addMethod("checkNameIsNull",function(){
	var resName = document.getElementById("resName").value;
	$("#checkedResName").empty();
		if (resName.trim()!="")
		{
			return true;
		}
		return false;

},'<font color="red"><s:text name="sdp.sce.dp.admin.resources.resName.notEmpty"/></font>');


$.validator.addMethod("checkedName",function(){

	var result = true;
	var resEnName = document.getElementById("resEnName").value;
	var parm ={'resEnName':resEnName};


	$.ajax({
	     cache:false,
         async:false,
         type:"POST",
	   	 	url: "resource!isResourceNameSame.action",
	   	 	data: parm,
	   	 dataType: 'json',

		    success: function(response){
		    	var dataObj = eval(response);
		    	$("#checkedResName").empty();
				if (dataObj && dataObj.msg) {
		    		result = false;
		    		//message="用户名已存在，请重新输入!";
		    		$("#checkedResName").append(dataObj.msg);
				}
		    }
	   	});

		return result;

},' ');

	function doSave() {

		var uspResForm = $("#uspResForm");
		if(validator.form()){
			uspResForm.submit();
		}
	}


	function checkRate(value){
	    var re = /^[1-9]+[0-9]*]*$/;
	    //var re2 = /^[0-9]$/;
	    if(!re.test(value))
	        return false;
	    else if(parseInt(value)>99){
	    	return false;
	    }else{
	    	return true;
	    }
	}

</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/> <s:text name="sdp.sce.dp.admin.resources"/>&gt; <s:text name="sdp.sce.dp.admin.resources.add"/></p>
  <div id="pright"></div>
</div>
<div class="view_nav"><s:text name=""/><s:text name="sdp.sce.dp.admin.resources.add"/></div>
<s:form id="uspResForm" action="resource!addRes.action" method="post">
<s:token/>
<s:hidden name="parentId" value="%{id }" />
<s:hidden name="resource.menuButton" value="%{menuButtonFlag }" />
<div class="data_view">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.resources.input.resourceName"/></th>
    <td><input id="resName" name="resource.name" type="text"/><span class="must_input">*</span>
    	<span class="notes"><s:text name="sdp.sce.dp.admin.resources.resname.tip"/></span><font color='gray'>&nbsp;<s:text name="sdp.sce.dp.admin.resources.resname.with.char"/></font>
    	<span id="checkedResName" style="color:red;"></span>
    </td>
  </tr>
  <tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.resources.resource.input.identifier"/></th>
    <td><input id="resEnName" name="resource.enName" type="text" /><span class="must_input">*</span>
    	<span class="notes"><s:text name="sdp.sce.dp.admin.resources.resIdentifier.tip"/></span>
    	<span id="checkedResEnName" style="color:red;"></span>
    </td>
  </tr>
  <tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.resources.input.resourceURL"/></th>
    <td><input id="url" type="text" name="resource.url" />
    	&nbsp;&nbsp;&nbsp;&nbsp;<span class="notes"><s:text name="sdp.sce.dp.admin.resources.resMenu.tip"/></span>
    </td>
  </tr>
  <tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.resources.input.resourceOrder"/></th>
    <td><input id="orderField" type="text" name="resource.orderField" value="1"/><span class="must_input">*</span>
    	<span class="notes"><s:text name="sdp.sce.dp.admin.resources.resMenu.order"/></span>
    </td>
  </tr>
  <tr>

  </tr>
  <tr>
  <th scope="row"><s:text name="sdp.sce.dp.admin.resources.input.resourceDescription"/></th>
    <td><textarea id="description" name="resource.description" cols="100" rows="4" ></textarea><p><span class="notes"><s:text name="sdp.sce.dp.admin.resources.lessthan200.char"/></span></p></td>
  </tr>
</table>
</div>
<div class="btnlistbar">
<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>" onclick="doSave()"/>
<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back()"/>
</div>
</s:form>

</body>
</html>
