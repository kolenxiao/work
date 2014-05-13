<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>通过用户ID得到用户详细信息</title>
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
 * 通过用户ID得到用户详细信息
 * @Title: getUserDetailInfoByUserId 
 * @param  userId 用户ID 
 * @return Result    返回类型 
 */
public Result getUserDetailInfoByUserId(Long userId)
</textarea>

	<form name="inputForm" action="getUserDetailInfoByUserId.action" method="post" target="_self">
		<table>
			<tr>
				<td>用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
	</form>
	<hr>
		<br>
		<table>
			<tr>
				<td>客户:</td>
				<td><input name="custId" value='<s:property value="result.user.custId"/>'/></td>
			</tr>
			<tr>
				<td>渠道标识:</td>
				<td><input name="channelId" value='<s:property value="result.user.channelId"/>'/>(11：官网, 12:秀团, 13:团货)</td>
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
				<td>邮箱:</td>
				<td><input name="email"  value='<s:property value="result.user.email"/>'/></td>
			</tr>
            <tr>
                <td>手机是否认证(0:未认证  1:认证通过):</td>
                <td><input name="mobileAuthenStatus"  value='<s:property value="result.user.mobileAuthenStatus"/>'/></td>
            </tr>
            <tr>
                <td>邮箱是否认证:</td>
                <td><input name="emailAuthenStatus"  value='<s:property value="result.user.emailAuthenStatus"/>'/></td>
            </tr>
			<tr>
				<td>用户状态:</td>
				<td><input name="userState"  value='<s:property value="result.user.userState"/>'/>(01：正常, 02:冻结, 03:禁用, 04:删除)</td>
			</tr>
			<tr>
				<td>客户状态:</td>
				<td><input name="custState"  value='<s:property value="result.user.custState"/>'/>(00: 未激活, 01：正常, 02:冻结, 03:禁用, 04:删除)</td>
			</tr>
            <tr>
                <td><s:radio  name="isMaster" value='result.user.isMaster' list="#{'Y':'是','N':'否' }" label="是否主用户" ></s:radio></td>
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
                <td>用户等级:</td>
                <td><input name="userLevel"  value='<s:property value="result.user.userLevel"/>'/></td>
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
				<td>生日:</td>
				<td><input name="birthday"  value='<s:date name="result.user.birthday"  format="yyyy-MM-dd"/>'/></td>
			</tr>
			<tr>
				<td>省:</td>
				<td><input name="provinceCode"  value='<s:property value="result.user.provinceCode"/><s:property value="result.user.provinceCodeDesc"/>'/></td>
			</tr>
			<tr>
				<td>市:</td>
				<td><input name="regionCode"  value='<s:property value="result.user.regionCode"/><s:property value="result.user.regionCodeDesc"/>'/></td>
			</tr>
			<tr>
				<td>县:</td>
				<td><input name="cityCode"  value='<s:property value="result.user.cityCode"/><s:property value="result.user.cityCodeDesc"/>'/></td>
			</tr>
			<tr>
				<td>街道地址:</td>
				<td><input name="addressInfo"  value='<s:property value="result.user.addressInfo"/>'/></td>
			</tr>
			<tr>
				<td>身份值:</td>
				<td><input name="workType"  value='<s:property value="result.user.workType"/><s:property value="result.user.workTypeDesc"/>'/></td>
			</tr>
			<tr>
				<td>性别:</td>
				<td><input name="sex"  value='<s:property value="result.user.sex"/><s:property value="result.user.sexDesc"/>'/></td>
			</tr>
			<tr>
				<td>学历:</td>
				<td><input name="degree"  value='<s:property value="result.user.degree"/><s:property value="result.user.degreeDesc"/>'/></td>
			</tr>
			<tr>
				<td>月收入:</td>
				<td><input name="monthIncome"  value='<s:property value="result.user.monthIncome"/><s:property value="result.user.monthIncomeDesc"/>'/></td>
			</tr>
			<tr>
				<td>婚姻状态:</td>
				<td><input name="marry"  value='<s:property value="result.user.marry"/><s:property value="result.user.marryDesc"/>'/></td>
			</tr>
			<tr>
				<td>邀请人ID:</td>
				<td><input name="recommendId"  value='<s:property value="result.user.recommendId"/>'/></td>
			</tr>
			<tr>
				<td>身份证:</td>
				<td><input name="idCard"  value='<s:property value="result.user.idCard"/>'/></td>
			</tr>
			<tr>
				<td>兴趣爱好:</td>
				<td><input name="interest"  value='<s:property value="result.user.interest"/>'/></td>
			</tr>
			<tr>
				<td>统计ID:</td>
				<td><input name="fromId"  value='<s:property value="result.user.fromId"/>'/></td>
			</tr>
			<tr>
				<td>来源类型:</td>
				<td><input name="sourceType"  value='<s:property value="result.user.sourceType"/><s:property value="result.user.sourceTypeDesc"/>'/></td>
			</tr>
			<tr>
				<td>消息订阅:</td>
				<td><input name="subscibeInfo"  value='<s:property value="result.user.subscibeInfo"/>'/></td>
			</tr>			
			<tr>
				<td>注册时间:</td>
				<td><input name="registerTime"  value='<s:date name="result.user.registerTime"  format="yyyy-MM-dd HH:mm:ss"/>'/></td>
			</tr>
			<tr>
				<td>最后修改时间:</td>
				<td><input name="updateTime"  value='<s:date name="result.user.updateTime"  format="yyyy-MM-dd HH:mm:ss"/>'/></td>
			</tr>
			<tr>
				<td>操作人员ID:</td>
				<td><input name="operId"  value='<s:property value="result.user.operId"/>'/></td>
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