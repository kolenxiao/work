<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>替换推荐应用列表</title>
<script type="text/javascript">

//分页跳转函数
function jumpPage(no) {
	url = 'dpAppInfo!getAppListForReplace.action?';
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'dpAppInfo!getAppListForReplace.action?';
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'dpAppInfo!getAppListForReplace.action?limit=' + pageSizeVal;
	formSubmit('searchForm', url);
}

//查询
function doQuery(){
	var searchForm = $("#searchForm").get(0);	
	searchForm.action = "dpAppInfo!getAppListForReplace.action";
	searchForm.submit();
}

//替换
function replaceApp(appPkgName, appName){
	window.returnValue = appName + "||" + appPkgName;
	window.close();
}

</script>
</head>

<body id="cnt_body">

<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>猜你喜欢&gt; 替换推荐应用列表</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}" />
			<s:hidden name="planId" id="planId"></s:hidden>
			<s:hidden name="sourceAppPkgName" id="sourceAppPkgName"></s:hidden>
			<ul>
				<li>应用名称：
					<s:textfield id="appName"
								name="appName" size="15" maxlength="64"
								value="%{appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>分类：
						<s:select id="typeId" name="typeId" value="%{typeId}" headerKey="" headerValue="全部"
								list="dpTypeList" listKey="id" listValue="typeName" cssStyle="width:60px;">
						</s:select>
				</li>
				<li>
					<input type="submit" value="" onclick="doQuery()" class="btn_sendData"  />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
</div>

<div class="databar">
	<jsp:include page="/common/prepage.jsp"></jsp:include>
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
			<th scope="col">序号</th>
			<th scope="col">应用名称</th>
			<th scope="col">应用分类</th>
			<th scope="col">应用状态</th>
			<th scope="col">操作</th>
		</tr>
  		<s:iterator value="page.resultList" status="st">
		<tr >
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td><a class="detail_link" href="javascript:viewDeatil('<s:property value="id"/>')" >
						<s:if test="%{appName.length() > 12}">
		               		<s:property value="%{appName.substring(0, 12) + \"..\"}"/>
		               	</s:if>
		               	<s:else>
		               		<s:property value="appName"/>
		                </s:else>
					</a></td>
			<td><s:property value="dpType.typeName"/></td>
			<td><s:if test="%{appStatus == '1000'}">
					<font color="green">草稿</font>
				</s:if>
				<s:if test="%{appStatus == '1001'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.Pending" /></font>
				</s:if>
				<s:elseif test="%{appStatus == '1002'}">
					<font color="red">审核未通过</font>
				</s:elseif>
				<s:elseif test="%{appStatus == '1003'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.pass" /></font>
				</s:elseif>
				<s:elseif test="%{appStatus == '1004'}">
					<font color="green">已上架</font>
				</s:elseif>
				<s:elseif test="%{appStatus == '1005'}">
					<font color="green">已下架</font>
				</s:elseif>
				<s:elseif test="%{appStatus == '1006'}">
					<font color="green">版本更新</font>
				</s:elseif></td>
			<td class="editbar">
				<a href="#" onclick="replaceApp('<s:property value="appFilePackage"/>', '<s:property value="appName"/>')" >
					选择
				</a>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>

<div class="btnlistbar">
	 <input class="inputstyle" type="button" value="关闭" onclick="window.parent.close()" />
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
