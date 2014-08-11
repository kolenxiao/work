<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用商店管理后台--操作成功页面</title>
</head>
<body>
	<div>
		<p>当前位置: 操作信息</p>
		<div style="color: red">
			操作成功！
			<br/>
			<s:property  value="result.data"/>
		</div>
	</div>	
	
	
</body>
</html>