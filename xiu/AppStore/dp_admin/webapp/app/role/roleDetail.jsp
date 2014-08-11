<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="sdp.sce.dp.admin.role.role.detail"/></title>

<script type="text/javascript">

function edit()
{
	var id = document.getElementById("id").value;
	location.href = 'role!showModify.action?roleId='+id;
}

</script>

</head>

<body id="cnt_body">
<div id="position">
 <p><s:text name="sdp.sce.dp.admin.global.label.current.position"/><s:text name="sdp.sce.dp.admin.role"/> &gt; <s:text name="sdp.sce.dp.admin.role.role.detail"/>
  <div id="pright"></div>
</div>
<div class="view_nav"><s:text name="sdp.sce.dp.admin.role.role.detail"/></div>
<s:form id="uspRoleForm" action="role!modifyRole.action" method="post">
<s:hidden name="role.id" id="id" value="%{role.id}" />
<s:hidden name="role.createdUser" value="%{role.createdUser }"></s:hidden>
<s:hidden name="role.createdDate" value="%{role.createdDate }"></s:hidden>
<div class="data_view">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <th scope="row"><s:text name="sdp.sce.dp.admin.role.roleName"/></th>
    <td><input id="roleName" name="role.name" type="text" value="${role.name}" disabled="disabled"/>
    </td>
  </tr>
  <tr>
  <th scope="row"><s:text name="sdp.sce.dp.admin.role.roleDescription"/></th>
    <td><textarea disabled="disabled" name="role.description" cols="100" rows="4" ><s:property value="role.description" /></textarea>
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
					<span class="folder"><input disabled="disabled" type="checkbox"	name="rolePerMap.<s:property value="enName" />"	id="<s:property value='id' />"
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
									<span class="file"><input disabled="disabled" type="checkbox" name="rolePerMap.<s:property value="enName" />"
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
														<input disabled="disabled" type="checkbox" name="rolePerMap.<s:property value="enName" />"
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
																		<input disabled="disabled" type="checkbox" name="rolePerMap.<s:property value="enName" />"
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
<s:if test="#session.userResMap.modifyRole != null">
	<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.edit'/>" onclick="edit()"/>
</s:if>
<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>" onclick="javascript:history.back()"/>
</div>
</s:form>

</body>
</html>
