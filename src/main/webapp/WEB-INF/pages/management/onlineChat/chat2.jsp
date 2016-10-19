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
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="<%=protonPath%>/assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=protonPath%>/assets/js/jquery-2.1.1.min.js"></script>
    <!--<script type="text/javascript" src="js/jquery-1.7.2.js"></script>-->
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=protonPath%>/assets/js/bootstrap.min.js"></script>
    <title>webSocket测试</title>
    <script type="text/javascript">
        alert("coming");
        var websocket;
        function connectChatService(){
            if(websocket!='undefined' && websocket!=null){
                if ('WebSocket' in window) {
                    alert("WebSocket");
                    websocket = new WebSocket("ws://localhost:8080/websocket?${user.userName}");
                } else if ('MozWebSocket' in window) {
                    alert("MozWebSocket");
                    websocket = new MozWebSocket("ws://websocket");
                } else {
                    websocket = new SockJS("ws://localhost:8080/websocket?${user.userName}");
                }
            }
            websocket.onopen = function (evnt) {
                $("#tou").html("链接服务器成功!")
            };
            websocket.onmessage = function (evnt) {
               alert("onmsg");
            };
            websocket.onerror = function (evnt) {
            };
            websocket.onclose = function (evnt) {
                $("#tou").html("与服务器断开了链接!");
            }
        }
        $('#send').bind('click', function() {
            send();
        });
        function send(){
            if (websocket != null) {
                var message = document.getElementById('message').value;
                websocket.send(message);
                console.log(websocket);
            } else {
                alert('未与服务器链接.');
            }
        }
    </script>

</head>
<body>

<div class="page-header" id="tou">
    webSocket及时聊天Demo程序
</div>
<div class="well" id="msg">
</div>
<div class="col-lg">
    <div class="input-group">
        <input type="text" class="form-control" placeholder="发送信息..." id="message">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button" id="send" >发送</button>
      </span>
    </div><!-- /input-group -->
</div><!-- /.col-lg-6 -->
</div><!-- /.row -->
</body>

</html>