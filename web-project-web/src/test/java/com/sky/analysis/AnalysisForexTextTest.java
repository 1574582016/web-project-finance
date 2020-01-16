package com.sky.analysis;

import com.sky.model.ForexDealData;
import com.sky.model.ForexDealDataOneMinute;
import com.sky.model.StockDealData;
import com.sky.service.ForexDealDataOneMinuteService;
import com.sky.service.ForexDealDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by ThinkPad on 2020/1/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalysisForexTextTest {

    @Autowired
    private ForexDealDataService forexDealDataService ;

    @Autowired
    private ForexDealDataOneMinuteService forexDealDataOneMinuteService ;

    @Test
    public void analysisForexText(){
        List<ForexDealDataOneMinute> dataList =  forexDealDataService.analysisForexDataFile("E://XAUUSD.txt");

        if (null != dataList && dataList.size() > 0) {
            int pointsDataLimit = 200;//限制条数
            Integer size = dataList.size();
            //判断是否有必要分批
            if (pointsDataLimit < size) {
                int part = size / pointsDataLimit;//分批数
                System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");
                for (int i = 0; i < part; i++) {
                    List<ForexDealDataOneMinute> listPage = dataList.subList(0, pointsDataLimit);
                    forexDealDataOneMinuteService.insertBatch(listPage);

                    //剔除
                    dataList.subList(0, pointsDataLimit).clear();
                }
                if (!dataList.isEmpty()) {
                    forexDealDataOneMinuteService.insertBatch(dataList);
                }
            } else {
                forexDealDataOneMinuteService.insertBatch(dataList);
            }
        } else {
            System.out.println("没有数据!!!");
        }
    }
}
