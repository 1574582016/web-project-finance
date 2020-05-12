package com.sky.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sky.core.utils.HttpUtil;
import com.sky.mapper.StockCompanyBusinessProfitMapper;
import com.sky.model.StockCompanyBusinessProfit;
import com.sky.service.StockCompanyBusinessProfitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by ThinkPad on 2020/5/12.
 */
@Service
@Transactional
public class StockCompanyBusinessProfitServiceImpl extends ServiceImpl<StockCompanyBusinessProfitMapper,StockCompanyBusinessProfit> implements StockCompanyBusinessProfitService {

    @Override
    public boolean spiderStockCompanyBusinessProfit(String stockCode) {
        String market = "SH";
        if (!stockCode.substring(0, 1).equals("6")) {
            market = "SZ";
        }

        String[] publishDays = {"","2019-03-31","2017-12-31","2016-09-30","2015-06-30","2014-03-31","2012-12-31","2011-09-30","2010-06-30","2009-03-31","2007-12-31","2006-09-30","2005-06-30","2004-03-31","2003-06-30"};
        Set<StockCompanyBusinessProfit> set = new HashSet();
        for(String publishDay : publishDays){
            String url = "http://f10.eastmoney.com/NewFinanceAnalysis/lrbAjax?companyType=4&reportDateType=0&reportType=2&endDate="+ publishDay +"&code=" + market + stockCode;
            String content = HttpUtil.getHtmlContentByGet(url);
            System.out.println(content);
            if(!content.equals("\"null\"")){
                content = content.replace("\\" , "");
                content = content.substring(1);
                content = content.substring(0,content.length()-1);
                JSONArray jsonArray = JSON.parseArray(content);
                for(int i = 0 ; i < jsonArray.size() ; i ++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String REPORTDATE = jsonObject.getString("REPORTDATE").replace("/" , "-").replace(" 0:00:00","").replace(" ","");//发布时间
                    String OPERATEREVE = jsonObject.getString("OPERATEREVE").replace(" ","");//营业总收入
                    String TOTALOPERATEEXP = jsonObject.getString("TOTALOPERATEEXP").replace(" ","");//营业总成本
                    String OPERATEPROFIT = jsonObject.getString("OPERATEPROFIT").replace(" ","");//营业利润
                    String SUMPROFIT = jsonObject.getString("SUMPROFIT").replace(" ","");//总利润
                    String NETPROFIT = jsonObject.getString("NETPROFIT").replace(" ","");//净利润
                    StockCompanyBusinessProfit profit = new StockCompanyBusinessProfit();
                    profit.setStockCode(stockCode);
                    profit.setPublishDay(REPORTDATE);
                    profit.setBusinessIncome(OPERATEREVE);
                    profit.setBusinessCost(TOTALOPERATEEXP);
                    profit.setBusinessProfit(OPERATEPROFIT);
                    profit.setTotalProfit(SUMPROFIT);
                    profit.setPureProfit(NETPROFIT);
                    set.add(profit);
                }
            }else{
                break;
            }
        }
        List<StockCompanyBusinessProfit> list = new ArrayList<>();
        for(StockCompanyBusinessProfit profit : set){
            list.add(profit);
        }
        boolean result = false;
        if(list.size() > 0){
            result = insertBatch(list);
        }
        return result;
    }
}
