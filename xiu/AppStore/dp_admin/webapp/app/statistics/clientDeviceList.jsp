<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>终端设备信息</title>
<script type="text/javascript">
	function backToList()
	{
		window.location="dpAppInfoStat!searchAppStatistic.action";
	}

	//分页跳转函数
	function jumpPage(no) {
		url = 'deviceInfo!viewDeviceDetail.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'deviceInfo!viewDeviceDetail.action?';
		goUrlPage('searchForm', url, start);
	}


	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'deviceInfo!viewDeviceDetail.action?limit=' + pageSizeVal+"&";
		//表单提交
		formSubmit('searchForm', url);
	}


</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt;<s:text name="sdp.sce.dp.admin.ap.statistic"/>&gt;
	 终端设备信息
  </p>
  <div id="pright"></div>
</div>
<!--  基本信息start-->
<div class="view_nav"  id="nav01" >
	<span class="float_l">
		终端设备信息
	</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span>
</div>
<div id="box1">
<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
	<s:hidden name="packageName" id="packageName" value="%{packageName}"></s:hidden>
	<s:hidden name="appName" id="appName" value="%{appName}"></s:hidden>
	<s:hidden name="typeName" id="typeName" value="%{typeName}"></s:hidden>
	<s:hidden name="staffName" id="staffName" value="%{staffName}"></s:hidden>
</s:form>
	<div class="data_view">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	 <tr>
	      <th>应用包名</th>
	      <td>${packageName}</td>
	    </tr>
	    <tr>
	    	<th >
	      	应用名称
	    	</th>
	       <td>${appName}</td>
	    </tr>
	    <tr>
	    	<th >
	      	所属分类
	    	</th>
	       <td>${typeName}</td>
	    </tr>
	    <tr>
	    	<th >
	      	所属开发者
	    	</th>
	       <td>${staffName}</td>
	    </tr>
	  </table>
	</div>
</div>
<div class="view_nav"  id="nav02" >
	<span class="float_l">安装的设备列表（总数：${page.totalCount}）</span>
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
  			<th scope="col">序号</th>
			<th scope="col">系统版本</th>
			<th scope="col">终端型号</th>
			<th scope="col">序列号</th>
			<th scope="col">MAC地址</th>
			<th scope="col">设备IP</th>
 			<th scope="col">安装时间</th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
		<tr>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td>
	            ${systemDpType.typeName}
			</td>
			<td><s:property value="deviceType"/></td>
			<td>
				<s:property value="deviceSerialNo"/>
			</td>
			<td>
				<s:property value="deviceMac"/>
			</td>
			<td>
				<s:property value="deviceIp"/>
			</td>
			<td>
				<s:date name="appStoreClientInstallDate" format="yyyy-MM-dd"></s:date>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/prepage.jsp"></jsp:include>
</div>

<div class="btnlistbar">
	<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="backToList()" />
</div>
</body>
</html>