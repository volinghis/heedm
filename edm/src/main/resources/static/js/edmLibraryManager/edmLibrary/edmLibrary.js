var tree1,grid1;
$(document).ready(function() {
	mini.parse();
	var tree = mini.get("tree1");
	var grid = mini.get("grid1");
	tree1=tree;
	grid1=grid;
	grid1.load();
	openAtPos();
});
function openAtPos() {
    var win1 = mini.open({
        title: '个人事项',
        url: $ctx + '/action/portal/task',
        showModal: false,
        allowDrag:false,
        width: 500,
        height: 300
    });
    win1.showAtPos('right','bottom');
}

function removeRow() {
	var row = grid1.getSelected();
	if(row){
		mini.confirm("确定删除吗？","确定提示",function(action) {
			if (action == "ok") {
				 mini.mask({html: '正在删除，请稍后.....', cls: 'mini-mask-loading'});
				$.ajax({
					type : "post",
					dataType:'json',
				    url: $ctx+'/action/edmLibraryManager/edmLibraryRemove?edmLibraryId='+row.id,
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
	grid1.load({deviceShortName:mini.get("key").getValue()});
}

function addRow() {
    mini.open({
        targetWindow: window,
        showCloseButton:true,
        url: $ctx + "/action/edmLibraryManager/edmLibraryEdit",
        title: "新增设备", width: $(document.body).outerWidth(true), height: $(document.body).outerHeight(true),
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { model: "edit" };
            iframe.contentWindow.initData(data);
        },
        ondestroy: function (action) {

            grid1.reload();
        }
    });
}

function editRow() {
	var row = grid1.getSelected();
	if(row){
	    mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        url: $ctx + "/action/edmLibraryManager/edmLibraryEdit?edmLibraryId="+row.id,
	        title: "编辑设备", width: $(document.body).outerWidth(true), height: $(document.body).outerHeight(true),
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

function prodRow() {
	var row = grid1.getSelected();
	if(row){
	    mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        url: $ctx + "/action/edmLibraryManager/edmAccountPrintEdit?edmLibraryId="+row.id,
	        title: "设备投产", width: $(document.body).outerWidth(true), height: $(document.body).outerHeight(true),
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
    mini.open({
        targetWindow: window,
        showCloseButton:true,
        url: $ctx + "/action/edmLibraryManager/edmLibraryEdit?edmLibraryId="+row.id,
        title: "查看设备", width: $(document.body).outerWidth(true), height: $(document.body).outerHeight(true),
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

//单元格重绘
function onFileRederer(e){
	var html='';
	var fileId=e.row.fileId;
	html+='<a href="'+$ctx+'/action/downloadFile?fileId='+fileId+'"   target="_blank" >下载</a>';
	return html;
}



//单击触发关联 
function onRowClick(e){
	var grid2 = mini.get("grid2");
	var grid3 = mini.get("grid3");
	console.log(e.row);
	grid2.load({dataCode:e.row.code});
	grid3.load({code:e.row.code});
}

//导出
function exportExcel() {
//	var className = $("input[name='nameString']").val();
	var title =document.title;
	var columns = grid1.getBottomColumns();
	function getColumns(columns) {
		columns = columns.clone();
		for (var i = columns.length - 1; i >= 0; i--) {
			var column = columns[i];
			if (!column.field) {
				columns.removeAt(i);
			} else {
				var c = { header: column.header, field: column.field };
				columns[i] = c;
			}
		}
		return columns;
	}
	var columns = getColumns(columns);
	DownLoad($ctx + "/action/exportExcel",{ type: "excel", columns: columns,className:nameString.value,title:title}, function () {
		mini.alert("导出成功");
	});
   return;
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
