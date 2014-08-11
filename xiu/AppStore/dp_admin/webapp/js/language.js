
function insertLanguageJS(prefix) {
	var language;
	if (navigator.language) { // 对于mozilla, Firefor
		language = navigator.language;

	} else if (navigator.browserLanguage) { // 对于IE
		language = navigator.browserLanguage;
	}

	language = language.toLowerCase();
	if (language.indexOf('en') != -1) {
		language = "en-us";
	} else {
		language = "zh-cn";
	}
	var js;
	if (prefix)
		js = '<script type="text/javascript" src="js/' + prefix
				+ 'MessageResource_' + language + '.js"></script>';
	else
		js = '<script type="text/javascript" src="js/MessageResource_'
				+ language + '.js"></script>';
	document.writeln(js);
}
