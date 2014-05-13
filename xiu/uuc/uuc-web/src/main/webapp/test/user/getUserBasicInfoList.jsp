<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>得到用户基本信息列表</title>
</head>

<script type="text/javascript">
	function doQuery(){
		inputForm.submit();
	}
</script>

<body>
<textarea rows="8" cols="100" style="color: red" >
/**
 * 得到用户基本信息列表
 * @Title: getUserBaseInfoList 
 * @param  userQueryDTO 
 * @return Result    返回类型 
 */
public Result getUserBaseInfoList(UserQueryDTO userQueryDTO);
</textarea>
	
	<form name="inputForm" action="getUserBaseInfoList.action" method="post" target="_self">
		<table>
			<tr>
				<td>用户ID:</td>
				<td><input name="userId" value='<s:property value="userId"/>' /></td>
			</tr>
			<tr>
				<td>渠道标识:</td>
				<td><input name="channelId" value='<s:property value="channelId"/>' /></td>
			</tr>
			<tr>
				<td>用户呢称:</td>
				<td><input name="petName" value='<s:property value="petName"/>' /></td>
			</tr>
			<tr>
				<td>手机号码:</td>
				<td><input name="mobile" value='<s:property value="mobile"/>' /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><input name="email" value='<s:property value="email"/>' /><input type="button" value="查询" onclick="doQuery()"></td>
			</tr>
			<tr>
				<td><s:radio name="exactQuery" list="#{'Y':'精确查询', 'N':'模糊查询'}" value='<s:property value="exactQuery"/>' label="查询模式"></s:radio></td>
			</tr>
		</table>
		<hr>
		<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 border=1>
			<tr>
				<td>序号</td>
				<td>客户ID</td>
				<td>用户ID</td>
				<td>用户登录名</td>
				<td>渠道标识</td>
				<td>用户状态</td>
				<td>用户等级</td>
				<td>是否主用户</td>
				<td>客户状态</td>
				<td>客户类型</td>
				<td>客户等级</td>
				<td>客户姓名</td>
				<td>用户昵称</td>
				<td>注册类型</td>
				<td>手机号码</td>
				<td>电子邮箱</td>
			</tr>
			<s:iterator value="result.user" status="status">			
			<tr>
				<td><s:property value="#status.index+1"/></td>
				<td><s:property value="custId"/></td>
				<td><s:property value="userId"/></td>
				<td><s:property value="logonName"/></td>
				<td><s:property value="channelId"/></td>
				<td><s:property value="userState"/></td>
				<td><s:property value="userLevel"/></td>
				<td><s:property value="isMaster"/></td>
				<td><s:property value="custState"/></td>
				<td><s:property value="custType"/></td>
				<td><s:property value="custLevel" /></td>
				<td><s:property value="custName" /></td>
				<td><s:property value="petName"/></td>
				<td><s:property value="registerType"/></td>
				<td><s:property value="mobile"/></td>
				<td><s:property value="email"/></td>
			</tr>
			</s:iterator>
		</table>
		<br>
		<div>第几页<input name="currentPage" value='<s:property value="result.page.currentPage"/>' />&nbsp;
		             每页<s:property value="result.page.pageSize"/>条&nbsp;
		            总共<s:property value="result.page.totalRecord"/>条&nbsp;
		          总共<s:property value="result.page.totalPage"/>页
		</div>
	</form>
</body>
</html>