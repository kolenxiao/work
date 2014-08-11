//该方法支持同时上传多个文件
function SimpleUpload(property){
    var multiple = false;
	var ID = property.ID;
	var contextPath = property.contextPath;
	var requestURL = property.requestURL;
	var fileType = property.fileType;
	var formdata = property.formdata;
	var callback = property.callback;
	var buttonImage = property.buttonImage;
	var fileObjName = property.fileObjName;
	var parentDIV = property.parentDIV;
	var childDIV = null;
	var err_msg_id = property.err_msg_id;

	
	if (property.multiple)
		multiple = property.multiple; //控件是否支持批量文件上传，默认为只支持上传单个
	
	this.bindUplodify = function() {
		$("#" + property.ID).uploadify({
			'method'        : 'post',
			'auto'          : true,
			'multi'         : multiple,
			'buttonImage'   : buttonImage,
			'removeTimeout' : 1,
			'successTimeout': 300,
			'swf'           : contextPath + '/uploadify/uploadify.swf',
			'formData'      : formdata,
			'fileTypeExts'  : fileType,
			'fileObjName'   : fileObjName,
			'uploader'      : contextPath + requestURL,
			'onSelect'      : function(file) {
				if (err_msg_id != null && err_msg_id != ''
						&& err_msg_id != undefined) {
					$("span[id='"+err_msg_id+"']").empty();
				}
				
			},
			'onFallback'    : function() {
				alert('您的浏览器不支持Flash请先安装!');
			},
	        'onUploadStart' : function(file) {
	        	var fileName = file.name;
	        	formdata["fileType"] = fileName.substring(fileName.lastIndexOf(".",fileName.length),fileName.length);
	        	$("#" + ID).uploadify('settings','formData',formdata);
	        },
			'onUploadSuccess' : function(file, data, response) {
				childDIV = callback(file, data, parentDIV, childDIV);
			}
		});
	};
}

//该方法一次只能上传单个文件
function SingleUpload(property){
    var multiple = false;
	var ID = property.ID;
	var contextPath = property.contextPath;
	var requestURL = property.requestURL;
	var fileType = property.fileType;
	var formdata = property.formdata;
	var callback = property.callback;
	var buttonImage = property.buttonImage;
	var fileObjName = property.fileObjName;
	var parentDIV = property.parentDIV;
	var inputID = property.inputID;
	var err_msg_id = property.err_msg_id;
	var uploadLimit = 1;
	
	if (property.multiple)
		multiple = property.multiple; //控件是否支持批量文件上传，默认为只支持上传单个
	
	this.bindUplodify = function() {
		$("#" + property.ID).uploadify({
			'method'        : 'post',
			'auto'          : true,
			'multi'         : multiple,
			'buttonImage'   : buttonImage,
			'uploadLimit'   : uploadLimit,
			'removeTimeout' : 1,
			'successTimeout': 300,
			'swf'           : contextPath + '/uploadify/uploadify.swf',
			'formData'      : formdata,
			'fileTypeExts'  : fileType,
			'fileObjName'   : fileObjName,
			'uploader'      : contextPath + requestURL,
			'onSelect'      : function(file) {
				if (err_msg_id != null && err_msg_id != ''
						&& err_msg_id != undefined) {
					$("span[id='"+err_msg_id+"']").empty();
				}
				$("#" + ID).uploadify('settings','uploadLimit',++uploadLimit);
			},
			'onFallback'    : function() {
				alert('您的浏览器不支持Flash请先安装!');
			},
	        'onUploadStart' : function(file) {
	        	var fileName = file.name;
	        	formdata["fileType"] = fileName.substring(fileName.lastIndexOf(".",fileName.length),fileName.length);
	        	$("#" + ID).uploadify('settings','formData',formdata);
	        },
			'onUploadSuccess' : function(file, data, response) {
				callback(file, data, parentDIV, inputID);
			}
		});
	};
}

