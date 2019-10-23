package com.sky.controller;

import com.sky.core.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by ThinkPad on 2019/5/10.
 */
@Controller
@RequestMapping("/stock")
public class StockCompanyController {

    @RequestMapping("/stockCompanyList")
    public String companyInformationList(){
        return "page/stockCompanyList";
    }

    @RequestMapping("/companyInformationEdit")
    public String companyInformationEdit(Integer id , Model model){
        model.addAttribute("id",id);
        return "page/companyInformationEdit";
    }

    @RequestMapping("/stockCompanyStatictis")
    public String stockCompanyStatictis(Integer id , String stockCode, Model model){
        model.addAttribute("id",id);
        model.addAttribute("stockCode",stockCode);
        return "page/stockCompanyStatictis";
    }

    @RequestMapping("/stockPoolList")
    public String stockPoolList(){
        return  "page/stockPoolList";
    }


    @RequestMapping("/stockNoticeClassList")
    public String stockNoticeClassList(){
        return "page/stockNoticeClassList";
    }

    @RequestMapping("/stockCompanyNoticeList")
    public String stockCompanyNoticeList(Model model){
        model.addAttribute("startDay" , DateUtils.getDate());
        model.addAttribute("endDay" , DateUtils.format(DateUtils.addDays(new Date(),1) ,"yyyy-MM-dd"));
        return "page/stockCompanyNoticeList";
    }

    @RequestMapping("/stockSectorCompanyList")
    public String stockSectorCompanyList(Model model ,String stockCode ,String stockName ,String firstSector ,String secondSector ,String thirdSecotor ,String forthSector){
        model.addAttribute("stockCode" , stockCode);
        model.addAttribute("stockName" , stockName);
        model.addAttribute("firstSector" , firstSector);
        model.addAttribute("secondSector" , secondSector);
        model.addAttribute("thirdSecotor" , thirdSecotor);
        model.addAttribute("forthSector" , forthSector);
        return "page/stockSectorCompanyList";
    }

    @RequestMapping("/stockCompanyFinancial")
    public String stockCompanyFinancial(Model model ,String stock_code ,String stockCode ,String stockName ,String firstSector ,String secondSector ,String thirdSecotor ,String forthSector){
        model.addAttribute("stock_code" , stock_code);
        model.addAttribute("stockCode" , stockCode);
        model.addAttribute("stockName" , stockName);
        model.addAttribute("firstSector" , firstSector);
        model.addAttribute("secondSector" , secondSector);
        model.addAttribute("thirdSecotor" , thirdSecotor);
        model.addAttribute("forthSector" , forthSector);
        return "page/stockCompanyFinancial";
    }

}
