/*
 * 表达验证规则封装插件，
 * 可以自定义扩展
 * Copyright (c) 2006-2008 coderhome.net
 * All rights reserved.
 * Support : ־��(dzjzmj@163.com)
 *
 * Version :  1.0
 */

var FormValid = function(frm) {
	this.frm = frm;
	this.errMsg = new Array();
	this.errName = new Array();

	this.required = function(inputObj) {
		if (typeof (inputObj) == "undefined" || inputObj.value.trim() == "") {
			return false;
		}
		return true;
	};

	this.eqaul = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('eqaulName')];

		if (fstObj != null && sndObj != null) {
			if (fstObj.value != sndObj.value) {
				return false;
			}
		}
		return true;
	};

	this.gt = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('eqaulName')];

		if (fstObj != null && sndObj != null && fstObj.value.trim() != ''
				&& sndObj.value.trim() != '') {
			if (parseFloat(fstObj.value) <= parseFloat(sndObj.value)) {
				return false;
			}
		}
		return true;
	};

	this.compare = function(inputObj, formElements) {
		var fstObj = inputObj;
		var sndObj = formElements[inputObj.getAttribute('objectName')];
		if (fstObj != null && sndObj != null && fstObj.value.trim() != ''
				&& sndObj.value.trim() != '') {
			if (!(eval(parseFloat(fstObj.value)
					+ inputObj.getAttribute('operate')
					+ parseFloat(sndObj.value)))) {
				return false;
			}
		}
		return true;
	};
	// 输入值的字符个数
	this.limit = function(inputObj) {
		// 2011.11.30 edit 注释掉这段代码是区分中英文字符长度，一个汉字表示两个字符
		/*
		 * var value = inputObj.value; var len =0; var tmpArr = value.split("");
		 * for (i = 0; i < tmpArr.length; i++) { if (tmpArr[i].charCodeAt(0) <
		 * 299) { len++; } else { len += 2; } }
		 */
		// ended of 2011.11.30
		var len = inputObj.value.length;
		if (len) {
			var minv = parseInt(inputObj.getAttribute('min'));
			var maxv = parseInt(inputObj.getAttribute('max'));
			minv = minv || 0;
			maxv = maxv || Number.MAX_VALUE;
			return minv <= len && len <= maxv;
		}
		return true;
	};
	// 输入值的范围，比如1-10，就只能输入1-10的数字
	this.range = function(inputObj) {
		var val = Number(inputObj.value);
		if (inputObj.value) {
			var minv = Number(inputObj.getAttribute('min'));
			var maxv = Number(inputObj.getAttribute('max'));
			minv = minv || 0;
			maxv = maxv || Number.MAX_VALUE;

			return minv <= val && val <= maxv;
		}
		return true;
	};

	this.requireChecked = function(inputObj) {
		var minv = parseInt(inputObj.getAttribute('min'));
		var maxv = parseInt(inputObj.getAttribute('max'));
		minv = minv || 1;
		maxv = maxv || Number.MAX_VALUE;

		var checked = 0;
		var groups = document.getElementsByName(inputObj.name);

		for ( var i = 0; i < groups.length; i++) {
			if (groups[i].checked)
				checked++;

		}
		return minv <= checked && checked <= maxv;
	};
	// 过滤后缀名
	this.filter = function(inputObj) {
		var value = inputObj.value;
		var allow = inputObj.getAttribute('allow');
		if (value.trim()) {
			return new RegExp("^.+\.(?=EXT)(EXT)$".replace(/EXT/g, allow.split(
					/\s*,\s*/).join("|")), "gi").test(value);
		}
		return true;
	};
	// 校验等于
	this.isNo = function(inputObj) {
		var value = inputObj.value;
		var noValue = inputObj.getAttribute('noValue');
		return value != noValue;
	};
	// 校验电话号码
	this.isTelephone = function(inputObj) {
		inputObj.value = inputObj.value.trim();
		if (inputObj.value == '') {
			return true;
		} else {
			if (!RegExps.isMobile.test(inputObj.value)
					&& !RegExps.isPhone.test(inputObj.value)) {
				return false;
			}
		}
		return true;
	};
	// 使用自定义正则表达式
	this.checkReg = function(inputObj, reg, msg) {
		inputObj.value = inputObj.value.trim();

		if (inputObj.value == '') {
			return true;
		} else {
			return reg.test(inputObj.value);
		}
	};
	// 校验密码
	this.passed = function() {
		if (this.errMsg.length > 0) {
			FormValid.showError(this.errMsg, this.errName, this.frm.name);
			if (this.errName[0].indexOf('[') == -1) {
				frt = document.getElementsByName(this.errName[0])[0];
				if (frt.type == 'text' || frt.type == 'password') {
					frt.focus();
				}
			}
			return false;
		} else {
			return FormValid.succeed();
		}
	};
	// 校验特殊字符:允许中文 英文 数字 %@&()（）_特殊字符，其他的特殊字符需要过滤
	this.checkSpecialChar = function(inputObj) {
		inputObj.value = inputObj.value.trim();
		if (inputObj.value == '') {
			return true;
		} else {
			if (!RegExps.isSpecial.test(inputObj.value)) {
				return false;
			}
		}
		return true;
	};
	this.addErrorMsg = function(name, str) {
		this.errMsg.push(str);
		this.errName.push(name);
	};

	this.addAllName = function(name) {
		FormValid.allName.push(name);
	};
};
FormValid.allName = new Array();
FormValid.showError = function(errMsg) {
	var msg = "";
	for (i = 0; i < errMsg.length; i++) {
		msg += "- " + errMsg[i] + "\n";
	}
};
FormValid.succeed = function() {
	return true;
};
function validator(frm) {
	var formElements = frm.elements;
	var fv = new FormValid(frm);
	FormValid.allName = new Array();
	for ( var i = 0; i < formElements.length; i++) {
		if (formElements[i].disabled == true)
			continue;
		var msgs = fvCheck(formElements[i], fv, formElements);
		if (msgs.length > 0) {
			for (n in msgs) {
				fv.addErrorMsg(formElements[i].name, msgs[n]);
			}
		}
	}
	return fv.passed();
}
function fvCheck(e, fv, formElements) {
	if (e.type == "text") {
		e.value = e.value.trim();
	}
	var validType = e.getAttribute('valid');
	var errorMsg = e.getAttribute('errmsg');
	if (!errorMsg) {
		errorMsg = '';
	}
	if (validType == null)
		return [];
	fv.addAllName(e.name);
	var vts = validType.split('|');
	var ems = errorMsg.split('|');
	var r = [];
	for ( var j = 0; j < vts.length; j++) {
		var curValidType = vts[j];
		var curErrorMsg = ems[j];
		// 2011-11-25增加国际化
		var langmsgs = Lang[ems[j]];
		if (langmsgs) {
			curErrorMsg = langmsgs;
		}
		// ended of 2011-11-25

		var validResult;
		switch (curValidType) {
		case 'isNumber':
		case 'isEmail':
		case 'isPhone':
		case 'isMobile':
		case 'isIdCard':
		case 'isMoney':
		case 'isZip':
		case 'isQQ':
		case 'isInt':
		case 'isNaturalInt':
		case 'isEnglish':
		case 'isChinese':
		case 'isUrl':
		case 'isDate':
		case 'isTime':
		case 'isSpecial':
		case 'isWSDL':
			validResult = fv.checkReg(e, RegExps[curValidType], curErrorMsg);
			break;
		case 'regexp':
			validResult = fv.checkReg(e, new RegExp(e.getAttribute('regexp'),
					"g"), curErrorMsg);
			break;
		case 'custom':
			validResult = eval(e.getAttribute('custom') + '(e,formElements)');
			break;
		default:
			validResult = eval('fv.' + curValidType + '(e,formElements)');
			break;
		}
		if (!validResult)
			r.push(curErrorMsg);
	}
	return r;
}
String.prototype.trim = function() {
	return this.replace(/^\s*|\s*$/g, "");
};
