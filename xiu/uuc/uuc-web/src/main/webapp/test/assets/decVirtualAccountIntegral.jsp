<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>积分支付</title>
</head>

<script type="text/javascript">

</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * @Title: decVirtualAccountIntegral 
 * @Description: 积分支付 使用场景：积分支付
 *               根据账目类型 积分03，进行积分支付
 * @return Result    返回类型 
 * @throws
 */
Result decVirtualAccountIntegral(AcctItemDTO acctItemDTO,AcctChangeDTO acctChangeDTO,IntegeralRuleDTO integeralRuleDTO);
</textarea>

	
	<form name="inputForm" action="assetsManager!decVirtualAccountIntegral.action" method="post">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="acctItemDTO.userId" /></td>
			</tr>
			<tr>
				<td>*支付积分:</td>
				<td><input name="acctItemDTO.totalAmount" /></td>
			</tr>
			<tr>
				<td>*业务流水:</td>
				<td><input name="acctChangeDTO.rltId" /></td>
			</tr>
			<tr>
				<td>*业务类型:</td>
				<td><input name="acctChangeDTO.busiType"/>(业务类型 1201.注册 1202.手机验证 1203.邮箱验证 1204.购买 1205.评论 1206.邀请注册 1207.回复其他会员咨询 1208.晒单获取 1209.活动 1210.意见反馈  1211.发表文章获取 1212.积分换购商品 1213.兑换优惠卡/券 1214.抽奖 1215.退换货 1216.被过期)</td>
			</tr>
			<tr>
				<td>*业务来源渠道标识:</td>
				<td><input name="acctChangeDTO.rltChannelId" value="10751"/>(10751：官网, 2:秀团, 3:团货)</td>
			</tr>
			<tr>
				<td>操作员ID:</td>
				<td><input name="acctChangeDTO.operId"/>(可选)</td>
			</tr>
		</table>
		<br>
		<input type="submit" value="确定">
	</form>
</body>
</html>