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
<title>查询隐式应用详情</title>
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

function doBack(){
	location.href ="implicit!doList.action";
}
</script>
</head>
<body id="cnt_body">
<div id="position">
	<p>
		<s:text name='sdp.sce.dp.admin.global.label.current.position' />
		隐式应用管理
		&gt;
		隐式应用详情
	</p>
	<div id="pright"></div>
</div>
<div class="view_nav"  id="nav01" >
	<span class="float_l">隐式应用详情</span>
</div>
<div class="data_view">
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	<tr>
	      	<th>应用名称</th>
	      	<td><s:property value="implicitApp.appName"/></td>
	    </tr>
	    <tr>
	       <th><s:text name="sdp.sce.dp.admin.appstore.minSystem" /></th>
	       <td><s:property value="implicitApp.systemVo"/></td>
	    </tr>
	     <tr>
	       <th><s:text name="sdp.sce.dp.admin.implicit.versionCode" /></th>
	       <td><s:property value="implicitApp.versionCode"/></td>
	    </tr>
	    <tr>
	       <th>apk包</th>
	       <td>
		      <a href="/upload/signedapp/<s:property value="implicitApp.apkFileSavePath"/>" target="downloadFrame">
				<s:property value="implicitApp.fileName"/>
			  </a>
	       </td>
	    </tr>
	     <tr>
	       <th>包名</th>
	       <td>
		      <s:property value="implicitApp.appFilePackage"/>
	       </td>
	    </tr>
	    <tr>
	       <th>创建时间</th>
	       <td>
	       		<s:date name="implicitApp.createTime" format="yyyy-MM-dd HH:ss"/>
	       </td>
	    </tr>
	    <s:if test="implicitApp.deployTime != null">
	    	<tr>
		       <th>部署时间</th>
		       <td>
		       		<s:date name="implicitApp.deployTime" format="yyyy-MM-dd HH:ss"/>
		       </td>
		    </tr>
	    </s:if>
	    <tr>
	       <th>执行类型</th>
	       <td>
	       		<s:if test='%{implicitApp.implicitType=="1"}'>
					安装
				</s:if>
				<s:elseif test='%{implicitApp.implicitType=="2"}'>
					升级
				</s:elseif>
				<s:elseif test='%{implicitApp.implicitType=="3"}'>
					卸载
				</s:elseif>
	       </td>
	    </tr>
	    <tr>
	       <th>应用状态</th>
	       <td>
	       		<s:if test='%{implicitApp.status == "0"}'>
					启用
				</s:if>
				<s:else>
					停用
				</s:else>
	       </td>
	    </tr>
	    <tr>
	       <th>应用描述</th>
	       <td>
	       		${implicitApp.description}
	       </td>
	    </tr>
	  </table>
</div>
<div class="btnlistbar" align="center">
  		<input class="inputstyle" type="button" value='<s:text name="sdp.sce.dp.admin.global.btn.return" />' onclick="doBack()"/>
	</div>
<script type="text/javascript">
	document.write("<iframe id='downloadFrame' style='display: none'></iframe> ");
</script>

</body>
</html>
