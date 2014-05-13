<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改当前账目冻结金额</title>
</head>

<script type="text/javascript">

</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * @Title: setUserAcctItemFreezeMoney 
 * @Description: 修改当前账目冻结金额(主要是可提现，不可提现账目冻结金额缺省为0)
 * @return Result    返回类型 
 * @throws
 */
Result setUserAcctItemFreezeMoney(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO) ;
</textarea>

	
	<form name="inputForm" action="assetsManager!setUserAcctItemFreezeMoney.action" method="post">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="acctItemDTO.userId" /></td>
			</tr>
			<tr>
				<td>*账目类型:</td>
				<td><input name="acctItemDTO.acctTypeCode"/>（01：可提现 02：不可提现 03：积分）</td>
			</tr>
			<tr>
				<td>*冻结金额或积分:</td>
				<td><input name="acctItemDTO.freezeAmount" />(冻结操作类型为01时必填，冻结操作类型为02时可不填)</td>
			</tr>
			<tr>
				<td>*冻结操作类型:</td>
				<td><input name="acctItemDTO.operType" value="01"/>(01：冻结加操作 02:冻结减操作)</td>
			</tr>
			<tr>
				<td>*业务流水:</td>
				<td><input name="acctChangeDTO.rltId" /></td>
			</tr>
			<tr>
				<td>*业务类型:</td>
				<td><input name="acctChangeDTO.busiType"/>(业务类型 1101.订单 1102.申请提现 1103.审核不通过 1104.返现成功 1105.返现失败 1106.手工调节 1107.退换货 1108.退换货赔损 1109.撤单 1110.虚拟账户充值  1111.补差价 1112.积分兑换)</td>
			</tr>
			<tr>
				<td>*业务来源渠道标识:</td>
				<td><input name="acctChangeDTO.rltChannelId" value="10751"/>(10751：官网, 2:秀团, 3:团货)</td>
			</tr>
			<tr>
				<td>操作人员ID:</td>
				<td><input name="acctChangeDTO.operId" /></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="确定">
	</form>
</body>
</html>