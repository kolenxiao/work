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
 * 1.虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
 * 2.积分明细查询（当前用户当前渠道积分账目变更信息，这里指积分）
 * @Title: getVirtualChangeList 
 * @Description: 虚拟账户明细查询（当前用户当前渠道虚拟账户账目变更信息，这里指金额）
 * @return Result    返回类型 
 * @throws
 */
Result getVirtualChangeList(AcctChangeDTO acctChangeDTO);

</textarea>
	
	<form name="inputForm" action="getVirtualChangeList.action" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td>
				   <input name="userId" value='<s:property value="userId"/>' />
				   <input type="button" value="查询" onclick="doQuery()">
			   </td>
			</tr>
		</table>
		<br>
		<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 border=1>
			<tr>
				<td>账目变更序列号</td>
				<td>账目ID</td>
				<td>导致帐目变化的业务流水</td>
				<td>账目进出类型</td>
				<td>业务类型</td>
				<td>出入数量</td>
				<td>业务来源渠道标识</td>
				<td>账户ID</td>
			</tr>
			<s:iterator value="result.virtualChangeList" status="status">			
			<tr>
				<td><s:property value="accountChangeId"/></td>
				<td><s:property value="acctItemId"/></td>
				<td><s:property value="rltId"/></td>
				<td><s:property value="ioType"/></td>
				<td><s:property value="busiType"/></td>
				<td><s:property value="ioAmount"/></td>
				<td><s:property value="rltChannelId"/></td>
				<td><s:property value="acctId"/></td>
			</tr>
			</s:iterator>
		</table>
	</form>
</body>
</html>