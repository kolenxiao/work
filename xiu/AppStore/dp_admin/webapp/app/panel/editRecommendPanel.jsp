<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改版块信息</title>
<script type="text/javascript" src="<%=ctxPath%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
function doUpdate() {
	var downloadform = document.getElementById("appRecommendPanelForm");
	if (validator(downloadform)) {
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

function backToPanelList() {
	location.href = "appRecommendPanel!doList.action";
}
</script>
<script type="text/javascript">
	//自定义输出错误信息
	FormValid.showError = function(errMsg, errName, formName) {
		if (formName == 'appRecommendPanelForm') {
			for (key in FormValid.allName) {
				document.getElementById('errMsg_' + FormValid.allName[key]).innerHTML = '';
			}
			for (key in errMsg) {
				document.getElementById('errMsg_' + errName[key]).innerHTML = errMsg[key];
			}
		}
	};
</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.global.label.current.position" />&gt;精选页版块管理> 修改版块信息
		</p>
		<div id="pright"></div>
	</div>
	<div class="view_nav">
		修改版块信息
	</div>
	<s:form id="appRecommendPanelForm" name="appRecommendPanelForm"
		action="appRecommendPanel!doUpdatePanel.action" method="post" cssStyle="margin:0"
		theme="simple">
		<s:token/>
		<input type="hidden" name="aformObj.id" id="aformObj.id" value="${aformObj.id}">
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row">
						版块名称：
					</th>
					<td>
						<s:textfield name="aformObj.panelName" size="55"
							id="aformObj.panelName" value="%{aformObj.panelName}"
							valid="required|limit" min="0" max="100"
							errmsg="dp_panelName__notEmpty|dp_panelName_lenlessthan_100" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
								<s:property value="message" />
							</label>
						</span>
						<span id="errMsg_aformObj.panelName" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
				   	<th scope="row">
						布局类型：
					</th>
					<td>
                     <s:select name="aformObj.layoutType" id="aformObj.layoutType" list="#{'1':'栏位','2':'入口'}" label="布局类型"
							></s:select>
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
						版块描述：
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
			<input class="inputstyle" type="button" onclick="backToPanelList()"
				value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.appRecommendPanelForm);
		insertLanguageJS();
	</script>
</body>
</html>
