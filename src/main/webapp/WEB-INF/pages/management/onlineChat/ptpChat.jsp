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
    String protonPath = basePath + "/styles/proton";
%>

<!--the css for jquery.mCustomScrollbar-->
<script type="text/javascript" src="<%=protonPath%>/assets/js/ptpChat.js"></script>
<div class="row" id="info">
    <div class="col-lg-7">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><img src="<%=basePath%>/file/upload/headpic/${headPicPath}"
                         style="width:30px;height:30px;"><strong>${friendCommentName}</strong></h2>
                <input name="chatWith" value="${friendUserName}" type="hidden">
                <div class="panel-actions">
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                    <a href="#" class="btn-close"><i class="fa fa-times"></i></a>
                </div>
                <div id="tou"></div>
            </div>
            <div class="panel-body">
                <div id="history" class="chatHistory">
                    <div class="hisContent">
                    </div>
                </div>
                <div class="btn-toolbar" data-role="editor-toolbar" data-target=".editor">
                    <div class="btn-group">
                        <button class="btn btn-default" data-edit="bold" id="btn" title="Bold (Ctrl/Cmd+B)"><i
                                class="fa fa-smile-o"></i></button>
                    </div>
                    <div class="btn-group">
                        <a class="btn btn-default" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i class="fa fa-undo"></i></a>
                        <a class="btn btn-default" data-edit="redo" title="Redo (Ctrl/Cmd+Y)"><i
                                class="fa fa-repeat"></i></a>
                    </div>
                </div>

                <div class="editorBox">
                    <div id="editor" contenteditable="true">

                    </div>
                </div>

                <div class="panel-footer">
                    <button type="button" class="btn btn-sm btn-success sendMsg" id="sendMsgTo${friendUserName}" ><i class="fa fa-dot-circle-o"></i>
                        发送
                    </button>
                </div>
            </div>
        </div><!--/col-->
    </div>
    <!--/row-->
</div>
<script>

</script>