<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案条件编辑</title>
<script type="text/javascript">
	// 保存
	function saveCondition(){
		var conditionForm = document.getElementById("conditionForm");
		if(validator(conditionForm)){
			if( null == $("#id").val() || "" == $("#id").val()){
				conditionForm.action="condition!save.action";
			}else{
				conditionForm.action="condition!update.action";
			}
			conditionForm.submit();
		}
	}
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt; 条件编辑</p>
  <div id="pright"></div>
</div>
	<div class="view_nav">
		<s:if test="%{queryCondition.id != null }">
			编辑
		</s:if>
		<s:else>
			新增
		</s:else>
	</div>
	<s:form id="conditionForm" name="conditionForm" action="condition!save.action" method="post" cssStyle="margin:0" theme="simple">
		<s:token/>
		<s:hidden name="queryCondition.id" id="id" value="%{queryCondition.id}"></s:hidden>
		<s:hidden name="queryCondition.status" id="status" value="%{queryCondition.status}"></s:hidden>
		<s:hidden name="queryCondition.createTime" id="id" value="%{queryCondition.createTime}"></s:hidden>
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row">条件名称</th>
					<td>
						<s:textfield name="queryCondition.name" id="name" size="55"
							value="%{queryCondition.name}" valid="required|limit"
							maxLength="100" errmsg="条件名称不能为空" />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
							<s:property value="message" /> </label>&nbsp;&nbsp;
						</span>
						<span id="errMsg_queryCondition.name" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">条件编码</th>
					<td>
						<s:select list="#{'channelId':'渠道号 (channelId) ','cityCode':'区域码 (cityCode) '}"  name="queryCondition.code" id="queryCondition.code" value="%{queryCondition.code}">
						</s:select>
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message">
							<s:property value="message" /> </label>&nbsp;&nbsp;
						</span>
						<span id="errMsg_queryCondition.code" style="color: #FF0000"></span>
					</td>
				</tr>				
				<tr>
					<th scope="row">条件值</th>
					<td>
						<s:textfield name="queryCondition.value" id="value"
							value="%{queryCondition.value}" valid="limit" maxLength="500"
							errmsg="条件值超出限定长度" size="55" />
							<span id="errMsg_queryCondition.value" style="color: #FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">条件描述</th>
					<td>
						<s:textarea name="queryCondition.description" id="description"
							cols="100" rows="12" value="%{queryCondition.description}"  maxLength="256"  errmsg="描述超出限定长度">
						</s:textarea>
						<span id="errMsg_queryCondition.description" style="color: #FF0000"></span>
					</td>			
				</tr>
			</table>
		</div>
		<!-- 保存和返回块 -->
		<div class="btnlistbar">
			<input class="inputstyle" type="button" value="保存" onclick="saveCondition()" />
			<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back(-1)" />
		</div>
	</s:form>
	<script type="text/javascript">
		initValid(document.conditionForm);
	    insertLanguageJS();
	</script>
</body>
</html>
