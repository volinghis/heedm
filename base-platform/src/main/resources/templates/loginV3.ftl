<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>登录</title>
  <meta name="description" content="particles.js is a lightweight JavaScript library for creating particles.">
  <meta name="author" content="Vincent Garreau" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
  <link rel="stylesheet" media="screen" href="${globalContextPath}/css/loginV3/style.css">
  <link rel="stylesheet" type="text/css" href="${globalContextPath}/css/loginV3/reset.css"/>
</head>
<body>

<div id="particles-js">
		<img style="position:absolute;padding: 15px;" src="${globalContextPath}/img/han_er_2019_V3.png"></img>
		<div class="login" >
			<div class="login-top">
				<lable>设&nbsp;备&nbsp;管&nbsp;理&nbsp;系&nbsp;统</lable><p>
				<lable style="font-size:15px">Equipment Management System</lable>
			</div>
		  		<form id="myForm" method="post" action="${globalContextPath}/action/loginV3">
		  		<div></div></br>
				<div class="login-center clearfix">
					<div class="login-center-img"><img src="${globalContextPath}/img/name.png"/></div>
					<div class="login-center-input">
						<input type="text" name="username" value="" placeholder="请输入您的用户名" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的用户名'"/>
						<div class="login-center-input-text">用户名</div>
					</div>
				</div>
				<div class="login-center clearfix">
					<div class="login-center-img"><img src="${globalContextPath}/img/password.png"/></div>
					<div class="login-center-input">
						<input type="password" name="password" value="" placeholder="请输入您的密码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入您的密码'"/>
						<div class="login-center-input-text">密码</div>
					</div>
				</div>
				<#if msg??>
					<div class="login-center clearfix">
						<div class="login-center-img"><img src="${globalContextPath}/img/captchaCode.png" style="width:80px;"/></div>
						<div class="login-center-input">
							<input name="captchaCode" value="" placeholder="请输验证码" onfocus="this.placeholder=''" onblur="this.placeholder='请输入验证码' "style="width:125px;"/>
							<img  src="/img/getKaptchaImage?crsfToken=${crsfToken!''}" onclick="this.src='/img/getKaptchaImage?crsfToken=${crsfToken!''}?rand='+Math.random() "/>
							<div class="login-center-input-text">验证码</div>
						</div>
					</div>
					<input type="hidden" value="${crsfToken!''}" id="crsfToken" name="crsfToken"/>
					<div style="text-align:center; color:#d23232;">${msg}</div>
		    	</#if> 
				<div id="loginBt" class="login-button">登录</div>
			</form>	
		</div>
		<div class="sk-rotating-plane"></div>
		<div style="position:absolute;width:300px;height:60px;margin-left:43%;bottom:0px;font-size:10px">
			<lable style="color:#fff;font-size:12px;">Copyright © 东恒鑫源软件开发有限公司版权所有</lable>
		</div>
</div>

<!-- scripts 
<script src="${globalContextPath}/js/loginV3/particles.min.js"></script> 
<script src="${globalContextPath}/js/loginV3/app.js"></script> -->
<script type="text/javascript">
	 mini.parse();
   
	function hasClass(elem, cls) {
	  cls = cls || '';
	  if (cls.replace(/\s/g, '').length == 0) return false; //当cls没有参数时，返回false
	  return new RegExp(' ' + cls + ' ').test(' ' + elem.className + ' ');
	}
	 
	function addClass(ele, cls) {
	  if (!hasClass(ele, cls)) {
	    ele.className = ele.className == '' ? cls : ele.className + ' ' + cls;
	  }
	}
	 
	function removeClass(ele, cls) {
	  if (hasClass(ele, cls)) {
	    var newClass = ' ' + ele.className.replace(/[\t\r\n]/g, '') + ' ';
	    while (newClass.indexOf(' ' + cls + ' ') >= 0) {
	      newClass = newClass.replace(' ' + cls + ' ', ' ');
	    }
	    ele.className = newClass.replace(/^\s+|\s+$/g, '');
	  }
	  
	}
	var l=function login(){
		addClass(document.querySelector(".login"), "active")
		setTimeout(function(){
			addClass(document.querySelector(".sk-rotating-plane"), "active")
			document.querySelector(".login").style.display = "none"
		},800)
		setTimeout(function(){
			removeClass(document.querySelector(".login"), "active")
			removeClass(document.querySelector(".sk-rotating-plane"), "active")
			document.querySelector(".login").style.display = "block"
		},2000)
		$("form").submit();
	} 
	
	document.querySelector(".login-button").onclick = l;
		
	$(document).keydown(function(event){ 
		if(event.keyCode == 13){ //绑定回车 
			$('#loginBt').click();
		} 
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