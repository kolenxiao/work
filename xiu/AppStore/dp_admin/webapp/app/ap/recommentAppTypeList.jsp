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
		url = 'dpAppInfo!toAddTypeRecommentList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'dpAppInfo!toAddTypeRecommentList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'dpAppInfo!toAddTypeRecommentList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	//推荐应用
	function recommendChoose()
	{
		var dpType = document.getElementById("queryAppInfo.dpType.id").value;
		var pageSizeVal = document.getElementById("pageSize").value;
		var param = {'commendFlag':'1','operater':'1','queryAppInfo.dpType.id':dpType};
		var pop = {'tips'     :'确定推荐选择的应用吗?',
				   'reqURL'   :'appTypeRecommend!doCommend.action',
				   'param'    : param,
				   'callback' : function(){document.forms['searchForm'].submit(); }};
		confirmChoose2(pop);//ajax提交
	}

	function searchApp() {
		url = 'dpAppInfo!toAddTypeRecommentList.action?';
		var pageSize = document.getElementById("pageSize").value;
		url = url+'start=' + 1 +'&limit='+pageSize;
		var form = document.getElementById("searchForm");
		form.action= url;
		form.submit();
	}

	function doCancle(){
		parent.document.getElementById("mainFrame").src="appTypeRecommend!doSearchAppTypeRecommendList.action?queryAppInfo.dpType.id=${queryAppInfo.dpType.id}";
		parent.window.Wclose();
	}
	
	//显示应用详细信息
	function viewDeatil(id) {
		location.href = 'dpAppInfo!doDisplay.action?forward=detail&appInfo.id='+ id;
	}
</script>
</head>
<body id="cnt_body">

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="dpAppInfo!toAddTypeRecommentList.action" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="queryAppInfo.dpType.id" name="queryAppInfo.dpType.id" value="${queryAppInfo.dpType.id}"/>
			<ul>

				<li><s:text name="sdp.sce.dp.admin.ap.label.appName" />
					<s:textfield id="queryAppInfo.appName"
								name="queryAppInfo.appName" size="15" maxlength="64"
								value="%{queryAppInfo.appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
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
					<input type="button" value="" class="btn_sendData"  onclick="searchApp()" />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
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
		</tr>
  		<s:iterator value="page.resultList" status="st">
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="<s:property value='id' />" onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td>
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
			<td><s:property value="developer"/></td>
			<td><s:date name="createTime" format="yyyy-MM-dd"></s:date></td>
			<td><s:date name="updateTime" format="yyyy-MM-dd"></s:date></td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>

<div class="btnlistbar">
	<input class="inputstyle" onclick="recommendChoose()"
		 type="button"  value="<s:text name="sdp.sce.dp.admin.global.btn.confirm" />"/>
	 <input class="inputstyle" onclick="doCancle()"
	 	type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
</div>
</body>
</html>
