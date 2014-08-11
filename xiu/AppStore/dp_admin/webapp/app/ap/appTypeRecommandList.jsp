<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用推荐管理</title>
<script type="text/javascript">

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

	//分页跳转函数
	function jumpPage(no) {

		url = 'appTypeRecommend!doSearchAppTypeRecommendList.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'appTypeRecommend!doSearchAppTypeRecommendList.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {

		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'appTypeRecommend!doSearchAppTypeRecommendList.action?limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}

	//批量取消推荐应用
	function delRecommend() {
		var idArray = new Array();
		var pageSizeVal = document.getElementById("pageSize").value;
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;
		var currentPage = document.getElementById("currentPage").value;
		
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked)
				idArray.push(childCheckboxs[i].id);
		}
		if (idArray.length > 0) {
			//把字符串数组转换成字符串
			var idVal = eval(idArray);
			var parm = {
				'ids' : idVal
			};
			$("#dialog").remove();
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300,
					150,
					'确定取消推荐这些应用？',
					0, 0, 2, this);
			//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行取消推荐操作
			$("#dialog_btn_conform").click(
				function(){
						$.ajax({
								cache : false,
								async : false,
								url : 'appTypeRecommend!doBatchCommend.action',
								type : 'POST',
								data : parm,
								dataType : 'json',
								error : function(msg) {
									$("#dialog").remove();
									dialogList(
											'<s:text name="sdp.sce.dp.admin.global.inform" />',
											300, 150, '发送请求出错，请联系管理人员', 0,0, 1, this);
								},
								success : function(response) {
									$("#dialog").remove();
									var dataObj = eval(response);

									if (dataObj && dataObj.success) {
										var totalCount = idArray.length;
										if(c_length == totalCount) {
											currentPage = (currentPage-1) < 0 ? 1 : (currentPage-1);
										}
										location.href = "appTypeRecommend!doSearchAppTypeRecommendList.action?queryAppInfo.dpType.id=${queryAppInfo.dpType.id}&limit="
												+ pageSizeVal+"&start="+currentPage;
									} else if (dataObj
											&& dataObj.msg) {
										dialogList(
												'<s:text name="sdp.sce.dp.admin.global.inform" />',
												300, 150,
												dataObj.msg, 0, 0,
												1, this);
												$("#dialog_btn_conform").click(
														function() {
															location.href = "appTypeRecommend!doSearchAppTypeRecommendList.action?queryAppInfo.dpType.id=${queryAppInfo.dpType.id}&limit="
																	+ pageSizeVal;
												});
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

		} else {
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300,
					150,
					'请选择需要取消推荐的应用',
					0, 0, 1, this);
		}
	}

	//点击审核按钮
	function auditOpt(obj, id) {
		var jsonObj = {
			scrollType : 'yes'
		};
		location.href = 'dpAppInfo!doDisplay.action?forward=audit&appInfo.id='
				+ id + "&flag=1";//flag = 0为从未审核进入，1：为从全部应用进入;
	}

	function searchApp(startTimeId, endTimeId) {

		url = 'appTypeRecommend!doSearchAppTypeRecommendList.action?';
		//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
		querydata('searchForm', url, startTimeId, endTimeId);
	}

	//显示应用详细信息
	function viewDeatil(id) {
		location.href = 'dpAppInfo!doDisplay.action?forward=detail&appInfo.id='
				+ id;
	}

	//commendFlag=1：推荐；commendFlag=0：取消推荐
	function commend(obj, id, commendFlag) {
		location.href = 'appTypeRecommend!doCommend.action?appInfo.id=' + id
				+ '&commendFlag=' + commendFlag+'&queryAppInfo.dpType.id=${queryAppInfo.dpType.id}';
	}

	function addRecommend()
	{
		action = "dpAppInfo!toAddTypeRecommentList.action?queryAppInfo.dpType.id=${queryAppInfo.dpType.id}";
		var jsonObj = {
			scrollType : 'yes'
		};

		top.openshow(action,
				'应用列表', 900,
		440, 1, jsonObj);
	}

	function createInput(parentID,index){
		var d = "#"+parentID;
	    $(d + " a").hide();
		var id = parentID;
	    $("div[id='"+parentID+"']").append("<input type='text' id='sort' paramId='"+id+"' regexp='^[0-9]*[1-9][0-9]*$' name='sort' size='3' valid='required|regexp' errmsg='dp_sort_required|dp_sort_regexp'/>");
	    $("div[id='"+parentID+"']").append("<input type='button' id='button' paramId='"+id+"' onclick='saveSort(this)' name='button' value='保存' />");
	    $("div[id='"+parentID+"']").append("<input type='button' id='button' paramId='"+id+"' onclick='cancle(this)' name='button' value='取消' style='width:34px;height:20px;border:none;color:#006DC1;padding:1px 4px;background:url(images/bg_select00.gif) no-repeat;' />");
	    $("div[id='"+parentID+"']").append("<input type='hidden' id='errMsg_sort' style='color: #FF0000'/>");
	    $("div[id='"+parentID+"']").append("<input type='hidden' id='index' name='index' value='"+index+"'/>");
	    $("div[id='"+parentID+"']").append("<input type='hidden' id='queryAppInfo.dpType.id' name='queryAppInfo.dpType.id' value='${queryAppInfo.dpType.id}'/>");
	}

	function cancle(inputE){
		var id = $(inputE).attr("paramId");
		var d = "#" + id;
		$(d + " a").show();

		$("input[paramId='" + id + "']").remove();
		$("span[id='errMsg_sort']").remove();
	}

	function saveSort(inputE) {
		var id = $(inputE).attr("paramId");
		var formId = id +"sortForm";
		var sortForm = document.getElementById(formId);

		initValid(sortForm);
		if (validator(sortForm)) {
			$("#"+formId).submit();
		}
	}

	function removeInput(parentID,DelDivID){
		var parent=document.getElementById(parentID);
	 	parent.removeChild(document.getElementById(DelDivID));
	}


	function backToList(){
		window.location="dpType!doSearchRecommendList.action";
	}

</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt; 应用推荐分类&gt;${dpTypeQueryInfo.typeName}</p>
  <div id="pright"></div>
</div>

<div id="searchbar">
  <div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
			<input type="hidden" id="limit" name="limt" value="${limit}"/>
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
					<input type="button" value="" class="btn_sendData"  onclick="searchApp('queryAppInfo.beginAppInfoCTime','queryAppInfo.endAppInfoCTime')" />&nbsp;
				</li>
			</ul>
			</s:form>
		</div>
</div>

<div class="databar">
	<div class="btnbar">
		<ul>
			<s:if test="#session.userResMap.cancleAppInTypeRecommend!=null">
				<li><a href="#" onclick="delRecommend()"><b>取消推荐</b></a></li>
			</s:if>
			<s:if test="#session.userResMap.searchAppInTypeRecommend!=null">
			<li><a href="#" onclick="addRecommend();"><b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b></a></li>
			</s:if>
		</ul>
	</div>
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
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.appName" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.portal.ap.classfily" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.label.dpstaff" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
 			<th scope="col"><s:text name="推荐时间" /></th>
			<th scope="col"><s:text name="sdp.sce.dp.admin.ap.state" /></th>
			<th width="140" class="editbar" scope="col"><s:text name="sdp.sce.dp.admin.global.label.operate" /></th>
			<th width="140" scope="col">自定义排名</th>
		</tr>
  		<s:iterator value="page.resultList" status="st"  >
		<tr >
			<td>
				<span id="sprycheckbox1">
					<label>
						<input type="checkbox" name="checkbox" id="<s:property value='dpAppInfo.id' />" onclick="selectChildAll()" />
					</label>
				</span>
			</td>
			<td width="50">${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
			<td>
				<!-- 查看应用详情超链接 -->
				<a class="detail_link" href="javascript:viewDeatil('<s:property value="dpAppInfo.id"/>')" >
					<s:if test="%{dpAppInfo.appName.length() > 12}">
	               		<s:property value="%{dpAppInfo.appName.substring(0, 12) + \"..\"}"/>
	               	</s:if>
	               	<s:else>
	               		<s:property value="dpAppInfo.appName"/>
	                </s:else>
				</a>
			</td>
			<td><s:property value="dpAppInfo.dpType.typeName"/></td>
			<td>
				<s:if test="%{dpAppInfo.dpStaffId != null}">
					<s:property value="dpAppInfo.dpStaff.userName"/>
				</s:if>
				<s:if test="%{dpAppInfo.userId != null}">
					<s:property value="dpAppInfo.user.userName"/>
				</s:if>
			</td>
			<td><s:date name="dpAppInfo.createTime" format="yyyy-MM-dd"></s:date></td>
			<td><s:date name="appRecommendCTime" format="yyyy-MM-dd"></s:date></td>
			<td>
				<s:if test="%{dpAppInfo.appStatus == '1000'}">
					<font color="green">草稿</font>
				</s:if>
				<s:if test="%{dpAppInfo.appStatus == '1001'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.Pending" /></font>
				</s:if>
				<s:elseif test="%{dpAppInfo.appStatus == '1002'}">
					<font color="red"><s:text name="sdp.sce.dp.admin.ap.state.notpass" /></font>
				</s:elseif>
				<s:elseif test="%{dpAppInfo.appStatus == '1003'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.pass" /></font>
				</s:elseif>
				<s:elseif test="%{dpAppInfo.appStatus == '1004'}">
					<font color="green">已上架</font>
				</s:elseif>
				<s:elseif test="%{dpAppInfo.appStatus == '1005'}">
					<font color="green">已下架</font>
				</s:elseif>
				<s:elseif test="%{dpAppInfo.appStatus == '1006'}">
					<font color="green">版本更新</font>
				</s:elseif>
			</td>
			<td class="editbar">
				<s:if test="#session.userResMap.appOnline!=null">
					<!-- 已上架 -->
					<s:if test="%{dpAppInfo.appStatus == '1004'}">
						<s:if test="%{pAppInfo.appRecommend == null}">
							<!-- 推荐操作 -->
							<a href="javascript:commend(this,'<s:property value="dpAppInfo.id"/>',1)" target="mainFrame" >
								<s:text name="sdp.sce.dp.admin.ap.commend.home" />
							</a>
							&nbsp;&nbsp;
						</s:if>
						<s:else>
							<!-- 取消推荐操作 -->
							<a href="javascript:commend(this,'<s:property value="dpAppInfo.id"/>',0)" target="mainFrame" >
								取消推荐
							</a>
							&nbsp;&nbsp;
						</s:else>
					</s:if>
				</s:if>
			</td>
			<td class="editbar">
				<s:hidden name="id" id="id" value="%{id}"></s:hidden>
				<s:form method="post" id="%{id}sortForm" action="appTypeRecommend!doRecommendSort.action" name="%{id}sortForm" theme="simple">
					<s:hidden name="limit" id="limit" value="%{limit}"></s:hidden>
					<s:hidden name="appTypeRecommend.id" id="appTypeRecommend.id" value="%{id}"></s:hidden>
					<div id="<s:property value="id"/>"><a href="#"
					onclick="createInput('<s:property value="id"/>','${(page.currentPage-1) * page.pageSize + st.index + 1 }')">人工排序</a></div>
				</s:form>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<jsp:include page="/common/nextpage.jsp"></jsp:include>
</div>
<div class="btnlistbar">
	<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="backToList();" />
</div>
<script type="text/javascript">
	insertLanguageJS();
</script>
</body>
</html>
