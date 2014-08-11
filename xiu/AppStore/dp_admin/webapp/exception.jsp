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
	
	<%-- <table cellpadding="0" cellspacing="0" width="706" height="454" align="center" border="0" background="<%=request.getContextPath()%>/images/error_bg01.png">
		<tr><td height="1" colspan="3">&nbsp;</td></tr>
		<tr>
			<td width="250">&nbsp;</td>
			<td align="right" width="290"   height="140" valign="top">
				<table width="226" height="100%" cellpadding="0" cellspacing="0" border="0"  background="<%=request.getContextPath()%>/images/error_bg02.png">
					<tr>
						<td height="60%" align="center"><span style="color:red"></span></td>
					</tr>
					<tr>
						<td >&nbsp;</td>
					</tr>
				</table>
			</td>
			<td >&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td colspan="2" height="250" align="left" valign="top">
				<img src="<%=request.getContextPath()%>/images/error-icon02.png" />
			</td>
			
		</tr>
		<div align="center" >
		<input class="inputstyle" type="button" value="返回" onclick="javascript:history.back(-1)"/>
	</div>
	</table>--%>
	
	<div id="position">
		<p>当前位置: 异常信息</p>
		<div id="pright"></div>
	</div>	
	
	
</body>
</html>

