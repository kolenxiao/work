<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.dptype.infodetail" /></title>
<style type="text/css">
	#btn_list{
	margin-top:10px;
}
</style>
<body id="cnt_body" class="data_view">
<s:form id="dpTypeForm" name="dpTypeForm" action="" method="post" cssStyle="margin:0" theme="simple">
	<div id="position">
		<p><s:text name="sdp.sce.dp.admin.dptype.pro" />: <s:text name="sdp.sce.dp.admin.system" />&gt; <s:text name="sdp.sce.dp.admin.dptype.infodetail" /></p>
		<div id="pright"></div>
	</div>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr id="tr_typeFlat">
			<th scope="row"><s:text name="sdp.sce.dp.admin.dptype.Taxonomic.category" />：</th>
			<s:hidden id="typeId" value="%{dpType.id}"></s:hidden>
			<td>${dpType.parentTypeName}</td>
		</tr>
		<tr>
			 <th scope="row">
			<s:text name="sdp.sce.dp.admin.dptype.nametype" />：
			</th>
				<td >
					<s:property value="dpType.typeName" />
					<span class="style-red" id="nameTip">&nbsp;&nbsp;</span>
			</td>
		</tr>
		<tr>
			<th>获取焦点图标</th>
			<td>
			<s:if test="%{dpType.typeImg1 !=null}">
				<a href="${appFilePath.imgPath}${dpType.typeImg1}" target="_blank">
					<img src="${appFilePath.imgPath}${dpType.typeImg1}" height="50px" width="50px"/>
				</a>
			</s:if>
			</td>
		</tr>
		<tr>
			<th>失去焦点图标</th>
			<td>
				<s:if test="%{dpType.typeImg2 !=null}">
					<a href="${appFilePath.imgPath}${dpType.typeImg2}" target="_blank">
						<img src="${appFilePath.imgPath}${dpType.typeImg2}" height="50px" width="50px"/>
					</a>
				</s:if>
			</td>
		</tr>
		<tr>
		<th scope="row"><s:text name="sdp.sce.dp.admin.dptype.descri" />：</th>
		<td >
				<s:hidden id="oldTypeDesc"  name="oldTypeDesc" value="%{dpType.typeDesc}"/>
				<s:property value="dpType.typeDesc" />
			</td>
		</tr>


	</table>
	<div class="btnlistbar" align="left"  style="margin-top:0px;">
			 <input class="inputstyle" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />" onclick="javascript:history.back()"/>
		</div>
</s:form>
</body>
</html>
