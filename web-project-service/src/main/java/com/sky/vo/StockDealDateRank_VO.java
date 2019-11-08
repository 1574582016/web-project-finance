package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/11/8.
 */
@Data
public class StockDealDateRank_VO extends VoModel {

    private String stockCode ;

    private String dealTime ;

    private BigDecimal openPrice ;

    private BigDecimal lowPrice ;

    private BigDecimal highPrice ;

    private BigDecimal closePrice ;

    private BigDecimal averagePrice ;

    private BigDecimal standarPrice ;

    private BigDecimal topPrice ;

    private BigDecimal bottomPrice ;

    private BigDecimal topDistance ;

    private BigDecimal bottomDistance ;

    private BigDecimal middleDistance ;

    private BigDecimal averageStock ;

    private BigDecimal isUpper ;

    private BigDecimal isTop ;

    private BigDecimal isMiddle ;

    private BigDecimal isBottom ;
}
