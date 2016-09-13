/**
 * Created by wajiangk on 9/8/2016.
 */
$(document).ready(function(e){
    //$("#checkbox").live("click",function(){
    //    $("input[name='checkbox']").attr('checked',this.checked);
    //});

    $("body").on("click","#checkbox",function(){
        $("input[name='checkbox']").attr('checked',this.checked);
    });

    $("body").on("click","#create",function(event) {
        event.preventDefault();
        $.ajax({
            url: this,
            dataType: 'html',
            type: 'get',
            success: function (data) {
                $("#main").append(data);
            }
        });
    });

});