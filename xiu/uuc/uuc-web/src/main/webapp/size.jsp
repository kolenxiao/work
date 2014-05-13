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
	
	<form name="size" action="size.action" method="post">
		<table>
			<tr>
				<td>*客户ID：</td>
				<td><input type="text" name="customerId"/>&nbsp;&nbsp;<input type="submit" value="查询"></td>
			</tr>
		</table>
		
		<br><br>
		------------------信件条数------------------- 
		<table>
			<tr>
				<td>未读条数：</td>
				<td>${sizeList[0] }</td>
			</tr>
			<tr>
				<td>已读条数：</td>
				<td>${sizeList[1] }</td>
			</tr>
			<tr>
				<td>所有条数：</td>
				<td>${sizeList[2] }</td>
			</tr>
		</table>
	</form>
	<br>
	<br>
</body>
</html>