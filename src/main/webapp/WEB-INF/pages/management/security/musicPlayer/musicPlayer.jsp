<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<%@ include file="../../../common/listDialog.jsp" %>
<style type="text/css">
    .btn-info{
        color:rgba(231,255,255,0.8);
        background: rgba(160,237,251,0.7);
        border:none;
    }
    .btn-info:hover{
        color:rgba(231,255,255,0.9);
        background: rgba(160,237,251,0.9);

    }

    .music_box{
        margin-top: 5px;
        width:100%;
        height:400px;
        font-family: 微软雅黑;
    }
    .jp-jplayer{
        width: 0px;
        height: 0px;
    }
    .music_avatar{
        width:120px;
        height:120px;
        float: left;
    }
    .music_avatar img{
        width: 120px;
        height: 120px;
        border: 3px solid rgba(255,255,255,0.5);
        border-radius: 69px;
    }
    .music_info{
        margin-left: 122px;
        width: 396px;
        height: 120px;
        color:#333;
    }
    #music_name{
       text-align: center;
    }

    .jp-progress{
        margin-left:3px;
        width:98%;
        margin-top:10px;
        height:10px;
    }
    .progress-timer{
        margin-left:286px;
        color:#333;
        font-size: 12px;
        height: 12px;
    }
    .progress-timer div{
        float:left;
    }
    .buttonGroup{
        margin-top:15px;
        margin-left:30px;
    }

    .music_list{
        width:100%;
        height:278px;
        color:black;
    }
    .music_list ul{
        margin-left:0px;
        font-size: 12px;
        color:white;
        padding-left: 0px;
    }
    .music_list ul li{
        list-style: none;
        text-align: center;
        background:rgba(173,173,173,0.3);
    }
    .music_list ul li:hover{
        background: rgba(160,237,251,0.5);
    }
    .jp-audio{
        height: 100%;
        width:517px;
        font-size: 16px;
        line-height: 1.6;
        color: #fff;
        float:left;
    }


    .jp-type-single .jp-play {
    }

    .jp-type-single .jp-controls button {
        width: 99px;
    }

    .jp-state-playing .jp-type-single .jp-play {
        border:1px solid red;
    }

    .jp-controls button {
        display: block;
        float: left;
        overflow: hidden;
        height: 34px;
        padding: 0;
        cursor: pointer;
    }
    .jp-seek-bar{
        width:100%;
        height:10px;
        background: rgba(63,219,248,0.6);
    }
    .jp-play-bar{
        height:10px;
        background:none;
        border-right:4px solid whitesmoke;
        border-radius: 2px;
    }
    .jp-play-bar img{
        height:10px;
        width:100%;
    }
    .blur {
        -webkit-filter: blur(2px); /* Chrome, Opera */
        -moz-filter: blur(2px);
        -ms-filter: blur(2px);
        filter: blur(2px);
/*
        filter: progid:DXImageTransform.Microsoft.Blur(PixelRadius=2, MakeShadow=false);*/
    }

    .lyric_area{
        color: black;
        border-left: 2px solid rgba(190,247,253,0.5);
        width: 769px;
        height: 400px;
        position: absolute;
        margin-left: 516px;
        overflow: scroll;
        text-align:center;
        overflow-y:visible;
        overflow-x:visible;
    }


    .lyric_parent{
        overflow-x: hidden;

    }
    .lyric_content{
        margin-top:60px;
        height:330px;
        width:110%;
        overflow-y: scroll;
    }
    .lyric_content span{
        height:18px;
        line-height: 18px;
        display: block;
        overflow-y: visible;
    }
</style>

<%--<link href="<%=basePath%>/styles/jPlayer-2.9.1/skin/pink.flag/jplayer.pink.flag.css" rel="stylesheet" type="text/css" />--%>

<script src="<%=protonPath%>/assets/js/jquery-2.1.1.min.js"></script>
<script src="<%=protonPath%>/assets/js/id3-minimized.js"></script>
<script src="<%=protonPath%>/assets/js/musicPlayer.js"></script>
<script type="text/javascript" src="<%=basePath%>/styles/jPlayer-2.9.1/dist/jplayer/jquery.jplayer.min.js"></script>

<div class="row" id="info">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-music red"></i><span class="break"></span><strong>MusicPlayer</strong></h2>
                <div class="panel-actions">
                    <a href="<%=basePath%>/management/security/menu/list" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="btn-action">
                    <input type="file" style="display: none;"/>
                    <a class="create btn btn-success "  href="javascript:void(0)" id="addMusic" title="添加歌曲">
                        <i class="fa fa-search-plus "></i>
                    </a>
                    <a class="btn btn-info" href="<%=basePath%>/management/security/menu/modify" title="修改" id="modify"> <i class="fa fa-edit "></i></a>
                    </a>
                    <a class="btn btn-info" href="<%=basePath%>/management/security/menu/modify" title="换肤" id="modiasdasfy"> <i class="fa fa-edit "></i></a>
                    </a>
                </div>
                <div class="music_box" style="background-image: url('<%=basePath%>/file/music_bg.png');background-repeat: no-repeat;background-size:cover">

                    <%--put music here--%>
                    <div id="jquery_jplayer_1" class="jp-jplayer"></div>

                    <div id="jp_container_1" class="jp-audio" role="application" aria-label="media player">
                        <div class="music_avatar"><img src="<%=basePath%>/file/upload/headpic/Jack1001.jpg"></div>
                        <div class="music_info">
                            <div id="music_name" class="jp-title">Singer_name:test_music_name</div>
                            <div class="jp-progress">
                                <%--<div class="progress thin progress-striped">
                                    <div class="progress-bar progress-bar-info"> </div>
                                </div>--%>
                               <div class="jp-seek-bar" style="width: 100%;">
                                    <div class="jp-play-bar" style="width: 0">
                                        <img src="<%=basePath%>/file/music_bg_bar.png" >
                                    </div>
                                </div>
                            </div>
                            <div class="progress-timer">
                                <div class="jp-current-time"></div>
                                <input type="hidden" id="current-time">
                                <div>/</div>
                                <div class="jp-duration" style="display: none"></div>
                                <div class="duration" style="clear: right;"></div>
                            </div>
                            <div class="buttonGroup">
                                <button class="btn btn-info fa fa-step-backward " role="button" ></button>
                                <button class="btn btn-info fa fa-play  jp-play " role="button" ></button>
                                <button class="btn btn-info fa  fa-stop jp-stop " role="button" ></button>
                                <button class="btn btn-info fa   fa-play-circle jp-repeat " role="button" ></button>
                                <button class="btn btn-info fa  fa-step-forward " role="button" ></button>
                                <button class="btn btn-info fa  fa-volume-down" role="button" ></button>
                                <button class="btn btn-info fa  fa-volume-off jp-mute" role="button" ></button>
                                <button class="btn btn-info fa  fa-volume-up" role="button" ></button>
                                <div class="jp-volume-bar"  style="display: none;">
                                    <div class="jp-volume-bar-value" style="display: none;"></div>
                                </div>

                               <%-- <div class="jp-volume-slider ui-slider ui-slider-horizontal ui-widget ui-widget-content ui-corner-all" aria-disabled="false">
                                    <div class="ui-slider-range ui-widget-header ui-corner-all ui-slider-range-min" style="width: 60%;"></div>
                                    <a --class="ui-slider-handle ui-state-default ui-corner-all" href="#" style="left: 60%;"></a>
                                </div>--%>
                            </div>

                        </div>
                        <%--music list--%>
                        <div class="music_list">
                            <ul>
                            </ul>
                        </div>
                    </div>
                    <div class="lyric_area">
                        <div class="lyric_parent">
                            <div class="lyric_content">

                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div><!--/col-->
</div><!--/row-->


