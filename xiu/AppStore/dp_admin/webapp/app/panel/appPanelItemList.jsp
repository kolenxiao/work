<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>版块下的元素列表</title>
<script type="text/javascript">
//分页跳转函数
function jumpPage(no) {
	url = 'appRecommendPanelItem!doPanelItemList.action?';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'appRecommendPanelItem!doPanelItemList.action?';
	//searchForm是form表单id,url 为跳转路径,start为第几页
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'appRecommendPanelItem!doPanelItemList.action?limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}

function addRecommend()
{
	action = "appRecommendPanelItem!doUseFulItemList.action?panelKey=${panelKey}";
	var jsonObj = {
		scrollType : 'yes'
	};

	top.openshow(action,
			'<s:text name="sdp.sce.dp.admin.ap.appinfo.detail" />', 1000,
	540, 1, jsonObj);
}

function delRecommendItem() {
	var pageSizeVal = document.getElementById("pageSize").value;
	var param = {'panelKey':'${panelKey}'};
	var pop = {'tips':'确定要取消关联这些元素吗?',
			   'reqURL':'appRecommendPanelItem!doDeletePanelItem.action',
			   'respURL':'appRecommendPanelItem!doPanelItemList.action?limit='+ pageSizeVal+'&start=${page.currentPage}&panelKey=${panelKey}',
			   'param'    : param};
	confirmChoose(pop);//ajax提交
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
</script>
</head>

<body id="cnt_body">

<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>精选页版块管理&gt;版块元素管理</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="appRecommendPanelItem!doPanelItemList.action" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}"/>
			<input type="hidden" id="panelKey" name="panelKey" value="${panelKey}"/>
			<ul>
				<li>元素名称：
					<s:textfield id="itemName"
								name="itemName" size="15" maxlength="64"
								value="%{itemName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>
				元素类型：
				     <s:select name="type" id="type" list="#{'':'全部','1':'应用','2':'专题','3':'文本'}" label="元素类型"></s:select>
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
            <li><a href="#" onclick="delRecommendItem()"><b>取消关联</b></a></li>
           <li><a href="#" onclick="addRecommend()"><b>关联元素</b></a></li>
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
			<th scope="col">元素名称</th>
			<th scope="col">元素类型</th>
			<th scope="col">当前顺序</th>
			<th scope="col">元素海报</th>
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
            <td>${panel.itemName }</td>
            <td>${panel.type == 1?"应用":(panel.type == 2?"专题":"文本")}</td>
            <td>${panel.sortNum}</td>
            <td><a href="${appFilePath.imgPath}${panel.itemPoster}" target="_blank"><img src="${appFilePath.imgPath}${panel.itemPoster}" style="width:80px;height:80px;"></a></td>
			<td class="editbar">				
				<s:form method="post" id="%{id}sortForm" action="appRecommendPanelItem!doSort.action" name="%{id}sortForm" theme="simple">
				    <input type="hidden" id="panelKey" name="panelKey" value="${panelKey}"/>
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
            <li><a href="#" onclick="delRecommendItem()"><b>取消关联</b></a></li>
            <li><a href="#" onclick="addRecommend()"><b>关联元素</b></a></li>
		</ul>
	</div>
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
