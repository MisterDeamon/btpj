<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../common/proton.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>BTPJ_Management</title>
    <link rel="shortcut icon" href="<%=protonPath%>/assets/ico/favicon.ico" type="image/x-icon" />
    <!-- Css files -->
    <link href="<%=protonPath%>/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/css/jquery.mmenu.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/css/climacons-font.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/plugins/xcharts/css/xcharts.min.css" rel=" stylesheet">
    <link href="<%=protonPath%>/assets/plugins/fullcalendar/css/fullcalendar.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/plugins/morris/css/morris.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/plugins/jquery-ui/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/plugins/jvectormap/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/css/style.min.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/css/add-ons.min.css" rel="stylesheet">
    <link href="<%=protonPath%>/assets/css/customer.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="<%=protonPath%>/assets/js/html5shiv.js"></script>
    <script src="<%=protonPath%>/assets//respond.min.js"></script>
    <![endif]-->


    <script src="<%=protonPath%>/assets/js/jquery-2.1.1.min.js"></script>

    <script src="<%=protonPath%>/assets/js/jquery-migrate-1.2.1.min.js"></script>
    <script src="<%=protonPath%>/assets/js/bootstrap.min.js"></script>
    <script src="<%=protonPath%>/assets/js/customer.js"></script>
    <script src="<%=protonPath%>/assets/js/list.js"></script>
    <script src="<%=protonPath%>/assets/js/ajaxfileupload.js"></script>

    <!-- page scripts -->
    <script src="<%=protonPath%>/assets/plugins/jquery-ui/js/jquery-ui-1.10.4.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/touchpunch/jquery.ui.touch-punch.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/moment/moment.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/fullcalendar/js/fullcalendar.min.js"></script>
    <!--[if lte IE 8]>
    <script language="javascript" type="text/javascript" src="<%=protonPath%>/assets/plugins/excanvas/excanvas.min.js"></script>
    <![endif]-->
    <script src="<%=protonPath%>/assets/plugins/flot/jquery.flot.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/flot/jquery.flot.pie.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/flot/jquery.flot.stack.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/flot/jquery.flot.resize.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/flot/jquery.flot.time.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/flot/jquery.flot.spline.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/xcharts/js/xcharts.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/autosize/jquery.autosize.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/placeholder/jquery.placeholder.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/datatables/js/jquery.dataTables.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/datatables/js/dataTables.bootstrap.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/raphael/raphael.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/morris/js/morris.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/jvectormap/js/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/jvectormap/js/jquery-jvectormap-world-mill-en.js"></script>
    <script src="<%=protonPath%>/assets/plugins/jvectormap/js/gdp-data.js"></script>
    <script src="<%=protonPath%>/assets/plugins/gauge/gauge.min.js"></script>


    <!-- theme scripts -->
    <script src="<%=protonPath%>/assets/js/SmoothScroll.js"></script>
    <script src="<%=protonPath%>/assets/js/jquery.mmenu.min.js"></script>
    <script src="<%=protonPath%>/assets/js/core.min.js"></script>
    <script src="<%=protonPath%>/assets/plugins/d3/d3.min.js"></script>

    <!-- inline scripts related to this page -->
    <script src="<%=protonPath%>/assets/js/pages/index.js"></script>



    <link rel="stylesheet" href="<%=protonPath%>/assets/jQuery.emoji/lib/css/jquery.mCustomScrollbar.min.css"/>
    <!--the css for this plugin-->
    <link href="<%=protonPath%>/assets/jQuery.emoji/css/jquery.emoji.css" rel="stylesheet"/>
    <!--(Optional) the js for jquery.mCustomScrollbar's addon-->
    <script src="<%=protonPath%>/assets/jQuery.emoji/lib/script/jquery.mousewheel-3.0.6.min.js"></script>
    <!--the js for jquery.mCustomScrollbar-->
    <script src="<%=protonPath%>/assets/jQuery.emoji/lib/script/jquery.mCustomScrollbar.min.js"></script>
    <script src="<%=protonPath%>/assets/jQuery.emoji/js/jquery.emoji.js"></script>
    <script src="<%=protonPath%>/assets/js/jquery.cookie.js"></script>
    <script src="<%=protonPath%>/assets/js/json2.js"></script>
    <script src="<%=protonPath%>/assets/js/json.js"></script>



    <script type="text/javascript">
        var local = window.location;
        var contextPath = local.pathname.split("/")[1];
        var ctx = local.protocol+"//"+local.host;

        $.ajax({
            url:'${basePath}/management/weatherInfo',
            type:'get',
            dataType:'json',
            contentType:'application/json',
            success:function(data){
                $("i[name='nicon']").addClass(data.nowWeather.codeIcon);
                $("div[name='week']").html(data.nowWeather.date);
                $("small[name='codeText']").html(data.nowWeather.codeText);
                $("div[name='temp']").html(data.nowWeather.temp+"°C");
                $("strong[name='cityName']").append(data.nowWeather.cityName);

                $("i[name='fcon']").each(function(index,item){
                    $(item).addClass(data.forecastWeather[index].codeIcon);
                });
                $("span[name='fweek']").each(function(index,item){
                    $(item).html(data.forecastWeather[index].date);
                });
                $("p[name='ftemp']").each(function(index,item){
                    $(item).html(data.forecastWeather[index].temp);
                });
                $("small[name='fcodeText']").each(function(index,item){
                    $(item).html(data.forecastWeather[index].codeText);
                });
            }
        });
    </script>
    <script type="application/javascript">

    </script>
</head>
<body>
<!-- start: Header -->
<div class="navbar" role="navigation">

    <div class="container-fluid">

        <ul class="nav navbar-nav navbar-actions navbar-left">
            <li class="visible-md visible-lg"><a href="index.html#" id="main-menu-toggle"><i class="fa fa-th-large"></i></a>
            </li>
            <li class="visible-xs visible-sm"><a href="index.html#" id="sidebar-menu"><i class="fa fa-navicon"></i></a>
            </li>
        </ul>

        <form class="navbar-form navbar-left">
            <button type="submit" class="fa fa-search"></button>
            <input type="text" class="form-control" placeholder="搜索..."></a>
        </form>

        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown visible-md visible-lg">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-envelope-o"></i><span
                        class="badge">5</span></a>
                <ul class="dropdown-menu">
                    <li class="dropdown-menu-header">
                        <strong>Messages</strong>
                        <div class="progress thin">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="30"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 30%">
                                <span class="sr-only">30% Complete (success)</span>
                            </div>
                        </div>
                    </li>

                    <%--mail--%>
                    <li class="avatar">
                        <a href="page-inbox.html">
                            <img class="avatar" src="<%=protonPath%>/assets/img/avatar1.jpg">
                            <div>New message</div>
                            <small>1 minute ago</small>
                            <span class="label label-info">NEW</span>
                        </a>
                    </li>
                    <li class="avatar">
                        <a href="page-inbox.html">
                            <img class="avatar" src="<%=protonPath%>/assets/img/avatar2.jpg">
                            <div>New message</div>
                            <small>3 minute ago</small>
                            <span class="label label-info">NEW</span>
                        </a>
                    </li>
                    <li class="dropdown-menu-footer text-center">
                        <a href="page-inbox.html">View all messages</a>
                    </li>
                </ul>
            </li>
            <%--notification--%>
            <li class="dropdown visible-md visible-lg">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell-o"></i>
                    <span class="badge" id="newMessageCount"></span></a>
                <ul class="dropdown-menu">
                    <li class="dropdown-menu-header">
                        <strong>新消息</strong>
                        <div class="progress thin">
                            <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="30"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 30%">
                                <span class="sr-only">30% Complete (success)</span>
                            </div>
                        </div>
                    </li>
                    <li class="clearfix" style="display: none">
                        <i class="fa fa-comment"></i>
                        <span name="from"></span>
                        <span name="receiveDate"></span>
                        <span name="msgContent"></span>
                    </li>
                </ul>
            </li>

            <%--settings--%>
            <li class="dropdown visible-md visible-lg">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gears"></i></a>
                <ul class="dropdown-menu update-menu" role="menu">
                    <li><a href="#"><i class="fa fa-users"></i> Users </a></li>
                </ul>
            </li>

            <%--user info and setting--%>
            <li class="dropdown visible-md visible-lg">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <img  src="<%=basePath%>/file/upload/headpic/${user.headPicPath}" alt="user-mail" style="border-radius: 29px; width: 39px;height: 39px;">${user.email}</a>
                    <span style="display: none" name="userName">${user.userName}</span>
                <ul class="dropdown-menu">
                    <li class="dropdown-menu-header">
                        <strong>Account</strong>
                    </li>
                    <li><a href="page-profile.html"><i class="fa fa-user"></i> Profile</a></li>
                    <li><a href="page-login.html"><i class="fa fa-wrench"></i> Settings</a></li>
                    <li><a href="page-invoice.html"><i class="fa fa-usd"></i> Payments <span
                            class="label label-default">10</span></a></li>
                    <li><a href="gallery.html"><i class="fa fa-file"></i> File <span
                            class="label label-primary">27</span></a></li>
                    <li class="divider"></li>
                    <li><a href="<%=basePath%>/logout"><i class="fa fa-sign-out"></i> Logout</a></li>
                </ul>
            </li>
            <li><a href="<%=basePath%>/logout"><i class="fa fa-power-off"></i></a></li>
        </ul>
    </div>
</div>
<!-- end: Header -->

<div class="container-fluid content">
    <div class="row">
        <!-- start: Main Menu -->
        <div class="sidebar ">
            <div class="sidebar-collapse">
                <%--left-top logo--%>
                <div class="sidebar-header t-center">
                        <span>
                            <img class="text-logo" src="<%=protonPath%>/assets/img/logo1.png">
                            <i class="fa fa-space-shuttle fa-3x blue"></i>
                        </span>
                </div>

                <%--left menu--%>
                <div class="sidebar-menu">
                    <ul class="nav nav-sidebar">
                        <li class="active">
                            <a href="<%=basePath%>/management/index"><i class="fa fa-laptop"></i><span
                                    class="text">首 页</span></a>
                        </li>
                        <c:forEach var="menu" items="${menus}">
                            <li>
                                <a href="${menu.url}">
                                    <i class="fa ${menu.icon}"></i><span class="text">${menu.name}</span>
                                    <c:if test="${fn:length(menu.childrenMenus)>0}">
                                    <span class="fa fa-angle-down pull-right"></span>
                                </a>
                                <ul class="nav sub">
                                    <c:forEach var="child" items="${menu.childrenMenus}">
                                        <li><a href="${child.url}" target=""><i class="fa ${child.icon}"></i><span
                                                class="text"> ${child.name}</span></a></li>
                                    </c:forEach>
                                </ul>
                                </c:if>
                                <c:if test="${fn:length(menu.childrenMenus)==0}">
                                    </a>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="sidebar-footer">
                <div class="sidebar-brand">
                    BTPJ
                </div>
                <ul class="sidebar-terms">
                    <li><a href="index.html#">Terms</a></li>
                    <li><a href="index.html#">Privacy</a></li>
                    <li><a href="index.html#">Help</a></li>
                    <li><a href="index.html#">About</a></li>
                </ul>

                <div class="copyright text-center">
                    <small>Proton <i class="fa fa-coffee"></i> from <a href="http://www.baidu.com/" title="BTPJ_JACK"
                                                                       target="_blank">BTPJ_JACK</a></small>
                </div>
            </div>

        </div>
        <!-- end: Main Menu -->

        <!-- start: Content -->
        <div class="main" id="main">
            <div class='row'>
                <div class='col-lg-12'><h3 class='page-header'><i class='fa fa-home'></i>首页</h3>
                    <ol class='breadcrumb'>
                        <li><i class='fa fa-home'></i>Home</li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <%-- start weather info--%>
                <div class="col-lg-3 col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body weather widget">
                            <div class="today text-center">
                                <h4 class="blue"><strong name="cityName"><i class="fa fa-map-marker fa-3x green"></i></strong></h4>
                                <i class="climacon" name="nicon"></i>

                                <div class="row">
                                    <div class="col-xs-5 text-right date">
                                        <div name="week"></div>
                                        <small name="codeText"></small>
                                    </div><!--/.col-->

                                    <div class="col-xs-7 text-left temp" name="temp">
                                    </div><!--/.col-->
                                </div><!--/.row-->
                            </div>

                            <div class="forecast row text-center">
                                <div class="col-xs-4">
                                    <i class="climacon" name="fcon"></i>
                                    <span class="label label-primary" name="fweek"></span>
                                    <small name="fcodeText"></small>
                                    <p name="ftemp"></p>
                                </div><!--/.col-->

                                <div class="col-xs-4">
                                    <i class="climacon" name="fcon"></i>
                                    <span class="label label-primary" name="fweek"></span>
                                    <small name="fcodeText"></small>
                                    <p name="ftemp"></p>
                                </div><!--/.col-->

                                <div class="col-xs-4">
                                    <i class="climacon" name="fcon"></i>
                                    <span class="label label-primary"  name="fweek"></span>
                                    <small name="fcodeText"></small>
                                    <p name="ftemp"></p>
                                </div><!--/.col-->

                            </div>
                        </div>
                    </div>
                </div>
                <%--weather info end--%>
            </div>

        </div>


    </div><!--/container-->


    <div class="modal fade" id="myModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Warning Title</h4>
                </div>
                <div class="modal-body">
                    <p>Here settings can be configured...</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

    <div class="clearfix"></div>
</div>
</body>
</html>