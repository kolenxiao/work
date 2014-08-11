<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.role.edit.role"/></title>

<script type="text/javascript">

var validator;
$(document).ready(function(){
	//$("#uspServGroup.uspGroupName").focus();
	//$("#re").click(function() {
	//     $("label").remove();

validator = $("#uspRoleForm").validate({

			rules: {
				"role.name":{
					required: true,
					maxlength:64,
					roleName:true
				},
				"role.description":{
					maxlength:200
				}

			},
			messages: {
				"role.name":{
					required: '<font color="red">角色名不能为空！</font>',
					maxlength: '<font color="red">角色名长度不能超过64个字符！</font>'
					},
				"role.description":{
					maxlength: '<font color="red">角色描述长度不能超过200个字符！</font>'
				}
			},
			errorPlacement: function(error, element) {
			      error.appendTo( element.parent() );
			    },
			    onkeyup: false
});
});

	function doSave() {

		var uspRoleForm = $("#uspRoleForm");
		if(validator.form()){
			uspRoleForm.submit();
		}
	}
	// Begin treeview 2011-8-4
	$(function() {
			$("#browser").treeview({
				collapsed: false,
				animated: "medium",
				control:"#treecontrol",
				persist: "location"
			});
		});

	function selectCheckboxByPid(pid){
		var $pidCheckbox = $("#" +pid);
		//设置当前checkbox的value的值为true或false
		$pidCheckbox.attr("value",$pidCheckbox.attr("checked"));

		// 子孙节点状态和值取决于根节点的状态和值  Begin root
		$("li[id*=_"+pid+"_] input:checkbox").attr("checked",$pidCheckbox.attr("checked"))  //设置选中状态
											 .attr("value", $pidCheckbox.attr("checked"));	//设置value的值为true或false
		// End root

		// 根节点的状态和值取决于子孙节点状态和值  Begin child
		var $currCheckbox = $("li[id$=_"+pid+"]");
		var hasParent = $currCheckbox.parent();
		var hasSiblings = $currCheckbox.siblings();
		var hasChildren = hasSiblings.children();

		// 父节点不是根节点，id为browser是根节点 Begin if 2011-8-25
		if("browser" != hasParent.attr("id")){

			var $checkboxArray = $("li[id$=_"+pid+"]").attr("id").replace("li_","").split("_");

			// 在兄弟节点或兄弟节点的子节点中有选中的checkbox,把当前节点的父节点设置为选中，值设置为true

			if( hasSiblings.find(":checked").val() || hasChildren.find(":checked").val()|| $pidCheckbox.attr("checked")){
				for(var i=0;i<$checkboxArray.length;i++){
					if($checkboxArray[i] != pid){
						$("#" +$checkboxArray[i]).attr("checked","checked").attr("value","true");
					}
				}
			}else{// 父节点下无子孙节点则取消父节点选中状态，值设置为false

				for(var i=$checkboxArray.length-1;i>=0;i--){
					if($checkboxArray[i] != pid && !$("#" +$checkboxArray[i]+"").parent().siblings().children().find(":checked").val()){
						$("#" +$checkboxArray[i]).removeAttr("checked","checked").attr("value","false");
					}
				}
			}
		}
		// End if 2011-8-25
		// End child
	}
	// End treeview 2011-8-4
	$.validator.addMethod("roleName",function(){
	var roleName = document.getElementById("roleName").value;
		if (RuleLib.Name.test(roleName.trim()))
		{
			return true;
		}
		return false;

},'<font color="red">角色名只能输入字母、数字、下划线、中文、空格！</font>');
</script>

</head>

<body id="cnt_body">
<div id="position">
  <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.role"/> &gt; <s:text name="sdp.sce.dp.admin.role.edit.role"/>
  <div id="pright"></div>
</div>
<div class="view_nav"><s:text name="sdp.sce.dp.admin.role.edit.role"/></div>
<s:form id="uspRoleForm" action="role!modifyRole.action" method="post">
<s:hidden name="role.id" value="%{role.id}" />
<s:hidden name="role.createdUser" value="%{role.createdUser }"></s:hidden>
<s:hidden name="role.createdDate" value="%{role.createdDate }"></s:hidden>
<s:hidden name="role.name" value="%{role.name }"></s:hidden>
<div class="data_view">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.role.roleName"/></th>
    <td><input id="roleName" name="role.name" type="text" value="${role.name}" disabled="disabled"/><span class="must_input">*</span>
    <!-- <font color='gray'>&nbsp;由字母、数字、下划线、中文、空格组成</font> -->

    </td>
  </tr>
  <tr>
  <th scope="row"><s:text name="sdp.sce.dp.admin.role.roleDescription"/></th>
    <td><textarea name="role.description" cols="100" rows="4" ><s:property value="role.description"/></textarea>
    <span class="notes"></span></td>
  </tr>
  <tr>
  <th scope="row"><s:text name="sdp.sce.dp.admin.role.hold.premission"/></th>
  <td>
  	<div id="treecontrol" style="float: none;">
		<a href="?#"><s:text name="sdp.sce.dp.admin.role.all.collapse"/></a> | <a href="?#"><s:text name="sdp.sce.dp.admin.role.all.expand"/></a>
	</div>
	<div id="main">
		<ul id="browser" class="filetree">
			<s:iterator value="res" status="st">
				<li id="li_<s:property value='id' />">
					<span class="folder"><input type="checkbox"	name="rolePerMap.<s:property value="enName" />"	id="<s:property value='id' />"
											<s:if test="rolePerMap[enName]">
												checked="checked" value='true'
											</s:if>
											onclick="selectCheckboxByPid(this.id)" />
										 <s:property value="name" />
					</span>
					<s:if test="res.size > 0">
						<ul>
							<s:iterator value="res" status="chiled">
								<li id="li_<s:property value='parentRes.id' />_<s:property value='id' />">
									<span class="file"><input type="checkbox" name="rolePerMap.<s:property value="enName" />"
									 						<s:if test="rolePerMap[enName]">
																checked="checked" value='true'
															</s:if>
															id="<s:property value='id' />" onclick="selectCheckboxByPid(this.id)" />
														<s:property value="name" />
									</span>
									<s:if test="res.size > 0">
										<ul>
											<s:iterator value="res" status="st">
												<li id="li_<s:property value='parentRes.parentRes.id' />_<s:property value='parentRes.id' />_<s:property value='id' />">
													<span class="file">
														<input type="checkbox" name="rolePerMap.<s:property value="enName" />"
																			<s:if test="rolePerMap[enName]">
																				checked="checked" value='true'
																			</s:if>
																			id="<s:property value='id' />" onclick="selectCheckboxByPid(this.id)" />
														<s:property value="name" />
													</span>
													<s:if test="res.size > 0">
														<ul>
															<s:iterator value="res" status="st">
																<li id="li_<s:property value='parentRes.parentRes.parentRes.id' />_<s:property value='parentRes.parentRes.id' />_<s:property value='parentRes.id' />_<s:property value='id' />">
																	<span class="file">
																		<input type="checkbox" name="rolePerMap.<s:property value="enName" />"
																							<s:if test="rolePerMap[enName]">
																								checked="checked" value='true'
																							</s:if>
																							id="<s:property value='id' />" onclick="selectCheckboxByPid(this.id)" />
																		<s:property value="name" />
																	</span></li>
															</s:iterator>
														</ul>
													</s:if>

													</li>
											</s:iterator>
										</ul>
									</s:if>
								</li>
							</s:iterator>
						</ul>
					</s:if>
				</li>
			</s:iterator>
		</ul>
	</div>
	<p>
		<span class="notes"></span>
	</p>
   </td>
  </tr>
</table>
</div>
<div class="btnlistbar">
<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>" onclick="doSave()"/>
<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back()"/>
</div>
</s:form>

</body>
</html>
