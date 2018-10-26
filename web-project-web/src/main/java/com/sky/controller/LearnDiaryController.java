package com.sky.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ThinkPad on 2018/10/13.
 */
@Controller
@RequestMapping("/diary")
public class LearnDiaryController {

    @RequestMapping("/learnDiaryList")
    public String larnDiaryList(){
        return "page/learnDiaryList";
    }

    @RequestMapping("/learnDiaryEdit")
    public String larnDiaryEdit(String type ,String id ,String diaryCode ,Model model){
        model.addAttribute("type",type);
        model.addAttribute("id",id);
        model.addAttribute("diaryCode",diaryCode);
        return "page/learnDiaryEdit";
    }

    @RequestMapping("/learnDiaryView")
    public String learnDiaryView(String id ,Model model){
        model.addAttribute("id",id);
        return "page/learnDiaryView";
    }

}
