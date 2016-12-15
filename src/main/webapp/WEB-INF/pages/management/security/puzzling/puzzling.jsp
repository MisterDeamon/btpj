<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ include file="../../../common/proton.jsp" %>
<script src="<%=protonPath%>/assets/js/puzzling.js"></script>
<script src="<%=protonPath%>/assets/js/cropper.min.js"></script>
<div class="row" id="info">
    <div class="col-lg-12">
        <div id="game"></div>
        <script type="application/javascript">
            $("#game").makePuzzling();
        </script>
    </div><!--/col-->
</div><!--/row-->
