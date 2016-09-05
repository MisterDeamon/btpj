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
%>
<link rel="stylesheet" href="<%=basePath%>/styles/jquery-ui-1.12.0/jquery-ui.min.css" />
<script type="text/javascript" src="<%=basePath%>/styles/js/common/jquery-1.8.1.min.js"></script>
<script src="<%=basePath%>/styles/jquery-ui-1.12.0/jquery-ui.min.js"></script>
<script src="<%=basePath%>/styles/js/common/utils.js"></script>
<script type="text/javascript">
    var local = window.location;
    var contextPath = local.pathname.split("/")[1];
    var ctx = local.protocol+"//"+local.host;
</script>