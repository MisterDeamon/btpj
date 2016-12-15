/**
 * Created by wajiangk on 11/21/2016.
 */
$(document).ready(function(){
    var music_list =[];
    $("#addMusic").click(function(){
        $("input[type='file']").click();
    });
    $("input[type='file']").change(function(e){
        var file=this.files[0];
        var fileReader = new FileReader();
        fileReader.onload=function(e){
            var data = e.target.result;
            var music=new Object();
            music.name=file.name;
            music.data=data;
            if(!isExist(music)){
                var url = file.urn || file.name;
                ID3.loadTags(url, function() {
                    setTags(music,url);

                    //load lyric
                    var url_1 = ctx+"/management/security/musicPlayer/musicInfo";
                    var musicInfo = new Object();
                    musicInfo.name=music.name;
                    musicInfo.title=music.title;
                    musicInfo.artist=music.artist;
                    musicInfo.album=music.album;
                    $.ajax({
                        url:url_1,
                        data:"musicInfo="+JSON.stringify(musicInfo),
                        type:'post',
                        dataType:'json',
                        success:function(data){
                            var text = data.lyric.toString().replace(/<[^>]+>/g,"\r\n")
                            music.lyric=parseLyric(text);
                        },
                        error:function(err){
                            console.log(err)
                        }
                    });

                }, {
                    tags: ["title","artist","album","picture"],
                    dataReader: ID3.FileAPIReader(file)
                });
                music_list.push(music);
            }
            showList();
        }
        fileReader.readAsDataURL(file);
    });

    var setTags=function(music,url){
        var tags = ID3.getAllTags(url);
        music.title = tags.title || "";
        music.artist=tags.artist || "";
        music.album = tags.album || "";
        var image = tags.picture;
        if (image) {
            var base64String = "";
            for (var i = 0; i < image.data.length; i++) {
                base64String += String.fromCharCode(image.data[i]);
            }
            var base64 = "data:" + image.format + ";base64," +
                window.btoa(base64String);
            music.pictureSrc=base64;
        } else {
            music.pictureSrc=ctx+"/file/avatar_1.jpg";
        }


    }
    var parseLyric = function(text) {
        var lyric = text.split('\r\n'); //先按行分割
        var lrc = new Array(); //新建一个数组存放最后结果
        for (var i = 0; i < lyric.length; i++) {
            var d = lyric[i].match(/\[\d{2}:\d{2}((\.|\:)\d{2})\]/g);  //正则匹配播放时间
            if (d != null) { //过滤掉空行等非歌词正文部分
                var t = lyric[i].split(d); //以时间为分割点分割每行歌词，数组最后一个为歌词正文
                d = d[0].replace(/\[/g,"").replace(/]/g,"");
                lrc.push([d, t[1]]);
            }
        }
        return lrc;
    }
    var lyric_p=null;
    var lyc_interval=null;
    var scroll=0;
    var deg
    var play_music =function(name,music){
        $(".lyric_content").animate({scrollTop:-16}, 1000);
        scroll=-16;
        lyric_p=music.lyric;
        var $lyrics=[];
        for(key in lyric_p){
            var $lyric=$("<span></span>");
            $lyric.attr("id",lyric_p[key][0]);
            $lyric.text(lyric_p[key][1]);
            $lyrics.push($lyric);
        }

        $(".lyric_content").html($lyrics);


        $(".jp-current-time").bind("DOMNodeInserted",function(){
            // var sh=$(".lyric_content").get(0).scrollHeight;
            $(".music_avatar").find("img").css("transform","rotate("+scroll+"deg)");
            scroll=scroll+3.5;
            $(".lyric_content").animate({scrollTop: scroll}, 1000);
        });
        name=name.substr(0,name.lastIndexOf("."));
        if(lyc_interval!=null){
            clearInterval(lyc_interval);
        }
        lyc_interval=setInterval(showCurrentLyric,1);

        $(".music_avatar").find("img").attr("src",music.pictureSrc);
        if(name.length>15){
            name=name.substring(0,15)+"..."
        }
        $("#jquery_jplayer_1").jPlayer("setMedia",{
            title: name,
            mp3: music.data
        });

    }

    function showCurrentLyric(){
        try{
            var audio=document.getElementById("jp_audio_0");
            var currentTime = timeFormat(audio.currentTime);
            if(lyric_p!=null) {
                for (key in lyric_p) {
                    if (lyric_p[key][0] == currentTime) {
                        if (lyric_p[key][0] == currentTime) {
                            if(lyric_p[key][1]!=""){
                                document.getElementById(lyric_p[key][0]).style.color = "white";
                                document.getElementById(lyric_p[key][0]).style.background = "rgba(173,173,173,0.3)";
                                var doms = document.getElementsByClassName("lyric_content")[0].childNodes;
                                for (var i = 0; i < doms.length; i++) {
                                    if (doms[i] != document.getElementById(lyric_p[key][0])) {
                                        doms[i].style.color = "black";
                                        doms[i].style.background = "none";

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch(e){
        }

    }

    function timeFormat(number) {
        var minute = parseInt(number / 60);
        var second = parseInt(number % 60);
        var msecond = (number- second-minute*60).toFixed(2)*100;
        minute = minute >= 10 ? minute : "0" + minute;
        second = second >= 10 ? second : "0" + second;
        msecond =msecond >= 10 ? msecond : "0" + msecond;
        return minute + ":" + second+"."+msecond;
    }
    //80000ms - second*1000-minute*60*1000

    var showList=function(){
        if(music_list.length!=0){
            for(key in music_list){
                if(!isShowed(music_list[key].name)){
                    var $new_music=$("<li></li>");
                    $new_music.html(music_list[key].name);
                    $new_music.click(function(){
                        play_music($new_music.html(),findMusic($new_music.html()));
                        $(".jp-stop").click();
                        $(".jp-play").click();
                    });
                    $(".music_list").find("ul").append($new_music);
                }
            }
        }
    }

    var findMusic=function(musicName){
        for(key in music_list){
            if(music_list[key].name==musicName){
                return music_list[key];
            }
        }
        return -1;
    }
    var isExist = function(music){
        for(key in music_list){
            if(music_list[key].name==music.name){
                return true;
            }
        }
        return false;
    }
    var isShowed=function(music_name){
        var result=false;
        $(".music_list").find("li").each(function(){
            if($(this).text()==music_name){
                result=true;
                return false;
            }
        });
        return result;
    }
    var options={
        circle_play:false
    };
    $("#jquery_jplayer_1").jPlayer({
        ready: function (event) {
            $(this).jPlayer("setMedia", {
                title: "Always in Silence -- Assens",
                mp3: ctx+"/styles/jPlayer-2.9.1/examples/always in silence.mp3",
            });

            //volume down
            $(".fa-volume-down").click(function(){
                var current_volume=parseInt ($(".jp-volume-bar-value").css("width").replace(/[^0-9]/g,""));
                var parent_volume=parseInt($(".jp-volume-bar").css("width").replace(/[^0-9]/g,""));
                current_volume=current_volume-10;
                $(".jp-volume-bar-value").css("width",current_volume+"px");
                $("#jquery_jplayer_1").jPlayer("option", "volume", current_volume/parent_volume);
            });
            //volume up
            $(".fa-volume-up").click(function(){
                var current_volume=parseInt ($(".jp-volume-bar-value").css("width").replace(/[^0-9]/g,""));
                var parent_volume=parseInt($(".jp-volume-bar").css("width").replace(/[^0-9]/g,""));
                current_volume=current_volume+10;
                $(".jp-volume-bar-value").css("width",current_volume+"px");
                $("#jquery_jplayer_1").jPlayer("option", "muted", false);
                $("#jquery_jplayer_1").jPlayer("option", "volume", current_volume/parent_volume);
            });

            $(".jp-play").click(function(){
                $(".lyric_content").animate({scrollTop: 0}, 1000);
                if($(this).hasClass("fa-play")){
                    // interval=setInterval(updateProgress, 50);
                    $(this).removeClass("fa-play");
                    $(this).addClass("fa-pause");
                }else{
                    clearInterval(lyc_interval);
                    $(this).removeClass("fa-pause");
                    $(this).addClass("fa-play");
                }
            });
            $(".jp-stop").click(function(){
                $(".jp-play").removeClass("fa-pause");
                $(".jp-play").addClass("fa-play");
            });

            $(".fa-play-circle").click(function(){
                if(options.circle_play){
                    options.circle_play=false
                }else{
                    options.circle_play=true;
                }
            });
        },
        loadedmetadata:function(e){
            $(".duration").text($(".jp-duration").text().replace(/-/g,""))
        },
        ended:function(){
            console.log("music end");
            if(lyc_interval!=null){
                clearInterval(lyc_interval);
            }
            $(".jp-stop").click();
            if(options.circle_play){
                $(".jp-play").click();
            }else{
                console.log("next");
            }

        },
        swfPath: ctx+"/styles/jPlayer-2.9.1/dist/jplayer",
        supplied: "m4a, oga,mp3",
        wmode: "window",
        useStateClassSkin: true,
        autoBlur: true,
        smoothPlayBar: true,
        keyEnabled: true,
        remainingDuration: true,
        toggleDuration: true
    });


});