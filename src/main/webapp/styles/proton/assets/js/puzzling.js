/**
 * Created by wajiangk on 11/15/2016.
 */
$.fn.makePuzzling = function(option){
    var defaultOption={
        width: 3.5,
        height: 3.5,
        style:{
            background_color:"white",
            padding: 18,
            block_size: 100,
            block_style:{
                "font-family": "微软雅黑",
                "font-weight": "bold",
                "text-align": "center"
            },
            canvas_style:{
                float:"left"
            }
        },
        animateSpeed: 200
    }
    option = $.extend({}, defaultOption, option);

    if(this.length > 1) throw "一次只能开始一个游戏";
    if(this.length == 0) throw "未找到游戏容器";
    var $this = $(this[0]);
    $this.css({
        "background-color": option.style.background_color,
        "width":option.width*120,
        "height":option.height*130,
        "border":"1px solid blue",
        "position": "relative",
        "-webkit-user-select": "none"

    });
    var buildImgInput = function(){
        var $imgInput = $("<input type='file' id='fileSelect' />");
        $imgInput.css("display","none");
        /*$imgInput.click(function(){
            var files=$imgInput.files;
            if (files.length) {
                var file = files[0];
                var reader = new FileReader();
                if (/text\/\w+/.test(file.type)) {
                    reader.onload = function() {
                        $('<pre>' + this.result + '</pre>').appendTo('body');
                    }
                    reader.readAsText(file);
                } else if(/image\/\w+/.test(file.type)) {
                    reader.onload = function() {
                        $('<img src="' + this.result + '"/>').appendTo('body');
                    }
                    reader.readAsDataURL(file);
                }
            }
        });*/
        var $buttonDiv=$("<div></div>");
        $buttonDiv.css({
            "width":'100%',
            "height":"30px",
            "display":'inline',
            "position":"absolute",
            "margin-left":"1000px"
        });
        var $imgBtn = $("<button id='fileSelectBtn'>选择图片</button>");
        $imgBtn.css({
            "width":"80px",
            "height":"30px"
        });
        var $fileInfo=$("<span name='fileInfo'></span>");
        $fileInfo.css('margin-left',"5px");
        var $img1=$("<canvas id='canvas1'></canvas>");
        var $img2=$("<canvas id='canvas2'></canvas>");
        var $img3=$("<canvas id='canvas3'></canvas>");
        var $img4=$("<canvas id='canvas4'></canvas>");
        var $img5=$("<canvas id='canvas5'></canvas>");
        var $img6=$("<canvas id='canvas6'></canvas>");
        var $img7=$("<canvas id='canvas7'></canvas>");
        var $img8=$("<canvas id='canvas8'></canvas>");
        var $img9=$("<canvas id='canvas9'></canvas>");

        $img1.css(option.style.canvas_style);
        $img2.css(option.style.canvas_style);
        $img3.css(option.style.canvas_style);
        $img4.css(option.style.canvas_style);
        $img5.css(option.style.canvas_style);
        $img6.css(option.style.canvas_style);
        $img7.css(option.style.canvas_style);
        $img8.css(option.style.canvas_style);
        $img9.css(option.style.canvas_style);

        $this.append($img1);
        $this.append($img2);
        $this.append($img3);
        $this.append($img4);
        $this.append($img5);
        $this.append($img6);
        $this.append($img7);
        $this.append($img8);
        $this.append($img9);

        $buttonDiv.append($imgBtn);

        var getRatio=function(x,y){
            var xDistance=x-option.width*120;
            var yDistance=y-option.height*130;
            var ratio=0;
            if(xDistance<=0||yDistance<=0){
                ratio = 1;
            }else if(xDistance<=50||yDistance<=50){
                ratio= 0.9;
            }else if(xDistance<=100||yDistance<=100){
                ratio= 0.8;
            }else if(xDistance<=200||yDistance<=200){
                ratio= 0.7;
            }else if(xDistance<=300||yDistance<=300){
                ratio= 0.6;
            }
            else{
                ratio=0.3;
            }
            console.log(ratio);
            return ratio;
        }
        $imgBtn.click(function(){

            $("#fileSelect").click();
            $("#fileSelect").change(function(){
                var file = this.files[0];
                var filename = file.name;
                var reader = new FileReader();
                reader.onload = function(e) {

                    var data = e.target.result;
                    var img = new Image();//构造JS的Image对象
                    img.src = data;//将本地图片赋给image对象

                    var ratio = getRatio(img.width,img.height)

                    var canvas1=document.getElementById("canvas1");
                    var context1=canvas1.getContext("2d");

                    var canvas2=document.getElementById("canvas2");
                    var context2=canvas2.getContext("2d");

                    var canvas3=document.getElementById("canvas3");
                    var context3=canvas3.getContext("2d");

                    var canvas4=document.getElementById("canvas4");
                    var context4=canvas4.getContext("2d");

                    var canvas5=document.getElementById("canvas5");
                    var context5=canvas5.getContext("2d");

                    var canvas6=document.getElementById("canvas6");
                    var context6=canvas6.getContext("2d");

                    var canvas7=document.getElementById("canvas7");
                    var context7=canvas7.getContext("2d");

                    var canvas8=document.getElementById("canvas8");
                    var context8=canvas8.getContext("2d");

                    var canvas9=document.getElementById("canvas9");
                    var context9=canvas9.getContext("2d");

                    img.onload=function(){
                        $fileInfo.text(filename);
                        canvas1.setAttribute("width", 120+"px");
                        canvas1.setAttribute("height", 120+"px");
                        canvas2.setAttribute("width", 120+"px");
                        canvas2.setAttribute("height", 120+"px");
                        canvas3.setAttribute("width", 120+"px");
                        canvas3.setAttribute("height", 120+"px");
                        canvas4.setAttribute("width", 120+"px");
                        canvas4.setAttribute("height", 120+"px");
                        canvas5.setAttribute("width", 120+"px");
                        canvas5.setAttribute("height", 120+"px");
                        canvas6.setAttribute("width", 120+"px");
                        canvas6.setAttribute("height", 120+"px");
                        canvas7.setAttribute("width", 120+"px");
                        canvas7.setAttribute("height", 120+"px");
                        canvas8.setAttribute("width", 120+"px");
                        canvas8.setAttribute("height", 120+"px");
                        canvas9.setAttribute("width", 120+"px");
                        canvas9.setAttribute("height", 120+"px");

                        // context.drawImage(img,0,0,120,120);
                        context1.drawImage(img,0,0,              img.width*ratio,img.height*ratio,  0,0,120,120);
                        context2.drawImage(img,img.width/3,0,    img.width*ratio,img.height*ratio,1,0,120,120);
                        context3.drawImage(img,img.width/3*2,0,  img.width*ratio,img.height*ratio,2,0,120,120);

                        context4.drawImage(img,0,img.height/3,            img.width*ratio,img.height*ratio,  0,1,120,120);
                        context5.drawImage(img,img.width/3,img.height/3,  img.width*ratio,img.height*ratio,1,1,120,120);
                        context6.drawImage(img,img.width/3*2,img.height/3,img.width*ratio,img.height*ratio,2,1,120,120);

                        context7.drawImage(img,0,img.height/3*2,            img.width*ratio,img.height*ratio   ,  0,2,120,120);
                        context8.drawImage(img,img.width/3,img.height/3*2, img.width*ratio,img.height*ratio,1,2,120,120);
                        context9.drawImage(img,img.width/3*2,img.height/3*2,img.width*ratio,img.height*ratio,2,2,120,120);
                        // context3.drawImage(img,0,0,img.width*ratio,img.height*ratio,0,0,120,120);
                        // context4.drawImage(img,0,0,img.width*ratio,img.height*ratio,0,0,120,120);
                        // context5.drawImage(img,0,0,img.width*ratio,img.height*ratio,0,0,120,120);
                        // context6.drawImage(img,1,1,img.width*ratio,img.height*ratio,0,0,120,120);

                    }

                    // $img.attr("src",data);
                    $this.append($fileInfo);
                }
                reader.readAsDataURL(file);
            });
        });
        $this.append($imgInput);
        $this.append($buttonDiv);
    }
    buildImgInput();
   /* var buildBackground = function(){
        var $bgDom = $("<div></div>");

        $bgDom.css({
            "width":option.width*10+"px",
            "height":option.height*10+"px",
            "background":option.style.background_color,
            "border":"1px solid blue"
        });
        $this.append($bgDom);
    }*/
    // buildBackground();

}