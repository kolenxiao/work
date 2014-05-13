<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>(邮箱/手机)是否通过验证  </title>
</head>

<script type="text/javascript">
	function doQuery(){
		if(inputForm.userId.value==""){
			alert("用户ID不能为空");
			return;
		}
		if(inputForm.authenType.value==""){
			alert("要验证的类型不能为空");
			return;
		}
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
	/**
	 * 
	 * @Title: isAuthentic
	 * @Description: (邮箱/手机)是否通过验证
	 * @param userId 用户ID
	 * @param authenType 要验证的类型(0:email  1:mobile)
	 * @return Result
	 */
	public Result isAuthentic(Long userId, String authenType)
</textarea>
	
	<form name="inputForm" action="isAuthentic.action" method="post" target="_self">
		<table>
			<tr>
				<td>*用户ID:</td>
				<td><input name="userId" value='<s:property value="result.userId"/>' /></td>
			</tr>
			<tr>
				<td>*要验证的类型(0:email  1:mobile):</td>
				<td><input name="authenType" value='<s:property value="result.authenType"/>' /><input type="button" value="提交" onclick="doQuery()"></td>
			</tr>
		</table>
		<hr>
		<div>是否通过验证:<s:property value="result.isAuthentic"/></div>
	</form>
</body>
</html>