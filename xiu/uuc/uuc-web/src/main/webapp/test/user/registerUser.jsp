<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户注册</title>
</head>

<script type="text/javascript">

</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 用户注册
 * @Title: registerUser 
 * @param  registerUserDTO 用户注册信息DTO
 * @return Result    返回类型 
 */
public Result registerUser(RegisterUserDTO registerUserDTO)
</textarea>

	
	<form name="inputForm" action="registerUser.action" method="post">
		<table>
			<tr>
				<td>*用户登录名:</td>
				<td><input name="logonName" /></td>
			</tr>
			<tr>
				<td>*用户登录密码:</td>
				<td><input name="password" /></td>
			</tr>
			<tr>
				<td>*创建渠道标识:</td>
				<td><input name="channelId" value="11" />(11：官网, 12:秀团, 13:团货)</td>
			</tr>
			<tr>
				<td>*注册类型:</td>
				<td><input name="registerType" value="01" />(01：邮箱注册, 02:手机注册, 03:呢称注册)</td>
			</tr>
			<tr>
				<td>*客户类型:</td>
				<td><input name="custType" value="01" />(01：秀用户, 02:联盟用户)</td>
			</tr>
			<tr>
				<td>*IP地址:</td>
				<td><input name="registerIp"  value="127.0.0.1"/></td>
			</tr>
			<tr>
				<td>联盟ID:</td>
				<td><input name="partnerId" /></td>
			</tr>
			<tr>
				<td>联盟渠道标识:</td>
				<td><input name="partnerChannelId" /></td>
			</tr>
			<tr>
				<td><s:radio name="isNeedActivate" value="'N'" list="#{'Y':'是','N':'否' }" label="是否需要将邮箱注册用户设置为'非激活'状态" ></s:radio></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="确定">
	</form>
</body>
</html>