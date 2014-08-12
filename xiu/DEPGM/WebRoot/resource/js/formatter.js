/**
 * EasyUI Formatter翻译
 */

//是否
function YesOrNoFormatter(value, row, index) {
    if (value) {
        return "是";
    } else {
        return "否";
    }
    return value;
}

//视频清晰度类型
function videoTypeFormatter(value, row, index) {
    if (value == 'HD') {
        return "高清";
    } else if (value == 'SD') {
        return "标清";
    }else {
    	return "未知";
    }
    return value;
}