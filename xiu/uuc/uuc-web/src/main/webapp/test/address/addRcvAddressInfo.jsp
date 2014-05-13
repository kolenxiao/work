<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>增加地址</title>
</head>

<script type="text/javascript">

</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 增加一条收货地址信息
 * @Title: addRcvAddressInfo 
 * @param  rcvAddressDTO 收货地址信息DTO 
 * @return Result    返回类型 
 */
public Result addRcvAddressInfo(RcvAddressDTO rcvAddressDTO)
</textarea>

	
	<form name="inputForm" action="rcvAddressManage!addRcvAddressInfo.action" method="post">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" /></td>
			</tr>
			<tr>
				<td>*渠道标识:</td>
				<td><input name="createChannelId"/>(11：官网, 12:秀团, 13:团货)</td>
			</tr>
			<tr>
				<td>*所在省CODE:</td>
				<td><input name="provinceCode" /></td>
			</tr>
			<tr>
				<td>*所在市CODE:</td>
				<td><input name="regionCode" /></td>
			</tr>
						<tr>
				<td>*所在县CODE:</td>
				<td><input name="cityCode" /></td>
			</tr>
			<tr>
				<td>*街道地址:</td>
				<td><input name="addressInfo" /></td>
			</tr>
			<tr>
				<td>*收货人姓名:</td>
				<td><input name="rcverName" /></td>
			</tr>
			<tr>
				<td>区号:</td>
				<td><input name="areaCode" /></td>
			</tr>
						<tr>
				<td>电话号码:</td>
				<td><input name="phone" /></td>
			</tr>
			<tr>
				<td>分机号:</td>
				<td><input name="divCode" /></td>
			</tr>
						<tr>
				<td>手机号码:</td>
				<td><input name="mobile" /></td>
			</tr>
			<tr>
				<td>邮政编码:</td>
				<td><input name="postCode" /></td>
			</tr>
			<tr>
				<td>订购人姓名:</td>
				<td><input name="bookerName" /></td>
			</tr>
			<tr>
				<td>订购人联系电话:</td>
				<td><input name="bookerPhone" /></td>
			</tr>
			<tr>
				<td><s:radio  name="isMaster" value="'Y'" list="#{'Y':'是','N':'否' }" label="是否设为默认地址" ></s:radio></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="确定">
	</form>
</body>
</html>