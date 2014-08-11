<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.resources.update"/></title>
<style>
.input2 {
	border: 1px solid #fff;
}
</style>
<script type="text/javascript">

var validator;
$(document).ready(function(){
	//$("#uspServGroup.uspGroupName").focus();
	//$("#re").click(function() {
	//     $("label").remove();

validator = $("#dpResForm").validate({

			rules: {
				"resource.name":{
						required: true,
						maxlength:200,
						resName:true
					},
				"resource.enName":{
					required: true,
					checkEnName:true,
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
					required: '<font color="red"><s:text name="sdp.sce.dp.admin.resources.resName.notEmpty"/></font>',
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
function doSave() {

	var dpResForm = $("#dpResForm");
	if(validator.form()){
		dpResForm.submit();
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
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/> <s:text name="sdp.sce.dp.admin.resources"/>&gt; <s:text name="sdp.sce.dp.admin.resources.update"/></p>
  <div id="pright"></div>
</div>
<div class="view_nav"><s:text name="sdp.sce.dp.admin.resources.update"/></div>
<s:form id="dpResForm" action="resource!modifyRes.action" method="post">
<s:hidden name="resource.id" value="%{id }" />
<s:hidden name="parentId" value="%{resource.parentRes.id }" />
<s:hidden name="resource.level" value="%{resource.level }" />
<s:hidden name="resource.menuButton" value="%{resource.menuButton }" />
<s:hidden name="resource.createdDate" value="%{resource.createdDate }" />
<s:hidden name="resource.createdUser" value="%{resource.createdUser }" />
<s:hidden name="resource.enName" value="%{resource.enName }" />
<div class="data_view">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.resources.input.resourceName"/></th>
    <td><input id="resName" type="text" name="resource.name" value="${resource.name }"/>
    	<span class="must_input">*</span>
    	<span class="notes"><s:text name="sdp.sce.dp.admin.resources.resname.tip"/></span><!-- <font color='gray'>&nbsp;由字母、数字、下划线、中文、空格组成</font> -->
    </td>
  </tr>
  <tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.resources.resource.input.identifier"/></th>
    <td><input id="resEnName" type="text" name="resource.enName" value="${resource.enName }"  disabled="disabled"/>
    	<span class="must_input">*</span>
    	<span class="notes"><s:text name="sdp.sce.dp.admin.resources.resIdentifier.tip"/></span>
    </td>
  </tr>
  <tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.resources.input.resourceURL"/></th>
    <td><input id="url" type="text" name="resource.url" value="${resource.url }"/>
    	&nbsp;&nbsp;&nbsp;&nbsp;<span class="notes"><s:text name="sdp.sce.dp.admin.resources.resMenu.tip"/></span>
    </td>
  </tr>
  <tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.resources.input.resourceOrder"/></th>
    <td><input id="orderField" type="text" name="resource.orderField" value="${resource.orderField }"/><span class="must_input">*</span>
    	<span class="notes"><s:text name="sdp.sce.dp.admin.resources.resMenu.order"/></span>
    </td>
  </tr>
  <tr>
  <th scope="row"><s:text name="sdp.sce.dp.admin.resources.input.resourceDescription"/></th>
    <td><textarea id="description" name="resource.description" cols="100" rows="4">${resource.description }</textarea><p><span class="notes"><s:text name="sdp.sce.dp.admin.resources.lessthan200.char"/></span></p></td>
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
