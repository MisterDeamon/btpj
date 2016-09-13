/**
 * Created by wajiangk on 8/25/2016.
 */
function currentHHmm(){
    var date = new Date();
    var hour = date.getHours()>9?date.getHours().toString():('0' + date.getHours());
    var minutes = date.getMinutes()>9?date.getMinutes().toString():('0' + date.getMinutes());
    var seconds = date.getSeconds()>9?date.getSeconds().toString():('0' + date.getSeconds());
    var currentTime =hour+":"+minutes+":"+seconds;
    $("#time").html(currentTime);
}

function openDir(str){
    var html="";
    var endStr = str.substring(str.lastIndexOf("/")+1);
    if(isFile(endStr)){
        html+="<div class='apps'><div class='app'><img src='"+ctx+"/styles/images/desktop/flag.jpg'/></div></div>";
    }else{
        html+="<div class='pic'><img src='"+ctx+"/styles/images/desktop/flag.jpg'/></div>";
    }

    $("#filePanner").html(html);
    $("#filePanner").dialog({
        closeOnEscape: false,
        draggable:true,
        resizable: true,
        height: 500,
        width:950,
    });

}
function isFile(dir){
    for(var i=0;i<dir.length;i++){
        if(dir[i]=="."){
            return true;
        }
    }
    return false;
}