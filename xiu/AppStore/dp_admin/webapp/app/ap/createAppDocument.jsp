<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/common/includejs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>创建应用文档</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/uploadify/uploadify.css">
<script src="${pageContext.request.contextPath}/uploadify/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/upload.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/uploadify/common.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/js/tag/js/jquery-ui-1.8.20.custom.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tag/js/jquery.taghandler.js" type="text/javascript" language="JavaScript"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/tag/css/jquery.taghandler.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/tag/css/custom-theme/jquery-ui-1.8.7.custom.css" type="text/css" media="all" />
<script type="text/javascript">
var apk={
		multiple:true,
		ID:"apk",
		contextPath:'${pageContext.request.contextPath}',
		requestURL:'/upload!doUpload.action;jsessionid=${pageContext.session.id}',
		fileObjName:'file',
		fileType:'*.apk;*.APK',
		formdata:{'flag':'1'},
		parentDIV:'div_app',
		buttonImage:'${pageContext.request.contextPath}/uploadify/browse-btn.png',
		err_msg_id:'errMsg_apkFileSaveName',
		callback:onUploadApkSuccess
};

var logos={
		multiple:true,
		ID:"logos",
		contextPath:'${pageContext.request.contextPath}',
		requestURL:'/upload!doUpload.action;jsessionid=${pageContext.session.id}',
		fileObjName:'file',
		fileType:'*.gif;*.jpg;*.jpeg;*.png;*.GIF;*.JPG;*.JPEG;*.PNG',
		formdata:{'flag':'2'},
		parentDIV:'div_logo',
		buttonImage:'${pageContext.request.contextPath}/uploadify/browse-btn.png',
		err_msg_id:'errMsg_logoFileSaveName',
		callback:onUploadLogoSuccess
};

var imgs={
		multiple:true,
		ID:"imgs",
		contextPath:'${pageContext.request.contextPath}',
		requestURL:'/upload!doUpload.action;jsessionid=${pageContext.session.id}',
		fileObjName:'file',
		fileType:'*.gif;*.jpg;*.jpeg;*.png;*.GIF;*.JPG;*.JPEG;*.PNG',
		<!-- formdata:{'flag':'3','pictrueWidth':'950','pictrueHeight':'534'}, -->
		formdata:{'flag':'3'},
		parentDIV:'div_img',
		buttonImage:'${pageContext.request.contextPath}/uploadify/browse-btn.png',
		err_msg_id:'errMsg_imgFileSaveName',
		callback:onUploadImgSuccess
};

var posters={
		multiple:true,
		ID:"posters",
		contextPath:'${pageContext.request.contextPath}',
		requestURL:'/upload!doUpload.action;jsessionid=${pageContext.session.id}',
		fileObjName:'file',
		fileType:'*.gif;*.jpg;*.jpeg;*.png;*.GIF;*.JPG;*.JPEG;*.PNG',
		<!-- formdata:{'flag':'4','pictrueWidth':'820','pictrueHeight':'354'}, -->
		formdata:{'flag':'4'},
		parentDIV:'div_poster',
		buttonImage:'${pageContext.request.contextPath}/uploadify/browse-btn.png',
		err_msg_id:'errMsg_posterFileSaveName',
		callback:onUploadPostersSuccess
};

var subjectPoster={
		inputID :'appInfo.subjectPoster',//文件实际存储名称
		multiple:true,
		ID:"file4",
		contextPath:'${pageContext.request.contextPath}',
		requestURL:'/upload!doUpload.action;jsessionid=${pageContext.session.id}',
		fileObjName:'file',
		fileType:'*.gif;*.jpg;*.jpeg;*.png;*.GIF;*.JPG;*.JPEG;*.PNG',
		<!-- formdata:{'flag':'4','pictrueWidth':'202','pictrueHeight':'282'}, -->
		formdata:{'flag':'4'},
		parentDIV:'div_subjectPoster',
		buttonImage:'${pageContext.request.contextPath}/uploadify/browse-btn.png',
		err_msg_id:'errMsg_subjectPoster',
		callback:onUploadSingleFileSuccess
};

var gameLogos={
		multiple:true,
		ID:"gameLogos",
		contextPath:'${pageContext.request.contextPath}',
		requestURL:'/upload!doUpload.action;jsessionid=${pageContext.session.id}',
		fileObjName:'file',
		fileType:'*.gif;*.jpg;*.jpeg;*.png;*.GIF;*.JPG;*.JPEG;*.PNG',
		formdata:{'flag':'5'},
		parentDIV:'div_gameLogo',
		buttonImage:'${pageContext.request.contextPath}/uploadify/browse-btn.png',
		err_msg_id:'errMsg_gameLogoFileSaveName',
		callback:onUploadGameLogoSuccess
};


$(document).ready(function(){

	var  uploadApk = new SimpleUpload(apk);
	uploadApk.bindUplodify();
	
 	var  uploadLogos = new SimpleUpload(logos);
	uploadLogos.bindUplodify();
	
/* 	$("#logos").click(function(){
		initPictrueSize();
	}); */
	
	var  uploadImgs = new SimpleUpload(imgs);
	uploadImgs.bindUplodify();
	
	var  uploadPosters = new SimpleUpload(posters);
	uploadPosters.bindUplodify();
	
	var uploadSubjectPoster = new SingleUpload(subjectPoster);
	uploadSubjectPoster.bindUplodify();
	
 	var  uploadGameLogos = new SimpleUpload(gameLogos);
 	uploadGameLogos.bindUplodify();
	
	var appInfoPrice = $("#appInfoPriceForjs").val();
	if(appInfoPrice>0)
	{
	    var appPrice = $("input[name='appInfo.price']");
	    var radio = $(":radio[value='1']");
	    radio.attr("checked", true);
	    appPrice.css("display", "");
	}
	
	errorMsg();
	
	//应用标签设置
	$('#tags').tagHandler({
		//assignedTags: [ 'APP'],
		//availableTags: [ '生活', '娱乐', '财经', '教育', '工具', '书籍', '新闻', '健康', '音乐', '角色扮演','益智','动作冒险','赛车竞技','棋牌桌游','射击','体育运动'],
		autocomplete: true,
		maxTags:5,
		onAdd: function(tag){
			if(tag.length < 2){
				alert("每个标签最少输入2位字符");
				return false;
			}else if(tag.length > 6){
				alert("每个标签最多输入6位字符");
				return false;
			}
			
			var addflag = true,tags = $('#tags').tagHandler("getTags");
	        jQuery.each(tags, function (i, e) {
				if(tag.toUpperCase()===e.toUpperCase()){
					$('#tags').find('.tagItem').each(function(){
						if($(this).html().toLocaleUpperCase()===tag.toLocaleUpperCase()){
							$(this).animate({opacity: 0.55}).delay(20).animate({opacity: 1}).animate({opacity: 0.55}).delay(20).animate({opacity: 1});
						}
					});
					//$('#log').hide(0).html("标签已存在").show().delay(2000).fadeOut();
					addflag = false;
				}
			});
			return addflag;
		}
	});
	
	//进入修改页面时初始化应用标签
	initAppTags();
});
</script>

<script type="text/javascript">

	//自定义输出错误信息
	FormValid.showError = function(errMsg, errName, formName) {
		if (formName == 'appUploadForm') {
			for (key in FormValid.allName) {
				document.getElementById('errMsg_' + FormValid.allName[key]).innerHTML = '';
			}
			for (key in errMsg) {
				document.getElementById('errMsg_' + errName[key]).innerHTML = errMsg[key];
			}
		}
	};

	function setAppPrice() {
		var radio = $("input:checked");
		var price = $("input[name='appInfo.price']");
		$("span[id='errMsg_appInfo.price']").empty();

		if (0 == radio.val()) {
			price.css("display", "none");
			price.val(0);
		} else {
			price.css("display", "");
		}
	}
	

	// 检查应用名称是否重复
	function checkAppName() {
		var result = true;
		// 添加时候要验证唯一性
		var operateOption = $("#operateOptionForjs").val();
		if (operateOption == 'Add') {
			var appName = $("input[name='appInfo.appName']").val();
			if ("" != appName) {
				var parm = {'appInfo.appName' : appName};
				$.ajax({
					cache : false,
					async : false,
					type : "POST",
					url : "dpAppInfo!doUniqueAppName.action",
					data : parm,
					dataType : 'json',

					success : function(response) {
						var dataObj = eval(response);
						if (dataObj && dataObj[0].success == true) {
							result = false;
							$("span[id='errMsg_appInfo.appName']").empty().html("应用名称已存在.");
						}
					}
				});
			} 
		}
		return result;
	}


	//验证表单，提交form
	function saveApp(form, op) {
		if (checkAppName() && validator(form)) {
			if(checkAppTag()==false){
				return;
			}
			var opModeLen = $("input[name=appInfo.opModeList]:checkbox:checked").length;
			if(opModeLen==0){
				$("#errMsg_opModeList").text("请选择操作类型");
				return;
			}else{
				$("#errMsg_opModeList").empty();
			}
			if (op == 'Add') {
				url = "dpAppInfo!doAddApp.action?forward=" + op;
			} else if (op == 'Modify') {
				url = "dpAppInfo!doModifyApp.action?forward=" + op;
			} else {
				url = "dpAppInfo!doUpgradeApp.action?forward=" + op;
			}
			form.action = url;
			form.submit();
		}
	}
	
	function errorMsg() {
		var msg = '<s:property value="msg"/>';
		var exception_msg = '<s:property value="exception_msg"/>';
		if(msg != null && msg.trim().length > 0) {
			dialogList('提示信息',300,150,msg,0,0,1,this);	
			$("#dialog_btn_conform").click(function(){
				parent.window.Wclose();
			});
		}else if(exception_msg != null && exception_msg.trim().length > 0){
		    dialogList('提示信息',300,150,exception_msg,0,0,1,this);	
			$("#dialog_btn_conform").click(function(){
				parent.window.Wclose();
			});
		}
	}
	
	
	//检查应用标签
	function checkAppTag(){
		var $tag = $("#tags li.tagItem");//获取标签集合
		var len = $tag.length;
		if(len < 1){
			alert("至少输入1 个标签");
			return false;
		}
		if(len > 5){
			alert("最多输入5 个标签");
			return false;
		}
		
		var appTags = new Array(len);
		$tag.each(function(i){
			appTags[i] = $(this).text();
		});
		$("#appTags").val(appTags);
		return true;
	}
	
	function initAppTags(){
		//初始化提示语
		var $tagHandler = $("div.tagHandler");
		var $tagInput = $("input.tagInputField");
		if($tagInput.val()==""){
			$tagInput.val("输入后按回车键确认");
		}
		$tagHandler.click(function(){
			if($tagInput.val()=="输入后按回车键确认"){
				$tagInput.val("");
			}
		});
		$tagInput.blur(function(){
			if($tagInput.val()==""){
				$tagInput.val("输入后按回车键确认");
			}
		})
		
		//初始化标签内容
		var $tagInputLi = $("#tags li.tagInput");//输入框
		var appTags = $("#appTags").val();
		if("" != appTags){
			var strs = appTags.split(",");//将它分割成数租
			$.each(strs, function (i, tx) { 
				$("<li class=tagItem>"+tx+"</li>").insertBefore($tagInputLi);
			});
		}
	}
</script>
</head>
<body id="cnt_body">
	<s:if test="%{appInfo.id != null }">
		<s:set name="title" value="'编辑应用'"/>
		<s:set name="module" value="'编辑应用'"/>
	</s:if>
	<s:else>
		<s:set name="title" value="'创建应用'" />
		<s:set name="module" value="'上传应用'"/>
	</s:else>
	<s:if test="%{operateOption == 'Modify'}">
	   <s:if test="%{appInfo.price == 0}">
	       <s:set name="price" value="0"/>
	   </s:if>
	   <s:else>
	       <s:set name="price" value="%{appInfo.price}"/>
	   </s:else>
	</s:if>
	<s:else>
		<s:set name="price" value="0"/>
	</s:else>
    <div id="position">
        <p> <s:text name='sdp.sce.dp.admin.global.label.current.position' />我的应用 &gt; <s:property value="#title"/></p>
    </div>
    <div class="view_nav">
        <s:property value="#module"/>
    </div>
    <s:form id="appUploadForm" name="appUploadForm" method="post" cssStyle="margin:0" theme="simple">
        <s:token/>
        <input type="hidden" value="${operateOption }" id="operateOptionForjs">
        <input type="hidden" value="${appInfo.price }" id="appInfoPriceForjs">
        <input type="hidden" value="${appInfo.id }" id="appId">
        <input type="hidden" name="flags" value="${flags}"/>
        <input type="hidden" id="appTags" name="appTags" value="${appTags}">
        <s:hidden name="appInfo.id" id="appInfo.id" value="%{appInfo.id}"/>
        <div class="data_view">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <th scope="row">应用名称 &nbsp;<strong style="color: #F00;">*</strong></th>
                <td>
                    <s:textfield type="text" name="appInfo.appName" id="appInfo.appName" valid="required|limit|regexp" regexp="^([\\w\\s\u4e00-\u9fa5]+)$"
                        min="0" max="64" errmsg="dp_appName_require|dp_appName_limit|dp_appName_regexp" value="%{appInfo.appName}" size="35" maxLength="64" />
                    
                    <span id="errMsg_appInfo.appName" style="color: #F00;"></span>
                </td>
            </tr>
            <s:if test="%{flags != 100}">
            <tr>
                <th scope="row" >应用发布包 &nbsp;<strong style="color: #F00;">*</strong><br><font color="#CC99FF">(大小必须小于或等于1GB)</font></th>
                <td>
                    <div align="left" id="div_app" style="width:200px;float:left;">
                        <input name="apk" type="file" id="apk" >
                        <s:hidden name="apkFileName" id="apkFileName"  value="%{apkFileName}"/>
                        <s:hidden name="apkFileSaveName" id="apkFileSaveName" valid="required" errmsg="download_downloadFile_notEmpty" value="%{apkFileSaveName}"/>
	                    <s:if test="%{apkFileSaveName != null}"> 
						<p id='file_${apkFileSaveName}' style="margin-left:15px;">${apkFileName}
						<a href='#' onclick="removeAndCleanApk('div_app','file_${apkFileSaveName}')"><font color="red">删除</font></a>
						</p>
					    </s:if>         
                    </div>
                    <span id="errMsg_apkFileSaveName" style="color: #FF0000"></span>  
                </td>
            </tr>
            </s:if>
            <s:if test="%{appInfo.id != null }">
	            <tr>
	                <th scope="row">版本号 &nbsp;<strong style="color: #F00;">*</strong></th>
	                <td class="editbar">
	                	<s:textfield type="text" name="appInfo.version" id="appInfo.version" valid="required" 
						      errmsg="validte_required" maxLength="50" size="15" />
						     <span id="errMsg_appInfo.version" style="margin-left:20px;color: #FF0000"></span>
	                </td>
	            </tr>
            </s:if>
            <tr>
                <th scope="row">所属分类 &nbsp;<strong style="color: #F00;">*</strong></th>
                <td>
                    <s:select id="dpType.id" name="dpType.id" value="%{appInfo.dpType.id}" headerKey="" headerValue="--请选择分类--"
                        list="dpTypeList" listKey="id" listValue="typeName" cssStyle="width:175px" valid="required" errmsg="dp_appTypeId_required">
                    </s:select>
                    <span id="errMsg_dpType.id" style="color: #FF0000"></span>
                </td>
            </tr>
            <tr>
		      <th scope="row">人工增加下载次数 &nbsp;<strong style="color: #F00;">*</strong></th>
			  <td>
			      <s:textfield type="text" name="appInfo.handDownCount" id="appInfo.handDownCount" valid="required|isNaturalInt" 
				      errmsg="validte_required|only_natural_int" maxLength="9" size="15" />
				  <span id="errMsg_appInfo.handDownCount" style="margin-left:20px;color: #FF0000"></span>
			  </td>
		    </tr>
		    <tr>
		      <th scope="row">人工增加评分人数 &nbsp;<strong style="color: #F00;">*</strong></th>
			  <td>
			      <s:textfield type="text" name="appInfo.handScoreCount" id="appInfo.handScoreCount" valid="required|isNaturalInt" 
				      errmsg="validte_required|only_natural_int" maxLength="9" size="15" />
				  <span id="errMsg_appInfo.handScoreCount" style="margin-left:20px;color: #FF0000"></span>
			  </td>
		    </tr>
		    <tr>
		      <th scope="row">人工平均评分 &nbsp;<strong style="color: #F00;">*</strong></th>
			  <td>
				  <s:textfield type="text" name="appInfo.handAvgScore" id="appInfo.handAvgScore" valid="required|range" min="0" max="5"
				      errmsg="validte_required|dp_handAvgScore_check" maxLength="4" size="15" />
				  <span id="errMsg_appInfo.handAvgScore" style="margin-left:20px;color: #FF0000"></span>
			  </td>
		    </tr>
            <tr>
                <th scope="row">标签 &nbsp;<strong style="color: #F00;">*</strong></th>
                <td>
                    <div id="container" style="width:500px;">
						<ul id="tags"></ul>
					</div>
                </td>
            </tr>
            <tr>
		       <th scope="row">操作类型 &nbsp;<strong style="color: #F00;">*</strong></th>
			       <td>
			       	   <s:checkboxlist name="appInfo.opModeList" list="opModeDpTypeList" listKey="typeCode" listValue="typeName" 
			       	   		value="%{appInfo.opModeList}"></s:checkboxlist>
					   <span id="errMsg_opModeList" style="color: #FF0000"></span>
			   </td>
		  </tr>
            <tr>
		       <th scope="row">应用语言 &nbsp;<strong style="color: #F00;">*</strong></th>
			       <td>
				       <s:select name="appInfo.language" id="appInfo.language" cssStyle="width:175px" value="%{appInfo.language}" headerKey="" headerValue="--请选择语言--"
					       list="languageDpTypeList" listKey="typeCode" listValue="typeName" valid="required" errmsg="dp_language_required">
					   </s:select>
					   <span id="errMsg_appInfo.language" style="color: #FF0000"></span>
			   </td>
		  </tr>
		  <tr>
		      <th scope="row">开发商</th>
			  <td>
			      <s:textfield type="text" name="appInfo.developer" id="appInfo.developer" valid="limit" min="0" max="255"
				      errmsg="dp_developer_limit" size="35" />
				  <span id="errMsg_appInfo.developer" style="color: #FF0000"></span>
			  </td>
		  </tr>
		  <tr>
		      <th scope="row">价格 &nbsp;<strong style="color: #F00;">*</strong></th>
			  <td>
			      <input type="radio" name="tempPrice" id="noPayPrice" value="0" checked onclick="setAppPrice();" /> 
				  <label for="noPayPrice">免费</label> 
				  <input type="radio" name="tempPrice" id="payPrice" value="1" onclick="setAppPrice();" /> 
				  <label for="payPrice">收费</label> 
				  <s:textfield style="display:none;" name="appInfo.price" id="appInfo.price"  valid="required|limit|regexp" min="0" max="20" 
				     regexp="0|(^[1-9]+\d*$)"  errmsg="dp_price_required|dp_price_limit|dp_price_low" 
				     value= '%{#price}' />
				  RMB 
				  <span id="errMsg_appInfo.price" style="margin-left:20px;color: #FF0000"></span>
			</td>
		 </tr>
		 <tr>
		     <th scope="row">应用图标 &nbsp;<strong style="color: #F00;">*</strong><br/><font color="#CC99FF">(图片格式必须为png、jpg、gif或jpeg，尺寸大小为170*170,215*215)</font></th>
			     <td>
			         <div align="left" id="div_logo" style="width:200px;float:left">
				     <input name="logos" type="file" id="logos">
				     <s:hidden name="logoFileName" id="logoFileName" value="%{logoFileName}"/>
				     <s:hidden name="logoFileSaveName" id="logoFileSaveName" valid="required" errmsg="download_upload_length_is0" value="%{logoFileSaveName}"/>
					     <s:iterator value="preLogo" id="map" var="map" >
					     <s:iterator value="map" id="column">  
					         <p id='file_<s:property value="key"/>' style="margin-left:15px;">
									<s:property value="value"/>
						         <a href='#' onclick="removeAndCleanImg('div_logo','file_<s:property value="key"/>','logoFileName','logoFileSaveName')"><font color="red">删除</font></a>
						     </p>
						     </s:iterator>
					     </s:iterator>
					 </div>
					 <span id="errMsg_logoFileSaveName" style="color: #FF0000"></span>
		      </td>
		   </tr>
		   <tr>
		       <th scope="row">应用截图 &nbsp;<strong style="color: #F00;">*</strong></br><font color="#CC99FF">(图片格式必须为png、jpg、gif或jpeg，尺寸大小为950*534)</font></th>
			       <td>
			       <div align="left" id="div_img" style="width:200px;float:left">
				       <input name="imgs" type="file" id="imgs">
                       <s:hidden name="imgFileName" id="imgFileName" value="%{imgFileName}"/>
				       <s:hidden name="imgFileSaveName" id="imgFileSaveName" valid="required" errmsg="download_upload_length_is0" value="%{imgFileSaveName}"/>
					     <s:iterator value="preImg" id="map" var="map" >
					         <s:iterator value="map" id="column"> 
								   <p id='file_<s:property value="key"/>' style="margin-left:15px;">
								        <s:property value="value"/>
									    <a href='#' onclick="removeAndCleanImg('div_img','file_<s:property value="key"/>','imgFileName','imgFileSaveName')"><font color="red">删除</font></a>
									</p>
							 </s:iterator>
							</s:iterator>
				   </div>
				   <span id="errMsg_imgFileSaveName" style="color: #FF0000"></span>
				  </td>
		   </tr>
		   <tr>
		       <th scope="row">应用海报</br><font color="#CC99FF">(图片格式必须为png、jpg、gif或jpeg，尺寸大小为820*354)</font></th>
			       <td>
			       <div align="left" id="div_poster" style="width:200px;float:left">
				       <input name="posters" type="file" id="posters">
                       <s:hidden name="posterFileName" id="posterFileName" value="%{posterFileName}"/>
				       <s:hidden name="posterFileSaveName" id="posterFileSaveName"  errmsg="download_upload_length_is0" value="%{posterFileSaveName}"/>
					   <span style="color: red" id="errMsg_posters"> </span>
					   <span style="color: red" id='err_poster'></span>
					     <s:iterator value="prePoster" id="map" var="map" >
					         <s:iterator value="map" id="column"> 
								   <p id='file_<s:property value="key"/>' style="margin-left:15px;">
								      <s:property value="value"/>
								      <a href='#' onclick="removeAndCleanImg('div_poster','file_<s:property value="key"/>','posterFileName','posterFileSaveName')"><font color="red">删除</font></a>
								   </p>
							 </s:iterator>
						  </s:iterator>
					  </div>
					  <span id="errMsg_posterFileSaveName" style="color: #FF0000"></span>
			     </td>
	        </tr>
	        <tr>
		       <th scope="row">专题海报<!-- (202 * 282) --></th>
			       <td>
			       <div align="left" id="div_subjectPoster" style="width:200px;float:left">
				       <input name="file4" type="file" id="file4">
				       <s:hidden name="appInfo.subjectPoster" id="appInfo.subjectPoster" value="%{appInfo.subjectPoster}"/>
					   <span style="color: red" id="errMsg_posters"> </span>
					   <span style="color: red" id='err_file4'></span>
                       <s:if test="%{appInfo.subjectPoster != null}"> 
						<p id='file_<s:property value="appInfo.subjectPoster"/>' style="margin-left:15px;">
						    ${appInfo.subjectPoster}
						    <s:if test="%{appInfo.subjectPoster != ''}">
						    	<a href='#' onclick="removeAndCleanFile('div_subjectPoster','file_${appInfo.subjectPoster}','appInfo.subjectPoster')"><font color="red">删除</font></a>
						    </s:if>
						</p>
					   </s:if>
					  </div>
					  <span id="errMsg_appInfo.subjectPoster" style="color: #FF0000"></span>
			     </td>
	        </tr>
	        <tr>
		     <th scope="row">游戏图标</th>
			     <td>
			         <div align="left" id="div_gameLogo" style="width:200px;float:left">
				     <input name="gameLogos" type="file" id="gameLogos">
				     <s:hidden name="gameLogoFileName" id="gameLogoFileName" value="%{gameLogoFileName}"/>
				     <s:hidden name="gameLogoFileSaveName" id="gameLogoFileSaveName" value="%{gameLogoFileSaveName}"/>
					     <s:iterator value="preGameLogo" id="map" var="map" >
					     <s:iterator value="map" id="column">  
					         <p id='file_<s:property value="key"/>' style="margin-left:15px;">
									<s:property value="value"/>
						         <a href='#' onclick="removeAndCleanImg('div_gameLogo','file_<s:property value="key"/>','gameLogoFileName','gameLogoFileSaveName')"><font color="red">删除</font></a>
						     </p>
						     </s:iterator>
					     </s:iterator>
					 </div>
					 <span id="errMsg_gameLogoFileSaveName" style="color: #FF0000"></span>
		      </td>
		   </tr>
	        
	        <tr>
			    <th scope="row" nowrap="nowrap">应用简介(200字以内) &nbsp;<strong style="color: #F00;">*</strong> </th>
				<td>
				    <s:textarea name="appInfo.appDesc" cols="50" rows="8" id="appInfo.appDesc" style="width:350px;"
					    valid="required|limit" min="0" max="200" errmsg="dp_appDesc_required|dp_appDesc_limit" />
					<span id="errMsg_appInfo.appDesc" style="color: #FF0000"></span>
				</td>
			</tr> 
	    </table>
	</div>
	
	<!-- 保存和返回块 -->
	<div class="btnlistbar">
	    <s:if test="%{operateOption == 'Modify'}">
		    <input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>"
			    onclick="saveApp(document.appUploadForm, 'Modify');" />
		</s:if>
		<s:elseif test="%{operateOption == 'Upgrade'}">
			<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>"
				onclick="saveApp(document.appUploadForm, 'Upgrade');" />
		</s:elseif>
		<s:else>
			<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.save'/>"
				onclick="saveApp(document.appUploadForm, 'Add');" />
		</s:else>
		<input class="inputstyle" type="button" value="<s:text name='sdp.sce.dp.admin.global.btn.return'/>"
				onclick="javascript:history.back(-1)" />
	</div>
</s:form>
<script type="text/javascript">
    initValid(document.appUploadForm);
    insertLanguageJS();
</script>
</body>
</html>
