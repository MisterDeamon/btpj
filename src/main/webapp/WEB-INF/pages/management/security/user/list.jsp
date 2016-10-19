<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<%@ include file="../../../common/listDialog.jsp" %>
<div class="row" id="info">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>用户信息</strong></h2>
                <div class="panel-actions">
                    <a href="<%=basePath%>/management/security/user/list" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <div class="btn-action">
                    <shiro:hasPermission name="User:create">
                        <a class="create btn btn-success "  href="<%=basePath%>/management/security/user/create" title="新增" id="create" data-toggle="modal">
                        <i class="fa fa-search-plus "></i>
                    </a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="User:modify">
                        <a class="btn btn-info" href="<%=basePath%>/management/security/user/modify" title="修改" id="modify"> <i class="fa fa-edit "></i></a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="User:delete">
                        <a class="btn btn-danger" href="<%=basePath%>/management/security/user/delete" id="remove" title="删除">
                        <i class="fa fa-trash-o "></i>
                    </a>
                    </shiro:hasPermission>
                    <shiro:hasPermission name="User:modify">
                    <a class="btn btn-warning" href="<%=basePath%>/management/security/user/roleSet" title="分配角色" id="roleSet">
                        <i class="fa fa-users "></i>
                    </a>
                    </shiro:hasPermission>
                    <form class="navbar-form navbar-right" style="margin-top:0px;" method="post" id="search" action="<%=basePath%>/management/security/user/list">
                        <a class="btn btn-default" href="javascript:void(0);" title="search" id="submitSf">
                            <i class="fa fa-search "></i>
                        </a>
                        <input type="text" class="form-control" name="userName" placeholder="用户名" value="${searchUser.userName}">
                        <input type="text" class="form-control" name="phone"placeholder="手机号码" value="${searchUser.phone}">
                        <input type="text" class="form-control" name="idCard" placeholder="身份证号" value="${searchUser.idCard}">
                        <input type="text" class="form-control" name="roleName"placeholder="角色名" value="${searchRole.roleName}">
                    </form>
                </div>
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                    <thead>
                    <tr>
                        <th><input type="checkbox" id="checkbox"  name="checkbox" value="0">选择</th>
                        <th>序号</th>
                        <th>用户名</th>
                        <th>角色</th>
                        <th>头像</th>
                        <th>证件号码</th>
                        <th>手机号码</th>
                        <th>QQ</th>
                        <th>邮箱</th>
                        <th>最后更新时间</th>
                        <th>账户状态</th>
                        <th>登录状态</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${userList}" varStatus="index">
                    <tr>
                        <td ><input type="checkbox" name="checkbox" value="${user.id}"></td>
                        <td>${index.count}</td>
                        <td>${user.userName}</td>
                        <td>
                            <c:forEach var="role" items="${user.sroles}">
                                ${role.roleName}&nbsp;
                            </c:forEach>
                        </td>
                        <td>
                           <img class="list-headpic" src="<%=basePath%>/file/upload/headpic/${user.headPicPath}" /></a>
                        </td>
                        <td>${user.idCard}</td>
                        <td>${user.phone}</td>
                        <td>${user.tencentNo}</td>
                        <td>${user.email}</td>
                        <td>${fnc:dateFormat(user.updatedDate)}</td>
                        <td>
                            <c:if test="${user.userStatus==1}">
                                <shiro:hasPermission name="User:modify">
                                    <a title="点击切换状态" name="acState" href="<%=basePath%>/management/security/user/changeAccountStatus?userId=${user.id}&status=0"><span class="label label-success">正常</span></a>
                                </shiro:hasPermission>
                                <shiro:lacksPermission name="User:modify">
                                    <span class="label label-success">正常</span>
                                </shiro:lacksPermission>
                            </c:if>
                            <c:if test="${user.userStatus==0}">
                                <shiro:hasPermission name="User:modify">
                                    <a title="点击切换状态" name="acState" href="<%=basePath%>/management/security/user/changeAccountStatus?userId=${user.id}&status=1"><span class="label label-danger"> 锁定</span></a>
                                </shiro:hasPermission>
                                <shiro:lacksPermission name="User:modify">
                                    <span class="label label-danger"> 锁定</span>
                                </shiro:lacksPermission>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${user.loginState==1}">
                                <span class="label label-success">在线</span>
                            </c:if>
                            <c:if test="${user.loginState==0}">
                                <span class="label label-default">离线</span>
                            </c:if>
                        </td>
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
