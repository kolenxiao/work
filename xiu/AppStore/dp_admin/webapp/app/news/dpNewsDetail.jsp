<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者管理平台</title>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name='sdp.sce.dp.admin.global.label.current.position' />
			<s:text name='sdp.sce.dp.admin.news' />
			&gt;
			<s:text name="sdp.sce.dp.admin.news.newsDetail" />
		</p>
		<div id="pright"></div>
	</div>
	<div class="news_detail">
		<div class="caption">
			<p class="title p2">
				<s:property value="dpNews.newsTitle" />
			</p>
			<p class="title_head1">
				<a href="<s:property value="dpNews.newsSourceUrl"/>"><font
					color="blue"><s:property value="dpNews.newsSourceUrl" />
				</font>
				</a>
				<s:text name="sdp.sce.dp.admin.news.date"></s:text>
				：
				<s:date name="dpNews.newsCreateTime" format="yyyy-MM-dd" />
				<s:text name="sdp.sce.dp.admin.news.type"></s:text>
				：
				<s:property value="dpNews.dpType.typeName" />
			</p>
		</div>
		<div class="news_article_small">
			<p class="p1">
				<s:property value="dpNews.newsContent" escape="false" />
			</p>
		</div>

		<p class="title_head2">
			<s:text name="sdp.sce.dp.admin.global.btn.edit"></s:text>
			：<%=request.getSession().getAttribute("userName")%>
			<s:text name="sdp.sce.dp.admin.news.news.comefrom"></s:text>
			<s:property value="dpNews.newsSource" />
		</p>
		<div class="return">
			<a href="portalDpnews!doList.action"></a>
		</div>
	</div>

	<div class="btnlistbar">
		<input class="inputstyle" type="button"
			value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
			onclick="javascript:history.back(-1)" />
	</div>
</body>
</html>
