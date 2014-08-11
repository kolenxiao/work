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
			<s:text name="sdp.sce.dp.admin.download.update" />
		</p>
		<div id="pright"></div>
	</div>
	<div class="view_nav">
		<s:text name="sdp.sce.dp.admin.download.update" />
	</div>
	<s:form id="dpdownloadForm" name="dpdownloadForm"
		action="" method="post" cssStyle="margin:0"
		theme="simple" enctype="multipart/form-data">
		<s:token/>
		<s:hidden name="dpDownloadInfo.id" id="dpDownloadInfo.id"
			value="%{dpDownloadInfo.id}"></s:hidden>
		<s:hidden name="dpDownloadInfo.attachmentFile.id"
			id="dpDownloadInfo.attachmentFile.id"
			value="%{dpDownloadInfo.attachmentFile.id}"></s:hidden>
		<s:hidden name="dpDownloadInfo.attachFileId"
			id="dpDownloadInfo.attachFileId"
			value="%{dpDownloadInfo.attachFileId}"></s:hidden>
		<s:hidden name="dpDownloadInfo.ctime" id="ctime"
			value="%{dpDownloadInfo.ctime}"></s:hidden>
		<s:hidden name="dpDownloadInfo.createUser"
			id="dpDownloadInfo.createUser" value="%{dpDownloadInfo.createUser}"></s:hidden>
		<s:hidden name="dpDownloadInfo.showIndex"
			id="dpDownloadInfo.showIndex" value="%{dpDownloadInfo.showIndex}"></s:hidden>
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row"><s:text
							name="sdp.sce.dp.admin.download.downloadName"></s:text>
					</th>
					<s:hidden id="oldDownloadName" name="oldDownloadName"
						value="%{dpDownloadInfo.downloadName}" />
					<td>
						<s:textfield name="dpDownloadInfo.downloadName" size="55"
							id="downloadName" value="%{dpDownloadInfo.downloadName}"
							valid="required|limit" min="0" max="100"
							errmsg="download_downloadName_notEmpty|download_downloadName_lenlessthan_100" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
								<s:property	value="message" />
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
						<s:textfield name="dpDownloadInfo.docSource"
							id="docSource" value="%{dpDownloadInfo.docSource}" valid="limit"
							min="0" max="128" errmsg="download_source_lenLess128" size="23" />
						<span id="errMsg_dpDownloadInfo.docSource" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.download.source.url" />
					</th>
					<td>
						<s:textfield name="dpDownloadInfo.docSourceUrl"
							id="docSourceUrl" size="55"
							value="%{dpDownloadInfo.docSourceUrl}" valid="isUrl|limit"
							min="0" max="128"
							errmsg="download_docSourceURL_special|download_docSource_lenLess128" />
						<span id="errMsg_dpDownloadInfo.docSourceUrl" style="color: #FF0000">
						</span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.download.belong.type" />
					</th>

					<td>
						<s:select id="typeId" name="dpDownloadInfo.dpType.id"
							size="1" list="dpTypeList" listKey="id" listValue="typeName"
							value="%{dpDownloadInfo.dpType.id}" headerKey="" headerValue=""
							cssStyle="width:175px" select="selected" valid="required"
							errmsg="download_downloadtype_notEmpty" />
						<span class="style-red">*</span>
						<span id="errMsg_dpDownloadInfo.dpType.id" style="color: #FF0000"></span>
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
						<s:text	name="sdp.sce.dp.admin.download.oldfile" />
					</th>
					<td>
						<s:if test="dpDownloadInfo.attachFileId != null">
							<!-- 2011.10.31 Edit 使用链接形式 -->
							<s:if test="dpDownloadInfo.attachmentFile.iscanDown==1">
								<a href="#"
									onclick="doDownLoad('<s:property value="dpDownloadInfo.attachmentFile.fileName" />','<s:property value='dpDownloadInfo.attachmentFile.fileSaveName'/>')">
									<s:property	value="fileName" />
									<s:property value="dpDownloadInfo.attachmentFile.fileName" />
								</a>
							</s:if>
							<s:if test="dpDownloadInfo.attachmentFile.iscanDown==0">
								<a href="#"
									onclick="cantDown('<s:property value="dpDownloadInfo.attachmentFile.fileName" />')">

									<s:property value="fileName" /> <s:property
										value="dpDownloadInfo.attachmentFile.fileName" />
								</a>
							</s:if> <%-- <a href="http://<%=request.getLocalName() %>:<%=request.getServerPort()%>/upload/download/${dpDownloadInfo.attachmentFile.fileSaveName }" > <s:property value="dpDownloadInfo.attachmentFile.fileName" /></a> --%>
						</s:if>
						<!-- ended of 2011.10.31 Edit -->
					</td>
				</tr>
				<tr>
					<th scope="row"><s:text
							name="sdp.sce.dp.admin.download.upload.file" />
					</th>
					<td>
					<input type="file" id="attachment" name="attachment"
						size="80" valid="filter"
						allow="doc,xls,ppt,pdf,exe,txt,rar,zip,war"
						errmsg="download_downloadFile_notEmpty|download_downloadFile_select" />
						<span class="style-red" id="error"></span> <span
						id="errMsg_attachment" style="color: #FF0000"></span></td>
				</tr>
			</table>
		</div>
		<div class="btnlistbar">
			<input class="inputstyle" type="button"
				value="<s:text name='sdp.sce.dp.admin.global.btn.confirm'/>"
				onclick="doSaveDoc('dpdownload!doEdit.action')" />
			<input class="inputstyle" type="button"
				value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
				onclick="backToDownList()" />
		</div>
	</s:form>
	<script type="text/javascript">
		document.write("<iframe id='downloadFrame' style='display: none'></iframe> ");
		insertLanguageJS();
		initValid(document.dpdownloadForm);
</script>
</body>
</html>
