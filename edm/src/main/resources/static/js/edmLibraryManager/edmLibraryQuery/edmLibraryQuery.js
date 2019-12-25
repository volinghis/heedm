var grid1;
$(document).ready(function() {
	mini.parse();
	var grid = mini.get("grid1");
	var form = new mini.Form("#form1");
	grid1=grid;
	grid1.load();
	$(".docancel").unbind("click").bind("click",function(){
		form.clear();
		var data = form.getData(); 
		grid1.load(data);
	});
	
	$(".doquery").unbind("click").bind("click",function(){
		var data = form.getData();      //获取表单多个控件的数据
        grid1.load(data);
	});
});

function beforenodeselect(e) {
    //禁止选中父节点
    if (e.isLeaf == false) e.cancel = true;
}

function onGridRowDbClick(e){
	var row=e.record;
	console.log(row);
    mini.open({
        targetWindow: window,
        showCloseButton:true,
        url: $ctx + "/action/edmLibraryManager/edmAccountQueryEdit?edmAccountPrintId="+row.id+"&code="+ row.installAddressCode,
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

