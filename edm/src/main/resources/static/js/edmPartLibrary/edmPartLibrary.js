var grid,tree;
$(document).ready(function(){
    mini.parse();
    $(".operControl").css("visibility","hidden");
    var tree1 = mini.get("tree");
    var grid1 = mini.get("grid1");
    var form = new mini.Form("#form");
    tree=tree1;
    grid=grid1;
    //树表联动
    grid.load();
    tree.on("nodeselect", function (e) {
    	var data = form.getData();
    	data["parentCode"]=e.node.id;
    	grid.load(data);
    });
    
    $(".docancel").unbind("click").bind("click",function(){
    	form.clear();
    	grid1.load();
    	tree.selectNode(null);
    });
    
});

//新增
function add() {
	var node = tree.getSelectedNode();
	var parentNodeCode = tree.getParentNode (node);
	console.log(node);
	if(node&&node.isLeaf){
		var open = mini.open({
	        targetWindow: window,
	        url:$ctx+"/action/edmPartLibrary/partLibraryEdit?parentCode="+ node.id+"&parentNodeCode="+parentNodeCode.id,
	        title: "备件信息新增", 
	        width: 900, height: 500,
	        showMaxButton:true,
	        onload: function () {
	            var iframe = this.getIFrameEl();
	            var data = {action: "edit"};
	            iframe.contentWindow.initData(data);
	        },
	        ondestroy: function (action) {
	            grid.reload();
	        }
	    });
	}else{
		mini.alert("请选择设备");
	}
}

//删除
function removeRow() {
	var row = grid.getSelected();
    if (row) {
    	mini.confirm("确定删除？","确定提示",function(action) {
    		$.ajax({
      	        url: $ctx+"/action/edmPartLibrary/partLibraryDel",
      	        data: {data: row.id},
      	        type: "get",
      	        success: function (text) {
      	        	var d=eval('(' + text + ')').resultType;
      	        	if(d=='OK'){
      	        		grid.removeRow(row, true);
      	        		grid.reload();
      	        		tree.load();
      	        	}
      	        },
      	        error: function (jqXHR, textStatus, errorThrown) {
      	            alert(jqXHR.responseText);
      	        }
      	    });
    	});
    }else{
  	  mini.alert("请选择一条记录");
    }
}

//表单模糊查询
function search() {
	var node = tree.getSelectedNode();
	var form = new mini.Form("#form");
	var data = form.getData();//获取表单多个控件的数据
	if(node){
		data["parentCode"]=node.id;
	}
	console.log(data);
    grid.load(data);
}
function onKeyEnter(e) {
    search();
}

//编辑
function editRow() {
    var row = grid.getSelected();
    if (row) {
        mini.open({
        	targetWindow: window,
        	showCloseButton:true,
            url: $ctx +"/action/edmPartLibrary/partLibraryEdit?edmPartLibraryId="+row.id+"&code="+row.code+"&parentNodeCode="+row.nodeCode+"&parentCode="+row.parentCode,
            title: "备件信息修改", 
            width: 900, height: 500,
            showMaxButton:true,
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = { action: "edit", id: row.id};
                iframe.contentWindow.initData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    }else {
      mini.alert("请选中一条记录");
    }
}

function onGridRowDbClick(e){
	var row=e.record;
	console.log(row);
	console.log(row.parentCode);
    mini.open({
        targetWindow: window,
        showCloseButton:true,
        showMaxButton:true,
        url: $ctx + "/action/edmPartLibrary/partLibraryEdit?edmPartLibraryId="+row.id+"&code="+row.code+"&parentCode="+row.parentCode,
        title: "查看设备", width: 900, height: 500,
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { action: "view",id: row.id };
            iframe.contentWindow.initData(data);
        },
        ondestroy: function (action) {
            grid1.reload();
        }
    });
}
