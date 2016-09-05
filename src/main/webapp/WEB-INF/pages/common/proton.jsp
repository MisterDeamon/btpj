<%--
  Created by IntelliJ IDEA.
  User: wajiangk
  Date: 8/25/2016
  Time: 10:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
    String basePath = request.getContextPath();
    String protonPath = basePath+"/styles/proton";
%>
<script type="text/javascript">
    var local = window.location;
    var contextPath = local.pathname.split("/")[1];
    var ctx = local.protocol+"//"+local.host;
</script>
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

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="<%=protonPath%>/assets/js/html5shiv.js"></script>
<script src="<%=protonPath%>/assets//respond.min.js"></script>
<![endif]-->


<!-- start: JavaScript-->
<!--[if !IE]>-->

<script src="<%=protonPath%>/assets/js/jquery-2.1.1.min.js"></script>

<!--<![endif]-->

<!--[if IE]>

<script src="<%=protonPath%>/assets/js/jquery-1.11.1.min.js"></script>

<![endif]-->

<!--[if !IE]>-->

<script type="text/javascript">
    window.jQuery || document.write("<script src='<%=protonPath%>/assets/js/jquery-2.1.1.min.js'>"+"<"+"/script>");
</script>

<!--<![endif]-->

<!--[if IE]>

<script type="text/javascript">
window.jQuery || document.write("<script src='<%=protonPath%>/assets/js/jquery-1.11.1.min.js'>"+"<"+"/script>");
</script>

<![endif]-->
<script src="<%=protonPath%>/assets/js/jquery-migrate-1.2.1.min.js"></script>
<script src="<%=protonPath%>/assets/js/bootstrap.min.js"></script>


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

<!-- end: JavaScript-->
