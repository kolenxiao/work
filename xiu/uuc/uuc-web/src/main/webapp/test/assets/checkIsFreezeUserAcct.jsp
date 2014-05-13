<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
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
 * @Title: checkIsFreezeUserAcct 
 * @Description: 校验当前用户账户是否被冻结 01:正常 02:已冻结
 * @return Result    返回类型 
 * @throws
 */
Result checkIsFreezeUserAcct(UserAcctDTO userAcctDTO);
</textarea>

	<form name="inputForm" action="checkIsFreezeUserAcct.action" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td>
				    <input name="userId" value='<s:property value="userId"/>' />
				    <input type="button" value="查询" onclick="doQuery()">
				 </td>
			</tr>
		</table>
	</form>
		<br>
		<s:if test=""></s:if>
		<table>
			<tr>
				<td>用户是否冻结:</td>
				<td><input name="acctState" value='<s:property value="result.IsFreezeUserAcct"/>'/>(账户状态 01:正常 02:已冻结)</td>
			</tr>
		</table>
</body>
</html>