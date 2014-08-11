<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AP应用专题列表</title>
<script type="text/javascript">
	//分页跳转函数
	function jumpPage(no) {
		url = 'dpAppSubjectType!doSearchAppSubjectTypeList.action?';
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'dpAppSubjectType!doSearchAppSubjectTypeList.action?';
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'dpAppSubjectType!doSearchAppSubjectTypeList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	//删除专题
	function delSubject() {
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
								$.ajax({cache : false,
										async : false,
										url : 'dpAppSubjectType!deleteSubject.action',
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
												location.href = "dpAppSubjectType!doSearchAppSubjectTypeList.action?&limit="
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
																	location.href = "dpAppSubjectType!doSearchAppSubjectTypeList.action?&limit="
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
			$("#dialog").remove();
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300,
					150,
					'<s:text name="sdp.sce.dp.admin.global.select.del.data" />',
					0, 0, 1, this);
		}
	}

	// 更改显示状态
	function changeVisibleState(vFlag, id) {
		
		var pageSizeVal = document.getElementById("pageSize").value;
		var currentPage = document.getElementById("currentPage").value;

		//把字符串数组转换成字符串
		var param = {'appSubjectType.visibleFlag':vFlag, 'appSubjectType.id':id};
		$("#dialog").remove();
		
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				'确认要修改专题的显示状态吗？',
				0, 0, 2, this);
		
		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform").click(function() {
			var ajaxParam = {'reqURL' :'dpAppSubjectType!doChangeVisibleStateType.action', 
			                 'param'  :param,
			                 'respURL':'dpAppSubjectType!doSearchAppSubjectTypeList.action?&start='+currentPage+'&limit='
							       + pageSizeVal,
					         'callback':function(){document.forms['searchForm'].submit();}};
			//异步提交
			submitByAjax(ajaxParam);
		});
	}

	function searchAppSubjectType(startTimeId, endTimeId) {
		url = 'dpAppSubjectType!doSearchAppSubjectTypeList.action?';
		querydata('searchForm', url, startTimeId, endTimeId);
	}

	//显示应用专题详细信息
	function viewDeatil(id) {
		location.href = 'dpAppSubjectType!doDisplay.action?appSubjectType.id='
				+ id;
	}
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt; 专题列表</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<ul>
				<li>专题名称：
					<s:textfield id="queryAppSubjectType.subjectName"
								name="queryAppSubjectType.subjectName" size="30" maxlength="64"
								value="%{queryAppSubjectType.subjectName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>显示状态
					<s:select
						list="#{0:'全选',1:'显示',2:'隐藏'}"
						id="queryAppSubjectType.visibleFlag"
						name="queryAppSubjectType.visibleFlag"
						value="%{queryAppSubjectType.visibleFlag}">
					</s:select>
				</li>
				<li>产品编码：
					<s:textfield id="queryAppSubjectType.productCode"
								name="queryAppSubjectType.productCode" size="30" maxlength="64"
								value="%{queryAppSubjectType.productCode}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>创建日期:
					<s:textfield id="queryAppSubjectType.beginSujectCTime"
							name="queryAppSubjectType.beginSujectCTime" size="10"
							value="%{queryAppSubjectType.beginSujectCTime}"  readonly="true" /></li><li>
					<img
					onclick="WdatePicker({el:'queryAppSubjectType.beginSujectCTime',dateFmt:'yyyy-MM-dd'})"
					src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
					width="16" height="22" />
				</li>
				<li>
					<s:textfield id="queryAppSubjectType.endSujectCTime"
							name="queryAppSubjectType.endSujectCTime" size="10"
							value="%{queryAppSubjectType.endSujectCTime}" readonly="true"/> </li><li>
					<img
					onclick="WdatePicker({el:'queryAppSubjectType.endSujectCTime',dateFmt:'yyyy-MM-dd'})"
					src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
					width="16" height="22" />
				</li>
				<li>
					<input type="button" value="" class="btn_sendData"  onclick="searchAppSubjectType('queryAppSubjectType.beginSujectCTime','queryAppSubjectType.endSujectCTime')" />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
</div>

<div class="databar">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.deleteSubject!=null">
					<li>
						<a href="#" onclick="delSubject()">
							<b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b>
						</a>
					</li>
			</s:if>
			<s:if test="#session.userResMap.toAppSubjectEdit!=null">
					<li>
						<a href="<s:property value="#session.userResMap.toAppSubjectEdit.url"/>" target="mainFrame">
							<b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b>
						</a>
					</li>
			</s:if>
		</ul>
	</div>
	<jsp:include page="/common/prepage.jsp"></jsp:include>
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
			<th scope="col">专题名称</th>
			<th scope="col">应用数</th>
			<th scope="col">产品编码</th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
 			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
			<th width="140" scope="col"><s:text name="sdp.sce.dp.admin.global.label.operate" /></th>
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
				<td>
					<!-- 查看应用专题详情超链接 -->
					<a class="detail_link"
						href="subjectAppinfoRelationAction!toEditSubject.action?appSubjectType.id=<s:property value="id"/>&operate=addApp" >
						<s:if test="%{subjectName.length() > 12}">
		               		<s:property value="%{subjectName.substring(0, 12) + \"..\"}"/>
		               	</s:if>
		               	<s:else>
		               		<s:property value="subjectName"/>
		                </s:else>
					</a>
				</td>
				<td><s:property value="appTotal"/></td>
				<td><s:property value="productCode"/></td>
				<td><s:property value="createUser"/></td>
				<td><s:date name="createDate" format="yyyy-MM-dd"></s:date></td>
				<td><s:date name="updateDate" format="yyyy-MM-dd"></s:date></td>
				<td class="editbar">
					<s:if test="#session.userResMap.toAppSubjectEdit!=null">
						<a href="subjectAppinfoRelationAction!toEditSubject.action?appSubjectType.id=<s:property value="id"/>" target="mainFrame">编辑</a>&nbsp;
						<a href="subjectAppinfoRelationAction!toEditSubject.action?appSubjectType.id=<s:property value="id"/>&operate=updateApp" target="mainFrame">关联应用</a>&nbsp;
					</s:if>

					<s:if test="%{visibleFlag == 1}">
						<a href="javascript:changeVisibleState(2, '<s:property value="id"/>')" 	target="mainFrame" >隐藏</a>
						&nbsp;&nbsp;
					</s:if>
					<s:elseif test="%{visibleFlag == 2}">
						<a href="javascript:changeVisibleState(1, '<s:property value="id"/>')" 	target="mainFrame" >显示</a>
						&nbsp;&nbsp;
					</s:elseif>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<s:if test="#session.userResMap.deleteSubject!=null">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.deleteSubject!=null">
					<li>
						<a href="#" onclick="delSubject()">
							<b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b>
						</a>
					</li>
			</s:if>

			<s:if test="#session.userResMap.toAppSubjectEdit!=null">
					<li>
						<a href="<s:property value="#session.userResMap.toAppSubjectEdit.url"/>" target="mainFrame">
							<b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b>
						</a>
					</li>
			</s:if>
		</ul>
	</div>
	</s:if>
<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
</body>
</html>
