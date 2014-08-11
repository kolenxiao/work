<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者管理平台</title>
<script type="text/javascript">
	//提交表单
	function doSave() {
		var downloadform = document.getElementById("dpdownloadForm");
		KE.sync('downloadDesc');
		if (validator(downloadform)) {
			downloadform.submit();
		}
	}

	/** 开发文档 */
	function backToDocList() {
		location.href = "dpdownload!doList.action";
	}

</script>
<script type="text/javascript"
	src="<%=ctxPath%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
	// 编辑器插件
	KE.show({
		id : "downloadDesc",
		width : "80%", //编辑器的宽度为80%
		height : "250px", //编辑器的高度为250px
		filterMode : true, //不会过滤HTML代码
		resizeMode : 2,//编辑器只能调整高度
		imageUploadJson : '../../jsp/upload_json.jsp',
		allowPreviewEmoticons : true
	});
</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.global.label.current.position" />
			<s:text name="sdp.sce.dp.admin.download" />
			&gt;
			<s:text name="sdp.sce.dp.admin.download.add" />
		</p>
		<div id="pright"></div>
	</div>
	<div class="view_nav">
		<s:text name="sdp.sce.dp.admin.download.add" />
	</div>
	<s:form id="dpdownloadForm" name="dpdownloadForm"
		action="dpdownload!doAdd.action" method="post" cssStyle="margin:0"
		theme="simple" enctype="multipart/form-data">
		<s:token/>
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.download.downloadName" />
					</th>
					<td>
						<s:textfield name="dpDownloadInfo.downloadName" size="55"
							id="downloadName" value="%{dpDownloadInfo.downloadName}"
							valid="required|limit" min="0" max="100"
							errmsg="download_downloadName_notEmpty|download_downloadName_lenlessthan_100" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
								<s:property value="message" />
							</label>
						</span>
						<span id="errMsg_dpDownloadInfo.downloadName" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.download.source" />
					</th>
					<td>
						<s:textfield name="dpDownloadInfo.docSource" id="docSource"
							value="%{dpDownloadInfo.docSource}" valid="limit" min="0" max="128"
							errmsg="download_source_lenLess128" size="23" />
							<span id="errMsg_dpDownloadInfo.docSource" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text name="sdp.sce.dp.admin.download.source.url" />
					</th>
					<td>
						<s:textfield name="dpDownloadInfo.docSourceUrl" id="docSourceUrl" size="55"
							value="%{dpDownloadInfo.docSourceUrl}"	valid="isUrl|limit" min="0" max="128"
							errmsg="download_docSourceURL_special|download_docSource_lenLess128" />
						<span id="errMsg_dpDownloadInfo.docSourceUrl" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.download.belong.type" />
					</th>
					<td>
						<s:select id="typeId" name="dpType.id" size="1"
							list="dpTypeList" listKey="id" listValue="typeName"
							value="%{dpType.id}" headerKey="" headerValue=""
							cssStyle="width:175px" valid="required"
							errmsg="download_downloadtype_notEmpty" >
						</s:select>
						<span class="style-red">*</span>
						<span id="errMsg_dpType.id"	style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.download.description" />
					</th>
					<td>
						<s:textarea name="dpDownloadInfo.downloadDesc"
							id="downloadDesc" cols="90" rows="12"
							value="%{dpDownloadInfo.downloadDesc}" valid="required|limit" min="1"
							max="300000" errmsg="download_docContent_notEmpty|download_downloadDesc_lenlessthan300000">
						</s:textarea>
						<span id="errMsg_dpDownloadInfo.downloadDesc" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.download.upload.file" />
					</th>
					<td>
						<input type="file" id="attachment" name="attachment"
						size="80" valid="filter"
						allow="doc,xls,ppt,pdf,exe,txt,rar,zip,war"
						errmsg="download_downloadFile_select" />
						<span class="style-red" id="error">*</span>
						<span id="errMsg_attachment" style="color: #FF0000"></span>
					</td>
				</tr>
			</table>
		</div>
		<div class="btnlistbar">
			<input class="inputstyle" type="button" onclick="doSave()"
				value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>" />
			<input class="inputstyle" type="button" onclick="backToDocList()"
				value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.dpdownloadForm);
		insertLanguageJS();
	</script>
</body>
</html>
