/*
 * 主要处理开发者管理平台中表单字段、表单提交信息的内容
 * Copyright (c) 2012-2015
 * All rights reserved.
 * Support : ־
 *
 * Version :  1.0
 */

/** 通用定义 */
// 自定义输出错误信息
FormValid.showError = function(errMsg, errName, formName) {
	if (formName=='dpNewsForm' || formName=='dpdownloadForm') {
		for (key in FormValid.allName) {
			document.getElementById('errMsg_' + FormValid.allName[key]).innerHTML = '';
		}
		for (key in errMsg) {
			document.getElementById('errMsg_' + errName[key]).innerHTML = errMsg[key];
		}
	}
};

/** 资讯新增修改 */
// 添加提交
function doAddNews(action) {
	var formName = document.getElementById("dpNewsForm");
	formName.action = action;
	if (validator(formName)) {
		formName.submit();
	}
}
// 编辑提交
function doEditNews(action) {
	var formName = document.getElementById("dpNewsForm");
	formName.action = action;
	if (validator(formName)) {
		formName.submit();
	}
}



// 校验资讯内容不能为空
function checkContentNull() {
	KE.sync('newsContent');
	var newsContentVal = $('#newsContent').val(); // jQuery
	if (newsContentVal != null && newsContentVal.trim() != "") {
		return true;
	} else {
		false;
	}
}

// 校验资讯长度
function checkContentLen() {
	KE.sync('newsContent');
	var newsContentLength = KE.count('newsContent', 'text');
	if (newsContentLength > 0 && newsContentLength <= 30000) {
		return true;
	} else {
		false;
	}
}

/** 资讯列表 */
//查询函数
function searchNews(startTimeId, endTimeId) {
	url = 'dpnews!doList.action?';
	//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
	querydata('searchForm', url, startTimeId, endTimeId);
}

/** 开发文档管理 */
//校验文档内容不能为空
function checkDownloadNull() {
	KE.sync('downloadDesc');
	var downloadDescVal = $('#downloadDesc').val(); // jQuery
	if (downloadDescVal != null && downloadDescVal.trim() != "") {
		return true;
	} else {
		false;
	}
}
// 返回开发文档列表
function backToDownList(){
	location.href = "dpdownload!doList.action";
}

//提交表单
function doSaveDoc(action) {
	var downloadform = document.getElementById("dpdownloadForm");
	KE.sync('downloadDesc');
	downloadform.action = action;
	if (validator(downloadform)) {
		downloadform.submit();
	}
}

//不能下载弹出提示框
function cantDown(fileName){
	dialogList(Lang['message'],300,150,Lang['file_khs']+fileName+Lang['file_khe']+Lang['file_NotDownload'],0,0,1,this);
}

//下载
function doDownLoad(fileName,fileSaveName){
	//var url = "dpdownload!doDownload.action?attachFileId="+id;//+"&attachmentFile.fileName="+fileName+"&attachmentFile.fileSaveName="+fileSaveName;
	var url = "dpdownload!doDownLoad.action?dpDownloadInfo.attachmentFile.fileName="+fileName+"&dpDownloadInfo.attachmentFile.fileSaveName="+fileSaveName;
	document.getElementById("downloadFrame").src=url;
}


//查询函数
function searchDownload(startTimeId, endTimeId) {
	url = 'dpdownload!doList.action?';
	//searchForm是form表单id,url 为跳转路径,startTimeId为开始时间输入框的Id，endTimeId为结束时间输入框的Id
	querydata('searchForm', url, startTimeId, endTimeId);
}




