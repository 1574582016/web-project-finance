package com.sky.spider;

import com.sky.model.StockCompanySector;
import com.sky.model.StockDealData;
import com.sky.model.StockMoneyFlow;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockMoneyFlowService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ThinkPad on 2020/1/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderStockMoneyFlow {

    @Autowired
    private StockCompanySectorService stockCompanySectorService;

    @Autowired
    private StockMoneyFlowService stockMoneyFlowService ;

    @Test
    public void spiderStockMoneyFlow() throws InterruptedException, ExecutionException {

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
                        try{
                            stockMoneyFlowService.spiderStockMoneyFlow(stockCode.getStockCode());
                            Thread.sleep(1000);
                        }catch (Exception e){
                            e.printStackTrace();
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
}
