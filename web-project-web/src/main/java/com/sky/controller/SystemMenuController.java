package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/6.
 */
@Controller
@RequestMapping("/menu")
public class SystemMenuController {

    @RequestMapping("/systemMenuList")
    public String systemMenuList(){
        return "page/systemMenuList";
    }
}
