<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.dptype.addtaxnonmic" /></title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#dpType.typeName").focus();
	    var id = document.getElementById("typeId").value;
	  });

//自定义输出错误信息
FormValid.showError = function(errMsg,errName,formName) {
	if (formName=='dpTypeForm') {
		for (key in FormValid.allName) {
			document.getElementById('errMsg_'+FormValid.allName[key]).innerHTML = '';
		}
		for (key in errMsg) {
			document.getElementById('errMsg_'+errName[key]).innerHTML = errMsg[key];
		}
	}
};
	//保存信息
	function doEdit(obj,id) {
		//添加分类
		var url = "dpType!doAdd.action";

		if(id!=null && id!=""){
			//修改分类
			url = 'dpType!doEdit.action';
		}
		var formName = document.getElementById("dpTypeForm");
		formName.action =url;

		var flag = checkTypeNameUnique();

		if(validator(formName) && !flag){
			formName.submit();
		}
	}

	function doBack(){
		location.href ="dpType!doList.action";
	}

	function checkTypeNameUnique(){
		var result = false;
		var typeId = document.getElementById("typeId").value.trim();
		var forward = document.getElementById("forward").value;
		var typeName = document.getElementById("typeName").value.trim();
		var typeCode = document.getElementById("dpType.parentTypeCode").value;
		if (typeName=="" || typeCode=="") {
			if (typeName=="") {
				$("span[id='errMsg_dpType.typeName']").empty().html("分类名称不能为空！");
			}
			if (typeCode=="") {
				$("span[id='errMsg_dpType.parentTypeCode']").empty().html("请选择分类！");
			}
			result = true;
		} else {
		  var parm ={
				  'dpType.typeName':typeName,'dpType.parentTypeCode':typeCode,
				  'dpType.id':typeId,'forward':forward
		  };
		  $.ajax({
			    cache:false,
		        async:false,
		        type:"POST",
			   	url: "dpType!isTypeNameUnique.action",
			   	data: parm,
			    dataType: 'json',
				success: function(response){
				 var dataObj = eval(response);
				 if (dataObj && dataObj.msg) {
					 $("span[id='errMsg_dpType.typeName']").empty().html("分类名称已存在！");
					 result = true;
					}
				}
			});
	    }
		return result;
	}

</script>
<style type="text/css">
	#btn_list{
	margin-top:10px;
}

</style>
<body id="cnt_body" class="data_view">
<s:form id="dpTypeForm" name="dpTypeForm" action="" method="post" cssStyle="margin:0" theme="simple" enctype="multipart/form-data">
	<s:token/>
	<div id="position">
		<p><s:text name="sdp.sce.dp.admin.dptype.pro" />: <s:text name="sdp.sce.dp.admin.system" />&gt; <s:text name="sdp.sce.dp.admin.dptype.name.add" /></p>
		<div id="pright"></div>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="tr_typeFlat">
			<th scope="row"><s:text name="sdp.sce.dp.admin.dptype.Taxonomic.category" />：</th>
			<s:hidden id="typeId" value="%{dpType.id}"></s:hidden>
			<s:hidden id="forward" value="%{forward}"></s:hidden>

			<td>
				<s:select
				 list="dpTypeList" name="dpType.parentTypeCode" id="dpType.parentTypeCode" size="1"
					listKey="typeCode" listValue="typeName" value="%{dpType.parentTypeCode}">
				</s:select>
				<span id="errMsg_dpType.parentTypeCode" style="color:#FF0000"></span>
			</td>
		</tr>
		<tr>
			 <th scope="row">
			<s:text name="sdp.sce.dp.admin.dptype.nametype" />：
			</th>
				<td >
					<s:hidden id="id" name="dpType.id" value="%{dpType.id}"/>
					<s:hidden id="createUser" value="%{dpType.createUser}"></s:hidden>
					<s:textfield id="typeName" name="dpType.typeName"
					value="%{dpType.typeName}" valid="isSpecial|limit" min="0" max="32"
					errmsg="type_typeName_special|type_typeName_lenless32"/>
					<font color="red">*</font>
					<span id="errMsg_dpType.typeName" style="color:#FF0000"></span>
			</td>
		</tr>
		<tr>
			<th scope="row">获取焦点图标：</th>
			<td>
				<s:if test="%{dpType.typeImg1!=null}">
					<a href="${appFilePath.imgPath}${dpType.typeImg1}" target="_blank">${dpType.typeImg1}</a>
				</s:if>
				<input type="file" id="uploadFocus"
					name="uploadFocus" size="30"
					valid="filter" allow="jpg,gif,png"
					errmsg=dp_type_icon_allow/>
					<span id="errMsg_upload" style="color:#FF0000"></span>
			</td>
		</tr>
		<tr>
			<th scope="row">失去焦点图标：</th>
			<td>
			<s:if test="%{dpType.typeImg2!=null}">
				<a href="${appFilePath.imgPath}${dpType.typeImg2}" target="_blank">${dpType.typeImg2}</a>
			</s:if>
			<s:file id="uploadLoseFocus"
					name="uploadLoseFocus" size="30"
					contentEditable="false" valid="filter" allow="jpg,gif,png"
					errmsg="errMsg_upload"/>
				<span id="dp_type_icon_allow" style="color:#FF0000"></span>
			</td>
		</tr>
		<tr>
		<th scope="row"><s:text name="sdp.sce.dp.admin.dptype.descri" />：</th>
		<td >
				<s:hidden id="oldTypeDesc"  name="oldTypeDesc" value="%{dpType.typeDesc}"/>
				<s:textarea id="dpType.typeDesc" name="dpType.typeDesc"  cols="80" rows="8" valid="limit" min="0" max="255"  errmsg="type_typeDesc_lenless64"/>
				 <span id="errMsg_dpType.typeDesc" style="color:#FF0000"></span>
			</td>
		</tr>
	</table>
	<div class="btnlistbar" align="left"  style="margin-top:0px;">
			<s:if test="%{dpType.id != null}">
				<input class="inputstyle" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.save" />" onclick="doEdit(this,'<s:property value="%{dpType.id}"/>')" align="right"/>
			</s:if>
			<s:else>
				<input class="inputstyle" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.add" />" onclick="doEdit(this,'<s:property value="%{dpType.id}"/>')"/>
			</s:else>
			 <input class="inputstyle" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.cancel" />" onclick="doBack()"/>
		</div>
</s:form>
<script type="text/javascript">
	initValid(document.dpTypeForm);
	insertLanguageJS();
</script>
</body>
</html>
