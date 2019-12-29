package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2019/12/27/027.
 */
@Data
public class CompanyProfit_VO extends VoModel {

    private String stockCode ;

    private Integer publishYear ;

    private BigDecimal totalProfit ;

    private BigDecimal maxProfit ;

    private BigDecimal minProfit ;

    private BigDecimal increaseRate ;

    private BigDecimal totalProfitIncreaseRate ;

    private BigDecimal firstSeasonIncreaseRate ;

    private BigDecimal secondSeasonIncreaseRate ;

    private BigDecimal thirdSeasonIncreaseRate ;

    private BigDecimal forthSeasonIncreaseRate ;

    private Integer minYear ;

    private Integer maxYear ;

    private Integer spaceYear ;
}
