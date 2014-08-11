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
		url = 'dpAppInfo!doSearchOnOrOffList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'dpAppInfo!doSearchOnOrOffList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'dpAppInfo!doSearchOnOrOffList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	//查询应用
	function searchApp(startTimeId, endTimeId) {
		url = 'dpAppInfo!doSearchOnOrOffList.action?';
		//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
		querydata('searchForm', url, startTimeId, endTimeId);
	}

	//显示应用详细信息
	function viewDeatil(id) {
		location.href = 'dpAppInfo!doDisplay.action?forward=detail&appInfo.id='
				+ id;
	}

	//上下架
	function onOrOffLineOpt(obj, id, appName,status) {
		var title ="应用上架";
		var msg='确认"'+ appName +'"应用提交上架吗?';
		if(status=='0')
		{
			title="应用下架";
			var msg='确认"'+ appName +'"应用提交下架吗?';
		}

		dialogList(title,300,150,msg,0, 0, 2, this);

		//给出提示框
		$("#dialog_btn_conform").click(
			function(){
				url = 'dpAppInfo!doOnOrDownline.action?';
				document.getElementById("appInfo.id").value = id;
				document.getElementById("onLineFlag").value = status;
				document.getElementById("flag").value = 1;
				goUrlPage('searchForm', url, '${page.currentPage}');
			}
		);
	}
	
	function bachOnOrOffLineOpt(status) {
		var title ="应用上架";
		var msg='确认提交上架吗?';
		if(status=='0')
		{
			title="应用下架";
			var msg='确认提交下架吗?';
		}
		
		var idArray = new Array();//定义元素数组
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;//获取元素个数
		
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked)
				idArray.push(childCheckboxs[i].id);//添加被选择的元素到数组
		}
		
		if (idArray.length > 0) {
			var idVal = eval(idArray);
			dialogList(title,300,150,msg,0, 0, 2, this);
			
			//给出提示框
			$("#dialog_btn_conform").click(
				function(){
					url = 'dpAppInfo!doBatchOnOrDownline.action?';
					document.getElementById("ids").value = idVal;
					document.getElementById("onLineFlag").value = status;
					document.getElementById("flag").value = 1;
					goUrlPage('searchForm', url, '${page.currentPage}');
				}
			);
		}else{
			dialogList(title,300,150,'至少选择一条记录',0, 0, 2, this);
		}
	}
	
	
$(document).ready(function() {
	$("a.sortClass").click(function() {
		$(this).hide();
		var $div = $(this).parent();
		$div.append("<input type='text' name='sortNum' maxlength='6' appId='"+$(this).attr("appId")+"' regexp='^[0-9]*[1-9][0-9]*$' size='3' valid='required|regexp' errmsg='dp_sort_required|dp_sort_regexp'/>");
	    $div.append("<input type='button' name='save' value='保存' onclick='saveSort(this)' />");
	    $div.append("<input type='button' name='cancel' value='取消' onclick='cancle(this)' style='width:34px;height:20px;border:none;color:#006DC1;padding:1px 4px;background:url(images/bg_select00.gif) no-repeat;' />");
	    $div.append("<input type='hidden' name='errMsg_sortNum' id='errMsg_sortNum' style='color: #FF0000'/>");
	});

})


//自定义输出错误信息
FormValid.showError = function(errMsg,errName,formName) {
		for (key in FormValid.allName) {
			document.getElementById('errMsg_'+FormValid.allName[key]).innerHTML = '';
		}
		for (key in errMsg) {
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300, 150, errMsg[key], 0,0, 1, this);
		}
};

function saveSort(e) {
	var $div = $(e).parent();
	var $sort = $div.children("input[name=sortNum]");
	var sortNum = $sort.val();
	var appId = $sort.attr("appId");
	var $sortForm = $("#"+appId+"sortForm");
	initValid($sortForm[0]);
	if (validator($sortForm[0])) {
		$.ajax({
			type : "POST",
			dataType : 'json',
			url : "dpAppInfo!doSort.action",
			data : {'appId' : appId,
					'sortNum' : sortNum},
			success : function(response) {
				var dataObj = eval(response);
				if (dataObj) {
					if(true==dataObj.success){
						alert("排序成功");
						$("#button_search").click();
					}else{
						alert("排序失败");
					}
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}

}

function cancle(e){
	var $div = $(e).parent();
	$div.children("a.sortClass").show();
	$div.children("input[name=sortNum]").remove();
	$div.children("input[name=save]").remove();
	$div.children("input[name=cancel]").remove();
	$div.children("input[name=errMsg_sortNum]").remove();
}


//关联条件
function toPlanList(appId){
	if(undefined == appId){
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,"应用id不能为空",0, 0, 1, this);
		return;
	}
	openWindowDialog("planManage!listForApp.action?appId="+appId);
}

//打开窗口
function openWindowDialog(url){
	var jsonObj = {scrollType : 'yes'};
	top.openshow(url,'方案应用关联', 1000,540, 1, jsonObj);
}
	
	
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt; 应用上架</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<s:hidden name="appInfo.id" id="appInfo.id"/>
			<s:hidden name="onLineFlag" id="onLineFlag"/>
			<s:hidden name="flag" id="flag"/>
			<s:hidden name="ids" id="ids"/>
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
								name="queryAppInfo.appName" size="12" maxlength="64"
								value="%{queryAppInfo.appName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>

				<li><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" />:
					<s:textfield id="queryAppInfo.dpStaff.userName"
								name="queryDpStaff.userName" size="12" maxlength="64"
								value="%{queryDpStaff.userName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
				</li>
				<li><s:text name="sdp.sce.dp.admin.ap.label.state" />
					<s:select list="#{'':'全部',1003:getText('sdp.sce.dp.admin.dpstaff.audit.pass'),1004:'已上架',1005:'已下架'}"  name="queryAppInfo.appStatus" id="queryAppInfo.appStatus" value="%{queryAppInfo.appStatus}">
					</s:select>

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
					<input type="button" id="button_search" value="" class="btn_sendData"  onclick="searchApp('queryAppInfo.beginAppInfoCTime','queryAppInfo.endAppInfoCTime')" />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
</div>

<div class="databar">
	<div class="btnbar">
		<ul>
            <li><a href="#" onclick="bachOnOrOffLineOpt(1)"><b>批量上架</b></a></li>
             <li><a href="#" onclick="bachOnOrOffLineOpt(0)"><b>批量下架</b></a></li>
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
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.appName" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.portal.ap.classfily" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
 			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.state" /></th>
			<th scope="col">排序</th>
			<th width="140" class="editbar" scope="col"><s:text name="sdp.sce.dp.admin.global.label.operate" /></th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
		<tr>
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="<s:property value='id' />" onclick="selectChildAll()" />
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
					<font color="green">版本更新</font>
				</s:elseif>
			</td>
			<td><s:property value="sortNum"/></td>
			<td class="editbar">
			<s:if test="#session.userResMap.appOnline!=null">
				<a href="dpAppInfo!doDisplay.action?forward=modifyOnline&appInfo.id=<s:property value='id'/>">在线修改</a>
				<!-- 审核通过 -->
				<s:if test="%{appStatus == '1003'}">
					<!-- 上架操作 -->
					<a href="#" onclick="onOrOffLineOpt(this,'<s:property value="id"/>','<s:property value="appName"/>','1')"	target="mainFrame" ><s:text name="sdp.sce.dp.admin.log.shelves.operate" /></a>
				</s:if>
				<!-- 已上架 -->
				<s:elseif test="%{appStatus == '1004'}">
					<a href="javascript:onOrOffLineOpt(this,'<s:property value="id"/>','<s:property value="appName"/>','0')" target="mainFrame" ><s:text name="sdp.sce.dp.admin.log.downline.operate" /></a>
					<a href="javascript:toPlanList('<s:property value="id"/>')" target="mainFrame" >关联方案</a>
				</s:elseif>				
				<!-- 已下架 -->
				<s:elseif test="%{appStatus == '1005'}">
					<!-- 上架操作 -->
					<a href="#" onclick="onOrOffLineOpt(this,'<s:property value="id"/>','<s:property value="appName"/>','1')"	target="mainFrame" ><s:text name="sdp.sce.dp.admin.log.shelves.operate" /></a>
				</s:elseif>
			</s:if>
			<s:form method="post" id="%{id}sortForm" name="%{id}sortForm" theme="simple">
				<div class='sortDiv'>
					<a class="sortClass" href="#" appId="<s:property value='id'/>">人工排序</a>
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
            <li><a href="#" onclick="bachOnOrOffLineOpt(1)"><b>批量上架</b></a></li>
             <li><a href="#" onclick="bachOnOrOffLineOpt(0)"><b>批量下架</b></a></li>
		</ul>
	</div>
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
