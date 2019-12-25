$(document).ready(function() {
	if($(".button-element")&&$(".button-element").length>0){
		var objElement=$(".button-element");
		var params="type="+objElement.attr("type")+"&processInstanceId="+objElement.attr("processInstanceId");
		$.ajax({  
	        type : "get",  
	        url : $ctx+"/action/ui/buttons",  
	        data : params,
	        dataType:'html',
	        async : false,  
	        success : function(result){  
	        	$(".button-element").html(result);
	        	//try{buttonLoadCallBack();}catch(e){};
	        }  
	    }); 
	}
});

function approvePersonbeforenodeselect(e) {
    //禁止选中父节点
    if (e.isLeaf == false) e.cancel = true;
}
function openWindow(url){
	var screenHeight=window.top.document.body.clientHeight;
	var screenWidth=window.top.document.body.clientWidth;
	window.open(url,"flowWindow","alwaysRaised=yes,height="+screenHeight+",width="+screenWidth+",location=no,menubar=no,resizable=no,screenX=0,screenY=0,scrollbars=no,titlebar=no,toolbar=no");
}
function ajaxResult(result,callbackFunc){
	if(result.resultType=='OK'){
		mini.showMessageBox({
			showHeader : true,
			width : 200,
			iconCls: "mini-messagebox-question",
			height : 140,
			title : "结果",
			buttons : [ "ok"],
			message : result.message,
			callback : callbackFunc
		});
	}else{
		mini.alert(result.message,"结果",callbackFunc);
	}
}
function closeWin(action){
	if (window.CloseOwnerWindow)
		return window.CloseOwnerWindow(action);
	else
		window.close();
}


function errorAjax(result){
	mini.alert("服务异常！");
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