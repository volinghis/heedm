var tree,grid;
$(document).ready(function() {
	mini.parse();
	var tree1 = mini.get("tree");
    var grid1 = mini.get("grid");
    tree=tree1;
    grid=grid1;
    grid.load();
    
	$(".docancel").unbind("click").bind("click", function() {
		mini.confirm("确定返回吗？","确定提示",function(action) {
			if (action == "ok") {
				closeWin(action);
			} else {
			}
		});
	});
	$(".dosave").unbind("click").bind("click",function(){
		//提交表单数据
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
	    var menuCode = $("input[name='menuCode']").val();
	    console.log(menuCode);
	    var data = grid.getSelected();
//	    var data = grid.getSelecteds();
//	    var json = mini.encode(data);
//	    console.log(json);
		$.ajax({
			type : "post",
		    url: $ctx+'/action/menuManager/menuManagerSave',
		    data: { roleCode: data.code,menuCode:menuCode },
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

