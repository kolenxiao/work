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

<script src="${pageContext.request.contextPath}/js/rrule/underscore.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/rrule/rrule.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/rrule/nlp.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/rrule/generate_rule.js" type="text/javascript"></script>


<script type="text/javascript">

	//保存方案基本信息
	function doSavePlan() { 
		var form = document.getElementById("planForm");
	    if (validator(form)) {
	    	if($("#startTime").val() > $("#endTime").val()){
		   		$("#errMsg_endTime").text("失效时间必须大于生效时间");
		   		return;
		    }
			form.submit(); 
	    }
	}

</script>
</head>
<body id="cnt_body">
	<s:if test="%{plan.id != null }">
		<s:set name="title" value="'编辑方案'"/>
	</s:if>
	<s:else>
		<s:set name="title" value="'创建方案'" />
	</s:else>
    <div id="position">
        <p> <s:text name='sdp.sce.dp.admin.global.label.current.position' />方案管理 &gt; <s:property value="#title"/></p>
    </div>
    <s:form id="planForm" name="planForm" action="planManage!createPlan.action" method="post" cssStyle="margin:0" theme="simple">
        <s:token/>
        <s:hidden name="plan.id" id="id" value="%{plan.id}"/>
         <s:hidden name="plan.status" id="status" value="%{plan.status}"/>
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
                	<s:textfield id="startTime" name="plan.startTime" size="20" readonly="true" valid="required" errmsg="时间不能为空" >
                		<s:param name="value" ><s:date name="plan.startTime" format="yyyy-MM-dd" /></s:param>
                	</s:textfield>
					 <img onclick="WdatePicker({el:'startTime',dateFmt:'yyyy-MM-dd'})"
						src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
						width="16" height="22" /><strong style="color: #F00;">*</strong>
						<span id="errMsg_plan.startTime" style="color: #F00;"></span>
                </td>
            </tr>
            <tr>
                <th scope="row">失效时间</th>
                <td>
                	<s:textfield id="endTime" name="plan.endTime" size="20" readonly="true" valid="required" errmsg="时间不能为空" >
                		<s:param name="value" ><s:date name="plan.endTime" format="yyyy-MM-dd" /></s:param>
                	</s:textfield>
					 <img onclick="WdatePicker({el:'endTime',dateFmt:'yyyy-MM-dd'})"
						src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
						width="16" height="22" /><strong style="color: #F00;">*</strong>
						<span id="errMsg_plan.endTime" style="color: #F00;"></span>
                </td>
            </tr>
	        <tr>
			    <th scope="row">方案简介</th>
				<td>
				    <s:textarea name="plan.description" cols="50" rows="5" id="description" style="width:350px;"
					    valid="limit" min="0" max="255" errmsg="plan_desc_limit" />
					<br/>
					<span id="errMsg_plan.description" style="color: #FF0000"></span>
				</td>
			</tr> 
			
			<!-- 
			<section id="output">
			<tr>
				<th scope="row">规则串：</th>

				<td><s:textfield name="regulation" id="regulation" maxlength="255" size="150" valid="required" errmsg="validte_required" readonly="true" />
				<strong style="color: #F00;">*</strong> <br/>
					<span id="errMsg_regulation" style="color: #FF0000"></span></td>
				
			</tr>
			 -->
			<!-- 
			<tr>
				<th><code>rule.all()</code></th>
				<td>
					<table id="dates"></table>
				</td>
			</tr>
			 -->
			</section>
	    </table>
	</div>
	
	<!-- 保存和返回块 -->
	<div class="btnlistbar">
	    <input class="inputstyle" type="button" value="保存" onclick="doSavePlan();" />
	    <input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back(-1)" />
	</div>
</s:form>






<!-- 
	<section id="options-input">
	<s:form id="rruleForm" name="rruleForm" method="post" cssStyle="margin:0" theme="simple">
	<input type="hidden" name="wkst" value="0" />
	<input type="hidden" name="interval" value="1" />
	<input type="hidden" value="100" name="count" />
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th>
						<div class="option-name">
							<code>周期</code>
						</div>
					</th>
					<td><label><input name="freq" type="radio" value="2" checked />周</label>
						<label><input name="freq" type="radio" value="3" />日</label>
					</td>
				</tr>
				<tr>
					<th>起始时间</th>
					<td><s:textfield id="dtstart" name="dtstart" size="20" readonly="true" valid="required" errmsg="validte_required" >
	                		<s:param name="value" ><s:date name="plan.startTime" format="yyyy-MM-dd" /></s:param>
	                	</s:textfield>
						<img onclick="WdatePicker({el:'dtstart',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
						</td>
				</tr>
				<tr>
					<th>截止时间</th>
					<td><s:textfield id="until" name="until" size="20" readonly="true" valid="required" errmsg="validte_required" >
	                		<s:param name="value" ><s:date name="plan.endTime" format="yyyy-MM-dd" /></s:param>
	                	</s:textfield>
						<img onclick="WdatePicker({el:'until',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
						</td>
				</tr>

				<tr>
					<th>次数</th>
					<td><input type="number" maxlength=3 value="30" name="count" /></td>
				</tr>

				<tr>
					<th>WeekDay</th>
					<td><label><input type="checkbox" name="byweekday"
							value="0">周一</label> <label><input type="checkbox"
							name="byweekday" value="1">周二</label> <label><input
							type="checkbox" name="byweekday" value="2">周三</label> <label><input
							type="checkbox" name="byweekday" value="3">周四</label> <label><input
							type="checkbox" name="byweekday" value="4">周五</label> <label><input
							type="checkbox" name="byweekday" value="5">周六</label> <label><input
							type="checkbox" name="byweekday" value="6">周日</label></td>
				</tr>

				<tr>
					<th>bymonth</th>
					<td><label><input name="bymonth" type="checkbox"
							value="1" /> Jan</label> <label><input name="bymonth"
							type="checkbox" value="2" /> Feb</label> <label><input
							name="bymonth" type="checkbox" value="3" /> Mar</label> <label><input
							name="bymonth" type="checkbox" value="4" /> Apr</label> <label><input
							name="bymonth" type="checkbox" value="5" /> May</label> <label><input
							name="bymonth" type="checkbox" value="6" /> Jun</label> <label><input
							name="bymonth" type="checkbox" value="7" /> Jul</label> <label><input
							name="bymonth" type="checkbox" value="8" /> Aug</label> <label><input
							name="bymonth" type="checkbox" value="9" /> Sep</label> <label><input
							name="bymonth" type="checkbox" value="10" /> Oct</label> <label><input
							name="bymonth" type="checkbox" value="11" /> Nov</label> <label><input
							name="bymonth" type="checkbox" value="12" /> Dec</label></td>
				</tr>

				<tr>
					<th>小时</th>
					<td><input name="byhour"  value="0"></td>
				</tr>

				<tr>
					<th>分钟</th>
					<td><input name="byminute"  value="0"></td>
				</tr>
				<tr>
					<th>秒</th>
					<td><input name="bysecond" value="0"></td>
				</tr>
			</table>
		</div>
	</s:form>
	</section>
 -->


	<script type="text/javascript">
		initValid(document.planForm);
		insertLanguageJS();
	</script>
</body>
</html>
