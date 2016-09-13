/**
 * Created by wajiangk on 9/7/2016.
 */

$(document).ready(function(e){


    $(".sidebar-menu a").live("click",function(event){
        var  str="";
        var ctg = $(this).children().text();
        if(ctg!="首 页"){
            if($(this).attr("href")=="javascript:;"){
                $(this).parent().find("ul").first().slideToggle("normal", function (){
                    if( $(this).parent().find("ul").first().hasClass("drop-shadow")){
                        $(this).removeClass("drop-shadow");
                    }else{
                        $(this).addClass("drop-shadow");
                    }
                });
            }else{
                event.preventDefault();
                var pCtg = $(this).parent().parent().parent().find("a").first().text();
                $.ajax({
                    type:'get',
                    dataType:'html',
                    url:this,
                    success:function(data){
                      str= "<div class='row'>"+
                            "<div class='col-lg-12'>"+
                            "<h3 class='page-header'><i class='fa fa-home'></i>"+ctg+"</h3>"+
                            " <ol class='breadcrumb'>"+
                            " <li><a href='"+ctx+"/management/index'><i class='fa fa-home'></i>Home</a></li>"+
                            "<li><i class='fa fa-home'></i>"+pCtg+"</a></li>"+
                            "<li><i class='fa fa-home'></i>"+ctg+"</a></li>"+
                            " </ol>"+
                            " </div>"+
                            "  </div>";
                        $("#main").html(str+data);
                    }
                });
                changeBg($(this).parent());
            }
        }else{
            changeBg($(this).parent());
        }

        function changeBg(node){
           $(".sidebar-menu li").each(function(index,item){
               if(node.text()!=$(item).text()){
                   $(item).removeClass("active");
               }else{
                   $(node).addClass("active");
               }
           });
        }
    });

    //validate userName
    $("body").on("blur",":input[name='userName']",function(){
        var userName = $(this).val();
        var info={"type":true,"msg":"✔"};
        var node = $(this);
        if(userName==""||userName=='undefined'){
            info={"type":false,"msg":"用户名不能为空"}
            showInfo(node,info);
        }else{
            $.ajax({
                type:'get',
                url:ctx+'/checkName?userName='+userName,
                dataType: 'json',
                scriptCharset: 'utf-8',
                success:function(data){
                    if(data.success){
                        info={"type":false,"msg":"用户名已存在"};
                    }
                    showInfo(node,info);
                }
            });
        }
    });

    //validate idCard
    $("body").on("blur",":input[name='idCard']",function(){
        var idCard = $(this).val();
        var info={"type":true,"msg":"✔"};
        var reg1= /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/;
        var reg2= /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;

        if(idCard=="")
        {
            info={"type":false,"msg":"身份证号不能为空"};
        }else{
            if(!reg1.test(idCard)&&!reg2.test(idCard)){
                info={"type":false,"msg":"身份证号格式错误"};
            }
        }
        showInfo($(this),info);
    });
    //validate password
    $("body").on("blur",":input[name='password']",function(){
        var password = $(this).val();
        var info={"type":true,"msg":"✔"};
        var reg=/^(?![A-Z]+$)(?![a-z]+$)(?!\d+$)(?![\W_]+$)\S{6,16}$/;
        if(password=="")
        {
            info={"type":false,"msg":"密码不能为空"};
        }else{
            if(!reg.test(password)){
                info={"type":false,"msg":"新密码必须由 6-16位字母、数字、特殊符号线组成"};
            }
        }
        showInfo($(this),info);
    });
    $("body").on("blur",":input[name='rePassword']",function(){
        var rePassword = $(this).val();
        var password = $(this).parent().parent().parent().find(":input[name='password']").val();
        var info={"type":true,"msg":"✔"};
        if(rePassword==""){
            info={"type":false,"msg":"请确认密码"};
        }else{
            if(password!=rePassword){
                info={"type":false,"msg":"两次密码不一致"};
            }
        }

        showInfo($(this),info);
    });

    //validate email
    $("body").on("blur",":input[name='email']",function(){
        var email = $(this).val();
        var info={"type":true,"msg":"✔"};
        var reg=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        if(email!=""){
            if(!reg.test(email)){
                info={"type":false,"msg":"邮箱地址无效"};
            }
        }else{
            info={"type":true,"msg":""};
        }

        showInfo($(this),info);
    });
    //validate qq
    $("body").on("blur",":input[name='tencentNo']",function(){
        var qq = $(this).val();
        var info={"type":true,"msg":"✔"};
        var reg=/^\d{5,11}$/;
        if(qq!=""){
            if(!reg.test(qq)){
                info={"type":false,"msg":"qq号码错误"};
            }
        }else{
            info={"type":true,"msg":""};
        }

        showInfo($(this),info);
    });
    //validate phone number
    $("body").on("blur",":input[name='phone']",function(){
        var phone = $(this).val();
        var info={"type":true,"msg":"✔"};
        var reg=/^1[3|4|5|7|8][0-9]{9}$/;

        if(phone!=""){
            if(!reg.test(phone)){
                info={"type":false,"msg":"手机号码错误"};
            }
        }else{
            info={"type":true,"msg":""};
        }

        showInfo($(this),info);
    });
    function showInfo(node,info){
        if(info.type){
            node.parent().find("span").removeAttr("class").addClass("alert-success").html(info.msg);
        }else{
            node.parent().find("span").removeAttr("class").addClass("alert-danger").html(info.msg);
        }

    }


    //validate form
    $("body").on("click",":button[type='submit']",function(e){
        e.preventDefault();
        var form = $(this).parent().parent();
        form.find("input").blur();
        var count=0;
        var info="";
        form.find("span").each(function(index,item){
            var msg = $(item).text();
            if(msg!=""&&msg!="✔"){
                count++;
                info+=" "+msg
            }
        });

        if(count!=0){
            form.parent().find(".alert").find("strong").html("共有"+count+"个错误："+info);
            form.parent().find(".alert").show("slow");
        }else {
            var url = form.attr("action");
            form.ajaxSubmit({
                url:url,
                type:'post',
                scriptCharset: 'utf-8',
                dataType:'json',
                success:function(r){
                    if(r.success){
                        form.parent().find(".alert").removeClass("alert-danger").addClass("alert-success").find("strong").html(r.msg);
                        form.parent().find(".alert").show("slow");
                        setTimeout(function(){
                            form.parent().find(".alert").hide("slow");
                        },3000);
                        setTimeout(function(){
                            $(".sidebar-menu a").each(function(){
                                if($(this).parent().hasClass("active")){
                                    $(this).click();
                                }
                            });
                        },2000);

                    }else{

                    }
                },
                error:function(e){
                   console.log(e);
                }

            });
        }
    });
    $("body").on("click",":button[type='reset']",function(e){
        e.preventDefault();
        var form = $(this).parent().parent();
        form.find("span").html("");
        form.find("input").val("");
    });
});
