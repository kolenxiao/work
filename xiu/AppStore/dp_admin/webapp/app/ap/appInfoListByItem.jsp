<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AP应用列表</title>
<script type="text/javascript">

	//分页跳转函数
	function jumpPage(no) {
		url = 'dpAppInfo!doAppList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'dpAppInfo!doAppList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'dpAppInfo!doAppList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	function searchApp(startTimeId, endTimeId) {

		url = 'dpAppInfo!doAppList.action?';
		//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
		querydata('searchForm', url, startTimeId, endTimeId);
	}
    
	function doCancle(){
		parent.window.Wclose();
	}
	
	function doRecommend(){
 		var idArray = new Array();//定义元素数组
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;//获取元素个数
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked)
				idArray.push(childCheckboxs[i].id);//添加被选择的元素到数组
		}
		if (idArray.length > 1) {
			alert("只能选择一条记录");
			return false;
		}
		parent.frames["mainFrame"].window["callBack"].apply(this, idArray);
		parent.window.Wclose();
	}

</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt; <s:text name="sdp.sce.dp.admin.ap.all" /></p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}"/>
			<ul>
				<li><s:text name="sdp.sce.dp.portal.ap.classfily" />
					<s:select id="queryAppInfo.dpType.id" name="queryAppInfo.dpType.id"
							value="%{queryAppInfo.dpType.id}" headerKey="" headerValue="全部"
							list="dpTypeList" listKey="id" listValue="typeName"
							cssStyle="width:60px;">
					</s:select>
				</li>
				<li><s:text name="sdp.sce.dp.admin.ap.label.appName" />
					<s:textfield id="queryAppInfo.appName"
								name="queryAppInfo.appName" size="15" maxlength="64"
								value="%{queryAppInfo.appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>

				<li><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" />:
					<s:textfield id="queryAppInfo.dpStaff.userName"
								name="queryDpStaff.userName" size="15" maxlength="64"
								value="%{queryDpStaff.userName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				
				<li><s:text name="sdp.sce.dp.admin.ap.label.uploadTime" />
					<s:textfield id="queryAppInfo.beginAppInfoCTime"
							name="queryAppInfo.beginAppInfoCTime" size="10"
							value="%{queryAppInfo.beginAppInfoCTime}"  readonly="true" /></li><li>
							 <img
							onclick="WdatePicker({el:'queryAppInfo.beginAppInfoCTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li><s:text name="sdp.sce.dp.admin.global.label.to" />
					<s:textfield id="queryAppInfo.endAppInfoCTime"
							name="queryAppInfo.endAppInfoCTime" size="10"
							value="%{queryAppInfo.endAppInfoCTime}" readonly="true"/> </li><li>
							<img
							onclick="WdatePicker({el:'queryAppInfo.endAppInfoCTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li>
					<input type="button" value="" class="btn_sendData"  onclick="searchApp('queryAppInfo.beginAppInfoCTime','queryAppInfo.endAppInfoCTime')" />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
</div>

<div class="databar">
	<!-- 2011.11.03 Edit 引入分页界面 -->
	<jsp:include page="/common/prepage.jsp"></jsp:include>
	<!-- ended of 2011.11.03 Edit  -->
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox1" id="checkboxAll" onclick="selectAll(this)" />
					</label>
				</span>
			</th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.appName" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.portal.ap.classfily" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
 			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.state" /></th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
		<tr>
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="{'id':'<s:property value='appFilePackage' />','name':'<s:property value="appName"/>'}" onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td>
				<!-- 查看应用详情超链接 -->
				<a class="detail_link" href="javascript:viewDeatil('<s:property value="id"/>')" >
					<s:if test="%{appName.length() > 12}">
	               		<s:property value="%{appName.substring(0, 12) + \"..\"}"/>
	               	</s:if>
	               	<s:else>
	               		<s:property value="appName"/>
	                </s:else>
				</a>
			</td>
			<td><s:property value="dpType.typeName"/></td>
			<td>
				<s:if test="%{dpStaffId != null}">
					<s:property value="dpStaff.userName"/>
				</s:if>
				<s:if test="%{userId != null}">
					<s:property value="user.userName"/>
				</s:if>
			</td>
			<td><s:date name="createTime" format="yyyy-MM-dd"></s:date></td>
			<td><s:date name="updateTime" format="yyyy-MM-dd"></s:date></td>
			<td>
				<s:if test="%{appStatus == '1000'}">
					<font color="green">草稿</font>
				</s:if>
				<s:if test="%{appStatus == '1001'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.Pending" /></font>
				</s:if>
				<s:elseif test="%{appStatus == '1002'}">
					<font color="red">审核未通过</font>
				</s:elseif>
				<s:elseif test="%{appStatus == '1003'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.pass" /></font>
				</s:elseif>
				<s:elseif test="%{appStatus == '1004'}">
					<font color="green">已上架</font>
				</s:elseif>
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
	<!-- 2011.11.03 Edit 引入分页界面 -->
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
	<!-- ended of 2011.11.03 Edit  -->
</div>
<div class="btnlistbar">
	<input class="inputstyle" onclick="doRecommend()" type="button"  value="<s:text name="sdp.sce.dp.admin.global.btn.confirm" />"/>
	 <input class="inputstyle" onclick="doCancle()" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
</div>
</body>
</html>
