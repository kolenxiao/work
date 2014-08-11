<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java"  contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/common/includejs.jsp"%>
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者管理平台</title>


<script type="text/javascript">
insertLanguageJS();
/**
 * 附件下载
 */
function doDownLoad(fileName,fileSaveName){
	var url = "dpdownload!doDownLoad.action?dpDownloadInfo.attachmentFile.fileName="+fileName+"&dpDownloadInfo.attachmentFile.fileSaveName="+fileSaveName;
	document.getElementById("downloadFrame").src=url;
}
function back(){
	location.href = "dpdownload!doList.action";
}

function cantDown(fileName){
	dialogList(Lang['message'],300,150,Lang['file_khs']+fileName+Lang['file_khe']+Lang['file_NotDownload'],0,0,1,this);
}
</script>
</head>
<body id="cnt_body">
	<div id="position">
	 <p>
	 	<s:text name="sdp.sce.dp.admin.global.label.current.position"/>
	 	<s:text name="sdp.sce.dp.admin.download"/>&gt;
	 	<s:text name="sdp.sce.dp.admin.download.detail"/>
	 </p>
	  <div id="pright"></div>
	</div>

	<div class="news_detail">
		<div class="caption">
			<p class="title p2">
				<s:property value="dpDownloadInfo.downloadName" />
			</p>
			<div class="line">
			</div>
			<p class="title_head1">
				<s:text name="sdp.sce.dp.admin.news.date"></s:text>
				：
				<s:date name="dpDownloadInfo.ctime" format="yyyy-MM-dd" />
				<s:text name="sdp.sce.dp.admin.news.type"></s:text>
				：
				<s:property value="dpDownloadInfo.dpType.typeName" />
				<s:text name="来源"></s:text>
				：
				<s:property value="dpDownloadInfo.docSource" />
			</p>
		</div>
		<div class="news_article_small">
			<p class="p1">
				<s:property value="dpDownloadInfo.downloadDesc" escape="false" />
			</p>
		</div>

		<p class="title_head2">
			<s:if test="dpDownloadInfo.attachFileId != null">
				<s:text name="本文档包含以下附件："></s:text>
				<s:if test="dpDownloadInfo.attachmentFile.iscanDown==1">
					<a href="#"
						onclick="doDownLoad('<s:property value="dpDownloadInfo.attachmentFile.fileName" />','<s:property value='dpDownloadInfo.attachmentFile.fileSaveName'/>')">
						<s:property value="dpDownloadInfo.attachmentFile.fileName" />
					</a>
				</s:if>
				<s:if test="dpDownloadInfo.attachmentFile.iscanDown==0">
					<a href="#"
						onclick="cantDown('<s:property value="dpDownloadInfo.attachmentFile.fileName" />')">

						<s:property	value="dpDownloadInfo.attachmentFile.fileName" />
					</a>
				</s:if>
			</s:if>
		</p>
	</div>

	<div class="btnlistbar">
		<input class="inputstyle" type="button"
			value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
			onclick="javascript:history.back(-1)" />
	</div>

	<script type="text/javascript">
			document.write("<iframe id='downloadFrame' style='display: none'></iframe> ");
	</script>
</body>
</html>
