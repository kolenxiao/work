<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<%
    request.setCharacterEncoding("UTF-8");
			String htmlData = request.getParameter("newsContent") != null
					? request.getParameter("newsContent")
					: "";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者管理平台</title>
<script type="text/javascript"
	src="<%=ctxPath%>/kindeditor/kindeditor.js"></script>
<script type="text/javascript">
	KE.show({
		id : "newsContent",
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
			<s:text name='sdp.sce.dp.admin.global.label.current.position' />
			<s:text name='sdp.sce.dp.admin.news' />
			&gt;
			<s:if test="%{dpNews.id != null }">
				<s:text name='sdp.sce.dp.admin.news.update.news' />
			</s:if>
			<s:else>
				<s:text name='sdp.sce.dp.admin.news.add.news' />
			</s:else>
		</p>
		<div id="pright"></div>
	</div>
	<div class="view_nav">
		<s:if test="%{dpNews.id != null }">
			<s:text name='sdp.sce.dp.admin.news.add.news' />
		</s:if>
		<s:else>
			<s:text name='sdp.sce.dp.admin.news.add.news' />
		</s:else>
	</div>
	<s:form id="dpNewsForm" name="dpNewsForm" action="" method="post"
		cssStyle="margin:0" theme="simple">
		<s:token/>
		<!-- 2011-11-25 start -->
		<s:hidden name="dpNews.id" id="dpNews.id" value="%{dpNews.id}"></s:hidden>
		<s:hidden name="dpNews.newsCreateTime" id="newsCreateTime"
			value="%{dpNews.newsCreateTime}"></s:hidden>
		<s:hidden name="dpNews.createUser" id="dpNews.createUser"
			value="%{dpNews.createUser}"></s:hidden>
		<s:hidden name="dpNews.newsStstus" id="dpNews.newsStstus"
			value="%{dpNews.newsStstus}"></s:hidden>
		<s:hidden name="dpNews.newsSummary" id="dpNews.newsSummary"
			value="%{dpNews.newsSummary}"></s:hidden>
		<!-- ended of 2011-11-25 -->

		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row"><s:text
							name="sdp.sce.dp.admin.news.newsTitle_" />
					</th>
					<s:hidden id="oldNewsTitle" name="oldNewsTitle" value="%{dpNews.newsTitle}" />
					<td>
						<s:textfield name="dpNews.newsTitle" id="newsTitle" size="55"
							value="%{dpNews.newsTitle}" valid="required|limit"
							min="0" max="128"
							errmsg="news_newsTitle_notEmpty|news_newsTitle_lenLess128" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
							<s:property	value="message" /> </label>&nbsp;&nbsp;
						</span>
						<span id="errMsg_dpNews.newsTitle" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.news.news.comefrom" />
					</th>
					<td>
						<s:textfield name="dpNews.newsSource" id="newsSource"
							value="%{dpNews.newsSource}" valid="limit" min="0" max="128"
							errmsg="news_newsSource_lenLess128" size="23" />
							<span id="errMsg_dpNews.newsSource" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text name="sdp.sce.dp.admin.news.newsUrl" />
					</th>
					<td>
						<s:textfield name="dpNews.newsSourceUrl" id="newsSourceUrl" size="55"
							value="%{dpNews.newsSourceUrl}"	valid="isUrl|limit" min="0" max="128"
							errmsg="news_newsSourceURL_special|news_newsSourceURL_lenLess128" />
						<span id="errMsg_dpNews.newsSourceUrl" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.news.belong.type" />
					</th>
					<td>
						<!-- 2011-11-25 edit  -->
						<s:if test="%{dpNews.id != null}">
							<s:select id="typeId" name="dpNews.dpType.id" size="1"
								list="dpTypeList" listKey="id" listValue="typeName"
								value="%{dpNews.dpType.id}" headerKey="" headerValue=""
								cssStyle="width:175px" select="selected" valid="required"
								errmsg="news_newsType_notEmpty" />
							<span class="style-red">*</span>
							<span id="errMsg_dpNews.dpType.id" style="color: #FF0000"></span>
						</s:if>
						<s:else>
							<s:select id="typeId" name="dpType.id" size="1" list="dpTypeList"
								listKey="id" listValue="typeName" value="%{dpType.id}"
								headerKey="" headerValue="" cssStyle="width:175px"
								valid="required" errmsg="news_newsType_notEmpty" />
							<span class="style-red">*</span>
							<span id="errMsg_dpType.id" style="color: #FF0000"></span>
						</s:else> <!-- ended of 2011-11-25 -->
					</td>
				</tr>
				<tr>
					<th scope="row">
						<s:text	name="sdp.sce.dp.admin.news.newsContent" />
					</th>
					<td>
						<s:textarea name="dpNews.newsContent" id="newsContent"
							cols="100" rows="12" value="%{dpNews.newsContent}" valid="custom"
							custom="checkContentLen" errmsg="download_downloadDesc_lenless">
						</s:textarea>
						<p>
							<span class="must_input" id="error">*</span><span class="notes">
							<s:text	name="sdp.sce.dp.admin.news.content.len.tip" />
							</span>
						</p>
						<span id="errMsg_dpNews.newsContent" style="color: #FF0000"></span>
					</td>
				</tr>
			</table>
		</div>
		<!-- 保存和返回块 -->
		<div class="btnlistbar">
			<s:if test="%{dpNews.id != null}">
				<input class="inputstyle" type="button"
					value="<s:text name='sdp.sce.dp.admin.global.btn.confirm'/>"
					onclick="doEditNews('dpnews!doEdit.action')" />
			</s:if>
			<s:else>
				<input class="inputstyle" type="button"
					value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>"
					onclick="doAddNews('dpnews!doAdd.action')" />
			</s:else>
			<input class="inputstyle" type="button"
				value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
				onclick="javascript:history.back(-1)" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.dpNewsForm);
		insertLanguageJS();
	</script>
</body>
</html>
