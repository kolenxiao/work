<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AP应用专题详情</title>
<style type="text/css">
	#imgDiv {
		float: left;
		border: none;
	}
</style>
<script type="text/javascript">
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

//分页跳转函数
function jumpPage(no) {
	url = 'dpAppSubjectType!doDisplay.action?';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'dpAppSubjectType!doDisplay.action?';
	//searchForm是form表单id,url 为跳转路径,start为第几页
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {

	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'dpAppSubjectType!doDisplay.action?limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}

function backToList()
{
	window.location="dpAppSubjectType!doSearchAppSubjectTypeList.action";
}

</script>
</head>
<body>
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt;应用专题&gt;专题详情
  </p>
  <div id="pright"></div>
</div>
<div class="view_nav"  id="nav01" >
	<span class="float_l">专题详情</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span>
</div>
<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
<s:hidden name="appSubjectType.id" value="%{appSubjectType.id}"></s:hidden>
</s:form>
<div id="box1" style="padding-bottom: 2px;" >
	<div style="padding-top:12px;margin-left: 10%">
		<div id="imgDiv"><img src="/upload/appimages/${appSubjectType.subjectImg}" height="100" width="100"/></div>
		<div style="padding-left: 112px;">
			<h1><s:property value="appSubjectType.subjectName"/></h1>
			<s:property value="appSubjectType.subjectDesc"/>
		</div>
		<div style="clear: both;"></div>
	</div>
 		<s:iterator value="appInfoList" id="appInfo">
  		<div style="padding-top:12px;margin-top:10px;margin-bottom:10px ;margin-left: 10%; margin-right: 10%;margin-bottom: 10px;border-color: black; border-width:1px;border-style: solid;">
			<div id="imgDiv">
				<s:iterator value="#appInfo.attachmentList" id="attachment">
					<s:if test="%{#attachment.fileDesc=='logo'}">
						<img src="/upload/applogo/<s:property value="#attachment.fileSaveName" />" height="100" width="100"/>
					</s:if>
				</s:iterator>
			</div>
			<div style="padding-left: 112px;display: block;">
				<table width="100%">
					<tr>
						<td style="width: 60%"><s:property value="appName"/></td>
						<td align="right"><s:if test="price=0.0">免费</s:if><s:else><s:property value="price"/>元</s:else></td>
					</tr>
					<tr>
						<td style="width: 60%;"><s:property value="appDesc"/></td>
						<td align="right">评分值:<s:property value="averageScore"/>&nbsp;&nbsp;下载次数:<s:property value="downloadCount"/>+</td>
					</tr>
				</table>
			</div>
			<div style="clear: both;"></div>
		</div>
		</s:iterator>
	<div class="databar">
		<jsp:include page="/common/prepage.jsp"></jsp:include>
	</div>
</div>
<div class="btnlistbar">
	<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="backToList();" />
</div>
</body>
</html>