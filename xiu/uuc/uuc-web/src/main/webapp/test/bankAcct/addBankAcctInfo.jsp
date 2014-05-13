<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增用户提现银行账号信息</title>
</head>

<script type="text/javascript">

</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * @Title: addBankAcctInfo 
 * @Description: 新增用户提现银行账号信息 
 * @return Result    返回类型 
 * @throws
 */
Result addBankAcctInfo(BankAcctDTO bankAcctDTO);
</textarea>

	
	<form name="inputForm" action="bankAcctManager!addBankAcctInfo.action" method="post">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" /></td>
			</tr>
			<tr>
				<td>*客户银行卡号:</td>
				<td><input name="bankAcctNo"/></td>
			</tr>
			<tr>
				<td>*开户人姓名:</td>
				<td><input name="bankAcctName" /></td>
			</tr>
			<tr>
				<td>*开户银行名称:</td>
				<td><input name="bankName" /></td>
			</tr>
			<tr>
				<td>*创建渠道:</td>
				<td><input name="createChannelId" value="10204"/>(10204：官网, 2:秀团, 3:团货)</td>
			</tr>
			<tr>
				<td>*省代码:</td>
				<td><input name="provinceCode" /></td>
			</tr>
			<tr>
				<td>*市代码:</td>
				<td><input name="cityCode" /></td>
			</tr>
			<tr>
			    <td>是否主卡:</td>
				<td><input name="isMasterCard" />(1.主卡 2.非主卡)</td>
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
				<td>手机号码:</td>
				<td><input name="mobile" /></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="确定">
	</form>
</body>
</html>