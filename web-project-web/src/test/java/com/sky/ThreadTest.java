package com.sky;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.model.SectorDealData;
import com.sky.model.StockCode;
import com.sky.model.StockDealData;
import com.sky.model.StockMarketClass;
import com.sky.service.SectorDealDataService;
import com.sky.service.StockCodeService;
import com.sky.service.StockDealDataService;
import com.sky.service.StockMarketClassService;
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


    @Test
    public void test() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
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
                        List<StockDealData> dataList = stockDealDataService.spiderStockDealData(1, stockCode.getStockCode());

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
                        List<SectorDealData> dataList = sectorDealDataService.spiderSectorDealData(1 , marketCode);

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
