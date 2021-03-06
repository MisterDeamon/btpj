<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<div class="row">
    <div class="col-lg-4 col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>角色分配</strong></h2>
                <div class="panel-actions">
                    <a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                    <a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <form action="<%=basePath%>/management/security/user/roleSet" method="post">
                    <table class="table table-striped table-bordered bootstrap-datatable ">
                        <thead>
                        <th>用户名</th>
                        <th>手机号码</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>${userRoleSet.userName}</td>
                                <td>${userRoleSet.phone}</td>
                                <input type="hidden" value="${userRoleSet.id}" name="userId" />
                            </tr>
                        </tbody>
                    </table>
                    <table class="table table-striped table-bordered bootstrap-datatable ">
                        <thead>
                            <th>序号</th>
                            <th>选择</th>
                            <th>角色名</th>
                        </thead>
                        <tbody>
                            <c:forEach var="role" items="${allRoles}" varStatus="ind">
                                <tr>
                                    <td>${ind.count}</td>

                                    <td><input type="checkbox" name="roleId" value="${role.roleId}"
                                    <c:forEach items="${userRoleSet.sroles}" var="tempRole">
                                        <c:if test="${tempRole.roleName == role.roleName }">
                                            checked="checked"
                                        </c:if>
                                    </c:forEach>
                                    ></td>
                                    <td> ${role.roleName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="panel-footer middle">
                        <button type="submit" id="subRoleSet" class="btn btn-sm btn-success"><i class="fa fa-dot-circle-o"></i> Submit</button>
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



