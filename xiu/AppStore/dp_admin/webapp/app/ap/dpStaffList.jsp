<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者列表</title>
<script type="text/javascript">
//删除用户
function doDelete() {
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
		var parm = {
			'ids' : idVal
		};
		$("#dialog").remove();
		dialogList(
				'<s:text name="sdp.sce.dp.admin.dp" />',
				300,
				150,
				'<s:text name="sdp.sce.dp.admin.global.del.data.confirm" />',
				0, 0, 2, this);

		//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform").click(function() {
			$.ajax({url : 'dpStaff!doDelete.action',
						type : 'POST',
						data : parm,
						dataType : 'json',
						error : function(msg) {
							dialogList(
									'<s:text name="sdp.sce.dp.admin.global.inform" />',
									300, 150, 'Error', 0,
									0, 1, this);
						},
						success : function(response) {
							$("#dialog").remove();
							var dataObj = eval(response);
							if (dataObj && dataObj.success) {
								location.href = "dpStaff!doList.action?&limit="
										+ pageSizeVal;
							} else if (dataObj
									&& dataObj.msg) {
								dialogList(
										'<s:text name="sdp.sce.dp.admin.global.inform" />',
										300, 150,
										dataObj.msg, 0, 0,
										1, this);
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
										'<s:text name="sdp.sce.dp.admin.dpstaff.ap.cannont.delete" />',
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
				'<s:text name="sdp.sce.dp.admin.global.select.del.data" />',
				0, 0, 1, this);
	}
}

//查询函数
function searchDp(startTimeId, endTimeId) {
	url = 'dpStaff!doList.action?flag=0&';
	//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
	querydata('searchForm', url, startTimeId, endTimeId);
}

//显示AP的详细信息
function viewDpStaffDetail(id) {
	location.href = 'dpStaff!doDisplay.action?id=' + id + '&forward=detail';
}

//判断是否要密码重置
function confirmPassWordReset(url) {
		dialogList(
		'<s:text name="sdp.sce.dp.admin.dp" />',
		300,
		150,
		'<s:text name="sdp.sce.dp.admin.dpstaff.confirm.password.reset" />',
		0, 0, 2, this);
		$("#dialog_btn_conform").click(function() {
				$.ajax({
						cache : false,
						async : false,
						type : "POST",
						url : url,
						dataType : 'json',
						success : function(response) {
							var dataObj = eval(response);
							//异步处理，要先清除前面那个对象
							$("#dialog").remove();
							if (dataObj && dataObj.success) {
								dialogList(
										'<s:text name="sdp.sce.dp.admin.global.inform" />',
										300,
										150,
										'<s:text name="sdp.sce.dp.admin.dpstaff.confirm.password.reset.success" />',
										0, 0, 1, this);
							} else if (dataObj && dataObj.exception) {
								dialogList(
										'<s:text name="sdp.sce.dp.admin.global.inform" />',
										300,
										150,
										dataObj.exception_msg,
										0, 0, 1, this);

							}

					 }
				});
		});
}

function jumpPage(no) {
	url = 'dpStaff!doList.action?flag=0&';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm', url, no);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'dpStaff!doList.action?flag=0&limit=' + pageSizeVal;
	//表单提交
	formSubmit('searchForm', url);
}

//上下页显示
function page(start) {
	url = 'dpStaff!doList.action?flag=0&';
	//searchForm是form表单id,url 为跳转路径,start为第几页
	goUrlPage('searchForm', url, start);
}

</script>
</head>
<body id="cnt_body">
	<div id="position">
	    <p>
	    	<s:text name="sdp.sce.dp.admin.dptype.pro" />：
	    	<s:text name="sdp.sce.dp.admin.dpstaff" /> &gt;
	    	<s:text name="sdp.sce.dp.admin.dpstaff.all.ap.information" />
	    </p>
		<div id="pright"></div>
	</div>
	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
				<ul>
					<li>
						<s:text name="sdp.sce.dp.admin.dpstaff.uesrname" />：
                        <s:textfield id="dpStaffUserName"
								name="dpStaff.userName" size="15" maxlength="32"
								value="%{dpStaff.userName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');"/>
                    </li>
					<li>
						<s:text name="sdp.sce.dp.admin.dpstaff.nickname" />：
                        <s:textfield id="dpStaffNickName"
								name="dpStaff.nickName" size="15" maxlength="32"
								value="%{dpStaff.nickName}" onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');" />
		            </li>
                    <li>
                    	<s:text name="sdp.sce.dp.admin.dpstaff.Status" />：
                        <s:select id="staffStatus" name="dpStaff.staffStatus" size="1"
                           list="#{'':'全部','1001':getText('sdp.sce.dp.admin.dpstaff.audit.not.pass'),'1002':getText('sdp.sce.dp.admin.dpstaff.audit.to.wait'),'1003':getText('sdp.sce.dp.admin.dpstaff.audit.pass')}"
							value="%{dpStaff.staffStatus}"  cssStyle="width:80px"/>

                     </li>
					 <li>
						<s:text name="sdp.sce.dp.admin.news.newsCTime" />：
						<s:textfield id="beginDpStaffCtime"
								name="dpStaff.beginDpStaffCtime" size="10"
								value="%{dpStaff.beginDpStaffCtime}"  readonly="true" /></li><li>
								 <img
								onclick="WdatePicker({el:'beginDpStaffCtime',dateFmt:'yyyy-MM-dd'})"
								src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
								width="16" height="22" />
					</li>
					<li>
						<s:text name="sdp.sce.dp.admin.dpstaff.to" />
						<s:textfield id="endDpStaffCtime"
								name="dpStaff.endDpStaffCtime" size="10"
								value="%{dpStaff.endDpStaffCtime}"  readonly="true" />
					</li>
					<li>
						<img onclick="WdatePicker({el:'endDpStaffCtime',dateFmt:'yyyy-MM-dd'})"
							 src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							 width="16" height="22" />
					</li>
					<li>
						<input type="button" value="" class="btn_sendData"
							   onclick="searchDp('beginDpStaffCtime','endDpStaffCtime')" />
					</li>
				</ul>
			</s:form>
		</div>
	</div>
	<div class="databar">
		<div class="btnbar">
			<ul>
				<s:if test="#session.userResMap.deleteAp !=null">
					<li>
						<a href="#" onclick="doDelete()">
							<b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b>
						</a>
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
				<th width="40" scope="col"><s:text name="sdp.sce.dp.admin.news.newsNo" /></th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.dpstaff.uesrname" /></th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.dpstaff.nickname" /></th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.dpstaff.email" /></th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.dpstaff.Status" /></th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsCTime" /></th>
				<th scope="col"><s:text name="sdp.sce.dp.admin.news.newsUTime" /></th>
				<th width="140" class="editbar"  scope="col"><s:text name="sdp.sce.dp.admin.global.label.operate" /></th>
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
				<td>${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
				<td><a class="detail_link" href="javascript:viewDpStaffDetail('<s:property value='id' />')">
				<s:property value="userName" /></a></td>
				<td><s:property value="nickName" /></td>
				<td><s:property value="email" /></td>
				<td class="editbar">
					<s:if test="%{staffStatus == '1001'}"> <s:text name="sdp.sce.dp.admin.dpstaff.audit.not.pass" /></s:if>
					<s:elseif test="%{staffStatus == '1002'}"><s:text name="sdp.sce.dp.admin.dpstaff.audit.to.wait" /></s:elseif>
					<s:elseif test="%{staffStatus == '1003'}"> <s:text name="sdp.sce.dp.admin.dpstaff.audit.pass" /></s:elseif>
					<s:elseif test="%{staffStatus == '1004'}"> <s:text name="sdp.sce.dp.admin.dpstaff.draft" /></s:elseif>
				</td>
				<td><s:date name="beginDpStaffTime" format="yyyy-MM-dd"/></td>
				<td><s:date name="updateDpStaffTime" format="yyyy-MM-dd" /></td>
			    <td class="editbar">
					<s:if test="%{staffStatus == '1002'}">
				        <s:if test="#session.userResMap.queryApInfo!=null">
				        <a href="<s:property value="#session.userResMap.queryApInfo.url"/>?forward=audit&Flag=0&id=<s:property value='id' />"
							target="mainFrame" class="detail_link">&nbsp;&nbsp;<s:text name="sdp.sce.dp.admin.log.audit.operate" />&nbsp;&nbsp;</a></s:if>
			    	</s:if>
					<s:if test="#session.userResMap.PassWordReset != null">
						<a href="javascript:confirmPassWordReset('<s:property value="#session.userResMap.PassWordReset.url"/>?id=<s:property value='id' />')"
							target="mainFrame"><s:text name="sdp.sce.dp.admin.log.reset.password" /></a>
					</s:if>
				</td>
			</tr>
			</s:iterator>
		</table>
	</div>
	<div class="databar">
		<div class="btnbar">
			<ul>
			<s:if test="#session.userResMap.deleteAp !=null">
			   	<li>
			   		<a href="#" onclick="doDelete()">
			   			<b><s:text name="sdp.sce.dp.admin.global.btn.del" /></b>
			   		</a>
				</li>
			</s:if>
		    </ul>
		</div>
		<!-- 2011.11.03 Edit 引入分页界面 -->
		<jsp:include page="/common/nextpage.jsp"></jsp:include>
		<!-- ended of 2011.11.03 Edit  -->
	</div>
</body>
</html>
