package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.FuturesClass;
import com.sky.model.FuturesDealData;
import com.sky.model.StockCompanySector;
import com.sky.model.StockDealData;
import com.sky.service.FuturesClassService;
import com.sky.service.FuturesDealDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2020/1/31/031.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FutureDataTest {

    @Autowired
    private FuturesClassService futuresClassService ;


    @Autowired
    private FuturesDealDataService futuresDealDataService ;

    @Test
    public void processFutureDataTime() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
//        List<StockCode> list = stockCodeService.selectList(new EntityWrapper<StockCode>().where("stock_code NOT IN(SELECT stock_code FROM stock_deal_data)"));
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
                    for(FuturesClass stockCode : listStr){

                        List<FuturesDealData> dataList = futuresDealDataService.selectList(new EntityWrapper<FuturesDealData>().where("futures_code = {0} and deal_period = 1 and point_year is null " ,stockCode.getFuturesCode()));
                        for(FuturesDealData dealData : dataList){
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
                                    List<FuturesDealData> listPage = dataList.subList(0, pointsDataLimit);
                                    futuresDealDataService.updateBatchById(listPage);
                                    //剔除
                                    dataList.subList(0, pointsDataLimit).clear();
                                }
                                if(!dataList.isEmpty()){
                                    futuresDealDataService.updateBatchById(dataList);
                                }
                            }else{
                                futuresDealDataService.updateBatchById(dataList);
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
