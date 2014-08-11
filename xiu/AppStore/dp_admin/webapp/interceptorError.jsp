<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>开发者平台(DP)异常信息</title>
<script type="text/javascript">
function errorMsg() {
	var msg = '<s:property value="#session.error"/>';
	
	if(msg != null && msg.trim().length > 0) {
		dialogList('提示信息',300,150,msg +"!",0,0,1,this);	
		$("#dialog_btn_conform").click(function(){
			javascript:history.back(-1);
		});
	}else
	{
		dialogList('提示信息',300,150,'系统异常请联系管理员',0,0,1,this);	
		$("#dialog_btn_conform").click(function(){
			javascript:history.back(-1);
		});
	}
}

</script>
</head>
<body id="cnt_body" onload="errorMsg()">
	 <s:if test="hasActionErrors()"> 
       <s:iterator value="actionErrors">   
            <s:property/>   
       </s:iterator> 
      </s:if> 
	
	
	<div id="position">
		<p>当前位置: 异常信息</p>
		<div id="pright"></div>
	</div>	
	
	
</body>
</html>

