package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2019/5/10.
 */
@Controller
@RequestMapping("/company")
public class CompanyController {

    @RequestMapping("/companyInformationList")
    public String companyInformationList(){
        return "page/companyInformationList";
    }

    @RequestMapping("/companyInformationEdit")
    public String companyInformationEdit(Integer id , Model model){
        model.addAttribute("id",id);
        return "page/companyInformationEdit";
    }

    @RequestMapping("/companyAllInfomationStatictis")
    public String companyAllInfomationStatictis(Integer id , String listCode, Model model){
        model.addAttribute("id",id);
        model.addAttribute("listCode",listCode);
        return "page/companyAllInfomationStatictis";
    }
}
