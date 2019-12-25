<!DOCTYPE <html>
<html>
<head>
<meta charset="UTF-8" />
<!-- 加载公共样式文件 -->
<link rel="stylesheet" href="${stackMap['globalContextPath']}/webjars/bootstrap/3.3.5/css/bootstrap.css">
<link rel="stylesheet" href="${stackMap['globalContextPath']}/css/datatables/jquery.dataTables.min.css" type="text/css" />
<!-- 加载公共样式文件 -->
<script type="text/javascript" src="${stackMap['globalContextPath']}/webjars/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"	src="${stackMap['globalContextPath']}/js/datatables/jquery.dataTables.min.js"></script>
<!-- 加载平台文件 -->
<link rel="stylesheet" href="${stackMap['platformPath']}/css/base/base.css" type="text/css" />
<script type="text/javascript"	src="${stackMap['platformPath']}/js/base/base.js"></script>
<script type="text/javascript" >
document.domain="${stackMap['domain']}";
</script>
<script type="text/javascript" src="${stackMap['globalContextPath']}/js/main/index.js"></script>
<title>首页</title>

</head>
<body>

<#list stackMap?keys as key>
	<input type="hidden" id="${key}" name="${key}" value="${stackMap[key]!''}" />
</#list>

	<div id="container" style="height:100%;width:100%;margin: 0 0 0 0;padding: 0 0 0 0;" ></div>
</body>
</html>