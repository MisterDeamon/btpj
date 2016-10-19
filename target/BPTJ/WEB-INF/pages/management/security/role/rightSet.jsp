<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<div class="row">
    <div class="col-lg-8 col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>权限分配</strong></h2>
                <div class="panel-actions">
                    <a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                    <a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
                </div>
            </div>

            <div class="panel-body">
                <form action="<%=basePath%>/management/security/role/rightSet" method="post">
                    <table class="table table-striped table-bordered bootstrap-datatable ">
                        <thead>
                        <th>角色名称</th>
                        <th>${roleSet.roleName}</th>
                        <input type="hidden" value="${roleSet.roleId}" name="roleId"/>
                        </thead>
                    </table>

                    <div class="panel-group" id="accordion">

                        <c:forEach items="${menus}" var="menu" varStatus="indexP">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <input type="checkbox" name="rightId" value="${menu.permission.rightId}"
                                            <c:if test="${fnc:hasRight(menu.permission.rightId,roleSet)}">checked="checked"</c:if>
                                        >
                                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion"
                                           href="<%=basePath%>/management/security/role/rightSet#collapse${indexP.count}">
                                              ${menu.name}
                                        </a>
                                    </h4>
                                </div>
                                <div id="collapse${indexP.count}"
                                     <c:if test="${indexP.count==1}">class="panel-collapse collapse in"</c:if>
                                     <c:if test="${indexP.count!=1}">class="panel-collapse collapse"</c:if>
                                >
                                    <div class="panel-body" id="children">
                                        <c:if test="${fn:length(menu.childrenMenus)>0}">
                                            <c:forEach var="child" items="${menu.childrenMenus}" varStatus="indexC">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <h4 class="panel-title">
                                                            <a data-toggle="collapse" data-parent="#children"
                                                               href="#chilrd${indexP.count}${indexC.count}">
                                                                    ${child.name}
                                                            </a>
                                                        </h4>
                                                    </div>
                                                    <div id="chilrd${indexP.count}${indexC.count}" class="panel-collapse collapse">
                                                        <div class="panel-body">
                                                            <ul class="steps nav nav-pills">
                                                                <c:set var="viewId" value="${fnc:findRightId(child.rightSn,'view',permissions)}"></c:set>
                                                                <c:set var="createId" value="${fnc:findRightId(child.rightSn,'create',permissions)}"></c:set>
                                                                <c:set var="modifyId" value="${fnc:findRightId(child.rightSn,'modify',permissions)}"></c:set>
                                                                <c:set var="deleteId" value="${fnc:findRightId(child.rightSn,'delete',permissions)}"></c:set>
                                                                <li><input type="checkbox" name="rightId" value="${viewId}" <c:if test="${fnc:hasRight(viewId,roleSet)}">checked="checked"</c:if> /><span class="badge badge-info"><i class="fa fa-search "></i></span>查看</li>
                                                                <li><input type="checkbox" name="rightId" value="${createId}" <c:if test="${fnc:hasRight(createId,roleSet)}">checked="checked"</c:if>  /><span class="badge badge-info"><i class="fa fa-plus-square-o "></i></span> 增加</li>
                                                                <li><input type="checkbox" name="rightId" value="${modifyId}" <c:if test="${fnc:hasRight(modifyId,roleSet)}">checked="checked"</c:if> /><span class="badge badge-info"><i class="fa fa-edit "></i></span> 编辑</li>
                                                                <li><input type="checkbox" name="rightId" value="${deleteId}" <c:if test="${fnc:hasRight(deleteId,roleSet)}">checked="checked"</c:if> /><span class="badge badge-info"><i class="fa fa-trash-o " title=""></i></span> 删除</li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>
                                            </c:forEach>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="panel-footer middle">
                        <button type="submit" id="subRoleSet" class="btn btn-sm btn-success"><i
                                class="fa fa-dot-circle-o"></i> Submit
                        </button>
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

</div>
<!--/row-->



