<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<script src="<%=protonPath%>/assets/js/textReader.js"></script>
<style type="text/css">
    .head{
        margin-left:16px;
        width:80%;
        line-height: 40px;
        height:40px;
    }
    .head button{
        display: block;
        float: left;
    }
    .head span{
        display: block;
        height:35px;
        line-height: 35px;
    }
    .m_top{
        margin-top: 30px;
    }
    .chapter{
        position: relative;
        width:150px;
        height:100%;
        margin-left:-10px;
        margin-top:-10px;
        margin-bottom:-10px;
        float:left;
        background: #181d20;
        color:#848788;
        border:1px solid  #181d20;
    }
    .chapter span{
        width:100%;
        height:25px;
        text-align: center;
        display: block;
    }
    .categoryTog{
        position: absolute;
        width:10px;
        margin-left:89%;
    }

    .bookContent{
        margin-left:15px;
        height:482px;
        overflow: scroll;
    }
    .toolbar{
        position: absolute;
        width: 86%;
        height: 36px;
        margin-left: 140px;
        margin-top: -26px;
        background: #d95043;
    }
    #para{
        padding-left:10px;
        padding-right:10px;
        line-height:28px;
        background: palegoldenrod;
    }
</style>
<div class="row" id="info">
    <div class="head">
        <input type="file" id="selectBook" style="display: none">
        <button id="selectBookBtn" class="btn btn-success">选择文件</button><span id="fileInfo"></span>
        <div class="progress">
            <div class="progress-bar" role="progressbar" id="progress" aria-valuenow="5" aria-valuemin="0" aria-valuemax="100" style="width: 5%;">
                0
            </div>
        </div>
    </div>

    <div class="col-lg-12 m_top">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong id="bookName"></strong></h2>
                <div class="panel-actions">
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="view">
                    <div class="chapter">
                        <div class="fa fa-chevron-left categoryTog"></div>
                    </div>
                    <div class="bookContent">
                            <p id="para"></p>
                    </div>
                    <div class="toolbar"><span id="nowChap"></span></div>
                </div>
            </div>
        </div>
    </div>



</div><!--/row-->
