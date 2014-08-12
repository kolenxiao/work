<%@ page language="java" pageEncoding="UTF-8"%>
<% 
  String path =request.getContextPath();
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<style type="text/css">
body {
	font-size: 12px;
	margin: 0;
	padding: 0;
	background: #93bbe5 url(resource/images/load_bg.jpg) top center no-repeat;
	color: #3a69ad;
}

.lo_tit {
	margin: 8px auto;
	margin-top: 104px;
	width: 458px;
	height: 19px;
}

.load {
	margin: 0 auto;
	width: 458px;
	height: 301px;
	background: url(resource/images/lo_line.jpg) repeat-x;
}

.load .side {
	float: left;
	width: 10px;
	height: 301px;
}

.load .ct {
	float: left;
	width: 438px;
	margin: 26px 0px;
	overflow: hidden;
}

.load .ct .tit {
	height: 29px;
	margin: 0px 60px;
	margin-bottom: 25px;
}

.lo_ct {
	clear: both;
	padding: 0;
	margin-bottom: 10px;
	width: 338px;
	margin: 0px 50px;
}

.lo_ct input {
	padding: 0;
	margin: 0;
	background: url(resource/images/load_in1.jpg) no-repeat;
	width: 137px;
	height: 23px;
	line-height: 23px;
	border: none;
	padding-left: 3px;
	color: #000000;
	padding-top: 3px; *
	padding-top: 1px;
}

.lo_ct span {
	float: left;
	width: 75px;
	padding-right: 4px;
}

lo_ct span .img1 {
	float: left;
	width: 62px;
}

.lo_ct span input {
	width: 75px;
	background: url(resource/images/load_in2.jpg) no-repeat;
}

.lo_ct .bt {
	width: 81px;
	height: 30px;
	background: none;
}

.at {
	height: 30px;
	padding-left: 8px;
	margin-top: 40px;
	color: #4C4C4C;
	text-align: left;
}

#b_at {
	position: absolute;
	top: 470px;
	left: 0;
	width: 100%;
	height: 30px;
	text-align: center;
}

.bot input {
	width: 81px;
	height: 30px;
	border: none;
	background: url(resource/images/bu_load1.jpg) no-repeat;
}

.bot input:hover {
	background: url(resource/images/bu_load2.jpg) no-repeat;
}

.red {
	color: #FF0000;
	font-size:10px;
}

/* dialog start*/
#dialogbg {
	width: 100%;
	height: auto;
	position: absolute;
	left: 0;
	top: 0;
	z-index: 20;
	background: #666;
	opacity: 0.3;
	filter: alpha(opacity = 30);
	-moz-opacity: 0.3;
}

#dialog {
	width: 500px;
	height: 300px;
	border: 1px solid #86B4E5;
	position: absolute;
	left: 0;
	top: 0;
	z-index: 21;
	font-family: Arial, Helvetica, sans-serif "瀹嬩綋";
	background: #FFF;
}

#dialog #dialog_title,#dialog h2 {
	font-size: 14px;
	height: 22px;
	line-height: normal;
	padding: 9px 0 0 8px;
	color: #0067B2;
	cursor: move;
	border-bottom: 1px solid #86B4E5;
	position: relative;
	background: #E9F2FB url(resource/images/bg_cntnav.jpg) center repeat-x;
}

#btn_closedialog {
	width: 16px;
	height: 16px;
	position: absolute;
	right: 8px;
	top: 8px;
	cursor: pointer;
	background: url(resource/images/btn_close.png) no-repeat;
}

#cnt_list {
	overflow: auto;
	overflow-x: hidden;
	margin: 5px 0 0 0;
	padding: 0 0 0 5px;
	position: relative;
}

#cnt_list table {
	text-align: center;
	text-align: justify;
	text-justify: inter-ideograph;
}

#cnt_list td {
	text-align: center;
}

#cnt_list td img {
	margin-right: 5px;
	position: relative;
	top: 2px;
}

#btn_list {
	height: 20px;
	padding: 8px 0;
	position: absolute;
	left: 0;
	bottom: 0;
	text-align: center;
	border-top: 1px dashed #CCC;
}

#btn_list input {
	width: 61px;
	height: 20px;
	border: none;
	background: url(resource/images/but_guild_1.gif) no-repeat;
}

#btn_list input:hover {
	background: url(resource/images/but_guild_2.gif) no-repeat;
}

.page_line .bu button,.info button,#pcolumn_delete button,.page_line .bu input,.info input,#pcolumn_delete input
	{
	float: center;
	padding: 0;
	margin: 0;
	border: none;
	text-align: center;
	margin-top: 5px;
	width: 100px;
	height: 20px;
	background: url(resource/images/but_guild_1.gif) top center no-repeat;
	color: #006ec1;
	font-size: 12px;
	line-height: 20px;
	font-weight: 100;
	word-spacing: 0;
}
.fix_inputbg{
	background:url(resource/images/bg_logininout.jpg) no-repeat;
}
.fix_inputbg input{
        background:none;
}
</style>
<title>深度EPG管理</title>
</head>
<body style="text-align:center">
		<div class="lo_tit">
			
		</div>
		<div class="load">
			<div class="side">
				<img src="resource/images/le_line.jpg" width="10" height="301">
			</div>
			<div class="ct">
				<div class="tit">
					<table>
					<tr><td><img src="resource/images/load_091009_14.jpg" height="29">
					<td style="font-size:20px;font-weight:bold">深度EPG管理
					</table>
				</div>
				<div class="lo_status">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
				</div>
				<div class="lo_ct">
					<form action="user_login" method="post" id="login">
						<input type="hidden" name="showSuccessDialog" value="" id="showSuccessDialog">
						<input type="hidden" name="coship_iepgm_login" value="true">
						<table width="338" border="0" cellpadding="0" cellspacing="0">
							<tbody><tr>
								<td width="25%" height="37" align="right">
									用户&nbsp;&nbsp;
								</td>
								<td width="47%" height="37">
									<div class="fix_inputbg">
										<input type="text" name="userName" value="" id="loginName" class="valid">
									</div>
								</td>
								<td width="28%" height="37" class="red" id="errorMessage">
									${message}
								</td>
							</tr>
							<tr>
								<td height="37" align="right">
									密码&nbsp;&nbsp;
								</td>
								<td height="37">
									<div class="fix_inputbg">
										<input type="password" name="password" id="textfield">
									</div>
								</td>
								<td height="37" class="red">
									
								</td>
							</tr>

							<tr>
								<td height="37">
									&nbsp;
								</td>
								<td height="37" class="bot">
									<input type="submit" style="cursor:pointer;" value="" id="imageField" src="resource/images/bu_load1.jpg">
								</td>
								<td height="37">
									&nbsp;
								</td>
							</tr>
						</tbody></table>
					</form>
				</div>
				<div class="at">
					<br>注：忘记密码，请联系管理员。
				</div>
			</div>
			<div class="side">
				<img src="resource/images/ri_line.jpg">
			</div>
		</div>
		<div id="b_at">
			© 深圳市同洲电子股份有限公司版权所有
		</div>
</body>
</html>