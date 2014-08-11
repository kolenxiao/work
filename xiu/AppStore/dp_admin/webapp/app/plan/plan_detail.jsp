<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案基本信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/uploadify/uploadify.css">
<script src="${pageContext.request.contextPath}/uploadify/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/upload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/common.js" type="text/javascript"></script>


</head>
<body id="cnt_body">
    <div id="position">
        <p> <s:text name='sdp.sce.dp.admin.global.label.current.position' />方案管理 &gt; 方案基本信息</p>
    </div>
    <s:form method="post" cssStyle="margin:0" theme="simple">
        <div class="data_view">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th scope="row">方案名称：</th>
                <td><s:property value="plan.name"/></td>
            </tr>
            <tr>
                <th scope="row">生效时间：</th>
                <td><s:date name="plan.startTime" format="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <th scope="row">失效时间：</th>
                <td><s:date name="plan.endTime" format="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <th scope="row">状态：</th>
                <td>
					<s:if test="%{plan.status == 0}">
						未生效
					</s:if>
					<s:elseif test="%{plan.status == 1}">
						已生效
					</s:elseif>
					<s:elseif test="%{plan.status == 2}">
						已禁用
					</s:elseif>
				</td>
            </tr>
            <tr>
                <th scope="row">是否默认：</th>
                <td><s:if test="%{plan.defaulted == false}">
						否
					</s:if>
					<s:elseif test="%{plan.defaulted == true}">
						是
					</s:elseif></td>
            </tr>
	        <tr>
			    <th scope="row">方案简介：</th>
				<td><s:textarea name="plan.description" rows="5" cols="50" disabled="true"></s:textarea></td>
			</tr> 
	    </table>
	</div>
	
	<!-- 保存和返回块 -->
	<div class="btnlistbar">
	    <input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back(-1)" />
	</div>
</s:form>
<script type="text/javascript">
    insertLanguageJS();
</script>
</body>
</html>
