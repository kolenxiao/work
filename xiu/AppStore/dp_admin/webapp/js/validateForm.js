String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.len = function () {
	return this.replace(/[^\x00-\xff]/g, "aa").length;
};
String.prototype.replaceAll = function (s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};
function checkTextareaSize(elementId, size) {
	var textarea = document.getElementById(elementId);
	if (textarea.value.length > size) {
		alert("\u8f93\u5165\u7684\u5185\u5bb9\u8d85\u8fc7\u6307\u5b9a\u957f\u5ea6");
		return false;
	}
	return true;
}
function checkFloat(value) {
	var re = /^[\-\+]?([0-9]\d*|0|[1-9]\d{0,2}(,\d{3})*)(\.\d+)?$/;
	if (!re.test(value)) {
		return false;
	}
	return true;
}
function showTip(contentId, tipId, size) {
	var textarea = document.getElementById(contentId);
	var hasInput = textarea.value.length;
	if (hasInput > size) {
		document.getElementById(tipId).innerText = "(\u8f93\u5165\u5b57\u6570\u5df2\u8d85\u8fc7\u4e0a\u9650)";
	} else {
		document.getElementById(tipId).innerText = "(" + "\u6700\u5927\u5b57\u6570:" + size + "  " + "\u5df2\u8f93\u5165\u5b57\u6570:" + hasInput + "  " + "\u5269\u4f59\u53ef\u8f93\u5165\u5b57\u6570:" + (size - hasInput) + ")";
	}
}
// 功能：判断文件的扩展名是否包含在strExt中。
	//	 strFileName表示文件名，strExt表示扩展名字符串，以','分割。
function IsFileExt(strFileName, strExt) {
	var index = strFileName.lastIndexOf(".") + 1;
	var ext;
	if (index >= 0) {
		ext = strFileName.substring(index).toLowerCase();
		if (strExt.toLowerCase().indexOf(ext) >= 0) {
			return true;
		}
	}
	return false;
}
function isNull(str) {
	if (str == null || str.trim() == "") {
		return true;
	}
	return false;
}
function RegExCheck(pattern, str) {
	return pattern.test(str);
}
function RegExReplace(pattern, str, substr) {
	if (substr == undefined) {
		return str.replace(pattern, "");
	} else {
		return str.replace(pattern, substr);
	}
}


////////////////////////////////////////
function ltrim(s) {
	s1 = "";
	c = "";
	i = 0;
	if (s.length < 1) {
		return "";
	}
	for (i = 0; i < s.length; i++) {
		c = s.charAt(i);
		if (!(c <= " " || c == "\ufffd\ufffd")) {
			break;
		}
	}
	len1 = s.length - i;
	if (len1 < 1) {
		return "";
	}
	return s.substr(i, len1);
}
function rtrim(s) {
	s1 = "";
	c = "";
	i = s.length - 1;
	if (i < 0) {
		return "";
	}
	while (i >= 0) {
		c = s.charAt(i);
		if (!(c <= " " || c == "\ufffd\ufffd")) {
			break;
		}
		i = i - 1;
	}
	i = i + 1;
	if (i < 1) {
		return "";
	}
	return s.substr(0, i);
}
function len(s) {
	var length = 0;
	var tmpArr = s.split("");
	for (i = 0; i < tmpArr.length; i++) {
		if (tmpArr[i].charCodeAt(0) < 299) {
			length++;
		} else {
			length += 2;
		}
	}
	return length;
}
function checkIpAddress(value) {
	var re = /^\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b$/;
	if (!re.test(value)) {
		return false;
	}
	return true;
}
function checkInteger(value) {
	var re = /^[\d]+$/;
	if (!re.test(value)) {
		return false;
	}
	return true;
}
//校验特殊字符
function checkSpecialChar(value){
	//允许中文 英文 数字 %@&()（）_特殊字符，其他的特殊字符需要过滤
	var regVal = /^[%@&()（）_a-zA-Z0-9\u4e00-\u9fa5]+$/;
	if(regVal.test(value)){
		return true;
	}else{
		return false;
	}
}
/**
91.* 检查是否为网址
92.* @param {} str_url
93.* @param {} alertStr    弹出字段内容
94.* @param {} idStr    光标定位的字段ID<b>只能接收ID</b>
95.* @return {Boolean} <b>不是</b>网址返回false;
96.*/
function IsURL(str_url){// 验证url
  var str =  /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\:+!]*([^<>])*$/;
  var re = new RegExp(str);
  if (!re.test(str_url))  {
	  return false;
  } else{
	  return true;
  }
}
