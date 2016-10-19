package com.jack.security.webapp;

import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wajiangk on 9/23/2016.
 */
public class BaseController {

//    protected int f_page = 0;//分页显示第一个页码
//    protected int l_page = 0;//分页显示最后一个页码
//
//    public  void getFirstandLastPn(Pager pager){
//        if (pager.getPageNumber() < 5) {
//            f_page = 1;
//        } else {
//            f_page = pager.getPageNumber() - 3;
//        }
//        if (pager.getPageNumber() + 3 >= pager.getPageCount()) {
//            l_page = pager.getPageCount();
//        } else {
//            l_page = pager.getPageNumber() + 3;
//        }
//
//    }

    protected String getJsonResult(Map<String,Object> map){
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void setPageInfo(HttpServletRequest request,Pager pager){
        if (request != null) {
            String pageNum = request.getParameter("pageNum");
            if (org.apache.commons.lang3.StringUtils.isNotBlank(pageNum)) {
                pager.setPageNumber(new Integer(pageNum));
            }
            String numPerPage = request.getParameter("numPerPage");
            if (StringUtils.isNotEmpty(numPerPage)) {
                pager.setPageSize(new Integer(numPerPage));
            }
        }

    }
}
