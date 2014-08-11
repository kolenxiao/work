<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者平台</title>
<script type="text/javascript">
//分页跳转函数
function jumpPage(no) {
	url = 'role!searchRole.action?';
	//需要传参数：form表单id，跳转URL，页码输入框的id。
	jumpPageTo('searchForm',url,no);
}

//每页显示多少条函数
function doChange(obj) {
	var pageSizeVal = obj.options[obj.selectedIndex].value;
	url = 'role!searchRole.action?limit='+ pageSizeVal;
	formSubmit('searchForm',url);
}

//查询函数
function searchRole(startTimeId,endTimeId) {
	url = 'role!searchRole.action?';
	//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
	querydata('searchForm',url, startTimeId,endTimeId);


}

/*
 * 打开新窗口
 * f:链接地址
 * n:窗口的名称
 * w:窗口的宽度
 * h:窗口的高度
 * s:窗口是否有滚动条，1：有滚动条；0：没有滚动条
 */
function openWin(f,n,w,h,s){
 sb = s == "1" ? "1" : "0";
 l = (screen.width - w)/2;
 t = (screen.height - h)/2;
 sFeatures = "left="+ l +",top="+ t +",height="+ h +",width="+ w
   + ",center=1,scrollbars=" + sb + ",status=0,directories=0,channelmode=0";
 openwin = window.open(f , n , sFeatures );
 if (!openwin.opener)
  openwin.opener = self;
 openwin.focus();
 return openwin;
}
/*
 * 打开删除窗口
 */
function openDeleteDialog(url,confirmString){
 var c = confirmString;
 if(c == null || c == ''){
  c = "<s:text name='sdp.sce.dp.admin.global.del.data.confirm'/>";
 }
 if(confirm(c)){
  return window.showModalDialog(url,"window123","dialogHeight:234px;dialogWidth:271px;resizable:no;help:yes;status:no;scroll:no");
 }
 return false;
}

/*
 * 删除记录
 */
function del(url,info){
 if(openDeleteDialog(url,info)){
  window.location.reload(true);
 }
}

function doDelete(url) {
	var ids = new Array();
	var childCheckboxs = document.getElementsByName("checkbox");
	var c_length = childCheckboxs.length;
	for(var i=0;i<c_length;i++){
		if(childCheckboxs[i].checked)
		    ids.push(childCheckboxs[i].id);
	}

	if(ids.length>0){
		dialogList('<s:text name="sdp.sce.dp.admin.global.btn.tishi"/>',300,150,'<s:text name="sdp.sce.dp.admin.global.del.data.confirm"/>',0,0,2,
				this);
		//注意:此处的dialog_btn_conform为"确定"按钮的id,点击"确定"时执行删除操作
		$("#dialog_btn_conform").click(function()
				  {
					//执行删除操作

					  location.href=url + '?ids='+ids;
					  $("#dialog").remove();
					  $("#dialog_bg").remove();
				  });

	}else{
		 dialogList('<s:text name="sdp.sce.dp.admin.global.btn.tishi"/>',300,150,'<s:text name="sdp.sce.dp.admin.global.page.select"/>',0,0,1,this);
	}
}

function errorMsg() {
	var msg = '<s:property value="errorMsg"/>';
	if(msg != null && msg.trim().length > 0) {
		dialogList('<s:text name="sdp.sce.dp.admin.global.btn.tishi"/>',300,150,msg,0,0,1,this);
	}
}

//翻页函数
function page(start){
	url = 'role!searchRole.action?';
	//searchForm是form表单id,url 为跳转路径,start为第几页，pageSizeVal为每页显示多少条
	goUrlPage('searchForm',url, start);
}

//显示角色详细信息
function viewRoleDetail(id)
{
	location.href = 'role!showRoleDetail.action?roleId='+id;
}
</script>
</head>
<body id="cnt_body" onload="errorMsg()">
	<div id="position">
		<p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.role"/> &gt; <s:text name="sdp.sce.dp.admin.role.role.list"/></p>
		<div id="pright"></div>
	</div>

	<div id="searchbar">
		<div id="search_itemlist">
			<s:form id="searchForm" name="searchForm" action="" method="post" cssStyle="margin:0" theme="simple" >
				<ul>
						<li><s:text name="sdp.sce.dp.admin.role.roleName"/>
							<s:textfield type="text" id="roleName"
								name="role.name" size="15" maxlength="64" value="%{role.name}"  onblur="return this.value = this.value.replace(/(^\s*)|(\s*$)/g, '');"/>
						</li>
						<li><s:text name="sdp.sce.dp.admin.role.ctime"/>
							<s:textfield type="text" id="beginDate"
							name="role.beginDate" size="6"
							 readonly="true"  value="%{role.beginDate}"/></li><li>
							 <img style="position:relative;top:5px"
							onclick="WdatePicker({el:'beginDate',dateFmt:'yyyy-MM-dd'})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
						</li>

						<li>
						<s:text name="sdp.sce.dp.admin.global.label.to"/>
							<s:textfield type="text" id="endDate"
							name="role.endDate" size="6"
							  readonly="true" value="%{role.endDate}" /></li><li>
							<img style="position:relative;top:5px"
							onclick="WdatePicker({el:'endDate',dateFmt:'yyyy-MM-dd',readOnly:true})"
							src="<%=request.getContextPath()%>/My97DatePicker/skin/datePicker.gif"
							width="16" height="22" />
						</li>
						<li>
							<input type="button" value="" class="btn_sendData"  onclick="searchRole('beginDate','endDate')" />
							<!-- <input type="reset" value="" class="btn_clean" onclick="clean()"/>  -->
						</li>
					</ul>
			</s:form>
		</div>
	</div>



	<div class="databar">
		<div class="btnbar">
			<ul>
			<s:if test="#session.userResMap.addRole != null">
				<li>
				<a href="role!showAdd.action"
					target="mainFrame"><b><s:text name="sdp.sce.dp.admin.global.btn.add"/></b> </a>
				</li>
				</s:if>
				<s:if test="#session.userResMap.deleteRole != null">
				<li>
						<a href='#' onclick="doDelete('<s:property value="#session.userResMap.deleteRole.url"/>')">
							<b><s:text name="sdp.sce.dp.admin.global.btn.del"/></b></a>
				</li>
				</s:if>
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
				     <input type="checkbox" name="checkbox1" id="checkboxAll"onclick="selectAll(this)" />
				    </label>
				  </span>
				</th>
				<th width="40" scope="col"><s:text name="sdp.sce.dp.admin.global.label.no"/></th>
				<th  scope="col"><s:text name="sdp.sce.dp.admin.role.label.roleName"/></th>
				<th  scope="col"><s:text name="sdp.sce.dp.admin.role.label.roleDescription"/></th>
				<th  scope="col"><s:text name="sdp.sce.dp.admin.role.label.creator"/></th>
				<th  scope="col"><s:text name="sdp.sce.dp.admin.role.label.ctime"/></th>
				<th  scope="col"><s:text name="sdp.sce.dp.admin.role.label.utime"/></th>

				<th class="editbar" scope="col"><s:text name="sdp.sce.dp.admin.global.label.operate"/></th>
			</tr>
			<s:iterator value="page.resultList" status="st"  >
				<tr>
					<td>
						<span id="sprycheckbox1">
							<label>
								<input
								type="checkbox" name="checkbox" id="<s:property value='id' />" onclick="selectChildAll()" />
						    </label>
						    </span>
					</td>
					<td>${(page.currentPage-1) * page.pageSize + st.index + 1 }</td>
					<td align="center">
					<div style="word-wrap:break-word;overflow:hidden;width:200px;">
					<a class="detail_link" href="javascript:viewRoleDetail('<s:property value='id' />')"><s:property value="name" /></a>
					</div>
					</td>

					<td align="center">
					<div style="word-wrap:break-word;overflow:hidden;width:200px;">
					<s:property value="description" />
					</div>
					</td>

					<td><s:property value="createdUser" /></td>
					<td><s:date name="createdDate"  format="yyyy-MM-dd"/></td>
					<td><s:date name="updatedDate" format="yyyy-MM-dd"/></td>

					<td class="editbar">
					<s:if test="#session.userResMap.modifyRole != null">
					<a href="role!showModify.action?roleId=<s:property value='id' />"
						target="mainFrame"><s:text name="sdp.sce.dp.admin.global.btn.edit"/></a>
					</s:if>

					</td>
				</tr>
			</s:iterator>

		</table>

	</div>
	<div class="databar">
		<div class="btnbar">
			<ul>
			<s:if test="#session.userResMap.addRole != null">
				<li>
				<a href="role!showAdd.action"
					target="mainFrame"><b><s:text name="sdp.sce.dp.admin.global.btn.add"/></b> </a>
				</li>
				</s:if>
				<s:if test="#session.userResMap.deleteRole != null">
				<li>
						<a href='#' onclick="doDelete('<s:property value="#session.userResMap.deleteRole.url"/>')">
							<b><s:text name="sdp.sce.dp.admin.global.btn.del"/></b></a>
				</li>
				</s:if>
			</ul>
		</div>

		<jsp:include page="/common/nextpage.jsp"></jsp:include>
	</div>
	<script type="text/javascript">
		var sprycheckbox1 = new Spry.Widget.ValidationCheckbox("sprycheckbox1",
				{
					isRequired : false
				});
	</script>
</body>
</html>
