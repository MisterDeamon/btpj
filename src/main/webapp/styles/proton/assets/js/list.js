/**
 * Created by wajiangk on 9/8/2016.
 */



$(document).ready(function () {

    $("body").on("click", "#checkbox", function () {
        $("input[name='checkbox']").attr('checked', this.checked);
    });

    //增加对象
    $("body").on("click", "#create", function (event) {
        event.preventDefault();
        $.ajax({
            url: this,
            dataType: 'html',
            type: 'get',
            success: function (data) {
                $("#main").append(data);
            }
        });
    });

    //编辑对象
    $("body").on("click", "#modify", function (event) {
        event.preventDefault();
        var url=this;
        var ids_temp = $("input[name='checkbox']");
        var ids = new Array();
        for (var i = 0; i < ids_temp.length; i++) {
            if ($(ids_temp[i]).attr("checked") == 'checked') {
                if(ids_temp[i].value!="0"){
                    ids.push(ids_temp[i].value);
                }
            }
        }
        if (ids.length == 0) {
            chooseNoneAlert("chose-modify");
        }else{
            $.ajax({
                url: url + "?id=" + ids[0],
                dataType: 'html',
                type: 'get',
                success: function (data) {
                    $("#main").append(data);
                }
            });
        }
    });

    //删除对象
    $("body").on("click", "#remove", function (event) {
        event.preventDefault();
        var url = this;
        var ids_temp = $("input[name='checkbox']");
        var ids = new Array();
        for (var i = 0; i < ids_temp.length; i++) {
            if ($(ids_temp[i]).attr("checked") == 'checked') {
                if(ids_temp[i].value!="0"){
                    ids.push(ids_temp[i].value);
                }
            }
        }
        if (ids.length == 0) {
            chooseNoneAlert("chose-delete");
        } else {
            $("#delete-confirm").dialog({
                closeOnEscape: false,
                open: function (event, ui) {
                    $(".ui-dialog-titlebar").hide();
                },
                draggable: false,
                resizable: false,
                height: 121,
                width: 350,
                modal: true,
                position: {
                    using: function () {
                        $(this).css({
                            "margin-left": "39%",
                            "margin-top": "19%",
                            "background": "#ddd"
                        });
                        $(".ui-dialog-content").css({
                            "padding-left": "0",
                            "height": "30px",
                            "overflow": "hidden",
                        });
                        $(" .ui-dialog .ui-dialog-buttonpane").css({
                            "height": "54px",
                        });
                        $(".ui-dialog-buttonset").css({
                            "margin-left": "92px",
                            "position": "absolute",
                        });
                    }
                },
                buttons: {
                    "确定": function () {
                        $(this).dialog("close");
                        //当点击确认(这里是确认删除,当然也可以是其他确认动作)的时候做的动作
                        $.ajax({
                            url: url + "?ids=" + ids,
                            dataType: 'json',
                            type: 'post',
                            success: function (data) {
                                if (data.success) {
                                    showEditInfo($("#info"),data);
                                } else {
                                    alert("删除失败，系统内部错误");
                                }
                            },
                            error: function () {
                                console.log("error");
                            }
                        });
                    },
                    "取消": function () {
                        $(this).dialog("close");
                    },
                }
            });
        }
    });

    $("body").on("click","#roleSet,#rightSet",function(event){
        event.preventDefault();
        var url = this;
        var userIds_temp = $("input[name='checkbox']");
        var userIds = new Array();
        for (var i = 0; i < userIds_temp.length; i++) {
            if ($(userIds_temp[i]).attr("checked") == 'checked') {
                if(userIds_temp[i].value!="0"){
                    userIds.push(userIds_temp[i].value);
                }
            }
        }
        if (userIds.length == 0) {
            chooseNoneAlert("chose-modify");
        }else{
            $.ajax({
                url: url + "?id=" + userIds[0],
                dataType: 'html',
                type: 'get',
                success: function (data) {
                    $("#main").append(data);
                }
            });
        }
    });

    $("body").on("click","#submitSf",function(){
        var url = $(this).parent().parent().find("form").attr("action");
        var data=  $(this).parent().parent().find("form").serialize();
        $.ajax({
            url:url,
            type:'post',
            data:data,
            dataType:'html',
            success:function(content){
                if($("#infoFriend").length==1){
                    $("#infoFriend").html(content);
                }else{
                    $("#info").html(content);
                }

            }
        });
    });

    //refresh info div
    $("body").on("click",".btn-setting",function(event){
        event.preventDefault();
        var url = this;
        $.ajax({
            url:url,
            type:'post',
            dataType:'html',
            success:function(content){
                $("#info").html(content);
            }
        });
    });

    $("body").on("click", "a[name='acState']", function (event){
        event.preventDefault();
        var url = this;
        $.ajax({
            url:url,
            type:'get',
            dataType:'json',
            success:function(data){
                if(data.success){
                    showEditInfo($("#info"),data);
                }
            }
        });

    });
    function chooseNoneAlert(id){
        $("#"+id).dialog({
            closeOnEscape: false,
            open: function (event, ui) {
                $(".ui-dialog-titlebar").hide();
            },
            draggable: false,
            resizable: false,
            height: 121,
            width: 350,
            modal: true,
            position: {
                using: function () {
                    $(this).css({
                        "margin-left": "39%",
                        "margin-top": "19%",
                        "background": "#ddd"
                    });
                    $(".ui-dialog-content").css({
                        "padding-left": "0",
                        "height": "30px",
                        "overflow": "hidden",
                    });
                    $(" .ui-dialog .ui-dialog-buttonpane").css({
                        "height": "54px",
                    });
                    $(".ui-dialog-buttonset").css({
                        "margin-left": "125px",
                        "position": "absolute",
                    });
                }
            },
            buttons: {
                "确定": function () {
                    $(this).dialog("close");
                },
            }
        });
    }

    $("body").on("click","#subRoleSet",function(event){
        event.preventDefault();
        var form = $(this).parent().parent();
        var url= form.attr("action");
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'post',
            success: function (data) {
                showEditInfo(form,data);
            }
        });
    });

    $("body").on("click","#iconValue",function(){
        var node = this;

        $("#icon-choose").dialog({
            closeOnEscape: false,
            open: function (event, ui) {
                $(".ui-dialog-titlebar").css({
                    "background":"none",
                    "border":"none",
                    "color":"black"
                });
                $(".ui-button").hide();
                $("body").on("click",".fontawesome-icon-list .col-md-3",function(){
                    var cls = $(this).text();
                    $(node).find("input").val(cls);
                    console.log($(node).find("input").val(cls))
                    $(node).find("i").removeClass().addClass("fa").addClass(cls);
                    $(".ui-widget-overlay").remove();
                    $(".ui-dialog").remove();
                });
            },
            draggable: true,
            resizable: true,
            height: 350,
            width: 850,
            modal: true,
            position: {
                using: function () {
                    $(" .ui-dialog").css({
                        "margin-top":"11%",
                        "margin-left":"29%"
                    });

                }
            }
        });
    });

    $("body").on("click","input[name='isParent']",function(){
        if($(this).val()==0){
            $("#parentId").removeAttr("disabled");
        }else{
            $("#parentId").attr("disabled","disabled");
        }
    });
    $("body").on("click",".steps li",function(){
        var navigation=$(this).parent().parent();
        var index = 0;
        for(var i=0;i<navigation.find("li").length;i++){
            if(this==navigation.find("li")[i]){
                index=i;
            }
        }
        var $total = navigation.find('li').length;
        var $current = index+1;
        var $percent = ($current/$total) * 100;
        $('#wizard2').find('.progress-bar').css({width:$percent+'%'});

        $('#wizard2 > .steps li').each( function (index) {
            $(this).removeClass('complete');
            index += 1;
            if(index < $current) {
                $(this).addClass('complete');
            }
        });

        if($current >= $total) {
            $('#wizard2').find('.button-next').hide();
            $('#wizard2').find('.button-finish').show();
        } else {
            $('#wizard2').find('.button-next').show();
            $('#wizard2').find('.button-finish').hide();
        }
    });
    $("body").on("click","#addFriendB",function(){
        var firendUserId=$(this).parent().find("input[name='userId']").val();
        var friendUserName=$(this).parent().find("span[name='userName']").text();
        $("input[name='userFriend.userName']").val(friendUserName);
        $("input[name='userFriend.id']").val(firendUserId);
    });

    $("body").on("keypress","#editor",function(e){
        if(e.keyCode == '13'){
            e.preventDefault();
            $("#sendMsg").click();
        }
    });

    $("body").on("click","#startChat",function(event){
        event.preventDefault();
        var url = this;
        var friendCommentName = $(this).parent().find("span[name='friendCommentName']").text();
        var headPicPath = $(this).parent().find("span[name='headPicPath']").text();
        var friendUserName = $(this).parent().find("span[name='friendUserName']").text();

        url+="?friendCommentName="+friendCommentName+"&headPicPath="+headPicPath+"&friendUserName="+friendUserName;
        $.ajax({
            type:'get',
            url:url,
            dataType:'html',
            success:function(data){
                $("#main").append(data);
            }
        });

    });

    window.websocket;
    connectChatService();
    function connectChatService(){
        if(window.websocket=='undefined' || window.websocket==null){
            if ('WebSocket' in window) {
                window.websocket = new WebSocket("ws://localhost:8080/websocket");
            } else if ('MozWebSocket' in window) {
                window.websocket = new MozWebSocket("ws://websocket");
            } else {
                window.websocket = new SockJS("ws://localhost:8080/websocket");
            }
        }
        window.websocket.onopen = function (evnt) {
            console.log("connected successfully!");
        };
        window.websocket.onmessage = function (evnt) {
            var msg=JSON.parse(evnt.data);
            window.newMsgCount = Number($("#newMessageCount").text());
            window.newMsgCount++;
            $("#newMessageCount").text(window.newMsgCount);
            var notificationNode = $("#newMessageCount").parent().parent();
            $("#newMessageCount").parent().parent().find(".clearfix").css("display","block")
            var friendName=notificationNode.find(".clearfix").find("span[name='from']").text();
            var msgParsed = msg.msgContent.replace(/#qq_/g,"<img src='"+ctx+"/styles/proton//assets/jQuery.emoji/img/qq/").replace(/#/g,".gif'>");
            if(friendName==""||friendName==msg.from){
                notificationNode.find(".clearfix").find("span[name='from']").html(msg.from);
                notificationNode.find(".clearfix").find("span[name='receiveDate']").html(msg.receiveDate);
                notificationNode.find(".clearfix").find("span[name='msgContent']").html(msgParsed.substr(0,10)+"...");
            }else{
                  var newNotification = "<li class='clearfix'><i class='fa fa-comment'></i>"+
                        +"<span name='from'>"+msg.from+"</span>"
                        +"<span name='receiveDate'>"+msg.receiveDate+"</span>"
                        +"<span name='msgContent'>"+msgParsed.substr(0,10)+"...</span></li>"
                notificationNode.find(".dropdown-menu").append(newNotification);

            }

            $("input[name='chatWith']").each(function(){
                if($(this).val()==msg.from){
                    $(this).parent().parent().find("#history").find(".hisContent")
                        .append("<div class='titleHeader'>"+ msg.from+"("+msg.from+")：" + "</div><span>"+msgParsed+"</span>");
                    $(".hisContent").scrollTop( $(".hisContent")[0].scrollHeight);

                }
            });


        };
        window.websocket.onerror = function (evnt) {
        };
        window.websocket.onclose = function (evnt) {
            $("#tou").html("与服务器断开了链接!");
        }
    }


});

function page(pageNum){
    var url;
    var data;
    var id;
    if($("#searchFriend").length==1){
        url=$("#searchFriend").attr("action")
        data = $("#searchFriend").serialize();
        id="#infoFriend";
    }else{
        url = $("#search").attr("action");
        data = $("#search").serialize();
        id="#info";
    }
    url+="?pageNum="+pageNum;
    $.ajax({
        url:url,
        data:data,
        type:'post',
        dataType:'html',
        success:function(content){
            $(id).html(content);
        },
    });
}


