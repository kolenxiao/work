<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改用户名认证</title>
</head>

<script type="text/javascript">
	function doSave(){
		if(inputForm.userId.value==""){
			alert("用户ID不能为空");
			return;
		}
		if(inputForm.authenType.value==""){
			alert("认证类型不能为空");
			return;
		}
		if(inputForm.authenValue.value==""){
			alert("认证值不能为空");
			return;
		}
		inputForm.action = "userManage!modifyUserAuthen.action";
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
	/**
	 * 
	 * @Title: modifyEmailAuthen
	 * @Description: 修改用户名认证
	 * @param userId 用户ID
	 * @param authenType 认证类型(0:邮箱, 1:手机)
	 * @param authenValue 认证值
	 * @return Result
	 */
	public Result modifyUserAuthen(Long userId, String authenType, String authenValue)
</textarea>
	
	<form name="inputForm" action="" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" value='<s:property value="result.userId"/>' /></td>
			</tr>
			<tr>
				<td>*认证类型(0:邮箱, 1:手机):</td>
				<td><input name="authenType" value='<s:property value="result.authenType"/>' /></td>
			</tr>
			<tr>
				<td>*认证值:</td>
				<td><input name="authenValue" value='<s:property value="result.authenValue"/>' /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="button" value="提交" onclick="doSave()"></td>
			</tr>
		</table>
	</form>
</body>
</html>