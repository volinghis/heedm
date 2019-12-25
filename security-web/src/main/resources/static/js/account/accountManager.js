var grid;
$(document).ready(function() {
	mini.parse();
	var grid1 = mini.get("datagrid1");
	grid=grid1;
	grid.load();
});

//重绘状态
var states = [{ id: 0, text: '启用' }, { id:1 , text: '停用'}];        
function onStateRenderer(e) {
    for (var i = 0, l = states.length; i < l; i++) {
        var g = states[i];
        if (g.id == e.value) 
        return g.text;
    }
    return "";
}

function onActionRenderer(e) {
    var state=e.row.state;
    var stateStr;
    if(state==0){
    	stateStr='启用';
    }else{
    	stateStr='停用';
    }
    var s = '<a  href="javascript:authorize()" style="text-decoration:none;">【授权】</a>'
    		+ ' <a  href="javascript:lockOrUnlock()" style="text-decoration:none;" >【'+stateStr+'】</a>'
            + ' <a  href="javascript:resetPwd()" style="text-decoration:none;">【重置密码】</a>';

    return s;
}


//授权
function  authorize(e){
	var row = grid.getSelected();
	 mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        url: $ctx + "/action/accountManager/authorize?account="+row.account,
	        title: "授权页面",
	        width: parseInt($(document.body).outerWidth(true)/2), height:parseInt($(document.body).outerHeight(true)/1.5),
	        onload: function () {
	        	 var iframe = this.getIFrameEl();
	        	 var data = { model: "edit",account:row.account };
	             iframe.contentWindow.loadGrid(data);
	        },
	        ondestroy: function (action) {
	            grid.reload();
	        }
	    });
	
}

//重置密码
function resetPwd(e){
	var row = grid.getSelected();
	mini.confirm("确定重置密码？","确定提示",function(action) {
		if (action == "ok") {
			 mini.mask({html: '正在执行操作，请稍后.....', cls: 'mini-mask-loading'});
			$.ajax({
				type : "post",
				dataType:'json',
				data:{accoutParam:row.account},
			    url: $ctx+'/action/accountManager/resetPwd',
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
	
}

//锁定或解锁
function lockOrUnlock(){
	var row = grid.getSelected();
	var SESSION_USER_ACCOUNT=mini.get("SESSION_USER_ACCOUNT").getValue();
	if(SESSION_USER_ACCOUNT!="admin"){
		 mini.alert("对不起，您没有该权限");
		 return;
	 }
   var state=(row.state==0)?state=1:state=0;
   console.log(state);
	$.ajax({
		type : "post",
		dataType:'json',
		data:{accoutParam:row.account,stateParam:state},
	    url: $ctx+'/action/accountManager/lockOrUnlock',
		success : function(text) {
			ajaxResult(text,function(clickResult){
				if(text.resultType=='OK'){
					grid.reload();
				}
			});
		},
		error:function(e){
			errorAjax(e);
		}
	});
}

//查询
function search(){
	var paramsData={
		searchParam:mini.get("key").getValue()
	};
	console.log(paramsData);
	grid.load(paramsData);
}
