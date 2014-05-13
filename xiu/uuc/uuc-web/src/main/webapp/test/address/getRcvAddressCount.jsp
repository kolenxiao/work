<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询地址数目</title>
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
 * 通过用户ID查询收货地址信息总条数
 * @Title: getRcvAddressCountByUserId 
 * @param  userId 用户ID 
 * @return Result    返回类型 
 */
public Result getRcvAddressCountByUserId(Long userId)
</textarea>
	
	<form name="inputForm" action="getRcvAddressCount.action" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
		<hr>
		<div>地址条数:<input name="count" value='<s:property value="result.count"/>'/></div>
	</form>
</body>
</html>