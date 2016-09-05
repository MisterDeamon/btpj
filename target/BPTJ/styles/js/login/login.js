function currentTime(){

    var date = new Date();
    var Week = ['日','一','二','三','四','五','六'];
    var  year = date.getFullYear();
    var month =(date.getMonth()+1)>9?(date.getMonth()+1).toString():('0' + (date.getMonth()+1));
    var day = date.getDate()>9?date.getDate().toString():('0' + date.getDate());

    var hour = date.getHours()>9?date.getHours().toString():('0' + date.getHours());
    var minutes = date.getMinutes()>9?date.getMinutes().toString():('0' + date.getMinutes());
    var seconds = date.getSeconds()>9?date.getSeconds().toString():('0' + date.getSeconds());
    var week = Week[date.getDay()];

    var currentTime = year+"年"+month+"月"+day+"日  周"+week+"  "+hour+":"+minutes+":"+seconds;
    $("#currentTime").html(currentTime);
}




function info(msg){
    if(msg!=null&&msg!='undefined'&&msg!=''){
        $("#info").animate({
            opacity: "show"
        }, "slow");
        $("#info").html(msg);
    }
}

function closeInfo(){
    $("input[name='userName']").bind('focus',function(){
        $("#info").animate({
            opacity: "hide"
        }, "slow");
    });
}


function checkName(){

    $("input[name='userName']").bind('blur',function(){
        var userName = $(this).val();
        var url=ctx + '/checkName?userName='+userName;
        if(userName!=null&&userName!=''){
            $.ajax({
                url: url,
                dataType: 'json',
                type: 'get',
                scriptCharset: 'utf-8',
                success: function (r) {
                    if (r.success) {
                        if(r.headpicPath!=null&& r.headpicPath!=''){
                            $("#headPic").attr('src', ctx+"/file/upload/headpic/"+r.headpicPath);
                        }
                    }else{
                        info(r.msg);
                    }
                },
                error:function(msg){
                    alert("网络异常！");
                }
            });
        }
    });


}