//公共操作权限验证函数
function permission(node,tree){
	var flag=false;
	var nodeLevel=tree.getLevel(node);
	var node_code;
	//alert("当前节点层级："+nodeLevel);
	if(nodeLevel!=1){
		//获取所有父节点
		var nodes =tree1.getAncestors(node);
		for(var i=0;i<nodes.length;i++){
			if(nodes[i]._level==1){
				console.log(nodes[i].name);
				node_code=nodes[i].id;
			}
		}
	}else{
			node_code=node.id;
	}
	
	//向服务器发送验证请求
	$.ajax({
		type : "post",
		dataType:'json',
		async:false,
		url: $ctx+'/action/checkOperPermission?code='+node_code,
		success : function(res) {
			console.log(res);
			if(res.resultType=="ERROR"){
				mini.alert(res.message);
				flag=true;
			}
		},
		error:function(e){
			mini.unmask();
			errorAjax(e);
		}
	});
		return flag;
}

function otherPermission(code){
	var flag=false;
	console.log(code);
	//向服务器发送验证请求
	$.ajax({
		type : "post",
		dataType:'json',
		async:false,
		url: $ctx+'/action/checkOperPermission?code='+code,
		success : function(res) {
			console.log(res);
			if(res.resultType=="ERROR"){
				mini.alert(res.message);
				flag=true;
			}
		},
		error:function(e){
			mini.unmask();
			errorAjax(e);
		}
	});
	return flag;
}