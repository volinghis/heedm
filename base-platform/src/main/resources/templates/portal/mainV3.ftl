<!doctype html>
<html>
<head>    
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title></title>
    <script src="${globalContextPath}/scripts/boot.js?ctx=${globalContextPath}&domain=${domain}" type="text/javascript"></script>
    <link href="${globalContextPath}/res/third-party/scrollbar/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <script src="${globalContextPath}/res/third-party/scrollbar/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/portal/menu/menu.css" rel="stylesheet" type="text/css" />
    <script src="${globalContextPath}/js/portal/menu/menu.js" type="text/javascript"></script>
    <script src="${globalContextPath}/js/portal/menutip.js" type="text/javascript"></script>
    <link href="${globalContextPath}/css/portal/tabs.css" rel="stylesheet" type="text/css" />
    <link href="${globalContextPath}/css/portal/frame.css" rel="stylesheet" type="text/css" />
    <link href="${globalContextPath}/css/portal/mainV3.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
    	body {
			padding: 0;
			margin: 0;
		}
		div#mainMenu ul.menu li.has-children a.menu-title{
			line-height: 40px;
			color: #f8f8f8;
		
		}
		div#mainMenu ul.menu li.has-children {
			color: #f8f8f8;
		}
		div#mainMenu ul.menu li.has-children {
	    	background: rgba(0,0,0,.5);
		}
		
	
    </style>
</head>
<body>
	<div class="navbar">
	    <div class="navbar-header">
	        <div class="navbar-brand">
	        	<img style="height:140%;padding: 1px;" src="${globalContextPath}/img/han_er_2019_V3.png"></img>
	        </div>
	        <div class="navbar-brand navbar-brand-compact">
	        	<img style="height:140%;padding: 1px;" src="${globalContextPath}/img/tang_V3.png"></img>
	        </div>
	    </div>
	    <ul class="nav navbar-nav">
	        <li><a id="toggle"><span class="fa fa-bars" ></span></a></li>
	        <#if systemList?? && (systemList?size >0) >
				 <#list systemList as items>
				 	<#if items.menuCode==defaultSysMenu.menuCode>
				 	<li class="icontop" menuCode="${items.menuCode}"><a href="#"><i class="${items.menuIcon}"></i><span >${items.menuName}</span></a></li>
				 	<#else>
				 	<li class="icontop" menuCode="${items.menuCode}"><a href="#"><i class="${items.menuIcon}"></i><span >${items.menuName}</span></a></li>
				 	</#if>
				 </#list>
			 </#if>
	    </ul>
	    <ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	            <a class="dropdown-toggle userinfo">
	                <img class="user-img" src="${globalContextPath}/images/user_V3_1.png" />
	                <font>${SESSION_USER_ACCOUNT}</font>&nbsp;&nbsp;
	                <i class="fa fa-angle-down"></i>
	            </a>
	            <ul class="dropdown-menu pull-right">
	                <li><a onclick="updatePassWord()"><i class="fa fa-pencil-square-o"></i> 修改密码</a></li>
	                <li><a href="${globalContextPath}/action/logout"><i class="fa fa-user"></i> 退出登录</a></li>
	            </ul>
	        </li>
	    </ul>
	</div>

	<div class="container">
	    <div class="sidebar">
	        <div id="mainMenu"></div>
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
    	$(".icontop").unbind("click").bind("click",function(){
    		$(".icontop").removeClass("active");
    		$(this).addClass("active");
    		window.top.location.href="${globalContextPath}/action/portalV2?systemCode="+$(this).attr("menuCode");
    	});
        //menu
        var menu = new Menu("#mainMenu", {
            itemclick: function (item) {
                if (!item.children) {
                    activeTab(item);
                }
            }
        });

        $(".sidebar").mCustomScrollbar({ autoHideScrollbar: true });
        new MenuTip(menu);
        $.ajax({
            url: "${globalContextPath}/action/menuData?parentCode=${defaultSysMenu.menuCode}",
            success: function (text) {
                var data = mini.decode(text);
                menu.loadData(data);
  				$(".has-children:first").addClass("open");
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

</script>
