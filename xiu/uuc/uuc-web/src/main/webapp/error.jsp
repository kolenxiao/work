<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>失败页面</title>
</head>
<body>
	操作失败！
	<br>
	<br>
	<br>
	<div style="color: red">
		<s:property  value="errorReason.get('msg')"/>
	</div>
	<div style="color:red">
	    <s:fielderror />
	    <s:actionerror/>
	</div>
</body>
</html>