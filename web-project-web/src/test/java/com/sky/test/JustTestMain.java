package com.sky.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sky.core.utils.SpiderUtils;
import com.sky.model.StockMoneyFlow;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2019/12/25/025.
 */
public class JustTestMain {

    public static void main(String[] args){
//        int max=100000000,min=1;
//        long randomNum = System.currentTimeMillis();
//        int ran3 = (int) (randomNum%(max-min)+min);
//        System.out.println(ran3);

//        String sameRoot = "brev,brevi,brief,bridg";
//        String str = getDifferentRoot(sameRoot);
//        System.out.println(str);

//        for(int x = 100 ; x >= 0 ; x--){
//            BigDecimal max = BigDecimal.valueOf(140).subtract((BigDecimal.valueOf(0.6).multiply(BigDecimal.valueOf(x)))).setScale(2,BigDecimal.ROUND_HALF_UP);
//            BigDecimal min = BigDecimal.valueOf(30).subtract((BigDecimal.valueOf(0.1).multiply(BigDecimal.valueOf(x)))).setScale(2,BigDecimal.ROUND_HALF_UP);
//            BigDecimal rate = max.divide(min ,2 , BigDecimal.ROUND_HALF_UP);
//            System.out.println("========"+ x +"=====max====" + max + "=====min====" + min + "=====rate====" + rate);
//            System.out.println();
//        }

        String url = "http://push2his.eastmoney.com/api/qt/stock/fflow/daykline/get?lmt=0&klt=101&secid=0.000333&fields1=f1,f2,f3,f7&fields2=f51,f52,f53,f54,f55,f56,f57,f58,f59,f60,f61,f62,f63,f64,f65&ut=b2884a393a59ad64002292a3e90d46a5&cb=jQuery18307951788775609181_1578903118106&_=1578903118636";
        String jsonString = SpiderUtils.HttpClientBuilderGet(url);
        String jStr = jsonString.substring(jsonString.indexOf("(")+1 ,jsonString.indexOf(")"));
        JSONArray jsonArray = JSON.parseObject(jStr).getJSONObject("data").getJSONArray("klines");
        for(int i = 0 ; i < jsonArray.size() ; i ++){
            StockMoneyFlow flow = new StockMoneyFlow();
            String single = jsonArray.getString(i);
            System.out.println(single);
            List<String> list = Arrays.asList(single.split(","));
            for(int j = 0 ; j < list.size() ; j ++){
                switch (j){
                    case 0 :
                        flow.setDealTime(list.get(j));
                        break;
                    case 1 :
                        flow.setMagorMoney(list.get(j));
                        break;
                    case 5 :
                        flow.setSuperMoney(list.get(j));
                        break;
                    case 4 :
                        flow.setBigMoney(list.get(j));
                        break;
                    case 3 :
                        flow.setMiddleMoney(list.get(j));
                        break;
                    case 2 :
                        flow.setSmallMoney(list.get(j));
                        break;
                    case 11 :
                        flow.setDayPrice(list.get(j));
                        break;
                }
            }
            System.out.println(flow.toString());
        }

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
