<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<%@ include file="../../../common/listDialog.jsp" %>
<%@ include file="../menu/icon.jsp" %>
<div class="row" id="info">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>菜单信息</strong></h2>
                <div class="panel-actions">
                    <a href="<%=basePath%>/management/security/menu/list" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="btn-action">
                    <shiro:hasPermission name="Menu:create"></shiro:hasPermission>
                        <a class="create btn btn-success "  href="<%=basePath%>/management/security/menu/create" title="新增" id="create" data-toggle="modal">
                        <i class="fa fa-search-plus "></i>
                    </a>

                    <shiro:hasPermission name="Menu:modify"></shiro:hasPermission>
                        <a class="btn btn-info" href="<%=basePath%>/management/security/menu/modify" title="修改" id="modify"> <i class="fa fa-edit "></i></a>

                    <shiro:hasPermission name="Menu:delete"></shiro:hasPermission>
                        <a class="btn btn-danger" href="<%=basePath%>/management/security/menu/delete" id="remove" title="删除">
                        <i class="fa fa-trash-o "></i>
                    </a>

                    <form class="navbar-form navbar-right" style="margin-top:0px;" method="post" id="search" action="<%=basePath%>/management/security/menu/list">
                        <a class="btn btn-default" href="javascript:void(0);" title="search" id="submitSf">
                            <i class="fa fa-search "></i>
                        </a>
                        <input type="text" class="form-control" name="name" placeholder="菜单名称" value="${searchMenu.name}">
                        <input type="text" class="form-control" name="rightSn"placeholder="授权标识" value="${searchMenu.rightSn}">
                        <input type="text" class="form-control" name="parentName" placeholder="父菜单" value="${parentName}">
                    </form>
                </div>
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="checkbox"  name="checkbox" value="0">选择</th>
                        <th>序号</th>
                        <th>菜单名称</th>
                        <th>授权标识</th>
                        <th>访问路径</th>
                        <th>优先级</th>
                        <th>图标</th>
                        <th>是否父菜单</th>
                        <th>父菜单</th>
                        <th>描述</th>
                        <th>创建人</th>
                        <th>创建时间</th>
                        <th>更新者</th>
                        <th>更新时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="menu" items="${menuList}" varStatus="index">
                    <tr>
                        <td ><input type="checkbox" name="checkbox" value="${menu.id}"></td>
                        <td>${index.count}</td>
                        <td>${menu.name}</td>
                        <td>${menu.rightSn}</td>
                        <td>${menu.url}</td>
                        <td>${menu.priority}</td>
                        <td><i class="fa ${menu.icon}"></i></td>
                        <td>${menu.isParent}</td>
                        <td>${menu.parentMenu.name}</td>
                        <td>${menu.description}</td>
                        <td>${menu.createdBy}</td>
                        <td>${fnc:dateFormat(menu.createdDate)}</td>
                        <td>${menu.updatedBy}</td>
                        <td>${fnc:dateFormat(menu.updatedDate)}</td>
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



