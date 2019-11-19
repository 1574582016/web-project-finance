package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/11/19.
 */
@Data
public class StockIndexWeekData_VO extends VoModel {

    private String pointTime ;

    private BigDecimal oneRiseRate ;

    private BigDecimal towRiseRate ;

    private BigDecimal threeRiseRate ;

    private BigDecimal fourRiseRate ;

    private BigDecimal fiveRiseRate ;

    private BigDecimal sixRiseRate ;

    private BigDecimal oneUpperAverage ;

    private BigDecimal towUpperAverage ;

    private BigDecimal threeUpperAverage ;

    private BigDecimal fourUpperAverage ;

    private BigDecimal fiveUpperAverage ;

    private BigDecimal sixUpperAverage ;


    private BigDecimal oneShockAverage ;

    private BigDecimal towShockAverage ;

    private BigDecimal threeShockAverage ;

    private BigDecimal fourShockAverage ;

    private BigDecimal fiveShockAverage ;

    private BigDecimal sixShockAverage ;
}
