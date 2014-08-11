<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱摸客用户反馈列表</title>
<script type="text/javascript">

	//分页跳转函数
	function jumpPage(no) {
		url = 'imoker!doList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'imoker!doList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'imoker!doList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>系统管理&gt;用户反馈信息列表</p>
  <div id="pright"></div>
</div>
<div id="searchbar">
  <div id="search_itemlist">
  <s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
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
           <th scope="col">序号</td>
           <th scope="col">邮箱</td>
           <th scope="col">反馈时间</td>
           <th scope="col">详情</td>
       </tr>
        <s:iterator value="page.resultList" status="st"  >
            <tr>
                <td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
                <td width="50"><s:property value="userEmail"/></td>
                <td width="50"><s:date name="feedbackTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
                <td width="50"><a href="imoker!doDetail.action?id=<s:property value="id"/>" target="mainFrame"><font color="#006DC1">浏览</font></a></td>
            </tr>
        </s:iterator>
    </table>
</div>
<div class="databar">
	<!-- 2011.11.03 Edit 引入分页界面 -->
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
	<!-- ended of 2011.11.03 Edit  -->
</div>
</body>
</html>
