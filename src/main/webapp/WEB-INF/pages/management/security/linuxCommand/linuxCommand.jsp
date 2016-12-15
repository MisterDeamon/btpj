<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<%@ include file="../../../common/listDialog.jsp" %>
<style type="text/css">
    .commandBox{
        border:1px solid brown;
        width:100%;
        height:500px;
        background: black;
        color:whitesmoke;
        padding:5px;
        margin-top:10px;
        overflow-y: scroll;
    }
    .commandBox input{
        background: none;
        width:100%;
        outline:none;
    }
    .commandBox input,.commandBox input:focus,.commandBox input:visited,.commandBox input:active{
        border:none;
    }
    .commandBox div:focus{
        border:none;
    }
    .excuted{
        width:100%;
    }
    .excuted span{
        display: block;
    }
</style>
<div class="row" id="info">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>Command</strong></h2>
                <div class="panel-actions">
                    <a href="<%=basePath%>/management/security/role/list" class="btn-setting"><i
                            class="fa fa-rotate-right"></i></a>
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="btn-action">
                    <a class="create btn btn-success " href="<%=basePath%>/management/security/role/create" title="新增"
                       id="create" data-toggle="modal">
                        <i class="fa fa-search-plus "></i>
                    </a>

                    <a class="btn btn-info" href="<%=basePath%>/management/security/role/modify" title="修改" id="modify">
                        <i class="fa fa-edit "></i></a>
                    <a class="btn btn-danger" href="<%=basePath%>/management/security/role/delete" id="remove"
                       title="删除">
                        <i class="fa fa-trash-o "></i>
                    </a>
                    <a class="btn btn-warning" href="<%=basePath%>/management/security/role/rightSet" id="rightSet"
                       title="分配权限">
                        <i class="fa fa-users "></i>
                    </a>
                </div>
                <div class="commandBox">
                    <div class="excutedBox">

                    </div>
                    <input type="text" id="commandContent"/>
                </div>
            </div>
        </div>
    </div><!--/col-->
</div>
<!--/row-->

<script>
    $(".commandBox").keypress(function(e){
        if(e.keyCode == '13'){
            e.preventDefault();
            var command = $("#commandContent").val();
            $.ajax({
                url:ctx+"/management/security/linuxCommand/excuteCmd?cmd="+command,
                type:"get",
                data:"json",
                success:function(data){
                    var $excutedDiv=$("<div class='excuted'></div>");
                    var $excutedCmd=$("<span></span>");
                    $excutedCmd.html("[root@localhost /]# "+command);
                    var $result=$('<span></span>');
                    $result.html(data.result);

                    $excutedDiv.append($excutedCmd);
                    $excutedDiv.append($result);

                    $(".excutedBox").append($excutedDiv);
                    $("#commandContent").val("");

                }

            });

        }
    });

</script>
