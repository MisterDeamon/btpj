<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<div class="row">
    <div class="col-lg-8 col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>编辑菜单</strong></h2>
                <div class="panel-actions">
                    <a href="index.html#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
                    <a href="index.html#" class="btn-close"><i class="fa fa-times"></i></a>
                </div>
            </div>
            <div class="panel-body">
                <form action="<%=basePath%>/management/security/menu/modify" method="post">
                    <table class="table table-striped table-bordered bootstrap-datatable ">
                        <input type="hidden" name="id" value="${menu.id}" readonly="readonly"/>
                        <tr >
                            <td>菜单名称</td><td><input type="text" name="name" value="${menu.name}" readonly="readonly"/><span></span></td>
                        </tr>
                        <tr>
                            <td>授权标识</td><td><input type="text" name="rightSn" value="${menu.rightSn}" eadonly="readonly"/><span></span></td>
                        </tr>
                        <tr>
                            <td>访问路径</td><td><input type="text" name="url" value="${menu.url}"/><span></span></td>
                        </tr>
                        <tr>
                            <td>优先级</td><td><input type="text" name="priority" value="${menu.priority}"/><span></span></td>
                        </tr>
                        <tr>
                            <td>图标</td>
                            <td id="iconValue">
                                <input  type="hidden" name="icon" value="${menu.icon}"/><i class="fa ${menu.icon}"></i><span></span>
                            </td>
                        </tr>
                        <tr>
                            <td>是否父菜单</td>
                            <td>
                                <input type="radio" name="isParent" alt="是" value="1" style="width:50px;"  <c:if test="${menu.isParent==1}">checked</c:if>/>是
                                <input type="radio" alt="否" name="isParent" value="0" style="width:50px;" <c:if test="${menu.isParent==0}">checked</c:if>/>否
                            </td>
                        </tr>
                        <tr>
                            <td>父菜单</td>
                            <td>
                                <div class="controls">
                                    <select id="parentId" name="parentId" class="form-control" data-rel="chosen" <c:if test="${menu.isParent==1}">disabled</c:if>>
                                        <option value="${menu.parentMenu.id}">${menu.parentMenu.name}</option>
                                        <c:forEach var="menuP" items="${parentMenus}">
                                            <option value="${menuP.id}">${menuP.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <span></span>
                            </td>
                        </tr>
                        <tr>
                            <td>描述</td>
                            <td>
                                <div class="col-md-8">
                                    <textarea id="description" name="description" rows="9" class="form-control" placeholder="description...">${menu.description}</textarea>
                                </div>
                                <span></span>
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



