<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/includejs.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>应用商店管理后台</title>
<script>
/* 	window.onload = function() {
		 $.ajax({
			 	url:'uspservice!doTask.action?uspService.uspServStatus=1001',
			    type: 'POST',
			    dataType: 'json',
			    error: function(){
			    	 dialogList('提示信息',300,150,'Error',0,0,1,this);
			    },
			    success: function(response){
			     	var dataObj = eval(response);
					if (dataObj && dataObj.success) {
						var table = document.getElementById("taskpanel");
						//alert(JSON.stringify(dataObj));
						if (dataObj.data.length > 0) {
							for(var i=0;i<dataObj.data.length;i++) {
								var tr = table.insertRow(table.rows.length);
								var td = tr.insertCell(0);
								td.innerHTML="<a href='"+dataObj.data[i].url+"' target='mainFrame'>"+dataObj.data[i].title+"</a>";
							}
						} else {
							var tr = table.insertRow(0);
							var td = tr.insertCell(0);
							td.innerHTML="暂无待办";
						}
					} else {
						if (dataObj && dataObj.msg) {
							dialogList('提示信息',300,150,dataObj.msg,0,0,1,this);
						}
					}
			    }
			});
	} */
</script>
</head>

<body id="index_body">
	<div id="position">
		<p>当前位置：首页
		<div id="pright"></div>
	</div>

	<div class="view_nav" id="nav01">
		<span class="float_l">欢迎进入应用商店管理后台</span><span class="icon_down"
			onclick="ShowHideBox(this,'box1','nav01')"></span>
	</div>
	<div id="box1">
		<div class="data_view">
			<table border="0" cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<td>
						<table border="0" cellspacing="0" cellpadding="0" width="100%" id="taskpanel" class="data_edit"></table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>
