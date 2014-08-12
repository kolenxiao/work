<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>开发者平台(DP)</title>

<script type="text/javascript">
function closewindow(){
	history.back();
    window.close(); 
}
function clock(){
 i = i -1;
 if(document.getElementById("info")){
  document.getElementById("info").innerHTML = "本窗口将在"+i+"秒后自动关闭";
 }
 if(i > 0)
  setTimeout("clock();",1000);
 else
  closewindow();
}

var i = 8;
clock();

</script>
</head>
<body onunload="window.opener.location.reload();">
<div class="view_nav">
<center>
 操作失败！<br>
 <span style="color:red">提示信息：<s:property value="errorMsg"/></span>
 <div id="info">本窗口将在7秒后自动关闭</div>
 <input type="button" value="关闭窗口" onclick="closewindow();">
</center>
</div>
</body>
</html>
