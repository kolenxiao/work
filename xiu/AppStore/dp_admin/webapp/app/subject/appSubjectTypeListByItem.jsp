<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AP应用专题列表</title>
<script type="text/javascript">
	//分页跳转函数
	function jumpPage(no) {
		url = 'dpAppSubjectType!doSearchAppSubjectTypeListByItem.action?';
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'dpAppSubjectType!doSearchAppSubjectTypeListByItem.action?';
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'dpAppSubjectType!doSearchAppSubjectTypeListByItem.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	function searchAppSubjectType(startTimeId, endTimeId) {

		url = 'dpAppSubjectType!doSearchAppSubjectTypeListByItem.action?';
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
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>精选页管理&gt;新增版块元素&gt;专题查询</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<ul>
				<li>专题名称：
					<s:textfield id="queryAppSubjectType.subjectName"
								name="queryAppSubjectType.subjectName" size="30" maxlength="64"
								value="%{queryAppSubjectType.subjectName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>创建日期:
					<s:textfield id="queryAppSubjectType.beginSujectCTime"
							name="queryAppSubjectType.beginSujectCTime" size="10"
							value="%{queryAppSubjectType.beginSujectCTime}"  readonly="true" /></li><li>
					<img
					onclick="WdatePicker({el:'queryAppSubjectType.beginSujectCTime',dateFmt:'yyyy-MM-dd'})"
					src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
					width="16" height="22" />
				</li>
				<li>
					<s:textfield id="queryAppSubjectType.endSujectCTime"
							name="queryAppSubjectType.endSujectCTime" size="10"
							value="%{queryAppSubjectType.endSujectCTime}" readonly="true"/> </li><li>
					<img
					onclick="WdatePicker({el:'queryAppSubjectType.endSujectCTime',dateFmt:'yyyy-MM-dd'})"
					src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
					width="16" height="22" />
				</li>
				<li>
					<input type="button" value="" class="btn_sendData"  onclick="searchAppSubjectType('queryAppSubjectType.beginSujectCTime','queryAppSubjectType.endSujectCTime')" />&nbsp;
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
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox1" id="checkboxAll" onclick="selectAll(this)" />
					</label>
				</span>
			</th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th scope="col">专题名称</th>
			<th scope="col">应用数</th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
 			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
			<tr >
				<td>
					<span id="sprycheckbox1">
						<label>
							<input type="checkbox" name="checkbox" id="{'id':'<s:property value='id' />','name':'<s:property value="subjectName"/>'}" onclick="selectChildAll()" />
						</label>
					</span>
				</td>
				<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
				<td>
					<!-- 查看应用专题详情超链接 -->
					<a class="detail_link"
						href="subjectAppinfoRelationAction!toEditSubject.action?appSubjectType.id=<s:property value="id"/>&operate=addApp" >
						<s:if test="%{subjectName.length() > 12}">
		               		<s:property value="%{subjectName.substring(0, 12) + \"..\"}"/>
		               	</s:if>
		               	<s:else>
		               		<s:property value="subjectName"/>
		                </s:else>
					</a>
				</td>
				<td><s:property value="appTotal"/></td>
				<td><s:property value="createUser"/></td>
				<td><s:date name="createDate" format="yyyy-MM-dd"></s:date></td>
				<td><s:date name="updateDate" format="yyyy-MM-dd"></s:date></td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
<div class="btnlistbar">
	<input class="inputstyle" onclick="doRecommend()" type="button"  value="<s:text name="sdp.sce.dp.admin.global.btn.confirm" />"/>
	 <input class="inputstyle" onclick="doCancle()" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
</div>
</body>
</html>
