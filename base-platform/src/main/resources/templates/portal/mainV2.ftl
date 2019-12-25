<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${defaultSysMenu.menuName}</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script> 
    <link href="${globalContextPath}/css/portal/mainV2.css" rel="stylesheet" type="text/css" />
</head>
<body>
    
<!--Layout-->
<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
    <div class="app-header" region="north" height="70" showSplit="false" showHeader="false">
    	<div style="height:100%;">
    		<img style="height:96%;" src="${globalContextPath}/img/han_er_2019.png"></img>
    	</div>
        <div class="topNav">    
            <a>${SESSION_USER_ACCOUNT}</a> | 
            <a onclick="updatePassWord()">修改密码</a> |
            <a href="${globalContextPath}/action/logout">安全退出</a>
        </div>

        <div style="position:absolute;right:12px;bottom:8px;font-size:12px;line-height:25px;font-weight:normal;">
            <a>上次登录时间:${lastTime!''}</a> |
        	系统切换：
            <select id="selectSkin" onchange="onSkinChange(this.value)" style="width:110px;" >
            <#if systemList?? && (systemList?size >0) >
				 <#list systemList as items>
				 	<#if items.menuCode==defaultSysMenu.menuCode>
				 		<option value="${items.menuCode}" selected="selected" >${items.menuName}</option>
				 	<#else>
				 		<option value="${items.menuCode}"  >${items.menuName}</option>
				 	</#if>
				 </#list>
			 </#if>
            </select>
        </div>
    </div>
    <div title="south" region="south" showSplit="false" showHeader="false" height="30" >
    	<div id="showdiv" name="showdiv" class="showdiv" style="position: absolute;right :0;bottom:0;weight:20px !important;height:20px;">
    		<lable style="margin:10px;font-weight:bold;">个人事项</lable>
    	</div>
        <div style="line-height:28px;text-align:center;cursor:default">Copyright © 东恒鑫源软件开发有限公司版权所有 </div>
    </div>
    <div title="center" region="center" style="border:0;" bodyStyle="overflow:hidden;">
        <!--Splitter-->
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="180" maxSize="250" minSize="100" showCollapseButton="true" style="border:0;">
                <!--OutlookTree-->
                <div id="leftTree" class="mini-outlooktree" url="${globalContextPath}/action/menuData?parentCode=${defaultSysMenu.menuCode}" onnodeclick="onNodeSelect"
                    textField="text" idField="id" parentField="pid"             
                >
                </div>
            </div>
            <div showCollapseButton="false" style="border:0;">
                <!--Tabs-->
                <div id="mainTabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;"      
                     plain="false" onactivechanged="onTabsActiveChanged" contextMenu="#tabsMenu"
                >
                    <div title="首页" name="first" url="${defaultSysMenu.menuUrl}" ></div>
                </div>
            </div>        
        </div>
    </div>
</div>

<ul id="tabsMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">        
    <li onclick="refresh">刷新标签页</li>                
    <li onclick="closeTab">关闭标签页</li>                
    <li onclick="closeAllButFirst">关闭其他[首页除外]</li>   
</ul>

<script type="text/javascript">
	 $(document).ready(function(){
		$("#showdiv").mouseenter(function(){
			openAtPos();
		});
	});

    mini.parse();
    var tree = mini.get("leftTree");
 	
	var tabs = mini.get("mainTabs");
	var currentTab = null;
	function onBeforeOpen(e) {
        currentTab = tabs.getTabByEvent(e.htmlEvent);
		if(currentTab.name=="first"){
			e.cancel = true;
			e.htmlEvent.preventDefault();
		}
    }
	function refresh(){
		tabs.reloadTab(currentTab);
	}
	
    function closeTab() {
    console.log(currentTab.name);
    	if (currentTab.name != "first") {
       	 tabs.removeTab(currentTab);
        }
    }
    function closeAllButFirst() {
        var but = [currentTab];            
        but.push(tabs.getTab("first"));
        tabs.removeAll(but);
    }
   
    function showTab(node) {
        var tabs = mini.get("mainTabs");

        var id = "tab$" + node.id;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            tab._nodeid = node.id;
            tab.name = id;
            tab.title = node.text;
            tab.showCloseButton = true;
            //这里拼接了url，实际项目，应该从后台直接获得完整的url地址
            tab.url = node.attribute1;
            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    }

    function onNodeSelect(e) {
        var node = e.node;
        var isLeaf = e.isLeaf;
        if (isLeaf) {
            showTab(node);
        }
    }

    function onClick(e) {
        var text = this.getText();
        alert(text);
    }
    function onQuickClick(e) {
        tree.expandPath("datagrid");
        tree.selectNode("datagrid");
    }
	function onSkinChange(s){
		window.top.location.href="${globalContextPath}/action/portalV2?systemCode="+s;
	}
    function onTabsActiveChanged(e) {
        var tabs = e.sender;
        var tab = tabs.getActiveTab();
        if (tab && tab._nodeid) {
            var node = tree.getNode(tab._nodeid);
            if (node && !tree.isSelectedNode(node)) {
                tree.selectNode(node);
            }
        }
    }
    var WinAlerts = window.alert;
    window.alert = function (e) {
        if (e != null && e.indexOf("提示内容")>-1)
        { 
        //和谐了
        }
        else
        {
        	WinAlerts (e);
        }
    };
    
    function updatePassWord(){
 		mini.open({
	        targetWindow: window,
	        showCloseButton:true,
	        url: $ctx + "/action/password/passwordManager",
	        title: "修改密码",
	        allowResize: false,
	        showModal: true,
	        width: 600, 
	        height: 350,
	        onload: function () {
	            var iframe = this.getIFrameEl();
	        },
	        ondestroy: function (action) {
	            grid.reload();
	        }
    	});
	}
    
    function openAtPos() {
        var win1 = mini.open({
            title: '个人事项',
            url: $ctx + '/action/flow/task/task',
            showModal: false,
            allowDrag:false,
            allowResize: false,
            showCloseButton:true,
            width: 500,
            height: 300
        });
        win1.showAtPos('right','bottom');
    }
    openAtPos();
</script>
</body>
</html>