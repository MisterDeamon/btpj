package com.jack.security.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wajiangk on 11/21/2016.
 */
@Controller
@RequestMapping(value = "/management/security")
public class TextReader {

    private final static String TEXTREADER = "management/security/textReader/textReader";

    @RequestMapping(value = "/textReader")
    public String textReader(){
        return TEXTREADER;
    }
}
