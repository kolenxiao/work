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
 * 1.虚拟账户信息查询
 * 2.积分信息查询
 * @Title: getVirtualAccountInfo 
 * @Description: 虚拟帐户信息（账目）包括：可提现总金额,可提现冻结金额,不可提现总金额,不可提现冻结金额,总积分,积分冻结
 * @return Result    返回类型 
 * @throws
 */
Result getVirtualAccountInfo(VirtualAcctItemDTO virtualAcctItemDTO);
</textarea>

	<form name="inputForm" action="getVirtualAccountInfo.action" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
		</table>
	</form>
		<br>
		<s:if test=""></s:if>
		<table>
			<tr>
				<td>客户ID:</td>
				<td><input name="custId" value='<s:property value="result.virtualAccount.custId"/>'/></td>
			</tr>
			<tr>
				<td>渠道标识:</td>
				<td><input name="channelId" value='<s:property value="result.virtualAccount.channelId"/>'/></td>
			</tr>
			<tr>
				<td>用户等级:</td>
				<td><input name="userLevel" value='<s:property value="result.virtualAccount.userLevel"/>'/></td>
			</tr>
			<tr>
				<td>可提现总金额:</td>
				<td><input name="drawTotalMoney" value='<s:property value="result.virtualAccount.drawTotalMoney"/>'/>(包括可提现冻结金额)</td>
			</tr>
			<tr>
				<td>可提现冻结金额 :</td>
				<td><input name="drawFreezeMoney" value='<s:property value="result.virtualAccount.drawFreezeMoney"/>'/></td>
			</tr>
			<tr>
				<td>不可提现总金额:</td>
				<td><input name="noDrawTotalMoney" value='<s:property value="result.virtualAccount.noDrawTotalMoney"/>'/>(包括不可提现冻结金额)</td>
			</tr>
			
			<tr>
				<td>不可提现冻结金额:</td>
				<td><input name="noDrawFreezeMoney" value='<s:property value="result.virtualAccount.noDrawFreezeMoney"/>'/></td>
			</tr>
			<tr>
				<td>总积分 :</td>
				<td><input name="totalIntegral" value='<s:property value="result.virtualAccount.totalIntegral"/>'/>(包括冻结积分)</td>
			</tr>
			<tr>
				<td>冻结积分:</td>
				<td><input name="freezeIntegral" value='<s:property value="result.virtualAccount.freezeIntegral"/>'/></td>
			</tr>
		</table>
</body>
</html>