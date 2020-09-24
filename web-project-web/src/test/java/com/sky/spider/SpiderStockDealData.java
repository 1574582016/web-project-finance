package com.sky.spider;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.*;
import com.sky.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/12/27/027.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderStockDealData {

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Autowired
    private StockIndexClassService stockIndexClassService;

    @Autowired
    private StockCompanySectorService stockCompanySectorService;


    @Autowired
    private IndexDealDataService indexDealDataService ;


    @Autowired
    private StockDealDataFiveMinuteService stockDealDataFiveMinuteService ;

    @Autowired
    private StockDealDataFifteenMinuteService stockDealDataFifteenMinuteService ;

    @Autowired
    private StockDealDataThirtyMinuteService stockDealDataThirtyMinuteService ;

    @Autowired
    private StockDealDataSixtyMinuteService stockDealDataSixtyMinuteService ;

    @Autowired
    private StockSectorLevelService stockSectorLevelService ;

    @Autowired
    private StockDealDataVolService stockDealDataVolService;

    @Test
    public void spiderStockDealData() throws InterruptedException, ExecutionException {
        // 开始时间
        long start = System.currentTimeMillis();
        List<StockCompanySector> list = stockCompanySectorService.selectList(null);
        // 每500条数据开启一条线程
        int threadSize = 500;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadSize + 1;
        // 定义标记,过滤threadNum为整数
        boolean special = dataSize % threadSize == 0;

        // 创建一个线程池
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        // 定义一个任务集合
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        List<StockCompanySector> cutList = null;

        // 确定每条线程的数据
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                if (special) {
                    break;
                }
                cutList = list.subList(threadSize * i, dataSize);
            } else {
                cutList = list.subList(threadSize * i, threadSize * (i + 1));
            }
            // System.out.println("第" + (i + 1) + "组：" + cutList.toString());
            final List<StockCompanySector> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    for(StockCompanySector stockCode : listStr){
                        String mk = "1";
                        if(!stockCode.getStockCode().substring(0,1).equals("6")){
                            mk = "2";
                        }
                        for(int t = 1 ; t <=1 ; t++) {
                            if(t == 2 || t == 3){
                                continue;
                            }
                            List<StockDealData> dataList = stockDealDataService.spiderStockDealData(t, stockCode.getStockCode(), mk);

                            if (null != dataList && dataList.size() > 0) {
                                int pointsDataLimit = 200;//限制条数
                                Integer size = dataList.size();
                                //判断是否有必要分批
                                if (pointsDataLimit < size) {
                                    int part = size / pointsDataLimit;//分批数
                                    System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
                                    for (int i = 0; i < part; i++) {
                                        List<StockDealData> listPage = dataList.subList(0, pointsDataLimit);
                                        saveStockDealData(t , listPage);

                                        //剔除
                                        dataList.subList(0, pointsDataLimit).clear();
                                    }
                                    if (!dataList.isEmpty()) {
                                        saveStockDealData(t , dataList);
                                    }
                                } else {
                                    saveStockDealData(t , dataList);
                                }
                            } else {
                                System.out.println("没有数据!!!");
                            }

                            Thread.sleep(1000);
                        }
                    }
                    return 1;
                }
            };
            // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
            tasks.add(task);
        }

        List<Future<Integer>> results = exec.invokeAll(tasks);

        for (Future<Integer> future : results) {
            System.out.println(future.get());
        }

        // 关闭线程池
        exec.shutdown();
        System.out.println("线程任务执行结束");
        System.err.println("执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
    }

    private void saveStockDealData(int periodType , List<StockDealData> dataList ){
        switch (periodType){
            case 1 :
                stockDealDataService.insertBatch(dataList);
                break;
            case 4 :
                List<StockDealDataFiveMinute> fiveMinuteList = new ArrayList<>();
                for(StockDealData dealData : dataList){
                    StockDealDataFiveMinute dealDataMinute = new StockDealDataFiveMinute();
                    dealDataMinute.setDealPeriod(dealData.getDealPeriod());
                    dealDataMinute.setPointYear(dealData.getPointYear());
                    dealDataMinute.setPointMonth(dealData.getPointMonth());
                    dealDataMinute.setPointWeek(dealData.getPointWeek());
                    dealDataMinute.setPointDay(dealData.getPointDay());
                    dealDataMinute.setDealTime(dealData.getDealTime());
                    dealDataMinute.setStockCode(dealData.getStockCode());
                    dealDataMinute.setOpenPrice(dealData.getOpenPrice());
                    dealDataMinute.setClosePrice(dealData.getClosePrice());
                    dealDataMinute.setHighPrice(dealData.getHighPrice());
                    dealDataMinute.setLowPrice(dealData.getLowPrice());
                    dealDataMinute.setDealCount(dealData.getDealCount());
                    dealDataMinute.setDealMoney(dealData.getDealMoney());
                    dealDataMinute.setAmplitude(dealData.getAmplitude());
                    dealDataMinute.setHandRate(dealData.getHandRate());
                    fiveMinuteList.add(dealDataMinute);
                }
                stockDealDataFiveMinuteService.insertBatch(fiveMinuteList);
                break;
            case 5 :
                List<StockDealDataFifteenMinute> fifteenMinuteList = new ArrayList<>();
                for(StockDealData dealData : dataList){
                    StockDealDataFifteenMinute dealDataMinute = new StockDealDataFifteenMinute();
                    dealDataMinute.setDealPeriod(dealData.getDealPeriod());
                    dealDataMinute.setPointYear(dealData.getPointYear());
                    dealDataMinute.setPointMonth(dealData.getPointMonth());
                    dealDataMinute.setPointWeek(dealData.getPointWeek());
                    dealDataMinute.setPointDay(dealData.getPointDay());
                    dealDataMinute.setDealTime(dealData.getDealTime());
                    dealDataMinute.setStockCode(dealData.getStockCode());
                    dealDataMinute.setOpenPrice(dealData.getOpenPrice());
                    dealDataMinute.setClosePrice(dealData.getClosePrice());
                    dealDataMinute.setHighPrice(dealData.getHighPrice());
                    dealDataMinute.setLowPrice(dealData.getLowPrice());
                    dealDataMinute.setDealCount(dealData.getDealCount());
                    dealDataMinute.setDealMoney(dealData.getDealMoney());
                    dealDataMinute.setAmplitude(dealData.getAmplitude());
                    dealDataMinute.setHandRate(dealData.getHandRate());
                    fifteenMinuteList.add(dealDataMinute);
                }
                stockDealDataFifteenMinuteService.insertBatch(fifteenMinuteList);
                break;
            case 6 :
                List<StockDealDataThirtyMinute> thirtyMinuteList = new ArrayList<>();
                for(StockDealData dealData : dataList){
                    StockDealDataThirtyMinute dealDataMinute = new StockDealDataThirtyMinute();
                    dealDataMinute.setDealPeriod(dealData.getDealPeriod());
                    dealDataMinute.setPointYear(dealData.getPointYear());
                    dealDataMinute.setPointMonth(dealData.getPointMonth());
                    dealDataMinute.setPointWeek(dealData.getPointWeek());
                    dealDataMinute.setPointDay(dealData.getPointDay());
                    dealDataMinute.setDealTime(dealData.getDealTime());
                    dealDataMinute.setStockCode(dealData.getStockCode());
                    dealDataMinute.setOpenPrice(dealData.getOpenPrice());
                    dealDataMinute.setClosePrice(dealData.getClosePrice());
                    dealDataMinute.setHighPrice(dealData.getHighPrice());
                    dealDataMinute.setLowPrice(dealData.getLowPrice());
                    dealDataMinute.setDealCount(dealData.getDealCount());
                    dealDataMinute.setDealMoney(dealData.getDealMoney());
                    dealDataMinute.setAmplitude(dealData.getAmplitude());
                    dealDataMinute.setHandRate(dealData.getHandRate());
                    thirtyMinuteList.add(dealDataMinute);
                }
                stockDealDataThirtyMinuteService.insertBatch(thirtyMinuteList);
                break;
            case 7 :
                List<StockDealDataSixtyMinute> sixtyMinuteList = new ArrayList<>();
                for(StockDealData dealData : dataList){
                    StockDealDataSixtyMinute dealDataMinute = new StockDealDataSixtyMinute();
                    dealDataMinute.setDealPeriod(dealData.getDealPeriod());
                    dealDataMinute.setPointYear(dealData.getPointYear());
                    dealDataMinute.setPointMonth(dealData.getPointMonth());
                    dealDataMinute.setPointWeek(dealData.getPointWeek());
                    dealDataMinute.setPointDay(dealData.getPointDay());
                    dealDataMinute.setDealTime(dealData.getDealTime());
                    dealDataMinute.setStockCode(dealData.getStockCode());
                    dealDataMinute.setOpenPrice(dealData.getOpenPrice());
                    dealDataMinute.setClosePrice(dealData.getClosePrice());
                    dealDataMinute.setHighPrice(dealData.getHighPrice());
                    dealDataMinute.setLowPrice(dealData.getLowPrice());
                    dealDataMinute.setDealCount(dealData.getDealCount());
                    dealDataMinute.setDealMoney(dealData.getDealMoney());
                    dealDataMinute.setAmplitude(dealData.getAmplitude());
                    dealDataMinute.setHandRate(dealData.getHandRate());
                    sixtyMinuteList.add(dealDataMinute);
                }
                stockDealDataSixtyMinuteService.insertBatch(sixtyMinuteList);
                break;
        }
    }


    @Test
    public void spiderIndexDealData() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
        EntityWrapper<StockIndexClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("market_type is not null");
        List<StockIndexClass> list = stockIndexClassService.selectList(entityWrapper);
        // 每500条数据开启一条线程
        int threadSize = 500;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadSize + 1;
        // 定义标记,过滤threadNum为整数
        boolean special = dataSize % threadSize == 0;

        // 创建一个线程池
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        // 定义一个任务集合
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        List<StockIndexClass> cutList = null;

        // 确定每条线程的数据
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                if (special) {
                    break;
                }
                cutList = list.subList(threadSize * i, dataSize);
            } else {
                cutList = list.subList(threadSize * i, threadSize * (i + 1));
            }
            // System.out.println("第" + (i + 1) + "组：" + cutList.toString());
            final List<StockIndexClass> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    for(StockIndexClass indexClass : listStr){

                        String indexCode = indexClass.getIndexCode();
                        indexCode = indexClass.getMarketType() + "." + indexCode ;
                        for(int t = 1 ; t <=7 ; t++){
                            String klt = "";
                            switch (t){
                                case 1 : klt = "101" ;break;
                                case 2 : klt = "102" ;break;
                                case 3 : klt = "103" ;break;

                                case 4 : klt = "60" ;break;
                                case 5 : klt = "30" ;break;
                                case 6 : klt = "15" ;break;
                                case 7 : klt = "5" ;break;
                            }
                            List<IndexDealData> dataList = indexDealDataService.spiderIndexDealData(t , indexCode ,klt);

                            if(null!=dataList&&dataList.size()>0){
                                int pointsDataLimit = 200;//限制条数
                                Integer size = dataList.size();
                                //判断是否有必要分批
                                if(pointsDataLimit<size){
                                    int part = size/pointsDataLimit;//分批数
                                    System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                    for (int i = 0; i < part; i++) {
                                        List<IndexDealData> listPage = dataList.subList(0, pointsDataLimit);
                                        indexDealDataService.insertBatch(listPage);
                                        //剔除
                                        dataList.subList(0, pointsDataLimit).clear();
                                    }
                                    if(!dataList.isEmpty()){
                                        indexDealDataService.insertBatch(dataList);
                                    }
                                }else{
                                    indexDealDataService.insertBatch(dataList);
                                }
                            }else{
                                System.out.println("没有数据!!!");
                            }

                            Thread.sleep(1000);
                        }
                    }
                    return 1;
                }
            };
            // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
            tasks.add(task);
        }

        List<Future<Integer>> results = exec.invokeAll(tasks);

        for (Future<Integer> future : results) {
            System.out.println(future.get());
        }

        // 关闭线程池
        exec.shutdown();
        System.out.println("线程任务执行结束");
        System.err.println("执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
    }


    @Test
    public void spiderStockDealDataVol() throws InterruptedException, ExecutionException {
         String dealDay = "2020-05-21";
        // 开始时间
        long start = System.currentTimeMillis();
        List<StockSectorLevel> list = stockSectorLevelService.selectList(new EntityWrapper<StockSectorLevel>().where("focus_level > 0 and stock_code not in (SELECT stock_code FROM stock_deal_data_vol  WHERE deal_time REGEXP {0} )" , dealDay).groupBy("stock_code").orderBy("stock_code desc"));
        // 每500条数据开启一条线程
        int threadSize = 500;
        // 总数据条数
        int dataSize = list.size();
        // 线程数
        int threadNum = dataSize / threadSize + 1;
        // 定义标记,过滤threadNum为整数
        boolean special = dataSize % threadSize == 0;

        // 创建一个线程池
        ExecutorService exec = Executors.newFixedThreadPool(threadNum);
        // 定义一个任务集合
        List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
        Callable<Integer> task = null;
        List<StockSectorLevel> cutList = null;

        // 确定每条线程的数据
        for (int i = 0; i < threadNum; i++) {
            if (i == threadNum - 1) {
                if (special) {
                    break;
                }
                cutList = list.subList(threadSize * i, dataSize);
            } else {
                cutList = list.subList(threadSize * i, threadSize * (i + 1));
            }
            // System.out.println("第" + (i + 1) + "组：" + cutList.toString());
            final List<StockSectorLevel> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    for(StockSectorLevel sectorLevel : listStr){
                        System.out.println(sectorLevel.getStockCode());
                        List<StockDealDataVol> dataList = stockDealDataVolService.spiderStockDealDataVol(sectorLevel.getStockCode() , dealDay);
                        if(null!=dataList&&dataList.size()>0){
                            int pointsDataLimit = 200;//限制条数
                            Integer size = dataList.size();
                            //判断是否有必要分批
                            if(pointsDataLimit<size){
                                int part = size/pointsDataLimit;//分批数
                                System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                for (int i = 0; i < part; i++) {
                                    List<StockDealDataVol> listPage = dataList.subList(0, pointsDataLimit);
                                    stockDealDataVolService.insertBatch(listPage);
                                    //剔除
                                    dataList.subList(0, pointsDataLimit).clear();
                                }
                                if(!dataList.isEmpty()){
                                    stockDealDataVolService.insertBatch(dataList);
                                }
                            }else{
                                stockDealDataVolService.insertBatch(dataList);
                            }
                        }else{
                            System.out.println("没有数据!!!");
                        }

                        Thread.sleep(1000);

                    }
                    return 1;
                }
            };
            // 这里提交的任务容器列表和返回的Future列表存在顺序对应的关系
            tasks.add(task);
        }

        List<Future<Integer>> results = exec.invokeAll(tasks);

        for (Future<Integer> future : results) {
            System.out.println(future.get());
        }

        // 关闭线程池
        exec.shutdown();
        System.out.println("线程任务执行结束");
        System.err.println("执行任务消耗了 ：" + (System.currentTimeMillis() - start) + "毫秒");
    }
}
