package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/10/23.
 */
@Data
public class StockCompanyProfitVO extends VoModel {

    private String publishYear ;

    private BigDecimal totalProfit ;

    private BigDecimal belongProfit;

    private BigDecimal firstSeasonProfit ;

    private BigDecimal secondSeasonProfit ;

    private BigDecimal thirdSeasonProfit ;

    private BigDecimal forthSeasonProfit ;

    private BigDecimal totalProfitRate ;

    private BigDecimal belongProfitRate ;

    private BigDecimal finalProfitRate ;

    private BigDecimal otherCompanyProfitRate ;

    private BigDecimal totalProfitGrowRate;

    private BigDecimal mainBusinessProfitRate;

    private Integer isGrow ;

    private Integer isBelong ;

    private Integer belongOther ;

}
