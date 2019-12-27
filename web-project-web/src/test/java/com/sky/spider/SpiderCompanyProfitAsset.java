package com.sky.spider;

import com.sky.model.StockCompanySector;
import com.sky.service.StockCompanyAssetService;
import com.sky.service.StockCompanyCashFlowService;
import com.sky.service.StockCompanyProfitService;
import com.sky.service.StockCompanySectorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Administrator on 2019/12/27/027.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpiderCompanyProfitAsset {

    @Autowired
    private StockCompanySectorService stockCompanySectorService;

    @Autowired
    private StockCompanyProfitService stockCompanyProfitService ;

    @Autowired
    private StockCompanyAssetService stockCompanyAssetService;

    @Autowired
    private StockCompanyCashFlowService stockCompanyCashFlowService;

    @Test
    public void spiderStockCompanyProfit() throws InterruptedException {
        List<StockCompanySector> list = stockCompanySectorService.selectList(null);
        for(StockCompanySector sector : list){
            for(int x = 0 ; x < 50 ; x ++){
                boolean just = stockCompanyProfitService.spiderStockCompanyProfit(sector.getStockCode() , x);
                if(just){
                    Thread.sleep(300);
                }else{
                    break;
                }
            }
        }
    }

    @Test
    public void spiderStockCompanyAsset() throws InterruptedException {
        List<StockCompanySector> list = stockCompanySectorService.selectList(null);
        for(StockCompanySector sector : list){
            for(int x = 0 ; x < 50 ; x ++){
                boolean just = stockCompanyAssetService.spiderStockCompanyAsset(sector.getStockCode() , x);
                if(just){
                    Thread.sleep(300);
                }else{
                    break;
                }
            }
        }
    }

    @Test
    public void spiderStockCompanyCashFlow() throws InterruptedException {
//        List<StockCompanySector> list = stockCompanySectorService.selectList(null);
//        for(StockCompanySector sector : list){
        for(int x = 0 ; x < 50 ; x ++){
            boolean just = stockCompanyCashFlowService.spiderStockCompanyCashFlow("000333" , x);
            if(just){
                Thread.sleep(300);
            }else{
                break;
            }
        }
//        }
    }


}
