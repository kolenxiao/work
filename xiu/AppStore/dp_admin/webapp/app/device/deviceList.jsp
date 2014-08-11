<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>终端设备列表</title>
<script type="text/javascript">
	//分页跳转函数
	function jumpPage(no) {
		url = 'appStoreClient!doList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'deviceInfo!doList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'deviceInfo!doList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>
			<s:text name="sdp.sce.dp.admin.global.label.current.position" />
			客户端管理
			&gt;
			终端信息列表
		</p>
		<div id="pright"></div>
	</div>

	<div id="searchbar">
		<s:form id="searchForm" name="searchForm" action="" method="post"
			cssStyle="margin:0" theme="simple">
		</s:form>
	</div>

	<div class="databar">
		<jsp:include page="/common/prepage.jsp"></jsp:include>
	</div>
	<div class="data_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" />
				</th>
				<th scope="col">终端型号
				</th>
				<th scope="col">终端系统版本
				</th>
				<th scope="col"> 终端序列号
				</th>
				<th scope="col">终端MAC地址
				</th>
				<th scope="col">终端ip地址
				</th>
				<th scope="col">客户端版本号
				</th>
				<th scope="col">安装的时间
				</th>
			</tr>
			<s:iterator value="page.resultList" status="st">
				<tr>
					<td width="50">${(page.currentPage-1) * page.pageSize +
						st.index + 1 }</td>
					<td>
						<s:property value="deviceType" />
					</td>
					<td>
						${systemDpType.typeName}
					</td>
					<td><s:property value="deviceSerialNo" /></td>
					<td><s:property value="deviceMac" /></td>
					<td><s:property value="deviceIp" /></td>
					<td><s:property value="appStoreClientVersion" /></td>
					<td><s:date name="appStoreClientInstallDate" format="yyyy-MM-dd HH:mm"></s:date>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	<div class="databar">
		<jsp:include page="/common/nextpage.jsp"></jsp:include>
	</div>
</body>
</html>
