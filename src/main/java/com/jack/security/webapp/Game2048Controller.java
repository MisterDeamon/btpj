package com.jack.security.webapp;

import com.jack.security.pojo.SecurityMenu;
import com.jack.security.service.SecurityMenuService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.utils.Pager;
import com.jack.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wajiangk on 9/23/2016.
 */
@Controller
@RequestMapping(value = "/management/security/2048")
public class Game2048Controller extends BaseController {

    private static final String GAME = "management/security/2048/2048";

    @RequestMapping(value = {"/game", ""}, method = {RequestMethod.GET, RequestMethod.POST})
    public String list(HttpServletRequest request, SecurityMenu searchMenu, Model model) {
        return GAME;
    }


}
