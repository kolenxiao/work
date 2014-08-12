<%@ page pageEncoding="utf-8"%>
<%@ include file="resource/resource.jsp" %> 
<html><head>
<meta charset="UTF-8">
<title>深度EPG管理</title>
<link rel="stylesheet" type="text/css" href="<%=path %>/resource/css/default.css">
<script type="text/javascript" src='<%=path %>/resource/js/outlook.js'> </script>
<style>
#west li {
	list-style-type:none;padding-left:10px;
}
#west li a{
	font-size:13px;width:80%;display:block;padding-left:24px;
	border:1px solid #FFF;line-height:30px; background-position:3 8;
	background-repeat:no-repeat;
}
#west li a:hover{
	border:1px dashed #416AA3;color:#416AA3;background-color:#D9E8FF;
}
.icon-program-type{ 
	background:url(resource/images/videos.png);
}
.icon-program-content{ 
	background:url(resource/images/video.png);
}
.icon-program{ 
	background:url(resource/images/program.png);
}
.icon-channel{ 
	background:url(resource/images/channel.png);
}
.icon-live{ 
	background:url(resource/images/look_live.png);
}
.icon-btv{ 
	background:url(resource/images/look_back.png);
}
</style>
</head>
<body class="easyui-layout" style="overflow-y:hidden" scroll="no">
	<div region="west" hide="true" split="true" title="深度EPG导航" style="width:150px;overflow:hidden" id="west">
		<div class="easyui-accordion" border="false" animate="false" multiple="true">
			<div title="业务管理" >			
				<li><a href="#" class="icon-channel" onclick="addTab('频道管理','content/channel_list.jsp','icon-channel')">频道管理</a>
				<li><a href="#" class="icon-program-type" onclick="addTab('分类管理','content/program_type_list.jsp','icon-program-type')">分类管理</a>
				<li><a href="#" class="icon-program-content" onclick="addTab('节目管理','content/program_content_list.jsp','icon-program-content')">节目管理</a>
				<li><a href="#" class="icon-program" onclick="addTab('节目单管理','content/program.jsp','icon-program')">节目单管理</a>
			</div>
			<div title="业务预览">
				<li><a href="#" class="icon-live" onclick="addTab('直播预览','content/depg/getLivePrograms.jsp','icon-live')">直播预览</a>
				<li><a href="#" class="icon-btv" onclick="addTab('一周回看预览','content/depg/getBTVPrograms.jsp','icon-btv')">回看预览</a>
			</div>
		</div>
	</div>
	
	<div region="south" border="0" style="height:50px;padding:10px;text-align:center">
		&copy;深圳市同洲电子股份有限公司版权所有 
	</div>
		
	<div region="north" split="false" border="false" style="overflow: hidden; height: 40px;
		background: url(<%=path%>/resource/images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
		line-height: 40px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
	<span style="float:right; padding-right:20px;" class="head">
		你好,${userName}&nbsp;&nbsp;&nbsp; <a href="<%=path %>/user_logout"
		id="loginOut">安全退出</a>
	</span>
	<span style="padding-left:10px; font-size: 16px; font-family:'华文楷体'; float:center;">深度EPG管理系统 </span>
	<ul id="css3menu"
		style="padding:0px; margin:0px;list-type:none; float:left; margin-left:40px;">
		<li><a class="active" name="basic" href="javascript:;" title=""></a>
		</li>
	</ul>
	</div>
		
	<div region="center" id="mainPanle" style="background: #eee; overflow-y:hidden;background-color:#FFF">
		<div id="tabs" class="easyui-tabs" fit="true" border="false"></div>
	</div>
</body>
</html>