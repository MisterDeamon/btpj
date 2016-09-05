<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../common/proton.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>BTPJ_Management</title>
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
                <input type="text" class="form-control" placeholder="Search..."></a>
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
                                <img class="avatar" src="assets/img/avatar2.jpg">
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-bell-o"></i><span
                            class="badge">3</span></a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-menu-header">
                            <strong>Notifications</strong>
                            <div class="progress thin">
                                <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="30"
                                     aria-valuemin="0" aria-valuemax="100" style="width: 30%">
                                    <span class="sr-only">30% Complete (success)</span>
                                </div>
                            </div>
                        </li>
                        <li class="clearfix">
                            <i class="fa fa-comment"></i>
                            <a href="page-activity.html" class="notification-user"> Sharon Rose </a>
                            <span class="notification-action"> replied to your </span>
                            <a href="page-activity.html" class="notification-link"> comment</a>
                        </li>

                        <li class="dropdown-menu-footer text-center">
                            <a href="page-activity.html">View all notification</a>
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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><img class="user-avatar"
                                                                                    src="<%=protonPath%>/assets/img/avatar.jpg"
                                                                                    alt="user-mail">jhonsmith@mail.com</a>
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
                <li><a href="index.html"><i class="fa fa-power-off"></i></a></li>
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
                            <li>
                                <a href="index.html"><i class="fa fa-laptop"></i><span class="text">首 页</span></a>
                            </li>
                            <%--系统管理--%>
                            <li>
                                <a href="#">
                                    <i class="fa fa-file-text"></i><span class="text"> 系统管理</span>
                                    <span class="fa fa-angle-down pull-right"></span>
                                </a>
                                <ul class="nav sub">
                                    <li><a href="page-activity.html"><i class="fa fa-car"></i><span class="text"> Activity</span></a></li>
                                    <li><a href="page-inbox.html"><i class="fa fa-envelope"></i><span class="text"> Mail</span></a></li>
                                    <li><a href="page-invoice.html"><i class="fa fa-credit-card"></i><span class="text"> Invoice</span></a>
                                    </li>
                                </ul>
                            </li>

                            <%--其他设置--%>
                            <li>
                                <a href="#"><i class="fa fa-list-alt"></i><span class="text"> Forms</span> <span
                                        class="fa fa-angle-down pull-right"></span></a>
                                <ul class="nav sub">
                                    <li><a href="form-elements.html"><i class="fa fa-indent"></i><span class="text"> Form elements</span></a>
                                    </li>
                                    <li><a href="form-wizard.html"><i class="fa fa-tags"></i><span
                                            class="text"> Wizard</span></a></li>
                                    <li><a href="form-dropzone.html"><i class="fa fa-plus-square-o"></i><span class="text"> Dropzone Upload</span></a>
                                    </li>
                                    <li><a href="form-x-editable.html"><i class="fa fa-pencil"></i><span class="text"> X-editable</span></a>
                                    </li>
                                </ul>
                            </li>
                            <li><a href="table.html"><i class="fa fa-table"></i><span class="text"> Tables</span></a></li>
                            <li>
                                <a href="#"><i class="fa fa-signal"></i><span class="text"> Visual Chart</span> <span
                                        class="fa fa-angle-down pull-right"></span></a>
                                <ul class="nav sub">
                                    <li><a href="chart-flot.html"><i class="fa fa-random"></i><span
                                            class="text"> Flot Chart</span></a></li>
                                    <li><a href="chart-xchart.html"><i class="fa fa-retweet"></i><span
                                            class="text"> xChart</span></a></li>
                                    <li><a href="chart-other.html"><i class="fa fa-bar-chart-o"></i><span class="text"> Other</span></a>
                                    </li>
                                </ul>
                            </li>
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
            <div class="main">
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