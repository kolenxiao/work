<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AP应用列表</title>
<script type="text/javascript">
	//分页跳转函数
	function jumpPage(no) {
		url = 'dpAppInfo!toAddAppToSubjectType.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'dpAppInfo!toAddAppToSubjectType.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'dpAppInfo!toAddAppToSubjectType.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	//向专题中添加应用
	function confirmChoose(id) {
		var idArray = new Array();
		var pageSizeVal = document.getElementById("pageSize").value;
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked)
				idArray.push(childCheckboxs[i].id);
		}
		if (idArray.length > 0) {
			//把字符串数组转换成字符串
			var idVal = eval(idArray);

			var parm = {
				'ids' : idVal,
				'appSubjectType.id':id
			};
			$("#dialog").remove();
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300,
					150,
					'确定向该主题添加选择的应用吗?',
					0, 0, 2, this);
			$("#dialog_btn_conform")
					.click(function() {
								$.ajax({
											cache : false,
											async : false,
											url : 'dpAppSubjectType!doAddAppToSubjectType.action',
											type : 'POST',
											data : parm,
											dataType : 'json',
											error : function(msg) {
												$("#dialog").remove();
												dialogList(
														'<s:text name="sdp.sce.dp.admin.global.inform" />',
														300, 150, 'Error', 0,
														0, 1, this);
											},
											success : function(response) {
												$("#dialog").remove();
												var dataObj = eval(response);
												if (dataObj && dataObj.success) {
													parent.document.getElementById("mainFrame").src="subjectAppinfoRelationAction!toEditSubject.action?appSubjectType.id="
															+id+"&operate=addApp";
													parent.window.Wclose();
												} else if (dataObj
														&& dataObj.msg) {
													dialogList(
															'<s:text name="sdp.sce.dp.admin.global.inform" />',
															300, 150,
															dataObj.msg, 0, 0,
															1, this);
													$("#dialog_btn_conform")
															.click(
																	function() {
																		parent.document.getElementById("mainFrame").src="subjectAppinfoRelationAction!toEditSubject.action?appSubjectType.id="
																				+id+"&operate=addApp";
																		parent.window.Wclose();
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
			$("#dialog").remove();
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300,
					150,
					'没有选择要添加到专题中的应用记录',
					0, 0, 1, this);
		}
	}

	function searchApp() {
		url = 'dpAppInfo!toAddAppToSubjectType.action?';
		var pageSize = document.getElementById("pageSize").value;
		url = url+'start=' + 1 +'&limit='+pageSize;
		var form = document.getElementById("searchForm");
		form.action= url;
		form.submit();
	}

	function doCancle(){
		parent.window.Wclose();
	}
</script>
</head>
<body id="cnt_body">
<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<s:hidden name="appSubjectType.id" value="%{appSubjectType.id}"></s:hidden>
			<ul>
				<li><s:text name="sdp.sce.dp.portal.ap.classfily" />
					<s:select id="queryAppInfo.dpType.id" name="queryAppInfo.dpType.id"
							value="%{queryAppInfo.dpType.id}" headerKey="" headerValue="全部"
							list="dpTypeList" listKey="id" listValue="typeName"
							cssStyle="width:60px;">
					</s:select>
				</li>
				<li><s:text name="sdp.sce.dp.admin.ap.label.appName" />
					<s:textfield id="queryAppInfo.appName"
								name="queryAppInfo.appName" size="15" maxlength="64"
								value="%{queryAppInfo.appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li><s:text name="sdp.sce.dp.admin.ap.label.uploadTime" />
					<s:textfield id="queryAppInfo.beginAppInfoCTime"
							name="queryAppInfo.beginAppInfoCTime" size="10"
							value="%{queryAppInfo.beginAppInfoCTime}"  readonly="true" /></li><li>
							 <img
							onclick="WdatePicker({el:'queryAppInfo.beginAppInfoCTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li><s:text name="sdp.sce.dp.admin.global.label.to" />
					<s:textfield id="queryAppInfo.endAppInfoCTime"
							name="queryAppInfo.endAppInfoCTime" size="10"
							value="%{queryAppInfo.endAppInfoCTime}" readonly="true"/> </li><li>
							<img
							onclick="WdatePicker({el:'queryAppInfo.endAppInfoCTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li>
					<input type="button" value="" class="btn_sendData"  onclick="searchApp()" />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
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
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.appName" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.portal.ap.classfily" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
 			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="<s:property value='id' />" onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td><s:property value="appName"/></td>
			<td><s:property value="dpType.typeName"/></td>
			<td><s:property value="developer"/></td>
			<td><s:date name="createTime" format="yyyy-MM-dd"></s:date></td>
			<td><s:date name="updateTime" format="yyyy-MM-dd"></s:date></td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>

<div class="btnlistbar">
	<input class="inputstyle" onclick="confirmChoose('<s:property value="appSubjectType.id"/>')"
		 type="button"  value="<s:text name="sdp.sce.dp.admin.global.btn.confirm" />"/>
	 <input class="inputstyle" onclick="doCancle()"
	 	type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
</div>
</body>
</html>
