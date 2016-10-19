package com.jack.onlinechat.websocket.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wajiangk on 10/9/2016.
 */
@Controller
@RequestMapping(value = "/management/onlineChat")
public class OnLineChatController {

    private static final String PTP = "management/onlineChat/ptpChat";
    private static final String PTG = "management/onlineChat/ptgChat";

    @RequestMapping(value="/ptpChat",method = RequestMethod.GET)
    public String ptpChat(HttpServletRequest request,Model model){
        model.addAttribute("friendCommentName",request.getParameter("friendCommentName"));
        model.addAttribute("headPicPath",request.getParameter("headPicPath"));
        model.addAttribute("friendUserName",request.getParameter("friendUserName"));
        return PTP;
    }

    @RequestMapping(value="/ptgChat",method = RequestMethod.GET)
    public String ptgChat(){ return PTG;}

}
