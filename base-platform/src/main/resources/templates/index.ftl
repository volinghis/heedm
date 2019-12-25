<!DOCTYPE <html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet" href="${globalContextPath}/webjars/bootstrap/3.3.5/css/bootstrap.css">
<link rel="stylesheet" href="${globalContextPath}/css/base/base.css" type="text/css" />
<script type="text/javascript" src="${globalContextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"	src="${globalContextPath}/js/base/base.js"></script>
<script type="text/javascript" >
document.domain="${domain}";
</script>
<link rel="stylesheet" href="${globalContextPath}/css/index.css" />
<script type="text/javascript" src="${globalContextPath}/js/main/index.js"></script>
<title>大唐电力</title>

</head>
<body>
	<input type="hidden" id="_globalPath" value="${globalPath}" />
	<input type="hidden" id="_globalContextPath" value="${globalContextPath}" />
	<input type="hidden" id="SESSION_USER_ACCOUNT" value="${SESSION_USER_ACCOUNT}" />
	<div class="left"><iframe src="${systemMap['edm']}/action/index?RURL=/action/chart"></iframe></div>
	<div class="center"></div>
	<div class="right"><iframe src="${systemMap['security-web']}/action/index?RURL=/action/chart"></iframe></div>
	<div id="_container" class="container" ></div>
</body>
</html>