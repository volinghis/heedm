<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" >

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>登录</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <style type="text/css">
		html.mini-xs.mini-sm.mini-md.mini-lg.mini-lg-active body#body
		{
	    	background-image:url(${globalContextPath}/img/loginBackGround.jpg);
		    background-repeat : repeat;
	        background-size:25%;
	        visibility: hidden !important;
		}
		
		div#loginWindow.mini-panel.mini-corner-all.mini-window.mini-window-drag{
			visibility: visible !important;
		}
    </style>
</head>
<body id="body">   
<div id="loginWindow" class="mini-window" title="用户登录" style="width:350px;height:220px;" showModal="true" showCloseButton="false" >
    <div id="loginForm" style="padding:15px;padding-top:10px;">
    <form  method="post" action="${globalContextPath}/action/loginV2">
        <table >
            <tr>
                <td style="width:60px;text-align: right;"><label for="username$text">帐号：</label></td>
                <td>
                    <input id="username" name="username"  class="mini-textbox" required="true" style="width:150px;"/>
                </td>    
            </tr>
            <tr>
                <td style="width:60px;text-align: right;"><label for="password$text">密码：</label></td>
                <td>
                    <input id="password" name="password"  class="mini-password"  requiredErrorText="密码不能为空" required="true" style="width:150px;" onenter="onLoginClick"/>
                    &nbsp;&nbsp;<a href="#" >忘记密码?</a>
                </td>
            </tr>    
            <#if msg??>
            <tr>
                <td style="width:60px;text-align: right;"><label for="captchaCode$text">验证码：</label></td>
                <td valign="middle">
                    <input id="captchaCode" name="captchaCode" class="mini-textbox" requiredErrorText="验证码不能为空"  required="true" style="width:150px;vertical-align: middle;" onenter="onLoginClick"/>
                    &nbsp;&nbsp;<img  src="/img/getKaptchaImage?crsfToken=${crsfToken!''}" onclick="this.src='/img/getKaptchaImage?crsfToken=${crsfToken!''}?rand='+Math.random()" style="cursor: pointer;width: 56px;height: 24px;vertical-align: middle;">
              	 <input type="hidden" value="${crsfToken!''}" id="crsfToken" name="crsfToken"/>
                </td>
            </tr> 
            <tr>
                <td style="width:60px;">&nbsp;&nbsp;</td>
                <td>
                   <label>${msg}</label>
                </td>
            </tr>     
   		 	</#if>        
            <tr>
                <td></td>
                <td style="padding-top:5px;">
                    <a onclick="onLoginClick" class="mini-button" style="width:60px;">登录</a>
                    <a onclick="onResetClick" class="mini-button" style="width:60px;">重置</a>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>
    <script type="text/javascript">
        mini.parse();
        var loginWindow = mini.get("loginWindow");
        loginWindow.show();
        
        function onLoginClick(e) {
            var form = new mini.Form("#loginWindow");

            form.validate();
            if (form.isValid() == false) return;

            $("form").submit();
        }
        function onResetClick(e) {
            var form = new mini.Form("#loginWindow");
            form.clear();
        }
        $("form").submit(function(e){
            loginWindow.hide();
            mini.loading("正在登录，请稍后...", "登录提示");
        	});
        
        var WinAlerts = window.alert;
        window.alert = function (e) {
	        if (e != null && e.indexOf("提示内容")>-1)
	        { 
	        //和谐了
	        }
	        else
	        {
	        	WinAlerts (e);
	        }
        };
    </script>

</body>
</html>