var _js=document.scripts;  
var _url=_js[_js.length-1].src;  
var $ctx=getQueryString(_url,'ctx');  
var $domain=getQueryString(_url,'domain');
//document.domain=$domain;
mini_debugger = true;                                           //

var skin = 'bootstrap';             //skin cookie   cupertino
var mode = 'large';                 //mode cookie     medium     



document.write('<meta name="renderer" content="ie-stand"  />');

//miniui
document.write('<script src="' + $ctx + '/scripts/jquery.min.js" type="text/javascript"></sc' + 'ript>');
document.write('<script src="' + $ctx + '/scripts/miniui/miniui.js" type="text/javascript" ></sc' + 'ript>');
document.write('<link href="' + $ctx + '/res/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />');
document.write('<link href="' + $ctx + '/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />');

//common
document.write('<link href="' + $ctx + '/res/css/common.css" rel="stylesheet" type="text/css" />');
document.write('<script src="' + $ctx + '/res/js/common.js" type="text/javascript" ></sc' + 'ript>');
//document.write('<script src="' + $ctx + '/res/js/ajaxReset.js" type="text/javascript" ></sc' + 'ript>');

//skin
if (skin && skin != "default") document.write('<link href="' + $ctx + '/scripts/miniui/themes/' + skin + '/skin.css" rel="stylesheet" type="text/css" />');

//mode
if (mode && mode != "default") document.write('<link href="' + $ctx + '/scripts/miniui/themes/default/' + mode + '-mode.css" rel="stylesheet" type="text/css" />');

//icon
document.write('<link href="' + $ctx + '/scripts/miniui/themes/icons.css" rel="stylesheet" type="text/css" />');

// document.write('<link href="' + $ctx + '/webjars/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet" type="text/css" />');
function getQueryString(url,name){  
    var reg = new RegExp("(\\?|&)"+ name +"=([^&]*)(&|$)");  
    var r = url.substr(1).match(reg);  
    if(r!=null)return unescape(r[2]); return null;  
}  


var WinAlerts = window.alert;
window.alert = function (e) {
	if (e != null && e.indexOf("试用到期 www.miniui.com")>-1)
	{
	//和谐了
	}
	else
	{
	WinAlerts (e);
	}
};
