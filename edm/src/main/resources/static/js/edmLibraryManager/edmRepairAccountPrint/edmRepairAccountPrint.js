var tree1,grid1,form;
var params={};
$(document).ready(function() {
	mini.parse();
	var tree = mini.get("tree1");
    var grid = mini.get("grid1");
    form = new mini.Form("#form");
    tree1=tree;
    grid1=grid;
    
    grid1.load();
    tree.on("nodeselect", function (e) {
    	var data = form.getData();
        	 data["devCode"]=e.node.id;
        	 console.log(data);
        	 params=data;
        	 params["export"]=false;
             grid1.load(data);
        	
    });
    
    $(".docancel").unbind("click").bind("click",function(){
    	form.reset();
    	grid1.load();
    	tree.selectNode(null);
    });
    
    
});

function removeRow() {
	var row = grid1.getSelected();
	console.log(row);
	if(row.flowStatusName == "审批中"){
		mini.alert("此检修记录正在审批中，不能删除！请撤销或者等待审批完成");
		return;
	}
	if(row){
		mini.confirm("确定删除？","确定提示",function(action) {
			if (action == "ok") {
				 mini.mask({html: '正在执行操作，请稍后.....', cls: 'mini-mask-loading'});
				$.ajax({
					type : "post",
					dataType:'json',
				    url: $ctx+'/action/edmLibraryManager/edmRepairAccountPrintRemove?edmRepairAccountPrintId='+row.id,
					success : function(text) {
						mini.unmask();
						ajaxResult(text,function(clickResult){
							if(text.resultType=='OK'){
								grid1.reload();
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

function onKeyEnter() {
    search();
}

function search(){
	var node = tree1.getSelectedNode();
	var data = form.getData();//获取表单多个控件的数据
	if(node) data["devCode"]=node.id;
	console.log(data);
	params=data;
	params["export"]=false;
	grid1.load(data);
}

//编辑
function editRow() {
	var row = grid1.getSelected();
	if(row){
		var devCode=row.deviceCode;
	    mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        showMaxButton:true,
	        url: $ctx + "/action/edmLibraryManager/edmRepairAccountPrintEdit?devCode="+devCode+"&edmRepairAccountPrintId="+row.id,
	        title: "编辑设备", width: 850, height: $(document.body).outerHeight(true),
	        onload: function () {
	            var iframe = this.getIFrameEl();
	            var data = { model: "edit",ckData: row.repairContent};
	            iframe.contentWindow.initData(data);
	        },
	        ondestroy: function (action) {
	            grid1.reload();
	        }
	    });
	}else{
		mini.alert("请选择一条数据");
	}
}

//查看
function onGridRowDbClick(e){
	var row=e.row;
    mini.open({
        targetWindow: window,
        showCloseButton:true,
        showMaxButton:true,
        url: $ctx + "/action/edmLibraryManager/edmRepairAccountPrintEdit?devCode="+row.deviceCode+"&edmRepairAccountPrintId="+row.id,
        title: "检修台账", width: 850, height: $(document.body).outerHeight(true),
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { model: "view",ckData: row.repairContent };
            iframe.contentWindow.initData(data);
        },
        ondestroy: function (action) {
            grid1.reload();
        }
    });
}

//新增
function addRow() {
	var node = tree1.getSelectedNode();
	console.log(node);
	if(node&&node.isLeaf){
			mini.open({
				targetWindow: window,
				showCloseButton:true,
				showMaxButton:true,
				url: $ctx + "/action/edmLibraryManager/edmRepairAccountPrintEdit?devCode="+node.id+"&nodeCode="+node.pid,
				title: "新增检修台账", 
				width: 1000, height: $(document.body).outerHeight(true),
				onload: function () {
					var iframe = this.getIFrameEl();
					var data = { model: "edit",flag:"add" };
					iframe.contentWindow.initData(data);
				},
				ondestroy: function (action) {
					
					grid1.reload();
				}
			});
	}else{
		mini.alert("请选择设备");
	}
}
	
//导出
function exportExcel() {
	var title =document.title;
	var columns = grid1.getBottomColumns();
    var node=tree1.getSelectedNode();
    function getColumns(columns) {
    	columns = columns.clone();
    	for (var i = columns.length - 1; i >= 0; i--) {
    		var column = columns[i];
    		if (!column.field) {
    			columns.removeAt(i);
    		} else {
    			var c = {  header: column.header, field: column.field };
    			columns[i] = c;
    		}
    	}
    	return columns;
    }
    var columns = getColumns(columns);
    //加入判断导出参数
    params["export"]=true;
    console.log(params);
    var text = grid1.getData();
    DownLoad($ctx + "/action/exportExcel", {columns: columns,className:className.value,title:title,dataList:text}, function () {
	//	 mini.alert("导出成功");
		});
//    $.ajax({
//		type : "post",
//		dataType:'json',
//	    url: $ctx+'/action/edmLibraryManager/edmRepairAccountPrintList',
//	    data:params,
//	    async:false,
//		success : function(text) {
//			console.log(text);
//			 DownLoad($ctx + "/action/exportExcel", {columns: columns,className:className.value,title:title,dataList:text}, function () {
////				 mini.alert("导出成功");
//				});
//		},
//		error:function(e){
//			errorAjax(e);
//		}
//	});
    
}

function DownLoad(url, fields, callback) {
    //创建Form
    var submitfrm = document.createElement("form");
    submitfrm.action = url;
    submitfrm.method = "post";
    submitfrm.target = "_blank";
    document.body.appendChild(submitfrm);
    if (fields) {
        for (var p in fields) {
            var input = mini.append(submitfrm, "<input type='hidden' name='" + p + "'>");
            var v = fields[p];
            if (typeof v != "string") v = mini.encode(v);
            input.value = v;
        }
    }
    submitfrm.submit();
    setTimeout(function () {
        submitfrm.parentNode.removeChild(submitfrm);
        if (callback) callback();
    }, 1000);
}
function beforenodeselect(e) {
    //禁止选中父节点
    if (e.isLeaf == false) e.cancel = true;
}
