<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用专题列表</title>
<script type="text/javascript">

$(document).ready(function() {
	//绑定窗口关闭事件
/* 	window.onunload = function(){
		if (window.top!=window.self){
			doCancle();
		}
	} */
})

//分页跳转函数
function jumpPage(no) {
	url = 'planItemAppManage!listUnSelectedApp.action?';
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'planItemAppManage!listUnSelectedApp.action?';
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'planItemAppManage!listUnSelectedApp.action?limit=' + pageSizeVal;
	formSubmit('searchForm', url);
}


//查询方案 
function doQuery(){
	var searchForm = $("#searchForm").get(0);
	searchForm.action = "planItemAppManage!listUnSelectedApp.action";
	searchForm.submit();
}

//返回
function doCancle(){
	var url = "planItemAppManage!listSelectedApp.action?planId=${planId}&planItemId=${planItemId}";
	parent.document.location.href = url;

}

function doAddPlanItemApp(){
	var planItemId = $("#planItemId").val();
	if(undefined == planItemId){
		alert("planItemId不能为空");
		return;
	}
	
	var appIdArray = new Array();
	var flag = true;
	$(":checkbox[name=checkbox]:checked").each(function(i,e){
		appIdArray.push(e.value);		
	});
	if(appIdArray.length < 1){
		alert("请选中要关联的应用");
		return;
	}
	if(confirm("确认关联分类？")){
		$.post("planItemAppManage!addPlanItemApp.action",{"planItemId":planItemId, "appIds": appIdArray.join(",")},function(data){
			if("success" == data){
				alert("操作成功");
				doQuery();
			}else{
				alert(data);
			}
		});
	}
}

</script>
</head>

<body id="cnt_body">

<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt; 未关联应用列表</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}" />
			<s:hidden name="planId" id="planId"></s:hidden>
			<s:hidden name="planItemId" id="planItemId"></s:hidden>
			<ul>
				<li>应用名称：
					<s:textfield id="appName"
								name="appName" size="15" maxlength="64"
								value="%{appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<s:if test="%{4 == itemType}">
					<li>分类：
						<s:select id="typeId" name="typeId" value="%{typeId}" headerKey="" headerValue="全部"
								list="result.dpTypeList" listKey="id" listValue="typeName" cssStyle="width:60px;">
						</s:select>
					</li>
				</s:if>
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
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkboxAll" id="checkboxAll" onclick="selectAll(this)" />
					</label>
				</span>
			</th>
			<th scope="col">序号</th>
			<th scope="col">应用名称</th>
			<th scope="col">应用分类</th>
			<th scope="col">应用状态</th>
		</tr>
  		<s:iterator value="page.resultList" status="st">
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" value='<s:property value="id"/>' onclick="selectChildAll()" />
					</label>
				</span>
			</td>
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
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>

<div class="btnlistbar">
	<s:if test="#session.userResMap.createPlanItemApp!=null">
		<input class="inputstyle" onclick="doAddPlanItemApp()" type="button"  value="<s:text name="sdp.sce.dp.admin.global.btn.confirm" />"/>
	</s:if>
	 <input class="inputstyle" onclick="doCancle()" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
