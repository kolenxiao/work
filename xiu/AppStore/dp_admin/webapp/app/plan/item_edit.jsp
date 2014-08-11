<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案类项编辑</title>
<script type="text/javascript">
	// 保存
	function saveItem(){
		var itemForm = document.getElementById("itemForm");
		if(validator(itemForm)){
			if( null == $("#id").val() || "" == $("#id").val()){
				itemForm.action="item!save.action";
			}else{
				itemForm.action="item!update.action";
			}
			itemForm.submit();
		}
	}
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt; 类项编辑</p>
  <div id="pright"></div>
</div>
	<div class="view_nav">
		<s:if test="%{queryItem.id != null }">
			编辑
		</s:if>
		<s:else>
			新增
		</s:else>
	</div>
	<s:form id="itemForm" name="itemForm" action="item!save.action" method="post" cssStyle="margin:0" theme="simple" enctype="multipart/form-data">
		<s:token/>
		<s:hidden name="queryItem.id" id="id" value="%{queryItem.id}"></s:hidden>
		<s:hidden name="queryItem.status" id="status" value="%{queryItem.status}"></s:hidden>
		<s:hidden name="queryItem.createTime" id="status" value="%{queryItem.createTime}"></s:hidden>
		<s:hidden name="queryItem.itemType" id="itemType" value="4"></s:hidden>
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row">类项名称</th>
					<td>
						<s:textfield name="queryItem.name" id="name" size="30"
							value="%{queryItem.name}" valid="required|limit"
							maxLength="100" errmsg="类项名称不能为空" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
							<s:property value="message" /> </label>&nbsp;&nbsp;
						</span>
						<span id="errMsg_queryItem.name" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">父类型</th>
					<td><s:select name="queryItem.parentTypeCode" headerKey=""
							headerValue="--请选择--" list="#{'APP_TYPE':'应用','GAME_TYPE':'游戏'}"
							valid="required" errmsg="请选择父类型"></s:select> <strong
						style="color: #F00;">*</strong> <span
						id="errMsg_queryItem.parentTypeCode" style="color: #FF0000"></span>
					</td>
				</tr>	
				<tr>
					<th scope="row">获取焦点图标：</th>
					<td>
						<s:if test="%{queryItem.typeImg1!=null}">
							<a href="${appFilePath.imgPath}${queryItem.typeImg1}" target="_blank">${queryItem.typeImg1}</a>
						</s:if>
						<input type="file" id="uploadFocus"
							name="uploadFocus" size="30"
							valid="filter" allow="jpg,gif,png"
							errmsg=dp_type_icon_allow/>
							<span id="errMsg_upload" style="color:#FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">失去焦点图标：</th>
					<td>
					<s:if test="%{queryItem.typeImg2!=null}">
						<a href="${appFilePath.imgPath}${queryItem.typeImg2}" target="_blank">${queryItem.typeImg2}</a>
					</s:if>
					<s:file id="uploadLoseFocus"
							name="uploadLoseFocus" size="30"
							contentEditable="false" valid="filter" allow="jpg,gif,png"
							errmsg="errMsg_upload"/>
						<span id="dp_type_icon_allow" style="color:#FF0000"></span>
					</td>
				</tr>			
				<tr>
					<th scope="row">类项描述</th>
					<td>
						<s:textarea name="queryItem.description" id="description"
							cols="100" rows="12" value="%{queryItem.description}"  maxLength="256"  errmsg="描述超出限定长度">
						</s:textarea>
						<span id="errMsg_queryItem.description" style="color: #FF0000"></span>
					</td>			
				</tr>
			</table>
		</div>
		<!-- 保存和返回块 -->
		<div class="btnlistbar">
			<input class="inputstyle" type="button" value="保存" onclick="saveItem()" />
			<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back(-1)" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.itemForm);
	    insertLanguageJS();
	</script>
</body>
</html>
