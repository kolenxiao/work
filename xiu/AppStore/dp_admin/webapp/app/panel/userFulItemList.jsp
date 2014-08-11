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
	url = 'appRecommendPanelItem!doUseFulItemList.action?';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'appRecommendPanelItem!doUseFulItemList.action?';
	//searchForm是form表单id,url 为跳转路径,start为第几页
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'appRecommendPanelItem!doUseFulItemList.action?limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}

function doCancle(){
	parent.document.getElementById("mainFrame").src="appRecommendPanelItem!doPanelItemList.action?panelKey=${panelKey}";
	parent.window.Wclose();
}

function doRecommend(){
	var pageSizeVal = document.getElementById("pageSize").value;
	var param = {'panelKey':'${panelKey}'};
	var pop = {'tips':'确定要关联这些元素吗?',
			   'reqURL':'appRecommendPanelItem!doRecommendItem.action',
			   'respURL':'appRecommendPanelItem!doUseFulItemList.action?limit='+ pageSizeVal+'&start=${page.currentPage}&panelKey=${panelKey}',
			   'param'    : param};
	confirmChoose(pop);//ajax提交
}
</script>
</head>

<body id="cnt_body">

<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>精选页版块管理&gt; 版块关联元素</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="appRecommendPanelItem!doUseFulItemList.action" method="post" cssStyle="margin:0" theme="simple" >
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
			<th scope="col">元素海报</th>
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
            <td><img src="${appFilePath.imgPath}${panel.itemPoster}" style="width:60px;height:60px;"></td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>

<div class="btnlistbar">
	<input class="inputstyle" onclick="doRecommend()" type="button"  value="<s:text name="sdp.sce.dp.admin.global.btn.confirm" />"/>
	 <input class="inputstyle" onclick="doCancle()" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
