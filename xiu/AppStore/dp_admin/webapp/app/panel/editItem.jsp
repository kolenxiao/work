<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改元素</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/uploadify/uploadify.css">
<script src="${pageContext.request.contextPath}/uploadify/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/upload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/common.js" type="text/javascript"></script>
<script type="text/javascript">
function doUpdate() {
	var downloadform = document.getElementById("appRecommendPanelItemForm");
	if (validator(downloadform)) {
		var itemType = document.getElementById("aformObj.type").value;
		if(itemType == 3) {
			var typeValue = document.getElementById("showName").value;
			document.getElementById("aformObj.typeValue").value = typeValue;
		}
		downloadform.submit();
	}
}

function isNumber(){
	var sortNum = document.getElementById("aformObj.sortNum").value;
	if(sortNum == "" || !isNaN(sortNum)){
		return true;
	}
	return false;
}

function backToDocList() {
	location.href = "appRecommendPanelItem!doList.action";
}

var subjectPoster={
		inputID :'aformObj.itemPoster',//文件实际存储名称
		multiple:true,
		ID:"file4",
		contextPath:'${pageContext.request.contextPath}',
		requestURL:'/upload!doUpload.action;jsessionid=${pageContext.session.id}',
		fileObjName:'file',
		fileType:'*.gif;*.jpg;*.jpeg;*.png;*.GIF;*.JPG;*.JPEG;*.PNG',
		formdata:{'flag':'4'},
		parentDIV:'div_poster',
		buttonImage:'${pageContext.request.contextPath}/uploadify/browse-btn.png',
		err_msg_id:'errMsg_aformObj.itemPoster',
		callback:onUploadSingleFileSuccess
};

function onChangeType(){
	document.getElementById("aformObj.typeValue").value="";
	document.getElementById("showName").value="";
	doChange();
}

//设置输入框的读写
function doChange(){
	$("span[id='errMsg_aformObj.typeValue']").empty();
    var val = document.getElementById("aformObj.type").value;
	if(val == 3){
        $("#showName").attr("readonly",false);
        $("#subject").hide();
        $("#type").hide();
	 }else{
		$("#showName").attr("readonly",true);
		if(val == 1){
			$("#type").show();
			$("#subject").hide();
		}else{
			$("#subject").show();
			$("#type").hide();
		}
	 }
}

function checkItemType(){
	var val = document.getElementById("aformObj.type").value;
	if(val != 3){
		var typeVal = document.getElementById("aformObj.typeValue").value;
		if(typeVal == "")
			return false;
		
	}
	return true;
}

function openWindowDialog(action){
	var jsonObj = {scrollType : 'yes'};
	top.openshow(action,
			'<s:text name="sdp.sce.dp.admin.ap.appinfo.detail" />', 1000,
	540, 1, jsonObj);
}

function callBack(o){
   var jsonobj=eval('('+o+')');
   document.getElementById("aformObj.typeValue").value=jsonobj.id;
   $("#showName").val(jsonobj.name);
}


$(document).ready(function(){
	doChange();
	var uploadSubjectPoster = new SingleUpload(subjectPoster);
	uploadSubjectPoster.bindUplodify();
});
</script>
<script type="text/javascript">
//自定义输出错误信息
FormValid.showError = function(errMsg, errName, formName) {
	if (formName == 'appRecommendPanelItemForm') {
		for (key in FormValid.allName) {
			document.getElementById('errMsg_' + FormValid.allName[key]).innerHTML = '';
		}
		for (key in errMsg) {
			document.getElementById('errMsg_' + errName[key]).innerHTML = errMsg[key];
		}
	}
};
</script>
</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.global.label.current.position" />&gt;精选页版块管理&gt;修改元素
		</p>
		<div id="pright"></div>
	</div>
	<div class="view_nav">
		修改元素
	</div>
	<s:form id="appRecommendPanelItemForm" name="appRecommendPanelItemForm"
		action="appRecommendPanelItem!doUpdateItem.action" method="post" cssStyle="margin:0"
		theme="simple" >
		<s:hidden name="aformObj.id" id="aformObj.id" value="%{aformObj.id}"/>
		<s:token/>
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row">
						元素名称：
					</th>
					<td>
						<s:textfield name="aformObj.itemName" size="35"
							id="aformObj.itemName" value="%{aformObj.itemName}"
							valid="required|limit" min="0" max="100"
							errmsg="dp_itemName__notEmpty|dp_itemName_lenlessthan_100" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
								<s:property value="message" />
							</label>
						</span>
						<span id="errMsg_aformObj.itemName" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
				   	<th scope="row">
						元素类型：
					</th>
					<td>
                        <s:select name="aformObj.type" id="aformObj.type" onchange="onChangeType()"
							list="#{'1':'应用','2':'专题','3':'文本'}" label="元素类型"></s:select>
					</td>
				</tr>
				<tr>
				   	<th scope="row">
						元素类型标识值：
					</th>
					<td>
					   <s:hidden name="aformObj.typeValue" id="aformObj.typeValue" valid="custom" custom="checkItemType" errmsg="dp_itemTypeValue__notEmpty" value="%{aformObj.typeValue}"/>
                       <input type="text" name="showName" id="showName" readonly="readonly" style="height:20px;width:220px;" value="${showName}"/>
                       <input type="button" value="选择应用" id="type" style="display:none;" onclick="openWindowDialog('dpAppInfo!doAppList.action')">
                       <input type="button" value="选择专题" id="subject" style="display:none;"  onclick="openWindowDialog('dpAppSubjectType!doSearchAppSubjectTypeListByItem.action')">
					   <span class="must_input">*&nbsp;&nbsp;</span>
					   <span id="errMsg_aformObj.typeValue" style="color: #FF0000"></span>
					   <span>&nbsp;&nbsp;(如果元素类型为文本，应用类型取值：GAME_TYPE和APP_TYPE，格式为： 应用类型;类型id，如: GAME_TYPE;11003)</span>
					</td>
				</tr>
	            <tr>
		            <th scope="row">元素海报图片</th>
			            <td>
			            <div align="left" id="div_poster" style="width:200px;float:left">
				        <input name="file4" type="file" id="file4">
				        <s:hidden name="aformObj.itemPoster" id="aformObj.itemPoster" valid="required"  errmsg="download_upload_length_is0" value="%{aformObj.itemPoster}"/>
					    <span style="color: red" id="errMsg_posters"> </span>
					    <span style="color: red" id='err_file4'></span>
                        <s:if test="%{aformObj.itemPoster != null}"> 
						<p id='file_<s:property value="aformObj.itemPoster"/>' style="margin-left:15px;">
						    ${aformObj.itemPoster}
							<a href='#' onclick="removeAndCleanFile('div_poster','file_${aformObj.itemPoster}','aformObj.itemPoster')"><font color="red">删除</font></a>
						</p>
					   </s:if>
					  </div>
					  <strong style="color: #F00;">*</strong>
					  <span id="errMsg_aformObj.itemPoster" style="color: #FF0000"></span>
			     </td>
	            </tr>
				<tr>
				   	<th scope="row">
						显示顺序：
					</th>
					<td>
                     <s:textfield name="aformObj.sortNum" size="20" id="aformObj.sortNum" valid="custom"
                       custom="isNumber" value="%{aformObj.sortNum}" errmsg="dp_sortNum_isNum" />
					<span id="errMsg_aformObj.sortNum" style="color: #FF0000"></span>
				   </td>
				</tr>
				
				<tr>
					<th scope="row">
						元素描述：
					</th>
					<td>     
                        <s:textarea name="aformObj.panelDesc" id="aformObj.panelDesc" cssStyle="width:500px;height:200px"
                        valid="limit" min="0" max="200"></s:textarea>
						<span id="errMsg_aformObj.panelDesc" style="color: #FF0000"></span>
					</td>
				</tr>
			</table>
		</div>
		
		
		<div class="btnlistbar">
			<input class="inputstyle" type="button" onclick="doUpdate()"
				value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>" />
			<input class="inputstyle" type="button" onclick="backToDocList()"
				value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.appRecommendPanelItemForm);
		insertLanguageJS();
	</script>
</body>
</html>
