var grid;
$(document).ready(function() {
	mini.parse();
    var grid1 = mini.get("grid1");
    grid=grid1;
    
    grid.load();
});

//查询
function search(){
	var paramsData={
		searchParam:mini.get("key").getValue()
	};
	console.log(paramsData);
	grid.load(paramsData);
}
 
