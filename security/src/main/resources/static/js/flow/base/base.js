$(document).ready(function() {
	$(".dotracing").unbind("click").bind("click",function(){
	    mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        url: $ctx + "/action/flow/base/processDiagram?processInstanceId="+mini.get("processInstanceId").getValue(),
	        title: "流程跟踪",width: parseInt($(document.body).outerWidth(true)), height:parseInt($(document.body).outerHeight(true)/2)
	    });
	});
	$(".doreject").unbind("click").bind("click",function(){
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
		$.ajax({
			type : "get",
		    url: $ctx + "/action/flow/base/processReject?processInstanceId="+mini.get("processInstanceId").getValue(),
		    dataType:'json',
			success : function(text) {
				mini.unmask();
				ajaxResult(text,function(clickResult){
					if(text.resultType=='OK'){
						closeWin(clickResult);
					}
				});
			},
			error:function(e){
				mini.unmask();
				errorAjax(e);
			}
		});
	});
	$(".donextstep").unbind("click").bind("click",function(){
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
		$.ajax({
			type : "get",
		    url: $ctx + "/action/flow/base/donextstep?processInstanceId="+mini.get("processInstanceId").getValue()+"&flowOper="+mini.get("flowOper").getValue(),
		    dataType:'json',
			success : function(text) {
				mini.unmask();
				ajaxResult(text,function(clickResult){
					if(text.resultType=='OK'){
						closeWin(clickResult);
					}
				});
			},
			error:function(e){
				mini.unmask();
				errorAjax(e);
			}
		});
	});
	$(".doend").unbind("click").bind("click",function(){
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
		$.ajax({
			type : "get",
		    url: $ctx + "/action/flow/base/doend?processInstanceId="+mini.get("processInstanceId").getValue(),
		    dataType:'json',
			success : function(text) {
				mini.unmask();
				ajaxResult(text,function(clickResult){
					if(text.resultType=='OK'){
						closeWin(clickResult);
					}
				});
			},
			error:function(e){
				mini.unmask();
				errorAjax(e);
			}
		});
	});
	
});
function initProcess(){
	if(mini.get('flowOper')){
		mini.get('flowOper').setEnabled(true);
	}
}