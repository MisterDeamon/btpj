<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<%@ include file="../../../common/listDialog.jsp" %>
<div class="row" id="info">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>角色信息</strong></h2>
                <div class="panel-actions">
                    <a href="<%=basePath%>/management/security/role/list" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="btn-action">
                    <shiro:hasPermission name="Role:create">
                        <a class="create btn btn-success "  href="<%=basePath%>/management/security/role/create" title="新增" id="create" data-toggle="modal">
                        <i class="fa fa-search-plus "></i>
                    </a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="Role:modify">
                        <a class="btn btn-info" href="<%=basePath%>/management/security/role/modify" title="修改" id="modify"> <i class="fa fa-edit "></i></a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="Role:delete">
                        <a class="btn btn-danger" href="<%=basePath%>/management/security/role/delete" id="remove" title="删除">
                        <i class="fa fa-trash-o "></i>
                    </a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="Role:modify">
                    <a class="btn btn-warning" href="<%=basePath%>/management/security/role/rightSet" id="rightSet" title="分配权限">
                        <i class="fa fa-users "></i>
                    </a>
                    </shiro:hasPermission>
                    <form class="navbar-form navbar-right" style="margin-top:0px;" method="post" id="search" action="<%=basePath%>/management/security/role/list">
                        <a class="btn btn-default" href="javascript:void(0);" title="search" id="submitSf">
                            <i class="fa fa-search "></i>
                        </a>
                        <input type="text" class="form-control" name="roleName" placeholder="角色名称" value="${searchRole.roleName}">
                        <input type="text" class="form-control" name="rightName"placeholder="权限名称" value="${searchPermission.rightName}">
                    </form>
                </div>
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="checkbox"  name="checkbox" value="0">选择</th>
                        <th>序号</th>
                        <th>角色名称</th>
                        <th>创建人</th>
                        <th>创建时间</th>
                        <th>更新者</th>
                        <th>更新时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="role" items="${roleList}" varStatus="index">
                    <tr>
                        <td ><input type="checkbox" name="checkbox" value="${role.roleId}"></td>
                        <td>${index.count}</td>
                        <td>${role.roleName}</td>
                        <td>${role.createdBy}</td>
                        <td>${fnc:dateFormat(role.createdDate)}</td>
                        <td>${role.updatedBy}</td>
                        <td>${fnc:dateFormat(role.updatedDate)}</td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%@ include file="../../pager.jsp" %>
                <div class="alert alert-danger" style="display:none">
                    <button type="button" class="close" data-dismiss="alert">×</button>
                    <strong></strong>
                </div>
            </div>
        </div>
    </div><!--/col-->
</div><!--/row-->
