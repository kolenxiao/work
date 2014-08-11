<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<%
	String strPlanId = request.getParameter("planId");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案条件列表</title>
<script type="text/javascript">

	//分页跳转函数
	function jumpPage(no) {
		url = 'condition!listForPlan.action?';
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'condition!listForPlan.action?';
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'condition!listForPlan.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);
	}
	
	//查询
	function search() {
		url = 'condition!listForPlan.action?';
		goUrlPage('searchForm', url, 1);
	}

	//关联方案
	function add(id) {
		var idArray = new Array();
		if(!!id){
			idArray.push(id);
		}else{
			var childCheckboxs = document.getElementsByName("checkbox");
			
			for ( var i = 0; i < childCheckboxs.length; i++) {
				if (childCheckboxs[i].checked){
					var vId = childCheckboxs[i].id;
					idArray.push(vId);
					if( 1 == childCheckboxs[i].getAttribute("data-planflag")){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,"不能添加关联【已关联】的数据",0, 0, 1, this);
						return;
					}
				}
			}
			if (idArray.length < 1){
				dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150, '请选中要【添加关联】的数据',0, 0, 1, this);
				return ;
			}
		}	
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'您确认要【添加关联】吗?',0, 0, 2, this);
		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform").click(
			function(){
				//把字符串数组转换成字符串 传入参数ids
				$.post("condition!addPlanCondition.action",{"planId":"<%=strPlanId%>","conditionIds": idArray.join(",")},function(data){
					$("#dialog").remove();
					if("success" == data){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'【添加关联】成功',0, 0, 1, this);
						$("#dialog_btn_conform").click(function(){
							search();
						});
					}else{
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300, 150, '【添加关联】失败', 0,0, 1, this);
					}
				});
		});
	}
	//去掉与方案的关联
	function del(id) {
		var idArray = new Array();
		if(!!id){
			idArray.push(id);
		}else{
			var childCheckboxs = document.getElementsByName("checkbox");
			
			for ( var i = 0; i < childCheckboxs.length; i++) {
				if (childCheckboxs[i].checked){
					var vId = childCheckboxs[i].id;
					idArray.push(vId);
					if( 0 == childCheckboxs[i].getAttribute("data-planflag")){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,"不能取消关联【未关联】的数据",0, 0, 1, this);
						return;
					}
				}
			}
			if (idArray.length < 1){
				dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150, '请选中要【取消关联】的数据',0, 0, 1, this);
				return ;
			}
		}	
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'您确认要【取消关联】吗?',0, 0, 2, this);
		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform").click(
			function(){
				//把字符串数组转换成字符串 传入参数ids
				$.post("condition!deletePlanCondition.action",{"planId":"<%=strPlanId%>","conditionIds": idArray.join(",")},function(data){
					$("#dialog").remove();
					if("success" == data){
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'【取消关联】成功',0, 0, 1, this);
						$("#dialog_btn_conform").click(function(){
							search();
						});
					}else{
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300, 150, '【取消关联】失败', 0,0, 1, this);
					}
				});
		});
	}

</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt; 方案条件关联</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
		<s:form id="searchForm" name="searchForm" action="condition" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}"/>
			<input type="hidden" id="planId" name="planId" value="<%=strPlanId %>"/>
			<ul>
				<li>是否关联:
					<s:select list="#{'-2':'全部',1:'已关联',0:'未关联'}"  name="queryCondition.planSearchFlag" id="queryCondition.planSearchFlag" value="%{queryCondition.planSearchFlag}">
					</s:select>
				</li>	
				<li>条件状态:
					<s:select list="#{'-2':'全部',1:'已启用',0:'已禁用'}"  name="queryCondition.status" id="queryCondition.status" value="%{queryCondition.status}">
					</s:select>
				</li>								
				<li>条件名称:
					<s:textfield id="queryCondition.name" name="queryCondition.name" size="15" maxlength="64"
								value="%{queryCondition.name}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>条件编码:
					<s:textfield id="queryCondition.code" name="queryCondition.code" size="15" maxlength="64"
								value="%{queryCondition.code}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>条件值:
					<s:textfield id="queryCondition.value" name="queryCondition.value" size="15" maxlength="64"
								value="%{queryCondition.value}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>								
				<li>
					<input type="button" value="" class="btn_sendData"  onclick="search()" />&nbsp;
				</li>
			</ul>
		</s:form>
	</div>
</div>

<div class="databar">
	<div class="btnbar">
		<ul>
			<li><a href="#" onclick="add()"><b>添加关联</b> </a></li>
			<li><a href="#" onclick="del()"><b>取消关联</b> </a></li>
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
						<input type="checkbox" name="checkbox1" id="checkboxAll" onclick="selectAll(this)" />
					</label>
				</span>
			</th>
			<th scope="col">序号</th>
			<th scope="col">条件名称</th>
			<th scope="col">条件编码</th>
			<th scope="col">条件值</th>
			<th scope="col">条件描述</th>
			<th scope="col">条件状态</th>
			<th scope="col">是否关联</th>
			<th scope="col">操作</th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
		<tr>
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="<s:property value='id' />"  data-planflag="<s:property value='planFlag' />" onclick="selectChildAll()"/>
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td><s:property value="name"/></td>
			<td><s:property value="code"/></td>
			<td><s:property value="value"/></td>
			<td><s:property value="description"/></td>
			<td>
				<s:if test="%{status == 1}">
					<font color="green">已启用</font>
				</s:if>
				<s:elseif test="%{status == 0}">
					<font color="red">已禁用</font>
				</s:elseif>		
			</td>			
			<td>
				<s:if test="%{planFlag == 1}">
					<font color="green">已关联</font>
				</s:if>
				<s:elseif test="%{planFlag == 0}">
					<font color="black">未关联</font>
				</s:elseif>	
			</td>
			<td class="editbar">
				<s:if test="%{planFlag == 0}">
					<a class="detail_link" href="javascript:add('<s:property value="id"/>')" >添加关联</a>
				</s:if>
				<s:if test="%{planFlag == 1}">
					<a class="detail_link" href="javascript:del('<s:property value="id"/>')" >取消关联</a>
				</s:if>			
			</td>			
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<div class="btnbar">
		<ul>
			<li><a href="#" onclick="add()"><b>添加关联</b> </a></li>
			<li><a href="#" onclick="del()"><b>取消关联</b> </a></li>
		</ul>								
	</div>
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
</body>
</html>
