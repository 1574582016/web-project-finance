package com.sky.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.BankCard;
import com.sky.model.StockHotSectorClass;
import com.sky.service.BankCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bankCard")
public class BankCardController {

    @Autowired
    private BankCardService bankCardService ;

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
}
