<%--
  Created by IntelliJ IDEA.
  User: wajiangk
  Date: 8/25/2016
  Time: 10:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%
    String basePath = request.getContextPath();
    String cssPath = basePath+"/styles/css";
%>
<link href="<%=basePath%>/styles/bootstrap-3.3.7/bootstrap.min.css" rel="stylesheet">
<!-- 可选的Bootstrap主题文件（一般不使用） -->
<script src="<%=basePath%>/styles/bootstrap-3.3.7/bootstrap-theme.min.css"></script>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script type="text/javascript" src="<%=basePath%>/styles/js/common/jquery-1.8.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath%>/styles/bootstrap-3.3.7/js/bootstrap.min.js"></script>