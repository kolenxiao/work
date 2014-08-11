<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱摸客反馈信息详情</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/uploadify/uploadify.css">
<script src="${pageContext.request.contextPath}/uploadify/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/upload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/common.js" type="text/javascript"></script>
</head>
<body id="cnt_body">
    <div id="position">
        <p> <s:text name='sdp.sce.dp.admin.global.label.current.position' />系统管理&gt;用户反馈信息详情</p>
    </div>
    <div class="view_nav">
        <s:property value="#module"/>
    </div>
    <s:form id="imokerForm" name="imokerForm" method="post" cssStyle="margin:0" theme="simple">
        <s:token/>
        <div class="data_view">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
			    <th scope="row">用户反馈信息详情</th>
				<td>
				    <s:textarea name="userFeedback.context" cols="50" rows="8" id="userFeedback.context" style="width:500px;height:300px;"/>
				</td>
			</tr> 
	    </table>
	</div>
	
	<!-- 保存和返回块 -->
	<div class="btnlistbar">
		<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
				onclick="javascript:history.back(-1)" />
	</div>
</s:form>
</body>
</html>
