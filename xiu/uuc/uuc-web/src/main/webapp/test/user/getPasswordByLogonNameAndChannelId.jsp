<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>通过登录名和渠道标识得到登录密码</title>
</head>

<script type="text/javascript">
	function doQuery(){
		if(inputForm.logonName.value==""){
			alert("用户登录名不能为空");
			return;
		}
		if(inputForm.channelId.value==""){
			alert("所属渠道标识不能为空");
			return;
		}
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 通过登录名和渠道标识得到登录密码
 * @Title: getPasswordByLogonNameAndChannelId 
 * @param  logonName 用户登录名
 * @param  channelId 渠道标识  
 * @return Result    返回类型 
 */
public Result getPasswordByLogonNameAndChannelId(String logonName, Integer channelId)
</textarea>
	
	<form name="inputForm" action="getPasswordByLogonNameAndChannelId.action" method="post" target="_self">
		<table>
			<tr>
				<td>用户登录名:</td>
				<td><input name="logonName" value='<s:property value="logonName"/>' /></td>
			</tr>
			<tr>
				<td>所属渠道标识:</td>
				<td><input name="channelId" value='<s:property value="channelId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
		<br>
		<hr>
		<div>密码:<s:property value="result.password"/></div>
	</form>
</body>
</html>