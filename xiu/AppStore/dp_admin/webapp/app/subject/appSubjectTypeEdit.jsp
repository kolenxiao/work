<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>AP应用专题修改</title>
<script type="text/javascript">
	//自定义输出错误信息
	FormValid.showError = function(errMsg,errName,formName) {
		if (formName=='editForm') {
			for (key in FormValid.allName) {
				document.getElementById('errMsg_'+FormValid.allName[key]).innerHTML = '';
			}
			for (key in errMsg) {
				document.getElementById('errMsg_'+errName[key]).innerHTML = errMsg[key];
			}
		} else {
			for (key in errMsg) {
				dialogList(
						'<s:text name="sdp.sce.dp.admin.global.inform" />',
						300, 150, errMsg[key], 0,0, 1, this);
			}
		}
	};
	//分页跳转函数
	function jumpPage(no) {
		url = 'subjectAppinfoRelationAction!toEditSubject.action?operate=addApp&';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm', url, no);
	}

	//上下页显示
	function page(start) {
		url = 'subjectAppinfoRelationAction!toEditSubject.action?operate=addApp&';
		//searchForm是form表单id,url 为跳转路径,start为第几页
		goUrlPage('searchForm', url, start);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'subjectAppinfoRelationAction!toEditSubject.action?operate=addApp&limit=' + pageSizeVal;
		//表单提交
		formSubmit('searchForm', url);
	}


	//显示应用详细信息
	function viewAppDeatil(id) {
		action = 'dpAppInfo!doDisplay.action?forward=detail&noBackBtn=true&appInfo.id='
				+ id;
		var jsonObj = {
				scrollType : 'yes'
			};
			top.openshow(action,
					'<s:text name="sdp.sce.dp.admin.ap.appinfo.detail" />', 900,
					600, 1, jsonObj);
	}

	//删除专题和应用的关联
	function delSubjectAppRelation(subjectId,id) {
		var idArray = new Array();
		var pageSizeVal = document.getElementById("pageSize").value;
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked)
				idArray.push(childCheckboxs[i].id);
		}
		if (idArray.length > 0 || id) {
			//把字符串数组转换成字符串
			var idVal =id ? id : eval(idArray);
			var parm = {
				'ids' : idVal,
				'appSubjectType.id':subjectId
			};
			$("#dialog").remove();
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300,
					150,
					'<s:text name="sdp.sce.dp.admin.global.del.data.confirm" />',
					0, 0, 2, this);
			//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
			$("#dialog_btn_conform")
					.click(
							function() {
								$.ajax({cache : false,
										async : false,
										url : 'dpAppSubjectType!doRemoveAppToSubjectType.action',
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
												location.href = "subjectAppinfoRelationAction!toEditSubject.action?&limit="
														+ pageSizeVal+"&appSubjectType.id="+subjectId+"&operate=addApp";
											} else if (dataObj
													&& dataObj.msg) {
												dialogList(
														'<s:text name="sdp.sce.dp.admin.global.inform" />',
														300, 150,
														dataObj.msg, 0, 0,
														1, this);
												$("#dialog_btn_conform")
														.click(
																function() {
																	location.href = "subjectAppinfoRelationAction!toEditSubject.action?&limit="
																			+ pageSizeVal+"&appSubjectType.id="+subjectId+"&operate=addApp";
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
			$("#dialog").remove();
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300,
					150,
					'<s:text name="sdp.sce.dp.admin.global.select.del.data" />',
					0, 0, 1, this);
		}
	}




	function doSaveEdit(url){
		var form = document.getElementById("editForm");
		form.action= url;
		if(validator(form)){
			form.submit();
		}
	}

	function backToList()
	{
		window.location="dpAppSubjectType!doSearchAppSubjectTypeList.action";
	}

	//打开新增专题应用页面
	function toAddAppToSubjectType(url,id)
	{
		action = url+ '?appSubjectType.id='	+ id;
		var jsonObj = {
			scrollType : 'yes'
		};

		top.openshow(action,
				'关联应用', 900,
		440, 1, jsonObj);
	}

	/**
	 * 构造输入框/按钮
	 */
	function createInput(parentID, index){
		var d = "#"+parentID;
	    $(d + " a").hide();
		var id = parentID;
	    $("div[id='"+parentID+"']").append("<input type='text' id='sort' paramId='"+id+"' regexp='^[0-9]*[1-9][0-9]*$' name='sort' size='3' valid='required|regexp' errmsg='dp_sort_required|dp_sort_regexp'/>");
	    $("div[id='"+parentID+"']").append("<input type='button' id='button' paramId='"+id+"' onclick='saveSort(this)' name='button' value='保存' />");
	    $("div[id='"+parentID+"']").append("<input type='button' id='button' paramId='"+id+"' onclick='cancle(this)' name='button' value='取消' style='width:34px;height:20px;border:none;color:#006DC1;padding:1px 4px;background:url(images/bg_select00.gif) no-repeat;' />");
	    $("div[id='"+parentID+"']").append("<input type='hidden' id='errMsg_sort' />");
	    $("div[id='"+parentID+"']").append("<input type='hidden' id='index' name='index' value='"+index+"'/>");
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


</script>
</head>
<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.ap.name" />&gt;应用专题&gt;
	  <s:if test="%{appSubjectType.id != null }">
	  	<s:if test="operate=='updateApp'">修改专题</s:if>
	  	<s:elseif test="operate=='addApp'">查看专题</s:elseif>
	  	<s:else>修改专题</s:else>
	  </s:if>
	  <s:else>新增专题</s:else>
  </p>
  <div id="pright"></div>
</div>
<s:if test="%{appSubjectType.id == null }">
	<div class="view_nav">
		新增专题
	</div>
</s:if>
<s:if test="operate==null">
	<s:form id="editForm" name="editForm" action="" method="post" cssStyle="margin:0" theme="simple" enctype="multipart/form-data">
		<s:token/>
		<s:hidden name="appSubjectType.id" id="appSubjectType.id" value="%{appSubjectType.id}"></s:hidden>
		<s:hidden name="appSubjectType.createTime" id="appSubjectType.createTime" value="%{appSubjectType.createTime}"></s:hidden>
		<s:hidden name="appSubjectType.createUser" id="appSubjectType.createUser" value="%{appSubjectType.createUser}"></s:hidden>
		<div class="data_view">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<th scope="row">专题名称</th>
					<td>
						<s:textfield name="appSubjectType.subjectName" id="appSubjectType.subjectName"
							value="%{appSubjectType.subjectName}" valid="required|isSpecial|limit"
							size="50" min="0" max="128" errmsg="subjectName_notEmpty|subjectName_special|subjectName_lenLess128"  />
						<span class="must_input">*&nbsp;&nbsp;
							<label id="message"><s:property value="message" />
							</label>&nbsp;&nbsp;
						</span>
						<span id="errMsg_appSubjectType.subjectName" style="color:#FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">专题图片</th>
					<s:if test="%{appSubjectType.id != null}">
						<td>
							<a href='${appFilePath.imgPath}<s:property value="appSubjectType.subjectImg"/>' target="_blank"><s:property value="appSubjectType.subjectImg"/></a>
							<br/>
							<input type="file" id="attachment" name="attachment"
								size="50"/>
						</td>
					</s:if>
					<s:else>
						<td>
							<input type="file" id="attachment" name="attachment"
							size="50" valid="filter" allow="png,jpg" errmsg="appAttachment_filter"/>
							<span id="errMsg_attachment" style="color:#FF0000"></span>
						</td>
					</s:else>
				</tr>
				<tr>
					<th scope="row">专题描述</th>
					<td>
						<s:textarea name="appSubjectType.subjectDesc" id="subjectDesc"
								value="%{appSubjectType.subjectDesc}" cols="70" rows="8"
								valid="limit" min="0" max="255"
								errmsg="appSubject_desc_limit"/>
						<p>
							<s:text name="sdp.sce.dp.admin.news.newskeyword.len.tip"/></span>
						</p>
						<span id="errMsg_appSubjectType.subjectDesc" style="color:#FF0000"></span>
					</td>
				</tr>
				<tr>
					<th scope="row">产品编码<p style="color: gray;">(DVB鉴权时使用，与套餐号保持一致)</p></th>
					<td>
						<s:textfield name="appSubjectType.productCode" id="appSubjectType.productCode"
							value="%{appSubjectType.productCode}"  size="50" min="0" max="128"/>
						<span id="errMsg_appSubjectType.productCode" style="color:#FF0000"></span>
					</td>
				</tr>
			</table>
		</div>
	</s:form>
</s:if>
<s:else>
<!--  基本信息start-->
<div class="view_nav"  id="nav01" >
	<span class="float_l">
		<s:if test="operate=='updateApp'">修改专题</s:if>
		<s:elseif test="operate=='addApp'">查看专题</s:elseif>
	</span>
	<span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span>
</div>
<div id="box1">
	<div class="data_view">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0">
	  	 <tr>
	      <th>专题名称</th>
	      <td><s:property value="appSubjectType.subjectName"/></td>
	    </tr>
	    <tr>
	    	<th >
	      	所属开发者
	    	</th>
	       <td><s:property value="appSubjectType.createUser"/></td>
	    </tr>
	    <tr>
	      <th>创建时间</th>
	      <td>
	      	<s:date name="appSubjectType.createDate" format="yyyy-MM-dd HH:ss" />
		  </td>
	    </tr>
	    <tr>
	       <th>更新时间</th>
	       <td>
	       	<s:date name="appSubjectType.updateDate" format="yyyy-MM-dd HH:ss" />
	       </td>
	    </tr>
	    <tr>
	       <th>专题描述</th>
	       <td ><s:property value="appSubjectType.subjectDesc"/></td>
	    </tr>
	    <tr>
	       <th>产品编码</th>
	       <td ><s:property value="appSubjectType.productCode"/></td>
	    </tr>
	    <tr>
	    <th>专题背景</th>
	     <td colspan="2"><a href="${appFilePath.imgPath}${appSubjectType.subjectImg}" target="_blank"><font color="blue">点击查看<font></a></td>
	    <tr>
	  </table>
	</div>
</div>
</s:else>
	<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
		<s:hidden name="appSubjectType.id" id="appSubjectType.id" value="%{appSubjectType.id}"></s:hidden>
		<s:hidden name="appSubjectType.createTime" id="appSubjectType.createTime" value="%{appSubjectType.createTime}"></s:hidden>
		<s:hidden name="appSubjectType.createUser" id="appSubjectType.createUser" value="%{appSubjectType.createUser}"></s:hidden>
	</s:form>
<!-- 编辑的时候才可以进行新增专题应用和移除专题应用 -->
<s:if test="%{appSubjectType.id != null && operate!=null}">
<div class="view_nav"  id="nav02" >
	<span class="float_l">关联的应用列表</span>
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
			<th width="140" class="editbar" scope="col"><s:text name="sdp.sce.dp.admin.global.label.operate" /></th>
			<th width="140" scope="col">自定义排名</th>
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
				<a class="detail_link" href="javascript:viewAppDeatil('<s:property value="appInfo.id"/>')" >
					<s:if test="%{appInfo.appName.length() > 12}">
	               		<s:property value="%{appInfo.appName.substring(0, 12) + \"..\"}"/>
	               	</s:if>
	               	<s:else>
	               		<s:property value="appInfo.appName"/>
	                </s:else>
				</a>
			</td>
			<td><s:property value="appInfo.dpType.typeName"/></td>
			<td>
				<s:if test="%{appInfo.dpStaffId != null}">
					<s:property value="appInfo.dpStaff.userName"/>
				</s:if>
				<s:if test="%{appInfo.userId != null}">
					<s:property value="appInfo.user.userName"/>
				</s:if>
			</td>
			<td><s:date name="appInfo.createTime" format="yyyy-MM-dd"></s:date></td>
			<td><s:date name="appInfo.updateTime" format="yyyy-MM-dd"></s:date></td>
			<td>
				<s:if test="%{appInfo.appStatus == '1000'}">
					<font color="green">草稿</font>
				</s:if>
				<s:if test="%{appInfo.appStatus == '1001'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.Pending" /></font>
				</s:if>
				<s:elseif test="%{appInfo.appStatus == '1002'}">
					<font color="red">审核未通过</font>
				</s:elseif>
				<s:elseif test="%{appInfo.appStatus == '1003'}">
					<font color="green"><s:text name="sdp.sce.dp.admin.ap.state.pass" /></font>
				</s:elseif>
				<s:elseif test="%{appInfo.appStatus == '1004'}">
					<font color="green">已上架</font>
				</s:elseif>
				<s:elseif test="%{appInfo.appStatus == '1005'}">
					<font color="green">已下架</font>
				</s:elseif>
				<s:elseif test="%{appInfo.appStatus == '1006'}">
					<font color="green">版本更新</font>
				</s:elseif>
			</td>
			<td class="editbar">
				<!-- 取消关联操作 -->
				<a href="#" onclick="delSubjectAppRelation('<s:property value="appSubjectType.id"/>','<s:property value="id"/>')" target="mainFrame" >
					移除关联
				</a>
			</td>
			<td>
				<s:hidden name="id" id="id" value="%{id}"></s:hidden>
				<s:form method="post" id="%{id}sortForm" action="subjectAppinfoRelationAction!doSubjectAppSort.action" name="%{id}sortForm" theme="simple">
					<s:hidden name="limit" id="limit" value="%{limit}"></s:hidden>
					<s:hidden name="relationQuery.id" id="relationQuery.id" value="%{id}"></s:hidden>
					<s:hidden name="appSubjectType.id" id="appSubjectType.id" value="%{appSubjectType.id}"></s:hidden>
					<div id="<s:property value="id"/>"><a href="#"
					onclick="createInput('<s:property value="id"/>','${(page.currentPage-1) * page.pageSize + st.index + 1 }')">人工排序</a></div>
				</s:form>
			</td>
		</tr>
		</s:iterator>
	</table>
</div>
<div class="databar">
	<div class="btnbar">
		<ul>
		<s:if test="#session.userResMap.removeAppToSubjectType!=null">
			<li>
				<a href="javascript:delSubjectAppRelation('<s:property value="appSubjectType.id"/>')">
				<b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b>
				</a>
			</li>
		</s:if>
		<s:if test="#session.userResMap.toAddAppToSubjectType!=null">
			<li>
				<a href="javascript:toAddAppToSubjectType('<s:property value="#session.userResMap.toAddAppToSubjectType.url"/>','<s:property value="appSubjectType.id"/>')" >
				<b><s:text name="sdp.sce.dp.admin.global.btn.add" /></b>
				 </a>
			</li>
		</s:if>
		</ul>
	</div>

	<jsp:include page="/common/prepage.jsp"></jsp:include>
</div>
</s:if>
	<div class="btnlistbar">
	<s:if test="operate==null">
		<s:if test="%{appSubjectType.id != null}">
			<input class="inputstyle" type="button"
					value="<s:text name='sdp.sce.dp.admin.global.btn.confirm'/>" onclick="doSaveEdit('dpAppSubjectType!doSaveEidt.action')"/></s:if>
		<s:else>
			<input class="inputstyle" type="button"
					value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>"
					onclick="doSaveEdit('dpAppSubjectType!doSaveEidt.action')" />
		</s:else>
	</s:if>
		<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="backToList()" />
	</div>
<script type="text/javascript">
	document.write("<iframe id='downloadFrame' style='display: none'></iframe> ");
	insertLanguageJS();
	<s:if test="operate==null">
		initValid(document.editForm);
	</s:if>
</script>
</body>
</html>