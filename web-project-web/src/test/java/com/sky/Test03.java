package com.sky;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockCompanySector;
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

    @Test
    public void test01(){
        List<StockCompanySector> list = stockCompanySectorService.selectList(new EntityWrapper<StockCompanySector>().where("LEFT(stock_code,1) != '3' AND LEFT(stock_code,2) != '68'"));
        List<StockDealDateRank_VO> topList = new ArrayList<>();
        List<StockDealDateRank_VO> middleList = new ArrayList<>();
        List<StockDealDateRank_VO> bottomList = new ArrayList<>();
        for(StockCompanySector sector : list){
            StockDealDateRank_VO rankVo = stockDealDataService.caculateBoll(sector.getStockCode() ,DateUtils.getDate() , "5");
            if(rankVo.getIsTop().compareTo(BigDecimal.ZERO) > 0){
                topList.add(rankVo);
            }
            if(rankVo.getIsMiddle().compareTo(BigDecimal.ZERO) > 0){
                middleList.add(rankVo);
            }
            if(rankVo.getIsBottom().compareTo(BigDecimal.ZERO) > 0){
                bottomList.add(rankVo);
            }
        }
        System.out.println(topList.toString());
        System.out.println(middleList.toString());
        System.out.println(bottomList.toString());

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
