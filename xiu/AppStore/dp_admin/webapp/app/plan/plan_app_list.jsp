<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<% String strAppId = request.getParameter("appId"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案列表</title>
<script type="text/javascript">

	//分页跳转函数
	function jumpPage(no) {
		url = 'planManage!listForApp.action?';
		jumpPageTo('searchForm', url, no);
	}
	
	//上下页显示
	function page(start) {
		url = 'planManage!listForApp.action?';
		goUrlPage('searchForm', url, start);
	}
	
	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'planManage!listForApp.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);
	}
	
	//查询
	function search() {
		url = 'planManage!listForApp.action?';
		goUrlPage('searchForm', url, 1);
	}

	//显示方案的基本信息
	function viewDeatil(planId) {
		if(undefined == planId){
			alert("planId不能为空");
			return;
		}
		var url = "planManage!doDisplay.action?planId=" + planId;
		url = url + "&flag=detail";
		location.href = url;
	}
	
	function appAddPlan(planId){
		var itemCheckBoxs = $("#checkbox_"+planId).find("input[type='checkbox']");
		var vAddItemIds = new Array();
		var vDeleteItemIds = new Array();
		for ( var i = 0; i < itemCheckBoxs.length; i++) {
			var vId = itemCheckBoxs[i].id;
			var vItemFlag =  itemCheckBoxs[i].getAttribute("data-itemflag");
			if (itemCheckBoxs[i].checked){
				if( vItemFlag == "0"){
					vAddItemIds.push(vId);
				}
			}else if(vItemFlag == "1"){
				vDeleteItemIds.push(vId);
			}
		}
		
		if(vAddItemIds.length < 1 && vDeleteItemIds.length < 1){
			dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'数据未改变',0, 0, 1, this);
			return;
		}
		
		var vParams = {};
		vParams["planId"] = planId;
		vParams["appId"] = "<%=strAppId%>";
		vParams["addItemIds"] = vAddItemIds.join(",");
		vParams["deleteItemIds"] = vDeleteItemIds.join(",");
		
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'您确认要【保存关联】吗?',0, 0, 2, this);
		$("#dialog_btn_conform").click(
				function(){
					//把字符串数组转换成字符串 传入参数ids
					$.post("planItemAppManage!appAddPlan.action",vParams,function(data){
						$("#dialog").remove();
						if("success" == data){
							dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'保存关联成功',0, 0, 1, this);
							$("#dialog_btn_conform").click(function(){
								search();
							});
						}else{
							dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300, 150, '保存关联失败', 0,0, 1, this);
						}
					});
			});
	}
	
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>应用上架&gt; 方案应用关联</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
	<div id="search_itemlist">
		<s:form id="searchForm" name="searchForm" action="planManage!listForApp.action" method="post" cssStyle="margin:0" theme="simple">
			<input type="hidden" id="limit" name="limt" value="${limit}" />
			<input type="hidden" id="appId" name="appId" value="<%=strAppId %>" />
			<ul>	
				<li>
					方案状态
					<s:select list="#{'-2':'全部',0:'未启用',1:'已启用'}" name="plan.status" id="status" value="%{plan.status}" />
				</li>				
				<li>方案名称 <s:textfield id="name" name="plan.name" size="15"
						maxlength="64" value="%{plan.name}"
						onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li>生效时间：
					<s:textfield id="startTime"
							name="plan.startTime" size="10"
							value="%{plan.startTime}"  readonly="true" /></li><li>
							 <img
							onclick="WdatePicker({el:'startTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li><s:text name="sdp.sce.dp.admin.global.label.to" />
					<s:textfield id="endTime"
							name="plan.endTime" size="10"
							value="%{plan.endTime}" readonly="true"/> </li><li>
							<img
							onclick="WdatePicker({el:'endTime',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
				</li>
				<li><input type="button" value="" class="btn_sendData" onclick="search()" />&nbsp;</li>
			</ul>
		</s:form>
	</div>
</div>

<div class="databar">
	<!--   
	<div class="btnbar">
		<ul>
				<li><a href="#" onclick="appAddPlan()"><b>添加关联</b></a></li>
				<li><a href="#" onclick="appDeletePlan()"><b>取消关联</b></a></li>
		</ul>
	</div>
	-->
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
			<th scope="col">序号</th>
			<th scope="col">方案名称</th>
			<th scope="col">生效时间</th>
			<th scope="col">失效时间</th>
			<th scope="col">默认方案</th>
			<th scope="col">状态</th>
			<th scope="col">选择应用类项</th>
			<th scope="col">操作</th>
		</tr>
		<s:iterator value="page.resultList" status="st"> 
			<tr>
				<td nowrap>
					<input type="checkbox" name="checkbox"  id="<s:property value="id"/>"  
					data-appflag="<s:property value='appFlag' />" onclick="selectChildAll()" />
				</td>			
				<td>${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
				<td><a class="detail_link" href="javascript:viewDeatil('<s:property value="id"/>')" >
						<s:if test="%{name.length() > 12}">
		               		<s:property value="%{name.substring(0, 12) + \"..\"}"/>
		               	</s:if>
		               	<s:else>
		               		<s:property value="name"/>
		                </s:else>
					</a></td>
				<td><s:date name="startTime" format="yyyy-MM-dd"/></td>
				<td><s:date name="endTime" format="yyyy-MM-dd"/></td>
				<td>
					<s:if test="%{defaulted == false}">
						<font color="black">否</font>
					</s:if>
					<s:elseif test="%{defaulted == true}">
						<font color="green">是</font>
					</s:elseif>
				</td>
				<td>
					<s:if test="%{status == 0}">
						<font color="red">未启用</font>
					</s:if>
					<s:elseif test="%{status == 1}">
						<font color="green">已启用</font>
					</s:elseif>
				</td>
				<td>
					<table id="checkbox_<s:property value='id'/>">
						<s:iterator value="itemInfo" >    
							<tr>
								<td nowrap>
							       <s:if test="%{itemFlag == 1}"> 
							       		<input type="checkbox" name="checkbox_item"  id="<s:property value='itemId'/>" data-itemflag="<s:property value='itemFlag'/>"  checked="checked"/>
							       </s:if>
									<s:elseif test="%{itemFlag == 0}">
										<input type="checkbox" name="checkbox_item"   id="<s:property value='itemId'/>"  data-itemflag="<s:property value='itemFlag'/>" />
									</s:elseif>												
								</td>		
								<td>
							       <s:property value="itemName"/>     
							       <s:if test="%{codeType == 'APP_TYPE'}"> 
							       		[应用类]
							       </s:if>
									<s:elseif test="%{codeType == 'GAME_TYPE'}">
										[游戏类]
									</s:elseif>								       
								</td>
							</tr>
						 </s:iterator>
					</table>
				</td>		
				<td class="editbar">
						<a href="#" onclick="appAddPlan('<s:property value="id"/>')" >保存关联</a>
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<!--   
	<div class="btnbar">
		<ul>
				<li><a href="#" onclick="appAddPlan()"><b>添加关联</b></a></li>
				<li><a href="#" onclick="appDeletePlan()"><b>取消关联</b></a></li>
		</ul>
	</div>
	-->
	<!-- 2011.11.03 Edit 引入分页界面 -->
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
	<!-- ended of 2011.11.03 Edit  -->
</div>
</body>
</html>
