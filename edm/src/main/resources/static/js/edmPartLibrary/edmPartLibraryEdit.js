$(document).ready(function() {
	$(".docancel").unbind("click").bind("click", function() {
		mini.confirm("确定取消吗？", "确定提示", function(action) {
			if (action == "ok") {
				closeWin();
			} else {

			}
		});
	});

	$(".dosave").unbind("click").bind("click", function() {
		$("#filesJson").val(getUploadGridData());
		//提交表单数据
		var form = new mini.Form("#form");
		var data = form.getData();
		console.log(data.purchaseAmount);
		data.purchaseAmount=data.purchaseAmount.replace(new RegExp(",",'g'),"");
		console.log(data.purchaseAmount);
		form.validate();
		if (form.isValid() == false)
			return;
		mini.mask({
			html : '正在提交，请稍后.....',
			cls : 'mini-mask-loading'
		});
		console.log(data);
		$.ajax({
			type : "post",
			url : $ctx + '/action/edmPartLibrary/partLibrarySave',
			data : $("form").serialize(),
			dataType : 'json',
			success : function(text) {
				mini.unmask();
				ajaxResult(text, function(clickResult) {
					if (text.resultType == 'OK') {
						closeWin(clickResult);
					}
				});
			},
			error : function(e) {
				mini.unmask();
				errorAjax(e);
			}
		});
	});
	
	$(".exportWord").unbind("click").bind("click", function() {
		var form = new mini.Form("#form");
		var text = form.getData();
		var profession =mini.get("profession").getValue();
		var materialTypeCode =mini.get("materialTypeCode").getValue();
		text.purchaseAmount=text.purchaseAmount.replace(new RegExp(",",'g'),"");
		DownLoad($ctx + '/action/edmPartLibrarEexportWord', {dataList:text,profession:profession,materialTypeName:materialTypeCode}, function() {
		});
	});
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

var fileDataloaded = false;
function loadResult() {
	fileDataloaded = true;
}

function initData(data) {
	if (data.action != "edit") {
		var form = new mini.Form("#form");
		data = mini.clone(data);
		form.setEnabled(false);
		$("div[property='toolbar']").hide();
		$(".dosave").hide();
		var st = setInterval(function() {
			if (fileDataloaded) {
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
function beforenodeselect(e) {
    //禁止选中父节点
    if (e.isLeaf == false) e.cancel = true;
}