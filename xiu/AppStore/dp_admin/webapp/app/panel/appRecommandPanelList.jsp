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
	url = 'appRecommendPanel!doList.action?';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'appRecommendPanel!doList.action?';
	//searchForm是form表单id,url 为跳转路径,start为第几页
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'appRecommendPanel!doList.action?limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}

function createInput(parentID,index){
	var d = "#"+parentID;
    $(d + " a").hide();
	var id = parentID;
    $("div[id='"+parentID+"']").append("<input type='text' id='sort' paramId='"+id+"' regexp='^[0-9]*[1-9][0-9]*$' name='sort' size='3' valid='required|regexp' errmsg='dp_sort_required|dp_sort_regexp'/>");
    $("div[id='"+parentID+"']").append("<input type='button' id='button' paramId='"+id+"' onclick='saveSort(this)' name='button' value='保存' />");
    $("div[id='"+parentID+"']").append("<input type='button' id='button' paramId='"+id+"' onclick='cancle(this)' name='button' value='取消' style='width:34px;height:20px;border:none;color:#006DC1;padding:1px 4px;background:url(images/bg_select00.gif) no-repeat;' />");
    $("div[id='"+parentID+"']").append("<input type='hidden' id='errMsg_sort' style='color: #FF0000'/>");
    $("div[id='"+parentID+"']").append("<input type='hidden' id='index' name='index' value='"+index+"'/>");
    $("div[id='"+parentID+"']").append("<input type='hidden' id='id' name='id' value='${panel.id}'/>");
 
}

function cancle(inputE){
	var id = $(inputE).attr("paramId");
	var d = "#" + id;
	$(d + " a").show();

	$("input[paramId='" + id + "']").remove();
	$("span[id='errMsg_sort']").remove();
}

function saveSort(inputE) {
	var id = $(inputE).attr("paramId");
	var formId = id +"sortForm";
	var sortForm = document.getElementById(formId);

	initValid(sortForm);
	if (validator(sortForm)) {
		$("#"+formId).submit();
	}
}

function addPanel(){
	window.location.href="appRecommendPanel!toSavePanel.action";
}

//删除版块信息
function delPanel() {
	var pageSizeVal = document.getElementById("pageSize").value;
	var param = {'panelKey':'${panelKey}'};
	var pop = {'tips':'确定删除这些版块吗?',
			   'reqURL':'appRecommendPanel!doDeletePanel.action',
			   'respURL':'appRecommendPanel!doList.action?limit='+ pageSizeVal+'&start=${page.currentPage}',
			   'param'    : param};
	confirmChoose(pop);//ajax提交
}

</script>
</head>

<body id="cnt_body">

<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>精选页版块管理&gt; 精选页版块列表</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="appRecommendPanel!doList.action" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}"/>
			<ul>
				<li>版块名称：
					<s:textfield id="panelName"
								name="panelName" size="15" maxlength="64"
								value="%{panelName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				
				<li>
					<input type="submit" value="" class="btn_sendData"  />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
</div>

<div class="databar">
	<div class="btnbar">
		<ul>
            <li><a href="#" onclick="delPanel()"><b>删除</b></a></li>
             <li><a href="#" onclick="addPanel()"><b>新增</b></a></li>
		</ul>
	</div>
	<jsp:include page="/common/prepage.jsp"></jsp:include>
	<!-- ended of 2011.11.03 Edit  -->
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox1" id="checkboxAll" onclick="selectAll(this)" />
					</label>
				</span>
			</th>
			<th scope="col">序号</th>
			<th scope="col">版块名称</th>
			<th scope="col">布局类型</th>
			<th scope="col">当前顺序</th>
			<th width="320" class="editbar" scope="col"><s:text name="sdp.sce.dp.admin.global.label.operate" /></th>
			<th width="200" scope="col">自定义排序</th>
		</tr>
  		<s:iterator value="page.resultList" var="panel" status="st">
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="${panel.id}" onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
            <td>${panel.panelName }</td>
            <td>${panel.layoutType == 1?"栏位":"入口"}</td>
            <td>${panel.sortNum}</td>
            <td class="editbar">
               <a href="${pageContext.request.contextPath}/appRecommendPanel!toDetail.action?key=${panel.id}" >编辑</a> 
               &nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/appRecommendPanelItem!doPanelItemList.action?panelKey=${panel.id}">版块元素管理</a>
            </td>
			<td class="editbar">				
				<s:form method="post" id="%{id}sortForm" action="appRecommendPanel!doSort.action" name="%{id}sortForm" theme="simple">
					<s:hidden name="limit" id="limit" value="%{limit}"></s:hidden>
					<input type="hidden" name="key" id="key" value="${panel.id}">
					<div id="<s:property value="id"/>"><a href="#"
					onclick="createInput('${panel.id}','${(page.currentPage-1) * page.pageSize + st.index + 1 }')">人工排序</a></div>
				</s:form>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<div class="btnbar">
		<ul>
             <li><a href="#" onclick="delPanel()"><b>删除</b></a></li>
             <li><a href="#" onclick="addPanel()"><b>新增</b></a></li>
		</ul>
	</div>
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
