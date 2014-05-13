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
		if(inputForm.userId.value==""){
			alert("用户ID不能为空");
			return;
		}
		if(inputForm.channelId.value==""){
			alert("目标渠道标识不能为空");
			return;
		}
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 得到在目标渠道下的用户基本信息
 * @Title: getUserInTargetChannel 
 * @param  userId 用户ID
 * @param  targetChannelId 目标渠道标识
 * @description 如果在指定的渠道下不存在用户，则系统代创建一个用户
 * @return Result    返回类型 
 */
</textarea>
	
	<form name="inputForm" action="getUserInTargetChannel.action" method="post" target="_self">
		<table>
			<tr>
				<td>用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /></td>
			</tr>
			<tr>
				<td>目标渠道标识:</td>
				<td><input name="channelId" value='<s:property value="channelId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
		<br>
		<hr>
		<div>目标渠道下的UserId:<s:property value="result.userId"/></div>
	</form>
</body>
</html>