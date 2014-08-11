<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/orbit-1.2.3.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.orbit-1.2.3.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/zclip/jquery.zclip.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AP应用审核</title>
<style>
table { table-layout : fixed;
	word-wrap: break-word;
}
</style>
<script type="text/javascript">
//自定义输出错误信息
FormValid.showError = function(errMsg,errName,formName) {
	if (formName=='auditForm') {
		for (key in FormValid.allName) {
			document.getElementById('errMsg_'+FormValid.allName[key]).innerHTML = '';
		}
		for (key in errMsg) {
			document.getElementById('errMsg_'+errName[key]).innerHTML = errMsg[key];
		}
	}
};

function ShowHideBox(me){
	var el = document.getElementById('box1');
	if(el.style.display == 'none'){
		el.style.display = 'block';
		me.style.background = "url(<%=ctxPath%>/images/icon_up.gif) no-repeat";
		document.getElementById("nav01").setAttribute("calss","view_nav");
	} else{
		el.style.display = 'none';
		me.style.background = "url(<%=ctxPath%>/images/icon_down.gif ) no-repeat";
		document.getElementById("nav01").setAttribute("calss","view_nav");
	}
}
//提交审核
function doAudit() {
	var auditForm = document.getElementById("auditForm");
	if(validator(auditForm)){
		auditForm.submit();
	}

}

$(window).load(function() {
	$('.featured').orbit();
});
</script>
<script type="text/javascript">
$(document).ready(function(){
	$('#copy-button').zclip({
	path:'${pageContext.request.contextPath}/zclip/ZeroClipboard.swf',
	copy:$("#contentDesc").val(),
	afterCopy:function(){
	   alert("已经复制到剪切板！" + "\n"+$("#contentDesc").val());
	 }
    });
});
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.dptype.pro" />：<s:text name="sdp.sce.dp.admin.ap.name" />&gt; <s:text name="sdp.sce.dp.admin.ap.audit" /></p>
  <div id="pright"></div>
</div>
<div class="view_nav"  id="nav01" >
	<span class="float_l"><s:text name="sdp.sce.dp.admin.ap.appinfo.detail" /></span><span class="icon_down" onclick="ShowHideBox(this)" ></span>
</div>
<div id="box1" style="padding-bottom: 20px;margin-left: 10%;margin-right: 10%" >
	<div style="padding-top:5px; padding-bottom: 20px">
			<table>
				<tr>
					<td rowspan="8">
						<s:iterator value="appInfo.attachmentList" id="attachment">
							<s:if test="%{#attachment.fileDesc=='logo'}">
								<img src="${appFilePath.logoPath}<s:property value="#attachment.fileSaveName" />" height="100" width="100"/>
							</s:if>
						</s:iterator>
					</td>
				</tr>
				<tr>
					<td colspan="4">${appInfo.appName }</td>
				</tr>
				<tr>
					<td>所属分类：</td>
					<td><s:property value="appInfo.dpType.typeName"/></td>
					<td style="padding-left: 30px">应用语言:</td>
					<td>${appInfo.language }</td>
				</tr>
				<tr>
					<td >系统要求：</td>
					<td>${appInfo.systemDpType.typeName}+</td>
					<td style="padding-left: 30px">开发商:</td>
					<td>${appInfo.developer}</td>
				</tr>
				<tr>
					<td>价格：</td>
					<td>${appInfo.price}</td>
					<td style="padding-left: 30px">版本:</td>
					<td>${appInfo.version}</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">平均评分:</td>
					<td colspan="3">${appInfo.averageScore}</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">标签:</td>
					<td colspan="3">${appTags}</td>
				</tr>
				<tr>
					<td style="padding-right: 30px">应用发布包:</td>
					<td colspan="3"><a  href="dpAppInfo!doDownLoad.action?appInfo.id=${appInfo.id }&flag=1" target="downloadFrame">
						<s:iterator value="appInfo.attachmentList" id="attachment">
							<s:if test="%{#attachment.fileDesc=='appfile'}">
								<s:property value="#attachment.fileSaveName" />
							</s:if>
						</s:iterator>
					</a></td>
				</tr>
			</table>
	</div>
	<!-- 应用简介 -->
	<div id="appdesc" style="padding-top:5px; padding-bottom: 20px">
		<s:text name="sdp.sce.dp.admin.ap.desc" />:
		<hr>
		${appInfo.appDesc }
	</div>
	<!-- 应用截图 -->
	<div style="padding-top:5px; padding-bottom: 20px">
		应用截图:
		<hr>
		<br/>
		<div class="featured">
			<s:iterator value="appInfo.attachmentList" id="attachment">
				<s:if test="%{#attachment.fileDesc=='img'}">
					<img src="${appFilePath.imgPath}<s:property value="#attachment.fileSaveName" />" height="300" width="750"/>
				</s:if>
			</s:iterator>
		</div>
	</div>
	<!-- 应用海报 -->
	<div id="app" style="padding-top:5px; padding-bottom: 20px">
		应用海报：
		<hr>
		<br/>
		<div class="featured">
			<s:iterator value="appInfo.attachmentList" id="attachment">
				<s:if test="%{#attachment.fileDesc=='poster'}">
					<img src="${appFilePath.imgPath}<s:property value="#attachment.fileSaveName" />"  height="300" width="750"/>
				</s:if>
			</s:iterator>
		</div>
	</div>
</div>


<s:if test="auditRecordList!=null && auditRecordList.size>0">
<!-- 审核记录start -->
<div class="view_nav"  id="nav03" >
	<span class="float_l">审核记录</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box3','nav03')" ></span>
</div>
<div id="box3">
	<div class="data_list">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <th width="40" scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
	      <th scope="col">审核时间</th>
	      <th scope="col">审核人</th>
	      <th scope="col">审核状态</th>
	      <th scope="col">备注</th>
	    </tr>
		<s:iterator value="auditRecordList" status="st">
		<tr>
			<td>${st.index + 1 }</td>
			<td><s:date name="auditDate"/></td>
			<td><s:property value="assessor" /></td>
			<td>
				<s:if test="%{auditResult == 1}"><s:text name="sdp.sce.dp.admin.ap.state.pass" /></s:if>
				<s:if test="%{auditResult == 2}"><s:text name="sdp.sce.dp.admin.ap.state.notpass" /></s:if>
				&nbsp;
			</td>
			<td>
				<s:if test="%{auditOption.length() > 50}">
               		<s:property value="%{auditOption.substring(0, 50) + \"..\"}"/>
               	</s:if>
               	<s:else>
               		<s:property value="auditOption"/>
                </s:else>
			</td>
		</tr>
		</s:iterator>
	  </table>
	</div>
</div>
</s:if>
<div class="view_nav"  id="nav04" >
	<span class="float_l">审核应用</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box4','nav04')" ></span>
</div>
<div id="box4">
<form action="dpAppInfo!doAudit.action?appInfo.id=${appInfo.id}&flag=${flag}" method="post" id="auditForm" name="auditForm">
<s:token/>
<div class="data_view">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th scope="row">测试链接</th>
      <td width="90%">
      		<s:if test="%{auditResult != \"1\"}">
			<s:iterator value="appInfo.attachmentList" id="attachment">
				<s:if test="%{#attachment.fileDesc=='appfile'}">
					<a href="dpAppInfo!doDownLoad.action?appInfo.id=${appInfo.id }&flag=1" target="downloadFrame"><s:property value="#attachment.fileSaveName" /></a>
					&nbsp;&nbsp;
					<%-- <input class="inputstyle" type="button" value="拷贝链接" onclick="copyToClipBoard('http://<%=request.getLocalAddr()+":"+request.getLocalPort()%>/upload/signedapp/<s:property value="#attachment.fileSaveName" />');"/> --%>
				    <input class="inputstyle" type="button" value="拷贝链接" id="copy-button">
				    <input type="hidden" value='${appFilePath.appPath}<s:property value="#attachment.fileSaveName" />' id="contentDesc">	
				</s:if>
			</s:iterator>
	   		</s:if>
       </td>
    </tr>
     <tr>
      <th scope="row"><s:text name="sdp.sce.dp.admin.dpstaff.audit.opinion" /></th>
      <td width="90%"><span><input name="auditResult" type="radio"   value="1"/> <s:text name="sdp.sce.dp.admin.ap.state.pass" /></span>
      <span style="margin-left:30px;"><input name="auditResult" type="radio" value="2" checked /><s:text name="sdp.sce.dp.admin.ap.state.notpass" /> </span></td>
    </tr>
        <tr>
      <th scope="row"><s:text name="sdp.sce.dp.admin.ap.audit.remaiks" /></th>
      <td width="90%"><textarea name="auditOption" cols="80" rows="4" valid="required|limit" min="0" max="200" errmsg="dp_audit_remake_notEmpty|dp_audit_remake_lenLess200"></textarea>
       <span class="style-red" id="error">*</span> <span id="errMsg_auditOption" style="color:#FF0000"></span>
      </td>
    </tr>
  </table>
</div>
<div class="btnlistbar">
  <input class="inputstyle" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.save" />" onclick="doAudit()"/>
  <input class="inputstyle" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"  onclick="javascript:history.back()"/>
</div>
</form>
</div>
<script type="text/javascript">
		document.write("<iframe id='downloadFrame' style='display: none'></iframe> ");
		initValid(document.auditForm);
		insertLanguageJS();
</script>
</body>
</html>
