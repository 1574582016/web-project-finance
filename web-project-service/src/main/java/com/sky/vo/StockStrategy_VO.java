package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/11/11.
 */
@Data
public class StockStrategy_VO extends VoModel {

    private String dealTime ;

    private String stockCode ;

    private String stockName ;

    private String forthSector ;

    private String companyLevel ;

    private String financialLevel ;

    private BigDecimal openPrice ;

    private BigDecimal closePrice ;

    private BigDecimal highPrice ;

    private BigDecimal lowPrice ;

    private BigDecimal averagePrice ;

    private BigDecimal standarPrice ;

    private BigDecimal topPrice ;

    private BigDecimal bottomPrice ;

    private BigDecimal topDistance ;

    private BigDecimal middleDistance ;

    private BigDecimal bottomDistance ;

    private BigDecimal averageStock ;

}
