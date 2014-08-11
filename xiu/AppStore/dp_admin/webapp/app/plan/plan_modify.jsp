<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建方案</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/uploadify/uploadify.css">
<script src="${pageContext.request.contextPath}/uploadify/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/upload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/common.js" type="text/javascript"></script>

<script type="text/javascript">

	$(document).ready(function() {
		errorMsg();
	})

	//保存方案基本信息
	function doModify() { 
		var form = document.getElementById("planForm");
	    if (validator(form)) {
	    	if($("#startTime").val() > $("#endTime").val()){
		   		$("#errMsg_endTime").text("失效时间必须大于生效时间");
		   		return;
		    }
			form.submit(); 
	    }
	}

	
	function errorMsg() {
		var msg = '<s:property value="msg"/>';
		var exception_msg = '<s:property value="exception_msg"/>';
		if(msg != null && msg.trim().length > 0) {
			dialogList('提示信息',300,150,msg,0,0,1,this);	
			$("#dialog_btn_conform").click(function(){
				parent.window.Wclose();
			});
		}else if(exception_msg != null && exception_msg.trim().length > 0){
		    dialogList('提示信息',300,150,exception_msg,0,0,1,this);	
			$("#dialog_btn_conform").click(function(){
				parent.window.Wclose();
			});
		}
	}
</script>
</head>
<body id="cnt_body">
    <div id="position">
        <p> <s:text name='sdp.sce.dp.admin.global.label.current.position' />方案管理 &gt; <s:property value="#title"/></p>
    </div>
    <div class="view_nav">
        <s:property value="#module"/>
    </div>
    <s:form id="planForm" name="planForm" action="planManage!modifyPlan.action" method="post" cssStyle="margin:0" theme="simple">
        <s:token/>
        <s:hidden name="plan.id" id="id" value="%{plan.id}"/>
        <s:hidden name="plan.status" value="%{plan.status}"/>
        <s:hidden name="plan.sortNum" value="%{plan.sortNum}"/>
        <s:hidden name="plan.defaulted" value="%{plan.defaulted}"/>
        <div class="data_view">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th scope="row">方案名称</th>
                <td>
                	<s:textfield name="plan.name" id="name" maxlength="50" size="50" valid="required" errmsg="方案名称不能为空" />
                    <strong style="color: #F00;">*</strong>
                    <span id="errMsg_plan.name" style="color: #F00;"></span>
                </td>
            </tr>
            <tr>
                <th scope="row">生效时间</th>
                <td>
                	<input name="plan.startTime" id="startTime" value='<s:date name="plan.startTime" format="yyyy-MM-dd"/>' readonly="true" valid="required" errmsg="时间不能为空"/>
							 <img onclick="WdatePicker({el:'startTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" /><strong style="color: #F00;">*</strong>
							<span id="errMsg_plan.startTime" style="color: #F00;"></span>
                </td>
            </tr>
            <tr>
                <th scope="row">失效时间</th>
                <td>
                	<input name="plan.endTime" id="endTime" value='<s:date name="plan.endTime" format="yyyy-MM-dd"/>' readonly="true" valid="required" errmsg="时间不能为空"/>
							 <img onclick="WdatePicker({el:'endTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" /><strong style="color: #F00;">*</strong>
							<span id="errMsg_plan.endTime" style="color: #F00;"></span>
                </td>
            </tr>
	        <tr>
			    <th scope="row">方案简介</th>
				<td>
				    <s:textarea name="plan.description" cols="50" rows="8" id="description" style="width:350px;"
					    valid="limit" min="0" max="255" errmsg="plan_desc_limit" />
					<br/>
					<span id="errMsg_plan.description" style="color: #FF0000"></span>
				</td>
			</tr> 
	    </table>
	</div>
	
	<!-- 保存和返回块 -->
	<div class="btnlistbar">
	    <input class="inputstyle" type="button" value="保存" onclick="doModify();" />
	    <input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back(-1)" />
	</div>
</s:form>
<script type="text/javascript">
	initValid(document.planForm);
    insertLanguageJS();
</script>
</body>
</html>
