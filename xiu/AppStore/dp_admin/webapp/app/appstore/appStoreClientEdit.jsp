<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑客户端</title>
<script type="text/javascript">

	$(document).ready(function() {
		//$("#dpType.typeName").focus();
	});

	//自定义输出错误信息
	FormValid.showError = function(errMsg, errName, formName) {
		if (formName == 'appStoreClientForm') {
			for (key in FormValid.allName) {
				document.getElementById('errMsg_' + FormValid.allName[key]).innerHTML = '';
			}
			for (key in errMsg) {
				document.getElementById('errMsg_' + errName[key]).innerHTML = errMsg[key];
			}
		}
	};
	//保存信息
	function doSaveEditAppClient(url) {
		var flag = isTeminalNumSelect();
		if (!flag) {
			$("#errMsg_teminalNum").html("请选择终端型号");
		} else {
			var formName = document.getElementById("appStoreClientForm");
			formName.action = url;
			if (validator(formName)) {
				formName.submit();
			}
		}
	}

	function isTeminalNumSelect() {
		var str = document.getElementsByName("appStoreClient.teminalNum");
		var objarray = str.length;
		var chestr = "";
		for ( var i = 0; i < objarray; i++) {
			if (str[i].checked == true) {
				chestr += str[i].value + ",";
			}
		}

		if (chestr == "") {
			return false;
		} else {
			$("#errMsg_teminalNum").html("");
		}

		return true;
	}

	function doBack() {
		location.href = "appStoreClient!doList.action";
	}
</script>

</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name='sdp.sce.dp.admin.global.label.current.position' />
			<s:text name='sdp.sce.dp.admin.appstore.appStoreManage' />
			&gt;
			<s:if test="%{appStoreClient.id != null }">
				<s:text name='sdp.sce.dp.admin.appstore.modify' />
			</s:if>
			<s:else>
				<s:text name='sdp.sce.dp.admin.appstore.add' />
			</s:else>
		</p>
		<div id="pright"></div>
	</div>
	<div class="view_nav">
		<s:if test="%{appStoreClient.id != null }">
			<s:text name='sdp.sce.dp.admin.appstore.modify' />
		</s:if>
		<s:else>
			<s:text name='sdp.sce.dp.admin.appstore.add' />
		</s:else>
	</div>
	<s:form id="appStoreClientForm" name="appStoreClientForm" action="" method="post"
		cssStyle="margin:0" theme="simple" enctype="multipart/form-data">
		<input type="hidden" name="appStoreClient.id" value="${appStoreClient.id}"/>
		<s:token/>
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.appstore.name" />
					</th>
					<td>
						<s:textfield name="appStoreClient.appName" size="50"
							id="appStoreClient.appName" value="%{appStoreClient.appName}"
							valid="required|limit|regexp" regexp="^[\u4E00-\u9FA5A-Za-z0-9]+$" min="0" max="100"
							errmsg="appStoreClient_appName_notEmpty|appStoreClient_appName_lenlessthan_100|appStoreClient_appName_special" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
								<s:property value="message" />
							</label>
						</span>
						<span id="errMsg_appStoreClient.appName" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
				<!-- 修改 -->
				<s:if test="%{appStoreClient.id != null }">
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.appstore.apkFile" />
					</th>
					<td>
						<s:text	name="appStoreClient.apkFileSavePath" /><br/>
						<input type="file" id="appStoreClientPackage" name="appStoreClientPackage"
						size="50" valid="filter"
						allow="apk"
						errmsg="appStoreClientPackage_select" />
						<span class="style-red" id="error">*</span>
						<span id="errMsg_appStoreClientPackage" style="color: #FF0000"></span>
					</td>
				</s:if>
				<!-- 新增 -->
				<s:else>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.appstore.apkFile" />
					</th>
					<td>
						<input type="file" id="appStoreClientPackage" name="appStoreClientPackage"
						size="50" valid="required|filter"
						allow="apk"
						errmsg="appStoreClientPackage_notEmpty|appStoreClientPackage_select" />
						<span class="style-red" id="error">*</span>
						<span id="errMsg_appStoreClientPackage" style="color: #FF0000"></span>
					</td>
				</s:else>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.appstore.minSystem" />
					</th>
					<td>
						<s:select name="appStoreClient.system"
									id="appStoreClient.system" value="%{appStoreClient.system}" headerKey=""
									headerValue="" list="androidDpTypeList" listKey="typeCode"
									listValue="typeName" valid="required" min="0" max="255"
									errmsg="appStoreClient_system_required"
						></s:select>
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
								<s:property value="message" />
							</label>
						</span>
						<span id="errMsg_appStoreClient.system" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						终端型号
					</th>
					<td>
						<label>
							<input name="appStoreClient.teminalNum" id="teminalNum"
							type="radio" value="1"
							<s:if test='%{appStoreClient.teminalNum == "1" || appStoreClient.teminalNum == "0"}'>
								checked='checked'</s:if> id="mstar"  /> MSTAR
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
							<input id="hisi" name="appStoreClient.teminalNum" id="teminalNum"
							 type="radio" value="2"
							<s:if test='%{appStoreClient.teminalNum == "2" || appStoreClient.teminalNum == "0"}'>
								checked='checked'</s:if> /> HISI
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
							<input id="mtk" name="appStoreClient.teminalNum" id="teminalNum"
							 type="radio" value="3"
							<s:if test='%{appStoreClient.teminalNum == "3" || appStoreClient.teminalNum == "0"}'>
								checked='checked'</s:if> /> MTK
						</label>
						<span class="style-red" id="error">*</span>
						<span id="errMsg_teminalNum" style="color: #FF0000"></span>
					</td>
				</tr>

			</table>
		</div>
		<div class="btnlistbar">
			<s:if test="%{appStoreClient.id != null}">
				<input class="inputstyle" type="button"
					value="<s:text name='sdp.sce.dp.admin.global.btn.confirm'/>"
					onclick="doSaveEditAppClient('appStoreClient!doModify.action')" />
			</s:if>
			<s:else>
				<input class="inputstyle" type="button"
					value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>"
					onclick="doSaveEditAppClient('appStoreClient!doAdd.action')" />
			</s:else>
			<input class="inputstyle" type="button"
				value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
				onclick="doBack();" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.appStoreClientForm);
		insertLanguageJS();
	</script>
</body>
</html>
