<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询地址列表</title>
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
 * 通过用户ID查询收货地址信息列表
 * @Title: getRcvAddressListByUserId 
 * @param  userId 用户ID 
 * @return Result    返回类型 
 */
public Result getRcvAddressListByUserId(Long userId)
</textarea>
	
	<form name="inputForm" action="getRcvAddressList.action" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
		<br>
		<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 border=1>
			<tr>
				<td>地址ID</td>
				<td>渠道标识</td>
				<td>省</td>
				<td>市</td>
				<td>县</td>
				<td>街道地址</td>
				<td>收货人姓名</td>
				<td>区号</td>
				<td>电话号码</td>
				<td>分机号</td>
				<td>手机号码</td>
				<td>邮编</td>
				<td>订购人姓名</td>
				<td>订购人电话</td>
				<td>是否默认</td>
			</tr>
			<s:iterator value="result.rcvAddressList" status="status">			
			<tr>
				<td><s:property value="addressId"/></td>
				<td><s:property value="createChannelId"/></td>
				<td><s:property value="provinceCode"/></td>
				<td><s:property value="regionCode"/></td>
				<td><s:property value="cityCode"/></td>
				<td><s:property value="addressInfo"/></td>
				<td><s:property value="rcverName"/></td>
				<td><s:property value="areaCode"/></td>
				<td><s:property value="phone"/></td>
				<td><s:property value="divCode" /></td>
				<td><s:property value="mobile" /></td>
				<td><s:property value="postCode"/></td>
				<td><s:property value="bookerName"/></td>
				<td><s:property value="bookerPhone"/></td>
				<td><s:property value="isMaster"/></td>
			</tr>
			</s:iterator>
		</table>
	</form>
</body>
</html>