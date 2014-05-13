<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>检查是否已被注册  </title>
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
 * 检查用户登录名是否已被注册
 * @Title: isLogonNameExist 
 * @param  logonName 用户登录名
 * @param  channelId 用户所属渠道标识 
 * @return Result    返回类型 
 */
public Result isLogonNameExist(String logonName, Integer channelId)
</textarea>
	
	<form name="inputForm" action="isLogonNameExist.action" method="post" target="_self">
		<table>
			<tr>
				<td>*用户登录名(包括用户呢称，手机，Email):</td>
				<td><input name="logonName" value='<s:property value="logonName"/>' /></td>
			</tr>
			<tr>
				<td>*所属渠道标识:</td>
				<td><input name="channelId" value='<s:property value="channelId"/>' /><input type="button" value="检查是否存在" onclick="doQuery()"></td>
			</tr>
		</table>
		<hr>
		<div>是否存在:<s:property value="result.isExist"/></div>
		<div>存在的用户ID:<s:property value="result.userId"/></div>
	</form>
</body>
</html>