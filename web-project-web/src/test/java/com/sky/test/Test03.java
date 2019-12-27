package com.sky.test;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockChoseStrategy;
import com.sky.model.StockCompanySector;
import com.sky.model.StockDealData;
import com.sky.service.StockChoseStrategyService;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockDealDataService;
import com.sky.vo.StockDealDateRank_VO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by ThinkPad on 2019/10/8.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test03 {

    @Autowired
    private StockDealDataService stockDealDataService ;

    @Autowired
    private StockCompanySectorService stockCompanySectorService ;

    @Autowired
    private StockChoseStrategyService stockChoseStrategyService;

    @Test
    public void test01() throws InterruptedException {
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != '68'"));
        for(StockCompanySector sector : list){
            StockDealDateRank_VO rankVo = stockDealDataService.caculateBoll(sector.getStockCode() ,DateUtils.getDate() , "50");



        }

    }


    @Test
    public void test001() throws InterruptedException, ExecutionException {

        // 开始时间
        long start = System.currentTimeMillis();
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,2) != '68'"));
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
                    for(StockCompanySector sector : listStr){
                        StockDealDateRank_VO rankVo = stockDealDataService.caculateBoll(sector.getStockCode() ,DateUtils.getDate() , "50");
                        saveResult( rankVo);
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

    private void saveResult(StockDealDateRank_VO rankVo){
        if(rankVo != null){
            StockChoseStrategy strategy = new StockChoseStrategy();
            strategy.setStrategyType(1);
            strategy.setStockCode(rankVo.getStockCode());
            strategy.setDealTime(rankVo.getDealTime());
            strategy.setOpenPrice(rankVo.getOpenPrice());
            strategy.setHighPrice(rankVo.getHighPrice());
            strategy.setLowPrice(rankVo.getLowPrice());
            strategy.setClosePrice(rankVo.getClosePrice());
            strategy.setAveragePrice(rankVo.getAveragePrice());
            strategy.setStandarPrice(rankVo.getStandarPrice());
            strategy.setTopPrice(rankVo.getTopPrice());
            strategy.setBottomPrice(rankVo.getBottomPrice());
            strategy.setTopDistance(rankVo.getTopDistance());
            strategy.setBottomDistance(rankVo.getBottomDistance());
            strategy.setMiddleDistance(rankVo.getMiddleDistance());
            strategy.setAverageStock(rankVo.getAverageStock());
            strategy.setIsUpper(rankVo.getIsUpper() == null ? 0 : rankVo.getIsUpper().intValue());
            if(rankVo.getIsTop() != null && rankVo.getIsTop().compareTo(BigDecimal.ZERO) > 0){
                strategy.setIsTop(1);
            }
            if(rankVo.getIsMiddle() != null && rankVo.getIsMiddle().compareTo(BigDecimal.ZERO) > 0){
                strategy.setIsMiddle(1);
            }
            if(rankVo.getIsBottom() != null && rankVo.getIsBottom().compareTo(BigDecimal.ZERO) > 0){
                strategy.setIsBottom(1);
            }
            StockChoseStrategy choseStrategy = stockChoseStrategyService.selectOne(new EntityWrapper<StockChoseStrategy>().where("stock_code = {0} and deal_time = {1} " , strategy.getStockCode() , strategy.getDealTime()));
            if(choseStrategy == null){
                stockChoseStrategyService.insert(strategy);
            }
        }

    }

    @Test
    public void test02(){
        StockDealDateRank_VO rankVo = stockDealDataService.caculateBoll("000001" ,DateUtils.getDate() , "50");
        System.out.println(rankVo.toString());

    }

/**
 * 1、市盈率——P/E——PER——Price Earnings Ratio
 *          ——本益比/股价收益比率
 *          ——股票价格/每股盈利——EPS
 *          ——比较不同价格的股票是否被高估或者低估的指标
 *          ——如果一家公司股票的市盈率过高，那么该股票的价格具有泡沫，价值被高估
 *          ——当一家公司增长迅速以及未来的业绩增长非常看好时，利用市盈率比较不同股票的投资价值时，这些股票必须属于同一个行业，因为此时公司的每股收益比较接近，相互比较才有效
 *          ——
 *
 * 2、市销率
 *
 * 3、市净率
 *
 * 4、总市值
 *
 * 5、净资产
 *
 * 6、净利润
 *
 * 7.毛利率
 *
 * 8、净利率
 *
 * 9、净资产收益率
 *
 * 10、每股收益增长率
 *
 * 11、营业收入增长率
 *
 * 12、净利润增长率
 *
 * 13、盈利增长速度
 *
 * 14、资产周转率
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
  */
}
