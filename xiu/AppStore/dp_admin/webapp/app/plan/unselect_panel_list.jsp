<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>精选页版块列表</title>
<script type="text/javascript">
//分页跳转函数
function jumpPage(no) {
	url = 'planItemManage!listUnSelectedPanel.action?';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'planItemManage!listUnSelectedPanel.action?';
	//searchForm是form表单id,url 为跳转路径,start为第几页
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'planItemManage!listUnSelectedPanel.action?limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}


//查询方案 
function doQuery(){
	var searchForm = $("#searchForm").get(0);
	searchForm.action = "planItemManage!listUnSelectedPanel.action";
	searchForm.submit();
}

//返回
function doCancle(){
	parent.document.getElementById("mainFrame").src="planItemManage!listSelected.action?planId=${planId}";
	parent.window.Wclose();
}

//方案关联首页版块
function doAddPlanPanel(){
	var planId = $("#planId").val();
	if(undefined == planId){
		alert("planId不能为空");
		return;
	}
	
	var itemIdArray = new Array();
	var flag = true;
	$(":checkbox[name=checkbox]:checked").each(function(i,e){
		itemIdArray.push(e.value);		
	});
	if(itemIdArray.length < 1){
		alert("请选中要关联的版块");
		return;
	}
	if(confirm("确认关联版块？")){
		$.post("planItemManage!addPlanItem.action",{"planId":planId, "itemIds": itemIdArray.join(","), "operFlag":"addPanel"},function(data){
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
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt; 方案未关联版块列表</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
		<s:form id="searchForm" name="searchForm" action="planItemManage!listUnSelectedPanel.action" method="post" cssStyle="margin:0" theme="simple" >
		<input type="hidden" id="limit" name="limt" value="${limit}" />
		<s:hidden name="planId" id="planId"></s:hidden>
		<ul>
			<li>版块名称：
				<s:textfield id="panelName"
							name="panelName" size="15" maxlength="64"
							value="%{panelName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
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
	<!-- ended of 2011.11.03 Edit  -->
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
			<th scope="col">版块名称</th>
			<th scope="col">布局类型</th>
		</tr>
  		<s:iterator value="page.resultList" var="panel" status="st">
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="${panel.id}" value='<s:property value="id"/>' onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td><a class="detail_link" href="javascript:viewDeatil('<s:property value="id"/>')" >
						<s:if test="%{panelName.length() > 12}">
		               		<s:property value="%{panelName.substring(0, 12) + \"..\"}"/>
		               	</s:if>
		               	<s:else>
		               		<s:property value="panelName"/>
		                </s:else>
					</a></td>
			<td>
				<s:if test="%{layoutType == 1}">栏位</s:if>
				<s:else>入口</s:else>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>

<div class="btnlistbar">
	<s:if test="#session.userResMap.addPlanItem!=null">
		<input class="inputstyle" onclick="doAddPlanPanel()" type="button"  value="<s:text name="sdp.sce.dp.admin.global.btn.confirm" />"/>
	</s:if>
	<input class="inputstyle" onclick="doCancle()" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
