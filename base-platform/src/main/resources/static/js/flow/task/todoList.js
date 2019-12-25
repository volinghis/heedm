var grid;
$(document).ready(function() {
	mini.parse();
	grid = mini.get("grid1");
	grid.load();
});

function oper(entityCode,formUrl,operMethod){
    mini.open({
        targetWindow: window.top,
        showCloseButton:true,
        showMaxButton:true,
        url: formUrl+"&entityCode="+entityCode,
        title: "任务处理", width: 900, height:820,
        onload: function () {
            var iframe = this.getIFrameEl();
            var data = { model: operMethod };
            var mm=iframe.contentWindow.initData(data);
        },
        ondestroy: function (action) {
        	grid.reload();
        }
    });
}

function onGridRowDbClick(e){
	oper(e.record.entityCode,e.record.formUrl,e.record.operMethod);
}