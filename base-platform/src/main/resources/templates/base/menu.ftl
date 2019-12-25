<link rel="stylesheet" href="${globalContextPath}/css/base/menu.css" />
<script type="text/javascript" src="${globalContextPath}/js/base/menu.js"></script>
<nav>
	<ul>
	<#list menuList as items>
		<#if (items.menuParentCode!'') == (parentCode!'') >
		<li >
			<a class="menuevent"  href="javascript:void(0)" link="${items.menuUrl!''}"><font>${items.menuName}</font></a>
			<ul>
			<#list menuList as itemSub>
			<#if (itemSub.menuParentCode!'') == (items.menuCode!'')>
				<li ><a class="menuevent" href="javascript:void(0)" link="${itemSub.menuUrl!''}"><font>${itemSub.menuName}</font></a></li>
			</#if>
			</#list>
			</ul>
		</li>	
		</#if>
	</#list>
	</ul>

<nav>