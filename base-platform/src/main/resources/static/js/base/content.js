$(document).ready(function() {
	$("#contentIframe").height($("#contentIframe").parent().height()-$("#_menu").height());
	$("#_menu").load($("#_globalContextPath").val()+"/action/menu?parentCode="+$("#menuCode").val());
	
});