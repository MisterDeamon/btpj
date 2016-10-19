<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<%@ include file="../../../common/listDialog.jsp" %>
<div class="row" id="info">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>群组信息</strong></h2>
                <div class="panel-actions">
                    <a href="<%=basePath%>/management/security/friendGroup/list" class="btn-setting"><i
                            class="fa fa-rotate-right"></i></a>
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="btn-action">
                    <a class="create btn btn-success " href="<%=basePath%>/management/security/friendGroup/create"
                       title="新增" id="create" data-toggle="modal">
                        <i class="fa fa-search-plus "></i>
                    </a>
                    <a class="btn btn-info" href="<%=basePath%>/management/security/friendGroup/modify" title="修改"
                       id="modify"> <i class="fa fa-edit "></i></a>

                    <form class="navbar-form navbar-right" style="margin-top:0px;" method="post" id="search"
                          action="<%=basePath%>/management/security/friendGroup/list">
                        <a class="btn btn-default" href="javascript:void(0);" title="search" id="submitSf">
                            <i class="fa fa-search "></i>
                        </a>
                        <input type="text" class="form-control" name="groupName" placeholder="群名称"
                               value="${searchfriendGroup.groupName}">
                    </form>
                </div>
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="checkbox" name="checkbox" value="0">选择</th>
                        <th>序号</th>
                        <th>群名称</th>
                        <th>优先级</th>
                        <th>更新人</th>
                        <th>最后更新时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="group" items="${friendGroupList}" varStatus="index">
                        <tr>
                            <td><input type="checkbox" name="checkbox" value="${group.groupId}"></td>
                            <td>${index.count}</td>
                            <td>${group.groupName}</td>
                            <td>${group.groupPriority}</td>
                            <td>${group.updatedBy}</td>
                            <td>${fnc:dateFormat(group.updatedDate)}</td>
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
</div>
<!--/row-->
