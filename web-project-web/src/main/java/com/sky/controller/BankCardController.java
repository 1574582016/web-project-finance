package com.sky.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.BankCard;
import com.sky.model.DailyExpensesClass;
import com.sky.service.BankCardService;
import com.sky.service.DailyExpensesClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/bankCard")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService ;

    @Autowired
    private DailyExpensesClassService dailyExpensesClassService ;

    @RequestMapping("/bankCardList")
    public String bankCardList(){
        return "page/bankCardList";
    }

    @RequestMapping("/bankLoadRecordList")
    public String bankLoadRecordList(Model model){
        List<BankCard> list = bankCardService.selectList(new EntityWrapper<BankCard>().where("isvalid = 1").orderBy("bank_type asc"));
        model.addAttribute("bankList" , list);
        return "page/bankLoadRecordList";
    }

    @RequestMapping("/dailyExpensesClassList")
    public String dailyExpensesClassList(){
        return "page/dailyExpensesClassList";
    }

    @RequestMapping("/dailyExpensesRecordList")
    public String dailyExpensesRecordList(Model model){
        List<DailyExpensesClass> list = dailyExpensesClassService.selectList(new EntityWrapper<DailyExpensesClass>().where("isvalid = 1").orderBy("class_order asc"));
        model.addAttribute("expenseList" , list);
        return "page/dailyExpensesRecordList";
    }
}
