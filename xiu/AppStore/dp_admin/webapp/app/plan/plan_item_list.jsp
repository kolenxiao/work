<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>版块下的元素列表</title>
<script type="text/javascript">

$(document).ready(function() {
	//人工排序
	$("a.sortClass").click(function() {
		$(this).hide();
		var $div = $(this).parent();
		$div.append("<input type='text' name='sortNum' maxlength='6' planItemId='"+$(this).attr("planItemId")+"' regexp='^[0-9]*[1-9][0-9]*$' size='3' valid='required|regexp' errmsg='dp_sort_required|dp_sort_regexp'/>");
	    $div.append("<input type='button' name='save' value='保存' onclick='saveSort(this)' />");
	    $div.append("<input type='button' name='cancel' value='取消' onclick='cancle(this)' style='width:34px;height:20px;border:none;color:#006DC1;padding:1px 4px;background:url(images/bg_select00.gif) no-repeat;' />");
	    $div.append("<input type='hidden' name='errMsg_sortNum' id='errMsg_sortNum' style='color: #FF0000'/>");
	});
})


	//刷新本页面
	function doRefresh(){
		var searchForm = $("#searchForm").get(0);
		searchForm.action = "planItemManage!listSelected.action";
		searchForm.submit();
	}
	
	//返回
	function doReturn(){
		var url = "planManage!goList.action";
		location.href = url;
	}

	//删除关联类项
	function deletePlanItem(operFlag){
		var checkboxName = "";
		if("deletePanel" == operFlag){
			checkboxName = "checkboxPanel";
		}else if("deleteSubject" == operFlag){
			checkboxName = "checkboxSubject";
		}else if("deleteDpType" == operFlag){
			checkboxName = "checkboxDpType";
		}
		
		var planItemIdArray = new Array();
		$(":checkbox[name="+checkboxName+"]:checked").each(function(i,e){
			planItemIdArray.push(e.value);		
		});
		if(planItemIdArray.length < 1){
			alert("请选中要取消的记录");
			return;
		}
		if(confirm("确认取消关联？")){
			$.post("planItemManage!deletePlanItem.action",{"planItemIds": planItemIdArray.join(",")},function(data){
				if("success" == data){
					alert("操作成功");
					doRefresh();
				}else{
					alert("操作失败");
				}
			});
		}
	}

	//新增关联版块
	function addPanel(){
		var action = "planItemManage!listUnSelectedPanel.action?planId=${planId}";
		var jsonObj = {
			scrollType : 'yes'
		};

		top.openshow(action, '', 1000, 540, 1, jsonObj);
	}

	//新增关联专题
	function addSubject(){
		action = "planItemManage!listUnSelectedSubject.action?planId=${planId}";
		var jsonObj = {
			scrollType : 'yes'
		};

		top.openshow(action, '',  1000, 540, 1, jsonObj);
	}

	//新增关联固定分类
	function addDpType(){
		action = "planItemManage!listUnSelectedDpType.action?planId=${planId}";
		var jsonObj = {
			scrollType : 'yes'
		};

		top.openshow(action, '',  1000, 540, 1, jsonObj);
	}
	
	//新增关联自定义分类
	function addSelfClass(){
		action = "planItemManage!listUnSelectedSelf.action?planId=${planId}";
		var jsonObj = {
			scrollType : 'yes'
		};

		top.openshow(action, '',  1000, 540, 1, jsonObj);
	}
	
	//分类关联应用
	function selectClassApp(planItemId){
		action = "planItemAppManage!listSelectedApp.action?planId=${planId}&planItemId="+planItemId;
		var jsonObj = {
			scrollType : 'yes'
		};

		top.openshow(action, '',  1200, 640, 1, jsonObj);
	}

	//排序
	function saveSort(e) {
		var $div = $(e).parent();
		var $sort = $div.children("input[name=sortNum]");
		var sortNum = $sort.val();
		var planItemId = $sort.attr("planItemId");
		var $sortForm = $("#"+planItemId+"sortForm");
		//if (validator($sortForm[0])) {
			$.ajax({
				type : "POST",
				dataType : 'json',
				url : "planItemManage!doSort.action",
				data : {'planItemId' : planItemId,
						'sortNum' : sortNum},
				success : function(response) {
					var dataObj = eval(response);
					if (dataObj) {
						if(true==dataObj.success){
							alert("排序成功");
							doRefresh();
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
	
	//全选
	function selectAllCheckbox(obj, checkboxName) {
		$('input:checkbox[name=' + checkboxName + ']').attr("checked",obj.checked);
	}


</script>
</head>

<body id="cnt_body">

<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/>方案管理&gt;方案关联类项管理</p>
  <div id="pright"></div>
</div>



<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple">
	<s:hidden name="planId" id="planId" />
	<input type="hidden" id="limit" name="limt" value="${limit}" />
</s:form>


<div class="databar">
	<div class="btnbar">
		<ul>
            <li><a href="#" onclick="doRefresh()"><b>刷新页面</b></a></li>
            <li><a href="#" onclick="doReturn()"><b>返回</b></a></li>
		</ul>
	</div>
</div>

</br>
<div class="view_nav">
	首页版块列表
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox1" id="checkboxAll" onclick="selectAllCheckbox(this, 'checkboxPanel')" />
					</label>
				</span>
			</th>
			<th scope="col">序号</th>
			<th scope="col">版块名称</th>
			<th scope="col">布局类型</th>
			<th scope="col">当前顺序</th>
			<th width="200" scope="col">操作</th>
		</tr>
  		<s:iterator value="result.planItemPanelList" status="st">
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkboxPanel" value='<s:property value="id"/>' onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td><s:if test="%{appRecommendPanel.panelName.length() > 12}">
               		<s:property value="%{appRecommendPanel.panelName.substring(0, 12) + \"..\"}"/>
               	</s:if>
               	<s:else>
               		<s:property value="appRecommendPanel.panelName"/>
                </s:else></td>
			<td>
				<s:if test="%{appRecommendPanel.layoutType == 1}">栏位</s:if>
				<s:else>入口</s:else>
			</td>
            <td><s:property value="sortNum"/></td>
			<td class="editbar">				
				<s:form method="post" id="%{id}sortForm" name="%{id}sortForm" theme="simple">
					<div class='sortDiv'>
						<a class="sortClass" href="#" planItemId="<s:property value='id'/>">人工排序</a>
					</div>
				</s:form>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<s:if test="#session.userResMap.addPlanItem!=null">
	<div class="databar">
		<div class="btnbar">
			<ul>
	            <li><a href="#" onclick="deletePlanItem('deletePanel')"><b>取消版块</b></a></li>
	            <li><a href="#" onclick="addPanel()"><b>关联版块</b></a></li>
			</ul>
		</div>
	</div>
</s:if>



</br>
</br>
<div class="view_nav">
	专题列表
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox1" id="checkboxAll" onclick="selectAllCheckbox(this, 'checkboxSubject')" />
					</label>
				</span>
			</th>
			<th scope="col">序号</th>
			<th scope="col">专题名称</th>
			<th scope="col">状态</th>
			<th scope="col">当前顺序</th>
			<th width="200" scope="col">操作</th>
		</tr>
  		<s:iterator value="result.planItemSubjectList" status="st">
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkboxSubject" value='<s:property value="id"/>' onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td><s:if test="%{appSubjectType.subjectName.length() > 12}">
               		<s:property value="%{appSubjectType.subjectName.substring(0, 12) + \"..\"}"/>
               	</s:if>
               	<s:else>
               		<s:property value="appSubjectType.subjectName"/>
                </s:else></td>
            <td><s:if test="%{appSubjectType.visibleFlag == 1}">显示</s:if>
				<s:elseif test="%{appSubjectType.visibleFlag == 2}"><font color="red">隐藏</font></s:elseif></td>
            <td><s:property value="sortNum"/></td>
			<td class="editbar">				
				<s:form method="post" id="%{id}sortForm" name="%{id}sortForm" theme="simple">
					<div class='sortDiv'>
						<a class="sortClass" href="#" planItemId="<s:property value='id'/>">人工排序</a>
					</div>
				</s:form>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<s:if test="#session.userResMap.addPlanItem!=null">
	<div class="databar">
		<div class="btnbar">
			<ul>
	            <li><a href="#" onclick="deletePlanItem('deleteSubject')"><b>取消专题</b></a></li>
	           <li><a href="#" onclick="addSubject()"><b>关联专题</b></a></li>
			</ul>
		</div>
	</div>
</s:if>



</br>
</br>
<div class="view_nav">
	分类列表
</div>
<div class="data_list">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  		<tr>
 			 <th width="40" scope="col">
  				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox1" id="checkboxAll" onclick="selectAllCheckbox(this, 'checkboxDpType')" />
					</label>
				</span>
			</th>
			<th scope="col">序号</th>
			<th scope="col">分类名称</th>
			<th scope="col">上级分类</th>
			<th scope="col">关联应用数量</th>
			<th scope="col">当前顺序</th>
			<th width="200" scope="col">操作</th>
		</tr>
  		<s:iterator value="result.planItemClassList" status="st">
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkboxDpType" value='<s:property value="id"/>' onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td><s:if test="%{itemType == 3}">
					<s:property value="dpType.typeName"/>
				</s:if>
				<s:else>
               		<s:property value="item.name"/>
                </s:else></td>
            <td><s:if test="%{itemType == 3}">
            		<s:if test="%{dpType.parentTypeCode == 'APP_TYPE'}">应用</s:if>
					<s:else>游戏</s:else>
				</s:if>
				<s:elseif test="%{itemType == 4}">
					<s:if test="%{item.parentTypeCode == 'APP_TYPE'}">应用</s:if>
	            	<s:elseif test="%{item.parentTypeCode == 'GAME_TYPE'}">游戏</s:elseif>
					<s:else>无</s:else>
				</s:elseif>
				</td>
			<td><s:property value="appNum"/></td>
            <td><s:property value="sortNum"/></td>
			<td class="editbar">				
				<a href="#" onclick="selectClassApp('<s:property value="id"/>')" target="mainFrame" >关联应用</a>
				<s:form method="post" id="%{id}sortForm" name="%{id}sortForm" theme="simple">
					<div class='sortDiv'>
						<a class="sortClass" href="#" planItemId="<s:property value='id'/>">人工排序</a>
					</div>
				</s:form>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<s:if test="#session.userResMap.addPlanItem!=null">
	<div class="databar">
		<div class="btnbar">
			<ul>
	        	<li><a href="#" onclick="deletePlanItem('deleteDpType')"><b>取消分类</b></a></li>
	        	<!-- 
	            <li><a href="#" onclick="addDpType()"><b>新增固定分类</b></a></li>
	             -->
	            <li><a href="#" onclick="addSelfClass()"><b>关联分类</b></a></li>
			</ul>
		</div>
	</div>
</s:if>





<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
