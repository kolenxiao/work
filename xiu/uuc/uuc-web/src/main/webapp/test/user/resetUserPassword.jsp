<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>重置用户密码</title>
</head>

<script type="text/javascript">
	function doSave(){
		if(inputForm.userId.value==""){
			alert("用户ID不能为空");
			return;
		}
		inputForm.action = "userManage!resetUserPassword.action";
		inputForm.submit();
	}
</script>

<body>
<textarea rows="11" cols="100" style="color: red" >
/**
 * 重置用户密码
 * @Title: resetUserPassword 
 * @param  userId 用户ID
 * @param  newPassword 新密码
 * @param  operId 操作员ID 
 * @param  remark 重置原因
 * @return Result    返回类型 
 */
public Result resetUserPassword(Long userId, String newPassword, String operId, String remark);
</textarea>
	
	<form name="inputForm" action="" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /></td>
			</tr>
			<tr>
				<td>*新密码:</td>
				<td><input name="newPassword" value='<s:property value="newPassword"/>' /></td>
			</tr>
			<tr>
				<td>操作员ID:</td>
				<td><input name="operId" value='<s:property value="operId"/>' /></td>
			</tr>
			<tr>
				<td>重置原因:</td>
				<td><input name="remark" value='<s:property value="remark"/>' /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td><input type="button" value="重置密码" onclick="doSave()"></td>
			</tr>
		</table>
	</form>
</body>
</html>