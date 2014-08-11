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
	url = 'dpType!doSearchRecommendList.action?';
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'dpType!doSearchRecommendList.action?';
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'dpType!doSearchRecommendList.action?limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}

// 显示分类详细信息
function viewDeatil(id)
{
	location.href = 'dpType!doDisplay.action?forward=detail&dpType.id='+id;
}

//批量取消推荐应用
function delRecommend() {
	var idArray = new Array();
	var pageSizeVal = document.getElementById("pageSize").value;
	var childCheckboxs = document.getElementsByName("checkbox");
	var c_length = childCheckboxs.length;
	var currentPage = document.getElementById("currentPage").value;

	
	for ( var i = 0; i < c_length; i++) {
		if (childCheckboxs[i].checked)
			idArray.push(childCheckboxs[i].id);
	}
	if (idArray.length > 0) {
		//把字符串数组转换成字符串
		var idVal = eval(idArray);
		var parm = {
			'ids' : idVal,
			'commendFlag':0,
			'operater':1,
			'start':currentPage
		};
		$("#dialog").remove();
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				'确定取消推荐这些分类？',
				0, 0, 2, this);
		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行取消推荐操作
		$("#dialog_btn_conform").click(
			function(){
					$.ajax({
							cache : false,
							async : false,
							url : 'dpType!doCommend.action',
							type : 'POST',
							data : parm,
							dataType : 'json',
							error : function(msg) {
								$("#dialog").remove();
								dialogList(
										'<s:text name="sdp.sce.dp.admin.global.inform" />',
										300, 150, '发送请求出错，请联系管理人员', 0,0, 1, this);
							},
							success : function(response) {
								$("#dialog").remove();
								var dataObj = eval(response);

								if (dataObj && dataObj.success) {
									//删除该页所有记录则返回上一页 
									var totalCount = idArray.length;
									if(c_length == totalCount) {
										currentPage = (currentPage-1) < 0 ? 1 : (currentPage-1);
									}
									
									location.href = "dpType!doSearchRecommendList.action?&limit="
											+ pageSizeVal+"&start="+currentPage;
									
								} else if (dataObj
										&& dataObj.msg) {
									dialogList(
											'<s:text name="sdp.sce.dp.admin.global.inform" />',
											300, 150,
											dataObj.msg, 0, 0,
											1, this);
											$("#dialog_btn_conform").click(
													function() {
														location.href = "dpType!doSearchRecommendList.action?&limit="
																+ pageSizeVal;
											});
								} else if (dataObj
										&& dataObj.exception) {
									dialogList(
											'<s:text name="sdp.sce.dp.admin.global.inform" />',
											300, 150,
											dataObj.exception,
											0, 0, 1, this);
								} else {
									dialogList(
											'<s:text name="sdp.sce.dp.admin.global.inform" />',
											300,
											150,
											'<s:text name="sdp.sce.dp.admin.dptype.type.bind.newsOrdownload" />',
											0, 0, 1, this);
								}
					}
				});
		});

	} else {
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				'请选择需要取消推荐的分类',
				0, 0, 1, this);
	}
}

function searchDpType()
{
	url = 'dpType!doSearchRecommendList.action?';
	goUrlPage('searchForm', url, 1);
}

function addRecommend()
{
	action = "dpType!doSearchUncludeTypeRecommend.action";
	var jsonObj = {
		scrollType : 'yes'
	};

	top.openshow(action,
			'<s:text name="sdp.sce.dp.admin.ap.name"/>&gt; 应用分类推荐&gt;新增分类推荐', 900,
	440, 1, jsonObj);
}

function addAppInfo(id){
	location.href = 'appTypeRecommend!doSearchAppTypeRecommendList.action?queryAppInfo.dpType.id='+id;
}

</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt; 应用分类推荐</p>
		<div id="pright"></div>
	</div>
	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action=""  method="post" cssStyle="margin:0" theme="simple" >
				<ul>
					<li><s:text name="sdp.sce.dp.admin.dptype.nametype" />
						<s:textfield id="dpTypeQueryInfo.typeName"
									name="dpTypeQueryInfo.typeName" size="15" maxlength="64"
									value="%{dpTypeQueryInfo.typeName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
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

	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.cancleTypeRecommend != null">
				<li><a href="#" onclick="delRecommend()"><b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b> </a>
				</li>
				</s:if>
				<s:if test="#session.userResMap.queryUncludeTypeRecommed != null">
				<li>
				<a onclick="addRecommend()" target="mainFrame"><b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b> 
				</a>
				</li>
				</s:if>
			</ul>
		</div>
		<!-- 2011.11.03 Edit 引入分页界面 -->
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
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th width="150"   scope="col"><s:text name="sdp.sce.dp.admin.dptype.nametype" /></th>
			<th width="150"   scope="col">应用数</th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsCreater" /></th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
			<th width="140" class="editbar" scope="col"><s:text name="sdp.sce.dp.admin.global.label.operate" /></th>
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
							<s:property value="typeName" />
					</td>
					<td>
						${page.resultList[st.index].appTotal }
					</td>
					<td><s:property value="createUser"></s:property></td>
					<td><s:date name="createDate" format="yyyy-MM-dd"></s:date></td>
					<td><s:date name="updateDate" format="yyyy-MM-dd"></s:date></td>
					<td class="editbar">
						<s:if test="%{visibleFlag == 1}">
							<a href="javascript:addAppInfo('<s:property value="id"/>')" 	target="mainFrame" >关联应用</a>
							&nbsp;&nbsp;
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>

	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.cancleTypeRecommend != null">
				<li><a href="#" onclick="delRecommend()"><b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b> </a>
				</li>
				</s:if>
				<s:if test="#session.userResMap.queryUncludeTypeRecommed != null">
				<li>
				<a onclick="addRecommend()" target="mainFrame"><b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b> 
				</a>
				</li>
				</s:if>
			</ul>
		</div>
		<jsp:include page="/common/nextpage.jsp"></jsp:include>
	</div>
</body>
</html>
