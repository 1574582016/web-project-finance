package com.sky;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sky.core.utils.CommonHttpUtil;
import com.sky.core.utils.DateUtils;
import com.sky.model.StockCompanySector;
import com.sky.model.StockRiseRate;
import com.sky.service.StockCompanyAssetService;
import com.sky.service.StockCompanyProfitService;
import com.sky.service.StockCompanySectorService;
import com.sky.service.StockRiseRateService;
import com.sky.vo.StockCompanyAssetVO;
import com.sky.vo.StockCompanyProfitVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ThinkPad on 2019/11/5.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test06 {

    @Autowired
    private StockCompanySectorService stockCompanySectorService;

    @Autowired
    private StockCompanyProfitService stockCompanyProfitService;

    @Autowired
    private StockCompanyAssetService stockCompanyAssetService;

    @Autowired
    private StockRiseRateService stockRiseRateService ;

    @Test
    public void test01(){
        try {
            List<StockCompanySector> list = stockCompanySectorService.selectList(null);
            for(StockCompanySector sector : list){
                List<StockCompanyProfitVO> voList = stockCompanyProfitService.getCompanyProfitGrowList(sector.getStockCode());
                if(voList.size() > 0){
                    JSONObject jsonObject = stockCompanyProfitLevel(voList);
                    BigDecimal profit_score = jsonObject.getBigDecimal("rightRightRate");
                    BigDecimal profit_grow_score = jsonObject.getBigDecimal("isGrowRate");
                    sector.setProfitGrowScore(profit_grow_score);
                    sector.setProfitScore(profit_score);
                }

                List<StockCompanyAssetVO> voList2 = stockCompanyAssetService.getCompanyAssetGrowList(sector.getStockCode());
                if(voList2.size() > 0){
                    JSONObject jsonObject2 = stockCompanyAssetLevel(voList2);
                    BigDecimal asset_score = jsonObject2.getBigDecimal("assetDebtRate");
                    BigDecimal asset_grow_score = jsonObject2.getBigDecimal("averageGrowLevel");
                    sector.setAssetScore(asset_score);
                    sector.setAssetGrowScore(asset_grow_score);
                }
                stockCompanySectorService.updateById(sector);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private JSONObject stockCompanyProfitLevel(List<StockCompanyProfitVO> list){
        String nowYear = DateUtils.getYear();
        String sixYear = Integer.parseInt(nowYear) - 6 + "";
        String fiveYear = Integer.parseInt(nowYear) - 5 + "";
        String fourYear = Integer.parseInt(nowYear) - 4 + "";
        String threeYear = Integer.parseInt(nowYear) - 3 + "";
        String twoYear = Integer.parseInt(nowYear) - 2 + "";
        String oneYear = Integer.parseInt(nowYear) - 1 + "";

        int totalIsGrow = 0;
        int totalIsBelong = 0;
        int totalBelongOther = 0;
        int num = 0;
        for(StockCompanyProfitVO profit : list){
            if(sixYear.equals(profit.getPublishYear()) ||
                    fiveYear.equals(profit.getPublishYear()) ||
                    fourYear.equals(profit.getPublishYear()) ||
                    threeYear.equals(profit.getPublishYear()) ||
                    twoYear.equals(profit.getPublishYear()) ||
                    oneYear.equals(profit.getPublishYear())
                    ){
                if(profit.getIsGrow() == 1){
                    totalIsGrow += 1;
                }
                if(profit.getIsBelong() == 1){
                    totalIsBelong += 1;
                }
                if(profit.getBelongOther() == 1){
                    totalBelongOther += 1;
                }
                num +=1;
            }
        }

        JSONObject jsonObject = new JSONObject();
        BigDecimal isGrowRate = BigDecimal.ZERO;
        String isGrowLevel = "";
        BigDecimal isBlongRate = BigDecimal.ZERO;
        String isBlongLevel = "";
        String belongOtherLevel = "";
        BigDecimal belongOtherRate = BigDecimal.ZERO;
        String rightRightLevel = "";
        BigDecimal rightRightRate = BigDecimal.ZERO ;
        if(num > 0){
            isGrowRate = BigDecimal.valueOf(totalIsGrow).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            if(isGrowRate.compareTo(BigDecimal.valueOf(80)) >=0){
                isGrowLevel = "S";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(70)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(80)) < 0){
                isGrowLevel = "A";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(60)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(70)) < 0){
                isGrowLevel = "B";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(50)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(60)) < 0){
                isGrowLevel = "C";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(40)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(50)) < 0){
                isGrowLevel = "D";
            }else if(isGrowRate.compareTo(BigDecimal.valueOf(30)) >=0 && isGrowRate.compareTo(BigDecimal.valueOf(40)) < 0){
                isGrowLevel = "E";
            }else{
                isGrowLevel = "F";
            }

            isBlongRate = BigDecimal.valueOf(totalIsBelong).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            if(isBlongRate.compareTo(BigDecimal.valueOf(80)) >=0){
                isBlongLevel = "S";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(70)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(80)) < 0){
                isBlongLevel = "A";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(60)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(70)) < 0){
                isBlongLevel = "B";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(50)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(60)) < 0){
                isBlongLevel = "C";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(40)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(50)) < 0){
                isBlongLevel = "D";
            }else if(isBlongRate.compareTo(BigDecimal.valueOf(30)) >=0 && isBlongRate.compareTo(BigDecimal.valueOf(40)) < 0){
                isBlongLevel = "E";
            }else{
                isBlongLevel = "F";
            }

            belongOtherRate = BigDecimal.valueOf(totalBelongOther).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            if(belongOtherRate.compareTo(BigDecimal.valueOf(80)) >=0){
                belongOtherLevel = "S";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(70)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(80)) < 0){
                belongOtherLevel = "A";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(60)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(70)) < 0){
                belongOtherLevel = "B";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(50)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(60)) < 0){
                belongOtherLevel = "C";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(40)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(50)) < 0){
                belongOtherLevel = "D";
            }else if(belongOtherRate.compareTo(BigDecimal.valueOf(30)) >=0 && belongOtherRate.compareTo(BigDecimal.valueOf(40)) < 0){
                belongOtherLevel = "E";
            }else{
                belongOtherLevel = "F";
            }


            rightRightRate = (isGrowRate.multiply(BigDecimal.valueOf(0.6))).add(isBlongRate.multiply(BigDecimal.valueOf(0.3))).add(belongOtherRate.multiply(BigDecimal.valueOf(0.1))).setScale(2,BigDecimal.ROUND_HALF_UP);
            if(rightRightRate.compareTo(BigDecimal.valueOf(80)) >=0){
                rightRightLevel = "S";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(70)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(80)) < 0){
                rightRightLevel = "A";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(60)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(70)) < 0){
                rightRightLevel = "B";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(50)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(60)) < 0){
                rightRightLevel = "C";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(40)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(50)) < 0){
                rightRightLevel = "D";
            }else if(rightRightRate.compareTo(BigDecimal.valueOf(30)) >=0 && rightRightRate.compareTo(BigDecimal.valueOf(40)) < 0){
                rightRightLevel = "E";
            }else{
                rightRightLevel = "F";
            }
        }

        jsonObject.put("isGrowRate" , isGrowRate);
        jsonObject.put("isGrowLevel" , isGrowLevel);
        jsonObject.put("isBlongRate" , isBlongRate);
        jsonObject.put("isBlongLevel" , isBlongLevel);
        jsonObject.put("belongOtherRate" , belongOtherRate);
        jsonObject.put("belongOtherLevel" , belongOtherLevel);
        jsonObject.put("rightRightRate" , rightRightRate);
        jsonObject.put("rightRightLevel" , rightRightLevel);
        return jsonObject;
    }

    private JSONObject stockCompanyAssetLevel(List<StockCompanyAssetVO> list){
        String nowYear = DateUtils.getYear();
        String nineYear = Integer.parseInt(nowYear) - 9 + "";
        String eightYear = Integer.parseInt(nowYear) - 8 + "";
        String sevenYear = Integer.parseInt(nowYear) - 7 + "";
        String sixYear = Integer.parseInt(nowYear) - 6 + "";
        String fiveYear = Integer.parseInt(nowYear) - 5 + "";
        String fourYear = Integer.parseInt(nowYear) - 4 + "";
        String threeYear = Integer.parseInt(nowYear) - 3 + "";
        String twoYear = Integer.parseInt(nowYear) - 2 + "";
        String oneYear = Integer.parseInt(nowYear) - 1 + "";

        BigDecimal totalGrowLevel = BigDecimal.ZERO;
        BigDecimal totalAssetDebtLevel = BigDecimal.ZERO;
        BigDecimal totalAssetLevel = BigDecimal.ZERO;
        int num = 0;
        for(StockCompanyAssetVO assetVO : list){
            if(nineYear.equals(assetVO.getPublishYear()) ||
                    eightYear.equals(assetVO.getPublishYear()) ||
                    sevenYear.equals(assetVO.getPublishYear()) ||
                    sixYear.equals(assetVO.getPublishYear()) ||
                    fiveYear.equals(assetVO.getPublishYear()) ||
                    fourYear.equals(assetVO.getPublishYear()) ||
                    threeYear.equals(assetVO.getPublishYear()) ||
                    twoYear.equals(assetVO.getPublishYear()) ||
                    oneYear.equals(assetVO.getPublishYear()) ||
                    nowYear.equals(assetVO.getPublishYear())){
                totalGrowLevel = totalGrowLevel.add(assetVO.getGrowLevel());
                totalAssetDebtLevel = totalAssetDebtLevel.add(assetVO.getAssetDebtLevel());
                totalAssetLevel = totalAssetLevel.add(assetVO.getAssetLevel());
                num +=1;
            }
        }
        BigDecimal averageGrowLevel = BigDecimal.ZERO;
        BigDecimal averageAssetDebtLevel = BigDecimal.ZERO;
        BigDecimal averageAssetLevel = BigDecimal.ZERO;
        if(num > 0){
            averageGrowLevel = totalGrowLevel.divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            averageAssetDebtLevel = totalAssetDebtLevel.divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
            averageAssetLevel = totalAssetLevel.divide(BigDecimal.valueOf(num) ,2 ,BigDecimal.ROUND_HALF_UP);
        }

        JSONObject jsonObject = new JSONObject();

        String isGrowLevel = "";
        if(averageGrowLevel.compareTo(BigDecimal.valueOf(80)) >=0){
            isGrowLevel = "S";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(70)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(80)) < 0){
            isGrowLevel = "A";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(60)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(70)) < 0){
            isGrowLevel = "B";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(50)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(60)) < 0){
            isGrowLevel = "C";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(40)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(50)) < 0){
            isGrowLevel = "D";
        }else if(averageGrowLevel.compareTo(BigDecimal.valueOf(30)) >=0 && averageGrowLevel.compareTo(BigDecimal.valueOf(40)) < 0){
            isGrowLevel = "E";
        }else{
            isGrowLevel = "F";
        }
        jsonObject.put("averageGrowLevel" , averageGrowLevel);
        jsonObject.put("growLevel" , isGrowLevel);

        String assetDebtLevel = "";
        if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(80)) >=0){
            assetDebtLevel = "S";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(70)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(80)) < 0){
            assetDebtLevel = "A";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(60)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(70)) < 0){
            assetDebtLevel = "B";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(50)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(60)) < 0){
            assetDebtLevel = "C";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(40)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(50)) < 0){
            assetDebtLevel = "D";
        }else if(averageAssetDebtLevel.compareTo(BigDecimal.valueOf(30)) >=0 && averageAssetDebtLevel.compareTo(BigDecimal.valueOf(40)) < 0){
            assetDebtLevel = "E";
        }else{
            assetDebtLevel = "F";
        }
        jsonObject.put("averageAssetDebtLevel" , averageAssetDebtLevel);
        jsonObject.put("assetDebtLevel" , assetDebtLevel);

        String assetLevel = "";
        if(averageAssetLevel.compareTo(BigDecimal.valueOf(80)) >=0){
            assetLevel = "S";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(70)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(80)) < 0){
            assetLevel = "A";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(60)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(70)) < 0){
            assetLevel = "B";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(50)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(60)) < 0){
            assetLevel = "C";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(40)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(50)) < 0){
            assetLevel = "D";
        }else if(averageAssetLevel.compareTo(BigDecimal.valueOf(30)) >=0 && averageAssetLevel.compareTo(BigDecimal.valueOf(40)) < 0){
            assetLevel = "E";
        }else{
            assetLevel = "F";
        }
        jsonObject.put("averageAssetLevel" , averageAssetLevel);
        jsonObject.put("assetLevel" , assetLevel);


        BigDecimal assetDebtRate = (averageGrowLevel.multiply(BigDecimal.valueOf(0.7))).add(averageAssetDebtLevel.multiply(BigDecimal.valueOf(0.2))).add(averageAssetLevel.multiply(BigDecimal.valueOf(0.1))).setScale(2,BigDecimal.ROUND_HALF_UP);
        String assetDebtRateLevel = "";
        if(assetDebtRate.compareTo(BigDecimal.valueOf(80)) >=0){
            assetDebtRateLevel = "S";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(70)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(80)) < 0){
            assetDebtRateLevel = "A";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(60)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(70)) < 0){
            assetDebtRateLevel = "B";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(50)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(60)) < 0){
            assetDebtRateLevel = "C";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(40)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(50)) < 0){
            assetDebtRateLevel = "D";
        }else if(assetDebtRate.compareTo(BigDecimal.valueOf(30)) >=0 && assetDebtRate.compareTo(BigDecimal.valueOf(40)) < 0){
            assetDebtRateLevel = "E";
        }else{
            assetDebtRateLevel = "F";
        }
        jsonObject.put("assetDebtRate" , assetDebtRate);
        jsonObject.put("assetDebtRateLevel" , assetDebtRateLevel);
        return jsonObject;
    }


    @Test
    public void test009(){
        stockRiseRateService.createWeekDealReport(12 , null);
    }

/**
 * 2020年前目标：
 *1、背股票公司信息
 *   ①11月——信息技术
 *         ——电信业务
 *         ——可选消费
 *         ——必要消费
 *   ②12月——工业
 *         ——医疗卫生
 *         ——金融地产
 *         ——公用事业
 *2、复习英语
 *   ①背单词——11月——500词汇
 *               12月——500词汇
 *   ②复习课程——11月——3、4、5、6
 *             ——12月——7、8、9、10
 *   ③升到12级——11月——第三周
 *             ——12月——月底
 *3、进行股票热点分析——2小时
 *   ①看新闻——
 *   ②找规律——
 *   ③猜热点——
 *4、进行外汇规律分析
 *   ①找各个指数对价格的影响规律——11月——利率、非农、PMI、CPI
 *                               ——12月——零售、屋建、收入、订单
 *   ②查看新闻看各类新闻对价格的影响规律——新闻分类
 *                                       ——分类影响
 *   ③模拟交易——每周3次
 *5、看书
 *   ①每天两本各一节——11月——操盘
 *                           ——时间管理
 *                           ——原则
 *
 *6、开发程序
 *   ①开发交易系统程序——
 *   ②学习工具编程语言——认识方法
 *                     ——练习编写
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
