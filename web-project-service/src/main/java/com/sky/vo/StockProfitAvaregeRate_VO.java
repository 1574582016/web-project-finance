package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

/**
 * Created by ThinkPad on 2019/12/30.
 */
@Data
public class StockProfitAvaregeRate_VO extends VoModel {

    private String forthSector ;

    private String stockCode ;

    private String stockName ;

    private String publishTime ;

    private String spaceYear ;

    private String averageGrowRate ;

    private String investor ;

    private String averageGrowProfit ;

    private String averageIncreaseRate ;

    private String totalIncreaseRate ;

    private String firstIncreaseRate ;

    private String secondIncreaseRate ;

    private String thirdIncreaseRate ;

    private String forthIncreaseRate ;
}
