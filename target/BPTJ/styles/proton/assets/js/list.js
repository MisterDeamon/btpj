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
                url: url + "?userId=" + userIds[0],
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
                            url: url + "?userIds=" + userIds,
                            dataType: 'json',
                            type: 'post',
                            success: function (data) {
                                if (data.success) {
                                    showSySInfo(data);
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

    $("body").on("click","#roleSet",function(event){
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
                url: url + "?userId=" + userIds[0],
                dataType: 'html',
                type: 'get',
                success: function (data) {
                    $("#main").append(data);
                }
            });
        }
    });

    $("body").on("click","#submitSf",function(){
        var url = $("#search").attr("action");
        var data=  $("#search").serialize();
        $.ajax({
            url:url,
            type:'post',
            data:data,
            dataType:'html',
            success:function(content){
                $("#info").html(content);
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

});

function page(pageNum){
    var url = $("#search").attr("action");
    url+="?pageNum="+pageNum;
    $.ajax({
        url:url,
        type:'post',
        dataType:'html',
        success:function(content){
            $("#info").html(content);
        },
    });
}


