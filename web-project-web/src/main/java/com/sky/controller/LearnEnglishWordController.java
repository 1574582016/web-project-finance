package com.sky.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.EnglishRootAffix;
import com.sky.service.EnglishRootAffixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by ThinkPad on 2018/10/11.
 */
@Controller
@RequestMapping("/english")
public class LearnEnglishWordController {

    @Autowired
    protected EnglishRootAffixService englishRootAffixService ;

    @RequestMapping("/learnEnglishWordList")
    public String learnEnglishWordList(){
        return "page/learnEnglishWordList";
    }

    @RequestMapping("/learnEnglishClassList")
    public String learnEnglishClassList(Model model){
        List<EnglishRootAffix> rootList = englishRootAffixService.selectList(new EntityWrapper<EnglishRootAffix>().where("root_type = '词根' " ).groupBy("same_root"));
        model.addAttribute("rootList" , rootList);
        return "page/learnEnglishClassList";
    }
}
