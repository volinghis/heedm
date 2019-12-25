var tree,grid;
$(document).ready(function() {
	mini.parse();
//	$(".operControl").css("visibility","hidden");
	var tree1 = mini.get("tree");
    var grid1 = mini.get("grid");
    tree=tree1;
    grid=grid1;
    
    tree.on("nodeselect", function (e) {
    	if(e.isLeaf){
    		$(".operControl").css("visibility","visible");
    		grid.load({menuCode :e.node.id});
    	}else {
    		$(".operControl").css("visibility","hidden");
    		grid.setData([]);
    		grid.setTotalCount(0);
    	}
    });
});
 
// 授权
function addRow() {
	var node = tree.getSelectedNode();
	if(node){
		mini.open({
			targetWindow: window,
			showCloseButton:true,
			showMaxButton:true,
			url: $ctx + "/action/menuManager/menuManagerEdit?menuCode="+node.id,
			title: "授权角色", 
			width: 800, height: 500,
			ondestroy: function (action) {
				grid.reload();
			}
		});
	}else{
		mini.alert("请选择一条菜单导航");
	}
}
  
// 取消授权
function removeRow() {
	var row = grid.getSelected();
	var node = tree.getSelectedNode();
	if(node){
		if(row){
			mini.confirm("确定删除角色吗？","确定提示",function(action) {
				if (action == "ok") {
					mini.mask({html: '正在删除，请稍后.....', cls: 'mini-mask-loading'});
					$.ajax({
						type : 'POST',
						data:{roleCode:row.roleCode,menuCode:node.id},
						dataType:'json',
						url: $ctx+'/action/menuManager/menuManagerRemove',
						success : function(text) {
							mini.unmask();
							ajaxResult(text,function(clickResult){
								if(text.resultType=='OK'){
									grid.reload();
								}
							});
						},
						error:function(e){
							mini.unmask();
							errorAjax(e);
						}
					});
				}
			});
		}else{
			mini.alert("请选择一条数据");
		}
	}else{
		mini.alert("请选择该导航下某条角色");
	}
}
