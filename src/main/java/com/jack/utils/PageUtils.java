package com.jack.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wajiangk on 9/23/2016.
 */
public class PageUtils {

    public static void getLastAndFirstPn(int l_page,int f_page,int pageNumber,int pageCount){
        if (pageNumber < 5) {
            f_page = 1;
        } else {
            f_page = pageNumber - 3;
        }
        if (pageNumber + 3 >=  pageCount) {
            l_page = pageCount;
        } else {
            l_page = pageNumber + 3;
        }
    }

    public static void getPageNumber(HttpServletRequest request,Pager pager){
        if (request != null) {
            String pageNum = request.getParameter("pageNum");
            if (StringUtils.isNotEmpty(pageNum)) {
                pager.setPageNumber(new Integer(pageNum));
            }
            String numPerPage = request.getParameter("numPerPage");
            if (StringUtils.isNotEmpty(numPerPage)) {
                pager.setPageSize(new Integer(numPerPage));
            }
        }
    }
}
