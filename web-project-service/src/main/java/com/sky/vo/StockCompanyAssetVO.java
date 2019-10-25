package com.sky.vo;

import com.sky.core.model.VoModel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by ThinkPad on 2019/10/24.
 */
@Data
public class StockCompanyAssetVO extends VoModel {

    private String publishYear ;

    private BigDecimal totalAsset ;

    private BigDecimal totalDebt ;

    private BigDecimal pureAsset ;

    private BigDecimal debtAssetRate ;

    private BigDecimal pureAssetRate ;

    private BigDecimal totalFlowAssetRate ;

    private BigDecimal totalUnflowAssetRate ;

    private BigDecimal useAssetRate ;

    private BigDecimal billAssetRate ;

    private BigDecimal flowOtherAssetRate ;

    private BigDecimal fixedAssetRate ;

    private BigDecimal investAssetRate ;

    private BigDecimal virtualAssetRate ;

    private BigDecimal deferredAssetRate ;

    private BigDecimal unflowOtherAssetRate ;

    private BigDecimal flowDebtRate ;

    private BigDecimal unflowDebtRate ;

    private BigDecimal loanDebtRate ;

    private BigDecimal billDebtRate ;

    private BigDecimal payDebtRate ;

    private BigDecimal otherDebtRate ;

    private BigDecimal unflowLoanDebtRate ;

    private BigDecimal unflowPayDebtRate ;

    private BigDecimal unflowDeferredDebtRate ;

    private BigDecimal unflowOtherDebtRate ;

    private BigDecimal growLevel ;

    private BigDecimal assetDebtLevel ;

    private BigDecimal assetLevel ;
}
