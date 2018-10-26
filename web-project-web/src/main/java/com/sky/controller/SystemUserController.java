package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/12.
 */
@Controller
@RequestMapping("/user")
public class SystemUserController {

    @RequestMapping("/systemUserList")
    public String systemUserList(){
        return "page/systemUserList";
    }
}
