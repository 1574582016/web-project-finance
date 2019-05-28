package com.sky.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.model.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ThinkPad on 2019/5/10.
 */
@RestController
@RequestMapping("/api/stock")
public class StockCompanyApiController extends AbstractController {

    @LogRecord(name = "getStockCompanyBaseList" ,description = "查询公司基本信息列表")
    @PostMapping("/getStockCompanyBaseList")
    public Object getStockCompanyBaseList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                          @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                          String stockCode ,
                                          String stockName ,
                                          String stockSector ,
                                          String stockExchange ,
                                          String startDay ,
                                          String endDay){
        Page selectedPage = stockCompanyBaseService.getStockCompanyBaseList( page , size , stockCode, stockName ,stockSector ,stockExchange ,startDay ,endDay );
        return PageData(selectedPage);
    }

    @LogRecord(name = "editCompanyBaseInformation" ,description = "编辑公司基本信息")
    @PostMapping("/editCompanyBaseInformation")
    public Object editCompanyBaseInformation(@RequestBody CompanyBaseInformation body){
        System.out.println(body.toString());
        int num = companyBaseInformationService.selectCount(new EntityWrapper<CompanyBaseInformation>().where("list_code = {0}",body.getListCode()));
        if(num == 1 && body.getId() == null){//添加时
            return ResponseEntity.ok(MapError("该公司已存在！"));
        }
        companyBaseInformationService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "getStockCompanyBaseById" ,description = "根据ID获取公司基本信息")
    @PostMapping("/getStockCompanyBaseById")
    public Object getStockCompanyBaseById(String id){
        StockCompanyBase companyBase = stockCompanyBaseService.selectById(id);
        List<StockCompanyProduct> productlist = stockCompanyProductService.getStockCompanyConstruct(companyBase.getStockACode());
        List<StockCompanyAnalyse> analyseList = stockCompanyAnalyseService.selectList(new EntityWrapper<StockCompanyAnalyse>().where("isvalid = 1 and stock_code = {0}", companyBase.getStockACode()));
        Map<String ,Object> map = new HashedMap();
        map.put("companyBase" , companyBase);
        List<StockCompanyProduct> sectorList = new ArrayList<>();
        List<StockCompanyProduct> productList = new ArrayList<>();
        List<StockCompanyProduct> regionList = new ArrayList<>();
        for(StockCompanyProduct product : productlist){
           if(product.getPruductType().equals("行业分类")){
               sectorList.add(product);
           }
            if(product.getPruductType().equals("产品分类")){
                productList.add(product);
            }
            if(product.getPruductType().equals("地域分类")){
                regionList.add(product);
            }
        }
        map.put("sectorList" , sectorList);
        map.put("productList" , productList);
        map.put("regionList" , regionList);
        map.put("analyseList" , analyseList);
        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

    @LogRecord(name = "getCompanyOperateInformationList" ,description = "查询公司经营分析信息列表")
    @PostMapping("/getCompanyOperateInformationList")
    public Object getCompanyOperateInformationList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                                @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                                String listCode ){
        Page selectedPage = companyOperateInformationService.getCompanyOperateInformationList( page , size , listCode);
        return PageData(selectedPage);
    }

    @LogRecord(name = "editCompanyOperateInformation" ,description = "编辑公司营运数据信息")
    @PostMapping("/editCompanyOperateInformation")
    public Object editCompanyOperateInformation(@RequestBody CompanyOperateInformation body){
        companyOperateInformationService.insertOrUpdate(body);
        return ResponseEntity.ok(MapSuccess("保存成功！"));
    }

    @LogRecord(name = "getCompanyOperateInformationById" ,description = "根据ID获取公司营运数据信息")
    @PostMapping("/getCompanyOperateInformationById")
    public Object getCompanyOperateInformationById(String id){
        CompanyOperateInformation operateInfo = companyOperateInformationService.selectById(id);
        return ResponseEntity.ok(MapSuccess("查询成功",operateInfo));
    }

    @LogRecord(name = "getCompanyOpratePieData" ,description = "查询公司运营构成数据")
    @PostMapping("/getCompanyOpratePieData")
    public Object getCompanyOpratePieData(String listCode){
        EntityWrapper<CompanyOperateInformation> entityWrapper = new EntityWrapper();
        entityWrapper.where("list_code = {0}" , listCode);
        List<CompanyOperateInformation> list = companyOperateInformationService.selectList(entityWrapper);
        JSONArray titleArray = new JSONArray();
        JSONArray revenueArray = new JSONArray();
        JSONArray costArray = new JSONArray();
        JSONArray profitArray = new JSONArray();
        for(CompanyOperateInformation operateInfo : list){
            String typeName = operateInfo.getTypeName() ;
            String operateRevenue = operateInfo.getOperateRevenue() ;
            String operateCost = operateInfo.getOperateCost() ;
            String operateProfit = operateInfo.getOperateProfit() ;

            titleArray.add(typeName);

            JSONObject revenueObject = new JSONObject();
            revenueObject.put("name" , typeName);
            revenueObject.put("value" , operateRevenue.substring(0,operateRevenue.length()-1));
            revenueArray.add(revenueObject);

            JSONObject costObject = new JSONObject();
            costObject.put("name" , typeName);
            costObject.put("value" , operateCost.substring(0,operateCost.length()-1));
            costArray.add(costObject);

            JSONObject profitObject = new JSONObject();
            profitObject.put("name" , typeName);
            profitObject.put("value" , operateProfit.substring(0,operateProfit.length()-1));
            profitArray.add(profitObject);
        }
        Map<String,Object> map = new HashedMap();
        map.put("titles" , titleArray);
        map.put("revenue" , revenueArray);
        map.put("cost" , costArray);
        map.put("profit" , profitArray);
        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }

    @LogRecord(name = "getCompanyOprateBarData" ,description = "查询公司运营构成增长数据")
    @PostMapping("/getCompanyOprateBarData")
    public Object getCompanyOprateBarData(String listCode){
        EntityWrapper<CompanyOperateInformation> entityWrapper = new EntityWrapper();
        entityWrapper.where("list_code = {0}" , listCode);
        entityWrapper.where("type_name = {0}" , "销售");
        entityWrapper.orderBy("publish_date desc");
        List<CompanyOperateInformation> list = companyOperateInformationService.selectList(entityWrapper);
        JSONArray titleArray = new JSONArray();
        JSONArray revenueArray = new JSONArray();
        JSONArray costArray = new JSONArray();
        JSONArray profitArray = new JSONArray();
        JSONArray grossArray = new JSONArray();
        for(CompanyOperateInformation operateInfo : list){
            String publishDate = operateInfo.getPublishDate() ;
            String operateRevenue = operateInfo.getOperateRevenue() ;
            String operateCost = operateInfo.getOperateCost() ;
            String operateProfit = operateInfo.getOperateProfit() ;

            titleArray.add(publishDate);
            revenueArray.add(operateRevenue.substring(0,operateRevenue.length()-1));
            costArray.add(operateCost.substring(0,operateCost.length()-1));
            profitArray.add(operateProfit.substring(0,operateProfit.length()-1));
            String profit = operateProfit.substring(0,operateProfit.length()-1);
            String revenue = operateRevenue.substring(0,operateRevenue.length()-1);
            System.out.println("=======profit==========" + profit + "============revenue===========" + revenue);
//            BigDecimal grossRate = new BigDecimal(profit).divide(new BigDecimal(revenue)).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
//            grossArray.add(grossRate);
        }
        Map<String,Object> map = new HashedMap();
        map.put("titles" , titleArray);
        map.put("revenue" , revenueArray);
        map.put("cost" , costArray);
        map.put("profit" , profitArray);
        map.put("gross" , grossArray);
        return ResponseEntity.ok(MapSuccess("查询成功",map));
    }
}
