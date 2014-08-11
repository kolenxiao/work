<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>方案列表</title>
<script type="text/javascript">

$(document).ready(function() {
	//人工排序
	$("a.sortClass").click(function() {
		$(this).hide();
		var $div = $(this).parent();
		$div.append("<input type='text' name='sortNum' maxlength='6' planId='"+$(this).attr("planId")+"' regexp='^[0-9]*[1-9][0-9]*$' size='3' valid='required|regexp' errmsg='dp_sort_required|dp_sort_regexp'/>");
	    $div.append("<input type='button' name='save' value='保存' onclick='saveSort(this)' />");
	    $div.append("<input type='button' name='cancel' value='取消' onclick='cancle(this)' style='width:34px;height:20px;border:none;color:#006DC1;padding:1px 4px;background:url(images/bg_select00.gif) no-repeat;' />");
	    $div.append("<input type='hidden' name='errMsg_sortNum' id='errMsg_sortNum' style='color: #FF0000'/>");
	});
	
});


	//分页跳转函数
	function jumpPage(no) {
		url = 'planManage!list.action?';
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'planManage!list.action?';
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'planManage!list.action?limit=' + pageSizeVal;
		formSubmit('searchForm', url);
	}

	//查询方案 
	function doQuery(){
		var form = document.forms[0];
		form.action = "planManage!list.action";
		form.submit();
	}
	
	//新增方案
	function createPlan(){
		var form = document.forms[0];
		form.action = "planManage!goCreatePlan.action";
		form.submit();
	}
	
	//修改方案
	function modify(planId){
		if(undefined == planId){
			alert("planId不能为空");
			return;
		}
		var url = "planManage!doDisplay.action?planId=" + planId;
		url = url + "&flag=modify";
		location.href = url;
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
	
	//删除方案
	function deletePlan(planId){
		if(undefined == planId){
			alert("planId不能为空");
			return;
		}
		
		if(confirm("确认删除方案？")){
			$.post("planManage!deletePlan.action",{"planIds": planId},function(data){
				if("success" == data){
					alert("操作成功");
					doQuery();
				}else{
					alert(data);
				}
			});
		}
	}
	
	//删除方案
	function batchDeletePlan(){
		var planIdArray = new Array();
		var flag = true;
		$(":checkbox[name=checkbox]:checked").each(function(i,e){
			planIdArray.push(e.value);		
			var status = $(e).attr("data_status");
			if(0 != status){
				alert("只能删除【未启用】的方案");
				flag = false;
				return false;
			}
		});
		if(true == flag){
			if(planIdArray.length < 1){
				alert("请选中要删除的方案");
				return;
			}
			if(confirm("确认删除方案？")){
				$.post("planManage!deletePlan.action",{"planIds": planIdArray.join(",")},function(data){
					if("success" == data){
						alert("操作成功");
						doQuery();
					}else{
						alert(data);
					}
				});
			}
		}
	}
	
	//启用方案
	function enabledPlan(){
		var planIdArray = new Array();
		var flag = true;
		$(":checkbox[name=checkbox]:checked").each(function(i,e){
			planIdArray.push(e.value);		
			var status = $(e).attr("data_status");
			if(0 != status){
				alert("只能选择【未启用】的方案");
				flag = false;
				return false;
			}
		});
		if(true == flag){
			if(planIdArray.length < 1){
				alert("请选中要启用的方案");
				return;
			}
			if(confirm("确认启用方案？")){
				$.post("planManage!enabledPlan.action",{"planIds": planIdArray.join(",")},function(data){
					if("success" == data){
						alert("操作成功");
						doQuery();
					}else{
						alert(data);
					}
				});
			}
		}
	}

	//禁用方案
	function disablePlan(){
		var planIdArray = new Array();
		var flag = true;
		$(":checkbox[name=checkbox]:checked").each(function(i,e){
			planIdArray.push(e.value);		
			var status = $(e).attr("data_status");
			if(1 != status){
				alert("只能选择【已启用】的方案");
				flag = false;
				return false;
			}
		});
		if(true == flag){
			if(planIdArray.length < 1){
				alert("请选中要启用的方案");
				return;
			}
			if(confirm("确认禁用方案？")){
				$.post("planManage!disablePlan.action",{"planIds": planIdArray.join(",")},function(data){
					if("success" == data){
						alert("操作成功");
						doQuery();
					}else{
						alert(data);
					}
				});
			}
		}
	}

	//设为默认方案
	function modifyDefault(){
		var planIdArray = new Array();
		var flag = true;
		$(":checkbox[name=checkbox]:checked").each(function(i,e){
			planIdArray.push(e.value);
			var status = $(e).attr("data_status");
			if(1 != status){
				alert("只能将【已启用】的方案设为默认值");
				flag = false;
				return false;
			}
			
			var defaulted = $(e).attr("data_defaulted");
			if('false' != defaulted){
				alert("只能选择【非默认】的方案");
				flag = false;
				return false;
			}
		});
		if(true == flag){
			if(planIdArray.length < 1){
				alert("请选中一个的方案");
				return;
			}
			if(planIdArray.length > 1){
				alert("只能选中一个的方案");
				return;
			}
			if(confirm("确认设置为默认方案？")){
				$.post("planManage!defaultPlan.action",{"planId": planIdArray[0]},function(data){
					if("success" == data){
						alert("操作成功");
						doQuery();
					}else{
						alert(data);
					}
				});
			}
		}
	}

	//关联类项
	function listItem(planId) {
		if(undefined == planId){
			alert("planId不能为空");
			return;
		}
		var url = "planItemManage!listSelected.action?planId=" + planId;
		location.href = url;
	}
	
	//关联条件
	function toPlanCondition(planId){
		if(undefined == planId){
			alert("planId不能为空");
			return;
		}
		openWindowDialog("condition!listForPlan.action?planId="+planId);
	}
	
	//打开窗口
	function openWindowDialog(url){
		var jsonObj = {scrollType : 'yes'};
		top.openshow(url,'方案条件关联', 1000,540, 1, jsonObj);
	}
	

	//排序
	function saveSort(e) {
		var $div = $(e).parent();
		var $sort = $div.children("input[name=sortNum]");
		var sortNum = $sort.val();
		var planId = $sort.attr("planId");
		var $sortForm = $("#"+planId+"sortForm");
		//if (validator($sortForm[0])) {
			$.ajax({
				type : "POST",
				dataType : 'json',
				url : "planManage!doSort.action",
				data : {'planId' : planId,
						'sortNum' : sortNum},
				success : function(response) {
					var dataObj = eval(response);
					if (dataObj) {
						if(true==dataObj.success){
							alert("排序成功");
							doQuery();
						}else{
							alert("排序失败");
						}
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		//}
	}
	
	//取消排序
	function cancle(e){
		var $div = $(e).parent();
		$div.children("a.sortClass").show();
		$div.children("input[name=sortNum]").remove();
		$div.children("input[name=save]").remove();
		$div.children("input[name=cancel]").remove();
		$div.children("input[name=errMsg_sortNum]").remove();
	}
	
	function showCondition(name,info,id){
		var vPosition = $("#position_"+id);
		var vPointX = vPosition.offset().left - 20;
		var vPointY = vPosition.offset().top;
		$("#condition-info-div").css({"left":vPointX,"top":vPointY});
		
		var vHtml = "<b>名称：</b>"+name+"<br/>";
		if( null == info || "" == info){
			vHtml += " <b>条件：</b>无";
		}else{
			vHtml += " <b>条件：</b><br/>";
			var arrInfo = info.split("<->");
			for(var index in arrInfo){
				vHtml += arrInfo[index] +"<br/>";
			}
		}
		$("#condition-info-div").html(vHtml);
		$("#condition-info-div").show();
	}
	
	function hideCondition(){
		$("#condition-info-div").hide();
	}
	
	function copyPlan(planId){
		$.post("planManage!copy.action",{"planId":planId},function(data){
			if("success" == data){
				dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'复制成功',0, 0, 1, this);
				$("#dialog_btn_conform").focus();
				$("#dialog_btn_conform").click(function(){
					doQuery();
				});
			}else{
				dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300, 150, '复制失败! '+data, 0,0, 1, this);
			}
		});
	}
	
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt; 方案列表</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
	<div id="search_itemlist">
		<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple">
			<s:hidden name="flag" id="flag" />
			<input type="hidden" id="limit" name="limt" value="${limit}" />
			<ul>
				<li>方案名称 <s:textfield id="name" name="plan.name" size="15"
						maxlength="64" value="%{plan.name}"
						onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li><s:text name="sdp.sce.dp.admin.ap.label.state" /> 
					<s:select list="#{'-2':'全部',0:'未启用',1:'已启用'}" name="plan.status" id="status" value="%{plan.status}">
					</s:select>
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
				<li><input type="button" value="" class="btn_sendData" onclick="doQuery()" />&nbsp;</li>
			</ul>
		</s:form>
	</div>
</div>

<div class="databar">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.createPlan!=null">
				<li><a href="#" onclick="createPlan()"><b>新增</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.enabledPlan!=null">
				<li><a href="#" onclick="enabledPlan()"><b>启用</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.disablePlan!=null">
				<li><a href="#" onclick="disablePlan()"><b>禁用</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.deletePlan!=null">
				<li><a href="#" onclick="batchDeletePlan()"><b>删除</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.defaultPlan!=null">
				<li><a href="#" onclick="modifyDefault()"><b>设为默认</b></a></li>
			</s:if>
		</ul>
	</div>
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
			<th scope="col">默认</th>
			<th scope="col">状态</th>
			<th scope="col">优先级</th>
			<th scope="col">操作</th>
		</tr>
		<s:iterator value="page.resultList" status="st"> 
			<tr>
				<td nowrap>
					<input type="checkbox" name="checkbox" value="<s:property value="id"/>" data_status="<s:property value='status' />" 
					 data_defaulted="<s:property value='defaulted' />" onclick="selectChildAll()" />
				</td>			
				<td>${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
				<td>
					<a class="detail_link" href="javascript:viewDeatil('<s:property value="id"/>')"  
					onmouseover="showCondition('<s:property value="name"/>','<s:property value="conditionInfo"/>','<s:property value="id"/>')"
					onmouseout="hideCondition();">
						<s:if test="%{name.length() > 12}">
		               		<s:property value="%{name.substring(0, 12) + \"..\"}"/>
		               	</s:if>
		               	<s:else>
		               		<s:property value="name"/>
		                </s:else>
					</a>
				</td>
				<td id="position_<s:property value='id'/>"><s:date name="startTime" format="yyyy-MM-dd"/></td>
				<td><s:date name="endTime" format="yyyy-MM-dd"/></td>
				<td>
					<s:if test="%{defaulted == false}">
						<font>否</font>
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
					<s:elseif test="%{status == -1}">
						<font color="red">已删除</font>
					</s:elseif>
				</td>
				<td><s:property value="sortNum"/></td>
				<td class="editbar">
					<s:if test="#session.userResMap.modifyPlan!=null">
						<a href="#" onclick="modify('<s:property value="id"/>')" target="mainFrame" >编辑</a>
					</s:if>
					<s:if test="#session.userResMap.deletePlan!=null">
						<s:if test="%{status != 1}">
							<a href="#" onclick="deletePlan('<s:property value="id"/>')" target="mainFrame" >删除</a>
						</s:if>
					</s:if>
					<a href="#" onclick="listItem('<s:property value="id"/>')" target="mainFrame" >关联类项</a>
					<a href="#" onclick="toPlanCondition('<s:property value="id"/>')" target="mainFrame" >关联条件</a>	
					<a href="#" onclick="copyPlan('<s:property value="id"/>')" target="mainFrame" >复制方案</a>
					<s:form method="post" id="%{id}sortForm" name="%{id}sortForm" theme="simple">
						<div class='sortDiv'>
							<a class="sortClass" href="#" planId="<s:property value='id'/>">设定优先级</a>
						</div>
					</s:form>	
				</td>
			</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.createPlan!=null">
				<li><a href="#" onclick="createPlan()"><b>新增</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.enabledPlan!=null">
				<li><a href="#" onclick="enabledPlan()"><b>启用</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.disablePlan!=null">
				<li><a href="#" onclick="disablePlan()"><b>禁用</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.deletePlan!=null">
				<li><a href="#" onclick="batchDeletePlan()"><b>删除</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.defaultPlan!=null">
				<li><a href="#" onclick="modifyDefault()"><b>设为默认</b></a></li>
			</s:if>
		</ul>
	</div>

	<!-- 2011.11.03 Edit 引入分页界面 -->
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
	<!-- ended of 2011.11.03 Edit  -->
</div>
<div id="condition-info-div" style="position:absolute;top:100px;left:100px;background-color:lightblue;padding:5px;5px;5px;5px;">
</div>
</body>
</html>
