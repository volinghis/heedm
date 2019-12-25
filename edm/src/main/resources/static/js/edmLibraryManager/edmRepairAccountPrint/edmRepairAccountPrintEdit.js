var ck; 
$(document).ready(function() {
	mini.parse();
	var tabs=mini.get("form1");
//	ck = CKEDITOR.replace('editor', {
//	      resize_enabled :false,
//	      uiColor: '#337ab7a3'
//	    });

	
	$(".docancel").unbind("click").bind("click", function() {
		mini.confirm("确定返回吗？","确定提示",function(action) {
			if (action == "ok") {
				closeWin(action);
			} else {
			}
		});
	});
	
	function getCkData(){
		var k =ck.getData();
		return k;
	}
	
	
	$(".dosave").unbind("click").bind("click",function(){
		//提交表单数据
		$("#filesJson").val(getUploadGridData());
		//mini.get("repairContent").setValue(getCkData());
		//班组成员数据处理
		var member=mini.get("groupMember");
		member.setValue(member.getText())
		//设备评级数据处理
		var equipR=mini.get("equipmentRating");
		equipR.setValue(equipR.getText())
//		var fileVal=mini.get("fileId").getValue();
//		if(fileVal==""||fileVal==undefined){
//			mini.alert("请上传文件到检修记事");
//			var curTab=tabs.getTab("repairTab");
//			tabs.activeTab(curTab);
//			return;
//		}
		var form = new mini.Form("#form1");
		form.validate();
		if (form.isValid() == false) return;
		var data = $("form").serialize();
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
		$.ajax({
			type : "post",
		    url: $ctx+'/action/edmLibraryManager/edmRepairAccountPrintSave',
		    data: data,
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
	
	$(".exportWord").unbind("click").bind("click",function(){
		var form = new mini.Form("#form1");
		var text = form.getData();
		var dutyPersonName =mini.get("dutyPersonCode").getText();
		var natureName =mini.get("repairNatureCode").getText();
		var teamName =mini.get("repairTeamCode").getText();
		var profession =mini.get("profession").getValue();
		var creationTime =mini.get("creationTime").getFormValue();
		var equipmentRating = mini.get("equipmentRating").getText();
		DownLoad($ctx + '/action/edmRepairAccountPrintExportWord', 
			{dataList:text,equipmentRating:equipmentRating,profession:profession,dutyPersonName:dutyPersonName,natureName:natureName,teamName:teamName,creationTime:creationTime}, function() {
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

var fileDataloaded=false;
function loadResult(){
	fileDataloaded=true;
}
function initData(e){
	//ck.setData(e.ckData);
	if(e.model != "edit"){
		var form = new mini.Form("#form1");
		form.setEnabled(false);
		$("div[property='toolbar']").hide();
		$(".dosave").hide();
		$("#picker").hide();
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
		$("#downLoad").hide();
		$(".exportWord").hide();
		uploadGrid.hideColumn (uploadGrid.getColumn("creationTime"));
		uploadGrid.hideColumn (uploadGrid.getColumn("creationName"));
	}
	initProcess();
}

function beforenodeselect(e) {
    //禁止选中父节点
    if (e.isLeaf == false) e.cancel = true;
}
