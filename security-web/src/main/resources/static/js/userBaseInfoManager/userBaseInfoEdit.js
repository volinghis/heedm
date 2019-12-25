var tree,grid;
$(document).ready(function() {
	mini.parse();
	var tree1 = mini.get("tree");
    var grid1 = mini.get("grid");
    tree=tree1;
    grid=grid1;
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
		var form = new mini.Form("#form1");
	    form.validate();
	    if (form.isValid() == false) return;
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
	    var data = $("form").serialize();
	    var json = mini.encode(data);
	    console.log(json);
		$.ajax({
			type : "post",
		    url: $ctx+'/action/userBaseInfoManager/userBaseInfoManagerSave',
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
});

//验证用户名称
function onUserCodeValidation(e){
	console.log(e);
	var userBaseInfoId = $("input[name='userBaseInfoId']").val();
	console.log(userBaseInfoId);
	$.ajax({
		type : 'GET',
	    url: $ctx+'/action/userBaseInfoManager/UserBaseInfoCodeCheck?code='+e.value+'&id='+userBaseInfoId,
	    dataType:'json',
//	    async:false,
		success : function(text) {
			if(eval(text).isRepeat){
				e.isValid=false;
				mini.alert("该用户账号已存在，请重新输入");
				var ddate =mini.get("dataCode");
				ddate.setValue("");
				console.log(e);
			}
		},
		error:function(e){
			errorAjax(e);
		}
	});
} 

function beforenodeselect(e) {
    //禁止选中父节点
    if (e.isLeaf == false) e.cancel = true;
}