package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.ChartUtils;
import com.sky.core.utils.DateUtils;
import com.sky.core.utils.Serie;
import com.sky.model.*;
import com.sky.scheduler.StockCompanyNoticeScheduler;
import com.sky.service.*;
import com.sky.vo.CompanyProfit_VO;
import com.sky.vo.StockCompanyProfitVO;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.List;

/**
 * Created by ThinkPad on 2019/9/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CaculateTest {

    @Autowired
    private StockCompanyNoticeService stockCompanyNoticeService;

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Autowired
    private MessagePriceStaticService messagePriceStaticService ;

    @Autowired
    private StockCompanyProfitService stockCompanyProfitService ;

    @Autowired
    private StockMoneyFlowService stockMoneyFlowService ;


    @Test
    public void test(){
        try {
            List<StockCompanyNotice> noticeList = stockCompanyNoticeService.selectList(new EntityWrapper<StockCompanyNotice>().where("notice_type not in ('股权激励计划' ,'重大合同' ,'其他')").groupBy("notice_type").orderBy("notice_type"));
            for(StockCompanyNotice notice : noticeList){
                String messageType = notice.getNoticeType();

                List<StockCompanyNotice> list = stockCompanyNoticeService.selectList(new EntityWrapper<StockCompanyNotice>().where("notice_type = {0}" , messageType).groupBy("stock_code,publish_time").orderBy("stock_code,publish_time"));
                for(StockCompanyNotice companyNotice : list){
                    List<MessagePriceStatic> arrList = new ArrayList<>();
                    String pointDay = companyNotice.getPublishTime();
                    List<StockDealData> dataList = stockDealDataService.getPointDayScopeList(companyNotice.getStockCode() , pointDay , "10");
                    Map<Integer ,String> lastMap  = new TreeMap<Integer, String>(
                            new Comparator<Integer>() {
                                public int compare(Integer obj1, Integer obj2) {
                                    // 降序排序
                                    return obj1.compareTo(obj2);
                                }
                            });
                    Map<Integer ,String> furtureMap  = new TreeMap<Integer, String>(
                            new Comparator<Integer>() {
                                public int compare(Integer obj1, Integer obj2) {
                                    // 降序排序
                                    return obj2.compareTo(obj1);
                                }
                            });
                    for(StockDealData dealData : dataList){
                        int days = DateUtils.differentDaysByString(dealData.getDealTime() , pointDay);
                        if(days > 0){
                            lastMap.put(days ,dealData.getDealTime());
                        }

                        if(days == 0){
                            MessagePriceStatic priceStatic = createModel( dealData , messageType , 0);
                            arrList.add(priceStatic);
                        }

                        if(days < 0){
                            furtureMap.put(days ,dealData.getDealTime());
                        }
                    }

                    Set<Integer> keySet = lastMap.keySet();
                    Iterator<Integer> iter = keySet.iterator();
                    int num = 0;
                    while (iter.hasNext()) {
                        Integer key = iter.next();
                        int timeType = 0 ;
                        if(num == 0){
                            timeType = -3 ;
                        }
                        if(num == 1){
                            timeType = -2 ;
                        }
                        if(num == 2){
                            timeType = -1 ;
                        }
                        for(StockDealData dealData : dataList){
                            if(dealData.getDealTime().equals(lastMap.get(key))){
                                MessagePriceStatic priceStatic = createModel( dealData , messageType , timeType);
                                arrList.add(priceStatic);
                            }
                        }

                        num ++;
                        if(num == 3){
                            break;
                        }
                    }

                    Set<Integer> keySet2 = furtureMap.keySet();
                    Iterator<Integer> iter2 = keySet2.iterator();
                    int num2 = 0;
                    while (iter2.hasNext()) {
                        Integer key = iter2.next();
                        int timeType = 0 ;
                        if(num2 == 0){
                            timeType = 1 ;
                        }
                        if(num2 == 1){
                            timeType = 2 ;
                        }
                        if(num2 == 2){
                            timeType = 3 ;
                        }
                        for(StockDealData dealData : dataList){
                            if(dealData.getDealTime().equals(furtureMap.get(key))){
                                MessagePriceStatic priceStatic = createModel( dealData , messageType , timeType);
                                arrList.add(priceStatic);
                            }
                        }
                        num2 ++;
                        if(num2 == 3){
                            break;
                        }
                    }
                    System.out.println(arrList.toString());
                    if(arrList.size() > 0){
                        messagePriceStaticService.insertBatch(arrList);
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        List<StockCompanyNotice> list = stockCompanyNoticeService.selectList(new EntityWrapper<StockCompanyNotice>().where("notice_type = {0}" , "股权激励计划").groupBy("stock_code,publish_time").orderBy("stock_code,publish_time"));
        BigDecimal upper = BigDecimal.ZERO ;
        BigDecimal down = BigDecimal.ZERO ;
        BigDecimal flat = BigDecimal.ZERO ;
        BigDecimal upperPrice = BigDecimal.ZERO ;
        BigDecimal downPrice = BigDecimal.ZERO ;

        BigDecimal maxPrice = BigDecimal.ZERO ;
        BigDecimal minPrice = BigDecimal.ZERO ;

        for(StockCompanyNotice companyNotice : list) {
            String pointDay = companyNotice.getPublishTime();
            List<StockDealData> dataList = stockDealDataService.getPointDayScopeList(companyNotice.getStockCode(), pointDay, null);

            for(StockDealData dealData : dataList){
                MessagePriceStatic priceStatic = new MessagePriceStatic();
                priceStatic.setMessageType("股权激励计划");
                priceStatic.setStockCode(dealData.getStockCode());
                priceStatic.setTimeType(0);

                BigDecimal closePrice = dealData.getClosePrice();
                BigDecimal openPrice = dealData.getOpenPrice();
                BigDecimal diffPrice = closePrice.subtract(openPrice).setScale(2,BigDecimal.ROUND_HALF_UP);



                if(diffPrice.compareTo(BigDecimal.ZERO) > 0){
                    upper = upper.add(BigDecimal.ONE);
                    upperPrice = upperPrice.add(diffPrice);
                    priceStatic.setDirectType(1);
                    priceStatic.setDiffPrice(diffPrice);

                    if(diffPrice.compareTo(maxPrice) > 0){
                        maxPrice = diffPrice ;
                    }
                }

                if(diffPrice.compareTo(BigDecimal.ZERO) == 0){
                    flat = flat.add(BigDecimal.ONE);
                    priceStatic.setDirectType(0);
                    priceStatic.setDiffPrice(diffPrice);
                }

                if(diffPrice.compareTo(BigDecimal.ZERO) < 0){
                    down = down.add(BigDecimal.ONE);
                    downPrice = downPrice.add(diffPrice);
                    priceStatic.setDirectType(-1);
                    priceStatic.setDiffPrice(diffPrice.multiply(BigDecimal.valueOf(-1)));

                    if(diffPrice.compareTo(minPrice) < 0){
                        minPrice = diffPrice ;
                    }
                }
                messagePriceStaticService.insert(priceStatic);

            }
        }
        BigDecimal upperRate = upper.multiply(BigDecimal.valueOf(100)).divide((upper.add(down)), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal downRate = down.multiply(BigDecimal.valueOf(100)).divide((upper.add(down)), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal upperAverage = upperPrice.divide(upper, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal downAverage = downPrice.divide(upper, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("===========upper===========" + upper);
        System.out.println("===========flat===========" + flat);
        System.out.println("===========down===========" + down);
        System.out.println("===========upperPrice===========" + upperPrice);
        System.out.println("===========downPrice===========" + downPrice);

        System.out.println("===========maxPrice===========" + maxPrice);
        System.out.println("===========minPrice===========" + minPrice);


        System.out.println("===========upperRate===========" + upperRate);
        System.out.println("===========downRate===========" + downRate);
        System.out.println("===========upperAverage===========" + upperAverage);
        System.out.println("===========downAverage===========" + downAverage);
    }


    private MessagePriceStatic createModel(StockDealData dealData ,String messageTitle ,Integer timeType){
        MessagePriceStatic priceStatic = new MessagePriceStatic();
        priceStatic.setMessageType(messageTitle);
        priceStatic.setStockCode(dealData.getStockCode());
        priceStatic.setTimeType(timeType);

        BigDecimal closePrice = dealData.getClosePrice();
        BigDecimal openPrice = dealData.getOpenPrice();
        BigDecimal diffPrice = closePrice.subtract(openPrice).setScale(2,BigDecimal.ROUND_HALF_UP);

        if(diffPrice.compareTo(BigDecimal.ZERO) > 0){
            priceStatic.setDirectType(1);
            priceStatic.setDiffPrice(diffPrice);
        }

        if(diffPrice.compareTo(BigDecimal.ZERO) == 0){
            priceStatic.setDirectType(0);
            priceStatic.setDiffPrice(diffPrice);
        }

        if(diffPrice.compareTo(BigDecimal.ZERO) < 0){
            priceStatic.setDirectType(-1);
            priceStatic.setDiffPrice(diffPrice.multiply(BigDecimal.valueOf(-1)));
        }

        return priceStatic ;
    }

    @Test
    public void test003(){
        BigDecimal result = stockCompanyProfitService.calculateCompanyProfitIncreaseRate("000001" , "2013" , "2018");
        System.out.println(result);
    }

    @Test
    public void test004(){
        BigDecimal result = stockCompanyProfitService.caculateProfitIncreaseRate("000001" , "5" , "2010");
        System.out.println(result);
    }

    @Autowired
    private StockProfitIncreaseRateService stockProfitIncreaseRateService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService ;

    @Test
    public void test005(){
        List<StockCompanySector> list = stockCompanySectorService.selectList(null);
        for(StockCompanySector sector : list){
            stockProfitIncreaseRateService.caculateStockProfitIncreaseRate(sector.getStockCode());
        }
    }


    @Test
    public void test009(){
       List<StockMoneyFlow> list = stockMoneyFlowService.selectList(new EntityWrapper<StockMoneyFlow>().where("stock_code = '000333'").orderBy("deal_time DESC"));
        List<JSONObject> jsonList = new ArrayList<>();
       for(int i = 0 ; i < list.size() - 4 ; i ++){
           StockMoneyFlow firstFlow = list.get(i);
           StockMoneyFlow secondFlow = list.get(i+1);
           StockMoneyFlow thirdFlow = list.get(i+2);
           StockMoneyFlow forthFlow = list.get(i+3);
           StockMoneyFlow fifthFlow = list.get(i+4);
           JSONObject jsonObject = new JSONObject();
           jsonObject.put("dealTime" , firstFlow.getDealTime());
           jsonObject.put("currentPrice" , new BigDecimal(firstFlow.getDayPrice()).divide(BigDecimal.TEN , 2 , BigDecimal.ROUND_HALF_UP));
           BigDecimal averagePrice = (new BigDecimal(firstFlow.getDayPrice()).add(new BigDecimal(secondFlow.getDayPrice())).add(new BigDecimal(thirdFlow.getDayPrice())).add(new BigDecimal(forthFlow.getDayPrice())).add(new BigDecimal(fifthFlow.getDayPrice()))).divide(BigDecimal.valueOf(5).multiply(BigDecimal.TEN) ,2 ,BigDecimal.ROUND_HALF_UP);
           jsonObject.put("averagePrice" , averagePrice);

           BigDecimal firstTotal = new BigDecimal(firstFlow.getMagorMoney()).add(new BigDecimal(firstFlow.getSuperMoney())).add(new BigDecimal(firstFlow.getBigMoney())).add(new BigDecimal(firstFlow.getMiddleMoney())).add(new BigDecimal(firstFlow.getSmallMoney()));
           BigDecimal secondTotal = new BigDecimal(secondFlow.getMagorMoney()).add(new BigDecimal(secondFlow.getSuperMoney())).add(new BigDecimal(secondFlow.getBigMoney())).add(new BigDecimal(secondFlow.getMiddleMoney())).add(new BigDecimal(secondFlow.getSmallMoney()));
           BigDecimal thirdTotal = new BigDecimal(thirdFlow.getMagorMoney()).add(new BigDecimal(thirdFlow.getSuperMoney())).add(new BigDecimal(thirdFlow.getBigMoney())).add(new BigDecimal(thirdFlow.getMiddleMoney())).add(new BigDecimal(thirdFlow.getSmallMoney()));
           BigDecimal forthTotal = new BigDecimal(forthFlow.getMagorMoney()).add(new BigDecimal(forthFlow.getSuperMoney())).add(new BigDecimal(forthFlow.getBigMoney())).add(new BigDecimal(forthFlow.getMiddleMoney())).add(new BigDecimal(forthFlow.getSmallMoney()));
           BigDecimal fifthTotal = new BigDecimal(fifthFlow.getMagorMoney()).add(new BigDecimal(fifthFlow.getSuperMoney())).add(new BigDecimal(fifthFlow.getBigMoney())).add(new BigDecimal(fifthFlow.getMiddleMoney())).add(new BigDecimal(fifthFlow.getSmallMoney()));

           BigDecimal averageTotal = (firstTotal.add(secondTotal).add(thirdTotal).add(forthTotal).add(fifthTotal)).divide(BigDecimal.valueOf(5).multiply(BigDecimal.valueOf(100000000)) , 2 , BigDecimal.ROUND_HALF_UP);

           jsonObject.put("averageTotal" , averageTotal);

           jsonList.add(jsonObject);
       }
        jsonList.sort((o1, o2) -> o1.getString("dealTime").compareTo(o2.getString("dealTime")));
        System.out.println(jsonList.toString());
        createFlowImg("000333" , jsonList);
    }

    private void createFlowImg(String stockCode ,List<JSONObject> jsonList){
        try {
            if(jsonList.size() == 0 ){
                return;
            }
            List<String> title = new ArrayList<>();
            List<BigDecimal> currentPrice = new ArrayList<>();
            List<BigDecimal> averagePrice = new ArrayList<>();
            List<BigDecimal> averageTotal = new ArrayList<>();

            for(JSONObject jsonObject : jsonList){
                title.add(jsonObject.getString("dealTime"));
                currentPrice.add(jsonObject.getBigDecimal("currentPrice"));
                averagePrice.add(jsonObject.getBigDecimal("averagePrice"));
                averageTotal.add(jsonObject.getBigDecimal("averageTotal"));
            }
            String[] categories = new String[title.size()];
            title.toArray(categories);
            Vector<Serie> series = new Vector<Serie>();
            // 柱子名称：柱子所有的值集合
            series.add(new Serie("当前价格", currentPrice));
            series.add(new Serie("平均价格", averagePrice));
            series.add(new Serie("平均流量", averageTotal));
            // 1：创建数据集合
            DefaultCategoryDataset dataset = ChartUtils.createDefaultCategoryDataset(series, categories);

            // 2：创建Chart[创建不同图形]
            JFreeChart chart = ChartFactory.createLineChart("利润增长", "", "收益（亿）", dataset);
            // 3:设置抗锯齿，防止字体显示不清楚
            ChartUtils.setAntiAlias(chart);// 抗锯齿
            // 4:对柱子进行渲染[[采用不同渲染]]
            ChartUtils.setLineRender(chart.getCategoryPlot(), false,true);//
            // 5:对其他部分进行渲染
            ChartUtils.setXAixs(chart.getCategoryPlot());// X坐标轴渲染
            ChartUtils.setYAixs(chart.getCategoryPlot());// Y坐标轴渲染
            // 设置标注无边框
            chart.getLegend().setFrame(new BlockBorder(Color.WHITE));

            ChartUtils.saveAsFile(chart , "E:/dataImg/"+ stockCode +"_profit.jpg" , 1024 , 420);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
