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
	if(inputForm.bankAcctId.value==""){
		alert("客户银行卡账号表ID不能为空");
		return;
	}
	inputForm.action = "searchModifyBankAcctInfo.action";
	inputForm.submit();
}

function doModify(){
	if(inputForm.bankAcctId.value==""){
		alert("客户银行卡账号表ID不能为空");
		return;
	}
	inputForm.action = "bankAcctManager!updateBankAcctInfo.action";
	inputForm.submit();
}
</script>

<body>

<textarea rows="8" cols="100" style="color: red" >
/**
 * @Title: updateBankAcctInfo 
 * @Description: 编辑用户提现银行账号帐号信息
 * @return Result    返回类型 
 * @throws
 */
Result updateBankAcctInfo(BankAcctDTO bankAcctDTO);
</textarea>
	
	<form name="inputForm" action="" method="post">
		<table>
			<tr>
				<td>客户银行卡账号表ID:</td>
				<td><input name="bankAcctId" value='<s:property value="bankAcctId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>*客户银行卡号:</td>
				<td><input name="bankAcctNo" value='<s:property value="result.bankAcct.bankAcctNo"/>'/></td>
			</tr>
			<tr>
				<td>*开户人姓名:</td>
				<td><input name="bankAcctName" value='<s:property value="result.bankAcct.bankAcctName"/>'/></td>
			</tr>
			<tr>
				<td>*开户银行名称:</td>
				<td><input name="bankName" value='<s:property value="result.bankAcct.bankName"/>'/></td>
			</tr>
			<tr>
				<td>*创建渠道:</td>
				<td><input name="createChannelId" value='<s:property value="result.bankAcct.createChannelId"/>'/>(10204：官网, 2:秀团, 3:团货)</td>
			</tr>
			<tr>
				<td>省代码:</td>
				<td><input name="provinceCode" value='<s:property value="result.bankAcct.provinceCode"/>'/></td>
			</tr>
			<tr>
				<td>市代码:</td>
				<td><input name="cityCode" value='<s:property value="result.bankAcct.cityCode"/>'/></td>
			</tr>
			<tr>
			    <td>是否主卡:</td>
				<td><input name="isMasterCard" value='<s:property value="result.bankAcct.isMasterCard"/>' />(1.主卡 2.非主卡)</td>
			</tr>
			<tr>
				<td>区号:</td>
				<td><input name="areaCode" value='<s:property value="result.bankAcct.areaCode"/>'/></td>
			</tr>
			<tr>
				<td>电话号码:</td>
				<td><input name="phone" value='<s:property value="result.bankAcct.phone"/>'/></td>
			</tr>
			<tr>
				<td>手机号码:</td>
				<td><input name="mobile" value='<s:property value="result.bankAcct.mobile"/>'/></td>
			</tr>
		</table>
		<br>
		<input type="button" value="确定" onclick="doModify()">
	</form>
</body>
</html>