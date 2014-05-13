<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>设置用户账户解冻</title>
</head>

<script type="text/javascript">

</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * @Title: setUserAcctUnFreeze 
 * @Description: 设置用户账户解冻
 * @return Result    返回类型 
 * @throws
 */
Result setUserAcctUnFreeze(UserAcctDTO userAcctDTO);
</textarea>

	
	<form name="inputForm" action="assetsManager!setUserAcctUnFreeze.action" method="post">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userAcctDTO.userId" /></td>
			</tr>
			<tr>
				<td>操作人员ID:</td>
				<td><input name="userAcctDTO.operId"/></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="确定">
	</form>
</body>
</html>