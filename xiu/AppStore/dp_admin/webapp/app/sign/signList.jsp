<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.sign.title.list" /></title>
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
		url = 'appsign!doList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//传参数：表单id（formId）,提交action
	function page(start) {
		url = 'appsign!doList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		//该url路径必须后面接的是： url+'start=' + pageNoVal+'&limit='+pageSizeVal
		url = 'appsign!doList.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);

	}

	//查询
	function search() {

		url = 'appsign!doList.action';
		formSubmit('searchForm', url);
	}

	// 应用签名
	function appsSign() {

		var idArray = new Array();
		var pageSizeVal = document.getElementById("pageSize").value;
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked)
				idArray.push(childCheckboxs[i].id);
		}
		if (idArray.length == 0) {
			dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />', 300,
					150, '<s:text name="sdp.sce.dp.admin.sign.choose.app" />', 0, 0, 1, this);
		} else {
			//把字符串数组转换成字符串
			var idVal = eval(idArray);

			dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />', 300,
					150, '', 0, 0, 2, this);

			$("#dialogFrame").find("td").append($("#signedCertId").clone().css("display","")).append("<font color='red'>*</font>");

			//点击"确定"时执行操作
			$("#dialog_btn_conform")
					.click(
							function() {
								// 校验证书
								var singedCert =  $("#dialogFrame").find("select option:selected").val();
								var errMsg = $("#dialogFrame").find("td").find("font");
								if(""==singedCert || null == singedCert){
									errMsg.html("*<br><s:text name='sdp.sce.dp.admin.certificate.file.null'/>");
									return false;
								}else{
									errMsg.empty();
								}
								// 参数
								var parm = {
										'ids' : idVal,
										signedCertId : singedCert
									};
								$
										.ajax({
											cache : false,
											async : false,
											url : 'appsign!doAppSign.action',
											type : 'POST',
											data : parm,
											dataType : 'json',
											success : function(response) {

												$("#dialog").remove();

												var dataObj = eval(response);

												if (dataObj && dataObj.success) {
													location.href = "appsign!doList.action?&limit="
															+ pageSizeVal;
												} else if (dataObj
														&& dataObj.exception) {
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
	}

	// 应用签名
	function appSign(appid){
		$("#" +appid).attr("checked",true);
		appsSign();
	}
</script>
</head>
<body id="cnt_body">
<s:select  id="signedCertId" name="signedCertId" list="signedPirvateAppCertificates"  headerKey=""  headerValue="%{getText('sdp.sce.dp.admin.certificate.select.signed')}" listKey="id"
            listValue="certificateSrcName" style="display:none;"></s:select>
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.global.label.current.position" />
			<s:text name="sdp.sce.dp.admin.certificate.manager" />
			&gt;
			<s:text name="sdp.sce.dp.admin.sign.title.list" />
		</p>
		<div id="pright"></div>
	</div>
	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post"
				cssStyle="margin:0" theme="simple">

				<ul>
					<li><s:text name="sdp.sce.dp.portal.ap.classfily" />
						<s:select id="queryAppInfo.dpType.id" name="queryAppInfo.dpType.id"
								value="%{queryAppInfo.dpType.id}" headerKey="" headerValue="全部"
								list="dpTypeList" listKey="id" listValue="typeName"
								cssStyle="width:60px;">
						</s:select>
					</li>
					<li><s:text name="sdp.sce.dp.admin.ap.label.appName" /> <s:textfield
							id="queryAppInfo.appName" name="queryAppInfo.appName"
							size="15" maxlength="100" value="%{queryAppInfo.appName}"
							onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
					</li>
					<li><s:text name="sdp.sce.dp.admin.certificate.name" /> <s:textfield
							id="queryAppCert.certificateSrcName"
							name="queryAppCert.certificateSrcName" size="15" maxlength="100"
							value="%{queryAppCert.certificateSrcName}"
							onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
					</li>
					<li><s:text name="sdp.sce.dp.admin.certificate.status" /> <s:select
							list="#{'':getText('sdp.sce.dp.admin.global.title.all'),1000:getText('sdp.sce.dp.admin.certificate.status.normal'),1001:getText('sdp.sce.dp.admin.certificate.status.revoked'),1002:getText('sdp.sce.dp.admin.certificate.status.expired')}"
							id="queryAppCert.revokeFlag" name="queryAppCert.revokeFlag"
							value="%{queryAppCert.revokeFlag}">
						</s:select></li>
					<li><input type="button" value="" class="btn_sendData"
						onclick="search();" />&nbsp;</li>
				</ul>

			</s:form>
		</div>
	</div>

	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.addAppSign != null">
					<li><a href="#" onclick="appsSign()" target="mainFrame"> <b><s:text
									name="sdp.sce.dp.admin.certificate.sign" /> </b> </a></li>
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
							onclick="selectAll(this)" /> </label> </span></th>
				<th width="40px" scope="col"><s:text
						name="sdp.sce.dp.admin.news.newsNo" />
				</th>
				<th width="150px" scope="col"><s:text
						name="sdp.sce.dp.admin.ap.appName" />
				</th>
				<th width="150px" scope="col"><s:text
						name="sdp.sce.dp.portal.ap.classfily" />
				</th>
				<th width="150px" scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.public"></s:text>
				</th>
				<th width="150px" scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.private"></s:text></th>
				<th width="150px" scope="col"><s:text
						name="sdp.sce.dp.admin.certificate.status"></s:text></th>
				<th width="150px" scope="col"><s:text
						name="sdp.sce.dp.admin.news.newsCTime" />
				</th>
				<th width="140px" class="editbar" scope="col"><s:text
						name="sdp.sce.dp.admin.global.label.operate" />
				</th>
			</tr>
			<s:iterator value="page.resultList" status="st">
				<tr>
					<td><span id="sprycheckbox1"> <label> <input
								type="checkbox" name="checkbox"
								id="<s:property value='dpAppInfo.id' />"
								onclick="selectChildAll()" /> </label> </span></td>
					<td width="50">${(page.currentPage-1) * page.pageSize +
						st.index + 1 }</td>
					<td class="p2"><s:property value="dpAppInfo.appName" />
					</td>
					<td><s:property value="dpAppInfo.dpType.typeName" />
					</td>

					<td><s:property value="appCert.certificateSrcName"></s:property>
					</td>
					<td><s:property value="appCert.privateKeySrcName"></s:property>
					</td>
					<td><s:if test="%{appCert.revokeFlag == '1000'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.normal" />
						</s:if> <s:elseif test="%{appCert.revokeFlag == '1001'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.revoked" />
						</s:elseif> <s:elseif test="%{appCert.revokeFlag == '1002'}">
							<s:text name="sdp.sce.dp.admin.certificate.status.expired" />
						</s:elseif>
					</td>
					<td><s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"></s:date>
					</td>
					<td class="editbar"><s:if
							test="#session.userResMap.gotoEditType != null">
							<a href="#"
								onclick="appSign('<s:property value='dpAppInfo.id' />')"
								target="mainFrame"><s:text
									name="sdp.sce.dp.admin.certificate.sign" /> </a>
							&nbsp;&nbsp;
						</s:if> <s:else>
							<font color="#858585"> <s:property
									value="%{getText('sdp.sce.dp.admin.certificate.sign')}" /> </font>
						</s:else></td>
				</tr>
			</s:iterator>
		</table>
	</div>

	<div class="databar">

		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.addAppSign != null">
					<li><a href="#" onclick="appsSign()" target="mainFrame"> <b><s:text
									name="sdp.sce.dp.admin.certificate.sign" /> </b> </a></li>
				</s:if>
			</ul>
		</div>
		<jsp:include page="/common/nextpage.jsp"></jsp:include>

	</div>

</body>
</html>
