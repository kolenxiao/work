<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改地址 </title>
</head>

<script type="text/javascript">
function doQuery(){
	if(inputForm.addressId.value==""){
		alert("地址ID不能为空");
		return;
	}
	inputForm.action = "searchModifyRcvAddressInfo.action";
	inputForm.submit();
}

function doModify(){
	if(inputForm.addressId.value==""){
		alert("地址ID不能为空");
		return;
	}
	inputForm.action = "rcvAddressManage!modifyRcvAddressInfo.action";
	inputForm.submit();
}
</script>

<body>

<textarea rows="8" cols="100" style="color: red" >
/**
 * 修改收货地址信息
 * @Title: modifyRcvAddressInfo 
 * @param  rcvAddressDTO 收货地址信息DTO 
 * @return Result    返回类型 
 */
public Result modifyRcvAddressInfo(RcvAddressDTO rcvAddressDTO)
</textarea>
	
	<form name="inputForm" action="" method="post">
		<table>
			<tr>
				<td>地址ID:</td>
				<td><input name="addressId" value='<s:property value="addressId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>*渠道标识:</td>
				<td><s:property value="result.rcvAddress.createChannelId"/>
				<input name="createChannelId" type="hidden"  value='<s:property value="result.rcvAddress.createChannelId"/>'/></td>
			</tr>
			<tr>
				<td>*所在省CODE:</td>
				<td><input name="provinceCode"  value='<s:property value="result.rcvAddress.provinceCode"/>'/></td>
			</tr>
			<tr>
				<td>*所在市CODE:</td>
				<td><input name="regionCode"  value='<s:property value="result.rcvAddress.regionCode"/>'/></td>
			</tr>
						<tr>
				<td>*所在县CODE:</td>
				<td><input name="cityCode"  value='<s:property value="result.rcvAddress.cityCode"/>'/></td>
			</tr>
			<tr>
				<td>*街道地址:</td>
				<td><input name="addressInfo"  value='<s:property value="result.rcvAddress.addressInfo"/>'/></td>
			</tr>
			<tr>
				<td>*收货人姓名:</td>
				<td><input name="rcverName"  value='<s:property value="result.rcvAddress.rcverName"/>'/></td>
			</tr>
			<tr>
				<td>区号:</td>
				<td><input name="areaCode"  value='<s:property value="result.rcvAddress.areaCode"/>'/></td>
			</tr>
						<tr>
				<td>电话号码:</td>
				<td><input name="phone"  value='<s:property value="result.rcvAddress.phone"/>'/></td>
			</tr>
			<tr>
				<td>分机号:</td>
				<td><input name="divCode"  value='<s:property value="result.rcvAddress.divCode"/>'/></td>
			</tr>
						<tr>
				<td>手机号码:</td>
				<td><input name="mobile"  value='<s:property value="result.rcvAddress.mobile"/>'/></td>
			</tr>
			<tr>
				<td>邮政编码:</td>
				<td><input name="postCode"  value='<s:property value="result.rcvAddress.postCode"/>'/></td>
			</tr>
			<tr>
				<td>订购人姓名:</td>
				<td><input name="bookerName"  value='<s:property value="result.rcvAddress.bookerName"/>'/></td>
			</tr>
			<tr>
				<td>订购人联系电话:</td>
				<td><input name="bookerPhone"  value='<s:property value="result.rcvAddress.bookerPhone"/>'/></td>
			</tr>
			<tr>
				<td><s:radio  name="isMaster" value='result.rcvAddress.isMaster' list="#{'Y':'是','N':'否' }" label="是否设为默认地址" ></s:radio></td>

			</tr>
		</table>
		<br>
		<input type="button" value="确定" onclick="doModify()">
	</form>
</body>
</html>