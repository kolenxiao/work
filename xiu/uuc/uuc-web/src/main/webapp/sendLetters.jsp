<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Insert title here</title>
	
	
</head>
<body>
	<form name="sendLetters" action="sendLetters.action" method="post">
		<table>
			<tr>
				<td>*信件标题：</td>
				<td><input name="title"/></td>
			</tr>
			<tr>
				<td>*发件人：</td>
				<td><input name="sendName"/></td>
			</tr>
			<tr>
				<td>*收件人：</td>
				<td><input name="revName"/></td>
			</tr>
			<tr>
				<td>*客户ID（收件人）：</td>
				<td><input name="customerId"/></td>
			</tr>
			<tr>
				<td>*信件发送时间：</td>
				<td><input name="sendTime"/>(格式：2001-01-01 12:30:30)</td>
			</tr>
			<tr>
				<td>客户真实名字：</td>
				<td><input name="customerName"/></td>
			</tr>
			<tr>
				<td>客户电话：</td>
				<td><input name="telphone"/></td>
			</tr>
			<tr>
				<td>客户邮箱：</td>
				<td><input name="email"/></td>
			</tr>
			<tr>	
				<td>*信件内容：</td>
				<td><textarea rows="5" cols="" name="txt"></textarea></td>
			</tr>
			
		</table>
		<input type="submit" value="提交">
	</form>
</body>
</html>