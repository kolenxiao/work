<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改用户详细信息</title>
</head>

<script type="text/javascript">
function doQuery(){
	if(inputForm.userId.value==""){
		alert("用户ID不能为空");
		return;
	}
	inputForm.action = "searchModifyUserDetailInfo.action";
	inputForm.submit();
}

function doModify(){
	if(inputForm.userId.value==""){
		alert("用户ID不能为空");
		return;
	}
	inputForm.action = "userManage!modifyUserDetailInfo.action";
	inputForm.submit();
}
</script>

<body>

<textarea rows="8" cols="100" style="color: red" >
/**
 * 修改用户详细信息
 * @Title: modifyUserDetailInfo 
 * @param  userDetailDTO
 * @return Result    返回类型 
 */
public Result modifyUserDetailInfo(UserDetailDTO userDetailDTO);
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
				<td><input name="birthday"  value='<s:date name="result.user.birthday"  format="yyyy-MM-dd"/>'/></td>
			</tr>
			<tr>
				<td>省:</td>
				<td><input name="provinceCode"  value='<s:property value="result.user.provinceCode"/>'/></td>
			</tr>
			<tr>
				<td>市:</td>
				<td><input name="regionCode"  value='<s:property value="result.user.regionCode"/>'/></td>
			</tr>
			<tr>
				<td>县:</td>
				<td><input name="cityCode"  value='<s:property value="result.user.cityCode"/>'/></td>
			</tr>
			<tr>
				<td>街道地址:</td>
				<td><input name="addressInfo"  value='<s:property value="result.user.addressInfo"/>'/></td>
			</tr>
			<tr>
				<td>身份值:</td>
				<td><input name="workType"  value='<s:property value="result.user.workType"/>'/></td>
			</tr>
			<tr>
				<td>性别:</td>
				<td><input name="sex"  value='<s:property value="result.user.sex"/>'/></td>
			</tr>
			<tr>
				<td>学历:</td>
				<td><input name="degree"  value='<s:property value="result.user.degree"/>'/></td>
			</tr>
			<tr>
				<td>月收入:</td>
				<td><input name="monthIncome"  value='<s:property value="result.user.monthIncome"/>'/></td>
			</tr>
			<tr>
				<td>婚姻状态:</td>
				<td><input name="marry"  value='<s:property value="result.user.marry"/>'/></td>
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
				<td>消息订阅:</td>
				<td><input name="subscibeInfo"  value='<s:property value="result.user.subscibeInfo"/>'/></td>
			</tr>
		</table>
		<br>
		<input type="button" value="确定" onclick="doModify()">
	</form>
</body>
</html>