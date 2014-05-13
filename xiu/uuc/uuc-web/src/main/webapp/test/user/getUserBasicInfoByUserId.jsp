<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>通过用户ID得到用户基本信息</title>
</head>

<script type="text/javascript">
function doQuery(){
	if(inputForm.userId.value==""){
		alert("用户ID不能为空");
		return;
	}
	inputForm.submit();
}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 通过用户ID得到用户基本信息
 * @Title: getUserBasicInfoByUserId 
 * @param  userId 用户ID 
 * @return Result    返回类型 
 */
public Result getUserBasicInfoByUserId(Long userId)
</textarea>

	<form name="inputForm" action="getUserBasicInfoByUserId.action" method="post" target="_self">
		<table>
			<tr>
				<td>用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
	</form>
	<hr>
		<br>
		<s:if test=""></s:if>
		<table>
			<tr>
				<td>客户ID:</td>
				<td><input name="custId" value='<s:property value="result.user.custId"/>'/></td>
			</tr>
			<tr>
				<td>渠道标识:</td>
				<td><input name="channelId" value='<s:property value="result.user.channelId"/>'/>(11：官网, 12:秀团, 13:团货)</td>
			</tr>
			<tr>
				<td>用户状态:</td>
				<td><input name="userState"  value='<s:property value="result.user.userState"/>'/>(01：正常, 02:冻结, 03:禁用, 04:删除)</td>
			</tr>
			<tr>
				<td>用户等级:</td>
				<td><input name="userLevel"  value='<s:property value="result.user.userLevel"/>'/></td>
			</tr>
			<tr>
				<td><s:radio  name="isMaster" value='result.user.isMaster' list="#{'Y':'是','N':'否' }" label="是否主用户" ></s:radio></td>
			</tr>
			<tr>
				<td>客户状态:</td>
				<td><input name="custState"  value='<s:property value="result.user.custState"/>'/>(00: 未激活, 01：正常, 02:冻结, 03:禁用, 04:删除)</td>
			</tr>
			<tr>
				<td>客户类型:</td>
				<td><input name="custType"  value='<s:property value="result.user.custType"/>'/>(01：秀用户, 02:联盟用户)</td>
			</tr>
			<tr>
				<td>联盟客户在联盟渠道中的标识:</td>
				<td><input name="partnerId"  value='<s:property value="result.user.partnerId"/>'/></td>
			</tr>
			<tr>
				<td>联盟客户来源渠道标识:</td>
				<td><input name="partnerChannelId"  value='<s:property value="result.user.partnerChannelId"/>'/></td>
			</tr>
			<tr>
				<td>客户等级:</td>
				<td><input name="custLevel"  value='<s:property value="result.user.custLevel"/>'/></td>
			</tr>
			<tr>
				<td>客户姓名:</td>
				<td><input name="custName"  value='<s:property value="result.user.custName"/>'/></td>
			</tr>
			<tr>
				<td>注册类型:</td>
				<td><input name="registerType"  value='<s:property value="result.user.registerType"/>'/>(01：邮箱注册用户, 02:手机注册用户)</td>
			</tr>
			<tr>
				<td>用户昵称:</td>
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
				<td><input name="lastLogonTime"  value='<s:date name="result.user.lastLogonTime" format="yyyy-MM-dd HH:mm:ss"/>'/></td>
			</tr>
			<tr>
				<td>最后登录渠道标识:</td>
				<td><input name="lastLogonChannelId"  value='<s:property value="result.user.lastLogonChannelId"/>'/></td>
			</tr>
		</table>
</body>
</html>