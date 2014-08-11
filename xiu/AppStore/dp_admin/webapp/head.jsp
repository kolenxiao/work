<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用商店管理后台</title>
</head>
<% if(request.getSession().getAttribute("user")==null){ %>
     <script type="text/javascript">
         top.location.href="login.jsp";
     </script>
<%
}
%>

<body>
<div id="header">
<div id="logo"><h1>应用商店管理后台</h1><!--  img src="images/logo.jpg" width="319" height="19" alt="开发者管理后台" /--></div>
<div id="nav_top">
<ul>
<li>用户名：<a href="user!userDetail.action" target="mainFrame" id="navtop_editpwd"><%=request.getSession().getAttribute("userName") %></a></li>
<li>|</li>
<li id="home"><a href="<%=ctxPath%>/index.jsp" target="mainFrame" >首页</a></li>
<li>|</li>
<li><a href="user!gotoEditPassword.action" target="mainFrame" id="navtop_editpwd">修改密码</a></li>
<li>|</li>
<li><a href="#" id="navtop_logout" onclick="top.location.href='user!logout.action';" style="padding-left:18px;">注销</a></li>
</ul>
</div>
</div>
</body>
</html>
