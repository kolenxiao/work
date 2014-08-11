<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<link rel="stylesheet" href="<%=ctxPath%>/css/orbit-1.2.3.css"/>
<script type="text/javascript" src="<%=ctxPath%>/js/jquery.orbit-1.2.3.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询客户端详情</title>
<style>
table { table-layout : fixed;
	word-wrap: break-word;
}
.app_pic {width:619px; margin-left:6px;}
.picture {border-left:1px solid #cacaca; border-right:1px solid #cacaca; padding:6px 0px 6px 9px;}
.picture img {margin-right:6px;}
.app_pic_topbg {width:619px; height:3px; background:url(../images/app_pic_tpbg.jpg) no-repeat;}
.app_pic_bottombg {width:619px; height:13px; background:url(../images/app_pic_bottombg.png) no-repeat;}

</style>
<script type="text/javascript">
function ShowHideBox(me){
	var el = document.getElementById('box2');
	if(el.style.display == 'none'){
		el.style.display = 'block';
		me.style.background = "url(<%=ctxPath%>/images/icon_up.gif) no-repeat";
		document.getElementById("nav02").setAttribute("calss","view_nav");
	} else{
		el.style.display = 'none';
		me.style.background = "url(<%=ctxPath%>/images/icon_down.gif ) no-repeat";
		document.getElementById("nav02").setAttribute("calss","view_nav");
	}
}

function doBack(){
	location.href ="appStoreClient!doList.action";
}
</script>
</head>
<body id="cnt_body">
<div id="position">
	<p>
		<s:text name='sdp.sce.dp.admin.global.label.current.position' />
		<s:text name='sdp.sce.dp.admin.appstore.appStoreManage' />
		&gt;
		<s:text name='sdp.sce.dp.admin.appstore.detail' />
	</p>
	<div id="pright"></div>
</div>
<div class="view_nav"  id="nav01" >
	<span class="float_l"><s:text name="sdp.sce.dp.admin.appstore.detail" /></span>
</div>
<div class="data_view">
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	 <tr>
	      	<th><s:text name="sdp.sce.dp.admin.appstore.name" /></th>
	      	<td><s:property value="appStoreClient.appName"/></td>
	    </tr>
	    <tr>
	       <th><s:text name="sdp.sce.dp.admin.appstore.versionCode" /></th>
	       <td><s:property value="appStoreClient.versionCode"/></td>
	    </tr>
	    <tr>
	       <th><s:text name="sdp.sce.dp.admin.appstore.apkFile" /></th>
	       <td>
		      <a href="/upload/appstore/<s:property value="appStoreClient.apkFileSavePath"/>" target="downloadFrame">
				<s:property value="appStoreClient.apkFileSavePath"/>
			  </a>
	       </td>
	    </tr>
	    <tr>
	       <th><s:text name="sdp.sce.dp.admin.appstore.minSystem" /></th>
	       <td><s:property value="appStoreClient.system"/></td>
	    </tr>
	    <tr>
	       <th>终端型号</th>
	       <td>
	       		<s:if test='%{appStoreClient.teminalNum == "1"}'>MSTAR</s:if>
				<s:elseif test='%{appStoreClient.teminalNum == "2"}'>HISI</s:elseif>
				<s:elseif test='%{appStoreClient.teminalNum == "3"}'>MTK</s:elseif>
	       </td>
	    </tr>
	  </table>
</div>
<div class="view_nav"  id="nav02" >
	<span class="float_l">部署应用客户端列表</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box2','nav02')" ></span>
</div>
<div id="box2">
	<div class="data_list" style="padding-top:5px; padding-bottom: 20px">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	    <tr>
	      <th width="40" scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
	      <th scope="col">终端系统版本</th>
	      <th scope="col">终端型号</th>
	      <th scope="col">终端序列号</th>
	      <th scope="col">终端MAC地址</th>
	      <th scope="col">终端ip地址</th>
	      <th scope="col">安装时间</th>
	    </tr>
		<s:iterator value="terminalList" status="st">
		<tr>
			<td>${st.index + 1 }</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
            <td></td>
		</tr>
		</s:iterator>
	  </table>
	<div class="databar">
		<jsp:include page="/common/prepage.jsp"></jsp:include>
	</div>
	</div>
	<div class="btnlistbar" align="center">
  		<input class="inputstyle" type="button" value='<s:text name="sdp.sce.dp.admin.global.btn.return" />' onclick="doBack()"/>
	</div>
</div>
<script type="text/javascript">
	document.write("<iframe id='downloadFrame' style='display: none'></iframe> ");
</script>

</body>
</html>
