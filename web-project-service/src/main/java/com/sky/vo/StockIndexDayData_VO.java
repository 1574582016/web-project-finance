package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/11/19.
 */
@Data
public class StockIndexDayData_VO extends VoModel {

    private String pointMonth ;

    private String pointWeek ;

    private BigDecimal oneRiseRate ;

    private BigDecimal towRiseRate ;

    private BigDecimal threeRiseRate ;

    private BigDecimal fourRiseRate ;

    private BigDecimal fiveRiseRate ;

    private BigDecimal oneUpperAverage ;

    private BigDecimal towUpperAverage ;

    private BigDecimal threeUpperAverage ;

    private BigDecimal fourUpperAverage ;

    private BigDecimal fiveUpperAverage ;


    private BigDecimal oneShockAverage ;

    private BigDecimal towShockAverage ;

    private BigDecimal threeShockAverage ;

    private BigDecimal fourShockAverage ;

    private BigDecimal fiveShockAverage ;

}