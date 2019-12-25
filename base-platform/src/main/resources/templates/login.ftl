<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8" />

<link rel="stylesheet"
	href="${globalContextPath}/webjars/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="${globalContextPath}/css/login.css" />
<title>登录</title>
</head>
<body >
	<#if domain??>
	<script type="text/javascript" >
		document.domain="${domain}";
			</script>
	</#if>
	<#if errorMsg??>
		<script type="text/javascript" >
			alert('${errorMsg}');
			window.top.location.href = '${platformLogin!''}';
		</script>
	</#if>
	<div class="container">
		<form class="form-signin" method="post" action="/action/login">
			<h2 class="form-signin-heading">Please sign in</h2>
			<p>
				<label for="username" class="sr-only">Username</label> <input
					type="text" id="username" name="username" class="form-control"
					placeholder="Username" required="required" autofocus="">
			</p>
			<p>
				<label for="password" class="sr-only">Password</label> <input
					type="password" id="password" name="password" class="form-control"
					placeholder="Password" required="required">
			</p>
			<#if msg??>
			<p>
			<table style="border: 0px; padding: 0px;">
				<tr>
					<td width="80%"><label for="captchaCode" class="sr-only">验证码 </label> <input
						type="text" style="width: 80%" name="captchaCode" id="captchaCode"
						class="form-control" placeholder="CheckCode" required="required" />

					</td >
					<td ><img  src="/img/getKaptchaImage?crsfToken=${crsfToken!''}" onclick="this.src='/img/getKaptchaImage?crsfToken=${crsfToken!''}';" style="cursor: pointer;"></td>
				</tr>
			</table>
			</p>
			  		<input type="hidden" value="${crsfToken!''}" id="crsfToken" name="crsfToken"/>
       		${msg}
       		<p>
   		 </#if>

        <button  class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
      </form>
	</div>
</body>
</html>