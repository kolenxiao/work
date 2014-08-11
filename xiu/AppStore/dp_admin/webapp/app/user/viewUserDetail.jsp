<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>服务注册</title>

<script type="text/javascript">
	function edit() {
		var id = document.getElementById("id").value;
		location.href = "user!editUser.action?id="+id;
	}
		
</script>
</head>
<body id="cnt_body">
<div id="position">
  <p>当前位置：权限管理 &gt; 用户管理
  <div id="pright"></div>
</div>
<s:form id="usrApiForm" name="usrApiForm" action="user!updateUser.action" method="post" cssStyle="margin:0" theme="simple">
<s:hidden name="user.password" value="%{user.password}" />
<s:hidden name="user.id" id="id" value="%{user.id}" />
<s:hidden name="user.createdDate" value="%{user.createdDate}" />
<s:hidden name="user.createdUser" value="%{user.createdUser}" />
<s:hidden name="user.userName" value="%{user.userName}" />

<div class="view_nav"  id="nav01" ><span class="float_l">用户详细信息</span><span class="icon_down" onclick="ShowHideBox(this,'box1','nav01')" ></span></div>
<div id="box1">
<div class="data_view">
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <th scope="row">用户名：</th>
      <td width="" colspan="3">
  		 <s:textfield  id="userName" name=""  size="64" maxlength="64" value="%{user.userName}" disabled="true" />    		  
      </td>      
    </tr>
    
    <tr>
      <th scope="row">真实姓名：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="realName" name="user.realName"  size="64" value="%{user.realName}" disabled="true"/>
      </td>
    </tr>
    <tr>
    	<th scope="row">电子邮箱：</th>
      <td id="upLoadTd" colspan="3">
			<s:textfield  id="email" name="user.email"  size="64" value="%{user.email}" disabled="true"/>
      </td>
    </tr>
    <tr>
      <th scope="row">联系电话：</th>
      <td  id="upLoadTd" colspan="3">
			<s:textfield  id="telephone" name="user.telephone"  size="20" value="%{user.telephone}" disabled="true"/>
      </td>
    </tr>
    <tr>
      <th scope="row">是否有效：</th>     
      <td id="upLoadTd" colspan="3">
			<select name="user.status" disabled="disabled">
			<s:if test='user.status == "Y"'>
				<option value="Y">是</option>
				<option value="N">否</option>
			</s:if>
			<s:elseif test='user.status == "N"'>
				<option value="N">否</option>
				<option value="Y">是</option>
			</s:elseif>
			<s:else>
				<option value="Y">是</option>
				<option value="N">否</option>
			</s:else>
				
			</select><span class="style-red">*</span>
      </td>
    </tr>    
  
     
     <tr>
      <th scope="row">分配角色：</th>   
      <td width="10%" align="left">
       <table width="50%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="center" style="font-weight: bold;border-style: none;" >系统拥有的角色</td>
  </tr>
  <tr>
    <td style="border-style: none;"><select multiple id="select1" style="width:200px;height:160px;" disabled="disabled">
	<s:iterator value="allRoleList" status="st"  >
		<option value="<s:property value='id' />"><s:property value="name" /></option>
	</s:iterator>		
	</select>	</td>
  </tr>
</table>
   
</td>
	<td width="77" style="width:30px;text-align:center;">
	<div>
      <ul>        
        <li><input name="" type="button" value="&gt;&gt;" onClick="add_all()" disabled="disabled"/></li>
        <li><input name="" type="button" value="&gt;" onClick="add()" disabled="disabled"/></li>
        <li><input name="" type="button" value="&lt;" onClick="remove()" disabled="disabled"/></li>
        <li><input name="" type="button" value="&lt;&lt;" onClick="remove_all()" disabled="disabled"/></li>
      
      </ul>
    </div>
    </td>
<td width="431">

<table width="50%" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td align="center" style="font-weight: bold;border-style: none;" >
	用户所属角色</td>
  </tr>
  <tr>
    <td style="border-style: none;">
	<select multiple id ="select2" style="width:200px;height:160px;" name="selectRole" disabled="disabled">
		<s:iterator value="userRoleList" status="st"  >
			<option value="<s:property value='id' />"><s:property value="name" /></option>
		</s:iterator>
	</select>	
		</td>
  </tr>
    
     
  </table>
   </table>
</div>
</div>

<div class="btnlistbar" align="left">
<s:if test="#session.userResMap.modifyUser != null">
  <input class="inputstyle" type="button" value="编辑" onclick="edit()"/>
  </s:if>
  <input class="inputstyle" type="button" value="返回" onclick="javascript:history.back()"/>
</div>

</s:form>




</body>
</html>
