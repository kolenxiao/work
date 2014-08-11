function onUploadApkSuccess(file, data, parentDIV, childDIV) {
	var obj = eval('(' + data + ')');
	var child = childDIV;
	if (obj != undefined) {
		if (obj.statusCode == 0) {
			var i = $("#"+parentDIV).children("p").length;
			if (i > 0) {
				if(child == null || child == undefined){
					child = "file_"+$("input[name='apkFileSaveName']").val();
				}
				remvoeChild(parentDIV, child);
			}
			
			child = "file_" + obj.extendMsg;
			$("input[name='apkFileName']").val(file.name);
			$("input[name='apkFileSaveName']").val(obj.extendMsg);
			
			var parent = document.getElementById(parentDIV);//获取父元素
			var div = document.createElement("p");//创建一个div容器用于显示文件信息
			var divName = child;//
			div.name = divName;
			div.id = divName;
			div.setAttribute("style", "margin-left:15px;");
			div.innerHTML = file.name+"&nbsp;<a href='#'  onclick=removeAndCleanApk('"+parentDIV+"','"+child+"')><font color='red'>删除</font></a>";
			parent.appendChild(div);//将div容器加入父元素

		}else {
			alert("上传文件失败");
		}
	}
	return child;
};

function onUploadLogoSuccess(file, data, parentDIV, childDIV) {
	var obj = eval('(' + data + ')');
	var child = childDIV;
	if (obj != undefined) {
		if (obj.statusCode == 0) {
			child = "file_" + obj.extendMsg;
			setInputVal('logoFileName','logoFileSaveName',file.name,obj.extendMsg);
			var parent = document.getElementById(parentDIV);//获取父元素
			var div = document.createElement("p");//创建一个div容器用于显示文件信息
			var divName = child;//
			div.name = divName;
			div.id = divName;
			div.setAttribute("style", "margin-left:15px;");
			div.innerHTML = file.name+"&nbsp;<a href='#'  onclick=removeAndCleanImg('"+parentDIV+"','"+child+"','logoFileName','logoFileSaveName')><font color='red'>删除</font></a>";
			parent.appendChild(div);//将div容器加入父元素

		}else if(obj.statusCode == 2){
			alert(obj.msg);
		} else {
			alert("上传文件失败");
		}
	}
	return child;
};

function onUploadGameLogoSuccess(file, data, parentDIV, childDIV) {
	var obj = eval('(' + data + ')');
	var child = childDIV;
	if (obj != undefined) {
		if (obj.statusCode == 0) {
			child = "file_" + obj.extendMsg;
			setInputVal('gameLogoFileName','gameLogoFileSaveName',file.name,obj.extendMsg);
			var parent = document.getElementById(parentDIV);//获取父元素
			var div = document.createElement("p");//创建一个div容器用于显示文件信息
			var divName = child;
			div.name = divName;
			div.id = divName;
			div.setAttribute("style", "margin-left:15px;");
			div.innerHTML = file.name+"&nbsp;<a href='#'  onclick=removeAndCleanImg('"+parentDIV+"','"+child+"','gameLogoFileName','gameLogoFileSaveName')><font color='red'>删除</font></a>";
			parent.appendChild(div);//将div容器加入父元素

		}else if(obj.statusCode == 2){
			alert(obj.msg);
		} else {
			alert("上传文件失败");
		}
	}
	return child;
};

function onUploadImgSuccess(file, data, parentDIV, childDIV) {
	var obj = eval('(' + data + ')');
	var child = childDIV;
	if (obj != undefined) {
		if (obj.statusCode == 0) {
			child = "file_" + obj.extendMsg;
			setInputVal('imgFileName','imgFileSaveName',file.name,obj.extendMsg);
			var parent = document.getElementById(parentDIV);//获取父元素
			var div = document.createElement("p");//创建一个div容器用于显示文件信息
			var divName = child;//
			div.name = divName;
			div.id = divName;
			div.setAttribute("style", "margin-left:15px;");
			div.innerHTML = file.name+"&nbsp;<a href='#'  onclick=removeAndCleanImg('"+parentDIV+"','"+child+"','imgFileName','imgFileSaveName')><font color='red'>删除</font></a>";
			parent.appendChild(div);//将div容器加入父元素

		}else if(obj.statusCode == 2){
			alert(obj.msg);
		} else {
			alert("上传文件失败");
		}
	}
	return child;
};

function onUploadPostersSuccess(file, data, parentDIV, childDIV) {
	var obj = eval('(' + data + ')');
	var child = childDIV;
	if (obj != undefined) {
		if (obj.statusCode == 0) {
			child = "file_" + obj.extendMsg;
			setInputVal('posterFileName','posterFileSaveName',file.name,obj.extendMsg);
			var parent = document.getElementById(parentDIV);//获取父元素
			var div = document.createElement("p");//创建一个div容器用于显示文件信息
			var divName = child;//
			div.name = divName;
			div.id = divName;
			div.setAttribute("style", "margin-left:15px;");
			div.innerHTML = file.name+"&nbsp;<a href='#'  onclick=removeAndCleanImg('"+parentDIV+"','"+child+"','postersFileName','postersFileSaveName')><font color='red'>删除</font></a>";
			parent.appendChild(div);//将div容器加入父元素

		}else if(obj.statusCode == 2){
			alert(obj.msg);
		} else {
			alert("上传文件失败");
		}
	}
	return child;
};

//异步上传响应消息
function onUploadSingleFileSuccess(file, data, parentDIV, inputID) {
	var obj = eval('(' + data + ')');
	var child;
	if (obj != undefined) {
		if (obj.statusCode == 0) {
			var i = $("#"+parentDIV).children("p").length;
			if (i > 0) {
				child = "file_"+$("input[name='"+inputID+"']").val();
				remvoeChild(parentDIV, child);
			}
			child = "file_" + obj.extendMsg;
			$("input[name='"+inputID+"']").val(obj.extendMsg);
			var parent = document.getElementById(parentDIV);//获取父元素
			var div = document.createElement("p");//创建一个div容器用于显示文件信息
			var divName = child;//
			div.name = divName;
			div.id = divName;
			div.setAttribute("style", "margin-left:15px;");
			div.innerHTML = obj.extendMsg+"&nbsp;<a href='#'  onclick=removeAndCleanFile('"+parentDIV+"','"+child+"','"+inputID+"')><font color='red'>删除</font></a>";
			parent.appendChild(div);//将div容器加入父元素

		}else if(obj.statusCode == 2){
			alert(obj.msg);
		}  else {
			alert("上传文件失败");
		}
	}
};

function removeAndCleanFile(parentID,DelDivID, inputID){
	remvoeChild(parentID, DelDivID);
	$("input[name='"+inputID+"']").val("");
}

function remvoeChild(parentID,DelDivID){
	var parent=document.getElementById(parentID);
 	parent.removeChild(document.getElementById(DelDivID));
};

function removeAndCleanApk(parentID,DelDivID){
	remvoeChild(parentID, DelDivID);
	$("input[name='apkFileName']").val("");
	$("input[name='apkFileSaveName']").val("");
}

function removeAndCleanImg(parentID, DelDivID, tagAtual, tagSave){
	remvoeChild(parentID, DelDivID);
	$tag0 = $("input[name='"+tagAtual+"']");
	$tag1 = $("input[name='"+tagSave+"']");
	
	var fileName = $tag0.val();
	var fileSaveName = $tag1.val();
	
	if(fileName != "" && fileSaveName != ""){
		var array = fileSaveName.split(",");
		var array2 = fileName.split(",");
		fileName = "";
		fileSaveName = "";
		for(var i = 0 ; i < array.length ; i++){
			if("file_"+$.trim(array[i]) != $.trim(DelDivID.trim())){
				fileName += $.trim(array2[i]);
				fileSaveName += $.trim(array[i]);
				if(i != array.length-1){
					fileName += ",";
					fileSaveName += ",";
				}
			}
		}
		fileName = fileName.replace(/,$/,"");
		fileSaveName = fileSaveName.replace(/,$/,"");
		$tag0.val(fileName);
		$tag1.val(fileSaveName);
	}
	
}

function setInputVal(tagAtual, tagSave, atualName, saveName){
	$tag0 = $("input[name='"+tagAtual+"']");
	$tag1 = $("input[name='"+tagSave+"']");
	
	var fileName = $tag0.val();
	var fileSaveName = $tag1.val();
	
	if(fileName == "" && fileSaveName == ""){
		$tag0.val(atualName);
		$tag1.val(saveName);
		
	}else if(fileName != "" && fileSaveName != ""){
		fileName = fileName.replace(/,$/,"");
		fileSaveName = fileSaveName.replace(/,$/,"");
		$tag0.val(fileName+","+atualName);
		$tag1.val(fileSaveName+","+saveName);
	}
}

//规定上传图片的尺寸
function initPictrueSize(){
	var logosLen = $("#div_logo p").length;
	var pictrueWidth = 0;
	var pictrueHeight = 0;
	if(logosLen==0){
		pictrueWidth = 215;
		pictrueHeight = 215;
	}else if(logosLen==1){
		pictrueWidth = 140;
		pictrueHeight = 140;
	}else if(logosLen==2){
		pictrueWidth = 290;
		pictrueHeight = 140;
	}else if(logosLen==3){
		pictrueWidth = 290;
		pictrueHeight = 290;
	}

	logos.formdata.pictrueWidth = pictrueWidth;
	logos.formdata.pictrueHeight = pictrueHeight;
	var $movie = $("#logos param[name=movie]");
	$movie.val(logos.requestURL+"&pictrueWidth="+pictrueWidth+"&pictrueHeight="+pictrueHeight);
}