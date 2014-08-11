<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用商店客户端列表</title>
<script type="text/javascript">
	//分页跳转函数
	function jumpPage(no) {
		url = 'appStoreClient!doList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'appStoreClient!doList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'appStoreClient!doList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	//删除应用商店客户端
	function del() {
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
				'ids' : idVal
			};

			$("#dialog").remove();

			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300,
					150,
					'<s:text name="sdp.sce.dp.admin.global.del.data.confirm" />',
					0, 0, 2, this);

			//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
			$("#dialog_btn_conform")
					.click(
							function() {
								$
										.ajax({
											cache : false,
											async : false,
											url : 'appStoreClient!doDelete.action',
											type : 'POST',
											data : parm,
											dataType : 'json',
											error : function(msg) {
												dialogList(
														'<s:text name="sdp.sce.dp.admin.global.inform" />',
														300, 150, 'Error', 0,
														0, 1, this);
											},
											success : function(response) {
												$("#dialog").remove();
												var dataObj = eval(response);
												if (dataObj && dataObj.success) {
													location.href = "appStoreClient!doList.action?&limit="
															+ pageSizeVal;
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
																		location.href = "appStoreClient!doList.action?&limit="
																				+ pageSizeVal;
																	});
												} else if (dataObj
														&& dataObj.exception) {
													$("#dialog").remove();
													dialogList(
															'<s:text name="sdp.sce.dp.admin.global.inform" />',
															300, 150,
															dataObj.exception,
															0, 0, 1, this);
												} else {
													$("#dialog").remove();
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
					'<s:text name="sdp.sce.dp.admin.global.select.del.data" />',
					0, 0, 1, this);
		}
	}

	//显示应用详细信息
	function viewDeatil(id) {
		location.href = 'appStoreClient!doDetail.action?appStoreClient.id='
				+ id;
	}
</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.global.label.current.position" />
			<s:text name="sdp.sce.dp.admin.appstore.appStoreManage" />
			&gt;
			<s:text name="sdp.sce.dp.admin.appstore.appStoreClientList" />
		</p>
		<div id="pright"></div>
	</div>

	<div id="searchbar">
		<s:form id="searchForm" name="searchForm" action="" method="post"
			cssStyle="margin:0" theme="simple">
		</s:form>
	</div>

	<div class="databar">

		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.doDeleteAppStoreClient!=null">
					<li><a href="#" onclick="del()"><b><s:text
									name="sdp.sce.dp.admin.global.btn.del" />
						</b> </a></li>
				</s:if>
				<s:if test="#session.userResMap.doAddAppClient!=null">
					<li><a href="appStoreClient!toAdd.action" target="mainFrame"><b><s:text
									name="sdp.sce.dp.admin.global.btn.add" />
						</b> </a></li>
				</s:if>
			</ul>
		</div>
		<jsp:include page="/common/prepage.jsp"></jsp:include>
	</div>
	<div class="data_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th width="40" scope="col"><span id="sprycheckbox1"> <label>
							<input type="checkbox" name="checkbox1" id="checkboxAll"
							onclick="selectAll(this)" /> </label> </span></th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" />
				</th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.appstore.name" />
				</th>
				<th scope="col"><s:text
						name="sdp.sce.dp.admin.appstore.versionCode" />
				</th>
				<th scope="col"><s:text
						name="sdp.sce.dp.admin.appstore.minSystem" />
				</th>
				<th scope="col">终端型号
				</th>
				<th scope="col"><s:text
						name="sdp.sce.dp.admin.appstore.apkFile" />
				</th>
				<th scope="col"><s:text
						name="sdp.sce.dp.admin.appstore.createTime" />
				</th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.appstore.status" />
				</th>
				<th width="140" class="editbar" scope="col"><s:text
						name="sdp.sce.dp.admin.global.label.operate" />
				</th>
			</tr>
			<s:iterator value="page.resultList" status="st">
				<tr>
					<td><span id="sprycheckbox1"> <label> <input
								type="checkbox" name="checkbox" id="<s:property value='id' />"
								onclick="selectChildAll()" /> </label> </span></td>
					<td width="50">${(page.currentPage-1) * page.pageSize +
						st.index + 1 }</td>
					<td><a class="detail_link"
						href="javascript:viewDeatil('<s:property value="id"/>')"> <s:if
								test="%{appName.length() > 12}">
								<s:property value="%{appName.substring(0, 12) + \"..\"}" />
							</s:if> <s:else>
								<s:property value="appName" />
							</s:else> </a></td>
					<td><s:property value="versionCode" /></td>
					<td><s:property value="system" /></td>
					<td>
						<s:if test='%{teminalNum == "1"}'>MSTAR</s:if>
						<s:elseif test='%{teminalNum == "2"}'>HISI</s:elseif>
						<s:elseif test='%{teminalNum == "3"}'>MTK</s:elseif>
					</td>
					<td><s:property value="apkFileSavePath" /></td>
					<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"></s:date>
					</td>
					<td>
					<s:if test="%{status == \"1\"}">
						在线
					</s:if>
					<s:else>
						下线
					</s:else>
					</td>
					<td class="editbar">
					<s:if test="%{status == \"2\"}">
						<s:if
							test="#session.userResMap.doModifyAppClient!=null">
							<a href="appStoreClient!toModify.action?appStoreClient.id=${id}"
								target="mainFrame"> <s:text
									name="sdp.sce.dp.admin.global.btn.modify" /> </a>
						</s:if>
					</s:if>
						<s:if test="#session.userResMap.doOnOrOffAppClient!=null">
							<s:if test="%{status == \"2\"}">
								<a
									href="appStoreClient!doOnOrOffLine.action?appStoreClient.id=${id}&appStoreClient.status=1"
									target="mainFrame"> 上架 </a>
							</s:if>
							<s:if test="%{status == \"1\"}">
								<a
									href="appStoreClient!doOnOrOffLine.action?appStoreClient.id=${id}&appStoreClient.status=2"
									target="mainFrame"> 下架 </a>
							</s:if>
						</s:if></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.doDeleteAppStoreClient!=null">
					<li><a href="#" onclick="del()"><b><s:text
									name="sdp.sce.dp.admin.global.btn.del" />
						</b> </a></li>
				</s:if>
				<s:if test="#session.userResMap.doAddAppClient!=null">
					<li><a href="appStoreClient!toAdd.action" target="mainFrame"><b><s:text
									name="sdp.sce.dp.admin.global.btn.add" />
						</b> </a></li>
				</s:if>
			</ul>
		</div>
		<jsp:include page="/common/nextpage.jsp"></jsp:include>
	</div>
</body>
</html>
