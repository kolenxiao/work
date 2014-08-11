<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>开发者平台(USP)</title>

<script type="text/javascript">
function closewindow(){
// window.returnValue = true;
// window.close();
//   history.back();
	history.back();
    self.location.reload();
}
function clock(){

 if(document.getElementById("info")){
  document.getElementById("info").innerHTML = "本窗口将在"+i+"秒后自动关闭";
 }
 i = i -1;
 if(i >= 0)
  setTimeout("clock();",1000);
 else
  closewindow();
}

var i = 3;
clock();

</script>
</head>
<body>
<center>
 权限管理<br>
<span style="color:red">错误提示：<s:property value="errorMsg"/></span><p>
 <div id="info">本窗口将在3秒后自动关闭</div>
 <input type="button" value="关闭窗口" onclick="closewindow();">
</center>
</body>
</html>
