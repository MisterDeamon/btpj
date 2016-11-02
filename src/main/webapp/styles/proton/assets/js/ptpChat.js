/**
 * Created by wajiangk on 10/21/2016.
 */


$("#editor").emoji({
    button: "#btn",
    showTab: false,
    animation: 'slide',
    icons: [{
        name: "QQ表情",
        path: ctx+"/styles/proton/assets/jQuery.emoji/img/qq/",
        maxNum: 91,
        excludeNums: [41, 45, 54],
        file: ".gif",
    }]
});

//load chat history
$("input[name='chatWith']").each(function(){
    var chatWith=$(this).val();
    var userName=$("span[name='userName']").text();
    var cookieName = userName+"chatHistoryWith"+chatWith;
    var cookieHis=$.cookie(cookieName);
    if(cookieHis!=undefined){
        cookieHis= (eval($.cookie(cookieName).replace(/"\(/,"(").replace(/\)"/,")").replace(/\n\n/g,"").replace(/"\\'/g,"'").replace(/\\'/g,"'")));
        var hisMsgContent=cookieHis.chatHisContent;
        $(this).parent().parent().find(".hisContent").append(hisMsgContent);
    }
});

$("button[id*='sendMsgTo']").each(function(){
    $(this).bind("click",function () {
        var msg = $(this).parent().parent().find("#editor");
        if(msg.html()!=''){
            if (window.websocket != null && window.websocket!='undefined') {
                var friendName = $(this).parent().parent().parent().find("input[name='chatWith']").val();
                var from = $("span[name='userName']").text();
                var msgContent = "<div class='titleHeader'>"+from+ "("+new Date().format("yyyy-MM-dd HH:mm:ss")+")：" + "<div><span>"+$(this).parent().parent().find("#editor").html()+"</span>";
                $(this).parent().parent().find(".hisContent").append(msgContent);

                //put msg into cookie
                var cookieName = $("span[name='userName']").text()+"chatHistoryWith"+friendName;
                var tempF = friendName;
                var tempMsg = emojiPareToText(msg).replace(/"/g,"\\\"").replace( /<br>/g,"").replace( /'/g,"\\\'").replace( /\n\n/g,"");
                //var tempMsg = emojiPareToText(msg).replace(/"/g,"\\\"").replace( /<br>/g,"").replace( /'/g,"\\\'").replace( /\n\n/g,"").replace(/<[^>]+>/g,"");
                var tempMs = msgContent;
                if($.cookie(cookieName)==undefined){
                    var hisJsonString ='{"chatWith":"'+tempF+'","chatHisContent":"'+tempMs.replace(/"/g,"\\\"").replace(/'/g,"\\\'").replace(/"\(/,"(").replace(/\)"/,")").replace(/\n\n/g,"")+'"}';
                    $.cookie(cookieName,"("+hisJsonString+")","{ expires: 7, path: '/' }");
                }else{
                    var hisJson = (eval($.cookie(cookieName).replace(/"\(/,"(").replace(/\)"/,")").replace(/\n\n/g,"")));
                    var hisJsonContent=hisJson.chatHisContent+tempMs;
                    hisJsonString = '{"chatWith":"'+tempF+'","chatHisContent":"'+hisJsonContent.replace(/"/g,"\\\"").replace(/'/g,"\\\'").replace(/"\(/,"(").replace(/\)"/,")").replace(/\n\n/g,"")+'"}';
                    $.cookie(cookieName,"("+hisJsonString+")","{ expires: 7, path: '/' }");
                }


                var message =  "{'from':'"+from+"','target':'"+friendName+"','msgContent':'"+tempMsg+"'}";
                $(this).parent().parent().find("#editor").html("");
                $(this).parent().parent().find(".hisContent").scrollTop( $(this).parent().parent().find(".hisContent")[0].scrollHeight);
                window.websocket.send(message);
            } else {
                alert('websocket异常.');
            }
        }
    })
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
    return node.html().toString().replace(/\\/g,"\\");
}
$("#editor").emojiParse({
    icons: [{
        path: ctx+"/styles/proton/assets/jQuery.emoji/img/tieba/",
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
        path: ctx+"/styles/proton/assets/jQuery.emoji/img/qq/",
        file: ".gif",
        placeholder: "#qq_{alias}#"
    }]
});