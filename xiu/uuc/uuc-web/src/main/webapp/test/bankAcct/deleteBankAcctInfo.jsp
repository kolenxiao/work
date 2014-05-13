<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>

<script type="text/javascript">
	function doDelete(){
		if(inputForm.bankAcctId.value==""){
			alert("客户银行卡账号ID不能为空");
			return;
		}
		inputForm.action = "bankAcctManager!deleteBankAcctInfo.action";
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * @Title: deleteBankAcctInfo 
 * @Description: 删除用户提现银行帐号信息(注意这里是逻辑删除，不是正真意义上的物理删除)
 * @return Result    返回类型 
 * @throws
 */
Result deleteBankAcctInfo(BankAcctDTO bankAcctDTO);
</textarea>
	
	<form name="inputForm" action="" method="post" target="_self">
		<table>
			<tr>
				<td>客户银行卡账号ID:</td>
				<td><input name="bankAcctId" value='<s:property value="bankAcctId"/>' /><input type="button" value="删除" onclick="doDelete()"></td>
			</tr>
		</table>
	</form>
</body>
</html>