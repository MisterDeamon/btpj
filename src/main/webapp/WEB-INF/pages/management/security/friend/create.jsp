<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%
    String basePath = request.getContextPath();
    String protonPath = basePath+"/styles/proton";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row" id="infoFriend">
    <div class="col-lg-8">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>添加好友</strong></h2>
                <div class="panel-actions">
                    <a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                    <a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
                </div>
            </div>


            <div class="panel-body">
                <div class="btn-action ">
                    <form class="navbar-form navbar-left" style="margin-top:0px;" method="post" id="searchFriend" action="<%=basePath%>/management/security/friend/addFriend">
                        <a class="btn btn-default" href="javascript:void(0);" title="查找" id="submitSf">
                            <i class="fa fa-search "></i>
                        </a>
                        <input type="text" class="form-control" name="userName" placeholder="好友名称" value="${searchUser.userName}">
                    </form>
                </div>
                <div class="stopLeft">
                    <ul class="user-friend-list searchFriend">
                        <c:forEach var="user" items="${userList}" varStatus="index">
                        <li><img  src="<%=basePath%>/file/upload/headpic/${user.headPicPath}" /><span name="userName">${user.userName}</span>
                            <input type="hidden" name="userId" value="${user.id}" />
                            <c:if test="${user.loginState==1}">
                                <span class="label label-success">在线</span>
                            </c:if>
                            <c:if test="${user.loginState==0}">
                                <span class="label label-default">离线</span>
                            </c:if>
                            <a title="加为好友" id="addFriendB"><i class="fa fa-plus"></i></a>
                        </li>
                        </c:forEach>
                    </ul>
                </div>
                <c:if test="${userList!=null}">
                    <%@ include file="../../pager.jsp" %>
                </c:if>
            </div>


            <div class="panel-body">
                <form action="<%=basePath%>/management/security/friend/saveFriend" method="post" enctype="multipart/form-data">
                    <table class="table table-striped table-bordered bootstrap-datatable">
                        <tr >
                            <td>好友名称</td><td><input type="text" name="userFriend.userName"/><span ></span></td>
                            <input type="hidden" name="userFriend.id"/>
                        </tr>
                        <tr >
                            <td>备注名</td><td><input type="text" name="friendCommentName"/><span ></span></td>
                        </tr>
                        <tr >
                            <td>选择分组</td>
                            <td>
                                <select name="friendGroup.groupId">
                                    <c:forEach var="group" items="${groups}">
                                        <option value="${group.groupId}">${group.groupName}</option>
                                    </c:forEach>
                                </select><span ></span>
                            </td>
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



