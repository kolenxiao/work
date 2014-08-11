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
	    //类型change
		$("#type").change(function(){
			if($(this).val()==1){//文字
				$("#div_value_text").show();
				$("#value").attr("disabled", false);
				
				$("#div_value_photo").hide();
				$("#uploadPhoto").attr("disabled", true);
			}else{//图片
				$("#div_value_text").hide();
				$("#value").attr("disabled", true);
				
				$("#div_value_photo").show();
				$("#uploadPhoto").attr("disabled", false);
			}
		}).change();
	    
	    
	})

	//保存方案基本信息
	function doSave() { 
		var form = document.getElementById("mainForm");
	    if (validator(form)) {
	    	if($("#id").val().length == 0 && $("#type").val()==2 && $("#upload").val() == ""){  
	            alert("请上传图片!");  
	            return false;  
	        }  
			form.submit(); 
	    }
	}

</script>
</head>
<body id="cnt_body">
	<s:if test="%{systemParam.id != null }">
		<s:set name="title" value="'编辑参数'"/>
	</s:if>
	<s:else>
		<s:set name="title" value="'新增参数'" />
	</s:else>
    <div id="position">
        <p> <s:text name='sdp.sce.dp.admin.global.label.current.position' />系统管理 &gt; <s:property value="#title"/></p>
    </div>
    
    <div class="center_right_conent_alert">
		<span id="fileErr" style="padding: 0; color: red">
			<s:fielderror/>
		</span>
	</div>
	
    <s:form id="mainForm" name="mainForm" action="systemParam!createOrModify.action" method="post" cssStyle="margin:0" theme="simple"  enctype="multipart/form-data">
        <s:token/>
        <s:hidden name="systemParam.id" id="id" value="%{systemParam.id}"/>
        <div class="data_view">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
        	<tr>
                <th scope="row">参数名称</th>
                <td>
                	<s:if test="%{systemParam.id == null }">
		                <s:textfield name="systemParam.name" id="code" maxlength="50" size="50" valid="required" errmsg="参数名称不能为空" />
						<strong style="color: #F00;">*</strong>
		                <span id="errMsg_systemParam.name" style="color: #F00;"></span>
					</s:if>
					<s:else>
						<s:property value="systemParam.name"/>
					</s:else>
                </td>
            </tr>
            <tr>
                <th scope="row">参数类型</th>
                <td>
                	<s:if test="%{systemParam.id == null }">
						<s:select name="systemParam.type" id="type" list="#{1:'文字',2:'图片'}"></s:select>
						
						<strong style="color: #F00;">*</strong>
	                	<span id="errMsg_systemParam.type" style="color: #F00;"></span>
					</s:if>
					<s:else>
						<s:select name="systemParam.type" id="type" list="#{1:'文字',2:'图片'}" disabled="true"></s:select>
						<s:hidden name="systemParam.type"></s:hidden>
					</s:else>
                </td>
            </tr>
            <tr>
                <th scope="row">参数编码</th>
                <td>
	                <s:if test="%{systemParam.id == null }">
						<s:textfield name="systemParam.code" id="code" maxlength="50" size="50" valid="required" errmsg="参数编码不能为空" />
											
						<strong style="color: #F00;">*</strong>
		                <span id="errMsg_systemParam.code" style="color: #F00;"></span>
					</s:if>
					<s:else>
						<s:property value="systemParam.code"/>
						<s:hidden name="systemParam.code"></s:hidden>
					</s:else>
                </td>
            </tr>
            <tr>
                <th scope="row">参数值</th>
                <td>
                	<div id="div_value_text">
                		<s:textfield name="systemParam.value" id="value" maxlength="50" size="50" valid="required" errmsg="参数值不能为空" />
                		<strong style="color: #F00;">*</strong>
                    	<span id="errMsg_systemParam.value" style="color: #F00;"></span>
                	</div>
                	<div id="div_value_photo" style="display: none">
                		<s:if test="%{systemParam.value!=null}">
							<a href="${appFilePath.systemPath}${systemParam.value}" target="_blank">${systemParam.value}</a>
						</s:if>
						<s:file name="upload" id="upload" label="上传"></s:file>
						<span id="errMsg_upload" style="color:#FF0000"></span>
                	</div>
                	
                	
                	
                	<!-- 
                	<div id="div_value_photo"  style="display: none">
                		<s:if test="%{systemParam.value!=null}">
							<a href="${appFilePath.imgPath}${systemParam.value}" target="_blank">${systemParam.value}</a>
						</s:if>
						<input type="file" id="uploadPhoto" name="uploadPhoto" size="30"
							valid="filter" allow="jpg,gif,png" errmsg=icon_allow/>
							<span id="errMsg_uploadPhoto" style="color:#FF0000"></span>
                	</div>
                	 -->
                </td>
            </tr>
			</section>
	    </table>
	</div>
	
	<!-- 保存和返回块 -->
	<div class="btnlistbar">
	    <input class="inputstyle" type="button" value="保存" onclick="doSave();" />
	    <input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back(-1)" />
	</div>
</s:form>



	<script type="text/javascript">
		initValid(document.mainForm);
		insertLanguageJS();
	</script>
</body>
</html>
