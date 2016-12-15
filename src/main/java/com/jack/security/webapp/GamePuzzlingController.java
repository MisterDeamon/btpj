package com.jack.security.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wajiangk on 11/15/2016.
 */
@Controller
@RequestMapping(value = "/management/security/puzzling")
public class GamePuzzlingController extends BaseController{

    private static final String PUZZLING="management/security/puzzling/puzzling";

    @RequestMapping(value={"","/puzzling"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String puzzling(){
        return PUZZLING;
    }
}
