<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="pagination pageMidlle">
    <c:choose>
        <c:when test="${page.pageNumber ==1}">
            <li><a href="javascript:void(0);">上一页</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="javascript:page('${ page.pageNumber - 1}');">上一页</a></li>
        </c:otherwise>
    </c:choose>
    <c:forEach var="i" begin="${page.f_page}" end="${page.l_page}">
        <c:choose>
            <c:when test="${i== page.pageNumber}">
                <li><a href="javascript:void(0);"  style="background:#eee"><strong>${i}</strong></a></li>
            </c:when>
            <c:otherwise>
                <li><a href="javascript:page('${i}');">${i}</a></li>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:choose>
        <c:when test="${page.pageNumber == page.pageCount}">
            <li><a href="javascript:void(0);">下一页</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="javascript:page('${ page.pageNumber + 1}');">下一页</a></li>
        </c:otherwise>
    </c:choose>
</ul>
