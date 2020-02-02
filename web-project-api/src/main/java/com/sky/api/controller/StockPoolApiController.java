package com.sky.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.core.utils.StringUtils;
import com.sky.model.*;
import com.sky.vo.*;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ThinkPad on 2019/8/31.
 */
@RestController
@RequestMapping("/api/stockPool")
public class StockPoolApiController extends AbstractController {

    @LogRecord(name = "getStockPoolClassTree" ,description = "查询股票池树状图")
    @PostMapping("/getStockPoolClassTree")
    public Object getStockPoolClassTree(){
        List<StockPoolClass> list = stockPoolClassService.selectList(new EntityWrapper<StockPoolClass>().where("isvalid = 1").orderBy("pool_code asc").orderBy("pool_sore asc"));
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        Iterator<StockPoolClass> iterator = list.iterator();
        while (iterator.hasNext()){
            StockPoolClass poolClass = iterator.next();
            if(poolClass.getParentCode().equals("1000000")){
                TreeNode treeNode = new TreeNode(false ,false ,false ,false);
                treeNode.setText(poolClass.getPoolName());
                treeNode.setCode(poolClass.getPoolCode());
                treeNode.setParentCode(poolClass.getParentCode());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        for(TreeNode treeNode : nodeList){
            getStoolFirstList(list , treeNode);
        }
        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }

    private void getStoolFirstList(List<StockPoolClass> list , TreeNode node){
        Iterator<StockPoolClass> iterator = list.iterator();
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        while (iterator.hasNext()){
            StockPoolClass poolClass = iterator.next();
            if(poolClass.getParentCode().equals(node.getCode())){
                TreeNode treeNode = new TreeNode(false ,false ,true ,false);
                treeNode.setText(poolClass.getPoolName());
                treeNode.setCode(poolClass.getPoolCode());
                treeNode.setParentCode(poolClass.getParentCode());
                treeNode.setHref("/api/stockPool/getStockPoolSecondClassTree?poolCode="+ poolClass.getPoolCode() + "&poolName=" + poolClass.getPoolName() + "&parentCode=" + poolClass.getParentCode());
                nodeList.add(treeNode);
                iterator.remove();
            }
        }
        node.setNodes(nodeList);
        for(TreeNode treeNode : nodeList){
            getStoolFirstList(list , treeNode);
        }
    }

    @LogRecord(name = "getStockPoolSecondClassTree" ,description = "查询股票池二级树状图")
    @PostMapping("/getStockPoolSecondClassTree")
    public Object getStockPoolSecondClassTree(String poolCode ,String poolName ,String parentCode){
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        TreeNode treeNode = new TreeNode(false ,false ,true ,false);
        treeNode.setText(poolName);
        treeNode.setCode(poolCode);
        treeNode.setParentCode(parentCode);
        List<StockPoolSecondClass> list = stockPoolSecondClassService.selectList(new EntityWrapper<StockPoolSecondClass>().where("isvalid = 1 and first_code = {0}",poolCode).orderBy("second_code asc").orderBy("scond_sore asc"));
        List<TreeNode> nodeList2 = new ArrayList<TreeNode>();
        for(StockPoolSecondClass secondClass : list){
            TreeNode treeNodes = new TreeNode(false ,false ,true ,false);
            treeNodes.setText(secondClass.getSecondName());
            treeNodes.setCode(secondClass.getSecondCode());
            treeNodes.setParentCode(secondClass.getFirstCode());
            treeNodes.setHref("/api/stock/getStockCompanyBaseList?stockSector=" + poolName + "&middleContrySector=" + secondClass.getSecondName());
            nodeList2.add(treeNodes);
        }
        treeNode.setNodes(nodeList2);
        nodeList.add(treeNode);
        return ResponseEntity.ok(MapSuccess("查询成功", nodeList));
    }

    @LogRecord(name = "getStockCompanyPoolList" ,description = "查询股票池列表")
    @PostMapping("/getStockCompanyPoolList")
    public Object getStockCompanyPoolList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                            @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                            String stockCode ,
                                            String firstSector ,
                                            String secondSector ,
                                            String thirdSecotor ,
                                            String forthSector ,
                                            String companyLevel){
        Page selectedPage = stockCompanySectorService.getStockCompanyPoolList(page ,size , stockCode, firstSector, secondSector, thirdSecotor, forthSector, companyLevel);
        return PageData(selectedPage);
    }

    @LogRecord(name = "getStockProfitIncreaseList" ,description = "查询股票增长率列表")
    @PostMapping("/getStockProfitIncreaseList")
    public Object getStockProfitIncreaseList(@RequestParam(required = false, defaultValue = PAGE_NUM) Integer page,
                                             @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                             String stockCode ,
                                             String stockName ,
                                             String firstSector ,
                                             String secondSector ,
                                             String thirdSecotor ,
                                             String forthSector ,
                                             String yearType){
        Page<StockProfitAvaregeRate_VO> list = stockProfitAvaregeRateService.getStockProfitAvaregeRateList(page ,size , stockCode, stockName , firstSector, secondSector, thirdSecotor, forthSector, yearType);
        return PageData(list);
    }

    @LogRecord(name = "stockPoolDetail" ,description = "查询股票基本信息")
    @PostMapping("/stockPoolDetail")
    public Object stockPoolDetail(String stockCode){
        Page<CreateCompanyWorld_VO> page = stockCompanySectorService.getStockCompanyPoolList(1 ,1 , stockCode, null, null, null, null, null);
        List<CreateCompanyWorld_VO> list = page.getRecords();
        CreateCompanyWorld_VO world_vo = new CreateCompanyWorld_VO();
        if(list.size() > 0){
            world_vo = list.get(0);
            List<StockCompanyProduct> productList = stockCompanyProductService.getNewCompanyProductList("产品分类" , stockCode);

            List<StockCompanyProduct> regionList = stockCompanyProductService.getNewCompanyProductList("地域分类" , stockCode);


            int num = productList.size();
            if(regionList.size() > productList.size()){
                num = regionList.size();
            }

            if(productList.size() == 0){
                for(StockCompanyProduct region : regionList){
                    StockCompanyProduct empity = new StockCompanyProduct();
                    empity.setProductName("  ");
                    empity.setProductRevenueRate("   ");
                    productList.add(empity);
                }
            }
            if(regionList.size() == 0){
                for(StockCompanyProduct product : productList){
                    StockCompanyProduct empity = new StockCompanyProduct();
                    empity.setProductName("  ");
                    empity.setProductRevenueRate("   ");
                    regionList.add(empity);
                }
            }

            world_vo.setRowSpan(num);

            world_vo.setProductList(productList);
            world_vo.setRegionList(regionList);

            String marketOrder = world_vo.getMarketOrder();
            if(StringUtils.isNotBlank(marketOrder)){
                String[] strs = marketOrder.split("->");
                Set<String> set = new HashSet<String>();
                for(String str : strs){
                    set.add(str);
                }
                StringBuilder builder = new StringBuilder();
                int i = 0 ;
                for(String str : set){
                    if(i < set.size() - 1){
                        builder.append(str);
                        builder.append("->");
                    }else{
                        builder.append(str);
                    }
                    i += 1;
                }
                world_vo.setMarketOrder(builder.toString());
            }

            String companyChance = world_vo.getCompanyChance();
            List<CompanyChance_VO> changeList = new ArrayList<>();
            List<CompanyChance_VO> changeRList = new ArrayList<>();
            if(StringUtils.isNotBlank(companyChance)){
                String[] strs = companyChance.split("->");
                for(String str : strs){
                    CompanyChance_VO chance_vo = new CompanyChance_VO();
                    if(str.indexOf("R") != -1){
                       String[] indexs = str.split("R");
                        chance_vo.setIndex(indexs[0]);
                        chance_vo.setIndentity("R");
                        chance_vo.setChance(indexs[1]);
                        changeRList.add(chance_vo);
                    }else {
                        chance_vo.setIndex(str.substring(0,str.length() - 2));
                        chance_vo.setIndentity("");
                        chance_vo.setChance(str.substring(str.length() - 2,str.length()));
                        changeList.add(chance_vo);
                    }
                }
                System.out.println(changeRList.toString());
                System.out.println(changeList.toString());

                List<CompanyChance_VO> changeNewList = new ArrayList<>();
                for(CompanyChance_VO chance_vo1 : changeRList){
                    boolean just = false ;
                    for(CompanyChance_VO chance_vo2 : changeList){
                        if(chance_vo1.getIndex().equals(chance_vo2.getIndex()) && chance_vo1.getChance().equals(chance_vo2.getChance())){
                            just = true ;
                        }
                    }
                    if(!just){
                        changeNewList.add(chance_vo1);
                    }
                }

                changeNewList.addAll(changeList);
                StringBuilder builder = new StringBuilder();
                int i = 0 ;
                for(CompanyChance_VO chance_vo : changeNewList){
                    if(i < changeNewList.size() - 1){
                        builder.append(chance_vo.getIndex());
                        builder.append(chance_vo.getIndentity());
                        builder.append(chance_vo.getChance());
                        builder.append("->");
                    }else{
                        builder.append(chance_vo.getIndex());
                        builder.append(chance_vo.getIndentity());
                        builder.append(chance_vo.getChance());
                    }
                    i += 1;
                }
                world_vo.setCompanyChance(builder.toString());
            }

            String groupHot = world_vo.getGroupHot();
            if(StringUtils.isNotBlank(groupHot) && groupHot.indexOf("(") != -1){
                String hot = groupHot.substring(0 , groupHot.indexOf("("));
                String index = groupHot.substring(groupHot.indexOf("(") + 1, groupHot.indexOf(")"));
                String[] strs = index.split("->");
                List<CompanyChance_VO> hotList = new ArrayList<>();
                for(String str : strs){
                    CompanyChance_VO companyChance_vo = new CompanyChance_VO();
                    if(str.indexOf("产业") != -1){
                        companyChance_vo.setOrder(1);
                        companyChance_vo.setIndex("");
                        companyChance_vo.setIndentity(str.substring(0,2));
                        companyChance_vo.setChance(str.substring(2,str.length()));
                        hotList.add(companyChance_vo);
                    }
                    if(str.indexOf("中证") != -1){
                        companyChance_vo.setOrder(2);
                        companyChance_vo.setIndex(str.substring(0,2));
                        companyChance_vo.setIndentity(str.substring(2,str.length()));
                        companyChance_vo.setChance("");
                        hotList.add(companyChance_vo);
                    }
                    if(str.indexOf("上证") != -1){
                        companyChance_vo.setOrder(3);
                        companyChance_vo.setIndex(str.substring(0,2));
                        companyChance_vo.setIndentity(str.substring(2,str.length()));
                        companyChance_vo.setChance("");
                        hotList.add(companyChance_vo);
                    }
                }
//                System.out.println(hotList.toString());
                Map<String, List<CompanyChance_VO>> resultMap = hotList.stream().collect(Collectors.groupingBy(CompanyChance_VO::getIndentity));
                Iterator<Map.Entry<String, List<CompanyChance_VO>>> iterator = resultMap.entrySet().iterator();

                List<CompanyChance_VO> newVoList = new ArrayList<>();
                while(iterator.hasNext()){
                    Map.Entry<String, List<CompanyChance_VO>> entry = iterator.next();
                    List<CompanyChance_VO> voList = entry.getValue();
                    Collections.sort(voList, (a,b) -> a.getOrder().compareTo(b.getOrder()));
                    newVoList.add(voList.get(0));
                }
                Collections.sort(newVoList, (a,b) -> a.getIndex().compareTo(b.getIndex()));
                StringBuilder builder = new StringBuilder();
                for(int i = 0 ; i < newVoList.size() ; i ++){
                    if(i < newVoList.size() - 1){
                        builder.append(newVoList.get(i).getIndex());
                        builder.append(newVoList.get(i).getIndentity());
                        builder.append(newVoList.get(i).getChance());
                        builder.append("->");
                    }else{
                        builder.append(newVoList.get(i).getIndex());
                        builder.append(newVoList.get(i).getIndentity());
                        builder.append(newVoList.get(i).getChance());
                    }
                }
                world_vo.setGroupHot(hot + "(" + builder.toString() + ")");
            }




            List<StockCompanyProfitVO> profitList = stockCompanyProfitService.getCompanyProfitGrowList(stockCode);
            List<String> profitTitle = new ArrayList<>();

            List<BigDecimal> totalProfit = new ArrayList<>();
            List<BigDecimal> belongProfit = new ArrayList<>();

            List<BigDecimal> firstSeasonProfit = new ArrayList<>();
            List<BigDecimal> secondSeasonProfit = new ArrayList<>();
            List<BigDecimal> threeSeasonProfit = new ArrayList<>();
            List<BigDecimal> forthtSeasonProfit = new ArrayList<>();

            for(StockCompanyProfitVO profit : profitList){
                profitTitle.add(profit.getPublishYear());
                totalProfit.add(profit.getTotalProfit());
                belongProfit.add(profit.getBelongProfit());

                firstSeasonProfit.add(profit.getFirstSeasonProfit());
                secondSeasonProfit.add(profit.getSecondSeasonProfit());
                threeSeasonProfit.add(profit.getThirdSeasonProfit());
                forthtSeasonProfit.add(profit.getForthSeasonProfit());
            }

            Map<String,Object> profitMap = new HashedMap();
            profitMap.put("profitTitle",profitTitle);
            profitMap.put("totalProfit",totalProfit);
            profitMap.put("belongProfit",belongProfit);

            profitMap.put("firstProfitRate",firstSeasonProfit);
            profitMap.put("secondProfitRate",secondSeasonProfit);
            profitMap.put("threeProfitRate",threeSeasonProfit);
            profitMap.put("forthtProfitRate",forthtSeasonProfit);

            world_vo.setProfitMap(profitMap);

            List<StockCompanyAssetVO> assetList = stockCompanyAssetService.getCompanyAssetGrowList(stockCode);
            List<String> assetTitle = new ArrayList<>();

            List<BigDecimal> totalAsset = new ArrayList<>();
            List<BigDecimal> totalDebt = new ArrayList<>();
            List<BigDecimal> pureAsset = new ArrayList<>();
            for(StockCompanyAssetVO asset : assetList){
                assetTitle.add(asset.getPublishYear());
                totalAsset.add(asset.getTotalAsset());
                totalDebt.add(asset.getTotalDebt());
                pureAsset.add(asset.getPureAsset());
            }

            Map<String,Object> assetMap = new HashedMap();
            assetMap.put("assetTitle",assetTitle);
            assetMap.put("totalAsset",totalAsset);
            assetMap.put("totalDebt",totalDebt);
            assetMap.put("pureAsset",pureAsset);

            world_vo.setAssetMap(assetMap);

            StockRiseRate riseRate = stockRiseRateService.selectOne(new EntityWrapper<StockRiseRate>().where("stock_code = {0} and deal_period = 3 and start_time = '2015-01-01'" , stockCode));
            Map<String ,JSONArray> map = new HashMap<>();
            JSONArray rateArr = new JSONArray();
            JSONArray upperArr = new JSONArray();
            JSONArray shockArr = new JSONArray();
            rateArr.add(riseRate.getOneRise());
            rateArr.add(riseRate.getTowRise());
            rateArr.add(riseRate.getThreeRise());
            rateArr.add(riseRate.getFourRise());
            rateArr.add(riseRate.getFiveRise());
            rateArr.add(riseRate.getSixRise());
            rateArr.add(riseRate.getSevenRise());
            rateArr.add(riseRate.getEightRise());
            rateArr.add(riseRate.getNineRise());
            rateArr.add(riseRate.getTenRise());
            rateArr.add(riseRate.getElevenRise());
            rateArr.add(riseRate.getTwelveRise());

            upperArr.add(riseRate.getOneAmplitude());
            upperArr.add(riseRate.getTowAmplitude());
            upperArr.add(riseRate.getThreeAmplitude());
            upperArr.add(riseRate.getFourAmplitude());
            upperArr.add(riseRate.getFiveAmplitude());
            upperArr.add(riseRate.getSixAmplitude());
            upperArr.add(riseRate.getSevenAmplitude());
            upperArr.add(riseRate.getEightAmplitude());
            upperArr.add(riseRate.getNineAmplitude());
            upperArr.add(riseRate.getTenAmplitude());
            upperArr.add(riseRate.getElevenAmplitude());
            upperArr.add(riseRate.getTwelveAmplitude());

            shockArr.add(riseRate.getOneShock());
            shockArr.add(riseRate.getTowShock());
            shockArr.add(riseRate.getThreeShock());
            shockArr.add(riseRate.getFourShock());
            shockArr.add(riseRate.getFiveShock());
            shockArr.add(riseRate.getSixShock());
            shockArr.add(riseRate.getSevenShock());
            shockArr.add(riseRate.getEightShock());
            shockArr.add(riseRate.getNineShock());
            shockArr.add(riseRate.getTenShock());
            shockArr.add(riseRate.getElevenShock());
            shockArr.add(riseRate.getTwelveShock());
            map.put("rateArr" , rateArr);
            map.put("upperArr" , upperArr);
            map.put("shockArr" , shockArr);

            world_vo.setCycleMap(map);

            StockRiseRate riseRate2 = stockRiseRateService.selectOne(new EntityWrapper<StockRiseRate>().where("stock_code = {0} and deal_period = 3 and start_time = '2010-01-01'" , stockCode));
            Map<String ,JSONArray> map2 = new HashMap<>();
            JSONArray rateArr2 = new JSONArray();
            JSONArray upperArr2 = new JSONArray();
            JSONArray shockArr2 = new JSONArray();
            rateArr2.add(riseRate2.getOneRise());
            rateArr2.add(riseRate2.getTowRise());
            rateArr2.add(riseRate2.getThreeRise());
            rateArr2.add(riseRate2.getFourRise());
            rateArr2.add(riseRate2.getFiveRise());
            rateArr2.add(riseRate2.getSixRise());
            rateArr2.add(riseRate2.getSevenRise());
            rateArr2.add(riseRate2.getEightRise());
            rateArr2.add(riseRate2.getNineRise());
            rateArr2.add(riseRate2.getTenRise());
            rateArr2.add(riseRate2.getElevenRise());
            rateArr2.add(riseRate2.getTwelveRise());

            upperArr2.add(riseRate2.getOneAmplitude());
            upperArr2.add(riseRate2.getTowAmplitude());
            upperArr2.add(riseRate2.getThreeAmplitude());
            upperArr2.add(riseRate2.getFourAmplitude());
            upperArr2.add(riseRate2.getFiveAmplitude());
            upperArr2.add(riseRate2.getSixAmplitude());
            upperArr2.add(riseRate2.getSevenAmplitude());
            upperArr2.add(riseRate2.getEightAmplitude());
            upperArr2.add(riseRate2.getNineAmplitude());
            upperArr2.add(riseRate2.getTenAmplitude());
            upperArr2.add(riseRate2.getElevenAmplitude());
            upperArr2.add(riseRate2.getTwelveAmplitude());

            shockArr2.add(riseRate2.getOneShock());
            shockArr2.add(riseRate2.getTowShock());
            shockArr2.add(riseRate2.getThreeShock());
            shockArr2.add(riseRate2.getFourShock());
            shockArr2.add(riseRate2.getFiveShock());
            shockArr2.add(riseRate2.getSixShock());
            shockArr2.add(riseRate2.getSevenShock());
            shockArr2.add(riseRate2.getEightShock());
            shockArr2.add(riseRate2.getNineShock());
            shockArr2.add(riseRate2.getTenShock());
            shockArr2.add(riseRate2.getElevenShock());
            shockArr2.add(riseRate2.getTwelveShock());
            map2.put("rateArr" , rateArr2);
            map2.put("upperArr" , upperArr2);
            map2.put("shockArr" , shockArr2);

            world_vo.setCycleTenMap(map2);
        }
        return ResponseEntity.ok(MapSuccess("查询成功", world_vo));
    }

    @LogRecord(name = "stockPoolInvestor" ,description = "查询股票投资者信息")
    @PostMapping("/stockPoolInvestor")
    public Object stockPoolInvestor(String stockCode){
            Map<String,Object> profitMap = new HashedMap();

            List<StockInvestor_VO> investorList = stockInvestorProductService.getStockInvestorStaticList(stockCode);

            List<String> investTitle = new ArrayList<>();
            List<JSONObject> investNum = new ArrayList<>();
            List<JSONObject> investCount = new ArrayList<>();

            for(StockInvestor_VO invest : investorList){
                investTitle.add(invest.getTypeName());
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name" , invest.getTypeName());
                jsonObject.put("value" , invest.getInvestorNum());
                investNum.add(jsonObject);
                JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("name" , invest.getTypeName());
                jsonObject2.put("value" , invest.getInvestCount());
                investCount.add(jsonObject2);
            }

            profitMap.put("investorTitle",investTitle);
            profitMap.put("investorArr",investNum);
            profitMap.put("investorCount",investCount);

            Map<String , List<StockInvestor_VO>> listMap = new HashMap<>();
            List<StockInvestor_VO> investDayList = stockInvestorProductService.getStockInvestorList(stockCode);
            for(StockInvestor_VO investor_vo : investDayList){
                List<StockInvestor_VO> investList = listMap.get(investor_vo.getTypeCode());
                if(investList == null || investList.size() == 0){
                    List<StockInvestor_VO> list = new ArrayList<>();
                    list.add(investor_vo);
                    listMap.put(investor_vo.getTypeCode(),list);
                }else{
                    investList.add(investor_vo);
                    listMap.put(investor_vo.getTypeCode(),investList);
                }
            }
        List<String> investTime = new ArrayList<>();
        List<JSONObject> lineList = new ArrayList<>();
        for (List<StockInvestor_VO> list : listMap.values()) {
            list.sort((a, b) -> a.getStaticTime().compareTo(b.getStaticTime()));

            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for(int i = 0 ; i < list.size() ; i++){
                StockInvestor_VO investor_vo = list.get(i);
                jsonArray.add(investor_vo.getInvestCount());
                if(investTime.size() < list.size()){
                    investTime.add(investor_vo.getStaticTime());
                }
                if(i == 0){
                    jsonObject.put("name" , investor_vo.getTypeName());
                    jsonObject.put("type" , "line");
                    jsonObject.put("stack" , "总量" + investor_vo.getTypeCode());
                }
            }
            jsonObject.put("data" , jsonArray);
            lineList.add(jsonObject);
        }
        profitMap.put("investTime",investTime);
        profitMap.put("lineList",lineList);


        return ResponseEntity.ok(MapSuccess("查询成功", profitMap));
    }
}
