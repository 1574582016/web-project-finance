package com.sky.controller;

import com.sky.core.consts.SystemConst;
import com.sky.core.controller.BaseController;
import com.sky.model.SystemUser;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ThinkPad on 2018/10/6.
 */
@Controller
public class IndexController{

    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest request, Model model){
        SystemUser systemUser = (SystemUser)  request.getSession().getAttribute(SystemConst.SYSTEMUSER);
//        System.out.println(systemUser.toString());
        if (systemUser == null) {
            return "login";
        }
        model.addAttribute("realName" , systemUser.getRealName());
        return "index";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute(SystemConst.SYSTEMUSER);
        return "redirect:/";
    }

    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
