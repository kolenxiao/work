<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者平台(DP)</title>
<script type="text/javascript">
	//分页跳转函数
	function jumpPage(no) {
		url = 'user!searchUser.action?';
		//需要传参数：form表单id，跳转URL，页码输入框的id。
		jumpPageTo('searchForm',url,no);
	}

	//每页显示多少条函数
	function doChange(obj) {
		var pageSizeVal = obj.options[obj.selectedIndex].value;
		url = 'user!searchUser.action?limit='+ pageSizeVal;
		formSubmit('searchForm',url);
	}

	//删除用户
	function del(sessionUserId) {
		var idArray = new Array();
		var childCheckboxs = document.getElementsByName("checkbox");
		var c_length = childCheckboxs.length;
		for ( var i = 0; i < c_length; i++) {
			if (childCheckboxs[i].checked){
				if(childCheckboxs[i].id==sessionUserId){
					dialogList('提示信息',300,150,'当前用户不能被冻结!',0,0,1,this);
					return false;
				}
				if(childCheckboxs[i].id==1){
					dialogList('提示信息',300,150,'系统用户不能被冻结!',0,0,1,this);
					return false;
				}
				idArray.push(childCheckboxs[i].id);
			}
		}
		if (idArray.length > 0) {
			//把字符串数组转换成字符串

			var idVal = eval(idArray);
			dialogList('提示信息',300,150,'确认冻结所选用户吗?',0,0,2,
					this);
			//注意此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
			$("#dialog_btn_conform").click(function()
					  {
						//执行删除操作
						  location.href ='user!doDelete.action?ids='
								+ idVal;;
						  $("#dialog").remove();
						  $("#dialog_bg").remove();
					  });



		} else {
			dialogList('提示信息',300,150,'请选择需要冻结的用户!',0,0,1,this);
		}

	}



	//查询函数
	function searchUser(startTimeId,endTimeId) {
		url = 'user!searchUserByCondition.action?';
		//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
		querydata('searchForm',url, startTimeId,endTimeId);
	}

	//翻页函数
	function page(start){
		url = 'user!searchUser.action?';
		//searchForm是form表单id,url 为跳转路径,start为第几页，pageSizeVal为每页显示多少条
		goUrlPage('searchForm',url, start);
	}

	//显示用户的详细信息
	function viewUserDetail(id)
	{
		location.href = 'user!viewUserDetail.action?id='+id;
	}

	//判断是否要密码重置
	function confirmPassWordReset(url){
		dialogList('<s:text name="sdp.sce.dp.admin.dp" />',300,150,'<s:text name="sdp.sce.dp.admin.dpstaff.confirm.password.reset" />',0,0,2,this);
		$("#dialog_btn_conform").click(function()
					  {
			$.ajax({
			    cache:false,
		        async:false,
		        type:"POST",
			   	url: url,
			   	dataType: 'json',
				success: function(response){
				   var dataObj = eval(response);
					//异步处理，要先清除前面那个对象
				   $("#dialog").remove();
				   if(dataObj && dataObj.success){
		        		dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'<s:text name="sdp.sce.dp.admin.dpstaff.confirm.password.reset.success" />',0,0,1,this);
		        		}
				   else if (dataObj && dataObj.exception)
					{
					   dialogList('<s:text name="sdp.sce.dp.admin.global.inform" />',300,150,'<s:text name="sdp.sce.dp.admin.dpstaff.database.exception" />',0,0,1,this);

					}

				}
			  });
		});
	}
</script>
</head>
<body id="cnt_body">
	<div id="position">
		<p>当前位置: 权限管理&gt; 用户列表</p>
		<div id="pright"></div>
	</div>
	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
				<ul>
					<li>用户名称： <s:textfield id="userName"
							name="qureyCon.userName" size="15" maxlength="64"
							value="%{qureyCon.userName}"  onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');"/>
					</li>

					<li>所属角色：

					<select name="qureyCon.roleId">
						<option value="">全部</option>

               		 		<s:iterator value="allRoleList" status="st"  >

									<option value="<s:property value='id' />"
										<s:if test="qureyCon.roleId == id">
											 selected
										</s:if>>
                   						<s:property value="name" />
                  					</option>

               		 		</s:iterator>

					</select>

					</li>
				 <li style="position:relative;bottom:5px">
					创建时间：<s:textfield id="startDate"
							name="qureyCon.startDate" size="6"
							value="%{qureyCon.startDate}"  readonly="true" />
							 <img style="position:relative;top:5px"
						onclick="WdatePicker({el:'startDate',dateFmt:'yyyy-MM-dd'})"
						src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
						width="16" height="22" />

						至<s:textfield id="endDate"
							name="qureyCon.endDate" size="6"
							value="%{qureyCon.endDate}" readonly="true"/>
						<img style="position:relative;top:5px"
						onclick="WdatePicker({el:'endDate',dateFmt:'yyyy-MM-dd',readOnly:true})"
						src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
						width="16" height="22" /></li>
						<li><input type="button" value="" class="btn_sendData"
							onclick="searchUser('startDate','endDate')" />
						</li>
				</ul>
			</s:form>
		</div>
	</div>
	<div class="databar">
		<div class="btnbar">
			<ul>
			<s:if test="#session.userResMap.addUser != null">
				<li>
				<a href="user!showAdd.action"
					target="mainFrame"><b>新增</b> </a>
				</li>
				</s:if>

			<s:if test="#session.userResMap.deleteUser != null">
				<li><a href="#" onclick="del('<s:property value="#session.user.id"/>')"><b>冻结用户</b> </a>
				</li>
			</s:if>
			</ul>
		</div>
		<%-- <div class="pagebar">
			<ul>
				<li><input type="hidden" id="totalPage"
					value="${page.totalPage}">
				</li>
				<li>每页显示<s:select id="pageSize" name="page.pageSize"
						list="#{'10':'10','20':'20','50':'50'}" onchange="doChange(this)"></s:select>条
				</li>
				<li>第${page.currentPage}/${page.totalPage}页</li>
				<li class="pnum_list"><s:if test="page.hasPrePage ==true">
							<a class="link2"
							href="javascript:pageDownUp(${page.prePage},${page.pageSize})">上一页</a>

					</s:if> <s:else>
						<font color="#858585"> 上一页</font>
					</s:else>
				</li>
				<li class="pnum_list"><s:if test="page.hasNextPage ==true">
							<a class="link2"
							href="javascript:pageDownUp(${page.nextPage},${page.pageSize})">下一页</a>
					</s:if> <s:else>
						<font color="#858585"> 下一页</font>
					</s:else>
				</li>
				<li class="jump_num">转到 <input id="pageNo2" name="pageNo"
					type="text" onkeyup="this.value=this.value.replace(/\D/g,'');" /> 页</li>
				<li class="lastpageli"><input type="button"
					class="btn_confirmpage" onclick="jumpPage('pageNo2',${page.pageSize});" />
			</ul>

		</div> --%>
		<jsp:include page="/common/prepage.jsp"></jsp:include>
	</div>
	<div class="data_list">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">

			<tr>
				<th width="40" scope="col"><span id="sprycheckbox1"> <label>
							<input type="checkbox" name="checkbox1" id="checkboxAll"
							onclick="selectAll(this)" /> </label> </span></th>
				<th  scope="col">序号</th>
				<th  scope="col">用户名</th>
				<th  scope="col">真实姓名</th>
				<th  scope="col">所属角色</th>
				<th  scope="col">电子邮箱</th>
				<th  scope="col">是否有效</th>
				<th  scope="col">创建者</th>
				<th  scope="col">创建时间 </th>
				<th  scope="col">修改时间 </th>

				<th width="140" class="editbar" scope="col">操作</th>
			</tr>
			<s:iterator value="page.resultList" status="st"  >

				<tr >
					<td><span id="sprycheckbox1"> <label><input
								type="checkbox" name="checkbox" id="<s:property value='id' />"  onclick="selectChildAll()" />
						</label> </span>
					</td>
					<td>${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
					<td><a class="detail_link" href="javascript:viewUserDetail('<s:property value='id' />')"> <s:property value="userName" /></a></td>
					<td>
					<s:property value="realName" />
					</td>
					<td>
					<s:property value="usrRoleMap[id]" />
					</td>

					<td><s:property value="email" />
					</td>
					<td>
					<s:if test='status == "Y"'>
						是
					</s:if>
					<s:if test='status == "N"'>
						否
					</s:if>

					</td>
					<td><s:property value="createdUser" /></td>
					<td><s:date name="createdDate" /></td>

					<td><s:date name="updatedDate" /></td>

					<td class="editbar">
					<s:if test="#session.userResMap.modifyUser != null">
					<a href="user!editUser.action?id=<s:property value='id' />&forward=edit"
						target="mainFrame">编辑</a>
					</s:if>
					<s:if test="#session.userResMap.passwordRet != null">
						<!-- <a href="<s:property value="#session.userResMap.passwordRet.url"/>?id=<s:property value='id' />"
						target="mainFrame">密码重置</a> -->

						<a href="javascript:confirmPassWordReset('<s:property value="#session.userResMap.passwordRet.url"/>?id=<s:property value='id' />')"
					target="mainFrame"><s:text name="sdp.sce.dp.admin.log.reset.password.operate" /></a>
					</s:if>
					</td>

				</tr>
			</s:iterator>

		</table>

	</div>
	<div class="databar">
		<div class="btnbar">
			<ul>
			<s:if test="#session.userResMap.addUser != null">
				<li>
				<a href="user!showAdd.action"
					target="mainFrame"><b>新增</b> </a>
				</li>
				</s:if>

			<s:if test="#session.userResMap.deleteUser != null">
				<li><a href="#" onclick="del('<s:property value="#session.user.id"/>')"><b>冻结用户</b> </a>
				</li>
			</s:if>
			</ul>
		</div>

	<jsp:include page="/common/nextpage.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		var sprycheckbox1 = new Spry.Widget.ValidationCheckbox("sprycheckbox1",{isRequired : false});
	</script>
</body>
</html>
