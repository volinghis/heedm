var grid ,name,account;
$(document).ready(function() {
	mini.parse();
	var grid1 = mini.get("grid1");
	grid=grid1;
	
	grid1.load();
	$(".docancel").unbind("click").bind("click", function() {
		mini.confirm("确定返回吗？","确定提示",function(action) {
			if (action == "ok") {
				closeWin(action);
			} else {
			}
		});
	});
	
	$(".dosave").unbind("click").bind("click",function(){
		var row = grid.getSelected();
	  	if(row == undefined){
	  		mini.alert("请选择一条记录");  
			return;
	  	}
		console.log(row);
	    $.ajax({
	        url: $ctx+"/action/accountManager/authForCurUser",
	        data: {dataCode:row.dataCode,account:account},
	        type: "post",
	        dataType:'json',
	        success : function(text) {
				ajaxResult(text,function(clickResult){
					if(text.resultType=='OK'){
						closeWin(clickResult);
					}
				});
			},
	        error:function(e){
				errorAjax(e);
			}
	    });
	});
});

//查询
function search(){
	var paramsData={
		searchParam:mini.get("key").getValue()
	};
	grid.load(paramsData);
}
//参数
function getParams(e){
	name=e.name;
	account=e.account;
}

