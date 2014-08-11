<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:if test="null == appCertificate">
		<s:text name="sdp.sce.dp.admin.certificate.title.add" />
	</s:if> <s:else>
		<s:text name="sdp.sce.dp.admin.certificate.title.modify" />
	</s:else></title>
<script type="text/javascript">
	//自定义输出错误信息
	FormValid.showError = function(errMsg, errName, formName) {
		if (formName == 'certificateForm') {
			for (key in FormValid.allName) {
				document.getElementById('errMsg_' + FormValid.allName[key]).innerHTML = '';
			}
			for (key in errMsg) {
				document.getElementById('errMsg_' + errName[key]).innerHTML = errMsg[key];
			}
		}
	};

	// 验证表单，提交form
	function save(form) {

		if (isNull() && validator(form)) {

			var url;
			<s:if test="null == appCertificate">
			url = "certificate!doAdd.action";
			</s:if>
			<s:else>
			url = "certificate!doModify.action";
			</s:else>

			form.action = url;
			form.submit();
		}

	}

	// 验证签名证书、私钥、吊销列表是否为空
	function isNull() {

		var flag = true;

		var revoke = $("#revoke").val();

		// 验证吊销列表

		if (null == revoke || '' == revoke) {
			$("#errMsg_revoke").html(
					"<s:text name="sdp.sce.dp.admin.certificate.file.null" />");
			flag = false;
		}

		return flag;
	}



	//取消操作
	function doBack() {
		location.href = "certificate!doRevokeList.action";
	}

</script>

<style type="text/css">
#btn_list {
	margin-top: 10px;
}
</style>
<body id="cnt_body" class="data_view">
	<s:form id="certificateForm" name="certificateForm" action=""
		method="post" cssStyle="margin:0" theme="simple"
		enctype="multipart/form-data">

		<s:hidden id="appCertificate.id" name="appCertificate.id"
			value="%{appCertificate.id}"></s:hidden>
		<div id="position">
			<p>
				<s:text name="sdp.sce.dp.admin.dptype.pro" />
				:
				<s:text name="sdp.sce.dp.admin.certificate.manager" />
				&gt;
				<s:if test="null == appCertificate">
					<s:text name="sdp.sce.dp.admin.certificate.revoke.add" />
				</s:if>
				<s:else>
					<s:text name="sdp.sce.dp.admin.certificate.revoke.modify" />
				</s:else>
			</p>
			<div id="pright"></div>
		</div>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th scope="row"><s:text
						name="sdp.sce.dp.admin.certificate.revoke.list"></s:text>：</th>
				<td><s:if test="%{null != revokeFileName}">
						<s:property value="revokeFileName" />
						<br />
					</s:if> <s:file type="file" id="revoke" name="revoke" size="30"
						valid="filter" allow="crl" errmsg="certificate_revoke_file_allow"></s:file>
					<strong style="color: #F00;">*</strong><span id="errMsg_revoke"
					style="color: #FF0000"></span></td>
			</tr>

			<tr>
				<th scope="row"><s:text
						name="sdp.sce.dp.admin.certificate.desc" />：</th>
				<td><s:textarea id="appCertificate.certificateDesc"
						name="appCertificate.certificateDesc" cols="80" rows="8"
						valid="limit" min="0" max="500" errmsg="certificateDesc_leng" />
					<span id="errMsg_appCertificate.certificateDesc"
					style="color: #FF0000"></span></td>
			</tr>
		</table>
		<div class="btnlistbar" align="left" style="margin-top: 0px;">
			<s:if test="null == appCertificate">
				<input class="inputstyle" type="button"
					value="<s:text name="sdp.sce.dp.admin.global.btn.add" />"
					onclick="save(document.certificateForm);" />
			</s:if>
			<s:else>
				<input class="inputstyle" type="button"
					value="<s:text name="sdp.sce.dp.admin.global.btn.modify" />"
					onclick="save(document.certificateForm);" />
			</s:else>

			<input class="inputstyle" type="button"
				value="<s:text name="sdp.sce.dp.admin.global.btn.cancel" />"
				onclick="doBack()" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.certificateForm);
		insertLanguageJS();
	</script>

</body>
</html>
