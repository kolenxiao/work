<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.certificate.revoke.list" /></title>
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
	var sprycheckbox1 = new Spry.Widget.ValidationCheckbox("sprycheckbox1", {
		isRequired : false
	});
</script>
<script type="text/javascript">
	$(function() {
		switchTableRow(".data_list", "tr_even", "tr_hover");
	});
	//分页跳转函数
	function jumpPage(no) {
		url = 'certificate!doRevokeList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//传参数：表单id（formId）,提交action
	function page(start) {
		url = 'certificate!doRevokeList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		//该url路径必须后面接的是： url+'start=' + pageNoVal+'&limit='+pageSizeVal
		url = 'certificate!doRevokeList.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);

	}

	//删除
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
				location.href = action + '?ids=' + idVal + '&limit=' + pageSize +'&publicPrivateRevokeFlag=0';
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

	//查询
	function search() {

		url = 'certificate!doRevokeList.action';
		formSubmit('searchForm', url);
	}

	// 删除
	function delCertificate(certId,action){
		$("#" +certId).attr("checked",true);
		del(action);
	}
</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.dptype.pro" />
			:
			<s:text name="sdp.sce.dp.admin.certificate.manager" />
			&gt;
			<s:text name="sdp.sce.dp.admin.certificate.revoke.list" />
		</p>
		<div id="pright"></div>
	</div>
	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" method="post"
				cssStyle="margin:0" theme="simple">				
				<ul>
					<li><s:text name="sdp.sce.dp.admin.certificate.name" /> <s:textfield
							id="queryAppCertificate.certificateSrcName"
							name="queryAppCertificate.certificateSrcName" size="15"
							maxlength="128" value="%{queryAppCertificate.certificateSrcName}"
							onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
					</li>					
					<li><s:text name="sdp.sce.dp.admin.certificate.status" /> <s:select
							list="#{'':getText('sdp.sce.dp.admin.global.title.all'),1000:getText('sdp.sce.dp.admin.certificate.status.normal'),1001:getText('sdp.sce.dp.admin.certificate.status.revoked'),1002:getText('sdp.sce.dp.admin.certificate.status.expired')}"
							id="queryAppCertificate.revokeFlag"
							name="queryAppCertificate.revokeFlag"
							value="%{queryAppCertificate.revokeFlag}">
						</s:select>
					</li>
					<li><input type="button" value="" class="btn_sendData"
						onclick="search();" />&nbsp;</li>
				</ul>

			</s:form>
		</div>
	</div>

	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.addOrEditRevoke != null">
					<li><a
						href="<s:property value="#session.userResMap.addOrEditRevoke.url"/>"
						target="mainFrame"> <b><s:text
									name="sdp.sce.dp.admin.global.btn.add" /> </b> </a>
					</li>
				</s:if>
				<s:if test="#session.userResMap.deleteCertificate != null">
					<li><a href="#"
						onclick="del('<s:property value="#session.userResMap.deleteCertificate.url"/>')">
							<b><s:text name="sdp.sce.dp.admin.global.btn.del" /> </b> </a>
					</li>
				</s:if>
			</ul>
		</div>
		<jsp:include page="/common/prepage.jsp"></jsp:include>

	</div>

	<div class="data_list">

		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th width="20px" scope="col"><span id="sprycheckbox1"> <label>
							<input type="checkbox" name="checkbox1" id="checkboxAll"
							onclick="selectAll(this)" /> </label> </span>
				</th>
				<th width="40px" scope="col"><s:text
						name="sdp.sce.dp.admin.news.newsNo" /></th>
				<th width="160px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.revoke.list" /></th>				
				<th width="100px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.status"></s:text>
				</th>
				<th width="150px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.serial.number" /></th>
				<th width="120px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.this.update.date" /></th>
				<th width="120px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.next.update.date" /></th>
				<th width="100px" class="editbar" scope="col"><s:text
						name="sdp.sce.dp.admin.global.label.operate" /></th>
			</tr>
			<s:iterator value="page.resultList" status="st">
				<tr>
					<td><span id="sprycheckbox1"> <label> <input
								type="checkbox" name="checkbox" id="<s:property value='id' />"
								onclick="selectChildAll()" /> </label> </span>
					</td>
					<td width="50">${(page.currentPage-1) * page.pageSize +
						st.index + 1 }</td>
					<td class="p2"><s:property value="revokeSrcName" /></td>					
					<td><s:if test="%{revokeFlag == '1000'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.normal" />
						</s:if> <s:elseif test="%{revokeFlag == '1001'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.revoked" />
						</s:elseif> <s:elseif test="%{revokeFlag == '1002'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.expired" />
						</s:elseif></td>

					<td><s:property value="serialNumber"></s:property>
					</td>
					<td><s:date name="thisUpdate" format="yyyy-MM-dd hh:mm:ss"></s:date></td>
					<td><s:date name="nextUpdate" format="yyyy-MM-dd hh:mm:ss"></s:date></td>
					<td class="editbar"><s:if
							test="#session.userResMap.addOrEdit != null">
							 <a
								href="certificate!doAddOrModifyRevoke.action?appCertificate.id=<s:property value='id' />"
								target="mainFrame"> <s:text
									name="sdp.sce.dp.admin.global.btn.edit" /></a>
						</s:if><s:else>
							<s:text name="sdp.sce.dp.admin.global.btn.edit" />
						</s:else> <s:if test="#session.userResMap.deleteCertificate != null">
							<a href="#" onclick="delCertificate('<s:property value='id' />','<s:property value="#session.userResMap.deleteCertificate.url"/>')"> <s:text
									name="sdp.sce.dp.admin.global.btn.del" /></a>
						</s:if> <s:else>
							<s:text name="sdp.sce.dp.admin.global.btn.del" />
						</s:else></td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div class="databar">

		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.addOrEditRevoke != null">
					<li><a
						href="<s:property value="#session.userResMap.addOrEditRevoke.url"/>"
						target="mainFrame"> <b><s:text
									name="sdp.sce.dp.admin.global.btn.add" /> </b> </a>
					</li>
				</s:if>
				<s:if test="#session.userResMap.deleteCertificate != null">
					<li><a href="#"
						onclick="del('<s:property value="#session.userResMap.deleteCertificate.url"/>')">
							<b><s:text name="sdp.sce.dp.admin.global.btn.del" /> </b> </a>
					</li>
				</s:if>
			</ul>
		</div>
		<jsp:include page="/common/nextpage.jsp"></jsp:include>

	</div>

</body>
</html>
