package com.sky.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.FileUtils;
import com.sky.core.utils.HttpUtil;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.ForexDealData;
import com.sky.model.ForexDealDataOneMinute;
import com.sky.model.StockCompanyBusinessProfit;
import com.sky.model.StockMoneyFlow;
import org.assertj.core.util.Sets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2019/12/25/025.
 */
public class JustTestMain {

    public static void main(String[] args){
            String url = "http://emweb.securities.eastmoney.com/CapitalStockStructure/CapitalStockStructureAjax?code=SH600585";
            String content = HttpUtil.getHtmlContentByGet(url);
            System.out.println(content);
            JSONObject jsonObject = JSONObject.parseObject(content).getJSONObject("CapitalStockStructureDetail");
            String ltgfhf = jsonObject.getString("jsonObject");//总股本
            String yssltag = jsonObject.getString("yssltag");//A股流通股份
            String yssltbg = jsonObject.getString("yssltbg");//B股流通股股份
            String jwssltg = jsonObject.getString("jwssltg");//境外流通股份
            String ltsxgf = jsonObject.getString("ltsxgf");//流通受限股份
            String wltgf = jsonObject.getString("wltgf");//未流通股份


    }

    private static String caculateDay(String dayString){
        return dayString.substring(0,4) + "-" + dayString.substring(4,6) + "-" + dayString.substring(6,8);
    }

    private static String caculateTime(String timeString){
        return timeString.substring(0,2) + ":" + timeString.substring(2,4) + ":" + timeString.substring(4,6);
    }

    public static String getDifferentRoot(String sameRoot){
        Set<String> list = new HashSet<>();
        String[] roots = sameRoot.split(",");
        for(String root: roots){
            boolean just = false ;
            for(String roote : roots){
                if(!root.equals(roote) && ( root.indexOf(roote) != -1 || roote.indexOf(root) !=  -1 )){
                    just = true ;
                    System.out.println(roote);
                    if(root.indexOf(roote) != 0){
                        list.add(root);
                    }
                    if(roote.indexOf(root) != 0){
                        list.add(roote);
                    }
                }
            }
            if(!just){
                list.add(root);
            }
        }
        String str = "";
        for (String root : list) {
            str += root + ",";
        }

        return str;
    }
}
