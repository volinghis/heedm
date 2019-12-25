
$(document).ready(function() {
	
	$(".menuevent").unbind("bind").bind("click",function(){
		if($(this).attr("link")!=''){
			$("#contentIframe").attr("src",$(this).attr("link"));
		}
	});
});