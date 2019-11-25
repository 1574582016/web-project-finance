package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
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

    @Override
    public void spiderStockCompanyBase(String stockCode) {
            String companyUrl = "http://f10.eastmoney.com/CompanySurvey/CompanySurveyAjax?code=" + stockCode;
            String subjectUrl = "http://f10.eastmoney.com/CoreConception/CoreConceptionAjax?code=" + stockCode;
            System.out.println(companyUrl);
            String companyString = SpiderUtils.HttpClientBuilderGet(companyUrl);
            JSONObject companyObject = JSON.parseObject(companyString);
            JSONObject baseObject = companyObject.getJSONObject("jbzl");
            JSONObject expendObject = companyObject.getJSONObject("fxxg");
            StockCompanyBase stockCompanyBase = new StockCompanyBase();
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
            JSONArray subjectArray = subjectObject.getJSONArray("hxtc");
            if (subjectArray.size() > 0) {
                JSONObject matterObject = subjectArray.getJSONObject(0);
                stockCompanyBase.setCompanySubjectMatter(matterObject.getString("ydnr"));
            }
            EntityWrapper<StockCompanyBase> entityWrapper = new EntityWrapper<>();
            if(!stockCompanyBase.getStockACode().equals("--")){
                    entityWrapper.where("stock_a_code = {0}" , stockCompanyBase.getStockACode());
            }else{
                    if(!stockCompanyBase.getStockBCode().equals("--")){
                            entityWrapper.where("stock_b_code = {0}" , stockCompanyBase.getStockBCode());
                    }else{
                            if(!stockCompanyBase.getStockHCode().equals("--")){
                                    entityWrapper.where("stock_h_code = {0}" , stockCompanyBase.getStockHCode());
                            }
                    }
            }

            StockCompanyBase isExist = selectOne(entityWrapper);
            if(isExist == null){
                    insert(stockCompanyBase);
            }
    }

    @Override
    public Page<StockCompanyBase> getStockCompanyBaseList(Integer page, Integer size, String stockCode, String stockName, String stockSector,String stockExchange , String startDay ,String endDay ,String middleContrySector) {
        Page<StockCompanyBase> pageInfo = new Page<StockCompanyBase>(page, size);
        List<StockCompanyBase> list = baseMapper.getStockCompanyBaseList( pageInfo,stockCode, stockName , stockSector, stockExchange , startDay , endDay ,middleContrySector);
        pageInfo.setRecords(list);
        return pageInfo;
    }
}
