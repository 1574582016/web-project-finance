package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/27.
 */
@Controller
@RequestMapping("/role")
public class SystemRoleController {

    @RequestMapping("/systemRoleList")
    public String systemRoleList(){
        return "page/systemRoleList";
    }
}
