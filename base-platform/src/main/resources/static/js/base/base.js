function openWindow(url){
	var screenHeight=window.top.document.body.clientHeight;
	var screenWidth=window.top.document.body.clientWidth;
	window.open(url,"flowWindow","alwaysRaised=yes,height="+screenHeight+",width="+screenWidth+",location=no,menubar=no,resizable=no,screenX=0,screenY=0,scrollbars=no,titlebar=no,toolbar=no");
}
function ajaxResult(result){
	if(result.resultType=='OK'){
		alert('操作成功！');
		history.go(-1);
	}else if(result.resultType=='WARING'){
		alert('操作成功！');
		history.go(-1);
	}else{
		alert('操作失败！');
	}
}
Date.prototype.Format = function (fmt) { //author: meizz
    var o = {
        "M+": this.getMonth() + 1,
        //月份
        "d+": this.getDate(),
        //日
        "h+": this.getHours(),
        //小时
        "m+": this.getMinutes(),
        //分
        "s+": this.getSeconds(),
        //秒
        "q+": Math.floor((this.getMonth() + 3) / 3),
        //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}