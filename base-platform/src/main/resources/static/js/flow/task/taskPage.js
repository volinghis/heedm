$(document).ready(function() {
    $("#taskFrame").attr("src",$ctx+"/action/flow/task/todoListPage");
});
$(document).on("mousedown", function (event) {
    try {
        var $ = top.jQuery;
        $(top.document).trigger("topmousedown", [document]);
    } catch (ex) { 
    }

});
function onItemClick(e) {        
    var atr = e.item.attribute5;
    $("#taskFrame").attr("src",$ctx+atr);
}
