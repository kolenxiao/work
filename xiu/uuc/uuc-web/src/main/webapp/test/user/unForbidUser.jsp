<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>起用用户</title>
</head>

<script type="text/javascript">
	function doSave(){
		if(inputForm.userId.value==""){
			alert("用户ID不能为空");
			return;
		}
		inputForm.action = "userManage!unForbidUser.action";
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 起用用户
 * @Title: unForbidUser 
 * @param  userBaseDTO
 * @return Result    返回类型 
 */
public Result unForbidUser(UserBaseDTO userBaseDTO)
</textarea>
	
	<form name="inputForm" action="" method="post" target="_self">
		<table>
			<tr>
				<td>用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /><input type="button" value="起用" onclick="doSave()"></td>
			</tr>
		</table>
	</form>
</body>
</html>