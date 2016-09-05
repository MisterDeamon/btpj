/**
 * Created by wajiangk on 8/25/2016.
 */

function changeSysClick(){
    $.each($("body").children(),function(index,item){
        var id = $(item).attr('id');
        if(id=='topbar'){
            $(".topbar").mousedown(function(e){
                if(3== e.which){
                    alert("right click topbar");
                }
            });
        }else{
            $(this).children().mousedown(function (e) {
                if (3 == e.which) {

                    if ($(this).attr('class') == 'apps ui-draggable ui-draggable-handle') {
                        rightClickAppMenu(event);
                    } else {
                        rightClickBgMenu(event);
                    }
                }
            });
        }
    });

    $(".apps").draggable({ containment: "#mainView", scroll: false });
    $(".app").dblclick(function(){
        var str = $(this).attr("name");
        if(str="fileManager"){
            str=ctx+"/file";
        }
        openDir(str);
    });
   /* $(".apps").click(function(){
       $(this).css('background','rgba(28,189,22,0.5)');
    });*/
}

//鼠标右击bg事件
function rightClickBgMenu(e){
    var x= e.pageX;
    var y= e.pageY;
    $("#rightClickBgMenu").dialog({
        closeOnEscape: false,
        open: function(event, ui) { $(".ui-dialog-titlebar").hide(); },
        draggable:false,
        resizable: false,
        height: 250,
        width: 150,
        modal: false,
        position: {
            my: "center",
            at: "center",
            of: window,
            collision: "fit",
            // Ensure the titlebar is always visible
            using:function(){
                $(this).focus();
                $(this).css({
                    "position":"absolute",
                    "top":y+"px",//设置弹出框距离是页面顶端下的200px
                    "left":x+"px",
                    "border":"none",
                    "outline":"none",
                    "background":"none",
                    "z-index":"10000000",

                });
                $(".ui-dialog-content").css({
                    "padding-left":"0",
                    "padding-right":"0",
                    "width":"200",
                });

            }
        }
    });
}
function closeRightBar(){
    $("#rightClickBgMenu").mouseleave(function(){
        $(this).hide();
    });
    $("#rightClickAppMenu").mouseleave(function(){
        $(this).hide();
    });
}

function rightClickAppMenu(e){
    var x= e.pageX;
    var y= e.pageY;
    $("#rightClickAppMenu").dialog({
        closeOnEscape: false,
        open: function(event, ui) { $(".ui-dialog-titlebar").hide(); },
        draggable:false,
        resizable: false,
        height: 250,
        width: 150,
        modal: false,
        position: {
            my: "center",
            at: "center",
            of: window,
            collision: "fit",
            // Ensure the titlebar is always visible
            using:function(){
                $(this).css({
                    "position":"absolute",
                    "top":y+"px",//设置弹出框距离是页面顶端下的200px
                    "left":x+"px",
                    "border":"none",
                    "outline":"none",
                    "background":"none",
                    "z-index":"100000"

                });
                $(".ui-dialog-content").css({
                    "padding-left":"0",
                    "padding-right":"0",
                    "width":"200",
                });

            }
        }
    });
}