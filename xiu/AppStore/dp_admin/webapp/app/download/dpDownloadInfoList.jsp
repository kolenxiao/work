<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>下载管理</title>
<style type="text/css">
.p1 {
	word-break: break-all;
	width: 630px;
}

.p2 {
	white-space: normal;
	word-wrap: break-word;
	word-break: break-all;
	width: 220px;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript">
	$(function() {
		switchTableRow(".data_list", "tr_even", "tr_hover");
	});
	//分页跳转函数
	function jumpPage(no) {
		url = 'dpdownload!doList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);

	}
	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'dpdownload!doList.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);
	}
	//删除下载
	function del(action) {
		var pageSize = document.getElementById("pageSize").value;
		var idArray = new Array();
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked)
				idArray.push(childCheckboxs[i].id);
		}
		if (idArray.length > 0) {
			//把字符串数组转换成字符串
			var idVal = eval(idArray);
			$("#dialog").remove();
			//弹出确认删除的提示窗口
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform"/>',
					300,
					150,
					'<s:text name="sdp.sce.dp.admin.global.del.data.confirm"/>',
					0, 0, 2, this);
			//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
			$("#dialog_btn_conform").click(function() {
				//执行删除操作
				location.href = action + '?id=' + idVal + '&limit=' + pageSize;
				$("#dialog").remove();
				$("#dialog_bg").remove();
			});
		} else {
			dialogList('<s:text name="sdp.sce.dp.admin.global.inform"/>', 300,
					150,
					'<s:text name="sdp.sce.dp.admin.global.select.del.data"/>',
					0, 0, 1, this);
		}
	}

	function page(start) {
		url = 'dpdownload!doList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页，pageSizeVal为每页显示多少条
		goUrlPage('searchForm', url, start);
	}

</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.global.label.current.position" />
			<s:text name="sdp.sce.dp.admin.download" />
			&gt;
			<s:text name="sdp.sce.dp.admin.download.download.list" />
		</p>
		<div id="pright"></div>
	</div>
	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post"
				cssStyle="margin:0" theme="simple">

				<ul>
					<li>
						<s:text name="sdp.sce.dp.admin.download.downloadName" />
						<s:textfield
							id="downloadName" name="queryDownloadInfo.downloadName" size="15"
							maxlength="200" value="%{queryDownloadInfo.downloadName}"
							onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
					</li>
					<li>
						<s:text name="sdp.sce.dp.admin.download.belong.type" />
						<s:select
							id="dpTypeId" name="querydpType.id" size="1" list="dpTypeList"
							listKey="id" listValue="typeName" value="%{querydpType.id}"
							headerKey="" headerValue="全部" cssStyle="width:120px" />
					</li>
					<li>
						<s:text name="sdp.sce.dp.admin.download.source" />
						<s:textfield
							id="docSource" name="queryDownloadInfo.docSource" size="15"
							maxlength="128" value="%{queryDownloadInfo.docSource}"
							onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
					</li>
					<li>
						<s:text name="sdp.sce.dp.admin.download.createTime" />
						<s:textfield
							id="ctimeStart" name="queryDownloadInfo.ctimeStart" size="10"
							value="%{queryDownloadInfo.ctimeStart}" readonly="true" />
					</li>
					<li>
						<img
							onclick="WdatePicker({el:'ctimeStart',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
					</li>
					<li>
						<s:text name="sdp.sce.dp.admin.global.label.to" />
						<s:textfield
							id="ctimeEnd" name="queryDownloadInfo.ctimeEnd" size="10"
							value="%{queryDownloadInfo.ctimeEnd}" readonly="true" />
					</li>
					<li>
						<img onclick="WdatePicker({el:'ctimeEnd',dateFmt:'yyyy-MM-dd'})"
						src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
						width="16" height="22" />
					</li>
					<li>
						<input type="button" value="" class="btn_sendData"
						onclick="searchDownload('ctimeStart','ctimeEnd')" />&nbsp;
					</li>

				</ul>
			</s:form>
		</div>
	</div>

	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.toDownloadAdd != null">
					<li>
						<a href="<s:property value="#session.userResMap.toDownloadAdd.url"/>
							?forward=add" target="mainFrame">
							<b>
								<s:text	name="sdp.sce.dp.admin.global.btn.add" />
							</b>
						</a>
					</li>
				</s:if>
				<s:if test="#session.userResMap.doDelDownInfo != null">
					<li>
						<a href="#"
							onclick="del('<s:property value="#session.userResMap.doDelDownInfo.url"/>')">
							<b>
								<s:text	name="sdp.sce.dp.admin.global.btn.del" />
							</b>
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
							<input type="checkbox" name="checkbox1" id="checkboxAll"
							onclick="selectAll(this)" />
						</label>
					</span>
				</th>
				<th  scope="col">
					<s:text	name="sdp.sce.dp.admin.global.label.no" />
				</th>

				<th  scope="col">
					<s:text
						name="sdp.sce.dp.admin.download.downloadName" />
				</th>
				<th  scope="col"><s:text
						name="sdp.sce.dp.admin.download.label.downtype" />
				</th>
				<th  scope="col"><s:text
						name="sdp.sce.dp.admin.download.label.creator" />
				</th>
				<th  scope="col"><s:text
						name="sdp.sce.dp.admin.download.label.createTime" />
				</th>
				<th  scope="col"><s:text
						name="sdp.sce.dp.admin.download.label.updateTime" />
				</th>
				<th width="90" class="editbar" scope="col"><s:text
						name="sdp.sce.dp.admin.global.label.operate" />
				</th>
			</tr>

			<s:iterator value="page.resultList" status="st">
				<tr>
					<td>
						<span id="sprycheckbox1">
							<label>
								<input type="checkbox" name="checkbox" id="<s:property value='id' />"
								onclick="selectChildAll()" />
							</label>
						</span>
					</td>
					<td>${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
					<td class="p2" >
						<a
						href="dpdownload!doDisplay.action?forward=detail&dpDownloadInfo.id=<s:property value='id' />"
						target="mainFrame" class="detail_link"> <s:property
								value="downloadName" />
						</a>
					</td>
					<td class="p2">
						<s:property value="dpType.typeName" />
					</td>
					<%-- <td><s:property value="downloadDesc" /></td>	 --%>
					<td>
						<s:property value="createUser" />
					</td>
					<td>
						<s:date name="ctime" format="yyyy-MM-dd" />
					</td>
					<td>
						<s:date name="updateTime" format="yyyy-MM-dd" />
					</td>
					<td class="editbar">
						<s:if test="#session.userResMap.toDownloadEdit != null">
							<a
								href="dpdownload!doDisplay.action?forward=edit&dpDownloadInfo.id=<s:property value='id' />"
								target="mainFrame" class="detail_link"> <s:text
									name="sdp.sce.dp.admin.global.btn.edit" />
							</a>
						</s:if>
						<s:else>
							<font color="#858585">
								<s:property
									value="%{getText('sdp.sce.dp.admin.global.btn.edit')}" />
							</font>
						</s:else> &nbsp; &nbsp;
					</td>
				</tr>
			</s:iterator>

		</table>
	</div>

	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.toDownloadAdd != null">
					<li>
						<a
							href="<s:property value="#session.userResMap.toDownloadAdd.url"/>?forward=add"
							target="mainFrame">
							<b>
							<s:text	name="sdp.sce.dp.admin.global.btn.add" />
							</b>
						</a>
					</li>
				</s:if>
				<s:if test="#session.userResMap.doDelDownInfo != null">
					<li><a href="#"
						onclick="del('<s:property value="#session.userResMap.doDelDownInfo.url"/>')"><b><s:text
									name="sdp.sce.dp.admin.global.btn.del" />
						</b> </a></li>
				</s:if>
			</ul>
		</div>
		<!-- 2011.11.03 Edit 引入分页界面 -->
		<jsp:include page="/common/nextpage.jsp"></jsp:include>
		<!-- ended of 2011.11.03 Edit  -->
	</div>

	<script type="text/javascript">
		document.write("<iframe id='downloadFrame' style='display: none'></iframe> ");
	</script>

	<script type="text/javascript">
		var sprycheckbox1 = new Spry.Widget.ValidationCheckbox("sprycheckbox1",
		{
			isRequired : false
		});
	</script>
</body>
</html>
