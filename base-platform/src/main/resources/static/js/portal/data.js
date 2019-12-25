var setting = {
	view: {
		dblClickExpand: false
	},
	check: {
		enable: false
	}
};
//创建菜单树
var zTree, table;
$(document).ready(function(){
	$.fn.zTree.init($("#tree"), setting, treeJsonObj);
});