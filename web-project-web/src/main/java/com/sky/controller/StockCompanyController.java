package com.sky.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockMarketClass;
import com.sky.model.SystemParam;
import com.sky.service.StockMarketClassService;
import com.sky.service.SystemParamService;
import com.sky.vo.SystemParam_VO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/10.
 */
@Controller
@RequestMapping("/stock")
public class StockCompanyController {

    @Autowired
    private StockMarketClassService stockMarketClassService;

    @Autowired
    private SystemParamService systemParamService ;

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
    public String stockPoolList(Model model ,
                                String stockCode ,
                                String stockName ,
                                String firstSector ,
                                String secondSector ,
                                String thirdSecotor ,
                                String forthSector){
        List<SystemParam_VO> list = systemParamService.getParamListByIdentity("companyQuality");
        model.addAttribute("qualityList" , list);
        model.addAttribute("stockCode" , stockCode);
        model.addAttribute("stockName" , stockName);
        model.addAttribute("firstSector" , firstSector);
        model.addAttribute("secondSector" , secondSector);
        model.addAttribute("thirdSecotor" , thirdSecotor);
        model.addAttribute("forthSector" , forthSector);
        return  "page/stockPoolList";
    }

    @RequestMapping("/stockProfitIncreaseList")
    public String stockProfitIncreaseList(){
        return  "page/stockProfitIncreaseList";
    }

    @RequestMapping("/stockPoolDetail")
    public String stockPoolDetail(Model model ,
                                  String stock_code ,
                                  String stockCode ,
                                  String stockName ,
                                  String firstSector ,
                                  String secondSector ,
                                  String thirdSecotor ,
                                  String forthSector){
        List<SystemParam_VO> list = systemParamService.getParamListByIdentity("companyQuality");
        model.addAttribute("qualityList" , list);
        model.addAttribute("stock_code" , stock_code);
        model.addAttribute("stockCode" , stockCode);
        model.addAttribute("stockName" , stockName);
        model.addAttribute("firstSector" , firstSector);
        model.addAttribute("secondSector" , secondSector);
        model.addAttribute("thirdSecotor" , thirdSecotor);
        model.addAttribute("forthSector" , forthSector);
        return  "page/stockPoolDetail";
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
    public String stockSectorCompanyList(Model model ,
                                         String stockCode ,
                                         String stockName ,
                                         String firstSector ,
                                         String secondSector ,
                                         String thirdSecotor ,
                                         String forthSector ,
                                         String firstHot ,
                                         String secondHot ,
                                         String thirdHot ,
                                         String forthHot ){
        List<SystemParam_VO> list = systemParamService.getParamListByIdentity("companyQuality");
        model.addAttribute("qualityList" , list);
        model.addAttribute("firstHot" , firstHot);
        model.addAttribute("secondHot" , secondHot);
        model.addAttribute("thirdHot" , thirdHot);
        model.addAttribute("forthHot" , forthHot);
        model.addAttribute("stockCode" , stockCode);
        model.addAttribute("stockName" , stockName);
        model.addAttribute("firstSector" , firstSector);
        model.addAttribute("secondSector" , secondSector);
        model.addAttribute("thirdSecotor" , thirdSecotor);
        model.addAttribute("forthSector" , forthSector);
        return "page/stockSectorCompanyList";
    }

    @RequestMapping("/stockCompanyFinancial")
    public String stockCompanyFinancial(Model model ,
                                        String stock_code ,
                                        String stockCode ,
                                        String stockName ,
                                        String firstSector ,
                                        String secondSector ,
                                        String thirdSecotor ,
                                        String forthSector ,
                                        String firstHot ,
                                        String secondHot ,
                                        String thirdHot ,
                                        String forthHot ){
        model.addAttribute("firstHot" , firstHot);
        model.addAttribute("secondHot" , secondHot);
        model.addAttribute("thirdHot" , thirdHot);
        model.addAttribute("forthHot" , forthHot);
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
