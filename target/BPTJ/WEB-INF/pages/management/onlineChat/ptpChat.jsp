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
<script type="text/javascript">


    $("#editor").emoji({
        button: "#btn",
        showTab: false,
        animation: 'slide',
        icons: [{
            name: "QQ表情",
            path: "<%=protonPath%>/assets/jQuery.emoji/img/qq/",
            maxNum: 91,
            excludeNums: [41, 45, 54],
            file: ".gif",
        }]
    });

</script>
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
                    <button type="button" class="btn btn-sm btn-success" id="sendMsg"><i class="fa fa-dot-circle-o"></i>
                        发送
                    </button>
                </div>
            </div>
        </div><!--/col-->
    </div>
    <!--/row-->
</div>
<script>
    Date.prototype.format = function(fmt)
    { //author: meizz
        var o = {
            "M+" : this.getMonth()+1, //月份
            "d+" : this.getDate(), //日
            "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
            "H+" : this.getHours(), //小时
            "m+" : this.getMinutes(), //分
            "s+" : this.getSeconds(), //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S" : this.getMilliseconds() //毫秒
        };
        if(/(y+)/.test(fmt))
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("("+ k +")").test(fmt))
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        return fmt;
    }
    $("#sendMsg").click(function () {
        var msg = $("#editor");
        var websocket = window.websocket;
        if (websocket != null && websocket!='undefined') {
            var friendName = $("input[name='chatWith']").val();
            var from = $("span[name='userName']").text();
            var date = new Date().format("yyyy-MM-dd HH:mm:ss");
            $(".hisContent").append("<div class='titleHeader'>${friendCommentName}"+ "("+date+")：" + "</div><span>"+$("#editor").html()+"</span>");
            var tempMsg = emojiPareToText(msg).replace(/"/g,"\"").replace(/:/g,"\:").replace(/,/g,"\,").replace(/{/g,"\{").replace(/}/g,"\}");
            var message =  "{'from':'"+from+"','target':'"+friendName+"','msgContent':'"+tempMsg+"'}";


            $("#editor").html("");
            $(".hisContent").scrollTop( $(".hisContent")[0].scrollHeight);
            websocket.send(message);
        } else {
            alert('websocket异常.');
        }

    });

    function emojiPareToText(node) {
        var iconsName = ["hehe","haha", "tushe","a", "ku","lu","kaixin", "han", "lei", "heixian","bishi","bugaoxing","zhenbang",
            "qian", "yiwen","yinxian","tu", "yi","weiqu", "huaxin","hu","xiaonian", "neng","taikaixin","huaji","mianqiang",
            "kuanghan", "guai", "shuijiao", "jinku", "shengqi","jinya","pen","aixin","xinsui", "meigui", "liwu", "caihong",
            "xxyl", "taiyang", "qianbi","dnegpao", "chabei", "dangao", "yinyue", "haha2","shenli", "damuzhi","ruo", "OK"];
        node.find("img").each(function(){
            var srcValue = $(this).attr("src").toString();
            var inconType=srcValue.substring(srcValue.lastIndexOf(".")+1);
            var iconNum;
            if(inconType=='jpg'){
                iconNum = srcValue.substring((srcValue.lastIndexOf("/img/tieba/")+11), (srcValue.lastIndexOf(".jpg")));
            }else{
                iconNum = srcValue.substring((srcValue.lastIndexOf("/img/qq/")+8), (srcValue.lastIndexOf(".gif")));
            }
            $(this).replaceWith("#qq_"+iconNum+"#");
        });
        return node.html().toString();
    }
    $("#editor").emojiParse({
        icons: [{
            path: "<%=protonPath%>/assets/jQuery.emoji/img/tieba/",
            file: ".jpg",
            placeholder: ":{alias}:",
            alias: {
                1: "hehe",
                2: "haha",
                3: "tushe",
                4: "a",
                5: "ku",
                6: "lu",
                7: "kaixin",
                8: "han",
                9: "lei",
                10: "heixian",
                11: "bishi",
                12: "bugaoxing",
                13: "zhenbang",
                14: "qian",
                15: "yiwen",
                16: "yinxian",
                17: "tu",
                18: "yi",
                19: "weiqu",
                20: "huaxin",
                21: "hu",
                22: "xiaonian",
                23: "neng",
                24: "taikaixin",
                25: "huaji",
                26: "mianqiang",
                27: "kuanghan",
                28: "guai",
                29: "shuijiao",
                30: "jinku",
                31: "shengqi",
                32: "jinya",
                33: "pen",
                34: "aixin",
                35: "xinsui",
                36: "meigui",
                37: "liwu",
                38: "caihong",
                39: "xxyl",
                40: "taiyang",
                41: "qianbi",
                42: "dnegpao",
                43: "chabei",
                44: "dangao",
                45: "yinyue",
                46: "haha2",
                47: "shenli",
                48: "damuzhi",
                49: "ruo",
                50: "OK"
            }
        }, {
            path: "<%=protonPath%>/assets/jQuery.emoji/img/qq/",
            file: ".gif",
            placeholder: "#qq_{alias}#"
        }]
    });
</script>