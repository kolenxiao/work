<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="resource/resource.jsp"></jsp:include>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者平台(DP)异常信息</title>
<script type="text/javascript">
function errorMsg() {
	var msg = '<s:property value="msg"/>';
	var exception_msg = '<s:property value="exception_msg"/>';
	if(msg != null && msg.trim().length > 0) {
		dialogList('提示信息',300,150,msg,0,0,1,this);	
		$("#dialog_btn_conform").click(function(){
			javascript:history.back(-1);
		});
	}else if(exception_msg != null && exception_msg.trim().length > 0){
	    dialogList('提示信息',300,150,exception_msg,0,0,1,this);	
		$("#dialog_btn_conform").click(function(){
			javascript:history.back(-1);
		});
	}else{
		dialogList('提示信息',300,150,'系统异常请联系管理员',0,0,1,this);	
		$("#dialog_btn_conform").click(function(){
			javascript:history.back(-1);
		});
	}
}

</script>
</head>
<body id="cnt_body" onload="errorMsg()">
	
	<div id="position">
		<p>当前位置: 异常信息</p>
		<div id="pright"></div>
	</div>	
	
	
</body>
</html>

