var grid;
$(document).ready(function() {
	mini.parse();
	var grid1 = mini.get("authGrid");
	grid=grid1;
	
});

//数据加载
function loadGrid(e){
	console.log(e);
	grid.load({ account: e.account });
}


function addRoleForAccount(){
	var curAccount= $("input[name='curAccount']").val();
	 mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        url: $ctx + "/action/accountManager/roleList",
	        title: "角色选择页面",
            width: 600, height: 400,
	        onload: function () {
	            var iframe = this.getIFrameEl();
	            var data = { model: "edit",account:curAccount };
	            iframe.contentWindow.getParams(data);
	        },
	        ondestroy: function (action) {
	            grid.reload();
	        }
	    });
}

//重绘
function onActionRenderer(e) {
    var s = '<a  href="javascript:removeRoleOfUser()" style="text-decoration:none;">【删除】</a>';
    return s;
}

//移除当前选中的用户角色
function removeRoleOfUser(){
	var row = grid.getSelected();
	console.log(row);
	mini.confirm("确定删除？","确定提示",function(action) {
		if (action == "ok") {
			 mini.mask({html: '正在执行操作，请稍后.....', cls: 'mini-mask-loading'});
			$.ajax({
				type : "post",
				dataType:'json',
				data:{account:row.account,roleCode:row.roleCode},
			    url: $ctx+'/action/accountManager/removeRoleForCurUser',
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