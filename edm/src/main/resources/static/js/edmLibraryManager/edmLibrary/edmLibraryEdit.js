var grid;
$(document).ready(function() {
	mini.parse();
    var grid1 = mini.get("datagrid1");
    grid = grid1;
    grid.load();
	$(".docancel").unbind("click").bind("click", function() {
		mini.confirm("确定返回吗？","确定提示",function(action) {
			if (action == "ok") {
				closeWin(action);
			} else {
			}
		});
	});
	$(".dosave").unbind("click").bind("click",function(){
		$("#filesJson").val(getUploadGridData());
		$("#libraryParamJson").val(getlibraryParamData());
		//提交表单数据
		var form = new mini.Form("#form1");
		form.validate();
		if (form.isValid() == false) return;
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
		$.ajax({
			type : "post",
		    url: $ctx+'/action/edmLibraryManager/edmLibrarySave',
		    data: $("form").serialize(),
		    dataType:'json',
			success : function(text) {
				mini.unmask();
				ajaxResult(text,function(clickResult){
					if(text.resultType=='OK'){
						closeWin(clickResult);
					}
				});
			},
			error:function(e){
				mini.unmask();
				errorAjax(e);
			}
		});
	});

	grid.on("cellendedit",function(e){
		var row=e.row;
		var field=e.field;
		if(field=="parameterName" || field=="parameterValue"){
		    if((e.value) == null) {
		        grid.removeRow (row);
		    }
		}
	});
});

function getlibraryParamData(){
	var data = grid.getData();
	var json = mini.encode(data);
	console.log(json);
	return json;
}

var fileDataloaded=false;
function loadResult(){
	fileDataloaded=true;
}

function initData(e){
	if(e.model!="edit"){
		var form = new mini.Form("#form1");
		form.setEnabled(false);
		grid.on("rowclick",function(e){
			grid.cancelEdit();
		});
		$("div[property='toolbar']").hide();
		$(".dosave").hide();
		if(e.model=="ref"){
//			grid.on("cellclick",function(e){
//				grid.beginEditCell();
//			});
			$(".docancel").hide();
		}
		var st=setInterval(function(){ 
				if(fileDataloaded){
					clearInterval(st);
					$(".file-remove").hide();
				}	
			}, 200);
	}else{
	}
}

function beforenodeselect(e) {
    //禁止选中父节点
    if (e.isLeaf == false) e.cancel = true;
}

function addRow() { 
    var newRow = { name: "New Row" };
    grid.addRow(newRow, 0);
    grid.beginEditCell(newRow, "parameterName");
}

