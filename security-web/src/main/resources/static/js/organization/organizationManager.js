var tree,grid;
var curSort=0;
$(document).ready(function() {
	mini.parse();
	var tree1 = mini.get("tree");
    var grid1 = mini.get("grid");
    tree=tree1;
    grid=grid1;
    
    grid.load({parentCode:"unit_top"});
    grid.on("cellendedit",function(e){
		var row=e.row;
		curSort=row.sort;
		var field=e.field;
		if(field == "dataCode"|| field=="name" ){
		    if((e.value) == null) {
		        grid.removeRow (row);
		    }
		}
	});
});
// 刷新树节点
function refresh() {
	tree.load();
	var node = tree.getSelectedNode();
	if(node){
		grid.load({parentCode :node.id});
	}
	
}

// 保存
function saveData() {
	var data,json;
    data = grid.getChanges();
    grid.validate();
    if (grid.isValid() == false) {
        mini.alert("编码和名称不能为空");
        return;
    }
    grid.commitEdit();
    for(i = 0,len=data.length; i < len; i++) {
    	delete data[i]._id;
    }
    json = mini.encode(data);
    console.log(json);
    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
    $.ajax({
        url: $ctx+"/action/organization/saveOrganization",
        data: { data: json },
        type: "post",
        success: function (text) {
        	mini.unmask();
        	var res=JSON.parse(text);
        	ajaxResult(res,function(){
				if(res.resultType=='OK'){
					curSort=0;//置零
	        		refresh();
				}
			});
        },
        error:function(e){
        	mini.unmask();
			errorAjax(e);
		}
    });
}
function onNodeSelect(e) {
	var node = e.node;
	var isLeaf = e.isLeaf;
	grid.load({parentCode :node.id});
}
  
//获取排序字段最大值
function getMaxSort(code){
	  var allData=grid.getResultObject();
	  var tatal=allData.total;
	  var resCode;
	  if(tatal>=1){
		  resCode=grid.getRow(tatal-1).sort;
	  }else{
		  resCode=curSort;
	  }
	  return resCode;
	}

// 新增
function addRow() {  
	var node = tree.getSelectedNode();
	if (node) {
		var maxSort=getMaxSort(node.id);
		var newRow = {sort:maxSort+1};
		newRow.parentCode = node.id;
		grid.addRow(newRow, 0);
		grid.beginEditCell(newRow,"dataCode");
	}else{
		mini.alert("请选择具体部门");
	}
}
// 删除
function removeRow() {
	var row = grid.getSelected();
    if (row) {
    	mini.confirm("是否继续删除","确定？",function(msg){
		if(msg=='ok'){
			doAjaxRomove(row);
		}
    		return;
    	});
    }else{
    	mini.alert("请选择一条记录");
    }
}
  
function doAjaxRomove(row){
	 mini.mask({html: '正删除，请稍后.....', cls: 'mini-mask-loading'});
	 $.ajax({
		 url: $ctx+"/action/organization/deleteOrgById",
		 data: {data: row.id,code:row.code},
		 async : false,
		 type: "get",
		 success: function (text) {
			 mini.unmask();
			 var res=JSON.parse(text);
			 ajaxResult(res,function(){
				 if(res.resultType=='OK'){
					 grid.removeRow(row, true);
					 refresh();
				 }
			 });
		 },
		 error:function(e){
			 mini.unmask();
			 errorAjax(e);
		 }
	 });
}

//上移, 下移
  function moveUp() {
      var row = grid.getSelected();
      var index = grid.indexOf(row);
      if (row) {
          if(index==0){
        	  mini.alert("已经是第一条记录，无法上移");
          }else{
        	  var toRow= grid.getRow(index-1);
        	  var orSort=row.sort;
        	  var toSort=toRow.sort;
        	  updateMovedRow(row.id,orSort,toRow.id,toSort);
        	  grid.moveRow(row, index - 1);
          }
      }
  }
  function moveDown() {
	  var  num=grid.getData().length-1; 
      var row = grid.getSelected();
      var index = grid.indexOf(row);
      if (row) {
    	  if(index>=num){
    		  mini.alert("已经是最后第一条记录，无法下移");
    	  }else{
    		  var toRow= grid.getRow(index+1);
    		  var orSort=row.sort;
    		  var toSort=toRow.sort;
    		  updateMovedRow(row.id,orSort,toRow.id,toSort);
    		  grid.moveRow(row, index + 2);
    	  }
         
      }
  }
  
  // 更新行的移动
  function updateMovedRow(orId,orSort,toId,toSort){
	  jQuery.ajax({
		    url: $ctx+"/action/organization/updateMovedRow",
			type: "POST",
			data: {orId:orId,orSort:orSort,toId:toId,toSort:toSort},
			success: function(text){
				var res=JSON.parse(text);
				if(res.resultType=='OK'){
					mini.showTips({
	                    content: "移动成功",
	                    state:"info",
	                    x:"center",
	                    y: "center",
	                    timeout: 2000
	                });
					grid.reload();
					refresh();
				}
				 
			},
			 error:function(e){
				errorAjax(e);
			}
	  });
	
}