package com.sky.spider;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.HttpUtil;
import com.sky.model.StockCompanySector;
import com.sky.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/12/27/027.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderCompanyBaseInfo {

    private static final long  sleep = 300;

    @Autowired
    private StockCompanyBaseService stockCompanyBaseService ;

    @Autowired
    private StockCompanyProductService stockCompanyProductService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService;

    @Test
    public void processStockCompanyBace() throws InterruptedException {
        List<StockCompanySector> codeList = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("stock_code NOT IN (SELECT stock_a_code FROM stock_company_base WHERE stock_a_code IS NOT NULL)"));
        for (StockCompanySector sector : codeList) {
            String stockCode = sector.getStockCode() ;
            String str = stockCode.substring(0,1);
            System.out.println(stockCode + "=======================" + str);
            String market = "sh";
            if(str.equals("0") || str.equals("3")){
                market = "sz";
            }
            stockCompanyBaseService.spiderStockCompanyBase(market+stockCode);
            Thread.sleep(sleep);
        }

    }

    @Test
    public void processStockCompanyProduct() throws InterruptedException {
        List<StockCompanySector> sectorList = stockCompanySectorService.selectList(null);
        for (StockCompanySector stockCode : sectorList) {
            String market = "sz";
            if(stockCode.getStockCode().substring(0,1).equals("6")){
                market = "sh";
            }
            stockCompanyProductService.spiderStockCompanyProduct(market , stockCode.getStockCode());
            Thread.sleep(sleep);
        }
    }

    @Test
    public void processStockCapital() throws InterruptedException, ExecutionException {
        // 开始时间
        long start = System.currentTimeMillis();
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("update_time not regexp '2020-05-13'"));
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
                        System.out.println("================stockCode=========================" + stockCode.getStockCode());
                        String market = "sz";
                        if(stockCode.getStockCode().substring(0,1).equals("6")){
                            market = "sh";
                        }
                        String url = "http://emweb.securities.eastmoney.com/CapitalStockStructure/CapitalStockStructureAjax?code=" + market + stockCode.getStockCode();
                        String content = HttpUtil.getHtmlContentByGet(url);
                        JSONObject jsonObject = JSONObject.parseObject(content).getJSONObject("CapitalStockStructureDetail");
                        String ltgfhf = jsonObject.getString("ltgfhf").replace("," , "");//总股本
                        String yssltag = jsonObject.getString("yssltag").replace("," , "");//A股流通股份
                        String yssltbg = jsonObject.getString("yssltbg").replace("," , "").replace("--" , "");//B股流通股股份
                        String jwssltg = jsonObject.getString("jwssltg").replace("," , "").replace("--" , "");//境外流通股份
                        String ltsxgf = jsonObject.getString("ltsxgf").replace("," , "").replace("--" , "");//流通受限股份
                        String wltgf = jsonObject.getString("wltgf").replace("," , "").replace("--" , "");//未流通股份
                        stockCode.setTotalStockCount(ltgfhf);
                        stockCode.setFlowStockCount(yssltag);
                        stockCode.setFlowStockCountB(yssltbg);
                        stockCode.setFlowStockCountJY(jwssltg);
                        stockCode.setLimitStockCount(ltsxgf);
                        stockCode.setUnflowStockCount(wltgf);
                        stockCode.setUpdateTime(new Date());
                        stockCompanySectorService.updateById(stockCode);
                        Thread.sleep(sleep);
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
