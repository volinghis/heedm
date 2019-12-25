var tree,grid;
$(document).ready(function(){
    mini.parse();
    var tree1 = mini.get("tree");
    var grid1 = mini.get("grid1");
    var form = new mini.Form("#form");
    tree = tree1;
    grid = grid1;
    grid.load();
    //树表联动
    tree.on("nodeselect", function (e) {
    	var data=form.getData();
		data["code"]=e.node.id;
		grid1.load(data);
    });
});

//模糊查询
function search() {
    var key = mini.get("key").getValue();
    if(key==""){
		  alert("请输入文件名称！");
	  }
    grid.load({ name: key });
}
function onKeyEnter(e) {
    search();
}

//新增
function add() {
	var node = tree.getSelectedNode();
	if(node&&node.isLeaf){
	   mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        showMaxButton:true,
	        url:$ctx+"/action/edmDataLibrary/rulesDatabaseEdit?code="+node.id,
	        title: "规章制度新增", width: parseInt($(document.body).outerWidth(true)/2), height:parseInt($(document.body).outerHeight(true)/2),
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
	   mini.alert("其选择规章制度类型");
   }
}

//删除
function removeRow() {
	var rows = grid.getSelecteds();
    if (rows.length > 0) {
  	  console.log(rows);
  	  $.ajax({
  	        url: $ctx+"/action/edmDataLibrary/rulesDatabaseDel",
  	        data: {data: rows[0].id},
  	        type: "get",
  	        success: function (text) {
  	        	var d=eval('(' + text + ')').resultType;
  	        	if(d=='OK'){
  	        		mini.alert("删除成功");
  	        		grid.removeRows(rows, true);
  	        		grid.reload();
  	        		tree.load();
  	        	}
  	        },
  	        error: function (jqXHR, textStatus, errorThrown) {
  	            alert(jqXHR.responseText);
  	        }
  	    });
    }else{
  	  mini.alert("请选择一条记录");
    }
}

//表单模糊查询
//function onFilterChanged(e) {
//    var namebox = mini.get("nameFilter");
//    var name = namebox.getValue().toLowerCase();
//    grid.filter(function (row) {
//        var r1 = true;
//        if (name) {
//            r1 = String(row.name).toLowerCase().indexOf(name) != -1;
//        }
//        return r1;
//    });
//}

//编辑
function editRow() {
    var row = grid.getSelected();
    if (row) {
        mini.open({
        	targetWindow: window,
        	showCloseButton:true,
        	showMaxButton:true,
            url: $ctx +"/action/edmDataLibrary/rulesDatabaseEdit?rulesDatabaseId="+row.id,
            title: "规章制度修改",width: parseInt($(document.body).outerWidth(true)/2), height:parseInt($(document.body).outerHeight(true)/2),
            onload: function () {
                var iframe = this.getIFrameEl();
                var data = { action: "edit"};
                iframe.contentWindow.initData(data);
            },
            ondestroy: function (action) {
                grid.reload();
            }
        });
    } 
    else {
    	mini.alert("请选中一条记录");
    }
}


function onGridRowDbClick(e){
	var row=e.record;
    mini.open({
        targetWindow: window,
        showCloseButton:true,
        showMaxButton:true,
        url: $ctx + "/action/edmDataLibrary/rulesDatabaseEdit?rulesDatabaseId="+row.id,
        title: "查看资料信息", width: parseInt($(document.body).outerWidth(true)/2), height:parseInt($(document.body).outerHeight(true)/2),
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { model: "view"};
            iframe.contentWindow.initData(data);
        },
        ondestroy: function (action) {
            grid.reload();
        }
    });
	
}
