<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<div class="row">
    <div class="col-lg-4 col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>编辑权限</strong></h2>
                <div class="panel-actions">
                    <a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                    <a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <form action="<%=basePath%>/management/security/right/modify" method="post">
                    <table class="table table-striped table-bordered bootstrap-datatable ">

                        <tr >
                            <td>权限名称</td><td><input type="text" name="rightName" value="${right.rightName}"/><input type="hidden" name="rightId" value="${right.rightId}" /><span ></span></td>
                        </tr>
                        <tr >
                            <td>授权标识</td><td><input type="text" name="rightSign" value="${right.rightSign}"/>
                        </tr>
                    </table>
                    <div class="panel-footer middle">
                        <button type="submit" class="btn btn-sm btn-success"><i class="fa fa-dot-circle-o"></i> Submit</button>
                        <button type="reset" class="btn btn-sm btn-danger"><i class="fa fa-ban"></i> Reset</button>
                    </div>
                </form>

                <div class="alert alert-danger" style="display: none">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <strong></strong>
                </div>
            </div>
        </div>
    </div><!--/col-->

</div><!--/row-->



