package com.sky.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.StringUtils;
import com.sky.model.*;
import com.sky.vo.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Maps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

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
                                          String endDay ,
                                          String middleContrySector ){
        Page selectedPage = stockCompanyBaseService.getStockCompanyBaseList( page , size , stockCode, stockName ,stockSector ,stockExchange ,startDay ,endDay ,middleContrySector );
        return PageData(selectedPage);
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


    @LogRecord(name = "getStockCompanyProductPieData" ,description = "查询公司运营构成数据")
    @PostMapping("/getStockCompanyProductPieData")
    public Object getStockCompanyProductPieData(String stockCode){
        EntityWrapper<StockCompanyProduct> entityWrapper = new EntityWrapper();
        entityWrapper.where("stock_code = {0}" , stockCode).and("publish_date = '2018-12-31'").and("pruduct_type = '产品分类'");
        List<StockCompanyProduct> list = stockCompanyProductService.selectList(entityWrapper);
        JSONArray titleArray = new JSONArray();
        JSONArray revenueArray = new JSONArray();
        JSONArray costArray = new JSONArray();
        JSONArray profitArray = new JSONArray();
        for(StockCompanyProduct product : list){
            String typeName = product.getProductName() ;
            String operateRevenue = product.getProductRevenue() ;
            String operateCost = product.getProductCost() ;
            String operateProfit = product.getProductProfit() ;

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

    @LogRecord(name = "getStockCompanyProductBarData" ,description = "查询公司运营构成增长数据")
    @PostMapping("/getStockCompanyProductBarData")
    public Object getStockCompanyProductBarData(String stockCode){
        EntityWrapper<StockCompanyProduct> entityWrapper = new EntityWrapper();
        entityWrapper.where("stock_code = {0}" , stockCode);
        entityWrapper.where("product_name = {0}" , "销售");
        entityWrapper.orderBy("publish_date desc");
        List<StockCompanyProduct> list = stockCompanyProductService.selectList(entityWrapper);
        JSONArray titleArray = new JSONArray();
        JSONArray revenueArray = new JSONArray();
        JSONArray costArray = new JSONArray();
        JSONArray profitArray = new JSONArray();
        JSONArray grossArray = new JSONArray();
        for(StockCompanyProduct product : list){
            String publishDate = product.getPublishDate() ;
            String operateRevenue = product.getProductRevenue() ;
            String operateCost = product.getProductCost() ;
            String operateProfit = product.getProductProfit() ;

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

    @LogRecord(name = "getStockMarketClassList" ,description = "查询股票板块分类数据")
    @PostMapping("/getStockMarketClassList")
    public Object getStockMarketClassList(String classType){
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("class_type = {0}" , classType).and("isvalid = 1");
        List<StockMarketClass> list = stockMarketClassService.selectList(entityWrapper);
        return ResponseEntity.ok(MapSuccess("查询成功",list));
    }

    @LogRecord(name = "getStockCompanySectorList" ,description = "查询公司行业信息列表")
    @PostMapping("/getStockCompanySectorList")
    public Object getStockCompanySectorList(String stockCode ,
                                            String stockName ,
                                            String firstSector ,
                                            String secondSector ,
                                            String thirdSecotor ,
                                            String forthSector ,
                                            String firstHot ,
                                            String secondHot ,
                                            String thirdHot ,
                                            String forthHot ){
        List<CompanySectorVO> list = stockCompanySectorService.getStockCompanySectorList(stockCode, stockName, firstSector, secondSector, thirdSecotor, forthSector ,firstHot , secondHot ,thirdHot ,forthHot );
        Map<String ,Object> map = new HashMap<String ,Object>();
        map.put("total",list.size());
        map.put("rows",list);
        return map;
    }

    @LogRecord(name = "getStockHotSectorClassList" ,description = "查询热点企业信息")
    @PostMapping("/getStockHotSectorClassList")
    public Object getStockHotSectorClassList(String firstSector, String secondSector, String thirdSector, String forthSector, String fiveSector ,String sectorTypes){
        List<StockHotSectorClass_VO> searchList = stockHotSectorClassService.getStockHotSectorClassList(firstSector , secondSector , thirdSector , forthSector , fiveSector ,sectorTypes);
        Map<String ,List<StockHotSectorClass_VO>> firstMap = new HashMap<String ,List<StockHotSectorClass_VO>>();
        for(StockHotSectorClass_VO companySectorVo : searchList){
            List<StockHotSectorClass_VO> list1 = firstMap.get(companySectorVo.getFirstSector());
            if(list1 == null || list1.size() == 0){
                list1 = new ArrayList<StockHotSectorClass_VO>();
                list1.add(companySectorVo);
            }else{
                list1.add(companySectorVo);
            }
            firstMap.put(companySectorVo.getFirstSector() , list1);
        }

        /*************************************第 2 次*****************************************/
        Map<String , Map<String , Map<String , Map<String ,List<StockHotSectorClass_VO>>>>>  resultFirstMap = new HashMap<>();
        for (Map.Entry<String, List<StockHotSectorClass_VO>> entry : firstMap.entrySet()) {
            String key2 = entry.getKey() ;
            List<StockHotSectorClass_VO> list2 = entry.getValue();

            Map<String ,List<StockHotSectorClass_VO>> secondMap = new HashMap<String ,List<StockHotSectorClass_VO>>();
            for(StockHotSectorClass_VO companySectorVo : list2){
                List<StockHotSectorClass_VO> list21 = secondMap.get(companySectorVo.getSecondSector());
                if(list21 == null || list21.size() == 0){
                    list21 = new ArrayList<StockHotSectorClass_VO>();
                    list21.add(companySectorVo);
                }else{
                    list21.add(companySectorVo);
                }
                secondMap.put(companySectorVo.getSecondSector() , list21);
            }

            /*************************************第 3 次*****************************************/
            Map<String , Map<String , Map<String ,List<StockHotSectorClass_VO>>>>  resultSecondMap = new HashMap<>();
            for (Map.Entry<String, List<StockHotSectorClass_VO>> entry2 : secondMap.entrySet()) {
                String key3 = entry2.getKey();
                List<StockHotSectorClass_VO> list3 = entry2.getValue();

                Map<String ,List<StockHotSectorClass_VO>> thirdMap = new HashMap<String ,List<StockHotSectorClass_VO>>();
                for(StockHotSectorClass_VO companySectorVo : list3){
                    List<StockHotSectorClass_VO> list31 = thirdMap.get(companySectorVo.getThirdSector());
                    if(list31 == null || list31.size() == 0){
                        list31 = new ArrayList<StockHotSectorClass_VO>();
                        list31.add(companySectorVo);
                    }else{
                        list31.add(companySectorVo);
                    }
                    thirdMap.put(companySectorVo.getThirdSector() , list31);
                }


                /*************************************第 4 次*****************************************/
                Map<String , Map<String ,List<StockHotSectorClass_VO>>>  resultThirdMap = new HashMap<>();
                for (Map.Entry<String, List<StockHotSectorClass_VO>> entry3 : thirdMap.entrySet()) {
                    String key4 = entry3.getKey();
                    List<StockHotSectorClass_VO> list4 = entry3.getValue();

                    Map<String ,List<StockHotSectorClass_VO>> forthMap = new HashMap<String ,List<StockHotSectorClass_VO>>();
                    for(StockHotSectorClass_VO companySectorVo : list4){
                        List<StockHotSectorClass_VO> list41 = forthMap.get(companySectorVo.getForthSector());
                        if(list41 == null || list41.size() == 0){
                            list41 = new ArrayList<StockHotSectorClass_VO>();
                            list41.add(companySectorVo);
                        }else{
                            list41.add(companySectorVo);
                        }
                        forthMap.put(companySectorVo.getForthSector() , list41);
                    }



                    Map<String ,List<StockHotSectorClass_VO>> result = new LinkedHashMap<>();
                    forthMap.entrySet().stream().sorted(Map.Entry.<String ,List<StockHotSectorClass_VO>>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
                    resultThirdMap.put(key4 + "_" + list4.size() , result);
                }

                Map<String , Map<String ,List<StockHotSectorClass_VO>>> result = new LinkedHashMap<>();
                resultThirdMap.entrySet().stream().sorted(Map.Entry.<String , Map<String ,List<StockHotSectorClass_VO>>>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
                resultSecondMap.put(key3 , result);

            }

            Map<String , Map<String , Map<String ,List<StockHotSectorClass_VO>>>> result = new LinkedHashMap<>();
            resultSecondMap.entrySet().stream().sorted(Map.Entry.<String , Map<String , Map<String ,List<StockHotSectorClass_VO>>>>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
            resultFirstMap.put(key2 , result);

        }
        Map<String , Map<String , Map<String , Map<String ,List<StockHotSectorClass_VO>>>>> result = new LinkedHashMap<>();
        resultFirstMap.entrySet().stream().sorted(Map.Entry.<String , Map<String , Map<String , Map<String ,List<StockHotSectorClass_VO>>>>>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    @LogRecord(name = "getStockCompanySector" ,description = "查询企业行业数据")
    @PostMapping("/getStockCompanySector")
    public Object getStockCompanySector(String stockCode){
        EntityWrapper<StockCompanySector> entityWrapper = new EntityWrapper();
        entityWrapper.where("stock_code = {0}" , stockCode).and("isvalid = 1");
        StockCompanySector stockCompanySector = stockCompanySectorService.selectOne(entityWrapper);
        return ResponseEntity.ok(MapSuccess("查询成功",stockCompanySector));
    }

    @LogRecord(name = "editStockCompanySector" ,description = "查询企业行业数据")
    @PostMapping("/editStockCompanySector")
    public Object editStockCompanySector(@RequestBody StockCompanySector body){
        EntityWrapper<StockCompanySector> entityWrapper = new EntityWrapper();
        entityWrapper.where("stock_code = {0}" , body.getStockCode());
        StockCompanySector stockCompanySector = stockCompanySectorService.selectOne(entityWrapper);
        stockCompanySector.setCompanyLevel(body.getCompanyLevel());
        stockCompanySector.setFiveSector(body.getFiveSector());
        stockCompanySector.setFiveOrder(body.getFiveOrder());
        stockCompanySector.setMainBusiness(body.getMainBusiness());
        stockCompanySector.setBelongFirstSecotr(body.getBelongFirstSecotr());
        stockCompanySector.setBelongSecondSector(body.getBelongSecondSector());
        stockCompanySector.setBelongThirdSector(body.getBelongThirdSector());
        stockCompanySector.setBelongForthSector(body.getBelongForthSector());
        stockCompanySector.setCompanyQuality(StringUtils.isBlank(body.getCompanyQuality()) ? " " : body.getCompanyQuality());
        return ResponseEntity.ok(stockCompanySectorService.updateById(stockCompanySector) ? MapSuccess("操作成功") : MapError("操作失败"));
    }

    @LogRecord(name = "getCompanyProfitGrowList" ,description = "查询企业利润数据")
    @PostMapping("/getCompanyProfitGrowList")
    public Object getCompanyProfitGrowList(String stockCode ,String season){
        List<StockCompanyProfitVO> list = stockCompanyProfitService.getCompanyProfitGrowList(stockCode);
        List<String> title = new ArrayList<>();

        List<BigDecimal> totalProfit = new ArrayList<>();
        List<BigDecimal> belongProfit = new ArrayList<>();

        List<BigDecimal> firstSeasonProfit = new ArrayList<>();
        List<BigDecimal> secondSeasonProfit = new ArrayList<>();
        List<BigDecimal> threeSeasonProfit = new ArrayList<>();
        List<BigDecimal> forthtSeasonProfit = new ArrayList<>();

        List<BigDecimal> totalProfitRate = new ArrayList<>();
        List<BigDecimal> belongProfitRate = new ArrayList<>();

        for(StockCompanyProfitVO profit : list){
            title.add(profit.getPublishYear());
            totalProfit.add(profit.getTotalProfit());
            belongProfit.add(profit.getBelongProfit());

            firstSeasonProfit.add(profit.getFirstSeasonProfit());
            secondSeasonProfit.add(profit.getSecondSeasonProfit());
            threeSeasonProfit.add(profit.getThirdSeasonProfit());
            forthtSeasonProfit.add(profit.getForthSeasonProfit());

            totalProfitRate.add(profit.getTotalProfitRate());
            belongProfitRate.add(profit.getBelongProfitRate());

        }

        JSONObject jsonObject = stockCompanyProfitLevel(list);

        Map<String,Object> resultMap = new HashedMap();
        resultMap.put("title",title);
        resultMap.put("totalProfit",totalProfit);
        resultMap.put("belongProfit",belongProfit);

        resultMap.put("firstProfitRate",firstSeasonProfit);
        resultMap.put("secondProfitRate",secondSeasonProfit);
        resultMap.put("threeProfitRate",threeSeasonProfit);
        resultMap.put("forthtProfitRate",forthtSeasonProfit);

        resultMap.put("totalProfitRate",totalProfitRate);
        resultMap.put("belongProfitRate",belongProfitRate);

        resultMap.put("profitLevel",jsonObject);
        return ResponseEntity.ok(MapSuccess("操作成功",resultMap));
    }


    private JSONObject stockCompanyProfitLevel(List<StockCompanyProfitVO> list){
        String nowYear = DateUtils.getYear();
        String sixYear = Integer.parseInt(nowYear) - 6 + "";
        String fiveYear = Integer.parseInt(nowYear) - 5 + "";
        String fourYear = Integer.parseInt(nowYear) - 4 + "";
        String threeYear = Integer.parseInt(nowYear) - 3 + "";
        String twoYear = Integer.parseInt(nowYear) - 2 + "";
        String oneYear = Integer.parseInt(nowYear) - 1 + "";

        int totalIsGrow = 0;
        int totalIsBelong = 0;
        int totalBelongOther = 0;
        int num = 0;
        for(StockCompanyProfitVO profit : list){
            if(sixYear.equals(profit.getPublishYear()) ||
                    fiveYear.equals(profit.getPublishYear()) ||
                    fourYear.equals(profit.getPublishYear()) ||
                    threeYear.equals(profit.getPublishYear()) ||
                    twoYear.equals(profit.getPublishYear()) ||
                    oneYear.equals(profit.getPublishYear())
                    ){
                if(profit.getIsGrow() == 1){
                    totalIsGrow += 1;
                }
                if(profit.getIsBelong() == 1){
                    totalIsBelong += 1;
                }
                if(profit.getBelongOther() == 1){
                    totalBelongOther += 1;
                }
                num +=1;
            }
        }

        JSONObject jsonObject = new JSONObject();
        BigDecimal isGrowRate = BigDecimal.ZERO;
        String isGrowLevel = "";
        BigDecimal isBlongRate = BigDecimal.ZERO;
        String isBlongLevel = "";
        String belongOtherLevel = "";
        BigDecimal belongOtherRate = BigDecimal.ZERO;
        String rightRightLevel = "";
        BigDecimal rightRightRate = BigDecimal.ZERO ;
        if(num > 0){
            isGrowRate = BigDecimal.valueOf(totalIsGrow).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            if(isGrowRate.compareTo(BigDecimal.valueOf(80)) >=0){
                isGrowLevel = "S";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(70)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(80)) < 0){
                isGrowLevel = "A";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(60)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(70)) < 0){
                isGrowLevel = "B";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(50)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(60)) < 0){
                isGrowLevel = "C";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(40)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(50)) < 0){
                isGrowLevel = "D";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(30)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(40)) < 0){
                isGrowLevel = "E";
            }else{
                isGrowLevel = "F";
            }

            isBlongRate = BigDecimal.valueOf(totalIsBelong).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            if(isBlongRate.compareTo(BigDecimal.valueOf(80)) >=0){
                isBlongLevel = "S";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(70)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(80)) < 0){
                isBlongLevel = "A";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(60)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(70)) < 0){
                isBlongLevel = "B";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(50)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(60)) < 0){
                isBlongLevel = "C";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(40)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(50)) < 0){
                isBlongLevel = "D";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(30)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(40)) < 0){
                isBlongLevel = "E";
            }else{
                isBlongLevel = "F";
            }

            belongOtherRate = BigDecimal.valueOf(totalBelongOther).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            if(belongOtherRate.compareTo(BigDecimal.valueOf(80)) >=0){
                belongOtherLevel = "S";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(70)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(80)) < 0){
                belongOtherLevel = "A";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(60)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(70)) < 0){
                belongOtherLevel = "B";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(50)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(60)) < 0){
                belongOtherLevel = "C";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(40)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(50)) < 0){
                belongOtherLevel = "D";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(30)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(40)) < 0){
                belongOtherLevel = "E";
            }else{
                belongOtherLevel = "F";
            }


            rightRightRate = (isGrowRate.multiply(BigDecimal.valueOf(0.6))).add(isBlongRate.multiply(BigDecimal.valueOf(0.3))).add(belongOtherRate.multiply(BigDecimal.valueOf(0.1))).setScale(2,BigDecimal.ROUND_HALF_UP);
            if(rightRightRate.compareTo(BigDecimal.valueOf(80)) >=0){
                rightRightLevel = "S";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(70)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(80)) < 0){
                rightRightLevel = "A";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(60)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(70)) < 0){
                rightRightLevel = "B";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(50)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(60)) < 0){
                rightRightLevel = "C";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(40)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(50)) < 0){
                rightRightLevel = "D";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(30)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(40)) < 0){
                rightRightLevel = "E";
            }else{
                rightRightLevel = "F";
            }
        }

        jsonObject.put("isGrowRate" , isGrowRate);
        jsonObject.put("isGrowLevel" , isGrowLevel);
        jsonObject.put("isBlongRate" , isBlongRate);
        jsonObject.put("isBlongLevel" , isBlongLevel);
        jsonObject.put("belongOtherRate" , belongOtherRate);
        jsonObject.put("belongOtherLevel" , belongOtherLevel);
        jsonObject.put("rightRightRate" , rightRightRate);
        jsonObject.put("rightRightLevel" , rightRightLevel);
        return jsonObject;
    }


    @LogRecord(name = "getCompanyAssetGrowList" ,description = "查询企业资产负债数据")
    @PostMapping("/getCompanyAssetGrowList")
    public Object getCompanyAssetGrowList(String stockCode ,String season){
        List<StockCompanyAssetVO> list = stockCompanyAssetService.getCompanyAssetGrowList(stockCode);
        List<String> title = new ArrayList<>();

        List<BigDecimal> totalAsset = new ArrayList<>();
        List<BigDecimal> totalDebt = new ArrayList<>();
        List<BigDecimal> pureAsset = new ArrayList<>();

        List<BigDecimal> debtAssetRate = new ArrayList<>();
        List<BigDecimal> pureAssetRate = new ArrayList<>();

        List<BigDecimal> totalFlowAssetRate = new ArrayList<>();
        List<BigDecimal> totalUnflowAssetRate = new ArrayList<>();
        List<BigDecimal> flowDebtRate = new ArrayList<>();
        List<BigDecimal> unflowDebtRate = new ArrayList<>();

        List<BigDecimal> useAssetRate = new ArrayList<>();
        List<BigDecimal> billAssetRate = new ArrayList<>();
        List<BigDecimal> flowOtherAssetRate = new ArrayList<>();

        List<BigDecimal> fixedAssetRate = new ArrayList<>();
        List<BigDecimal> investAssetRate = new ArrayList<>();
        List<BigDecimal> virtualAssetRate = new ArrayList<>();
        List<BigDecimal> deferredAssetRate = new ArrayList<>();
        List<BigDecimal> unflowOtherAssetRate = new ArrayList<>();

        List<BigDecimal> loanDebtRate = new ArrayList<>();
        List<BigDecimal> billDebtRate = new ArrayList<>();
        List<BigDecimal> payDebtRate = new ArrayList<>();
        List<BigDecimal> otherDebtRate = new ArrayList<>();

        List<BigDecimal> unflowLoanDebtRate = new ArrayList<>();
        List<BigDecimal> unflowPayDebtRate = new ArrayList<>();
        List<BigDecimal> unflowDeferredDebtRate = new ArrayList<>();
        List<BigDecimal> unflowOtherDebtRate = new ArrayList<>();

        for(StockCompanyAssetVO asset : list){
            title.add(asset.getPublishYear());
            totalAsset.add(asset.getTotalAsset());
            totalDebt.add(asset.getTotalDebt());
            pureAsset.add(asset.getPureAsset());

            debtAssetRate.add(asset.getDebtAssetRate());
            pureAssetRate.add(asset.getPureAssetRate());

            totalFlowAssetRate.add(asset.getTotalFlowAssetRate());
            totalUnflowAssetRate.add(asset.getTotalUnflowAssetRate());
            flowDebtRate.add(asset.getFlowDebtRate());
            unflowDebtRate.add(asset.getUnflowDebtRate());

            useAssetRate.add(asset.getUseAssetRate());
            billAssetRate.add(asset.getBillAssetRate());
            flowOtherAssetRate.add(asset.getFlowOtherAssetRate());

            fixedAssetRate.add(asset.getFixedAssetRate());
            investAssetRate.add(asset.getInvestAssetRate());
            virtualAssetRate.add(asset.getVirtualAssetRate());
            deferredAssetRate.add(asset.getDeferredAssetRate());
            unflowOtherAssetRate.add(asset.getUnflowOtherAssetRate());

            loanDebtRate.add(asset.getLoanDebtRate());
            billDebtRate.add(asset.getBillDebtRate());
            payDebtRate.add(asset.getPayDebtRate());
            otherDebtRate.add(asset.getOtherDebtRate());

            unflowLoanDebtRate.add(asset.getUnflowLoanDebtRate());
            unflowPayDebtRate.add(asset.getUnflowPayDebtRate());
            unflowDeferredDebtRate.add(asset.getUnflowDeferredDebtRate());
            unflowOtherDebtRate.add(asset.getUnflowOtherDebtRate());
        }

        JSONObject jsonObject = stockCompanyAssetLevel(list);

        Map<String,Object> resultMap = new HashedMap();
        resultMap.put("title",title);
        resultMap.put("totalAsset",totalAsset);
        resultMap.put("totalDebt",totalDebt);
        resultMap.put("pureAsset",pureAsset);

        resultMap.put("debtAssetRate",debtAssetRate);
        resultMap.put("pureAssetRate",pureAssetRate);

        resultMap.put("totalFlowAssetRate",totalFlowAssetRate);
        resultMap.put("totalUnflowAssetRate",totalUnflowAssetRate);
        resultMap.put("flowDebtRate",flowDebtRate);
        resultMap.put("unflowDebtRate",unflowDebtRate);

        resultMap.put("useAssetRate",useAssetRate);
        resultMap.put("billAssetRate",billAssetRate);
        resultMap.put("flowOtherAssetRate",flowOtherAssetRate);

        resultMap.put("fixedAssetRate",fixedAssetRate);
        resultMap.put("investAssetRate",investAssetRate);
        resultMap.put("virtualAssetRate",virtualAssetRate);
        resultMap.put("deferredAssetRate",deferredAssetRate);
        resultMap.put("unflowOtherAssetRate",unflowOtherAssetRate);

        resultMap.put("loanDebtRate",loanDebtRate);
        resultMap.put("billDebtRate",billDebtRate);
        resultMap.put("payDebtRate",payDebtRate);
        resultMap.put("otherDebtRate",otherDebtRate);

        resultMap.put("unflowLoanDebtRate",unflowLoanDebtRate);
        resultMap.put("unflowPayDebtRate",unflowPayDebtRate);
        resultMap.put("unflowDeferredDebtRate",unflowDeferredDebtRate);
        resultMap.put("unflowOtherDebtRate",unflowOtherDebtRate);
        resultMap.put("assetLevel",jsonObject);
        return ResponseEntity.ok(MapSuccess("操作成功",resultMap));
    }


    private JSONObject stockCompanyAssetLevel(List<StockCompanyAssetVO> list){
        String nowYear = DateUtils.getYear();
        String nineYear = Integer.parseInt(nowYear) - 9 + "";
        String eightYear = Integer.parseInt(nowYear) - 8 + "";
        String sevenYear = Integer.parseInt(nowYear) - 7 + "";
        String sixYear = Integer.parseInt(nowYear) - 6 + "";
        String fiveYear = Integer.parseInt(nowYear) - 5 + "";
        String fourYear = Integer.parseInt(nowYear) - 4 + "";
        String threeYear = Integer.parseInt(nowYear) - 3 + "";
        String twoYear = Integer.parseInt(nowYear) - 2 + "";
        String oneYear = Integer.parseInt(nowYear) - 1 + "";

        BigDecimal totalGrowLevel = BigDecimal.ZERO;
        BigDecimal totalAssetDebtLevel = BigDecimal.ZERO;
        BigDecimal totalAssetLevel = BigDecimal.ZERO;
        int num = 0;
        for(StockCompanyAssetVO assetVO : list){
            if(nineYear.equals(assetVO.getPublishYear()) ||
              eightYear.equals(assetVO.getPublishYear()) ||
              sevenYear.equals(assetVO.getPublishYear()) ||
              sixYear.equals(assetVO.getPublishYear()) ||
              fiveYear.equals(assetVO.getPublishYear()) ||
              fourYear.equals(assetVO.getPublishYear()) ||
              threeYear.equals(assetVO.getPublishYear()) ||
              twoYear.equals(assetVO.getPublishYear()) ||
              oneYear.equals(assetVO.getPublishYear()) ||
              nowYear.equals(assetVO.getPublishYear())){
                totalGrowLevel = totalGrowLevel.add(assetVO.getGrowLevel());
                totalAssetDebtLevel = totalAssetDebtLevel.add(assetVO.getAssetDebtLevel());
                totalAssetLevel = totalAssetLevel.add(assetVO.getAssetLevel());
                num +=1;
            }
        }
        BigDecimal averageGrowLevel = BigDecimal.ZERO;
        BigDecimal averageAssetDebtLevel = BigDecimal.ZERO;
        BigDecimal averageAssetLevel = BigDecimal.ZERO;
        if(num > 0){
            averageGrowLevel = totalGrowLevel.divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            averageAssetDebtLevel = totalAssetDebtLevel.divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            averageAssetLevel = totalAssetLevel.divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
        }

        JSONObject jsonObject = new JSONObject();

        String isGrowLevel = "";
        if(averageGrowLevel.compareTo(BigDecimal.valueOf(80)) >=0){
            isGrowLevel = "S";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(70)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(80)) < 0){
            isGrowLevel = "A";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(60)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(70)) < 0){
            isGrowLevel = "B";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(50)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(60)) < 0){
            isGrowLevel = "C";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(40)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(50)) < 0){
            isGrowLevel = "D";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(30)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(40)) < 0){
            isGrowLevel = "E";
        }else{
            isGrowLevel = "F";
        }
        jsonObject.put("averageGrowLevel" , averageGrowLevel);
        jsonObject.put("growLevel" , isGrowLevel);

        String assetDebtLevel = "";
        if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(80)) >=0){
            assetDebtLevel = "S";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(70)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(80)) < 0){
            assetDebtLevel = "A";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(60)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(70)) < 0){
            assetDebtLevel = "B";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(50)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(60)) < 0){
            assetDebtLevel = "C";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(40)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(50)) < 0){
            assetDebtLevel = "D";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(30)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(40)) < 0){
            assetDebtLevel = "E";
        }else{
            assetDebtLevel = "F";
        }
        jsonObject.put("averageAssetDebtLevel" , averageAssetDebtLevel);
        jsonObject.put("assetDebtLevel" , assetDebtLevel);

        String assetLevel = "";
        if(averageAssetLevel.compareTo(BigDecimal.valueOf(80)) >=0){
            assetLevel = "S";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(70)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(80)) < 0){
            assetLevel = "A";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(60)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(70)) < 0){
            assetLevel = "B";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(50)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(60)) < 0){
            assetLevel = "C";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(40)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(50)) < 0){
            assetLevel = "D";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(30)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(40)) < 0){
            assetLevel = "E";
        }else{
            assetLevel = "F";
        }
        jsonObject.put("averageAssetLevel" , averageAssetLevel);
        jsonObject.put("assetLevel" , assetLevel);


        BigDecimal assetDebtRate = (averageGrowLevel.multiply(BigDecimal.valueOf(0.7))).add(averageAssetDebtLevel.multiply(BigDecimal.valueOf(0.2))).add(averageAssetLevel.multiply(BigDecimal.valueOf(0.1))).setScale(2,BigDecimal.ROUND_HALF_UP);
        String assetDebtRateLevel = "";
        if(assetDebtRate.compareTo(BigDecimal.valueOf(80)) >=0){
            assetDebtRateLevel = "S";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(70)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(80)) < 0){
            assetDebtRateLevel = "A";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(60)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(70)) < 0){
            assetDebtRateLevel = "B";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(50)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(60)) < 0){
            assetDebtRateLevel = "C";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(40)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(50)) < 0){
            assetDebtRateLevel = "D";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(30)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(40)) < 0){
            assetDebtRateLevel = "E";
        }else{
            assetDebtRateLevel = "F";
        }
        jsonObject.put("assetDebtRate" , assetDebtRate);
        jsonObject.put("assetDebtRateLevel" , assetDebtRateLevel);
        return jsonObject;
    }


    @LogRecord(name = "getMyStockCompanySectorList" ,description = "查询自主公司分类列表")
    @PostMapping("/getMyStockCompanySectorList")
    public Object getMyStockCompanySectorList(String firstSector ,String sectorTypes ,String sectorFocus){
        List<MyStockCompanySector_VO> list = stockCompanySectorService.getMyStockCompanySectorList(firstSector , sectorTypes , sectorFocus);
        Map<String ,List<MyStockCompanySector_VO>> firstMap = new HashMap<String ,List<MyStockCompanySector_VO>>();
        for(MyStockCompanySector_VO companySectorVo : list){
            List<MyStockCompanySector_VO> list1 = firstMap.get(companySectorVo.getSecondSector());
            if(list1 == null || list1.size() == 0){
                list1 = new ArrayList<MyStockCompanySector_VO>();
                list1.add(companySectorVo);
            }else{
                list1.add(companySectorVo);
            }
            firstMap.put(companySectorVo.getSecondSector() , list1);
        }

        Map<String , Map<String , Map<String ,List<MyStockCompanySector_VO>>>>  resultFirstMap = new HashMap<String , Map<String , Map<String ,List<MyStockCompanySector_VO>>>>();

        for (Map.Entry<String, List<MyStockCompanySector_VO>> entry : firstMap.entrySet()) {
            String key = entry.getKey() ;
            List<MyStockCompanySector_VO> list2 = entry.getValue();

            Map<String ,List<MyStockCompanySector_VO>> secondMap = new HashMap<String ,List<MyStockCompanySector_VO>>();
            for(MyStockCompanySector_VO companySectorVo : list2){
                List<MyStockCompanySector_VO> list21 = secondMap.get(companySectorVo.getThirdSector());
                if(list21 == null || list21.size() == 0){
                    list21 = new ArrayList<MyStockCompanySector_VO>();
                    list21.add(companySectorVo);
                }else{
                    list21.add(companySectorVo);
                }
                secondMap.put(companySectorVo.getThirdSector() , list21);
            }


            Map<String , Map<String ,List<MyStockCompanySector_VO>>>  resultSecondMap = new HashMap<String , Map<String ,List<MyStockCompanySector_VO>>>();

            for (Map.Entry<String, List<MyStockCompanySector_VO>> entry2 : secondMap.entrySet()) {
                String key2 = entry2.getKey() ;
                List<MyStockCompanySector_VO> list3 = entry2.getValue();

                Map<String ,List<MyStockCompanySector_VO>> thirdMap = new HashMap<String ,List<MyStockCompanySector_VO>>();
                for(MyStockCompanySector_VO companySectorVo : list3){
                    List<MyStockCompanySector_VO> list31 = thirdMap.get(companySectorVo.getForthSector());
                    if(list31 == null || list31.size() == 0){
                        list31 = new ArrayList<MyStockCompanySector_VO>();
                        list31.add(companySectorVo);
                    }else{
                        list31.add(companySectorVo);
                    }
                    thirdMap.put(companySectorVo.getForthSector() , list31);
                }

                Map<String ,List<MyStockCompanySector_VO>> result = new LinkedHashMap<>();
                thirdMap.entrySet().stream().sorted(Map.Entry.<String ,List<MyStockCompanySector_VO>>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
                resultSecondMap.put(key2 + "_" + list3.size() , result);
            }


            Map<String , Map<String ,List<MyStockCompanySector_VO>>> result = new LinkedHashMap<>();
            resultSecondMap.entrySet().stream().sorted(Map.Entry.<String , Map<String ,List<MyStockCompanySector_VO>>>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
            resultFirstMap.put(key , result);
        }


        Map<String , Map<String , Map<String ,List<MyStockCompanySector_VO>>>> result = new LinkedHashMap<>();

        resultFirstMap.entrySet().stream().sorted(Map.Entry.<String , Map<String , Map<String ,List<MyStockCompanySector_VO>>>>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));

        return result;
    }

}
