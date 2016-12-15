/**
 * Created by wajiangk on 11/21/2016.
 */
$("#selectBookBtn").click(function(){
    $("#selectBook").click();
});
$("#selectBook").change(function(){
    $("#progress").css("width","5%");
    $("#progress").text("0");
    var fileReader = new FileReader();
    var file = this.files[0];
    var fileName = file.name;
    var fileSize = file.size;
    $("#fileInfo").html("&nbsp;&nbsp;&nbsp;"+fileName+" "+(fileSize/1024/1024).toFixed(2)+"MB");
    var chapters = [];
    fileReader.onload=function(e){
        var data = e.target.result;
        var content=data.toString().replace(/\u7b2c[\u4e00-\u9fa5]{1,}\u7ae0([\s\S]{0,50})http:\/\/www.qingkan520.com\//g,"").split(/\u7b2c[\u4e00-\u9fa5]{1,}\u7ae0/);

        for(var i=0;i<content.length;i++){
            var title = "第"+i+"章";
            var chapter = new Object();
            chapter.title=title;
            chapter.content=content[i];
            chapters.push(chapter);
        }
        // $(".content").text("<p>"+chapters[1].content+"</p>");
        for(var chapter in chapters){
            if(chapter==0){
                $("#bookName").text(chapters[chapter].content);
            }else{
                if(chapter==1){
                    $("#para").text(chapters[chapter].content);
                }
                if(chapter<20){
                    $(".chapter").append("<span>"+chapters[chapter].title+"</span>");
                    if(chapter==19&&chapters.length>19){
                        $(".chapter").append("<span id='perPage'><button id='np' class='btn btn-primary btn-xs'>下一页</button></span>");
                    }
                }
            }
        }

        $("body").on("click","#np",function(){
            var pageNo = $($(".chapter").find("span")[18]).text().replace(/[^0-9]/g,"");
            for(var i=0;i<19;i++){
                $(".chapter").find("span").each(function(index,item){
                    if(index==i){
                        $(item).text(chapters[i+parseInt(pageNo)].title);
                    }
                });
            }

            $("#perPage").html("<button id='lp' class='btn btn-primary btn-xs'>上一页</button><button id='np' class='btn btn-primary btn-xs'>下一页</button>");
        });
        $("body").on("click","#lp",function(){
            var pageNo = $($(".chapter").find("span")[0]).text().replace(/[^0-9]/g,"");
            for(var i=18;i>=0;i--){
                $(".chapter").find("span").each(function(index,item){
                    if(index==(18-i)){
                        $(item).text(chapters[pageNo-i].title);
                    }
                });
            }
            $("#perPage").html("<button id='lp' class='btn btn-primary btn-xs'>上一页</button><button id='np' class='btn btn-primary btn-xs'>下一页</button>");

        });


        // $(".categoryTog").height()*$(".chapter").find("span").length/2
        $(".categoryTog").css("margin-top",25*$(".chapter").find("span").length/2);
        $(".chapter").find("span").hover(function(){
            $(this).css("background","#d95043");
        });
        $(".chapter").find("span").mouseout(function(){
            $(this).css("background","#181d20");
        });

        $(".chapter").find("span").click(function(){
            var _this=this;
            $(".chapter").find("span").each(function(){
                if(this!=_this){
                    $(this).css({
                        "background":"#181d20",
                        "color":"#848788"
                    });
                }
            });
            $("#nowChap").text($(this).text());
            $(this).css({
                "background":"#d95043",
                "color":"#fff"
            });
           var title = $(this).text();
            // alert(title);
            // alert(getChaNoByTitle(title));
           if(getChaNoByTitle(title)!=-1){
               $("#para").text(chapters[getChaNoByTitle(title)].content);
           }
        });

        var getChaNoByTitle=function(title){
            for(var i in chapters){
                if(chapters[i].title==title){
                    return i;
                }
            }
            return -1;
        }
    };

    fileReader.onprogress= function(e) {
        e.loaded;
        //更新进度条
        $("#progress").css("width",(e.loaded/fileSize).toFixed(2)*100+"%");
        $("#progress").text((e.loaded/fileSize).toFixed(2)*100+"%");
    }
    fileReader.readAsText(file);

    $(".categoryTog").click(function(){
        var width;
        var toolbar_left;
        var toolbar_width;
        if($(".categoryTog").css("margin-left")=='131.719px'){ //hide
            $(".categoryTog").css({
                "margin-left":"0px",
                "color":"#848788"
            });
            $(".chapter").css({"color":"#181d20","overflow":"hidden"});
            $(".chapter").find("span").css({"color":"#181d20","overflow":"hidden"});
            $(".categoryTog").removeClass("fa-chevron-left");
            $(".categoryTog").addClass("fa-chevron-right");
            $(".toolbar").css({"margin-left":5,"width":"100%"});
            width='15px';
            toolbar_left=5;
            toolbar_width="96.8%";
        }else{ //show
            $(".categoryTog").css("margin-left","131.719px");
            $(".chapter").find("span").css({"color":"#848788","overflow":"hidden"});
            $(".categoryTog").removeClass("fa-chevron-right");
            $(".categoryTog").addClass("fa-chevron-left");
            width='150px';
            toolbar_left=140;
            toolbar_width="86.7%";
        }
        $(".toolbar").animate({
            "margin-left":toolbar_left,
            "width":toolbar_width
        },300);
        $(".chapter").animate({
            "width": width
        },300);
    });
});