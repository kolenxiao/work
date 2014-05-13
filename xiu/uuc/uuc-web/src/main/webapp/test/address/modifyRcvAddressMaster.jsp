<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设为默认地址</title>
</head>

<script type="text/javascript">

function doMaster(){
	if(inputForm.addressId.value==""){
		alert("地址ID不能为空");
		return;
	}
	inputForm.action = "rcvAddressManage!modifyRcvAddressMaster.action";
	inputForm.submit();
}
</script>

<body>

<textarea rows="8" cols="100" style="color: red" >
/**
 * 设置收货地址为默认地址
 * @Title: modifyRcvAddressMaster 
 * @param  addressId 地址信息ID
 * @return Result    返回类型 
 */
public Result modifyRcvAddressMaster(Long addressId)
</textarea>
	
	<form name="inputForm" action="" method="post">
		<table>
			<tr>
				<td>*地址ID:</td>
				<td><input name="addressId" value='<s:property value="addressId"/>' /><input type="button" value="设为默认地址" onclick="doMaster()"></td>
			</tr>
		</table>
	</form>
</body>
</html>