<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h2><i class="fa fa-table red"></i><span class="break"></span><strong>Members</strong></h2>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered bootstrap-datatable datatable">
                    <thead>
                    <tr>
                        <th>Employe</th>
                        <th>Position</th>
                        <th>Salary</th>
                        <th>Status</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Willson</td>
                        <td>Developer</td>
                        <td>2563$</td>
                        <td>
                            <span class="label label-warning">Pending</span>
                        </td>
                        <td>
                            <a class="btn btn-success" href="table.html#">
                                <i class="fa fa-search-plus "></i>
                            </a>
                            <a class="btn btn-info" href="table.html#">
                                <i class="fa fa-edit "></i>
                            </a>
                            <a class="btn btn-danger" href="table.html#">
                                <i class="fa fa-trash-o "></i>

                            </a>
                        </td>
                    </tr>

                    </tbody>
                </table>
                <ul class="pagination">
                    <li><a href="table.html#">Prev</a></li>
                    <li class="active">
                        <a href="table.html#">1</a>
                    </li>
                    <li><a href="table.html#">2</a></li>
                    <li><a href="table.html#">3</a></li>
                    <li><a href="table.html#">4</a></li>
                    <li><a href="table.html#">5</a></li>
                    <li><a href="table.html#">Next</a></li>
                </ul>
            </div>
        </div>
    </div><!--/col-->

</div><!--/row-->