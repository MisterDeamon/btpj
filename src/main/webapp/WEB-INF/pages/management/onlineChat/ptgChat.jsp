<%--
  Created by IntelliJ IDEA.
  User: wajiangk
  Date: 10/9/2016
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getContextPath();
    String protonPath = basePath+"/styles/proton";
%>

<div class="row" id="info">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-chat red"></i><span class="break"></span><img src="<%=basePath%>/${headPicPath}" style="width:30px;height:30px;"><strong>${friendCommentName}</strong></h2>
            </div>
            <div class="panel-body">
                <tr><td>aaaa</td></tr>
                <tr><td>aaaa</td></tr>
                <tr><td>aaaa</td></tr>
                <tr><td>aaaa</td></tr>
                <tr><td>aaaa</td></tr>
                <tr><td>aaaa</td></tr>
                <tr><td>aaaa</td></tr>
            </div>
        </div><!--/col-->
    </div>
    <!--/row-->
</div>