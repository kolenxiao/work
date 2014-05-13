<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>删除地址</title>
</head>

<script type="text/javascript">
	function doDelete(){
		if(inputForm.addressId.value==""){
			alert("地址ID不能为空");
			return;
		}
		inputForm.action = "rcvAddressManage!deleteRcvAddressInfo.action";
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 通过用户ID查询收货地址信息总条数
 * @Title: getRcvAddressCountByUserId 
 * @param  userId 用户ID 
 * @return Result    返回类型 
 */
public Result getRcvAddressCountByUserId(Long userId)
</textarea>
	
	<form name="inputForm" action="" method="post" target="_self">
		<table>
			<tr>
				<td>*地址ID:</td>
				<td><input name="addressId" value='<s:property value="addressId"/>' /><input type="button" value="删除" onclick="doDelete()"></td>
			</tr>
		</table>
	</form>
</body>
</html>