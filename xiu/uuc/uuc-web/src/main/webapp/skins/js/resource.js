/**
 * 弹出窗口
 * @param url
 * @param name
 * @param iWidth
 * @param iHeight
 */
function openwindow(url,name,iWidth,iHeight)
{
	var url; //转向网页的地址;
	var name; //网页名称，可为空;
	var iWidth; //弹出窗口的宽度;
	var iHeight; //弹出窗口的高度;
	//var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
	//var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
	var iTop = 50; 
	var iLeft = 10;
	window.open(url,name,'height='+iHeight+',innerHeight='+iHeight+',width='+iWidth+',innerWidth='+iWidth+',top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,scrollbars=auto,resizeable=no,location=no,status=no');
}



/**
 * 处理textArea的maxLength
 * @param obj
 * @param max
 */
function textAreaMaxLen(obj, max){
	var str = obj.value;
	if(str=="") return;
	var len = str.replace(/[^x00-xff]/g,"aa").length;
	if(max>0 && len>max){		
		obj.value = str.substr(0,max);
		alert("最多只能输入"+max+"个字符,系统已自动截断！");
	}
}

/**
 * 得到字符串的长度
 * @param s
 * @returns
 */
function chkStrLen(s) {
	var str = s.replace(/[\\\/\:\?\*\<\>\|\"\=\']/g, '');
	str = str.replace(/(^\s*)|(\s*$)/g, '');

	if (str.match(/[\u4E00-\u9FA5]/ig)) {
		var chn = str.replace(/[^\u4E00-\u9FA5]/ig, '');
		var uch = str.replace(/[\u4E00-\u9FA5]/ig, '');
		str = chn.length * 3 + uch.length;
	} else {
		str = str.length;
	}
	return str;
} 

/**
 *  将字符串格式化为时间
 * @param dateStr
 * @returns {Date}
 */
function stringToDate(dateStr){//格式化时间
/*	alert(dateStr);
	var temp=dateStr.split(" ");   
    var str=temp[0].split("-");   
    //注意8进制问题 parseInt(str[1],10)   
    var strTime=temp[1].split(":");
    var date=new Date(str[0],(parseInt(str[1],10)-1),str[2],strTime[0],strTime[1],strTime[2],0);   
    return date;    */
	var d = new Date(Date.parse(dateStr.replace(/-/g, "/")));
	return d;
}

/**
 * 将时间格式化为字符串
 * @param date
 * @returns {String}
 */
function dateToString(date){
	var y = date.getFullYear();   
	var m = new String(date.getMonth() + 1);   
	var d = new String(date.getDate());
	var h = new String(date.getHours());   
	var i = new String(date.getMinutes());   
	var s = new String(date.getSeconds());
	
	if(m.length==1) m = "0"+m;
	if(d.length==1) d = "0"+d;
	if(h.length==1) h = "0"+h;
	if(i.length==1) i = "0"+i;
	if(s.length==1) s = "0"+s;
	
	return y + '-' + m + '-' + d + ' ' + h + ':' + i + ':' + s;
}


/**
 * 给id为_id的增加_days天
 * @param id
 * @param days
 */
function timeAdd(_id, _days){
	if(_days=="" || _days==0) return;
	if(!isAllInteger(_days)){
		alert("请填入正确的数字");
		return;
	}
	_days = parseInt(_days);
	var time = $("#"+_id).val();
	var t = stringToDate(time);
	t.setDate(t.getDate()+_days);
	$("#"+_id).val(dateToString(t));
}