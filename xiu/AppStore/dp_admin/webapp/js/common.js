/*
 * 公共js
 * 包含本系统中的分页方法
 *
 */

/**
 * * 分页跳转url函数
 *
 * @param {Object}
 *            formid 表单跳转id;
 * @param {Object}
 *            url 跳转链接
 * @param {Object}
 *            no 页码
 */
function jumpPageTo(formid, url, no) {

	// 获取页码值
	var pageNoVal = $("#" + no).val().trim();
	// 总页码数
	var totalNum = $("#totalPage").val();
	if (isNull(pageNoVal)) {
		dialogList('提示信息', 300, 150, '请输入跳转页码!', 0, 0, 1, this);
		return false;
	}
	pageNoVal = parseInt(pageNoVal, 10);

	totalNum = parseInt(totalNum);

	if (pageNoVal == 0) {
		dialogList('提示信息', 300, 150, '输入页码不能为0!', 0, 0, 1, this);
		$("#" + no).attr("value", "");
		return false;
	}
	if (pageNoVal > totalNum) {
		dialogList('提示信息', 300, 150, '输入页码超过了最大分页数!', 0, 0, 1, this);
		$("#" + no).attr("value", "");
		return false;
	}
	// 跳转url
	goUrlPage(formid, url, pageNoVal);

}

/**
 * 跳转url函数
 *
 * @param {Object}
 *            formid 表单id
 * @param {Object}
 *            url 链接
 * @param {Object}
 *            pageNoVal 当前页面数
 * @param {Object}
 *            pageSize 每页显示多少条
 *
 */
function goUrlPage(formid, url, pageNoVal) {
	// 每页显示多少条
	var pageSize = document.getElementById("pageSize").value;
	url = url + 'start=' + pageNoVal + '&limit=' + pageSize;
	// var form = document.getElementById(formid);
	// form.action= url+'start=' + pageNoVal+'&limit='+pageSize;
	// form.submit();
	formSubmit(formid, url);
}

function formSubmit(formid, url) {
	var form = document.getElementById(formid);
	form.action = url;
	form.submit();
}

/**
 * 查询数据函数
 *
 * @param {Object}
 *            formid 表单id
 * @param {Object}
 *            url 链接
 * @param {Object}
 *            startTimeId 开始时间文本框Id
 * @param {Object}
 *            endTimeId 结束时间文本框Id
 *
 */
function querydata(formid, url, startTimeId, endTimeId) {
	var startTime = document.getElementById(startTimeId).value;
	var endTime = document.getElementById(endTimeId).value;
	if (CompareDate(startTime, endTime)) {
		document.getElementById(startTimeId).value = "";
		document.getElementById(endTimeId).value = "";
		// dialogList('<s:text
		// name="sdp.sce.dp.admin.global.inform"/>',300,150,'<s:text
		// name="sdp.sce.dp.admin.global.startTime.lessthan.endTime"/>',0,0,1,this);
		dialogList('提示信息', 300, 150, '开始时间不能比结束时间大', 0, 0, 1, this);
		return false;
	}
	goUrlPage(formid, url, 1);
}

/**
 * 全部选中 调用该方法checkbox的name属性必需等于checkbox（name='checkbox'），不然不起作用
 */
function selectAll(obj) {
	if (obj.checked == true) {
		$.each($('input:checkbox[name="checkbox"]').get(),
				function(index, obj) {
					obj.checked = true;
				});
	} else {
		$.each($('input:checkbox[name="checkbox"]').get(),
				function(index, obj) {
					obj.checked = false;
				});
	}
}
/**
 * 检查子checkbox是否全部选中 调用该方法checkbox的name属性必需等于checkbox（name='checkbox'），不然不起作用
 * 全选框的id必须等于checkboxAll（id='checkboxAll'），不然不起作用
 */
function selectChildAll() {
	var checkAll = $("#checkboxAll")[0];
	$.each($('input:checkbox[name="checkbox"]').get(), function(index, obj) {
		if (obj.checked == false) {
			checkAll.checked = false;
			return false;
		}
		checkAll.checked = true;
	});
}

// 复制到剪切板js代码
function copyToClipBoard(s) {
	if (window.clipboardData) {
		window.clipboardData.setData("Text", s);
		alert("已经复制到剪切板！" + "\n" + s);
	} else if (navigator.userAgent.indexOf("Opera") != -1) {
		window.location = s;
	} else if (window.netscape) {
		try {
			netscape.security.PrivilegeManager
					.enablePrivilege("UniversalXPConnect");
		} catch (e) {
			alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
		}
		var clip = Components.classes['@mozilla.org/widget/clipboard;1']
				.createInstance(Components.interfaces.nsIClipboard);
		if (!clip)
			return;
		var trans = Components.classes['@mozilla.org/widget/transferable;1']
				.createInstance(Components.interfaces.nsITransferable);
		if (!trans)
			return;
		trans.addDataFlavor('text/unicode');
		var str = new Object();
		var len = new Object();
		var str = Components.classes["@mozilla.org/supports-string;1"]
				.createInstance(Components.interfaces.nsISupportsString);
		var copytext = s;
		str.data = copytext;
		trans.setTransferData("text/unicode", str, copytext.length * 2);
		var clipid = Components.interfaces.nsIClipboard;
		if (!clip)
			return false;
		clip.setData(trans, null, clipid.kGlobalClipboard);
		alert("已经复制到剪切板！" + "\n" + s)
	}
}

// 隔行变色
$(function() {
	switchTableRow(".data_list", "tr_even", "tr_hover");
});

function confirmChoose(pop){
	var idArray = new Array();//定义元素数组
	
	var childCheckboxs = document.getElementsByName("checkbox");
	var c_length = childCheckboxs.length;//获取元素个数
	
	for ( var i = 0; i < c_length; i++) {
		if (childCheckboxs[i].checked)
			idArray.push(childCheckboxs[i].id);//添加被选择的元素到数组
	}
	
	if (idArray.length > 0) {
		var idVal = eval(idArray);
		$("#dialog").remove();//移除弹出层
		
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				pop.tips,//弹出提示消息
				0, 0, 2, this);
		
		$("#dialog_btn_conform").click(function() {
			var param = pop.param;
			param["ids"] = idVal;
			var ajaxParam = {'reqURL':pop.reqURL, 
					         "param":param,
					         'respURL':pop.respURL};
			confirmByAjax(ajaxParam);
		});
		
	}else{
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				'至少选择一条记录',
				0, 0, 1, this);
	}
}

function confirmByAjax(pop){
	$("#dialog").remove();
	$.ajax({
		cache : false,
		async : false,
		url   : pop.reqURL,
		type  : 'POST',
		data  : pop.param,
		dataType : 'json',
		error : function(msg) {
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300, 150, 'Error', 0,
					0, 1, this);
		},success : function(response) {
			$("#dialog").remove();
			var dataObj = eval(response);
			if (dataObj && dataObj.success) {
				dialogList(
						'<s:text name="sdp.sce.dp.admin.global.inform" />',
						300, 150, dataObj.success, 0,
						0, 1, this);
			} else if (dataObj && dataObj.msg) {
				dialogList(
						'<s:text name="sdp.sce.dp.admin.global.inform" />',
						300, 150,
						dataObj.msg, 0, 0,
						1, this);
			} else if (dataObj && dataObj.exception) {
				dialogList(
						'<s:text name="sdp.sce.dp.admin.global.inform" />',
						300, 150,
						dataObj.exception,
						0, 0, 1, this);
			}
			
			$("#dialog_btn_conform")
			.click(
					function() {
						window.location.href = pop.respURL;
					});
		}
	});
}


function submitByAjax(pop){
	$("#dialog").remove();
	$.ajax({
		cache : false,
		async : false,
		url   : pop.reqURL,
		type  : 'POST',
		data  : pop.param,
		dataType : 'json',
		error : function(msg) {
			dialogList(
					'<s:text name="sdp.sce.dp.admin.global.inform" />',
					300, 150, 'Error', 0,
					0, 1, this);
		},success : function(response) {
			$("#dialog").remove();
			var dataObj = eval(response);
			if (dataObj && dataObj.success) {
				dialogList(
						'<s:text name="sdp.sce.dp.admin.global.inform" />',
						300, 150, dataObj.success, 0,
						0, 1, this);
			} else if (dataObj && dataObj.msg) {
				dialogList(
						'<s:text name="sdp.sce.dp.admin.global.inform" />',
						300, 150,
						dataObj.msg, 0, 0,
						1, this);
			} else if (dataObj && dataObj.exception) {
				dialogList(
						'<s:text name="sdp.sce.dp.admin.global.inform" />',
						300, 150,
						dataObj.exception,
						0, 0, 1, this);
			}
			
			$("#dialog_btn_conform")
			.click(
					function() {
						pop.callback();
					});
		}
	});
}

function reloadURL(url){
	window.location.href = url;
}

function submitParentForm(formName){
	parent.forms[formName].submit();
}

function confirmChoose2(pop){
	var idArray = new Array();//定义元素数组
	
	var childCheckboxs = document.getElementsByName("checkbox");
	var c_length = childCheckboxs.length;//获取元素个数
	
	for ( var i = 0; i < c_length; i++) {
		if (childCheckboxs[i].checked)
			idArray.push(childCheckboxs[i].id);//添加被选择的元素到数组
	}
	
	if (idArray.length > 0) {
		var idVal = eval(idArray);
		$("#dialog").remove();//移除弹出层
		
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				pop.tips,//弹出提示消息
				0, 0, 2, this);
		
		$("#dialog_btn_conform").click(function() {
			var param = pop.param;
			param["ids"] = idVal;
			var ajaxParam = {'reqURL':pop.reqURL, 
					         "param":param,
					         'respURL':pop.respURL,
					         'callback':pop.callback};
			submitByAjax(ajaxParam);
		});
		
	}else{
		dialogList(
				'<s:text name="sdp.sce.dp.admin.global.inform" />',
				300,
				150,
				'至少选择一条记录',
				0, 0, 1, this);
	}
}
