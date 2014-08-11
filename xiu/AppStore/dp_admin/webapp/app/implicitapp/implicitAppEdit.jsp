<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑隐式应用</title>
<script type="text/javascript">

	//自定义输出错误信息
	FormValid.showError = function(errMsg,errName,formName) {
	if (formName=='implicitAppForm') {
		for (key in FormValid.allName) {
			document.getElementById('errMsg_'+FormValid.allName[key]).innerHTML = '';
		}
		for (key in errMsg) {
			document.getElementById('errMsg_'+errName[key]).innerHTML = errMsg[key];
		}
	}
	};
	//保存信息
	function doSaveEditImplicitApp(url) {
		var flag = isTeminalNumSelect();
		if (!flag) {
			$("#errMsg_teminalNum").html("请选择终端型号");
		} else {
			var formName = document.getElementById("implicitAppForm");
			formName.action =url;
			if(validator(formName)){
				formName.submit();
			}
		}

	}

	function isTeminalNumSelect() {
		var str=document.getElementsByName("implicitApp.teminalNum");
		var objarray=str.length;
		var chestr="";
		for (var i = 0; i < objarray; i++) {
			if (str[i].checked == true) {
		   		chestr += str[i].value + ",";
		  	}
		}

		if(chestr == "") {
			return false;
		} else {
			$("#errMsg_teminalNum").html("");
		}

		return true;
	}


	function doBack(){
		location.href ="implicit!doList.action";
	}

	// 选择不同开发者类型显示不同信息
	function changeImpType(sel) {
		var impType = $(sel).val();
		$("#apkFileId").remove();
		$("#filePackageId").remove();
		if (impType == '3') {
			$("#impTypeId").after(filePackageHtml);
		} else {
			$("#impTypeId").after(apkFileHtml);
		}

	}

	var filePackageHtml="";	// 包名
	var apkFileHtml="";		// 应用apk
	function initImplicitPage()
	{
		var implicitAppId = $("#implicitAppId").val();
		filePackageHtml = $("#filePackageId");
		apkFileHtml=$("#apkFileId");
		if (implicitAppId != undefined) {
			// 表明是修改操作
			$("#apkFileId").remove();
			$("#filePackageId").remove();

			var impType = $("#impType").val();
			if (impType == '3') {
				// 表明是卸载操作
				$("#impTypeId").after(filePackageHtml);
			} else {
				$("#impTypeId").after(apkFileHtml);
			}
		} else {
			// 表明新增操作
			$("#impTypeId").after(apkFileHtml);
		}

	}

</script>

</head>
<body id="cnt_body" onload="initImplicitPage()">
	<div id="position">
		<p>
			<s:text name='sdp.sce.dp.admin.global.label.current.position' />
			隐式应用管理
			&gt;
			<s:if test="%{implicitApp.id != null }">
				修改隐式应用
			</s:if>
			<s:else>
				新增隐式应用
			</s:else>
		</p>
		<div id="pright"></div>
	</div>
	<div class="view_nav">
		<s:if test="%{implicitApp.id != null }">
			<s:text name='sdp.sce.dp.admin.implicit.modify' />
		</s:if>
		<s:else>
			<s:text name='sdp.sce.dp.admin.implicit.add' />
		</s:else>
	</div>
	<s:form id="implicitAppForm" name="implicitAppForm" action="" method="post"
		cssStyle="margin:0" theme="simple" enctype="multipart/form-data">
		<s:hidden  id="implicitAppId" name="implicitApp.id" value="%{implicitApp.id}">
		</s:hidden>
		<s:hidden id="impType" name="impType" value="%{impType}"></s:hidden>
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.appstore.name" />
					</th>
					<td>
						<s:textfield name="implicitApp.appName" size="50"
							id="implicitApp.appName" value="%{implicitApp.appName}"
							valid="required|limit|regexp" regexp="^[\u4E00-\u9FA5A-Za-z0-9]+$" min="0" max="100"
							errmsg="implicitApp_appName_notEmpty|implicitApp_appName_lenlessthan_100|implicitApp_appName_special" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
								<s:property value="message" />
							</label>
						</span>
						<span id="errMsg_implicitApp.appName" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr id="impTypeId">
					<th scope="row" >
						执行类型
					</th>
					<td>
						<s:select
							list="#{'1':'安装','2':'升级','3':'卸载'}"
							id="implicitApp.implicitType"
							name="implicitApp.implicitType"
							value="%{implicitApp.implicitType}"
							onchange="changeImpType(this);"
							>
						</s:select>
					</td>
				</tr>
				<tr id="filePackageId">
					<th scope="row">APK包名</th>
					<td>
						<s:textfield name="implicitApp.appFilePackage" size="50"
							id="implicitApp.appFilePackage" value="%{implicitApp.appFilePackage}"
							valid="required|limit" min="0" max="100"
							errmsg="implicitApp_apkPackage_notEmpty|implicitApp_apkPackage_lenlessthan_100" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
								<s:property value="message" />
							</label>
						</span>
						<span id="errMsg_implicitApp.appFilePackage" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr id="apkFileId">
					<s:if test="%{implicitApp.id != null }">
					<!-- 修改start -->
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.implicit.apkFile" />
					</th>
					<td>
						<s:text	name="implicitApp.apkFileSavePath" /><br/>
						<input type="file" id="implicitAppPackage" name="implicitAppPackage"
						size="50" valid="filter"
						allow="apk"
						errmsg="implicitAppPackage_select" />
						<span class="style-red" id="error">*</span>
						<span id="errMsg_implicitAppPackage" style="color: #FF0000"></span>
					</td>
					<!-- 修改end -->
					</s:if>
					<s:else>
					<!-- 新增start -->
						<th scope="row">
							<s:text	name="sdp.sce.dp.admin.appstore.apkFile" />
						</th>
						<td>
							<input type="file" id="implicitAppPackage" name="implicitAppPackage"
								size="50" valid="required|filter" allow="apk"
								errmsg="implicitAppPackage_notEmpty|implicitAppPackage_select" />
							<span class="style-red" id="error">*</span>
							<span id="errMsg_implicitAppPackage" style="color: #FF0000"></span>
						</td>
					<!-- 新增end -->
					</s:else>
				</tr>

				<tr>
					<th scope="row">
						终端型号
					</th>
					<td>
						<label>
							<input name="implicitApp.teminalNum" id="teminalNum"
							type="radio" value="1"
							<s:if test='%{implicitApp.teminalNum == "1" || implicitApp.teminalNum == "0"}'>
								checked='checked'</s:if> id="mstar"  /> MSTAR
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
							<input id="hisi" name="implicitApp.teminalNum" id="teminalNum"
							 type="radio" value="2"
							<s:if test='%{implicitApp.teminalNum == "2" || implicitApp.teminalNum == "0"}'>
								checked='checked'</s:if> /> HISI
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label>
							<input id="mtk" name="implicitApp.teminalNum" id="teminalNum"
							 type="radio" value="3"
							<s:if test='%{implicitApp.teminalNum == "3" || implicitApp.teminalNum == "0"}'>
								checked='checked'</s:if> /> MTK
						</label>
						<span class="style-red" id="error">*</span>
						<span id="errMsg_teminalNum" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<tr>
					<th scope="row">应用描述</th>
					<td>
						<s:textarea name="implicitApp.description"
							cols="50" rows="8" id="implicitApp.description" style="width:350px;" />
					</td>
				</tr>
			</table>
		</div>
		<div class="btnlistbar">
			<s:if test="%{implicitApp.id != null}">
				<input class="inputstyle" type="button"
					value="<s:text name='sdp.sce.dp.admin.global.btn.confirm'/>"
					onclick="doSaveEditImplicitApp('implicit!doModify.action')" />
			</s:if>
			<s:else>
				<input class="inputstyle" type="button"
					value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>"
					onclick="doSaveEditImplicitApp('implicit!doAdd.action')" />
			</s:else>
			<input class="inputstyle" type="button"
				value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
				onclick="doBack();" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.implicitAppForm);
		insertLanguageJS();
	</script>
</body>
</html>
