<!DOCTYPE>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>会话终止</title>
    </head>
    <body >
	<#if errorMsg??>
		<script type="text/javascript" >
			alert('${errorMsg}');
			window.top.location.href = "/action/loginV3";
		</script>
	</#if>
    </body>
</html>