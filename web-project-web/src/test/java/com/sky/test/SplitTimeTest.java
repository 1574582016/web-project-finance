package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockCompanySector;
import com.sky.model.StockDealData;
import com.sky.model.StockHandleDealData;
import com.sky.model.StockRiseRate;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockDealDataService;
import com.sky.service.StockHandleDealDataService;
import com.sky.service.StockRiseRateService;
import com.sky.vo.HandleDealMonthWeek_VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ThinkPad on 2019/11/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SplitTimeTest {

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService ;

    @Autowired
    private StockHandleDealDataService stockHandleDealDataService ;

    @Autowired
    private StockRiseRateService stockRiseRateService ;

    @Test
    public void test(){
       String startDay = "2015-01-01";
       for(int i = 0 ; i < 1000 ; i ++){
           Date pointDate = DateUtils.addDays(DateUtils.parseDate(startDay , "yyyy-MM-dd") , i);
           String pointDay = DateUtils.formatDate( pointDate, "yyyy-MM-dd");
           JSONObject jsonObject = DateUtils.getYearMonthWeekDay(pointDay);
           System.out.println(pointDay + "================" + jsonObject.getInteger("year") + "年" + jsonObject.getInteger("month") + "月" + jsonObject.getInteger("week") + "周" + jsonObject.getInteger("day") + "天");
       }
    }

    @Test
    public void test01(){
        List<StockDealData> list = stockDealDataService.selectList(new EntityWrapper<StockDealData>().where("stock_code = '000333' and deal_period = 1 and deal_time >= '2015-01-01'"));
        for(StockDealData dealData : list){
            String dealTime = dealData.getDealTime() ;
            System.out.println(dealTime +"=========================="+ DateUtils.getYearMonthWeekDay(dealTime).toString());
        }
    }

    @Test
    public void test02(){
        String pointDay = "2019-11-22";
        Date pointDate = DateUtils.parseDate(pointDay , "yyyy-MM-dd");
        String year = DateUtils.getYear(pointDate);
        String month = DateUtils.getMonth(pointDate);
        String week = DateUtils.getWeek(pointDate);
        String day = DateUtils.getDay(pointDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(pointDate);
        int monthWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        System.out.println(year + "================" + month + "=============" + monthWeek + "=================" + week + "==================" + day);

    }


    @Test
    public void test03(){
        List<StockCompanySector> sectorList = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != 68"));
        for(StockCompanySector sector : sectorList){
            List<StockDealData> list = stockDealDataService.selectList(new EntityWrapper<StockDealData>().where("stock_code = {0} and deal_period = 1 " ,sector.getStockCode()));
            for(StockDealData dealData : list){
                String dealTime = dealData.getDealTime() ;
                JSONObject jsonObject = DateUtils.getYearMonthWeekDay(dealTime);
                dealData.setPointYear(jsonObject.getInteger("year"));
                dealData.setPointMonth(jsonObject.getInteger("month"));
                dealData.setPointWeek(jsonObject.getInteger("week"));
                dealData.setPointDay(jsonObject.getInteger("day"));
                stockDealDataService.updateById(dealData);
            }
        }
    }

    @Test
    public void test04(){
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != 68"));
        for(StockCompanySector sector : list){
//           for(int i = 2 ; i <= 3 ; i++){
//               List<StockHandleDealData> dataList = stockHandleDealDataService.getGroupDealDataList(sector.getStockCode() , i+"");
//               if(dataList.size()>0){
//                   stockHandleDealDataService.insertBatch(dataList);
//               }
//           }

            stockHandleDealDataService.updateHandleClosePrice(sector.getStockCode());
        }

//        List<StockHandleDealData> list = stockHandleDealDataService.getGroupDealDataList("600261" , "2");
//        boolean just = stockHandleDealDataService.insertBatch(list);
//        if(just){
//            stockHandleDealDataService.updateHandleClosePrice("600261");
//        }
    }

    @Test
    public void test0404(){
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != 68 and stock_code IN (SELECT stock_code FROM stock_handle_deal_data WHERE close_price = 0)"));
        for(StockCompanySector sector : list){
            List<StockHandleDealData> handleList = stockHandleDealDataService.selectList(new EntityWrapper<StockHandleDealData>().where("close_price = 0 and stock_code = {0}" , sector.getStockCode()));
            for(StockHandleDealData dealData : handleList){
                StockDealData stockDealData = stockDealDataService.selectOne(new EntityWrapper<StockDealData>().where("stock_code = {0} and deal_time = {1} and deal_period = 1" , dealData.getStockCode() , dealData.getEndTime()));
                dealData.setClosePrice(stockDealData.getClosePrice());
                stockHandleDealDataService.updateById(dealData);
            }
        }

    }



    @Test
    public void test333() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
//        List<StockCode> list = stockCodeService.selectList(new EntityWrapper<StockCode>().where("stock_code NOT IN(SELECT stock_code FROM stock_deal_data)"));
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != 68"));
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

                        List<StockDealData> dataList = stockDealDataService.selectList(new EntityWrapper<StockDealData>().where("stock_code = {0} and deal_period = 1 and point_year is null " ,stockCode.getStockCode()));
                        for(StockDealData dealData : dataList){
                            String dealTime = dealData.getDealTime() ;
                            JSONObject jsonObject = DateUtils.getYearMonthWeekDay(dealTime);
                            dealData.setPointYear(jsonObject.getInteger("year"));
                            dealData.setPointMonth(jsonObject.getInteger("month"));
                            dealData.setPointWeek(jsonObject.getInteger("week"));
                            dealData.setPointDay(jsonObject.getInteger("day"));
                        }
                        if(null!=dataList&&dataList.size()>0){
                            int pointsDataLimit = 200;//限制条数
                            Integer size = dataList.size();
                            //判断是否有必要分批
                            if(pointsDataLimit<size){
                                int part = size/pointsDataLimit;//分批数
                                System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                for (int i = 0; i < part; i++) {
                                    List<StockDealData> listPage = dataList.subList(0, pointsDataLimit);
                                    stockDealDataService.updateBatchById(listPage);
                                    //剔除
                                    dataList.subList(0, pointsDataLimit).clear();
                                }
                                if(!dataList.isEmpty()){
                                    stockDealDataService.updateBatchById(dataList);
                                }
                            }else{
                                stockDealDataService.updateBatchById(dataList);
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

    @Test
    public void test4444() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
//        List<StockCode> list = stockCodeService.selectList(new EntityWrapper<StockCode>().where("stock_code NOT IN(SELECT stock_code FROM stock_deal_data)"));
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != 68 and stock_code IN (SELECT stock_code FROM stock_handle_deal_data WHERE close_price = 0)"));
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

                        List<StockHandleDealData> dataList = stockHandleDealDataService.selectList(new EntityWrapper<StockHandleDealData>().where("close_price = 0 and stock_code = {0}" , stockCode.getStockCode()));
                        if(null!=dataList&&dataList.size()>0){
                            int pointsDataLimit = 200;//限制条数
                            Integer size = dataList.size();
                            //判断是否有必要分批
                            if(pointsDataLimit<size){
                                int part = size/pointsDataLimit;//分批数
                                System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                for (int i = 0; i < part; i++) {
                                    List<StockHandleDealData> listPage = dataList.subList(0, pointsDataLimit);
                                    for(StockHandleDealData dealData : listPage){
                                        StockDealData stockDealData = stockDealDataService.selectOne(new EntityWrapper<StockDealData>().where("stock_code = {0} and deal_time = {1} and deal_period = 1" , dealData.getStockCode() , dealData.getEndTime()));
                                        dealData.setClosePrice(stockDealData.getClosePrice());
                                        stockHandleDealDataService.updateById(dealData);
                                    }
                                    //剔除
                                    dataList.subList(0, pointsDataLimit).clear();
                                }
                                if(!dataList.isEmpty()){
                                    for(StockHandleDealData dealData : dataList){
                                        StockDealData stockDealData = stockDealDataService.selectOne(new EntityWrapper<StockDealData>().where("stock_code = {0} and deal_time = {1} and deal_period = 1" , dealData.getStockCode() , dealData.getEndTime()));
                                        dealData.setClosePrice(stockDealData.getClosePrice());
                                        stockHandleDealDataService.updateById(dealData);
                                    }
                                }
                            }else{
                                for(StockHandleDealData dealData : dataList){
                                    StockDealData stockDealData = stockDealDataService.selectOne(new EntityWrapper<StockDealData>().where("stock_code = {0} and deal_time = {1} and deal_period = 1" , dealData.getStockCode() , dealData.getEndTime()));
                                    dealData.setClosePrice(stockDealData.getClosePrice());
                                    stockHandleDealDataService.updateById(dealData);
                                }
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


    @Test
    public void test555() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
//        List<StockCode> list = stockCodeService.selectList(new EntityWrapper<StockCode>().where("stock_code NOT IN(SELECT stock_code FROM stock_deal_data)"));
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != 68 and stock_code not IN (SELECT stock_code FROM stock_rise_rate)"));
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
                        for(int t = 2 ; t <= 3 ; t ++){
                            List<StockRiseRate> dataList = stockHandleDealDataService.getCalculateHandleDealMonthWeekList(stockCode.getStockCode() , t + "" , "2015-01-01" , "2020-01-01");
                            if(null!=dataList&&dataList.size()>0){
                                int pointsDataLimit = 200;//限制条数
                                Integer size = dataList.size();
                                //判断是否有必要分批
                                if(pointsDataLimit<size){
                                    int part = size/pointsDataLimit;//分批数
                                    System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                    for (int i = 0; i < part; i++) {
                                        List<StockRiseRate> listPage = dataList.subList(0, pointsDataLimit);
                                        stockRiseRateService.insertBatch(listPage);
                                        //剔除
                                        dataList.subList(0, pointsDataLimit).clear();
                                    }
                                    if(!dataList.isEmpty()){
                                        stockRiseRateService.insertBatch(dataList);
                                    }
                                }else{
                                    stockRiseRateService.insertBatch(dataList);
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
    public void test0009(){
        List<StockRiseRate> dataList = stockDealDataService.getCalculateHandleDealDayList("000333" , "1" , "2015-01-01" , "2020-01-01");
    }

    @Test
    public void test666() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
//        List<StockCode> list = stockCodeService.selectList(new EntityWrapper<StockCode>().where("stock_code NOT IN(SELECT stock_code FROM stock_deal_data)"));
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != 68 and stock_code not IN (SELECT stock_code FROM stock_rise_rate where deal_period = 1)"));
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

                            List<StockRiseRate> dataList = stockDealDataService.getCalculateHandleDealDayList(stockCode.getStockCode() , "1" , "2015-01-01" , "2020-01-01");
                            if(null!=dataList&&dataList.size()>0){
                                int pointsDataLimit = 200;//限制条数
                                Integer size = dataList.size();
                                //判断是否有必要分批
                                if(pointsDataLimit<size){
                                    int part = size/pointsDataLimit;//分批数
                                    System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                    for (int i = 0; i < part; i++) {
                                        List<StockRiseRate> listPage = dataList.subList(0, pointsDataLimit);
                                        stockRiseRateService.insertBatch(listPage);
                                        //剔除
                                        dataList.subList(0, pointsDataLimit).clear();
                                    }
                                    if(!dataList.isEmpty()){
                                        stockRiseRateService.insertBatch(dataList);
                                    }
                                }else{
                                    stockRiseRateService.insertBatch(dataList);
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
