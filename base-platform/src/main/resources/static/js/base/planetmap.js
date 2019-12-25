$(document).ready(function() {
	initImg();
	initHands();
	var resizeTimer = null;
	$(window).bind('resize', function (){
		$(".area-box").remove();
    	initImg();
    	initHands();
	});
});

function initHands() {
	var initIndex = 101;
	$("area").each(function(idx, obj) {
		var postions = "";
		var coords = $(this).attr("coords").split(",");
		for (var i = 0; i < coords.length; i++) {
			if (i % 2 == 0) {
				coords[i]=parseInt(coords[i])+$(".left").width()
			}
			postions = postions + coords[i] + "px "
			if (i % 2 != 0 && i < coords.length - 1) {
				postions = postions + ","
			}
		}
		var pos_div = $("<div>");
		pos_div.css("z-index", initIndex+idx);
		pos_div.css("-webkit-clip-path", "polygon(" + postions + ")");
		pos_div.css("opacity", "0.7");
		pos_div.addClass("area-box");
		pos_div.attr("dataTarget", $(this).attr("id"));
		pos_div.hide();
		if($(this).attr("model")=="stop"){
			pos_div.css("display", "block");
			pos_div.css("background-color", "black");
		}
		if($(this).attr("model")=="error"){
			pos_div.css("display", "block");
			pos_div.css("background-color", "red");
		}
		$("body").append(pos_div);
	})
	bindAreaBoxEvent();
}
function area_div_show(obj) {
	$("#_container").load($("#_globalContextPath").val()+"/action/base/content?menuCode=00007&groupCode=jzbh");
	$("#_container").show();
}
function bindAreaBoxEvent() {
	$(".area-box").unbind("mouseout").bind("mouseout",function(){
			if(!$("#"+$(this).attr("dataTarget")).attr("model")){
				$(this).hide();
			}
	})
	$(".area-box").unbind("click").bind("click",function(){
		area_div_show($(this));
	})
}
function initImg() {
	// 获取body的宽
	var _clientWidth =  $(".center").width();
	// 获取body的高
	var _clientHeight = $(".center").height();
	// 获得图片的宽
	var imgWidth = parseInt($("#bgImg").attr("normalWidth"));
	// 获得图片的高
	var imgHeight = parseInt($("#bgImg").attr("normalHeight"));

	// 获取area所有内容
	$("area").each(function(idx, obj) {
	    var tmpdata1 = "";
	    var arr1 = $(this).attr("normalcoords").split(',');
	    for (var i = 0; i < arr1.length; i++) {
	        if (i % 2 == 0) {
	            tmpdata1 = tmpdata1 + parseInt(arr1[i] * _clientWidth / imgWidth) + ",";
	        } else {
	            tmpdata1 = tmpdata1 + parseInt(arr1[i] * _clientHeight / imgHeight) + ",";
	        }
	    }
	    tmpdata1 = tmpdata1.substring(0, tmpdata1.lastIndexOf(','));

	    $(this).attr("coords", tmpdata1);
	    $(this).bind("mouseover",function() {
	        $("div[dataTarget='" + $(this).attr("id") + "']").show();
	    });
	   
	});
}