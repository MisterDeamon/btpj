/**
 * Created by wajiangk on 10/27/2016.
 */
    //bind the button or href on function makeFeedbackForm
$("#feedbackAccess").bind("click",function() {
    makeFeedbackForm({"form_url": "#aa"});
});
 var makeFeedbackForm=function(option){
    //default option
    var defaultOption = {
        "font-family":"Arial",
        mask:true,
        style:{
            "width":830,
            "height":490,
            "left":"28%",
            "top":"13%",
            "position": "absolute",
            "background-color":"white",
            "color":"black",
            "z-index":1003,
            "button_style":{
                "font-family":"Arial",
                "width":100,
                "height":45,
                "background":"#00B389",
                "border":"none",
                "color":"white",
                "margin-left":5,
                "mouse_hover":"#00B368"
            }
        },
        mask_style:{
            "position": "absolute",
            "top": "0px",
            "left": "0px",
            "filter": "alpha(opacity=60)",
            "background-color": "#777",
            "z-index": 1002,
            "opacity":0.5,
            "moz-opacity":0.5
        },
        form_url:"#"
    }
    option = $.extend({},defaultOption,option);
    var dialogForm = function(){
        if(option.mask){
            var $mask = $("<div id='mask'></div>");
            //$mask.css(option.mask_style);
            $mask.css(option.mask_style);
            $mask.css({
                "height":"100%",
                "width":"100%",
                "display":"block"
            });
            $("body").append($mask);
        }
    }

     var $feedbackForm=$("<div id='feedback'></div>");
     $feedbackForm.css(option.style);

     //start  setting feedback form elements
     var $form = $("<form method='post'></form>");
     var formComponents=[];
     $form.css({
         "width":"95%",
         "height":"85%",
         "margin":"auto",
         "margin-top":"15px",
         "border-top":"4px solid #00B389"

     });
     $form.attr("action",option.form_url);

     //set userName input
     var $userNameInput=$("<input name='userName' type='hidden'/>");
     //TODO
     var getUserName = function(){return "userName";}
     $userNameInput.val(getUserName());
     formComponents.push($userNameInput);

     //set userEmail input
     var $userEamilInput=$("<input name='userEmail' type='hidden' />");
     //TODO
     var getUserEamil = function(){return "userEmail"};
     $userEamilInput.val(getUserEamil());
     formComponents.push($userEamilInput);

     //set application name
     var $applicationNameInput=$("<input name='applicationName' type='hidden' />");
     //TODO
     var getAllicationName = function(){return "ApplicationName"};
     $applicationNameInput.val(getAllicationName());
     formComponents.push($applicationNameInput);

     var $title=$("<span></span>");
     $title.css({
         "font-family":"Arial",
         "font-size":18,
         "font-weight":"bold",
         "color":"black",
         "margin-left":"5px"
     });
     $title.text("Feedback for "+getAllicationName());
     formComponents.push($title);

     var $fieldsDiv=$("<div><ul></ul></div>");
     $fieldsDiv.css({
         "margin-top":30,
         "margin-left":15,
         "list-style":"none",
         "width":"100%"
     });

     var $fieldOne=$("<li><span>fieldOne</span><input name='fieldOne' errorMsg='FieldOne is required'/></li>");
     $fieldsDiv.append($fieldOne);

     var $fieldTwo=$("<li><span>fieldTwo</span><input name='fieldTwo' errorMsg='FieldTwo is required'/></li>");
     $fieldsDiv.append($fieldTwo);

     var $fieldThree=$("<li><span>fieldThree</span><input name='fieldThree' errorMsg='FieldThree is required'/></li>");
     $fieldsDiv.append($fieldThree);

     $fieldsDiv.find("li").css({
        "margin-top":18,
         "width":"100%"
     });
     $fieldsDiv.find("span").css({
         "width":"9%",
         "margin-left":8,
         "float":"left",
         "display":"block"
     });
     $fieldsDiv.find("input").css({
         "width":"85%",
         "margin-left":8
     });
     formComponents.push($fieldsDiv);

     var $feedbackContent=$("<div><span>feedback</span><textarea name='feedbackContent' errorMsg='FeedBackContent is required'>Please input your description...</textarea></div>");
     $feedbackContent.css({
         "margin-top":15,
         "width":"100%",
         "margin-left":15
     });
     $feedbackContent.find("span").css({
         "width":"9%",
         "margin-left":8,
         "float":"left",
         "display":"block"
     });
     $feedbackContent.find("textarea").css({
         "width":"85%",
         "height":200,
         "margin-left":8
     });
     formComponents.push($feedbackContent);

     $form.append(formComponents);
     $feedbackForm.append($form);

     //set buttons
     var $buttons  = $("<div></div>");
     $buttons.css({
         "text-align":"center"
     });
     var $buttonSubmit  = $("<button id='submitFeedBack'>Submit</button>");
     $buttonSubmit.css(option.style.button_style);
     var $buttonCancel  = $("<button id='cancelFeedBack'>Cancel</button>");
     $buttonCancel.css(option.style.button_style);
     $buttons.append($buttonSubmit);
     $buttons.append($buttonCancel);
     $feedbackForm.append($buttons);
     $buttonCancel.click(function(){
         $("#mask").replaceWith(null);
         $("#feedback").replaceWith(null);
     });

     dialogForm();
     $("body").append($feedbackForm);

     //set button style while hovering
     $("button").each(function(){
         $(this).hover(function(){
             btHover($(this));
         });
         $(this).mouseout(function(){
             btMouseout($(this));
         });
     });

     var btHover=function(node){
         node.css("background",option.style.button_style.mouse_hover);
     }
     var btMouseout=function(node){
         node.css("background", option.style.button_style.background);
     }
     var errors=[];
     $buttonSubmit.click(function(){
         $("li input,div textarea").blur();
         if(errors.length==0){
             //TODO submit the form

             $("#feedback").animate({"opacity":"0"},1000,function(){
                 $("#feedback").replaceWith(null);
             });
             var $response = $("<div></div>");
             $response.css({
                 "top":"26%",
                 "left":option.style.left,
                 "width":option.style.width,
                 "height":219,
                 "position":"absolute",
                 "opacity":0,
                 "background":"white",
                 "z-index":"10003"
             });
             var $responseContent="<div></div>";
             $response.append($responseContent);
             var $r_title=$("<span></span>");
             $r_title.css({
                 "font-family":"Arial",
                 "font-size":18,
                 "font-weight":"bold",
                 "color":"black",
                 "margin-left":"5px"
             });
             $r_title.text("Feedback for "+getAllicationName());
             $response.find("div").append($r_title);
             $response.find("div").css({
                 "width":"98%",
                 "height":"50%",
                 "border-top":"4px solid #00B389",
                 "margin":"auto",
                 "margin-top":"15px"
             });
             var $content=$("<p>Thanks for your feedback! We'll reply to you with the E-mail as soon as possibly!</p>")
             $content.css({
                 "width":"90%",
                 "margin-left": "3%",
                 "margin-top": "2%",
                 "font-size": 17
             });
             $response.find("div").append($content);
             var $r_buttonCancel = $("<button>Cancel</button>");
             $r_buttonCancel.css({
                 "margin-left":"39%",
                 "font-family":"Arial",
                 "width":100,
                 "height":45,
                 "background":"#00B389",
                 "border":"none",
                 "color":"white"
                 //"mouse_hover":"#00B368"
             });
             $response.append($r_buttonCancel);

             $r_buttonCancel.hover(function(){
                 btHover($r_buttonCancel);
             });
             $r_buttonCancel.mouseout(function(){
                 btMouseout($r_buttonCancel);
             });
             $r_buttonCancel.click(function(){
                 $response.replaceWith(null);
                 $("#mask").replaceWith(null);
             });

             $("body").append($response);
             $response.animate({"opacity":1},1500);

         }else{
             errors=[];
         }
     });

     //validate form elements
     $("li input,div textarea").each(function(){
         $(this).bind("blur",function(){
             if($(this).val()==""||$(this).val()=="Please input your description..."){
                var $errorMsg=$("<label></label>");
                 $errorMsg.css({
                     "color":"red",
                     "position":"absolute",
                     "margin-top":"-18px",
                     "margin-left":"-250px",
                     "width":"250px",
                     "text-align":"right"
                 });
                 errors.push($(this).attr("errorMsg"));
                 $errorMsg.html($(this).attr("errorMsg"));
                 window.setTimeout(function(){
                     $errorMsg.replaceWith(null)
                 },1500);
                 $(this).parent().append($errorMsg);
             }
         });
     });

     $("div textarea").blur(function(){
         if($(this).val()==""){
             $(this).val("Please input your description...");
         }
     });
     $("div textarea").focus(function(){
         if($(this).val()=="Please input your description..."){
             $(this).val("");
         }
     });


}
