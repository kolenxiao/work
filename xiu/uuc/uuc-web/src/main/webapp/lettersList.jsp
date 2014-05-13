<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
</head>
<body>
	------------------所有信件---------------
	<form name="lettersList" action="lettersList.action" method="post">
		<table>
			<tr>
				<td>*客户ID：</td>
				<td><input type="text" name="customerId"/></td>
			</tr>
			<tr>
				<td>*当前页：</td>
				<td><input type="text" name="currentPage"/></td>
			</tr>
			<tr>
				<td>*每页显示的条数（非0）：</td>
				<td><input type="text" name="pageSize"/></td>
			</tr>
		</table>
		<input type="submit" value="提交">
	</form>
	<br>
	<br>
	<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 border=1>
		<tr>
			<td>信件ID</td>
			<td>发件人</td>
			<td>收件人</td>
			<td>发件时间</td>
			<td>信件标题</td>
			<td>信件内容</td>
			<td>是否阅读</td>
			<td>阅读时间</td>		
			<td>客户ID</td>
			<td>客户真实姓名</td>
			<td>客户电话</td>
			<td>客户邮箱</td>
			<td>操作</td>
		</tr>
		
		<c:forEach items="${list }" var="letters">
	    	<tr>
			    <td><c:out value="${letters.lettersId}" escapeXml="false"/></td>
			    <td><c:out value="${letters.sender}" escapeXml="false"/></td>
			    <td><c:out value="${letters.addressee}" escapeXml="false"/></td>
			    <td><c:out value="${letters.sendTime}" escapeXml="false"/></td>
			    <td><c:out value="${letters.title}" escapeXml="false"/></td>
			    <td><c:out value="${letters.content}" escapeXml="false"/></td>
			    <td><c:out value="${letters.read}" escapeXml="false"/></td>
			    <td><c:out value="${letters.receivTime}" escapeXml="false"/></td>
			    <td><c:out value="${letters.customerId}" escapeXml="false"/></td>
			    <td><c:out value="${letters.customerName}" escapeXml="false"/></td>
			    <td><c:out value="${letters.telphone}" escapeXml="false"/></td>
			    <td><c:out value="${letters.email}" escapeXml="false"/></td>
			    <td>
			    	<a href="findLetters.action?lettersId=${letters.lettersId }">阅读</a>
			    	<a href="deleteLetters.action?lettersId=${letters.lettersId }&customerId=${letters.customerId}">删除</a>
			    </td>
	    	</tr>
 		</c:forEach>
 		
	</table>
</body>
</html>