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
		url = 'implicit!doList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'implicit!doList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'implicit!doList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	//删除隐式应用
	function del() {
		var idArray = new Array();
		var pageSizeVal = document.getElementById("pageSize").value;
		var childCheckboxs = document.getElementsByName("checkbox");
		var currentPage = document.getElementById("currentPage").value;
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
								$.ajax({
									cache : false,
									async : false,
									url : 'implicit!doDelete.action',
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
											location.href = "implicit!doList.action?start="+currentPage+"&limit="
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
																location.href = "implicit!doList.action?&start="+currentPage+"&limit="
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

	// 隐式应用启用和停用
	function changeImplicitStatus(id, status) {
		var pageSizeVal = document.getElementById("pageSize").value;

		//把字符串数组转换成字符串
		var parm = {
			'implicitApp.id' : id,
			'implicitApp.status' : status
		};

		$("#dialog").remove();
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				'要更改该隐式应用的状态吗？',
				0, 0, 2, this);

		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行更改状态操作
		$("#dialog_btn_conform")
			.click(
				function() {
				$.ajax({
					cache : false,
					async : false,
					url : 'implicit!doOnOrOffLine.action',
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
							location.href = "implicit!doList.action?&limit="
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
												location.href = "implicit!doList.action?&limit="
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
	}

	// 隐式应用启用和停用
	function addUninstallImplicitApp(id) {
		var pageSizeVal = document.getElementById("pageSize").value;

		//把字符串数组转换成字符串
		var parm = {
			'implicitApp.id' : id
		};

		$("#dialog").remove();
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				'确认要新增类型为卸载的隐式应用吗？',
				0, 0, 2, this);

		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行新增卸载类型的隐式应用
		$("#dialog_btn_conform")
			.click(
				function() {
				$.ajax({
					cache : false,
					async : false,
					url : 'implicit!doUninstall.action',
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
							location.href = "implicit!doList.action?&limit="
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
												location.href = "implicit!doList.action?&limit="
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
	}

	//显示应用详细信息
	function viewDeatil(id) {
		location.href = 'implicit!doDetail.action?implicitApp.id='
				+ id;
	}

	function searchImplicit()
	{
		url = 'implicit!doList.action?';
		goUrlPage('searchForm', url, 1);
	}
</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.global.label.current.position" />
			<s:text name="隐式应用管理" />
			&gt;
			<s:text name="新增隐式应用" />
		</p>
		<div id="pright"></div>
	</div>

	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="implicit!doList.action" method="post"
				cssStyle="margin:0" theme="simple">
				<ul>
					<li><s:text name="sdp.sce.dp.admin.dptype.nametype" />
						<s:textfield id="queryImplicitApp.appName"
									name="queryImplicitApp.appName" size="15" maxlength="64"
									value="%{queryImplicitApp.appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
					</li>
					<li>终端型号：
						<s:select
							list="#{'':'全选',1:'MSTAR',2:'HISI',3:'MTK'}"
							id="queryImplicitApp.teminalNum"
							name="queryImplicitApp.teminalNum"
							value="%{queryImplicitApp.teminalNum}">
						</s:select>
					</li>
					<li>隐式类型
						<s:select
							list="#{'':'全选','1':'安装','3':'卸载','2':'升级'}"
							id="queryImplicitApp.implicitType"
							name="queryImplicitApp.implicitType"
							value="%{queryImplicitApp.implicitType}">
						</s:select>
					</li>
					<li>状态
						<s:select
							list="#{'':'全选','0':'启用','1':'停用'}"
							id="queryImplicitApp.status"
							name="queryImplicitApp.status"
							value="%{queryImplicitApp.status}">
						</s:select>
					</li>
					<li>
						<input type="submit" value="" class="btn_sendData"  onclick="searchImplicit();" />&nbsp;
					</li>
				</ul>
			</s:form>
		</div>
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
					<li><a href="implicit!toAdd.action" target="mainFrame"><b><s:text
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
							onclick="selectAll(this)" /> </label> </span>
				</th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.appstore.name" />
				<th scope="col">
					隐式类型
				</th>
				<th scope="col">
					终端型号
				</th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.appstore.status" />
				</th>
				<th scope="col"><s:text
						name="sdp.sce.dp.admin.appstore.createTime" />
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
						st.index + 1 }
					</td>
					<td>
						<a class="detail_link"
							href="javascript:viewDeatil('<s:property value="id"/>')">
							<s:if test="%{appName.length() > 12}">
								<s:property value="%{appName.substring(0, 12) + \"..\"}" />
							</s:if>
							<s:else>
								<s:property value="appName" />
							</s:else>
						</a>
					</td>
					<td>
						<s:if test='%{implicitType=="1"}'>
							安装
						</s:if>
						<s:elseif test='%{implicitType=="2"}'>
							升级
						</s:elseif>
						<s:elseif test='%{implicitType=="3"}'>
							卸载
						</s:elseif>
					</td>
					<td>
						<s:if test='%{teminalNum == "1"}'>MSTAR</s:if>
						<s:elseif test='%{teminalNum == "2"}'>HISI</s:elseif>
						<s:elseif test='%{teminalNum == "3"}'>MTK</s:elseif>
					</td>
					<td>
						<s:if test='%{status == "0"}'>
							启用
						</s:if>
						<s:elseif test='%{status == "1"}'>
							停用
						</s:elseif>
					</td>
					<td><s:date name="createTime" format="yyyy-MM-dd HH:ss"></s:date></td>
					<td class="editbar">
						<s:if test='%{status == "1"}'>
							<s:if
								test="#session.userResMap.doModifyAppClient!=null">
								<a href="implicit!toModify.action?implicitApp.id=${id}&impType=${implicitType}"
									target="mainFrame"> <s:text
										name="sdp.sce.dp.admin.global.btn.modify" /> </a>
							</s:if>
						</s:if>
						<s:if test="#session.userResMap.doOnOrOffAppClient!=null">
							<s:if test='%{status == "1"}'>
								<a href="javascript:changeImplicitStatus('${id}','0')"
									target="mainFrame">启用
								</a>
							</s:if>
							<s:elseif test='%{status == "0"}'>
								<a href="javascript:changeImplicitStatus('${id}','1')"
									target="mainFrame">停用
								</a>
							</s:elseif>
						</s:if>

					</td>
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
					<li><a href="implicit!toAdd.action" target="mainFrame"><b><s:text
									name="sdp.sce.dp.admin.global.btn.add" />
						</b> </a></li>
				</s:if>
			</ul>
		</div>
		<jsp:include page="/common/nextpage.jsp"></jsp:include>
	</div>
</body>
</html>
