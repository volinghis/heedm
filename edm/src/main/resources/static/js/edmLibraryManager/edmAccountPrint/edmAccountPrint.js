var tree1,grid1,founder,form;
var params={};
$(document).ready(function() {
	mini.parse();
	var tree = mini.get("tree1");
    var grid = mini.get("grid1");
    form = new mini.Form("#form");
    founder = $("input[name='founder']").val();
    tree1=tree;
    grid1=grid;
    grid1.load();
    tree1.on("nodeselect", function (e) {
    	var data = form.getData();
    	data["code"]=e.node.id;
    	console.log(data);
    	params=data;
    	params["export"]=false;
    	grid.load(data);
    });
    
    $(".docancel").unbind("click").bind("click",function(){
    	form.reset();
    	grid1.load();
    	tree1.selectNode(null);
    });
});

function addRow() {
	var node = tree1.getSelectedNode();
	console.log(node);
	if(node && node.isLeaf){
		//权限验证
		if(permission(node,tree1)) return;
		mini.open({
			targetWindow: window,
			showCloseButton:true,
			showMaxButton:true,
			url: $ctx + "/action/edmLibraryManager/edmAccountPrintEdit?founder="+founder+"&code="+ node.id,
			title: "新增设备", 
			width: 1000, height: $(document.body).outerHeight(true),
			onload: function () {
				var iframe = this.getIFrameEl();
				var data = { model: "edit" };
				iframe.contentWindow.initData(data);
			},
			ondestroy: function (action) {
				grid1.reload();
			}
		});
	}else{
		mini.alert("请选择设备系统");
	}
}

function removeRow() {
	var row = grid1.getSelected();
	if(row){
		//权限验证
		if(otherPermission(row.installAddressCode)) return;
		mini.confirm("确定废弃吗？","确定提示",function(action) {
			if (action == "ok") {
				 mini.mask({html: '正在执行操作，请稍后.....', cls: 'mini-mask-loading'});
				$.ajax({
					type : "post",
					dataType:'json',
					async:false,
				    url: $ctx+'/action/edmLibraryManager/edmAccountPrintRemove?edmAccountPrintId='+row.id,
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

function search(){
	var node = tree1.getSelectedNode();
	var data = form.getData();//获取表单多个控件的数据
	if(node) data["deviceName"]=node.id;
	params=data;
	params["export"]=false;
	grid1.load(data);
	
}

function editRow() {
	var row = grid1.getSelected();
	if(row){
		//权限验证
	   if(otherPermission(row.installAddressCode)) return;
		mini.open({
			targetWindow: window,
			showCloseButton:true,
			showMaxButton:true,
			url: $ctx + "/action/edmLibraryManager/edmAccountPrintEdit?edmAccountPrintId="+row.id+"&founder="+founder+"&code="+ row.installAddressCode,
			title: "编辑设备", width: 1000, height: $(document.body).outerHeight(true),
			onload: function () {
				var iframe = this.getIFrameEl();
				var data = { model: "edit" };
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

function onGridRowDbClick(e){
	var row=e.record;
	console.log(row);
    mini.open({
        targetWindow: window,
        showCloseButton:true,
        showMaxButton:true,
        url: $ctx + "/action/edmLibraryManager/edmAccountPrintEdit?edmAccountPrintId="+row.id+"&founder="+founder+"&code="+ row.installAddressCode,
        title: "查看设备", width: 1000, height: $(document.body).outerHeight(true),
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { model: "view" };
            iframe.contentWindow.initData(data);
        },
        ondestroy: function (action) {
            grid1.reload();
        }
    });
}

function onRowClick(e){
	var grid2 = mini.get("grid2");
	grid2.load({ devCode: e.row.code });
}

function onRowDbClick(e){
	var grid2 = mini.get("grid2");
	var row=e.row;
    mini.open({
        targetWindow: window,
        showCloseButton:true,
        url: $ctx + "/action/edmLibraryManager/edmRepairAccountPrintEdit?devCode="+row.deviceCode+"&edmRepairAccountPrintId="+row.id,
        title: "检修台账", width: 1000, height: $(document.body).outerHeight(true),
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { model: "view",ckData: row.repairContent };
            iframe.contentWindow.initData(data);
        },
        ondestroy: function (action) {
            grid2.reload();
        }
    });
	
}

//导出
function exportExcel() {
	var title =document.title;
	var columns = grid1.getBottomColumns();
    var node=tree1.getSelectedNode();
    console.log(grid1.getResultObject());
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
    var data=grid1.getData();
    console.log(1111);
    console.log(data);
    console.log(1111);
	var columns = getColumns(columns);
	 //加入判断导出参数
    params["export"]=true;
    console.log(params);
    DownLoad($ctx + "/action/exportExcel", {columns: columns,className:className.value,title:title,dataList:data}, function () {
    });
//    $.ajax({
//		type : "post",
//		dataType:'json',
//	    url: $ctx+'/action/edmLibraryManager/edmAccountPrintList',
//	    data:params,
//	    async:false,
//		success : function(text) {
//			console.log(text);
//			DownLoad($ctx + "/action/exportExcel", {columns: columns,className:className.value,title:title,dataList:text}, function () {
////				 mini.alert("导出成功");
//			});
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
