<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>好友列表</strong></h2>
                <div class="panel-actions">
                    <a href="<%=basePath%>/management/security/user/list" class="btn-setting"><i
                            class="fa fa-rotate-right"></i></a>
                    <a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                </div>
            </div>
            <div class="panel-body">

                <div class="panel-group" id="accordion">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <div class="btn-action">
                                    <a class="create btn btn-success "
                                       href="<%=basePath%>/management/security/friend/addFriend"
                                       title="添加好友" id="create" data-toggle="modal">
                                        <i class="fa fa-search-plus "></i>
                                    </a>
                                    <form class="navbar-form navbar-right" style="margin-top:0px;" method="post"
                                          id="search"
                                          action="<%=basePath%>/management/security/user/list">
                                        <a class="btn btn-default" href="javascript:void(0);" title="search"
                                           id="submitSf">
                                            <i class="fa fa-search "></i>
                                        </a>
                                        <input type="text" class="form-control" name="userName" placeholder="用户名"
                                               value="${searchUser.userName}">

                                    </form>
                                </div>
                            </h4>
                        </div>
                    </div>


                    <div id="wizard2" class="wizard-type1">
                        <ul class="steps nav nav-pills info">
                            <c:forEach var="group" items="${friends}" varStatus="ind">
                                <li
                                        <c:if test="${ind.count==1}">class="active"</c:if>
                                        <c:if test="${ind.count!=1}">class="complete"</c:if>>
                                    <a href="#tab${ind.count}" data-toggle="tab">
                                        <span class="badge badge-info"><i class="fa fa-user"></i></span>
                                            ${group.groupName}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="progress thin">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="0"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
                            </div>
                        </div>
                        <div class="tab-content">
                            <c:forEach var="group" items="${friends}" varStatus="ind">
                                <div
                                        <c:if test="${ind.count==1}">class="tab-pane active"</c:if>
                                        <c:if test="${ind.count!=1}">class="tab-pane"</c:if> id="tab${ind.count}">
                                    <ul class="user-friend-list">
                                        <c:forEach var="friend" items="${group.friends}">
                                            <li>
                                                <img src="<%=basePath%>/file/upload/headpic/${friend.userFriend.headPicPath}">
                                                <span name="headPicPath"
                                                      style="display:none;">${friend.userFriend.headPicPath}</span>
                                                <c:if test="${friend.friendCommentName !=null && friend.friendCommentName!=''}">
                                                    <span name="friendCommentName">${friend.friendCommentName}</span>
                                                    <span name="friendUserName" style="display: none">${friend.userFriend.userName}</span>
                                                </c:if>
                                                <c:if test="${friend.friendCommentName ==null || friend.friendCommentName==''}">
                                                    <span name="friendUserName">${friend.userFriend.userName}</span>
                                                </c:if>
                                                <c:if test="${friend.userFriend.loginState==0}">
                                                    (离线)
                                                </c:if>
                                                <c:if test="${friend.userFriend.loginState==1}">
                                                    (在线)
                                                </c:if>
                                                <a class="btn btn-success" title="点击聊天" id="startChat"
                                                   href="<%=basePath%>/management/onlineChat/ptpChat"><i
                                                        class="fa fa-comments"></i></a>
                                                <a class="btn btn-info" title="发送邮件"><i
                                                        class="fa fa-envelope-o"></i></a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

            </div>
        </div><!--/col-->
    </div>
    <!--/row-->
</div>