<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.news.newsType" /></title>
<style type="text/css">
    .p1{ word-break:break-all; width:630px;}
   .p2{white-space:normal;word-wrap:break-word; word-break:break-all;width:220px;}
</style>
<script type="text/javascript">

//分页跳转函数
function jumpPage(no) {
	url = 'dpType!doSearchUncludeTypeRecommend.action?';
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'dpType!doSearchUncludeTypeRecommend.action?';
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'dpType!doSearchUncludeTypeRecommend.action?limit=' + pageSizeVal;
	formSubmit('searchForm', url);//表单提交
}

// 显示分类详细信息
function viewDeatil(id)
{
	location.href = 'dpType!doDisplay.action?forward=detail&dpType.id='+id;
}

//推荐应用
function recommendChoose()
{
	var pageSizeVal = document.getElementById("pageSize").value;
	var param = {'commendFlag':'1','operater':'1'};
	var pop = {'tips'     :'确定推荐选择的分类吗?',
			   'reqURL'   :'dpType!doCommend.action',
			   'param'    : param,
			   'callback' : function(){document.forms['searchForm'].submit(); }};
	confirmChoose2(pop);//ajax提交
}

function searchDpType()
{
	url = 'dpType!doSearchUncludeTypeRecommend.action?';
	goUrlPage('searchForm', url, 1);
}

function doCancle(){
	parent.document.getElementById("mainFrame").src="dpType!doSearchRecommendList.action";
	parent.window.Wclose();
}

</script>
</head>
<body id="cnt_body">
	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="dpType!doSearchUncludeTypeRecommend.action"  method="post" cssStyle="margin:0" theme="simple" >
				<ul>
					<li><s:text name="sdp.sce.dp.admin.dptype.nametype" />
						<s:textfield id="dpTypeQueryInfo.typeName"
									name="dpTypeQueryInfo.typeName" size="15" maxlength="64"
									value="%{dpTypeQueryInfo.typeName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
					</li>

					<li>所属类别
					<s:select list="dpTypeList" headerValue="所有分类" headerKey ="" size="1" select="selected"
						name="dpTypeQueryInfo.parentTypeCode" id="dpTypeQueryInfo.parentTypeCode"
						listKey="typeCode" listValue="typeName" value="%{dpTypeQueryInfo.typeCode}">
					</s:select>
					</li>
					<li>显示状态
					<s:select
						list="#{0:'全选',1:'显示',2:'隐藏'}"
						id="dpTypeQueryInfo.visibleFlag"
						name="dpTypeQueryInfo.visibleFlag"
						value="%{dpTypeQueryInfo.visibleFlag}">
					</s:select>
					</li>
					<li>
						<input type="submit" value="" class="btn_sendData"  onclick="searchDpType();" />&nbsp;
					</li>
				</ul>
			</s:form>
		</div>
	</div>
		<!-- 2011.11.03 Edit 引入分页界面 -->
			
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
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th width="150"   scope="col"><s:text name="sdp.sce.dp.admin.dptype.nametype" /></th>
			<th width="150"   scope="col">所属类别</th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsCreater" /></th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
		</tr>
			<s:iterator value="page.resultList" status="st"  >
				<tr>
					<td>
						<span id="sprycheckbox1">
						 	<label>
						 		<input type="checkbox" name="checkbox" id="<s:property value='id' />" onclick="selectChildAll()" />
							</label>
						</span>
					</td>
					<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
					<td class="p2">
						<a class="detail_link" href="javascript:viewDeatil('<s:property value="id"/>')" >
							<s:property value="typeName" />
						</a>
					</td>
					<td>
						${page.resultList[st.index].parentTypeName }
					</td>
					<td><s:property value="createUser"></s:property></td>
					<td><s:date name="createDate" format="yyyy-MM-dd"></s:date></td>
					<td><s:date name="updateDate" format="yyyy-MM-dd"></s:date></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div class="databar">
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
    </div>
	<div class="btnlistbar">
	<input class="inputstyle" onclick="recommendChoose()"
		 type="button"  value="<s:text name="sdp.sce.dp.admin.global.btn.confirm" />"/>
	 <input class="inputstyle" onclick="doCancle()"
	 	type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
	</div>
</body>
</html>
