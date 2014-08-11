<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改上架应用数据</title>

<script type="text/javascript">

$(document).ready(function(){
	errorMsg();
	
	//选择方案时
	$("#planId").change(function(){
		$("#tb_appRelate").html("");
		$("#relateType").val("");
		
		getRelateList();
	})
	
	$("#relateType").change(function(){
		$("#tb_appRelate").html("");
		var relateType = $(this).val();
		if("" == relateType){
			return;
		}
		
		getRelateList();
	})
	
	//点修改按钮
	$("#bt_modify").click(function(){
		var appId = $("#appId").val();
		if("" == appId){
			alert("appId不能为空");
			return;
		}
		var param = {'appInfo.id':$("#appId").val(), 
				     'appInfo.handDownCount':$("#handDownCount").val(),
				     'appInfo.handScoreCount':$("#handScoreCount").val(),
				     'appInfo.handAvgScore':$("#handAvgScore").val(),
				     'planId':$("#planId").val(),
				     'relateType':$("#relateType").val(),
				     'relateAppFilePackage':
				    	 $("input[name=relateAppFilePackage]").map(function(){
							return $(this).val();
						 }).get()
					};
		
		if(confirm("确认修改？")){
			$.post("dpAppInfo!modifyAppOnline.action",param,function(data){
				if("success" == data){
					$("#infoMessage").text("修改成功！");
				}else{
					alert(data);
				}
			});
		}
	})
	
	function getRelateList(){
		var planId = $("#planId").val();
		if("" == planId){
			return;
		}
		var url = "dpAppInfo!getAppRelateByPlanIdAndPkgName.action?planId=" + planId;
		url += "&appFilePackage=" + $("#appFilePackage").val();
		url += "&relateType=" + $("#relateType").val();
		$.getJSON(url, function(data){
			if(true == data.success){
				$("#relateType").val(data.relateType);
				
				$.each(data.dpApplist, function(i,e){
					var $td1 = $("<td></td>").append(e.appName).append("<input name=relateAppFilePackage type=hidden value='"+e.appFilePackage+"' />");
					var $tr = $("<tr></tr>").append($td1);
					if("1" == $("#relateType").val()){
						//人工
						var $td2 = $("<td></td>").append("<input type='button' value='替换' onclick=doSelect(this) />  ");
						$tr.append($td2);
					}
					$("#tb_appRelate").append($tr);
				})
				
			}else{
				alert("操作失败");
			}
		});
	}

});

</script>

<script type="text/javascript">

	
	function doSelect(obj){
		var planId = $("#planId").val();
		var sourceAppPkgName = $("#appFilePackage").val();
		var action = "dpAppInfo!getAppListForReplace.action?planId="+planId;
		action += "&sourceAppPkgName="+sourceAppPkgName;
		var jsonObj = {
			scrollType : 'yes'
		};
		
		var returnValue = window.showModalDialog(action, '', 1000, 540, 1, jsonObj);
		if(undefined != returnValue){
			var appName = returnValue.split('||')[0];
			var appPackage = returnValue.split('||')[1];

			var $td1 = $("<td></td>").append(appName).append("<input name=relateAppFilePackage type=hidden value='"+appPackage+"' />");
			var $td2 = $("<td></td>").append("<input type='button' value='替换' onclick=doSelect(this) />  ");
			
			var $tr = $(obj).parent().parent();
			$tr.html("");
			$tr.append($td1).append($td2);
		}

		//openshow(action, '', 1000, 540, 1, jsonObj);
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
	

	//自定义输出错误信息
	FormValid.showError = function(errMsg, errName, formName) {
		if (formName == 'appForm') {
			for (key in FormValid.allName) {
				document.getElementById('errMsg_' + FormValid.allName[key]).innerHTML = '';
			}
			for (key in errMsg) {
				document.getElementById('errMsg_' + errName[key]).innerHTML = errMsg[key];
			}
		}
	};

</script>
</head>
<body id="cnt_body">
	<s:set name="title" value="'修改上架应用数据'"/>
	<s:set name="module" value="'修改上架应用数据'"/>

    <div id="position">
        <p> <s:text name='sdp.sce.dp.admin.global.label.current.position' />我的应用 &gt; <s:property value="#title"/></p>
    </div>
    
   <div id="infoMessage" style="color:red">
		<s:iterator value="infoMessages">
			<s:property value="value"/><br />
		</s:iterator>
	</div>
		
    <div class="view_nav">
        <s:property value="#module"/>
    </div>
    <s:form id="appForm" name="appForm" method="post" cssStyle="margin:0" theme="simple">
        <s:token/>
        <s:hidden name="appInfo.id" id="appId" value="%{appInfo.id}"/>
        <s:hidden id="appFilePackage" value="%{appInfo.appFilePackage}"/>
        

	
        <div class="data_view">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th scope="row">应用名称</th>
                <td><s:property value="appInfo.appName"/></td>
            </tr>
            <tr>
		      <th scope="row">人工增加下载次数</th>
			  <td>
			      <s:textfield type="text" name="appInfo.handDownCount" id="handDownCount" valid="required|isNaturalInt" 
				      errmsg="validte_required|only_natural_int" maxLength="9" size="15" /><strong style="color: #F00;">*</strong>
				  <span id="errMsg_appInfo.handDownCount" style="margin-left:20px;color: #FF0000"></span>
			  </td>
		    </tr>
		    <tr>
		      <th scope="row">人工增加评分人数</th>
			  <td>
			      <s:textfield type="text" name="appInfo.handScoreCount" id="handScoreCount" valid="required|isNaturalInt" 
				      errmsg="validte_required|only_natural_int" maxLength="9" size="15" /><strong style="color: #F00;">*</strong>
				  <span id="errMsg_appInfo.handScoreCount" style="margin-left:20px;color: #FF0000"></span>
			  </td>
		    </tr>
		    <tr>
		      <th scope="row">人工平均评分</th>
			  <td>
				  <s:textfield type="text" name="appInfo.handAvgScore" id="handAvgScore" valid="required|range" min="0" max="5"
				      errmsg="validte_required|dp_handAvgScore_check" maxLength="4" size="15" /><strong style="color: #F00;">*</strong>
				  <span id="errMsg_appInfo.handAvgScore" style="margin-left:20px;color: #FF0000"></span>
			  </td>
		    </tr>
		    <tr>
		      <th scope="row">猜你喜欢</th>
			  <td>
			  	<s:select id="planId" list="result.planList" listKey="id" listValue="name" headerKey="" headerValue="--请选择方案--" />
			  	<s:select id="relateType" list="#{'0':'自动推荐','1':'人工指定'}"  headerKey="" headerValue="--请选择推荐类型--"  />
			  	<br/>
			  	<br/>
			  	<table id="tb_appRelate">
			  	</table>
			  </td>
		    </tr>
	    </table>
	</div>
	
	<!-- 保存和返回块 -->
	<div class="btnlistbar">
	    <input class="inputstyle" type="button" id="bt_modify" value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>" />
		<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
				onclick="javascript:history.back(-1)" />
	</div>
</s:form>
<script type="text/javascript">
    initValid(document.appForm);
    insertLanguageJS();
</script>
</body>
</html>
