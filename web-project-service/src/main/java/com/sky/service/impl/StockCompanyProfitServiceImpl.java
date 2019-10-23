package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.DateUtils;
import com.sky.mapper.StockCompanyProfitMapper;
import com.sky.model.StockCompanyProfit;
import com.sky.service.StockCompanyProfitService;
import com.sky.vo.StockCompanyProfitVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ThinkPad on 2019/10/22.
 */
@Service
@Transactional
public class StockCompanyProfitServiceImpl extends ServiceImpl<StockCompanyProfitMapper,StockCompanyProfit> implements StockCompanyProfitService {

    @Override
    public boolean spiderStockCompanyProfit(String stockCode, Integer page) {
        String ukm = "SZ";
        if(stockCode.substring(0,1).equals("6")){
            ukm = "SH";
        }

        String url = "http://f10.eastmoney.com/NewFinanceAnalysis/lrbAjax?companyType=4&reportDateType=0&reportType=2&endDate="+ DateUtils.format(DateUtils.addYears(new Date(),-1 * page),"yyyy") +"%2F6%2F30+0%3A00%3A00&code=" + ukm + stockCode;
        if(page == 0){
            url = "http://f10.eastmoney.com/NewFinanceAnalysis/lrbAjax?companyType=4&reportDateType=0&reportType=2&endDate=&code=" + ukm + stockCode;
        }
        String jsStr = CommonHttpUtil.sendGet(url);
        if(StringUtils.isNotBlank(jsStr)&& !jsStr.equals("\"null\"")){
            jsStr = jsStr.replace("\\","");
            jsStr = jsStr.substring(1,jsStr.length()-1);
            JSONArray jsonArray = JSON.parseArray(jsStr);
            List<StockCompanyProfit> profitList = new ArrayList<>();
            for(int i = 0 ; i < jsonArray.size() ; i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String publishDay = jsonObject.getString("REPORTDATE");//发布日期
                String totalIncome = jsonObject.getString("TOTALOPERATEREVE");//总收入
                String businessIncome = jsonObject.getString("OPERATEREVE");//营业收入
                String totalCost = jsonObject.getString("TOTALOPERATEEXP");//总成本
                String businessCost = jsonObject.getString("OPERATEEXP");//营业成本
                String totalRevenue = jsonObject.getString("OPERATEPROFIT");//营业利润 + 其他营业利润
                String otherTotalRevenue = jsonObject.getString("SUMPROFIT");//营业利润 + 其他营业利润 + 其他收入利润
                String taxMoney = jsonObject.getString("INCOMETAX");//扣税金额
                String belongProfit = jsonObject.getString("PARENTNETPROFIT");//扣除其他利润分配后，归属公司利润
                String lastProfit = jsonObject.getString("KCFJCXSYJLR");//扣税损益后，最终的剩余利润
                StockCompanyProfit profit = new StockCompanyProfit();
                profit.setStockCode(stockCode);
                profit.setPublishDay(publishDay);
                profit.setTotalIncome(totalIncome);
                profit.setBusinessIncome(businessIncome);
                profit.setTotalCost(totalCost);
                profit.setBusinessCost(businessCost);
                profit.setTotalProfit(otherTotalRevenue);
                profit.setBusinessTotalProfit(totalRevenue);
                profit.setIncomeTax(taxMoney);
                profit.setBelongProfit(belongProfit);
                profit.setFinalProfit(lastProfit);
                StockCompanyProfit stockCompanyProfit = selectOne(new EntityWrapper<StockCompanyProfit>().where("stock_code = {0} and publish_day = {1}" ,stockCode ,publishDay));
                if(stockCompanyProfit == null){
                    profitList.add(profit);
                }
            }
            if(profitList.size() > 0){
                return insertBatch(profitList);
            }
        }else {
            return false;
        }
        return false;
    }


    @Override
    public List<StockCompanyProfitVO> getCompanyProfitGrowList(String stockCode) {
        return baseMapper.getCompanyProfitGrowList(stockCode);
    }
}
