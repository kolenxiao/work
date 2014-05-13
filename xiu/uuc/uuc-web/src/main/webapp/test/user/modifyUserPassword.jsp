<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改用户密码</title>
</head>

<script type="text/javascript">
	function doSave(){
		if(inputForm.userId.value==""){
			alert("用户ID不能为空");
			return;
		}
		inputForm.action = "userManage!modifyUserPassword.action";
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 修改用户密码
 * @Title: modifyUserPassword 
 * @param  userId 用户ID
 * @param  oldPassword 当前使用密码 
 * @param  newPassword 新密码
 * @return Result    返回类型 
 */
public Result modifyUserPassword(Long userId, String oldPassword, String newPassword);
</textarea>
	
	<form name="inputForm" action="" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /></td>
			</tr>
			<tr>
				<td>*当前登录密码:</td>
				<td><input name="oldPassword" value='<s:property value="oldPassword"/>' /></td>
			</tr>
			<tr>
				<td>*新密码:</td>
				<td><input name="newPassword" value='<s:property value="newPassword"/>' /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="button" value="修改密码" onclick="doSave()"></td>
			</tr>
		</table>
	</form>
</body>
</html>