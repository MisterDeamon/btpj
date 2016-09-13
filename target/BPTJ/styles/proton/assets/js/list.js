/**
 * Created by wajiangk on 9/8/2016.
 */
$(document).ready(function () {
    //$("#checkbox").live("click",function(){
    //    $("input[name='checkbox']").attr('checked',this.checked);
    //});

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
                    console.log( userIds_temp[i].value)

                }
            }
        }
        if (userIds.length == 0) {
            alert("请选择删除对象");
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
                        //当点击确认(这里是确认删除,当然也可以是其他确认动作)的时候做的动作
                        $.ajax({
                            url: url + "?userIds=" + userIds,
                            dataType: 'json',
                            type: 'post',
                            success: function (data) {
                                $("#delete-confirm").dialog("close");
                                if (data.success) {
                                    $(url).parent().parent().find(".alert").removeClass("alert-danger").addClass("alert-success").find("strong").html(data.msg);
                                    $(url).parent().parent().find(".alert").show("slow");
                                    setTimeout(function () {
                                        $(url).parent().parent().find(".alert").hide("slow");
                                    }, 3000);
                                    setTimeout(function () {
                                        $(".sidebar-menu a").each(function () {
                                            if ($(this).parent().hasClass("active")) {
                                                $(this).click();
                                            }
                                        });
                                    }, 4000);
                                } else {
                                    alert("删除失败，系统内部错误");
                                }
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

});