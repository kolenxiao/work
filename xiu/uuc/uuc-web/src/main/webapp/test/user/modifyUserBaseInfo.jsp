<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改用户基本信息</title>
</head>

<script type="text/javascript">
function doQuery(){
	if(inputForm.userId.value==""){
		alert("用户ID不能为空");
		return;
	}
	inputForm.action = "searchModifyUserBaseInfo.action";
	inputForm.submit();
}

function doModify(){
	if(inputForm.userId.value==""){
		alert("用户ID不能为空");
		return;
	}
	inputForm.action = "modifyUserBaseInfo.action";
	inputForm.submit();
}
</script>

<body>

<textarea rows="8" cols="100" style="color: red" >
/**
 * 修改用户基本信息
 * @Title: modifyUserBaseInfo 
 * @param  userBaseDTO
 * @return Result    返回类型 
 */
public Result modifyUserBaseInfo(UserBaseDTO userBaseDTO);
</textarea>
	
	<form name="inputForm" action="" method="post">
		<table>
			<tr>
				<td>用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
		<hr>
		<table>		
			<tr>
				<td>客户等级:</td>
				<td><input name="custLevel"  value='<s:property value="result.user.custLevel"/>'/></td>
			</tr>
			<tr>
				<td>客户姓名:</td>
				<td><input name="custName"  value='<s:property value="result.user.custName"/>'/></td>
			</tr>
			<tr>
				<td>客户昵称:</td>
				<td><input name="petName"  value='<s:property value="result.user.petName"/>'/></td>
			</tr>
			<tr>
				<td>手机号码:</td>
				<td><input name="mobile"  value='<s:property value="result.user.mobile"/>'/></td>
			</tr>
			<tr>
				<td>电子邮箱:</td>
				<td><input name="email"  value='<s:property value="result.user.email"/>'/></td>
			</tr>
			<tr>
				<td>生日:</td>
				<td><input name="birthday"  value='<s:date name="result.user.birthday" format="yyyy-MM-dd"/>'/></td>
			</tr>
			<tr>
				<td>最后登录IP:</td>
				<td><input name="lastLogonIp"  value='<s:property value="result.user.lastLogonIp"/>'/></td>
			</tr>
			<tr>
				<td>最后登录时间:</td>
				<td><input name="lastLogonTime"  value='<s:date name="result.user.lastLogonTime" format="yyyy-MM-dd"/>'/></td>
			</tr>
			<tr>
				<td>最后登录渠道标识:</td>
				<td><input name="lastLogonChannelId"  value='<s:property value="result.user.lastLogonChannelId"/>'/></td>
			</tr>
		</table>
		<br>
		<input type="button" value="确定" onclick="doModify()">
	</form>
</body>
</html>