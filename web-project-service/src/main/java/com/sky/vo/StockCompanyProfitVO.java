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

    private BigDecimal operateCost ;

    private BigDecimal belongProfit;

    private BigDecimal mainBusinessProfit ;

    private BigDecimal viceBusinessProfit ;

    private BigDecimal otherProfit ;

    private BigDecimal firstProfitRate ;

    private BigDecimal secondProfitRate ;

    private BigDecimal threeProfitRate ;

    private BigDecimal forthtProfitRate ;

    private BigDecimal operateProfitRate ;

    private BigDecimal belongProfitRate ;

    private BigDecimal finalProfitRate ;

    private BigDecimal otherCompanyProfitRate ;

    private BigDecimal totalProfitGrowRate;

    private BigDecimal mainBusinessProfitRate;

    private Integer isGrow ;

    private Integer isBelong ;

    private Integer belongOther ;

}
