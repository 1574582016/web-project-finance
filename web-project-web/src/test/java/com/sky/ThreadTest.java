package com.sky;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.*;
import com.sky.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ThinkPad on 2019/9/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadTest {

    @Autowired
    private StockCodeService stockCodeService ;

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Autowired
    private SectorDealDataService sectorDealDataService ;

    @Autowired
    private StockMarketClassService stockMarketClassService ;

    @Autowired
    private FuturesClassService futuresClassService;

    @Autowired
    private FuturesDealDataService futuresDealDataService;

    @Test
    public void test() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
//        List<StockCode> list = stockCodeService.selectList(new EntityWrapper<StockCode>().where("stock_code NOT IN(SELECT stock_code FROM stock_deal_data)"));
        List<StockCode> list = stockCodeService.selectList(null);
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
        List<StockCode> cutList = null;

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
            final List<StockCode> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    for(StockCode stockCode : listStr){
                        System.out.println("================stockCode=========================" + stockCode);
                        String mk = "1";
                        if(stockCode.getStockMarket().equals("sz")){
                            mk = "2";
                        }
                        List<StockDealData> dataList = stockDealDataService.spiderStockDealData(1, stockCode.getStockCode() ,mk);

                        if(null!=dataList&&dataList.size()>0){
                            int pointsDataLimit = 200;//限制条数
                            Integer size = dataList.size();
                            //判断是否有必要分批
                            if(pointsDataLimit<size){
                                int part = size/pointsDataLimit;//分批数
                                System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                for (int i = 0; i < part; i++) {
                                    List<StockDealData> listPage = dataList.subList(0, pointsDataLimit);
                                    stockDealDataService.insertBatch(listPage);
                                    //剔除
                                    dataList.subList(0, pointsDataLimit).clear();
                                }
                                if(!dataList.isEmpty()){
                                    stockDealDataService.insertBatch(dataList);
                                }
                            }else{
                                stockDealDataService.insertBatch(dataList);
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
    public void test2() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("class_type = '行业板块'");
        List<StockMarketClass> list = stockMarketClassService.selectList(entityWrapper);
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
        List<StockMarketClass> cutList = null;

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
            final List<StockMarketClass> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    for(StockMarketClass marketClass : listStr){
                        System.out.println("================stockCode=========================" + marketClass);
                        String marketCode = marketClass.getClassCode();
                        marketCode = marketCode.substring(0,marketCode.length()-1);
                        for(int t = 1 ; t <=7 ; t++){
                            List<SectorDealData> dataList = sectorDealDataService.spiderSectorDealData(t , marketCode);

                            if(null!=dataList&&dataList.size()>0){
                                int pointsDataLimit = 200;//限制条数
                                Integer size = dataList.size();
                                //判断是否有必要分批
                                if(pointsDataLimit<size){
                                    int part = size/pointsDataLimit;//分批数
                                    System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                    for (int i = 0; i < part; i++) {
                                        List<SectorDealData> listPage = dataList.subList(0, pointsDataLimit);
                                        sectorDealDataService.insertBatch(listPage);
                                        //剔除
                                        dataList.subList(0, pointsDataLimit).clear();
                                    }
                                    if(!dataList.isEmpty()){
                                        sectorDealDataService.insertBatch(dataList);
                                    }
                                }else{
                                    sectorDealDataService.insertBatch(dataList);
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

    @Autowired
    private IndexDealDataService indexDealDataService ;

    @Test
    public void test3() throws InterruptedException, ExecutionException {
      List<IndexDealData> list = new ArrayList<>();
        String klt = "";
      for(int t = 1 ; t <=7 ;t++){
          switch (t){
              case 1 : klt = "101" ;break;
              case 2 : klt = "102" ;break;
              case 3 : klt = "103" ;break;

              case 4 : klt = "60" ;break;
              case 5 : klt = "30" ;break;
              case 6 : klt = "15" ;break;
              case 7 : klt = "5" ;break;
          }
          List<IndexDealData> singleList = indexDealDataService.spiderIndexDealData(t , "1.000001" ,klt);
          list.addAll(singleList);
      }

      for(IndexDealData dealData : list){
          indexDealDataService.insert(dealData);
      }

    }


    @Autowired
    private HotDealDataService hotDealDataService ;


    @Test
    public void test5() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
        EntityWrapper<StockMarketClass> entityWrapper = new EntityWrapper();
        entityWrapper.where("class_type = '概念板块'");
        List<StockMarketClass> list = stockMarketClassService.selectList(entityWrapper);
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
        List<StockMarketClass> cutList = null;

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
            final List<StockMarketClass> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    for(StockMarketClass marketClass : listStr){
                        System.out.println("================stockCode=========================" + marketClass);
                        String marketCode = marketClass.getClassCode();
                        for(int t = 1 ; t <=7 ; t++){
                            List<HotDealData> dataList = hotDealDataService.spiderHotDealData(t , marketCode);

                            if(null!=dataList&&dataList.size()>0){
                                int pointsDataLimit = 200;//限制条数
                                Integer size = dataList.size();
                                //判断是否有必要分批
                                if(pointsDataLimit<size){
                                    int part = size/pointsDataLimit;//分批数
                                    System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                    for (int i = 0; i < part; i++) {
                                        List<HotDealData> listPage = dataList.subList(0, pointsDataLimit);
                                        hotDealDataService.insertBatch(listPage);
                                        //剔除
                                        dataList.subList(0, pointsDataLimit).clear();
                                    }
                                    if(!dataList.isEmpty()){
                                        hotDealDataService.insertBatch(dataList);
                                    }
                                }else{
                                    hotDealDataService.insertBatch(dataList);
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
    public void test6() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
        List<FuturesClass> list = futuresClassService.selectList(null);
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
        List<FuturesClass> cutList = null;

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
            final List<FuturesClass> listStr = cutList;
            task = new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    for(FuturesClass marketClass : listStr){
                        System.out.println("================stockCode=========================" + marketClass);
                        String marketCode = marketClass.getFuturesCode();
//                        for(int t = 1 ; t <=7 ; t++){
                            List<FuturesDealData> dataList = futuresDealDataService.spiderFuturesDealData(1 , marketCode);

                            if(null!=dataList&&dataList.size()>0){
                                int pointsDataLimit = 200;//限制条数
                                Integer size = dataList.size();
                                //判断是否有必要分批
                                if(pointsDataLimit<size){
                                    int part = size/pointsDataLimit;//分批数
                                    System.out.println("共有 ： "+size+"条，！"+" 分为 ："+part+"批");
                                    for (int i = 0; i < part; i++) {
                                        List<FuturesDealData> listPage = dataList.subList(0, pointsDataLimit);
                                        futuresDealDataService.insertBatch(listPage);
                                        //剔除
                                        dataList.subList(0, pointsDataLimit).clear();
                                    }
                                    if(!dataList.isEmpty()){
                                        futuresDealDataService.insertBatch(dataList);
                                    }
                                }else{
                                    futuresDealDataService.insertBatch(dataList);
                                }
                            }else{
                                System.out.println("没有数据!!!");
                            }

                            Thread.sleep(1000);
//                        }
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
