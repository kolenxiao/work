<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<% 
  String path =request.getContextPath();
%>
<link rel="stylesheet" type="text/css" href="<%=path %>/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/icon.css">
<script type="text/javascript" src="<%=path %>/easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path %>/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=path %>/resource/js/formatter.js"></script>
<style>
table.input{
	border-collapse:collapse;width:100%;
}
table.input input{
	border:1px solid #AAA;
}
table.input td,table.input th{
	border:1px solid #96b7e0;padding:3px;
}
table.input th{
	background-color:#E6F0F6;
}
table.input td.color{
	background-color:#F7FAFF;
}
</style>