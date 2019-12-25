$(function() {
	mini.parse();
	$(".dosave").unbind("click").bind("click",function(){
		//提交表单数据
		var newpwd = $("input[name='newPassword']").val();
		var confirmPwd = $("input[name='confirmNewPassword']").val();
		if (newpwd != confirmPwd) {
			mini.alert("'新密码'与'确认密码'不一致,请重新输入");
			$("input[name='confirmNewPassword']").val("");
			return;
		}
		var form = new mini.Form("#form1");
	    form.validate();
	    if (form.isValid() == false) return;
	    mini.mask({html: '正在提交，请稍后.....', cls: 'mini-mask-loading'});
		$.ajax({
			type : "post",
		    url: $ctx+'/action/password/savePassword',
		    data: $("form").serialize(),
		    dataType:'json',
			success : function(text) {
				mini.unmask();
				ajaxResult(text,function(clickResult){
					if(text.resultType=='OK'){
						closeWin(clickResult);
						parent.location.href = $ctx+'/action/logout';
					}
					if(text.resultType=='ERROR'){
						$("input[name='oldPassword']").val("");
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