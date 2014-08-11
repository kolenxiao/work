<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用统计</title>
<script type="text/javascript">
	//分页跳转函数
	function jumpPage(no) {
		url = 'dpAppInfoStat!searchAppStatistic.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'dpAppInfoStat!searchAppStatistic.action?';
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'dpAppInfoStat!searchAppStatistic.action?limit=' + pageSizeVal+"&";
		//表单提交
		formSubmit('searchForm', url);
	}

	function searchApp(startTimeId, endTimeId) {

		url = 'dpAppInfoStat!searchAppStatistic.action?';
		querydata('searchForm', url, startTimeId, endTimeId);
	}

	//显示应用详细信息
	function viewDeatil(id) {
		location.href = 'dpAppInfoStat!viewDetailAppStatic.action?appStatQuery.appId='
				+ id;
	}

	function viewDeviceDetail(packageName, staffName)
	{
		location.href = 'deviceInfo!viewDeviceDetail.action?packageName='
			+ packageName+'&staffName='+staffName;
	}

	/**
	*	列头排序
	*	col点击中的列头dom元素对象
	*   startTimeId, endTimeId 查询表单中的日期id
	*/
	function sortColumn(col,startTimeId, endTimeId){
		var orderType = $(col).attr("sortType");
		var prop = $(col).attr("id");

		if(orderType=="desc"){
			orderType="asc";
		}else{
			orderType="desc";
		}
		$("#orderType").val(orderType);
		$("#orderProperty").val(prop);
		url = 'dpAppInfoStat!searchAppStatistic.action?';
		querydata('searchForm', url, startTimeId, endTimeId);
	}

	$(document).ready(function(){
		var orderType = $("#orderType").val();
		var orderProperty =$("#orderProperty").val();
		var selector= "#"+orderProperty;
		$(selector).attr("sortType",orderType);
		var ascIconUrl = "<%=ctxPath%>/images/asc.gif";
		var descIconUrl = "<%=ctxPath%>/images/desc.gif";
		if(orderType=='desc'){
			$("img",selector).attr("src",ascIconUrl);
			//$(selector).attr("class","descCol");
		}else{
			$("img",selector).attr("src",descIconUrl);
			//$(selector).attr("class","ascCol");
		}
	});
</script>
<style type="text/css">
.sortableCol {
  background: url('<%=ctxPath%>/images/sortable_bg.gif') no-repeat scroll right center transparent;
  cursor: pointer;
}

.ascCol {
  background: url('<%=ctxPath%>/images/desc.gif') no-repeat scroll right center transparent;
  cursor: pointer;
}
.descCol {
  background: url('<%=ctxPath%>/images/asc.gif') no-repeat scroll right center transparent;
  cursor: pointer;
}

</style>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt; <s:text name="sdp.sce.dp.admin.ap.statistic" /></p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<s:hidden name="appStatQuery.orderType" id="orderType" value="%{appStatQuery.orderType}"/>
			<s:hidden name="appStatQuery.orderProperty" id="orderProperty" value="%{appStatQuery.orderProperty }"/>
			<ul>
				<li><s:text name="sdp.sce.dp.portal.ap.classfily" />
					<s:select id="appStatQuery.appTypeId" name="appStatQuery.appTypeId"
							value="%{appStatQuery.appTypeId}" headerKey="" headerValue="全部"
							list="dpTypeList" listKey="id" listValue="typeName"
							cssStyle="width:60px;">
					</s:select>
				</li>
				<li><s:text name="sdp.sce.dp.admin.ap.label.appName" />
					<s:textfield id="appStatQuery.appName"
								name="appStatQuery.appName" size="15" maxlength="64"
								value="%{appStatQuery.appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>

				<li><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" />:
					<s:textfield id="appStatQuery.dpStaffName"
								name="appStatQuery.dpStaffName" size="15" maxlength="64"
								value="%{appStatQuery.dpStaffName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>更新时间
					<s:textfield id="appStatQuery.startTime"
							name="appStatQuery.startTime" size="10"
							value="%{appStatQuery.startTime}"  readonly="true" /></li><li>
							 <img
							onclick="WdatePicker({el:'appStatQuery.startTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li><s:text name="sdp.sce.dp.admin.global.label.to" />
					<s:textfield id="appStatQuery.endTime"
							name="appStatQuery.endTime" size="10"
							value="%{appStatQuery.endTime}" readonly="true"/> </li><li>
							<img
							onclick="WdatePicker({el:'appStatQuery.endTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li>
					<input type="button" value="" class="btn_sendData"  onclick="searchApp('appStatQuery.startTime','appStatQuery.endTime')" />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
</div>

<div class="databar">
<jsp:include page="/common/prepage.jsp"></jsp:include>
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.appName" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.portal.ap.classfily" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" /></th>
			<th class="select_td" sortType="" id="commentCount" scope="col" onclick="sortColumn(this,'appStatQuery.startTime','appStatQuery.endTime')">评论数<img src="<%=ctxPath%>/images/sortable_bg.gif"/></th>
			<th class="select_td" sortType="" id="commentAvgScore" scope="col" onclick="sortColumn(this,'appStatQuery.startTime','appStatQuery.endTime')">平均评分值<img src="<%=ctxPath%>/images/sortable_bg.gif"/></th>
			<th class="select_td" sortType="" id="downloadCount" scope="col" onclick="sortColumn(this,'appStatQuery.startTime','appStatQuery.endTime');">下载量<img src="<%=ctxPath%>/images/sortable_bg.gif"/></th>
 			<th class="select_td" sortType="" id="updateTime" scope="col" onclick="sortColumn(this,'appStatQuery.startTime','appStatQuery.endTime');"><s:text name="sdp.sce.dp.admin.news.newsUTime" /><img src="<%=ctxPath%>/images/sortable_bg.gif"/></th>
 			<th scope="col">终端设备信息</th>
 			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.state" /></th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
		<tr>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td>
				<a class="detail_link" href="javascript:void(0)" onclick="viewDeatil('<s:property value="appId"/>')">
					<s:if test="%{appName.length() > 12}">
	               		<s:property value="%{appName.substring(0, 12) + \"..\"}"/>
	               	</s:if>
	               	<s:else>
	               		<s:property value="appName"/>
	                </s:else>
				</a>
			</td>
			<td><s:property value="appType"/></td>
			<td><s:property value="dpStaffName"/></td>
			<td><s:property value="commentCount"/></td>
			<td><s:property value="commentAvgScore"/></td>
			<td><s:property value="downloadCount"/></td>
			<td><s:date name="updateTime" format="yyyy-MM-dd"></s:date></td>
			<td><a onclick="viewDeviceDetail('<s:property value="packageName"/>','<s:property value="dpStaffName"/>')"
					 class="detail_link" href="javascript:void(0)">终端设备详情</a></td>
			<td>
				<s:if test="%{appStatus == '1004'}">
					<font color="green">已上架</font>
				</s:if>
				<s:elseif test="%{appStatus == '1005'}">
					<font color="green">已下架</font>
				</s:elseif>
				<s:elseif test="%{appStatus == '1006'}">
					<font color="green">版本已更新</font>
				</s:elseif>
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
