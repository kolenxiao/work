<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.certificate.list" /></title>
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
		url = 'certificate!doList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//传参数：表单id（formId）,提交action
	function page(start) {
		url = 'certificate!doList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		//该url路径必须后面接的是： url+'start=' + pageNoVal+'&limit='+pageSizeVal
		url = 'certificate!doList.action?limit=' + pageSizeVal;
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
				location.href = action + '?ids=' + idVal + '&limit=' + pageSize + '&publicPrivateRevokeFlag=1';
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

		url = 'certificate!doList.action';
		formSubmit('searchForm', url);
	}

	// 删除
	function delCertificate(certId,action){
		$("#" +certId).attr("checked",true);
		del(action);
	}

	// 设置证书为默认证书
	function setDefaultCertificate(action){
		var pageSizeVal = document.getElementById("pageSize").value;
		var currentPage = document.getElementById("currentPage").value;
			//弹出确认删除的提示窗口
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform"/>',
					300,
					150,
					'确定设置为默认签名证书?',
					0, 0, 2, this);
			//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行设置操作
			$("#dialog_btn_conform").click(function() {
				urlStr = action + '&limit=' + pageSizeVal;
				$.ajax({
					cache : false,
					async : false,
					url : urlStr,
					type : 'POST',
					data : {},
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
							location.href = "certificate!doList.action?&start="+currentPage+"&limit="
									+ pageSizeVal;
						}else{
							dialogList(
									'<s:text name="sdp.sce.dp.admin.global.inform" />',
									300, 150,
									dataObj.exception,
									0, 0, 1, this);
						}
					}
				});
			});
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
			<s:text name="sdp.sce.dp.admin.certificate.list" />
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
				<s:if test="#session.userResMap.addOrEdit != null">
					<li><a
						href="<s:property value="#session.userResMap.addOrEdit.url"/>"
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
				<th width="30px" scope="col"><s:text
						name="sdp.sce.dp.admin.news.newsNo" /></th>
				<th width="150px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.public" /></th>
				<th width="100px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.private" /></th>
				<th width="60px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.status"></s:text>
				</th>
				<th width="100px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.serial.number" /></th>
				<th width="120px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.start.time" /></th>
				<th width="120px"  scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.end.time" /></th>
				<th width="150px" class="editbar" scope="col"><s:text
						name="sdp.sce.dp.admin.global.label.operate" /></th>
			</tr>
			<s:iterator value="page.resultList" status="st">
				<tr>
					<td width="20px"><span id="sprycheckbox1"> <label> <input
								type="checkbox" name="checkbox" id="<s:property value='id' />"
								onclick="selectChildAll()" /> </label> </span>
					</td>
					<td width="30px">${(page.currentPage-1) * page.pageSize +
						st.index + 1 }</td>
					<td width="100px"><s:property value="certificateSrcName" /></td>
					<td width="100px"><s:property value="privateKeySrcName" /></td>
					<td width="60px"><s:if test="%{revokeFlag == '1000'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.normal" />
						</s:if> <s:elseif test="%{revokeFlag == '1001'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.revoked" />
						</s:elseif> <s:elseif test="%{revokeFlag == '1002'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.expired" />
						</s:elseif></td>

					<td width="150px"><s:property value="serialNumber"></s:property>
					</td>
					<td width="120px"><s:date name="notBefore" format="yyyy-MM-dd hh:mm:ss"></s:date></td>
					<td width="120px"><s:date name="notAfter" format="yyyy-MM-dd hh:mm:ss"></s:date></td>
					<td class="editbar">
						<s:if
							test="#session.userResMap.addOrEdit != null">
							 <a href="certificate!doAddOrModify.action?appCertificate.id=<s:property value='id' />"
								target="mainFrame">
								<s:text
									name="sdp.sce.dp.admin.global.btn.edit" />
							</a>
						</s:if>
						<s:else>
							<s:text name="sdp.sce.dp.admin.global.btn.edit" />
						</s:else>
						<s:if test="#session.userResMap.deleteCertificate != null">
							<a href="#" onclick="delCertificate('<s:property value='id' />','<s:property value="#session.userResMap.deleteCertificate.url"/>')"> <s:text
									name="sdp.sce.dp.admin.global.btn.del" /></a>
						</s:if>
						<s:else>
							<s:text name="sdp.sce.dp.admin.global.btn.del" />
						</s:else>
						<s:if test="#session.userResMap.addOrEdit != null">
							<s:if test="%{isDefault == 0}">
								<a href="#" onclick="setDefaultCertificate('certificate!setDefault.action?ids=<s:property value='id' />')">
									<s:text name="sdp.sce.dp.certificate.setDefault" />
								</a>
							</s:if>
						</s:if>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div class="databar">

		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.addOrEdit != null">
					<li><a
						href="<s:property value="#session.userResMap.addOrEdit.url"/>"
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
