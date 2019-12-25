var tree,grid;
$(document).ready(function() {
	mini.parse();
	var tree1 = mini.get("tree");
    var grid1 = mini.get("grid");
    tree=tree1;
    grid=grid1;
    grid.load();
    
    
    tree.on("nodeselect", function (e) {
    	var node=e.node;
    	var key=mini.get("key").getValue();
    	console.log(key);
    	if(key==""){
    		grid.load({orgCode :node.id});
    	}else{
    		grid.load({orgCode :node.id,searchParam:key});
    	}
    });
});
 
// 新增
  function addRow() {
	  var node = tree.getSelectedNode();
	  console.log(grid.getResultObject());
	  console.log(node);
	  if(node){
		  mini.open({
			  targetWindow: window,
			  showCloseButton:true,
			  showMaxButton:true,
			  url: $ctx + "/action/userBaseInfoManager/userBaseInfoEdit?orgCode="+node.id,
			  title: "新增用户", 
			  width: 700, height: 380,
			  ondestroy: function (action) {
				  grid.reload();
			  }
		  });
	  }else{
		  mini.alert("请选择具体部门");
	  }
}
  
  //修改
  function editRow() {
		var row = grid.getSelected();
		if(row){
		    mini.open({
		        targetWindow: window,
		        showCloseButton:true,
		        showMaxButton:true,
		        url:$ctx + "/action/userBaseInfoManager/userBaseInfoEdit?userBaseInfoId="+row.id+"&orgCode="+row.orgCode,
		        title: "编辑用户", width: 700, height: 380,
		        ondestroy: function (action) {

		            grid.reload();
		        }
		    });
		}else{
			mini.alert("请选择一条数据");
		}

	}
 //
  function removeRow() {
		var row = grid.getSelected();
		console.log(row);
		if(row){
			mini.confirm("确定删除吗？","确定提示",function(action) {
				if (action == "ok") {
					 mini.mask({html: '正在删除，请稍后.....', cls: 'mini-mask-loading'});
					$.ajax({
						type : 'POST',
						data:{userBaseInfoId:row.id},
						dataType:'json',
					    url: $ctx+'/action/userBaseInfoManager/UserBaseInfoManagerRemove',
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
	}

//模糊查询
  function search() {
	var key=mini.get("key").getValue();
    var node = tree.getSelectedNode();
     if(node){
    	 grid.load({searchParam:key,orgCode:node.id});
     }else{
    	 grid.load({searchParam:key});
     }
  }