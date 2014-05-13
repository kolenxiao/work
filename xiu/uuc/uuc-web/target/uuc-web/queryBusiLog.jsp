<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查询虚拟账户业务操作日志列表</title>
<script type="text/javascript" src="/skins/js/My97DatePicker/WdatePicker.js"></script>
</head>

<script type="text/javascript">
	function doQuery() {
		queryForm.submit();
	}
</script><body>
	<textarea rows="8" cols="100" style="color: red">
/**
 * 查询虚拟账户业务操作日志列表
 * @Title: getUserBaseInfoList 
 * @param  userQueryDTO 
 * @return Result    返回类型 
 */
</textarea>

	<s:form id="queryForm" theme="simple" method="post">
		<s:hidden name="currentPage" id="pageId"></s:hidden>

		<div>

			<fieldset class="clearfix adminSearch">
				操作功能:
				<s:textfield name="busiType" theme="simple" cssClass="w100 ml10" />
				操作内容:
				<s:textfield name="afterContent" theme="simple" cssClass="w100 ml10" />
				&nbsp;&nbsp;&nbsp;&nbsp;
				<%--<s:select name="keyWordType" theme="simple" list="resourcePropertyUtil.LOG_KEY_VALUE_TYPE" listKey="key" listValue="value" headerKey="" headerValue="-关键字-" />:<s:textfield name="keyWord"  theme="simple" cssClass="w100 ml10"/>&nbsp;&nbsp;--%>
				操作时间:
				<s:textfield name="createTimeMin" theme="simple" cssClass="inpData"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				-
				<s:textfield name="createTimeMax" theme="simple" cssClass="inpData"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
				<input type="image" src="/skins/img/button_query24.gif" class="ml10"
					onclick="javascript:doQuery();return false" /> <br />
				<%-- 			排序方式：
			<s:select name="sort" theme="simple" list="resourcePropertyUtil.SORT_FIELD_RES" listKey="key" listValue="value" />
			<s:select name="order" theme="simple" list="resourcePropertyUtil.SORT_ORDER" listKey="key" listValue="value"/>
			显示：
			<s:select name="pageSize" theme="simple" list="resourcePropertyUtil.PAGE_SIZE" listKey="key" listValue="value"/> --%>
			</fieldset>

			<div id="infoMessage" style="color: red">
				<s:iterator value="infoMessages">
					<s:property value="value" />
					<br />
				</s:iterator>
			</div>
		</div>


		<table style="BORDER-COLLAPSE: collapse" borderColor=#000000 border=1>
			<tr>
				<td>序号</td>
				<td>卡类型</td>
				<td>操作功能</td>
				<td>关键值类型</td>
				<td>关键值</td>
				<td>操作前内容</td>
				<td>操作后内容</td>
				<td>操作人员</td>
				<td>操作IP</td>
				<td>操作时间</td>
			</tr>
			<s:iterator value="logList" status="status">
				<tr>
					<td><s:property value="#status.index+1" />
					</td>
					<td><s:property value="typeDesc" />
					</td>
					<td><s:property value="busiTypeDesc" />
					</td>
					<td><s:property value="keyWordTypeDesc" />
					</td>
					<td><s:property value="keyWord" />
					</td>
					<td><s:property value="beforeContent" />
					</td>
					<td><s:property value="afterContent" />
					</td>
					<td><s:property value="operId" />
					</td>
					<td><s:property value="operIp" />
					</td>
					<td nowrap="nowrap"><s:property value="createTime" />
					</td>
				</tr>
			</s:iterator>
		</table>
		</div>
		<br>
		<div>
			第几页<input name="currentPage"
				value='<s:property value="result.page.currentPage"/>' />&nbsp; 每页
			<s:property value="result.page.pageSize" />
			条&nbsp; 总共
			<s:property value="result.page.totalRecord" />
			条&nbsp; 总共
			<s:property value="result.page.totalPage" />
			页
		</div>
	</s:form>
</body>
</html>