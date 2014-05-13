function checkSelect(mainId, name) {
	var main = document.getElementById(mainId);
	if (main.checked == true) {
		checkAll(name);
	} else {
		checkAllNo(name);
	}
}

// 全选
function checkAll(name) {
	var names = document.getElementsByName(name);
	var len = names.length;
	if (len > 0) {
		var i = 0;
		for (i = 0; i < len; i++) {
			if (names[i].disabled != true)
				names[i].checked = true;
		}
	}
}

// 全不选
function checkAllNo(name) {
	var names = document.getElementsByName(name);
	var len = names.length;
	if (len > 0) {
		var i = 0;
		for (i = 0; i < len; i++) {
			if (names[i].disabled != true)
				names[i].checked = false;
		}
	}
}

// 反选
function reserveCheck(name) {
	var names = document.getElementsByName(name);
	var len = names.length;
	if (len > 0) {
		var i = 0;
		for (i = 0; i < len; i++) {
			if (names[i].checked) {
				if (names[i].disabled != true)
					names[i].checked = false;
			} else {
				if (names[i].disabled != true)
					names[i].checked = true;
			}

		}
	}
}

// 批量操作
function batchOperation(name, url) {
	var objarray = document.getElementsByName(name);
	var checkvalue = false;
	for ( var i = 0; i < objarray.length; i++) {
		if (objarray[i].checked) {
			checkvalue = true;
		}
	}
	if (checkvalue == false) {
		alert("请选择要操作的项目");
	} else {
		document.forms[0].action = url;
		document.forms[0].submit();
	}
}
