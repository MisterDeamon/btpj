<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%
    String basePath = request.getContextPath();
    String cssPath = basePath+"/styles/css";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>BTPJ_LOGIN</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <link  href="<%=cssPath%>/login/login.css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=basePath%>/styles/js/common/jquery-1.8.1.min.js"></script>
        <script type="text/javascript" src="<%=basePath%>/styles/js/login/login.js"></script>
        <style type="text/css">
            body{
                background-image:url('<%=basePath%>/styles/images/login/bg.jpg');
                color:whitesmoke;
                font-family: Arial;
                line-height: 100%;
                margin:0px;
                margin-top: -12px;
            }
        </style>
        <script type="text/javascript">
            var local = window.location;
            var contextPath = local.pathname.split("/")[1];
            var ctx = local.protocol+"//"+local.host;

            $(document).ready(function(){
                setInterval("currentTime()",1000);
                info('${msg}');
                closeInfo();
                checkName();
            });


        </script>
    </head>
    <body>
        <div class="sysMenu">
            <div id="currentTime"class="currentTime"></div>
        </div>
        <div class="lgform">
            <div class="info" id="info">

            </div>
            <div class="headPic">
                <img src="<%=basePath%>/styles/images/login/head.jpg" id="headPic"/>
            </div>
            <div class="boundaryLine" ></div>

            <form class="inputDiv" method="post" autocomplete="off">

                <li>
                    <span><img src="<%=basePath%>/styles/images/login/account.jpg" /></span>
                    <input name="userName" value="" type="text" class="inputText">
                </li>
                <li>
                    <span><img src="<%=basePath%>/styles/images/login/key.jpg" /></span><input name="passwd" value="" type="password">
                </li>
                <div class="butt">
                    <button type="reset"><img src="<%=basePath%>/styles/images/login/reset.png" alt="Reset" title="Reset"/></button>
                    <button type="submit"><img src="<%=basePath%>/styles/images/login/login.png" alt="Login" title="Login"/></button>
                </div>
            </form>

        </div>
    </body>
</html>