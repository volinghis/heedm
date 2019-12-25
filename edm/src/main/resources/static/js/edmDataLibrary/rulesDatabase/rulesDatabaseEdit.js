$(document).ready(function() {
	mini.parse();
	$(".docancel").unbind("click").bind("click", function() {
		mini.confirm("确定返回吗？","确定提示",function(action) {
			if (action == "ok") {
				closeWin(action);
			} else {
			}
		});
	});
	$(".dosave").unbind("click").bind("click",function(){
		$("#filesJson").val(getUploadGridData());
		//提交表单数据
		var form = new mini.Form("#form");
		form.validate();
		if (form.isValid() == false) return;
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
	    console.log($("form").serialize());
		$.ajax({
			type : "post",
		    url: $ctx+'/action/edmDataLibrary/rulesDatabaseSave',
		    data: $("form").serialize(),
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


var fileDataloaded=false;
function loadResult(){
	fileDataloaded=true;
}

function initData(data) {
	if (data.action != "edit") {
		var form = new mini.Form("#form");
		data = mini.clone(data);
		form.setEnabled(false);
		$("div[property='toolbar']").hide();
		$(".dosave").hide();
		var st = setInterval(function() {
			if (fileDataloaded) {
				clearInterval(st);
				$(".file-remove").hide();
			}
		}, 200);
	}else{
		var uploadGrid = mini.get("uploadGrid");
		uploadGrid.hideColumn (uploadGrid.getColumn("creationTime"));
		uploadGrid.hideColumn (uploadGrid.getColumn("creationName"));
	}
}