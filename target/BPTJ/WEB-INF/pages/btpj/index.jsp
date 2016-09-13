<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../common/jquery-ui_include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title>BTPJ_Management</title>

        <style type="text/css">
            body{
                background-image:url('<%=basePath%>/styles/images/desktop/flower.png');
                color:whitesmoke;
                width: 100%;
                font-family: Arial;
                line-height: 100%;
                margin:0px;
                margin-top: -12px;
            }
        </style>
        <link href="<%=basePath%>/styles/css/desktop/desktop.css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=basePath%>/styles/js/desktop/desktop.js"></script>
        <script type="text/javascript">
            $(document).bind("contextmenu",function(e){
                return false;
            });
            $(document).ready(function(){

                setInterval("currentHHmm()",1000);
                changeSysClick();
                closeRightBar();
            });
        </script>

    </head>
    <body>
        <div class="topbar" id="topbar">
           <div class="menu_left">
               <%--<li><img src="<%=basePath%>/styles/images/desktop/home.png" /></li>--%>
               <li>BTPJ_DESKTOP</li>
           </div>
            <div class="menu_right">
                <li><img src="<%=basePath%>/styles/images/desktop/mail_close.png" /></li>
                <li><img src="<%=basePath%>/styles/images/desktop/voices.png" /></li>
                <li id="time"></li>
                <li><img src="<%=basePath%>/styles/images/desktop/admin.png" style="width: 22px"/>${user.userName}</li>
                <li><img src="<%=basePath%>/styles/images/desktop/settings.png" style="width: 22px;height:22px"/></li>
            </div>
        </div>
        <div class="mainView" id="mainView">
            <div class="apps">
                <div class="app" name="fileManager"><img src="<%=basePath%>/styles/images/desktop/sysIcons/b16.ico">文件管理</div>
            </div>
            <div class="apps">
                <div class="app"><img src="<%=basePath%>/styles/images/desktop/sysIcons/exec.png">系统设置</div>
            </div>
            <div class="apps">
                <div class="app"><img src="<%=basePath%>/styles/images/desktop/sysIcons/folder_sound.png">音频文件</div>
            </div>
            <div class="appBg" id="test"></div>
        </div>
        <div id="rightClickBgMenu">
            <ul>
                <li>查看</li>
                <li>排序方式</li>
                <a href="javascript:window.location.reload();"><li>刷新</li></a>
                <li>注销</li>
                <li>个性化</li>
                <li>终端</li>
                <li>预留</li>
            </ul>
        </div>
        <div id="rightClickAppMenu">
            <ul>
                <li>打开</li>
                <li>复制</li>
                <a href="javascript:window.location.reload();"><li>粘贴</li></a>
                <li>删除</li>
                <li>属性</li>
            </ul>
        </div>
        <div id="filePanner">

        </div>
    </body>
</html>