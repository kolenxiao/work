<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- 引入管理平台使用到的所有js和css -->
<html>
<head>
<%@ include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>应用商店管理后台</title>
</head>
<body>
  <iframe src="<%=ctxPath%>/head.jsp" name="topFrame" scrolling="no" frameborder="0" noresize="noresize" id="topFrame" title="topFrame" style="border: 0px;width: 100%;height: 50px;margin: 0px 0px;padding: 0px 0px"></iframe>
  <iframe src="<%=ctxPath%>/menu.jsp" name="leftFrame" scrolling="no" frameborder="0"  marginwidth="0px" noresize="noresize" id="leftFrame" title="leftFrame" style="border: 0px;width: 15%;height: 743px;margin: 0px 0px;padding: 0px 0px"></iframe>
  <iframe src="<%=ctxPath%>/index.jsp" name="mainFrame" id="mainFrame" frameborder="0"  marginwidth="0px" title="mainFrame" style="border: 0px;width:84%;height: 743px;margin-left: 0;margin-top: 0;margin-right: 0;margin-bottom: 0;"></iframe>
  <iframe src="<%=ctxPath%>/foot.jsp" name="footFrame" scrolling="no" frameborder="0" marginwidth="0px" noresize="noresize" id="footFrame" title="footFrame" style="border: 0px;width: 100%;height: 30px;margin: 0px 0px;padding: 0px 0px"></iframe>
</body>
</html>
