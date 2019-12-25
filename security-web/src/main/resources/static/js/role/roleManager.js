var tree,grid;
$(document).ready(function() {
	mini.parse();
	var tree1 = mini.get("tree1");
	var grid1 = mini.get("grid1");
	tree=tree1;
	grid=grid1;
	grid.load();
	
	grid.on("cellendedit",function(e){
		var row=e.row;
		var field=e.field;
		if(field == "dataCode"|| field=="name" || field=="remark" ){
		    if((e.value) == null) {
		        grid.removeRow (row);
		    }
		}
	});
});

//新增
function addRow() {  
	var newRow = {};
	grid.addRow(newRow, 0);
	grid.beginEditCell(newRow,"dataCode");
}

//保存
function saveData() {
	var data,json;
	grid.validate();
	if (grid.isValid() == false) {
		mini.alert("单元格内容不能为空");
		return;
	}
	grid.commitEdit();
    data = grid.getChanges();
    if(data.length==0)return;
    for(i = 0,len=data.length; i < len; i++) {
    	delete data[i]._id;
    }
    console.log(data);
    json = mini.encode(data);
    console.log(json);
    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
    $.ajax({
        url: $ctx+"/action/roleManager/roleManagerSave",
        data: { data: json },
        type: "post",
        success: function (text) {
        	mini.unmask();
        	var res=JSON.parse(text);
        	ajaxResult(res,function(){
				if(res.resultType=='OK'){
					grid.reload();
				}
				if(res.resultType=='ERROR'){
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
//查询
function search(){
	var paramsData={
		searchParam:mini.get("key").getValue()
	};
	grid.load(paramsData);
}

//删除
function removeRow() {
	var row = grid.getSelected();
	if (row.dataCode == null) {
		grid.removeRow(row);
		return;
	}
	console.log(row);
	if(row.dataCode == 'sysAdmin' || row.dataCode == 'normal'){
		mini.alert("此角色不可删除");
		return;
	}
	if(row){
		mini.confirm("确定删除吗？","确定提示",function(action) {
			if (action == "ok") {
				 mini.mask({html: '正在删除，请稍后.....', cls: 'mini-mask-loading'});
				$.ajax({
					type : "get",
					dataType:'json',
				    url: $ctx+'/action/roleManager/roleManagerRemove?roleId='+row.id,
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

