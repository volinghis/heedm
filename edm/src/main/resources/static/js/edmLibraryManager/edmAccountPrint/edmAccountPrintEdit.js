var grid ,grid2;
var serveTime ,leaveTime;
$(document).ready(function() {
	mini.parse();
	top["win"]=window;
	var orgCode = $("input[name='orgCode']").val();
	var grid1 = mini.get("datagrid1");
	var grid2 = mini.get("datagrid2");
	grid = grid1;
	grid.load();
	grid2.load();
	$("#cframe").unbind("load").bind("load",function(){
         var data = { model: "ref" };
         document.getElementById("cframe").contentWindow.initData(data);
	});
	
//	//打开新的上传窗口
	mini.get("uploadBtn").on("click", function (e) {
		mini.open({
		showMaxButton:false, 
	        url:$ctx+'/action/edmLibraryManager/goUploader',
	        title: "设备资料上传", width:500, height:300,
	    });
	})
	
	$(".docancel").unbind("click").bind("click", function() {
		mini.confirm("确定返回吗？","确定提示",function(action) {
			if (action == "ok") {
				closeWin(action);
			} else {
			}
		});
	});
	
	$(".dosave").unbind("click").bind("click",function(){
		//提交表单数据
		$("#filesJson").val(getUploadGridData());
		$("#accountParamJson").val(getAccountPrintParamData());
		$("#principalParamJson").val(getprincipalParamData());
		var form = new mini.Form("#form1");
		if (serveTime > leaveTime) {
	    	mini.alert("'离任时间'不能早于'担任时间'");
			return;
		}
	    form.validate();
	    if (form.isValid() == false) return;
		console.log($("form").serialize())
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
		$.ajax({
			type : "post",
		    url: $ctx+'/action/edmLibraryManager/edmAccountPrintSave?orgCode='+orgCode,
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
		    if((e.value) == null && (e.value) == undefined) {
		        grid.removeRow (row);
		    }
		}
	});
	grid2.on("cellendedit",function(e){
		var row=e.row;
		var field=e.field;
		if(field =="principal" || field == "serveTime" || field == "leaveTime"){
			if((e.value) == null && (e.value) == undefined) {
				grid2.removeRow (row);
		    }
		}
	    serveTime= mini.formatDate(row.serveTime, 'yyyy-MM-dd');
	    leaveTime= mini.formatDate(row.leaveTime, 'yyyy-MM-dd');
	});
	
	$(".exportWord").unbind("click").bind("click",function(){
		var form = new mini.Form("#form1");
		var text = form.getData();
		var accountParams = getAccountPrintParamData();
		var principalParam = getprincipalParamData();
		var installFullName = $("input[name='installFullName']").val();
//		var data = form.getData();
//		$("form").serialize();
//		$("#dataList").val(data);
//		console.log(data);
//		console.log(principalParam);
		DownLoad($ctx + '/action/edmAccountPrintExportWord', {principalParamJson:principalParam,accountParamJsons:accountParams,installFullName:installFullName,dataList:text}, function() {
			
		});
	});
	
	mini.get("beginBtn").on("click", function (e) {
		
	})
});


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
    }, 100);
}

function getAccountPrintParamData(){
	var data = grid.getData();
	var json = mini.encode(data);
//	console.log(json);
	return json;
}
function getprincipalParamData(){
	var grid2 = mini.get("datagrid2");
	var data = grid2.getData();
	var json = mini.encode(data);
//	console.log(json);
	return json;
}

function ondayRenderer(e) {
    if (e.value) {
        var value = new Date(e.value);
//        console.log(mini.formatDate(value, 'yyyy-MM-dd'));
        if (value) return mini.formatDate(value, 'yyyy-MM-dd');
    }
}

var fileDataloaded=false;
function loadResult(){
	fileDataloaded=true;
}

function initData(e){
	var grid2 = mini.get("datagrid2");
	if(e.model!="edit"){
		var form = new mini.Form("#form1");
		grid.on("rowclick",function(e){
			grid.cancelEdit();
		});
		grid2.on("rowclick",function(e){
			grid2.cancelEdit();
		});
		$("div[property='toolbar']").hide();
		form.setEnabled(false);
		$(".dosave").hide();
		if(e.model=="ref"){
			$(".docancel").hide();
		}
		var st=setInterval(function(){ 
			if(fileDataloaded){
				clearInterval(st);
				$(".file-remove").hide();
			}	
		}, 200);
	}else{
		var uploadGrid = mini.get("uploadGrid");
		uploadGrid.hideColumn (uploadGrid.getColumn("creationTime"));
		uploadGrid.hideColumn (uploadGrid.getColumn("creationName"));
		$(".exportWord").hide();
	}
}
function toggleFieldSet(ck, id) {
	var dom = document.getElementById(id);
	dom.className = !ck.checked ? "hideFieldset" : "";
	if (id == "fd3") {
		if(dom.className =="hideFieldset"){
	    	document.getElementById("datagrid1").style.display="none";
	    }else{
	    	document.getElementById("datagrid1").style.display="block";
	    }
	}
	if (id == "fd1") {
		if(dom.className !="hideFieldset"){
			$("#fd1[style='width:700px; margin:0px auto;']").attr("style",'width:700px; height:650px; margin:0px auto;');
			document.getElementById("cframe").style.display="block";
		}else{
			$("#fd1[style ='width:700px; height:650px; margin:0px auto;']").attr("style",'width:700px; margin:0px auto;');
			document.getElementById("cframe").style.display="none";
		}
	}
}

function addRow() { 
    var newRow = { name: "New Row" };
    grid.addRow(newRow, 0);
    grid.beginEditCell(newRow, "parameterName");
}
function addPrincipal() { 
	var grid2 = mini.get("datagrid2");
	var newRow = { name: "New Row" };
	grid2.addRow(newRow, 0);
	grid2.beginEditCell(newRow, "principal");
}


function beforenodeselect(e) {
    //禁止选中父节点
    if (e.isLeaf == false) e.cancel = true;
}

