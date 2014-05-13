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
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * @Title: getBankAcctListInfo 
 * @Description: 查询用户已有的提现银行账号列表 
 * @return Result    返回类型 
 * @throws
 */
Result getBankAcctListInfo(BankAcctDTO bankAcctDTO);
</textarea>
	
	<form name="inputForm" action="getBankAcctListInfo.action" method="post" target="_self">
		<table>
			<tr>
				<td>客户ID:</td>
				<td><input name="custId" value='<s:property value="custId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
		<br>
		<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 border=1>
			<tr>
				<td>客户银行卡账号表ID</td>
				<td>客户ID</td>
				<td>客户银行卡号</td>
				<td>开户人姓名</td>
				<td>开户银行名称</td>
				<td>创建渠道</td>
				<td>省代码</td>
				<td>市代码</td>
				<td>是否主卡</td>
				<td>区号</td>
				<td>电话号码</td>
				<td>手机号码</td>
			</tr>
			<s:iterator value="result.bankAcctList" status="status">			
			<tr>
				<td><s:property value="bankAcctId"/></td>
				<td><s:property value="custId"/></td>
				<td><s:property value="bankAcctNo"/></td>
				<td><s:property value="bankAcctName"/></td>
				<td><s:property value="bankName"/></td>
				<td><s:property value="createChannelId"/></td>
				<td><s:property value="provinceCode"/></td>
				<td><s:property value="cityCode"/></td>
				<td><s:property value="isMasterCard"/></td>
				<td><s:property value="areaCode"/></td>
				<td><s:property value="phone" /></td>
				<td><s:property value="mobile" /></td>
			</tr>
			</s:iterator>
		</table>
	</form>
</body>
</html>