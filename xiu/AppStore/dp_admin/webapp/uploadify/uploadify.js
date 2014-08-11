function UploadFile() {
	var previousId = "";
	var child = null;

	this.bindUplodify = function(fileId, contextPath, requestURL, fileType, params, fn) {
		$("#" + fileId).uploadify({
			'method' : 'post',
			'auto' : false,
			'multi' : false,
			'buttonImage' : '/uploadify/browse-btn.png',
			'removeTimeout' : 1,
			'buttonText' : '浏览文件',
			'swf' : contextPath + '/uploadify/uploadify.swf',
			'formData' : params,
			'fileTypeExts' : fileType,
			'fileObjName' : "file",
			'uploader' : contextPath + requestURL,
			'onSelect' : function(file) {
				previousId = fileId;
			},
			'onFallback' : function() {
				alert('您的浏览器不支持Flash请先安装!');
			},
	        'onUploadStart' : function(file) {
	        	var fileName = file.name;
	        	params["fileType"] = fileName.substring(fileName.lastIndexOf(".",fileName.length),fileName.length);
	        	$("#" + previousId).uploadify('settings','formData',params);
	        },
			'onUploadSuccess' : function(file, data, response) {
				fn(file, data);
			}
		});
	};

	this.onUploadApkSuccess = function(file, data) {
		var obj = eval('(' + data + ')');
		if (obj != undefined) {
			if (obj.statusCode == 0) {
				if(child != undefined && child != null){
					this.remvoeChild("div_app",child);
				}
				$("input[name='apkFileName']").val(file.name);
				$("input[name='apkFileSaveName']").val(obj.extendMsg);
				child = "file_"+obj.extendMsg;
				var parent=document.getElementById("div_app");//获取父元素
				var div=document.createElement("p");//创建一个div容器用于包含input file
				var x=parseInt(Math.random()*(80-1))+1;
				var divName=child;//随机div容器的名称
				div.name=divName;
				div.id=divName;
				div.innerHTML= file.name;
				parent.appendChild(div);//将div容器加入父元素
				
			} else {
				alert("上传文件失败");
				this.previousFile = null;
				$("#" + previousId).uploadify('cancel');
			}
		}
	};
}

function remvoeChild(parentID,DelDivID){
	var parent=document.getElementById(parentID);
 	parent.removeChild(document.getElementById(DelDivID));
};
