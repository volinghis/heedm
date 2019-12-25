<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${globalContextPath}/webjars/bootstrap/3.3.5/css/bootstrap.css">
<link rel="stylesheet" href="${globalContextPath}/css/base/base.css" type="text/css" />
<link rel="stylesheet" href="${globalContextPath}/css/portal/main.css" />
<script type="text/javascript" src="${globalContextPath}/webjars/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript"	src="${globalContextPath}/js/base/base.js"></script>
<script type="text/javascript" src="${globalContextPath}/js/portal/main.js"></script>
<script type="text/javascript" >
document.domain="${domain}";
</script>
<title>大唐电力</title>
</head>
<body >
	<input type="hidden" id="_globalPath" value="${globalPath}" />
	<input type="hidden" id="_globalContextPath" value="${globalContextPath}" />
	<input type="hidden" id="SESSION_USER_ACCOUNT" value="${SESSION_USER_ACCOUNT}" />
	<div  class="systemDiv">
		<table>
		 <#if systemList?? && (systemList?size >0) >
			<#list systemList as items>
			<tr><td class="lefttd" mcode="${items.menuCode}"><font class="leftfont">${items.menuName}</font></td></tr>
			</#list>
			</#if>
		</table>
	</div>
	<div class="top-left ">
		<table id="_lt">
			<tr>
				<td class="lefttd"><font class="leftfont">${defaultSysMenu.menuName}</font><span class="glyphicon  glyphicon-arrow-down" aria-hidden="true"></span> </td>

			</tr>
		</table>
	</div>
	<div class="top-right " >
		<table id="_mt">
			<tr>
			 <#if subMenuList?? && (subMenuList?size >0) >
				 <#list subMenuList as items>
				 	<td mcode="${items.menuCode}"> <font >${items.menuName}</font> </td>
				 </#list>
			 </#if>
			</tr>
		</table>
	</div>
	<div class="bottom "><iframe src="${defaultSysMenu.menuUrl!''}"></iframe></div>
</body>
</html>