<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page isELIgnored="false" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
</head>
<body>
	<form name="sendLetters" action="sendLetters.action" method="post">
		<table>
			<tr>
				<td>*信件标题：</td>
				<td><input name="title" value="${statLettersDTO.title }"/></td>
			</tr>
			<tr>
				<td>*发件人：</td>
				<td><input name="sendName" value="${statLettersDTO.sender }"/></td>
			</tr>
			<tr>
				<td>*收件人：</td>
				<td><input name="revName" value="${statLettersDTO.addressee}"/></td>
			</tr>
			<tr>
				<td>*客户ID（收件人）：</td>
				<td><input name="customerId" value="${statLettersDTO.customerId }"/></td>
			</tr>
			<tr>
				<td>*信件发送时间：</td>
				<td><input name="sendTime" value="${statLettersDTO.sendTime }"/></td>
			</tr>
			<tr>
				<td>客户真实名字：</td>
				<td><input name="customerName" value="${statLettersDTO.customerName }"/></td>
			</tr>
			<tr>
				<td>客户电话：</td>
				<td><input name="telphone" value="${statLettersDTO.telphone }"/></td>
			</tr>
			<tr>
				<td>客户邮箱：</td>
				<td><input name="email" value="${statLettersDTO.email }"/></td>
			</tr>
			<tr>	
				<td>*信件内容：</td>
				<td><textarea rows="5" cols="" name="txt" >${statLettersDTO.content }</textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>