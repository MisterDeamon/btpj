<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>编辑用户</strong></h2>
                <div class="panel-actions">
                    <a href="index.html#" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
                    <a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                    <a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <form action="<%=basePath%>/management/security/user/modify" method="post" enctype="multipart/form-data">
                    <table class="table table-striped table-bordered bootstrap-datatable ">

                        <tr >
                            <td>用户名</td><td><input type="text" name="userName" value="${user.userName}"/><input type="hidden" name="id" value="${user.id}" /><span ></span></td>
                        </tr>
                        <tr>
                            <td>证件号码</td><td><input type="text" name="idCard" value="${user.idCard}"/><span ></span></td>
                        </tr>
                        <tr>
                            <td>是否允许登陆</td>
                            <td>
                                <input type="radio" name="userStatus" alt="允许登录" value="1" style="width:50px;" <c:if test='${user.userStatus==1}'>checked="checked"</c:if> />是
                                <input type="radio" alt="禁止登录" name="userStatus" value="0" style="width:50px;" <c:if test='${user.userStatus==0}'>checked="checked"</c:if> />否
                            </td>
                        </tr>
                        <tr>
                            <td>邮箱</td><td><input type="text" name="email" value="${user.email}"/><span ></span></td>
                        </tr>
                        <tr>
                            <td>手机号码</td><td><input type="text" name="phone" value="${user.phone}"/><span ></span></td>
                        </tr>
                        <tr>
                            <td>QQ</td><td><input type="text" name="tencentNo" value="${user.tencentNo}"/><span ></span></td>
                        </tr>
                        <tr>
                            <td>头像</td><td><input type="file" name="file" id="file" /><span ></span></td>
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


