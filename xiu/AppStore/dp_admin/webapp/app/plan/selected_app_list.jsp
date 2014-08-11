<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>已关联应用列表</title>
<script type="text/javascript">

$(document).ready(function() {	
	//批量排序
	$("div[name=div_batchSort]").dblclick(function(){
		$(this).hide();//隐藏div
		
		var $input = $(this).siblings("input[name=sortNum]");
		$input.attr("disabled",false);
		$input.show();
	})
	
	$("input[name=sortNum]").dblclick(function(){
		var $div = $(this).siblings("div[name=div_batchSort]");
		$div.show();
		
		$(this).val($div.text());
		$(this).hide();
		$(this).attr("disabled",true);
	})
	
	$("a[name=a_batchSort]").click(function(){
		var planItemAppIdArray = new Array();
		var sortNumArray = new Array();
		
		$("input[name=sortNum]:visible").each(function(i,e){
			planItemAppIdArray.push($(e).attr("planItemAppId"));
			sortNumArray.push($(e).val());
		});
		
		if(planItemAppIdArray.length < 1){
			$("#div_result").text("没有需要修改的排序!");
			return;
		}
		
		if(confirm("确认批量修改排序？")){
			var param = {"planItemAppIds": planItemAppIdArray.join(","),
						 "sortNums": sortNumArray.join(",")}
			
			$.post("planItemAppManage!doSort.action", param, function(data){
				if(true == data.success){
					$("#div_result").text("排序成功!");
					setTimeout(doQuery, 1000);
				}else{
					$("#div_result").text("排序失败!");
				}
			}, "json");
		}
	})
	
	
})

	//分页跳转函数
	function jumpPage(no) {
		url = 'planItemAppManage!listSelectedApp.action?';
		jumpPageTo('searchForm', url, no);
	}
	
	//上下页显示
	function page(start) {
		url = 'planItemAppManage!listSelectedApp.action?';
		goUrlPage('searchForm', url, start);
	}
	
	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'planItemAppManage!listSelectedApp.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);
	}
	
	//查询方案 
	function doQuery(){
		var searchForm = $("#searchForm").get(0);
		searchForm.action = "planItemAppManage!listSelectedApp.action";
		searchForm.submit();
	}
	
	//返回
	function doCancle(){
		var url = "planItemManage!listSelected.action?planId=${planId}";
		parent.document.getElementById("mainFrame").src = url;
		parent.window.Wclose();
	}

	//新增关联
	function addPlanItemApp(){
		var action = "planItemAppManage!listUnSelectedApp.action?planId=${planId}&planItemId=${planItemId}";
		var jsonObj = {
			scrollType : 'yes'
		};

		openshow(action, '', 1000, 540, 1, jsonObj);
	}
	
	//删除关联
	function delPlanItemApp(){
		var planItemAppIdArray = new Array();
		var flag = true;
		$(":checkbox[name=checkbox]:checked").each(function(i,e){
			planItemAppIdArray.push(e.value);		
		});
		if(planItemAppIdArray.length < 1){
			alert("请选中要取消的应用");
			return;
		}
		if(confirm("确认取消应用？")){
			$.post("planItemAppManage!deletePlanItemApp.action",{"planItemAppIds": planItemAppIdArray.join(",")},function(data){
				if("success" == data){
					alert("操作成功");
					doQuery();
				}else{
					alert(data);
				}
			});
		}
	}


</script>
</head>

<body id="cnt_body">

<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt;已关联应用列表</p>
  <div id="pright"></div>
</div>
	
<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}" />
			<s:hidden name="planId" id="planId"></s:hidden>
			<s:hidden name="planItemId" id="planItemId"></s:hidden>
			<ul>
				<li>应用名称：
					<s:textfield id="appName"
								name="appName" size="15" maxlength="64"
								value="%{appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>
					<input type="submit" value="" onclick="doQuery()" class="btn_sendData"  />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
</div>

<div id="div_result" style="color: red"></div>

<div class="databar">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.deletePlanItemApp!=null">
				<li><a href="#" onclick="delPlanItemApp()"><b>取消应用</b></a></li>
			</s:if>
           <li><a href="#" onclick="addPlanItemApp()"><b>关联应用</b></a></li>
           <li><a href="#" name="a_batchSort"><b>保存排序</b></a></li>
		</ul>
	</div>
	<jsp:include page="/common/prepage.jsp"></jsp:include>
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkboxAll" id="checkboxAll" onclick="selectAll(this)" />
					</label>
				</span>
			</th>
			<th scope="col">序号</th>
			<th scope="col">应用名称</th>
			<th scope="col">应用分类</th>
			<th scope="col">应用状态</th>
			<th scope="col">当前顺序</br>(双击可批量修改排序)</th>
		</tr>
  		<s:iterator value="page.resultList" status="st">
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" value='<s:property value="id"/>' onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td><s:if test="%{dpAppInfo.appName.length() > 12}">
               		<s:property value="%{dpAppInfo.appName.substring(0, 12) + \"..\"}"/>
               	</s:if>
               	<s:else>
               		<s:property value="dpAppInfo.appName"/>
                </s:else></td>
			<td><s:property value="dpAppInfo.dpType.typeName"/></td>
			<td><s:if test="%{dpAppInfo.appStatus == '1000'}">
					<font color="green">草稿</font>
				</s:if>
				<s:if test="%{dpAppInfo.appStatus == '1001'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.Pending" /></font>
				</s:if>
				<s:elseif test="%{dpAppInfo.appStatus == '1002'}">
					<font color="red">审核未通过</font>
				</s:elseif>
				<s:elseif test="%{dpAppInfo.appStatus == '1003'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.pass" /></font>
				</s:elseif>
				<s:elseif test="%{dpAppInfo.appStatus == '1004'}">
					<font color="green">已上架</font>
				</s:elseif>
				<s:elseif test="%{dpAppInfo.appStatus == '1005'}">
					<font color="red">已下架</font>
				</s:elseif>
				<s:elseif test="%{dpAppInfo.appStatus == '1006'}">
					<font color="green">版本更新</font>
				</s:elseif></td>
            <td>
            	<div name="div_batchSort"><s:property value='sortNum'/></div>
            	<input name="sortNum" disabled="true" style="display:none" value="<s:property value='sortNum'/>" planItemAppId="<s:property value='id'/>" maxlength='6' size='8' regexp='^[0-9]*[1-9][0-9]*$' valid='required|regexp' errmsg='dp_sort_required|dp_sort_regexp' />
            	
            	
            </td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.deletePlanItemApp!=null">
				<li><a href="#" onclick="delPlanItemApp()"><b>取消应用</b></a></li>
			</s:if>
           <li><a href="#" onclick="addPlanItemApp()"><b>关联应用</b></a></li>
           <li><a href="#" name="a_batchSort"><b>保存排序</b></a></li>
		</ul>
	</div>
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
<div class="btnlistbar">
	 <input class="inputstyle" onclick="doCancle()" type="button" value="<s:text name="sdp.sce.dp.admin.global.btn.return" />"/>
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
