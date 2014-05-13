<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>引导联盟用户注册</title>
</head>

<script type="text/javascript">

</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 *@Title: leadPartnerRegister 
* @Description: 引导联盟用户注册
* @param leadRegisterDTO
* @return
 */
public Result leadPartnerRegister(LeadRegisterDTO leadRegisterDTO);
</textarea>

	
	<form name="inputForm" action="leadPartnerRegister.action" method="post">
		<table>
            <tr>
                <td>*用户ID:</td>
                <td><input name="userId" /></td>
            </tr>
			<tr>
				<td>邮箱:</td>
				<td><input name="email" /></td>
			</tr>
            <tr>
                <td>手机号:</td>
                <td><input name="mobile" /></td>
            </tr>
			<tr>
				<td>*用户登录密码:</td>
				<td><input name="password" /></td>
			</tr>
			<tr>
				<td>*渠道标识:</td>
				<td><input name="channelId" value="11" />(11：官网, 12:秀团, 13:团货)</td>
			</tr>
		</table>
		<br>
		<input type="submit" value="确定">
	</form>
</body>
</html>