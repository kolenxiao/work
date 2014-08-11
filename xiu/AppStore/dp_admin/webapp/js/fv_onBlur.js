/*
 * 即时提示错误信息展示插件
 * Copyright (c) 2006-2008 coderhome.net
 * All rights reserved.
 * Support : ־��(dzjzmj@163.com)
 *
 * Version :  0.1
 */
var iname = [];
function initValid(frm) {
	var formElements = frm.elements;
	for ( var i = 0; i < formElements.length; i++) {
		var validType = formElements[i].getAttribute('valid');
		if (validType == null)
			continue;
		formElements[i].onblur = (function(a, b) {
			return function() {
				validInput(a, b)
			}
		})(formElements[i], frm);
	}
}
function validInput(ipt, frm, p) {
	if (p == null)
		p = 'errMsg_';
	var fv = new FormValid(frm);
	var formElements = frm.elements;
	var msgs = fvCheck(ipt, fv, formElements);
	if (msgs.length > 0) {
		document.getElementById(p + ipt.name).innerHTML = msgs.join(',');
	} else {
		document.getElementById(p + ipt.name).innerHTML = '';
	}
}
