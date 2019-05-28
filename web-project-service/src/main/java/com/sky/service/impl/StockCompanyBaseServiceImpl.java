package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.SpiderUtils;
import com.sky.mapper.StockCodeMapper;
import com.sky.mapper.StockCompanyBaseMapper;
import com.sky.model.StockCode;
import com.sky.model.StockCompanyAnalyse;
import com.sky.model.StockCompanyBase;
import com.sky.model.StockCompanyProduct;
import com.sky.service.StockCodeService;
import com.sky.service.StockCompanyAnalyseService;
import com.sky.service.StockCompanyBaseService;
import com.sky.service.StockCompanyProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThinkPad on 2019/5/27.
 */
@Service
@Transactional
public class StockCompanyBaseServiceImpl extends ServiceImpl<StockCompanyBaseMapper,StockCompanyBase> implements StockCompanyBaseService {

    @Autowired
    private StockCodeService stockCodeService;

    @Autowired
    private StockCompanyAnalyseService stockCompanyAnalyseService ;

    @Autowired
    private StockCompanyProductService stockCompanyProductService ;

    @Override
    public void spiderStockCompanyBase(String url ,String sector) {
        EntityWrapper<StockCode> entityWrapper = new EntityWrapper();
        entityWrapper.where("stock_sector = {0}" , sector);
        List<StockCode> codeList = stockCodeService.selectList(entityWrapper);
        List<StockCompanyBase> companyList = new ArrayList<StockCompanyBase>();
        List<StockCompanyAnalyse> analyseList = new ArrayList();
        List<StockCompanyProduct> productList = new ArrayList();
        for(StockCode stockCode : codeList){
            String companyUrl = "http://f10.eastmoney.com/CompanySurvey/CompanySurveyAjax?code=" + stockCode.getStockMarket() + stockCode.getStockCode();
            String subjectUrl = "http://f10.eastmoney.com/CoreConception/CoreConceptionAjax?code=" + stockCode.getStockMarket() + stockCode.getStockCode();
            String productUrl = "http://f10.eastmoney.com/BusinessAnalysis/BusinessAnalysisAjax?code=" + stockCode.getStockMarket() + stockCode.getStockCode();
            String companyString = SpiderUtils.HttpClientBuilderGet(companyUrl);
            JSONObject companyObject = JSON.parseObject(companyString);
            JSONObject baseObject = companyObject.getJSONObject("jbzl");
            JSONObject expendObject = companyObject.getJSONObject("fxxg");
            StockCompanyBase stockCompanyBase = new StockCompanyBase();
//            System.out.println("====================" + companyUrl);
//            System.out.println("====================" + baseObject);
            stockCompanyBase.setCompanyName(baseObject.getString("gsmc"));
            stockCompanyBase.setEnglishName(baseObject.getString("ywmc"));
            stockCompanyBase.setLastName(baseObject.getString("cym"));
            stockCompanyBase.setStockACode(baseObject.getString("agdm"));
            stockCompanyBase.setStockAName(baseObject.getString("agjc"));
            stockCompanyBase.setStockBCode(baseObject.getString("bgdm"));
            stockCompanyBase.setStockBName(baseObject.getString("bgjc"));
            stockCompanyBase.setStockHCode(baseObject.getString("hgdm"));
            stockCompanyBase.setStockHName(baseObject.getString("hgjc"));
            stockCompanyBase.setStockPlate(baseObject.getString("zqlb"));
            stockCompanyBase.setStockSector(baseObject.getString("sshy"));
            stockCompanyBase.setContrySector(baseObject.getString("sszjhhy"));
            stockCompanyBase.setStockExchange(baseObject.getString("ssjys"));
            stockCompanyBase.setCompanyManager(baseObject.getString("zjl"));
            stockCompanyBase.setCompanyLegalPerson(baseObject.getString("frdb"));
            stockCompanyBase.setCompanyChairmanSecretary(baseObject.getString("dm"));
            stockCompanyBase.setCompanyChairman(baseObject.getString("dsz"));
            stockCompanyBase.setCompanyStockRepresent(baseObject.getString("zqswdb"));
            stockCompanyBase.setCompanyIndependentDirector(baseObject.getString("dlds"));
            stockCompanyBase.setCompanyTelephone(baseObject.getString("lxdh"));
            stockCompanyBase.setCompanyEmail(baseObject.getString("dzxx"));
            stockCompanyBase.setCompanyFax(baseObject.getString("cz"));
            stockCompanyBase.setCompanyPostCode(baseObject.getString("yzbm"));
            stockCompanyBase.setCompanyWebsite(baseObject.getString("gswz"));
            stockCompanyBase.setCompanyAddress(baseObject.getString("bgdz"));
            stockCompanyBase.setCompanyRegistAddress(baseObject.getString("zcdz"));
            stockCompanyBase.setCompanyRegion(baseObject.getString("qy"));
            stockCompanyBase.setCompanyRegistMoney(baseObject.getString("zczb"));
            stockCompanyBase.setIndustryCommerceCode(baseObject.getString("gsdj"));
            stockCompanyBase.setCompanyEmployee(baseObject.getString("gyrs"));
            stockCompanyBase.setCompanyEmployer(baseObject.getString("glryrs"));
            stockCompanyBase.setLawFirm(baseObject.getString("lssws"));
            stockCompanyBase.setAccountingFirm(baseObject.getString("kjssws"));
            stockCompanyBase.setEstablishDate(expendObject.getString("clrq"));
            stockCompanyBase.setPublishDate(expendObject.getString("ssrq"));
            stockCompanyBase.setPublishPERatio(expendObject.getString("fxsyl"));
            stockCompanyBase.setWebPublishDate(expendObject.getString("wsfxrq"));
            stockCompanyBase.setPublishType(expendObject.getString("fxfs"));
            stockCompanyBase.setPublishFaceValue(expendObject.getString("mgmz"));
            stockCompanyBase.setPublishAmount(expendObject.getString("fxl"));
            stockCompanyBase.setPublishPrice(expendObject.getString("mgfxj"));
            stockCompanyBase.setPublishCost(expendObject.getString("fxfy"));
            stockCompanyBase.setPublishMarketValue(expendObject.getString("fxzsz"));
            stockCompanyBase.setPublishRaiseFund(expendObject.getString("mjzjje"));
            stockCompanyBase.setFirstStartPrice(expendObject.getString("srkpj"));
            stockCompanyBase.setFirstHighPrice(expendObject.getString("srzgj"));
            stockCompanyBase.setFirstClosePrice(expendObject.getString("srspj"));
            stockCompanyBase.setFirstTurnoverRate(expendObject.getString("srhsl"));
            stockCompanyBase.setOffLineDistributeRate(expendObject.getString("wxpszql"));
            stockCompanyBase.setPriceWinRate(expendObject.getString("djzql"));
            stockCompanyBase.setCompanyBusinessScope(baseObject.getString("jyfw"));

            String subjectString = SpiderUtils.HttpClientBuilderGet(subjectUrl);
            JSONObject subjectObject = JSON.parseObject(subjectString);
//            System.out.println("=====================" + subjectObject);
            JSONArray subjectArray = subjectObject.getJSONArray("hxtc");
            if(subjectArray.size() > 0){
                JSONObject matterObject = subjectArray.getJSONObject(0);
                stockCompanyBase.setCompanySubjectMatter(matterObject.getString("ydnr"));
            }
            companyList.add(stockCompanyBase);

            for(int i = 1 ; i < subjectArray.size() ; i ++){
                JSONObject analyseObject = subjectArray.getJSONObject(i);
                StockCompanyAnalyse stockCompanyAnalyse = new StockCompanyAnalyse();
                stockCompanyAnalyse.setStockCode(stockCompanyBase.getStockACode());
                stockCompanyAnalyse.setOperatePoint(analyseObject.getString("gjc"));
                stockCompanyAnalyse.setPointAnalyse(analyseObject.getString("ydnr"));
                analyseList.add(stockCompanyAnalyse);
            }

            String productString = SpiderUtils.HttpClientBuilderGet(productUrl);
            JSONObject productObject = JSON.parseObject(productString);
            JSONArray productArray =productObject.getJSONArray("zygcfx");
            for(int j = 0 ; j < productArray.size() ; j++){
               JSONObject detailObject = productArray.getJSONObject(j);
                String date = detailObject.getString("rq");
                JSONArray sectorArray = detailObject.getJSONArray("hy");
                JSONArray regionArray = detailObject.getJSONArray("qy");
                JSONArray saleArray = detailObject.getJSONArray("cp");
                for(int x = 0 ;x < sectorArray.size() ; x++){
                    JSONObject sectorObject = sectorArray.getJSONObject(x);
                    StockCompanyProduct stockCompanyProduct = new StockCompanyProduct();
                    stockCompanyProduct.setStockCode(stockCompanyBase.getStockACode());
                    stockCompanyProduct.setPublishDate(date);
                    stockCompanyProduct.setPruductType("行业分类");
                    stockCompanyProduct.setProductName(sectorObject.getString("zygc"));
                    stockCompanyProduct.setProductRevenue(sectorObject.getString("zysr"));
                    stockCompanyProduct.setProductRevenueRate(sectorObject.getString("srbl"));
                    stockCompanyProduct.setProductCost(sectorObject.getString("zycb"));
                    stockCompanyProduct.setProductCostRate(sectorObject.getString("cbbl"));
                    stockCompanyProduct.setProductProfit(sectorObject.getString("zylr"));
                    stockCompanyProduct.setProductProfitRate(sectorObject.getString("lrbl"));
                    stockCompanyProduct.setProductGrossRate(sectorObject.getString("mll"));
                    productList.add(stockCompanyProduct);
                }

                for(int y = 0 ;y < saleArray.size() ; y++){
                    JSONObject saleObject = saleArray.getJSONObject(y);
                    StockCompanyProduct stockCompanyProduct = new StockCompanyProduct();
                    stockCompanyProduct.setStockCode(stockCompanyBase.getStockACode());
                    stockCompanyProduct.setPublishDate(date);
                    stockCompanyProduct.setPruductType("产品分类");
                    stockCompanyProduct.setProductName(saleObject.getString("zygc"));
                    stockCompanyProduct.setProductRevenue(saleObject.getString("zysr"));
                    stockCompanyProduct.setProductRevenueRate(saleObject.getString("srbl"));
                    stockCompanyProduct.setProductCost(saleObject.getString("zycb"));
                    stockCompanyProduct.setProductCostRate(saleObject.getString("cbbl"));
                    stockCompanyProduct.setProductProfit(saleObject.getString("zylr"));
                    stockCompanyProduct.setProductProfitRate(saleObject.getString("lrbl"));
                    stockCompanyProduct.setProductGrossRate(saleObject.getString("mll"));
                    productList.add(stockCompanyProduct);
                }

                for(int z = 0 ;z < regionArray.size() ; z++){
                    JSONObject regionObject = regionArray.getJSONObject(z);
                    StockCompanyProduct stockCompanyProduct = new StockCompanyProduct();
                    stockCompanyProduct.setStockCode(stockCompanyBase.getStockACode());
                    stockCompanyProduct.setPublishDate(date);
                    stockCompanyProduct.setPruductType("地域分类");
                    stockCompanyProduct.setProductName(regionObject.getString("zygc"));
                    stockCompanyProduct.setProductRevenue(regionObject.getString("zysr"));
                    stockCompanyProduct.setProductRevenueRate(regionObject.getString("srbl"));
                    stockCompanyProduct.setProductCost(regionObject.getString("zycb"));
                    stockCompanyProduct.setProductCostRate(regionObject.getString("cbbl"));
                    stockCompanyProduct.setProductProfit(regionObject.getString("zylr"));
                    stockCompanyProduct.setProductProfitRate(regionObject.getString("lrbl"));
                    stockCompanyProduct.setProductGrossRate(regionObject.getString("mll"));
                    productList.add(stockCompanyProduct);
                }
            }
        }
        System.out.println("=======================" + companyList.toString());
        insertBatch(companyList);
        System.out.println("=======================" + analyseList.toString());
        stockCompanyAnalyseService.insertBatch(analyseList);
        System.out.println("=======================" + productList.toString());
        stockCompanyProductService.insertBatch(productList);
    }
}
