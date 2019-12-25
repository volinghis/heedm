var grid,tree,form;
$(document).ready(function() {
	mini.parse();
	var grid1 = mini.get("grid1");
	var tree1 = mini.get("tree");
	form = new mini.Form("#form");
	tree=tree1;
	grid=grid1;
	
	grid.load();
	
	tree.on("nodeselect", function (e) {
		var data=form.getData();
			data["code"]=e.node.id;
			grid1.load(data);
    });
	
	//查询重置
	$(".docancel").unbind("click").bind("click",function(){
    	form.reset();
    	grid.load();
    	tree.selectNode(null);
    });
	
	
	grid.on("drawcell", function (e) {
        var record = e.record,
	    column = e.column,
	    field = e.field,
	    value = e.value;
        var fileId=record.fileId;
        //action列，超连接操作按钮
        if (column.field == "action") {
            e.cellStyle = "text-align:center";
            e.cellHtml = '<a  href="'+$ctx+'/action/downloadFile?fileId='+fileId+'" style="text-decoration:none;">下载</a>'
            + ' <a class="file-view" href="javascript:void(0)" onClick="fileView(\''+fileId+'\')" style="text-decoration:none;">预览</a>'
        }

      //将文件类型换成图片
        if (column.field == "type") {
        	if (e.value == "txt") {
        		e.cellHtml = "<img src='../../img/txt.png'/>"
        	} else if (e.value == "doc" || e.value == "docx" || e.value == "wps") {
        		e.cellHtml = "<img src='../../img/word.png'/>"
        	} else if (e.value == "xls" || e.value == "xlsx" || e.value == "et") {
        		e.cellHtml = "<img src='../../img/excle.png'/>"
        	} else if (e.value == "jpg" || e.value == "png" || e.value =="bmp" || e.value == "tif" || e.value == "gif" || e.value == "jpeg" || e.value == "svg") {
        		e.cellHtml = "<img src='../../img/img.png'/>"
        	} else if (e.value == "rar" || e.value == "zip" || e.value == "7z") {
        		e.cellHtml = "<img src='../../img/compression.png'/>"
        	} else if (e.value == "ppt" || e.value == "pptx" || e.value == "pps" || e.value == "dps") {
        		e.cellHtml = "<img src='../../img/PPT.png'/>"
        	} else if (e.value == "pdf") {
        		e.cellHtml = "<img src='../../img/pdf.png'/>"
        	}else if (e.value == "avi" || e.value == "mp4" || e.value == "flv" || e.value == "rmvb" || e.value == "rm" || e.value == "3gp" || e.value == "mov" || e.value == "mkv" || e.value == "wmv" || e.value == "asf" || e.value == "mpg" || e.value == "f4v" || e.value == "m4v") {
        		e.cellHtml = "<img src='../../img/video.png'/>"
        	}else{
        		e.cellHtml = "<img src='../../img/unknow.png'/>"
        	}
        }

    });
});

function fileView(id){
	 mini.open({
 		showMaxButton: true, 
	        url:$ctx+'/action/viewFile?fileId='+id,
	        title: "预览页面", width:800, height:850,
	    });
}


//新增
function add() {
	var node = tree.getSelectedNode();
	if(node&&node.isLeaf){
	   mini.open({
	        targetWindow: window,
	        url:$ctx+"/action/edmDataLibrary/equipmentDatabaseEdit?code="+node.id+"&nodeCode="+node.pid,
	        title: "设备资料新增", width: 850, height: $(document.body).outerHeight(true),
	        onload: function () {
	            var iframe = this.getIFrameEl();
	            var data = { model:"edit"};
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

//编辑
function editRow() {
	var row = grid.getSelected();
	if(row){
		var devCode=row.deviceCode;
	    mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        url: $ctx + "/action/edmDataLibrary/equipmentDatabaseEdit?fileInfoId="+row.id,
	        title: "设备资料编辑", width: 850, height: $(document.body).outerHeight(true),
	        onload: function () {
	            var iframe = this.getIFrameEl();
	            var data = { model:"edit"};
	            iframe.contentWindow.initData(data);
	        },
	        ondestroy: function (action) {
	            grid.reload();
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
        url: $ctx + "/action/edmDataLibrary/equipmentDatabaseEdit?fileInfoId="+row.id,
        title: "设备资料查看", width: 850, height: $(document.body).outerHeight(true),
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

function removeRow() {
	var row = grid.getSelected();
	console.log(row);
	if(row){
		mini.confirm("确定删除？","确定提示",function(action) {
			if (action == "ok") {
				 mini.mask({html: '正在执行操作，请稍后.....', cls: 'mini-mask-loading'});
				$.ajax({
					type : "get",
					dataType:'json',
				    url: $ctx+'/action/edmDataLibrary/equipmentDatabaseRemove?fileInfoId='+row.id,
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
	var node = tree.getSelectedNode();
	var data = form.getData();//获取表单多个控件的数据
	if(node) data["code"]=node.id;
    console.log(data);
    grid.load(data);
}
function onKeyEnter(e) {
    search();
}