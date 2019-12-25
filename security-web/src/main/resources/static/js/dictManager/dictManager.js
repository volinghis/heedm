var tree,grid;
$(document).ready(function() {
	mini.parse();
	var tree1 = mini.get("tree1");
	var grid1 = mini.get("grid1");
	tree=tree1;
	grid=grid1;
	
	grid.on("cellendedit",function(e){
		var row=e.row;
		var field=e.field;
		if(field=="text" || field=="dataCode"){
		    if((e.value) == null && (e.value) == undefined) {
		        grid.removeRow (row);
		    }
		}
	});

});

//节点选中函数
function onNodeSelect(e) {
	if (e.node.id != "dataDict") {
		grid.load({parentCode :e.node.id});
	}
}


//刷新树节点
function refreshNode() {
	var node = tree.getSelectedNode();
	if(node)tree.load();
}

//新增子节点
function addChildRow() {  
	 var node = tree.getSelectedNode();
	 var newRow={};
	 if (node) {
		 var nodeLevel=tree.getLevel(node);
		 console.log(nodeLevel);
		 if(nodeLevel==2){
			 mini.alert("禁止增加子节点");
			 return;
		 }else{
			 newRow.parentCode = node.id;
		 }
		 grid.addRow(newRow, 0);
		 grid.beginEditCell(newRow,"text");
	 }else{
		 mini.alert("请选择父节点");
	 }
}


//保存
function saveData() {
	var data,json;
	grid.validate();
	if (grid.isValid() == false) {
		mini.alert("名称不能为空");
		return;
	}
	grid.commitEdit();
    data = grid.getChanges();
    if(data.length==0)return;
    for(i = 0,len=data.length; i < len; i++) {
    	delete data[i]._id;
	 }
    json = mini.encode(data);
    console.log(json);
    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
    $.ajax({
        url: $ctx+"/action/dictManager/saveDictData?flag="+data[0]._state,
        data: { data: json },
        type: "post",
        success: function (text) {
        	mini.unmask();
        	var res=JSON.parse(text);
        	ajaxResult(res,function(){
				if(res.resultType=='OK'){
					grid.reload();
					refreshNode();
				}
			});
        		
        },
        error:function(e){
        	mini.unmask();
			errorAjax(e);
		}
    });
}

function doAjaxRomove(rows){
	  $.ajax({
	        url: $ctx+"/action/dictManager/deleteDictById",
	        data: {data: rows[0].id},
	        async : false,
	        type: "get",
	        success: function (text) {
	        	var res=JSON.parse(text);
	        	ajaxResult(res,function(){
					if(res.resultType=='OK'){
						grid.removeRows(rows, true);
						refreshNode();
						grid.reload();
					}
				});
	        },
	        error:function(e){
				errorAjax(e);
			}
	    });
	  
}


//删除
function removeRow() {
	var row = grid.getSelected();
    if (row) {
//  	  console.log(rows[0].id);
//  	  if(rows[0].id==undefined){//如果为空行 直接删除
//  		  grid.removeRows(rows, true);
//  		  return;
//  	  }
  	  mini.confirm("是否继续删除","确定？",function(msg){
  		  if(msg=='ok'){
//  			 doAjaxRomove(rows);
  		  }
  		  return;
  	  });
  	  
    }else{
  	 mini.alert("请选择一条记录");
    }
} 


