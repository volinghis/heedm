<!doctype html>
<html>
<head>    
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initia l-scale=1" />
    <title></title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <link href="${globalContextPath}/res/third-party/scrollbar/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <script src="${globalContextPath}/res/third-party/scrollbar/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
    <script src="${globalContextPath}/js/portal/menu/menuV4.js" type="text/javascript"></script>
    <script src="${globalContextPath}/js/portal/menu/menupop.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/portal/tabsV4.css" rel="stylesheet" type="text/css" />
    <link href="${globalContextPath}/css/portal/frameV4.css" rel="stylesheet" type="text/css" />
    <link href="${globalContextPath}/css/portal/menuV4.css" rel="stylesheet" type="text/css" />
    <link href="${globalContextPath}/css/portal/index.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
		.mini-window .mini-panel-header {
		    background: #0078d4;
		}
		.mini-panel .mini-panel-title{
			color:aliceblue;
		}
	</style>
</head>
<body>
	<div class="sidebar">
	    <div id="mainMenu"></div>
	</div>

<div class="container">
    <div class="navbar" style="background-color:#0078d4">
        <div class="navbar-brand" style="padding:1px;background-color: #0078d4;">
       		 <img style="height:100%;padding: 8px;" src="${globalContextPath}/img/haner_v4.png"></img>
        </div>
        <ul class="nav navbar-nav navbar-right">
        <!--    <li><a href="#"><i class="fa fa-paper-plane"></i> 代办事项</a></li>   -->
            <li class="dropdown">
                <a class="dropdown-toggle userinfo" style="background-color: #0078d4">
                    <img class="user-img" src="${globalContextPath}/images/user.jpg" />
                    <font style="color: aliceblue;">工号：${SESSION_USER_ACCOUNT}</font>&nbsp;&nbsp;
	                <font style="color: aliceblue;">姓名：${user.name!''}</font>&nbsp;&nbsp;
	                <font style="color: aliceblue;">部门：${user.orgName!'未分配'}</font>&nbsp;&nbsp;
	                <font style="color: aliceblue;">岗位：${user.position!'未分配'}</font>&nbsp;&nbsp;
                    <i class="fa fa-angle-down" style="color: aliceblue;"></i>
                </a>
                <ul class="dropdown-menu pull-right" >
                    <li style="cursor:pointer;"><a onclick="updatePassWord()"><i class="fa fa-pencil-square-o"></i>修改密码</a></li>
                    <li><a href="${globalContextPath}/action/logout"><i class="fa fa-sign-out"></i> 退出登录</a></li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="main">
        <div id="mainTabs" class="mini-tabs main-tabs" activeIndex="0" style="height:97%;" plain="false" contextMenu="#tabsMenu" arrowPosition="side" >
            <div name="index" iconCls="fa-home" url="${defaultSysMenu.menuUrl}" title="首页"></div>
        </div>
        <div id="showdiv" name="showdiv" class="showdiv" style="position: absolute;right :0;bottom:0;weight:20px !important;height:20px;">
    		<lable class="fa fa-bell-o" style="margin:5px;font-weight:bold;">个人事项</lable>
    	</div>
        <div style="line-height:28px;text-align:center;cursor:default">Copyright © 东恒鑫源软件开发有限公司版权所有 </div>
	</div>
	
	<ul id="tabsMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">        
	    <li onclick="refresh">刷新标签页</li>                
	    <li onclick="closeTab">关闭标签页</li>                
	    <li onclick="closeAllButFirst">关闭其他[首页除外]</li>   
	</ul>
</body>
</html>
<script>
	mini.parse();
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
    	if (currentTab.name != "index") {
       	 tabs.removeTab(currentTab);
        }
    }
    function closeAllButFirst() {
        var but = [currentTab];            
        but.push(tabs.getTab("index"));
        tabs.removeAll(but);
    }

    function activeTab(item) {
        var tabs = mini.get("mainTabs");
        var tab = tabs.getTab(item.id);
        if (!tab) {
            tab = { name: item.id, title: item.text, url: item.attribute1, iconCls: item.iconCls, showCloseButton: true };
            tab = tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    }
    
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
	
	$(document).ready(function(){
		$("#showdiv").mouseenter(function(){
			openAtPos();
		});
	});
	
	function openAtPos() {
        var win1 = mini.open({
            title: '个人事项',
            url: $ctx + '/action/flow/task/task',
            showModal: false,
            allowDrag:false,
            allowResize: false,
            showCloseButton:true,
            width: 500,
            height: 360
        });
        win1.showAtPos('right','bottom');
    }
    openAtPos();

    $(function () {
       //menu
        var menu = new Menu("#mainMenu", {
            itemclick: function (item) {
                if (!item.children) {
                    activeTab(item);
                }
            }
        });
        $(".sidebar").mCustomScrollbar({ autoHideScrollbar: true });

        function autoScrollbar() {
            var jq = $(".mCSB_container");
            if (jq.parent().height() >= jq.children().outerHeight()) {
                jq.css("height", "100%");
            } else {
                jq.css("height", "auto");
            }
        }
        $(window).on("resize", function () {
            autoScrollbar();
        });
        autoScrollbar();
        new MenuPop(menu);
        $.ajax({
            url: "${globalContextPath}/action/menuData?parentCode=${defaultSysMenu.menuCode}",
            success: function (text) {
                var data = mini.decode(text);
                menu.loadData(data);


            }
        })
        //toggle
        $("#toggle, .sidebar-toggle").click(function () {
            $('body').toggleClass('compact');
            mini.layout();
        });

        //dropdown
        $(".dropdown-toggle").click(function (event) {
            $(this).parent().addClass("open");
            return false;
        });

        $(document).click(function (event) {
            $(".dropdown").removeClass("open");
        });
    });
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

</script>
