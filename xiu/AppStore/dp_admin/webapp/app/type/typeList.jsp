<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.news.newsType" /></title>
<style type="text/css">
    .p1{ word-break:break-all; width:630px;}
   .p2{white-space:normal;word-wrap:break-word; word-break:break-all;width:220px;}
</style>
<script type="text/javascript">

//分页跳转函数
function jumpPage(no) {
	url = 'dpType!doList.action?';
	jumpPageTo('searchForm', url, no);
}

//上下页显示
function page(start) {
	url = 'dpType!doList.action?';
	goUrlPage('searchForm', url, start);
}

//每页显示多少条函数
function doChange(obj) {

	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'dpType!doList.action?limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}

//添加分类
function showSpecPage()
{
	var jsonObj = {
		scrollType : 'yes'
	};
	location.href = 'dpType!doDisplay.action?forward=add';
}

//修改分类
	function edit(id){
	var jsonObj = {
			scrollType : 'yes'
		};
	location.href = 'dpType!doDisplay.action?forward=edit&dpType.id='+id;
}

	// 更改显示状态
	function changeVisibleState(vFlag, id) {

		var pageSizeVal = document.getElementById("pageSize").value;
		var currentPage = document.getElementById("currentPage").value;

		//把字符串数组转换成字符串
		var parm = {
			'dpType.visibleFlag':vFlag,
			'dpType.id':id
		};
		$("#dialog").remove();
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				'确认要修改分类的显示状态吗？',
				0, 0, 2, this);
		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform")
			.click(
				function() {
					$.ajax({cache : false,
							async : false,
							url : 'dpType!doChangeVisibleStateType.action',
							type : 'POST',
							data : parm,
							dataType : 'json',
							error : function(msg) {
								$("#dialog").remove();
								dialogList(
										'<s:text name="sdp.sce.dp.admin.global.inform" />',
										300, 150, 'Error', 0,
										0, 1, this);
							},
							success : function(response) {
								$("#dialog").remove();
								var dataObj = eval(response);
								if (dataObj && dataObj.success) {
									searchDpType();
								} else if (dataObj
										&& dataObj.exception) {
									dialogList(
										'<s:text name="sdp.sce.dp.admin.global.inform" />',
										300, 150,
										dataObj.exception,
										0, 0, 1, this);
								} else {
									dialogList(
										'<s:text name="sdp.sce.dp.admin.global.inform" />',
										300,
										150,
										'<s:text name="sdp.sce.dp.admin.dptype.type.bind.newsOrdownload" />',
										0, 0, 1, this);
								}

							}
						});
					});
	}

// 显示分类详细信息
function viewDeatil(id)
{
	location.href = 'dpType!doDisplay.action?forward=detail&dpType.id='+id;
}

//删除分类
function del() {
	var idArray = new Array();
	var pageSizeVal = document.getElementById("pageSize").value;
	var childCheckboxs = document.getElementsByName("checkbox");
	var c_length = childCheckboxs.length;
	for ( var i = 0; i < c_length; i++) {
		if (childCheckboxs[i].checked)
			idArray.push(childCheckboxs[i].id);
	}

	if (idArray.length > 0) {
		//把字符串数组转换成字符串
		var idVal = eval(idArray);
		var parm ={'ids':idVal};
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'<s:text name="sdp.sce.dp.admin.global.del.data.confirm" />',0,0,2,
				this);
		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform").click(function()
				  {
			$.ajax({
			    url: 'dpType!doDelete.action',
			    type: 'POST',
			    data: parm,
			    dataType: 'json',
			    error: function(msg){
			    	 dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'Error',0,0,1,this);
			    },
			    success: function(response){
			    	$("#dialog").remove();
			    	var dataObj = eval(response);
					if (dataObj && dataObj.success) {
			    		location.href = "dpType!doList.action?&limit="+pageSizeVal;
					}else if (dataObj && dataObj.msg)
					{
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,dataObj.msg,0,0,1,this);
					}else if (dataObj && dataObj.exception)
					{
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,dataObj.exception,0,0,1,this);
					}
					else {
						dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'<s:text name="sdp.sce.dp.admin.dptype.type.bind.newsOrdownload" />',0,0,1,this);
					}
			    }
			  });
				  });



	} else {
		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'<s:text name="sdp.sce.dp.admin.global.select.del.data" />',0,0,1,this);
	}
}

function searchDpType()
{
	url = 'dpType!doList.action?';
	goUrlPage('searchForm', url, 1);
}


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
	var typeId = $sort.attr("typeId");
	var $sortForm = $("#"+typeId+"sortForm");
	initValid($sortForm[0]);
	if (validator($sortForm[0])) {
		$.ajax({
			type : "POST",
			dataType : 'json',
			url : "dpType!doSort.action",
			data : {'typeId' : typeId,
					'sortNum' : sortNum},
			success : function(response) {
				var dataObj = eval(response);
				if (dataObj) {
					if(true==dataObj.success){
						alert("排序成功");
						searchDpType();
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



$(document).ready(function() {
	$("a.sortClass").click(function() {
		$(this).hide();
		var $div = $(this).parent();
		$div.append("<input type='text' name='sortNum' maxlength='4' typeId='"+$(this).attr("typeId")+"' regexp='^[0-9]*[1-9][0-9]*$' size='3' valid='required|regexp' errmsg='dp_sort_required|dp_sort_regexp'/>");
	    $div.append("<input type='button' name='save' value='保存' onclick='saveSort(this)' />");
	    $div.append("<input type='button' name='cancel' value='取消' onclick='cancle(this)' style='width:34px;height:20px;border:none;color:#006DC1;padding:1px 4px;background:url(images/bg_select00.gif) no-repeat;' />");
	    $div.append("<input type='hidden' name='errMsg_sortNum' id='errMsg_sortNum' style='color: #FF0000'/>");
	});

})
</script>
</head>
<body id="cnt_body">
	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="dpType!doList.action" method="post" cssStyle="margin:0" theme="simple" >
				<ul>
					<li><s:text name="sdp.sce.dp.admin.dptype.nametype" />
						<s:textfield id="dpTypeQueryInfo.typeName"
									name="dpTypeQueryInfo.typeName" size="15" maxlength="64"
									value="%{dpTypeQueryInfo.typeName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
					</li>

					<li>所属类别
					<s:select list="dpTypeList" headerValue="所有分类" headerKey ="" size="1" select="selected"
						name="dpTypeQueryInfo.parentTypeCode" id="dpTypeQueryInfo.parentTypeCode"
						listKey="typeCode" listValue="typeName" value="%{dpTypeQueryInfo.typeCode}">
					</s:select>
					</li>
					<li>显示状态
					<s:select
						list="#{0:'全选',1:'显示',2:'隐藏'}"
						id="dpTypeQueryInfo.visibleFlag"
						name="dpTypeQueryInfo.visibleFlag"
						value="%{dpTypeQueryInfo.visibleFlag}">
					</s:select>
					</li>
					<li>
						<input type="submit" value="" class="btn_sendData"  onclick="searchDpType();" />&nbsp;
					</li>
				</ul>
			</s:form>
		</div>
	</div>
	<div id="position">
		<p><s:text name="sdp.sce.dp.admin.dptype.pro" />: <s:text name="sdp.sce.dp.admin.system" />&gt; <s:text name="sdp.sce.dp.admin.dptype.categorylist" /></p>
		<div id="pright"></div>
	</div>
	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.addType != null">
				<li><a
					href="#" onclick="showSpecPage()"
					target="mainFrame"><b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b> </a>
				</li>
				</s:if>
				<s:if test="#session.userResMap.deleteType != null">
				<li><a href="#" onclick="del()"><b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b> </a>
				</li>
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
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th width="150"   scope="col"><s:text name="sdp.sce.dp.admin.dptype.nametype" /></th>
			<th width="150"   scope="col">所属类别</th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsCreater" /></th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
			<th  scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
			<th width="150"   scope="col">排序</th>
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
					<td class="p2">
						<a class="detail_link" href="javascript:viewDeatil('<s:property value="id"/>')" >
							<s:property value="typeName" />
						</a>
					</td>
					<td>${page.resultList[st.index].parentTypeName }</td>
					<td><s:property value="createUser"></s:property></td>
					<td><s:date name="createDate" format="yyyy-MM-dd"></s:date></td>
					<td><s:date name="updateDate" format="yyyy-MM-dd"></s:date></td>
					<td><s:property value="sortNum"></s:property></td>
					<td class="editbar">
						<s:if test="#session.userResMap.gotoEditType != null">
							<a href="javascript:edit('<s:property value="id"/>')" 	target="mainFrame" ><s:text name="sdp.sce.dp.admin.global.btn.edit" /></a>
							&nbsp;&nbsp;
						</s:if>
						<s:else>
							<font color="#858585"> <s:property value="%{getText('sdp.sce.dp.admin.global.btn.edit')}"/></font>
						</s:else>
						<s:if test="%{visibleFlag == 1}">
							<a href="javascript:changeVisibleState(2, '<s:property value="id"/>')" 	target="mainFrame" >隐藏</a>
							&nbsp;&nbsp;
						</s:if>
						<s:elseif test="%{visibleFlag == 2}">
							<a href="javascript:changeVisibleState(1, '<s:property value="id"/>')" 	target="mainFrame" >显示</a>
							&nbsp;&nbsp;
						</s:elseif>
						<s:form method="post" id="%{id}sortForm" name="%{id}sortForm" theme="simple">
							<div class='sortDiv'>
								<a class="sortClass" href="#" typeId="<s:property value='id'/>">人工排序</a>
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
				<s:if test="#session.userResMap.addType != null">
				<li><a
					href="#" onclick="showSpecPage()"
					target="mainFrame"><b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b> </a>
				</li>
				</s:if>
				<s:if test="#session.userResMap.deleteType != null">
				<li><a href="#" onclick="del()"><b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b> </a>
				</li>
				</s:if>
			</ul>
		</div>
		<jsp:include page="/common/nextpage.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
