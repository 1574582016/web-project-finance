package com.sky.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sky.annotation.LogRecord;
import com.sky.api.AbstractController;
import com.sky.core.model.TreeNode;
import com.sky.model.*;
import com.sky.vo.CreateCompanyWorld_VO;
import com.sky.vo.StockCompanyAssetVO;
import com.sky.vo.StockCompanyProfitVO;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

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

    @LogRecord(name = "stockPoolDetail" ,description = "查询股票池二级树状图")
    @PostMapping("/stockPoolDetail")
    public Object stockPoolDetail(String stockCode){
        Page<CreateCompanyWorld_VO> page = stockCompanySectorService.getStockCompanyPoolList(1 ,1 , stockCode, null, null, null, null, null);
        List<CreateCompanyWorld_VO> list = page.getRecords();
        CreateCompanyWorld_VO world_vo = new CreateCompanyWorld_VO();
        if(list.size() > 0){
            world_vo = list.get(0);
            List<StockCompanyProduct> productList = stockCompanyProductService.getNewCompanyProductList("产品分类" , stockCode);

            List<StockCompanyProduct> regionList = stockCompanyProductService.getNewCompanyProductList("地域分类" , stockCode);

            world_vo.setProductList(productList);
            world_vo.setRegionList(regionList);


            List<StockCompanyProfitVO> profitList = stockCompanyProfitService.getCompanyProfitGrowList(stockCode);
            List<String> profitTitle = new ArrayList<>();

            List<BigDecimal> totalProfit = new ArrayList<>();
            List<BigDecimal> mainBusinessProfit = new ArrayList<>();
            List<BigDecimal> viceBusinessProfit = new ArrayList<>();
            List<BigDecimal> belongProfit = new ArrayList<>();

            List<BigDecimal> firstProfitRate = new ArrayList<>();
            List<BigDecimal> secondProfitRate = new ArrayList<>();
            List<BigDecimal> threeProfitRate = new ArrayList<>();
            List<BigDecimal> forthtProfitRate = new ArrayList<>();

            for(StockCompanyProfitVO profit : profitList){
                profitTitle.add(profit.getPublishYear());
                totalProfit.add(profit.getTotalProfit());
                mainBusinessProfit.add(profit.getMainBusinessProfit());
                viceBusinessProfit.add(profit.getViceBusinessProfit());
                belongProfit.add(profit.getBelongProfit());

                firstProfitRate.add(profit.getFirstProfitRate());
                secondProfitRate.add(profit.getSecondProfitRate());
                threeProfitRate.add(profit.getThreeProfitRate());
                forthtProfitRate.add(profit.getForthtProfitRate());
            }

            Map<String,Object> profitMap = new HashedMap();
            profitMap.put("profitTitle",profitTitle);
            profitMap.put("totalProfit",totalProfit);
            profitMap.put("mainBusinessProfit",mainBusinessProfit);
            profitMap.put("viceBusinessProfit",viceBusinessProfit);
            profitMap.put("belongProfit",belongProfit);

            profitMap.put("firstProfitRate",firstProfitRate);
            profitMap.put("secondProfitRate",secondProfitRate);
            profitMap.put("threeProfitRate",threeProfitRate);
            profitMap.put("forthtProfitRate",forthtProfitRate);

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

            StockRiseRate riseRate = stockRiseRateService.selectOne(new EntityWrapper<StockRiseRate>().where("stock_code = {0} and deal_period = 3" , stockCode));
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
        }
        return ResponseEntity.ok(MapSuccess("查询成功", world_vo));
    }
}
