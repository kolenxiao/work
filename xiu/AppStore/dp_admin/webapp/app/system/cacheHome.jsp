<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>缓存管理</title>
<script type="text/javascript">

	$(document).ready(function() {
		
		//刷新系统缓存
		$("#btn_refresh").click(function(){
			$("#div_result").text("正在刷新中...");
			time(this, "刷新系统缓存");
			
			var url = "systemAdmin!refreshCache.action";
			$.getJSON(url, function(data){
			  if (data.success) {
			  	$("#div_result").text("刷新成功!");
			  }else{
				$("#div_result").text("刷新失败: "+data.msg);  
			  }
			  
			})
		});
		
		//同步数据到搜索引擎
		$("#btn_synData").click(function(){
			$("#div_result").text("正在同步中...");
			time(this, "同步数据到搜索引擎");
			
			var url = "systemAdmin!syncDataToSearch.action";
			$.getJSON(url, function(data){
			  if (data.success) {
			  	$("#div_result").text("同步成功!");
			  }else{
				$("#div_result").text("同步失败: "+data.msg);  
			  }
			  
			})
		});

	})
	

	//倒计时
	var seconds = 20;
	var wait = seconds;
	function time(o,msg) {
		if (wait == 0) {
			o.removeAttribute("disabled");
			o.value = msg;
			wait = seconds;
		} else {
			o.setAttribute("disabled", true);
			o.value = "重新操作(" + wait + ")";
			wait--;
			setTimeout(function() {
				time(o, msg)
			}, 1000)
		}
	}

</script>

</head>

<body id="cnt_body">
	<div id="position">
		<p>当前位置: 系统管理&gt; 缓存管理</p>
		<div id="pright"></div>
	</div>
	<div>
	
		<div id="div_result" style="color: red">
		</div>
		
		<div id="search_itemlist">
			<s:form id="mainForm" name="mainForm" action="" method="post" theme="simple">
				<ul>
					<li><input type="button" id="btn_refresh" value="刷新系统缓存" /></li>
					<li><input type="button" id="btn_synData" value="同步数据到搜索引擎" /></li>
				</ul>
			</s:form>
		</div>

	</div>

</body>
</html>
